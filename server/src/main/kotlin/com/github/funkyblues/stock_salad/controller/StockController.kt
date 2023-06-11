package com.github.funkyblues.stock_salad.controller

import com.github.funkyblues.stock_salad.model.Stock
import com.github.funkyblues.stock_salad.model.StockPrice
import com.github.funkyblues.stock_salad.util.MongoDBUtil
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.web.bind.annotation.*

@RestController
class StockController {
    @GetMapping("/stock/all")
    fun getAllStock(): List<Stock> {
        val mongoTemplate = MongoDBUtil.getMongoTemplate()
        return mongoTemplate.findAll(Stock::class.java)
    }

    @GetMapping("/stock/prices")
    fun getStockPrices(
        @RequestParam("isinCd") isinCd: String,
        @RequestParam("startDate") startDate: String,
        @RequestParam("endDate") endDate: String
    ): List<StockPrice> {
        val mongoTemplate = MongoDBUtil.getMongoTemplate()
        val criteria = Criteria
            .where("isinCd").`is`(isinCd)
            .and("basDt").gte(startDate).lte(endDate)
        val query = Query.query(criteria)
        return mongoTemplate.find(query, StockPrice::class.java)
    }
}
