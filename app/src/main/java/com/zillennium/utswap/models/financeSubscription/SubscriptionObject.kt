package com.zillennium.utswap.models.financeSubscription

import com.zillennium.utswap.models.BaseResponse.BaseResponse

object SubscriptionObject {

    class Subscription: BaseResponse() {
        var data: SubscriptionRes? = null
    }

    class SubscriptionRes {
    }
    class SubscriptionList {

    }


    class SubscriptionBody {
        var ls: Int = 0
        var project: String = ""
        var start: String = ""
        var end: String = ""

    }
}