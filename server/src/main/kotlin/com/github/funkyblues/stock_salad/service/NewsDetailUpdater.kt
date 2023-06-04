package com.github.funkyblues.stock_salad.service

import com.github.funkyblues.stock_salad.Constants
import com.github.funkyblues.stock_salad.Settings
import com.github.funkyblues.stock_salad.model.News
import com.github.funkyblues.stock_salad.model.NewsURL
import com.github.funkyblues.stock_salad.util.MongoDBUtil
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.lang.Exception

@Component
class NewsDetailUpdater {
    @Scheduled(fixedDelay = Constants.News.REQUEST_PERIOD)
    fun updateNewsDetail() {
        if (!Settings.fetchNewsEnabled) {
            return
        }

        // 1. Create MongoDB connection
        val mongoTemplate = MongoDBUtil.getMongoTemplate()

        // 2. Get list of saved news that not updated yet
        val query = Query(Criteria
            .where("isDetailCreated").`is`(false)
            .and("failCount").lte(Constants.News.MAX_FAIL_COUNT))
        val notUpdatedNewsList: List<String> = mongoTemplate.find(query, NewsURL::class.java).map { it.url }

        // 3. Update news
        fetchNewsFromNaver(notUpdatedNewsList) { news ->
            try {
                mongoTemplate.insert(news)
                mongoTemplate.updateFirst(
                    Query(Criteria.where("url").`is`(news.url)),
                    Update().set("isDetailCreated", true), NewsURL::class.java
                )
            } catch (e: Exception) {
                // Fail to insert news
                mongoTemplate.updateFirst(Query(Criteria.where("url").`is`(news.url)),
                    Update().inc("failCount", 1), NewsURL::class.java)
            }
        }
    }

    private fun fetchNewsFromNaver(notUpdatedNewsList: List<String>, insertNews: (News) -> Unit) {
        for (url in notUpdatedNewsList) {
            val document = Jsoup.connect(url).get()
            val news = getNewsFromDocument(url, document)
            insertNews(news)

            println("[NewsDetailUpdater] News detail updated: $url, ${news.title}, ${news.categoryName}")
            Thread.sleep(Constants.News.SLEEP_TIME)
        }
    }

    private fun getNewsFromDocument(url: String, document: Document): News {
        val title = document.select("h2#title_area > span").text()
        val description = document.select("div#dic_area").text()
        val categoryUrl: String? =
            document.select("li.is_active a.Nitem_link").firstOrNull()?.attr("href")
        val categoryId: Int? = categoryUrl?.split("=")?.lastOrNull()?.toIntOrNull()
        val categoryName = document.select("li.is_active span.Nitem_link_menu").text()
        val writeDateTime = document.select("span.media_end_head_info_datestamp_time")
            .attr("data-date-time")
        val modifyDateTime = document.select("span.media_end_head_info_datestamp_time")
            .attr("data-modify-date-time")
        val journalist = document.select("em.media_end_head_journalist_name").text()

        return News(null, url, title, description, categoryId, categoryName, writeDateTime, modifyDateTime, journalist)
    }
}

