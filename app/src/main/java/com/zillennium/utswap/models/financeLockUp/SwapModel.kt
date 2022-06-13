package com.zillennium.utswap.models.financeLockUp

import java.io.Serializable

class SwapModel(
    var titleLockUp: String,
    var dateStartLockUp: String,
    var dateEndLockUp: String,
    var amountLockUp: Double,
    var durationLockUp: String,
    var status: Int,
) : Serializable