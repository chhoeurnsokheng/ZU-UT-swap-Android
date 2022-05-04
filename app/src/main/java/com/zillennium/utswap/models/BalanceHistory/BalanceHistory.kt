package com.zillennium.utswap.models.BalanceHistory

import java.io.Serializable


class BalanceHistory(
    var imagePath: Int,
    var date: String,
    var transaction: String,
    var transactionDetail: String,
    var moneyIn: String,
    var moneyOut: String,
    var balance: String
) :
    Serializable