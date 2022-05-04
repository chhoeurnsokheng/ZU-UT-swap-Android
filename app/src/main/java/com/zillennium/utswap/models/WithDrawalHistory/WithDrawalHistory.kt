package com.zillennium.utswap.models.WithDrawalHistory

import java.io.Serializable


class WithDrawalHistory(
    var transactionId: String,
    var time: String,
    var amount: String,
    var amountArrival: String,
    var receivingAddress: String
) :
    Serializable