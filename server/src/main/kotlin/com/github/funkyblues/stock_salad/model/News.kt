package com.github.funkyblues.stock_salad.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "news")
data class News(
    @Id
    val id: String? = null,
    val url: String,
    val title: String,
    val description: String,
    val categoryId: Int? = null,
    val categoryName: String? = null,
    val writeDateTime: String? = null,
    val modifyDateTime: String? = null,
    val journalist: String? = null
)
