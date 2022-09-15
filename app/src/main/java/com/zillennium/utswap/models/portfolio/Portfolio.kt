package com.zillennium.utswap.models.portfolio

object Portfolio {

    /** Portfolio */
    class GetPortfolio{
        var status: Int? = null
        var message: String? = null
        var data: GetPortfolioData? = null
    }
    class GetPortfolioDashboardChartRes{
        var status: Int? = null
        var message: String? = null
        var data:ArrayList<DataGetPortfolioDashboardChartRes> = arrayListOf()
    }
    class DataGetPortfolioDashboardChartRes{
        var x:Float  = 0f
        var y:Float  = 0f
        var date:String   = ""
    }
    class GetPortfolioData{
        var id: String? = null
        var coinname: String? = null
        var name: String? = null
        var watch_lists: List<GetPortfolioWatchList> = arrayListOf()
        var user_balance:PortfolioUserBalance? = null
        var profolio_dashboard: List<GetPortfolioDashBoard> = arrayListOf()
        var total_user_balance: Double? = null
        var total_market_value: Double? = null
        var buy_price_type: Int? = null
        var balance_weight:String? = null
        var ut_projects:String? = null

    }
    class PortfolioUserBalance{
        var usd:Double?  =null
        var usdd:Double? = null
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
        var project_id:String? = null
        var market_name:String? =null
        var market_id:Int?  =null
    }

    class GetPortfolioObject(
        var type: Int? = null
    )
}