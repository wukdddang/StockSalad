package com.github.funkyblues.stock_salad.model

data class StockKorAPIResponseWrapper(
    val response: StockKorAPIResponse
)

data class StockKorAPIResponse(
    val header: StockKorAPIResponseHeader,
    val body: StockKorAPIResponseBody
)

data class StockKorAPIResponseHeader(
    val resultCode: String,
    val resultMsg: String
)

data class StockKorAPIResponseBody(
    val numOfRows: Int,
    val pageNo: Int,
    val totalCount: Int,
    val items: StockKorAPIResponseItemWrapper
)

data class StockKorAPIResponseItemWrapper(
    val item: List<StockPrice>
)
