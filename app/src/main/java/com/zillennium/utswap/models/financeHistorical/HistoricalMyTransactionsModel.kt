package com.zillennium.utswap.models.financeHistorical

import java.io.Serializable

class HistoricalMyTransactionsModel(
    var imageMyTrans: Int,
    var titleMyTrans: String,
    var dateMyTrans: String,
    var amountMyTrans: Double,
    var statusMyTrans: String,
    var titleCheckMyTrans: String,

    ) : Serializable