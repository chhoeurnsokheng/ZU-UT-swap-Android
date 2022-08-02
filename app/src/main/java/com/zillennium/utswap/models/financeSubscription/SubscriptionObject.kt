package com.zillennium.utswap.models.financeSubscription

import com.zillennium.utswap.models.BaseResponse.BaseResponse

object SubscriptionObject {
    class SubscriptionRes {
        var data: ArrayList<SubscriptionList>? = arrayListOf()
    }
    class SubscriptionList {

    }
    class SubscriptionBody {
        var id: Int? = 0
        var start: String? = ""
        var end: String? = ""

    }
}