package com.github.funkyblues.stock_salad

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class StockSaladApplication

fun main(args: Array<String>) {
	println("Hello World!")
	runApplication<StockSaladApplication>(*args)
}
