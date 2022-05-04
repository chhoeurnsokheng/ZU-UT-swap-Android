package com.zillennium.utswap.models.TransferHistory

import java.io.Serializable


class TransferHistory(
    var transactionId: String,
    var time: String,
    var sender: String,
    var receiver: String,
    var amount: String,
    var total: String
) :
    Serializable