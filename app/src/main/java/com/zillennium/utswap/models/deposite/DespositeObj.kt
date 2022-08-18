package com.zillennium.utswap.models.deposite

/**
 * Created by Sokheng Chhoeurn on 16/8/22.
 * Build in Mac
 */
object DepositObj {
    class DepositRes{
        var status:Int? =null
        var message:String ? = null
        var data:List<DataListRes> ? =null
    }
    class DataListRes{
        var id:String? = null
        var title:String? = null
        var img_url:String? = null
        var bic:String? =null
        var storelink:StoreLinkObj? = null
       /* PATH_ONLINE_DEPOSIT*/
        var payment_link:String? = null

        /* PATH_FINANCE_TRANSFER_LOGS*/
        var userid:String? = null
        var balance:String? = null
        var memo:String? = null
        var coin:String? = null
        var amount:String? = null
        var fee:String? = null
        var total:String? = null
        var tid:String? = null
        var sender_account:String? = null
        var receiver_account:String? = null
        var sender_name:String? = null
        var receiver_name:String? = null
        var addtime:String? = null
        var status:String? = null
        var type:String? = null
        var created_by:String? = null
        var addtimeReadable:String? = null


    }
    class DepositReturn{
        var status:Int? =null
        var message:String ? = null
        var data:DataListRes ? =null
    }
    class StoreLinkObj{
        var ios:String? = null
        var android:String? = null
    }
    class DepositRequestBody{
        var type:String ? =null
        var num:String ? =null
        var coinname:String? =null
        var payment_method:String? = null
        var deep_link:String? = null
    }
}