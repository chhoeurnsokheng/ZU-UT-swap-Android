package com.zillennium.utswap.models.portfolio

object Portfolio {

    /** Portfolio */
    class GetPortfolio{
        var status: Int? = null
        var message: String? = null
        var data: GetPortfolioData? = null
    }

    class GetPortfolioData{
        var id: String? = null
        var coinname: String? = null
        var name: String? = null
        var watch_lists: List<GetPortfolioWatchList> = arrayListOf()
        var profolio_dashboard: List<GetPortfolioDashBoard> = arrayListOf()
        var total_user_balance: Double? = null
        var total_market_value: Double? = null
        var buy_price_type: Int? = null
    }

    class GetPortfolioWatchList{
        var id: String? = null
        var coinname: String? = null
        var buycoin: String? = null
        var name: String? = null
        var change: Double? = null
        var Last: Double? = null
        var market_id: String? = null
        var market_name: String? = null
    }

    class GetPortfolioDashBoard{
        var mkt_project_name: String? = null
        var mkt_project_change: String? = null
        var mkt_project_mkt_price: String? = null
        var mkt_project_buy_price: String? = null
        var mkt_project_perf: String? = null
        var weight: String? = null
        var vol: String? = null
        var value: Double? = null
    }

    class GetPortfolioObject(
        var type: Int? = null
    )
}