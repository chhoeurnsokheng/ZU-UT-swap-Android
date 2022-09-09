package com.zillennium.utswap.models.withdraw

/**
 * Created by Sokheng Chhoeurn on 31/8/22.
 * Build in Mac
 */
class WithdrawObj {
    class WithdrawRes{
        var status:Int? = null
        var message:String?  =null
        var data:List<DataWithdraw> ? = null
    }
    class DataWithdraw{
        var id:Int?  =null
        var name:String? = null
        var bic:String?  =null
        var url:String?  =null
    }
}
