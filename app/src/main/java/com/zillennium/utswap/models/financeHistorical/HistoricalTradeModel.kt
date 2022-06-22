package com.zillennium.utswap.models.financeHistorical

import java.io.Serializable

class HistoricalTradeModel(
    var titleTrade: String,
    var utAmount: Int,
    var openAmountTrade: Double,
    var closeAmountTrade: Double,
    var highAmountTrade: Double,
    var lowAmountTrade: Double,
    var dateTrade: String,
) : Serializable
