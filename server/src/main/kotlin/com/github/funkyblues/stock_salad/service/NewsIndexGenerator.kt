package com.github.funkyblues.stock_salad.service

import AhoCorasick
import com.github.funkyblues.stock_salad.Constants
import com.github.funkyblues.stock_salad.model.News
import com.github.funkyblues.stock_salad.model.Stock
import com.github.funkyblues.stock_salad.util.MongoDBUtil
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class NewsIndexGenerator {
    @Scheduled(fixedDelay = Constants.News.REQUEST_PERIOD)
    fun generateNewsIndex() {

        // 1. Create MongoDB connection
        val mongoTemplate = MongoDBUtil.getMongoTemplate()

        // 2. Load stock list
        val stockList = mongoTemplate.findAll(Stock::class.java).map { Pair(it.itmsNm, it.isinCd) }
        val ahoCorasick = AhoCorasick(stockList)

        // 3. Get list of saved news that not indexed yet
        val query = Query(Criteria
            .where("isinCdList").`is`(null))
            .limit(Constants.News.LIMIT_OF_FIND_NOT_INDEXED_NEWS)
        val notIndexedNewsList = mongoTemplate.find(query, News::class.java)

        // 4. Update news
        for (news in notIndexedNewsList) {
            val isinCdList = ahoCorasick.searchWordsIn(news.title + "\n" + news.description)
            mongoTemplate.updateFirst(
                Query(Criteria.where("_id").`is`(news.id)),
                Update().set("isinCdList", isinCdList), News::class.java
            )
        }

        println("[NewsIndexGenerator] News index generated")
    }
}