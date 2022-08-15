package com.zillennium.utswap.models.financeSubscription

import com.zillennium.utswap.models.BaseResponse.BaseResponse

object SubscriptionObject {

    class Subscription: BaseResponse() {
        var data: SubscriptionRes? = null

    }

    class SubscriptionRes {
        var market_list: ArrayList<ProjectList> = arrayListOf()
        var date: Date? = null
        var market: Any? = null
        var transaction: ArrayList<SubscriptionList> = arrayListOf()
        var total_transactions: String = ""
    }

    class ProjectList {
        var project: String = ""
        var id: String = ""
        var name: String = ""
        var project_name = ""
        var coniname: String = ""
    }
    class SubscriptionList {
        var addtimeReadble : String = ""
        var mum : String = ""
        var num : String = ""
        var coinname : String = ""
        var remark : String = ""
        var shen : Int = 0
        var step_id : Int = 0
        var type : String = ""
        var userid : Int = 0
        var lock_period_left : Int = 0
        var price : Double = 0.0
        var convert_price : Double = 0.0
        var id : Int = 0
        var lock_type : Int = 0
        var jian : Int = 0
        var transaction_id : String = ""
        var peerid : Int = 0
        var unlock : String = ""
        var ci : Int = 0
        var endtime : String = ""
        var sort : String = ""
        var convertcurrency : String = ""
        var buycoin : String = ""
        var addtime : String = ""
        var name : String = ""
        var status : String = ""
    }

    class Date {
        var start: String = ""
        var end: String = ""

    }


    class SubscriptionBody {
        var ls: Int = 10
        var project: String = ""
        var start: String = ""
        var end: String = ""
        var order_page: Int = 1

    }
}