package com.github.funkyblues.stock_salad.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "news_url")
data class NewsURL(
    @Id
    val id: String? = null,
    val url: String,
    val isDetailCreated: Boolean = false,
    val failCount: Int = 0
)