package com.zillennium.utswap.models.tradingList

object TradingList {
    class TradingListRes{
        var market_trend: TradingMarketTrend? = null
    }

    class TradingMarketTrend{
        var info: String? = null
        var status: String? = null
        var url: ArrayList<List<Any>>? = arrayListOf()
    }

    class TradingListDetailRes{
        var market_summary: TradingListSummary? = null
    }

    class TradingListSummary{
        var info: TradingListDetailInfo? = null
    }

    class TradingListDetailInfo{
        var title: String? = null
        var new_price: String? = null
        var max_price: String? = null
        var min_price: String? = null
        var buy_price: String? = null
        var sell_price: String? = null
        var volume: String? = null
        var change: String? = null
    }
}