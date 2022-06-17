package com.zillennium.utswap.models.financeHistorical

import java.io.Serializable

class HistoricalAllTransactionsModel(
    var dateAllTrans: String,
    var volumeAllTrans: Int,
    var priceAllTrans: Double,
    var grossAllTrans: Double,
    var titleAllTrans: String,
) : Serializable