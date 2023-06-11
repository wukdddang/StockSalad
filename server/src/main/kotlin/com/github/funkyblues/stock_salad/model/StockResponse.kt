package com.github.funkyblues.stock_salad.model

data class StockResponseWrapper(
    val response: StockResponse
)

data class StockResponse(
    val header: Header,
    val body: Body
)

data class Header(
    val resultCode: String,
    val resultMsg: String
)

data class Body(
    val numOfRows: Int,
    val pageNo: Int,
    val totalCount: Int,
    val items: ItemWrapper
)

data class ItemWrapper(
    val item: List<StockPrice>
)
