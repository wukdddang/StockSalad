package com.github.funkyblues.stock_salad.controller

import com.github.funkyblues.stock_salad.model.News
import com.github.funkyblues.stock_salad.model.NewsResponse
import com.github.funkyblues.stock_salad.util.MongoDBUtil
import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class NewsController {
    @GetMapping("/news/all")
    fun getAllNews(): String {
        return "This API is removed."
//        val mongoTemplate = MongoDBUtil.getMongoTemplate()
//        return mongoTemplate.findAll(News::class.java)
    }

    @GetMapping("/news")
    fun getNews(
        @RequestParam("isinCd") isinCd: String,
        @RequestParam("count", defaultValue = "10") count: Int): List<NewsResponse> {
        val mongoTemplate = MongoDBUtil.getMongoTemplate()

        val criteria = Criteria.where("isinCdList").`is`(isinCd)
        val query = Query.query(criteria).with(Sort.by(Sort.Direction.DESC, "writeDateTime")).limit(count)

        return mongoTemplate.find(query, News::class.java).map { it.getNewsResponse() }
    }
}
