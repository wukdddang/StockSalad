package com.github.funkyblues.stock_salad.controller

import com.github.funkyblues.stock_salad.model.News
import com.github.funkyblues.stock_salad.util.MongoDBUtil
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class NewsController {
    @GetMapping("/news")
    fun getAllNews(): List<News> {
        val mongoTemplate = MongoDBUtil.getMongoTemplate()
        return mongoTemplate.findAll(News::class.java)
    }
}
