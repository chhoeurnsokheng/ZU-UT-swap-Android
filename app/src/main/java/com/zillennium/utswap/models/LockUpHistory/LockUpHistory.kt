package com.zillennium.utswap.models.LockUpHistory

import java.io.Serializable


class LockUpHistory(
    var amount: String,
    var date: String,
    var project: String,
    var period: String,
    var maturity: String
) :
    Serializable