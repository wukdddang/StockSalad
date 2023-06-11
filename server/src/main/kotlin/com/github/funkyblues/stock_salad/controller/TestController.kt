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
                writeDateTime = "2023-05-13T10:15:30"
            ),
            News(
                id = "2",
                url = "https://news.example.com/2",
                title = "News Title 2",
                description = "News Description 2",
                writeDateTime = "2023-05-13T10:15:31"
            ),
            News(
                id = "3",
                url = "https://news.example.com/3",
                title = "News Title 3",
                description = "News Description 3",
                writeDateTime = "2023-05-13T10:15:32"
            )
        )
    }

    @GetMapping("/test/stock")
    fun getStockList(): List<Stock> {
        return listOf(
            Stock(
                id = "1",
                srtnCd = "123456",
                isinCd = "123456789012",
                itmsNm = "종목명",
                mrktCtg = "코스피",
            ),
            Stock(
                id = "2",
                srtnCd = "123456",
                isinCd = "123456789012",
                itmsNm = "종목명",
                mrktCtg = "코스피",
            ),
            Stock(
                id = "3",
                srtnCd = "123456",
                isinCd = "123456789012",
                itmsNm = "종목명",
                mrktCtg = "코스피",
            )
        )
    }
}
