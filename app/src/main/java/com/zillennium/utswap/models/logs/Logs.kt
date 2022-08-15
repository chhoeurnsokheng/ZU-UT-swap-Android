package com.zillennium.utswap.models.logs

object Logs{
    class AccountLogsRes{
        var status : Int? =null
        var message : String? = null
        var data : List<AccountLogsData>? = emptyList()
    }
    class AccountLogsData{
        var id : String? = null
        var userid : String? = null
        var type : String? = null
        var remark : String? = null
        var addip : String? = null
        var addr : String? = null
        var sort : String? = null
        var addtime : String? = null
        var endtime : String? = null
        var status : String? = null
    }

    class AccountLogsObject(
        var page : Int? = 1
    )
}