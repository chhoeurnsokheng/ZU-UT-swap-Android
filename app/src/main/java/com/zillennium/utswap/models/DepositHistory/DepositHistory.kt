package com.zillennium.utswap.models.DepositHistory

import java.io.Serializable


class DepositHistory(
    var imagePath: Int,
    var transactionId: String,
    var depositMethod: String,
    var amount: String,
    var fee: String,
    var netValue: String,
    var rechargeTime: String,
    var status: String
) :
    Serializable