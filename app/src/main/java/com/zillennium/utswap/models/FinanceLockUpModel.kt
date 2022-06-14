package com.zillennium.utswap.models

import java.io.Serializable

class FinanceLockUpModel(
    var titleLockUp: String,
    var dateStartLockUp: String,
    var dateEndLockUp: String,
    var amountLockUp: Double,
    var durationLockUp: String,
    var status: Int,
    var category: String

) : Serializable