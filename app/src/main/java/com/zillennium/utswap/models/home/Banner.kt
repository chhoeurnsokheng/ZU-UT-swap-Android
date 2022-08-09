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
}