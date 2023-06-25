package com.github.funkyblues.stock_salad.model

data class NewsResponse(
    val url: String,
    val title: String,
    val description: String,
    val categoryId: Int? = null,
    val categoryName: String? = null,
    val writeDateTime: String? = null,
    val modifyDateTime: String? = null,
    val journalist: String? = null
)
