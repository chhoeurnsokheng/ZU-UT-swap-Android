package com.zillennium.utswap.models.financeLockUp

import java.io.Serializable

class BuyBackModel(
    var titleLockUp: String,
    var dateStartLockUp: String,
    var dateEndLockUp: String,
    var amountLockUp: Double,
    var durationLockUp: String,
    var status: Int,

) : Serializable