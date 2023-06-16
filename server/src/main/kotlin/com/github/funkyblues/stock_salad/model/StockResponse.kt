package com.github.funkyblues.stock_salad.model

data class StockResponse(
    val srtnCd: String, // 단축코드
    val isinCd: String, // ISIN코드
    val itmsNm: String, // 종목명
    val mrktCtg: String, // 시장구분
    val clpr: String, // 종가
    val fltRt: String, // 등락률
)