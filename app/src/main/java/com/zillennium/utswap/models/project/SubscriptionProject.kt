package com.zillennium.utswap.models.project

object SubscriptionProject {
    class SubscriptionProjectRes {
        var status: Int? = null
        var message: String? = null
        var data: ArrayList<SubscriptionProjectData>? = arrayListOf()
    }

    class SubscriptionProjectData {
        var id: Int = 0
        var num: Int? = null
        var deal: Int? = null
        var price: Double? = null
        var jian: Int? = null
        var addtime: String? = null
        var endtime: String? = null
        var user_account_type: String? = null
        var min: Int? = null
        var max: Int? = null
        var launch: Int? = null
        var type: Int? = null
        var name: String? = null
    }

    class SubscriptionProjectBody(
        var id: Int? = null,
        var date_range: String? = null
    )

    /** Project Subscription Order*/
    class SubscriptionConfirmRes {
        var status: Int? = null
        var message: String? = null
        var data: SubscribeConfirmBody? = null
    }

    class SubscribeConfirmBody(
        var id: Int? = null,
        var ut_number: Int? = null,
        var fund_password: String = ""
    )

    /**Subscription Order**/
    class SubscriptionOrderRes {
        var status: Int? = null
        var message: String? = null
        var data: SubscriptionOrderResData? = null
    }

    class SubscriptionOrderResData{}

    class SubscribeOrderBody(
        var sign_type: String? = null,
        var sign: String? = null,
        var id: Int? = null,
        var ut_number: Int? = null,
        var fund_password: String = ""
    )
}