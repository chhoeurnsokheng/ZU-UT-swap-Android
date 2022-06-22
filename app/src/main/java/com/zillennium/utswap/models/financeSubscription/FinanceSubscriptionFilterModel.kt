package com.zillennium.utswap.models.financeSubscription

import java.io.Serializable

class FinanceSubscriptionFilterModel(
    var titleFilter: String,
    var statusFilter: Int,
    var imageFilter: Int,
) : Serializable