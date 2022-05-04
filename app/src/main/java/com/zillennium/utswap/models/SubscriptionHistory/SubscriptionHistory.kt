package com.zillennium.utswap.models.SubscriptionHistory

import java.io.Serializable


class SubscriptionHistory(
    var nameSub: String,
    var value: String,
    var date: String,
    var price: String,
    var volume: String,
    var transactionId: String
) :
    Serializable