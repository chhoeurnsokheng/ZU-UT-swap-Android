package com.zillennium.utswap.models.deposite

/**
 * Created by Sokheng Chhoeurn on 23/8/22.
 * Build in Mac
 */
class DataQueryOrderObj {
    class DataQueryOrderRes{
        var status:Int?  =null
        var message:String? = null
        var data:DataRese?  =null
    }
    class DataRese{
        var dataQueryOrder:DataQueryOrder ? = null
    }
    class DataQueryOrder{
        var success:Boolean = false
        var message:String ? =null
        var code:Double ? = null
        var data:Data? = null
    }
    class Data {
        var total_amount:String ? =null
        var currency:String ? = null
        var status:String? = null
        var meta:Meta? = null
    }
    class Meta{

    }

}