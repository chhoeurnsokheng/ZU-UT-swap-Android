package com.zillennium.utswap.models.home

/**
 * Created by Sokheng Chhoeurn on 4/8/22.
 * Build in Mac
 */
object BannerObj {
    class Banner{
        var status:Int? =null
        var message:String? = null
        var data:ArrayList<ItemsBanner> = arrayListOf()
    }
    class ItemsBanner{
        var id:Int ? = null
        var banner:String ? = null
        var linkable_type :String? = null
        var linkable_id:String? = null
    }
    class whistListRes{
        var status:Int? = null
        var message:String? = null
        var data:DataWishList? = null

    }
    class DataWishList{
        var  watch_lists :List<ItemWishList>? = null
        var total_user_balance:Double? = 0.00
        var total_market_value:Double ? =null
        var buy_price_type:Double? =null
        var profolio_dashboard:List<Listprofolio_dashboard> ? =null
    }
    class ItemWishList{
        var id:String? = null
        var name:String? =null
        var change:String? = ""
        var Last:String? = ""
        var status:String ? =null
        var market_id:String?  = null
    }
    class Listprofolio_dashboard{
        var mkt_project_name:String? =null
        var mkt_project_mkt_price:String? =null
        var mkt_project_buy_price:String? =null
    }
}