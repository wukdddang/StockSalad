package com.github.funkyblues.stock_salad.service

import com.github.funkyblues.stock_salad.Constants
import com.github.funkyblues.stock_salad.Settings
import com.github.funkyblues.stock_salad.model.StockPrice
import com.github.funkyblues.stock_salad.model.StockResponse
import com.github.funkyblues.stock_salad.model.StockResponseWrapper
import com.github.funkyblues.stock_salad.util.MongoDBUtil
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.dao.DuplicateKeyException
import org.springframework.web.reactive.function.client.WebClient
import java.net.URI
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@Component
class StockFetcher {
    // Fetch stock every day at 12:00 PM
    @Scheduled(cron = "0 0 12 * * *")
    fun fetchStock() {
        if (!Settings.fetchStockEnabled) {
            return
        }

        val mongoTemplate = MongoDBUtil.getMongoTemplate()

        for (date in getValidDateList()) {
            for (page in 1..Constants.Stock.MAX_PAGE) {
                val keepCalling = fetchStockFromKorAPI(date, page) { stockPrice ->
                    try {
                        mongoTemplate.insert(stockPrice)
                    } catch (e: DuplicateKeyException) {
                        // Ignore duplicated key error
                    }
                    try {
                        val stock = stockPrice.getStock()
                        mongoTemplate.insert(stock)
                    } catch (e: DuplicateKeyException) {
                        // Ignore duplicated key error
                    }
                }
                Thread.sleep(Constants.Stock.SLEEP_TIME)

                if (!keepCalling) {
                    break
                }
            }
        }
    }

    fun getValidDateList(): List<String> {
        val currentDateTime = LocalDateTime.now()
        val validStartDate = currentDateTime.minusDays(1).toLocalDate()
        val validEndDate = currentDateTime.minusMonths(1).toLocalDate()

        return validEndDate.datesUntil(validStartDate.plusDays(1)) // end date is exclusive in datesUntil, so we add one day
            .map { it.format(DateTimeFormatter.ofPattern(Constants.News.DATE_PATTERN)) }
            .toList()
            .reversed()
    }

    /**
     * @return If true, the function should be called again, if false, it should stop.
     */
    fun fetchStockFromKorAPI(date: String, page: Int, insertStock: (StockPrice) -> Unit): Boolean {
        val url = Constants.Stock.QUERY_URL.format(URLEncoder.encode(Settings.apiKey, StandardCharsets.UTF_8),
            Constants.Stock.NUM_OF_ROWS, page, Constants.Stock.RESULT_TYPE, date)
        val client = WebClient.create(Constants.Stock.QUERY_URL)
        val response: StockResponse = client.get()
            .uri(URI.create(url))
            .header("accept", "application/json")
            .retrieve().bodyToMono(StockResponseWrapper::class.java).block()!!.response
        val stockPrices = response.body.items.item
        for (stockPrice in stockPrices) {
            insertStock(stockPrice)
        }

        println("[StockFetcher] Stock updated for date: $date, " +
                "page: $page, size: ${stockPrices.size}, " +
                "top item: ${stockPrices.firstOrNull()?.getStock()?.itmsNm}")

        return stockPrices.isNotEmpty()
    }
}