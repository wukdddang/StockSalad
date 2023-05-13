package com.github.funkyblues.stock_salad.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import com.github.funkyblues.stock_salad.model.News
import com.github.funkyblues.stock_salad.model.Stock

@RestController
class TestController {
    @GetMapping("/test/news")
    fun getNewsList(): List<News> {
        return listOf(
            News(
                id = "1",
                url = "https://news.example.com/1",
                title = "News Title 1",
                description = "News Description 1",
                summary = "News Summary 1",
                dateTime = "2023-05-13T10:15:30"
            ),
            News(
                id = "2",
                url = "https://news.example.com/2",
                title = "News Title 2",
                description = "News Description 2",
                summary = "News Summary 2",
                dateTime = "2023-05-13T10:15:31"
            ),
            News(
                id = "3",
                url = "https://news.example.com/3",
                title = "News Title 3",
                description = "News Description 3",
                summary = "News Summary 3",
                dateTime = "2023-05-13T10:15:32"
            )
        )
    }

    @GetMapping("/test/stock")
    fun getStockList(): List<Stock> {
        return listOf(
            Stock(
                id = "1",
                name = "Company 1",
                price = "100.00"
            ),
            Stock(
                id = "2",
                name = "Company 2",
                price = "200.00"
            ),
            Stock(
                id = "3",
                name = "Company 3",
                price = "300.00"
            ),
            Stock(
                id = "4",
                name = "Company 4",
                price = "400.00"
            ),
            Stock(
                id = "5",
                name = "Company 5",
                price = "500.00"
            )
        )
    }
}
