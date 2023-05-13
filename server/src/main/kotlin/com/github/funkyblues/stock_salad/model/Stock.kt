package com.github.funkyblues.stock_salad.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "stocks")
data class Stock(
    @Id
    val id: String? = null,
    val name: String,
    val price: String
)
