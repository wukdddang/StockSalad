package com.github.funkyblues.stock_salad.controller

import com.github.funkyblues.stock_salad.model.StockPrice
import com.github.funkyblues.stock_salad.model.StockResponse
import com.github.funkyblues.stock_salad.util.MongoDBUtil
import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.web.bind.annotation.*

@RestController
class StockController {
    @GetMapping("/stock/all")
    fun getAllStock(): List<StockResponse> {
        val mongoTemplate = MongoDBUtil.getMongoTemplate()
        // 가장 최신의 basDt 찾기
        val latestBasDt: String? = mongoTemplate
            .find(Query().limit(1).with(Sort.by(Sort.Direction.DESC, "basDt")), StockPrice::class.java)
            .map { it.basDt }
            .firstOrNull()

        // 가장 최신 basDt를 가진 StockPrice 모두 찾기
        return mongoTemplate
            .find(Query(Criteria.where("basDt").`is`(latestBasDt)), StockPrice::class.java)
            .map { it.getStockResponse() }
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
