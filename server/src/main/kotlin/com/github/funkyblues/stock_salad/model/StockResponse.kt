package com.github.funkyblues.stock_salad.model

data class StockResponse(
    val srtnCd: String, // 단축코드
    val isinCd: String, // ISIN코드
    val itmsNm: String, // 종목명
    val mrktCtg: String, // 시장구분
    val clpr: String, // 종가
    val fltRt: String, // 등락률
)

data class StockPriceResponse(
    val basDt: String, // 기준일자
    val srtnCd: String, // 단축코드
    val isinCd: String, // ISIN코드
    val itmsNm: String, // 종목명
    val mrktCtg: String, // 시장구분
    val clpr: String, // 종가
    val vs: String, // 대비
    val fltRt: String, // 등락률
    val mkp: String, // 시가
    val hipr: String, // 고가
    val lopr: String, // 저가
    val trqu: String, // 거래량
    val trPrc: String, // 거래대금
    val lstgStCnt: String, // 상장주식수
    val mrktTotAmt: String, // 시가총액
)