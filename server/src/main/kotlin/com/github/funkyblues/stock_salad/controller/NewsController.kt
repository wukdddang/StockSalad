package com.github.funkyblues.stock_salad.controller

import com.github.funkyblues.stock_salad.model.News
import com.github.funkyblues.stock_salad.model.DateRangeRequest
import com.github.funkyblues.stock_salad.util.MongoDBUtil
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
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
        @RequestParam startDate: String,
        @RequestParam endDate: String): String {
        return "This API is removed."
//        val mongoTemplate = MongoDBUtil.getMongoTemplate()
//        val startDateTime = "$startDate 00:00:00"
//        val endDateTime = "$endDate 23:59:59"
//        val criteria = Criteria.where("writeDateTime").gte(startDateTime).lte(endDateTime)
//        val query = Query.query(criteria)
//        return mongoTemplate.find(query, News::class.java)
    }
}
