package com.zillennium.utswap.models.tradingList

import com.zillennium.utswap.models.newsService.News

object TradingList {
    class TradingListRes{
        var market_trend: TradingMarketTrend? = null
    }

    class TradingMarketTrend{
        var info: String? = null
        var status: String? = null
        var url: ArrayList<List<Any>>? = arrayListOf()
    }

    /** Trade Detail*/

    class TradingListDetailRes{
        var market_summary: TradingListSummary? = null
    }

    class TradingListSummary{
        var info: TradingListDetailInfo? = null
    }

    class TradingListDetailInfo{
        var title: String? = null
        var new_price: Any? = null
        var max_price: Any? = null
        var min_price: Any? = null
        var buy_price: String? = null
        var sell_price: String? = null
        var volume: String? = null
        var target_price: Any? = null
        var market_open: Boolean? = null
        var change: String? = null

    }

    /** Upcoming project trading list*/
    class TradeUpComingProjectRes{
        var status: Int? = null
        var message: String? = null
        var data: TradeUpComingProjectData? = null
    }

    class TradeUpComingProjectData{
        var project: List<TradeUpComingProjectList>? = null
    }

    class TradeUpComingProjectList{
        var id: String? = null
        var project_name: String? = null
        var status: String? = null
    }

    /** Trade Order Book Table*/
    class TradeOrderBookTableRes{
        var sell: ArrayList<List<Any>>? = arrayListOf()
        var buy: ArrayList<List<Any>>? = arrayListOf()
        var buyvol: String? = null
        var sellvol: String? = null
        var type: String? = null
        var market: String? = null
    }

    /** Check Favorite Project in Trade*/
    class TradeFavoriteProjectRes{
        var status: Int? = null
        var message: String? = null
        var data: TradeFavoriteProjectData? = null
    }

    class TradeFavoriteProjectData{
        var is_favorite: Boolean? = null
    }
    
    class TradeFavoriteProjectObj(
        var issue_id: Int
    )

    /** Add Favorite Project*/
    class TradeAddFavoriteObj(
        var add_fav: Int,
        var issue_id: Int
    )

    class TradeAddFavoriteRes{
        var status: Int? = null
        var message: String? = null
        var data: TradeAddFavoriteData? = null
    }

    class TradeAddFavoriteData{

    }

    /** Trade Create Place Order*/
    class TradeCreateOrderRes{
        var status: Int? = null
        var message: String? = null
        var data: TradeCreateOrderData? =null
    }

    class TradeCreateOrderData{

    }

    class TradeCreateOrderObj(
        var sign_type: String,
        var sign: String,
        var market: String,
        var price: String,
        var num: String,
        var type: String,
        var tradeType: String
    )

    /** Get Trade Order Pending List*/
    class TradeOrderPendingObj(
        var market: String,
        var type: Int,
        var page: Int,
        var sort: String
    )

    class TradeOrderPendingRes{
        var status: Int? = null
        var message: String? = null
        var data: TradeOrderPendingData? = null
    }

    class TradeOrderPendingData{
        var TOTAL_PAGE: Int? = null
        var entrust: List<TradeOrderPendingEntrust>? = null
    }

    class TradeOrderPendingEntrust{
        var market: String? = null
        var addtime: String? =null
        var condition: String? = null
        var stop: String? = null
        var type: String? = null
        var price: String? = null
        var num: Int? = null
        var deal: Int? = null
        var id: String? = null
        var tradetype: String? = null
        var total: String? = null
    }

    /** Matching Trade Transaction*/
    class TradeMatchingTransactionObj(
        var market: String,
        var type: Int,
        var page: Int,
        var sort: String
    )

    class TradeMatchingTransactionRes{
        var status: Int? = null
        var message: String? = null
        var data: TradeMatchingTransactionData? = null
    }

    class TradeMatchingTransactionData{
        var TOTAL_PAGE: Int? = null
        var entrust: List<TradeMatchingTransactionEntrust>? = null
    }

    class TradeMatchingTransactionEntrust{
        var addtime: String? =null
        var type: String? = null
        var price: String? = null
        var num: Int? = null
        var id: String? = null
        var gross: String? = null
        var value: String? = null
        var fee_buy: String? = null
    }

    /** Get Available Balance*/
    class AvailableBalanceObj(
        var market: String
    )

    class AvailableBalanceRes{
        var status: Int? = null
        var message: String? = null
        var data: AvailableBalanceData? = null
    }

    class AvailableBalanceData{
        var xnb: Double? = null
        var xnbd: Double? = null
        var usd: Double? = null
        var usdd: Double? = null
        var lock_ut: Int? = null
        var sum_usd: Double? = null
        var sum_ut: Int? = null
        var lock_usd: Double? = null
    }

    /** Trade Cancel Order*/
    class TradeCancelOrderRes{
        var status: Int? = null
        var message: String? = null
        var data: TradeCancelOrderData? = null
    }

    class TradeCancelOrderData{

    }

    class TradeCancelOrderObj(
        var trade_id: Int
    )

    /** Trade Chart*/
    class TradeChartRes{
        var status: Int? = null
        var message: String? = null
        var data: TradeChartData? = null
    }

    class TradeChartData{
        var trading_view: String? = null
    }

    /** Trade Transaction Detail*/
    class TradeTransactionDetailObj(
        var trade_log_id: Int?
    )

    class TradeTransactionDetailRes{
        var status: Int? = null
        var message: String? = null
        var data: TradeTransactionDetailData? = null
    }

    class TradeTransactionDetailData{
        var name: String? = null
        var addtime: String? = null
        var transaction_id: String? = null
        var type: String? = null
        var price: String? = null
        var volume: String? = null
        var gross: String? = null
        var fee: String? = null
        var net: String? = null
    }

}