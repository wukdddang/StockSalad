package com.github.funkyblues.stock_salad.service

import com.github.funkyblues.stock_salad.Constants
import com.github.funkyblues.stock_salad.Settings
import com.github.funkyblues.stock_salad.util.MongoDBUtil
import com.github.funkyblues.stock_salad.model.NewsURL
import org.jsoup.Jsoup
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Query
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.dao.DuplicateKeyException
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@Component
class NewsURLFetcher {
    @Scheduled(fixedDelay = Constants.News.REQUEST_PERIOD)
    fun fetchNewsURL() {
        if (!Settings.fetchNewsEnabled) {
            return
        }

        // 1. Create MongoDB connection
        val mongoTemplate = MongoDBUtil.getMongoTemplate()

        // 2. Get latest saved news list from MongoDB to prevent duplication
        val latestSavedNewsList = HashSet(getNewsFromMongodb(mongoTemplate))

        // 3. Fetch latest news list
        for (date in getValidDateList()) {
            for (page in 1..Constants.News.MAX_PAGE) {
                val keepCalling = fetchNewsURLFromNaver(date, page, latestSavedNewsList) { url ->
                    try {
                        mongoTemplate.insert(NewsURL(url = url))
                    } catch (e: DuplicateKeyException) {
                        // Ignore duplicated key error
                    }
                }
                Thread.sleep(Constants.News.SLEEP_TIME)

                if (!keepCalling) {
                    break
                }
            }
        }
    }

    fun getValidDateList() : List<String> {
        val currentDateTime = LocalDateTime.now()
        val validStartDate = currentDateTime.minusDays(2).toLocalDate()
        val validEndDate = currentDateTime.minusDays(9).toLocalDate()

        return validEndDate.datesUntil(validStartDate.plusDays(1)) // end date is exclusive in datesUntil, so we add one day
            .map { it.format(DateTimeFormatter.ofPattern(Constants.News.DATE_PATTERN)) }
            .toList()
            .reversed()
    }

    /**
     * @return If true, the function should be called again, if false, it should stop.
     */
    fun fetchNewsURLFromNaver(date: String, page: Int, latestSavedNewsSet: Set<String>, insertNewsURL: (String) -> Unit): Boolean {
        val url = Constants.News.QUERY_URL.format(date, page)
        val document = Jsoup.connect(url).get()
        val fetchedNewsList = getNewsURLFromDocument(document)
        val parsedPage = getPageFormDocument(document)

        // If the page is not parsed or the page is end, stop fetching
        println("[NewsURLFetcher] url: $url, page: $parsedPage")
        if (parsedPage != null && parsedPage != page) {
            println("[NewsURLFetcher] page mismatched. parsedPage: $parsedPage, page: $page")
            return false
        }

        for (newsURL in fetchedNewsList) {
            // If the news is already saved, stop fetching
            if (latestSavedNewsSet.contains(newsURL)) {
                return false
            }
            insertNewsURL(newsURL)
        }

        return true
    }

    fun getNewsURLFromDocument(document: org.jsoup.nodes.Document) : List<String> {
        val latestNewsList = ArrayList<String>()
        val titleList = ArrayList<String>()

        for (element in document.select("div.newsflash_body li")) {
            val title = element.select("a").text()
            val url = element.select("a").attr("href")

            latestNewsList.add(url)
            titleList.add(title)
        }

        if (titleList.isNotEmpty()) {
            println("[NewsURLFetcher] size : " + titleList.size + ", title: " + titleList[0] + ", url : " + latestNewsList[0])
        }

        return latestNewsList
    }

    fun getPageFormDocument(document: org.jsoup.nodes.Document) : Int? {
        val page = document.select("div.paging strong").last()?.text()
        return page?.toInt()
    }

    fun getNewsFromMongodb(mongoTemplate: MongoTemplate): List<String> {
        val doc = mongoTemplate.find(Query(), NewsURL::class.java)
        return doc.map {it.url}
    }
}