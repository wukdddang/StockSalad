package com.github.funkyblues.stock_salad.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "news")
data class News(
    @Id
    val id: String,
    val url: String,
    val title: String? = null,
    val description: String? = null,
    val dateTime: String? = null,
    val summary: String? = null,
)
