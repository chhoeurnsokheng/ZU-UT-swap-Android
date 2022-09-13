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
        var IOS:IOSData?  =null
        var ANDROID:ANDROIDData? = null

    }
    class IOSData{
        var version:String? = null
        var message:String? = null
        var app_url:String? = null
        var version_type:String? = null
    }
    class ANDROIDData{
        var version:String? = null
        var message:String? = null
        var app_url:String? = null
        var version_type:String? = null
    }
    class AppUrl{
        var ios:String? = null
        var android:String? = null
    }
}