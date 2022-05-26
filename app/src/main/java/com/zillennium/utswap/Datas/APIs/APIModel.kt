package com.zillennium.utswap.Datas.APIs


object APIModel {

    class MarketMain {
        val market: List<Market> by lazy { emptyList() }
    }
    class Market {
        val id: Int? by lazy { null }
        val ticker: String? by lazy { null }
        val fee_buy: Double? by lazy { null }
        val fee_sell: Double? by lazy { null }
        val name: String? by lazy { null }
        val icon: String? by lazy { null }
        val new_price: Double? by lazy { null }
        val buy_price: Double? by lazy { null }
        val sell_price: Double? by lazy { null }
        val min_price: Double? by lazy { null }
        val max_price: Double? by lazy { null }
        val change: Int? by lazy { null }
        val volume: Int? by lazy { null }
    }
    class MarketModel {
        val status: Int by lazy { 0 }
        val message: String by lazy { "hello" }
        val datas: MarketMain by lazy { MarketMain() }
    }
}