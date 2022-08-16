package com.zillennium.utswap.models.portfolio

/**
 * Created by Sokheng Chhoeurn on 15/8/22.
 * Build in Mac
 */
interface PortfolioFilterObj {
    class portfolioFilterRes{
        var status:Int? = null
        var message:String? = null
        var data:PortfolioFilterData? = null
    }
    class PortfolioFilterData{
        var id:String? = null
        var name:String ? =null
        var change:String? = null
        var Last:String? = null
        var watch_lists:List<ItemWishListPortfolio> ? = null
        var total_user_balance:Double? = null
        var total_market_value:Double? = null
        var buy_price_type:Int? =null
    }
    class ItemWishListPortfolio{
        var id:String? = null
        var name:String? = null
        var change:String? = null
        var Last:String? = null
    }
}