package com.zillennium.utswap.models.project

object SubscriptionProject {
    class SubscriptionProjectRes {
        var status: Int? = null
        var message: String? = null
        var data: ArrayList<SubscriptionProjectData>? = null
    }

    class SubscriptionProjectData {
        var num: Int? = null
        var deal: Int? = null
        var price: Int? = null
        var jian: Int? = null
        var addtime: String? = null
        var endtime: String? = null
        var user_account_type: String? = null
    }
}