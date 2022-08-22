package com.zillennium.utswap.models.home

/**
 * Created by Sokheng Chhoeurn on 17/8/22.
 * Build in Mac
 */
object ForceUpdate {
    class ForceUpdateRes{
        var status:Int? =null
        var message:String? = null
        var data:DataRes? = null
    }
    class DataRes{
        var version:String? = null
        var message:String? = null
         var app_url:AppUrl? = null
    }
    class AppUrl{
        var ios:String? = null
        var android:String? = null
    }
}