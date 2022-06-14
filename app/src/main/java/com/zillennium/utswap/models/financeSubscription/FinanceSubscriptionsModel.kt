package com.zillennium.utswap.models.financeSubscription

import java.io.Serializable

class FinanceSubscriptionsModel(
    var imageSubscription: Int,
    var titleSubscription: String,
    var dateSubscription: String,
    var amount: Double,
    var durationDay: String,
    var status: Int,
) : Serializable