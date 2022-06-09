package com.zillennium.utswap.models

import java.io.Serializable

class FinanceBalanceModel(
//    var iconBalance: String,
    var imageBalance: Int,
    var titleTransaction: String,
    var dateTransaction: String,
    var amountBalance: Double,
    var status: Int,
):
    Serializable