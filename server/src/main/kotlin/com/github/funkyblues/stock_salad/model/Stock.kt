package com.github.funkyblues.stock_salad.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "stock")
data class Stock(
    @Id
    val id: String? = null,
    val srtnCd: String, // 단축코드
    val isinCd: String, // ISIN코드
    val itmsNm: String, // 종목명
    val mrktCtg: String, // 시장구분
)
