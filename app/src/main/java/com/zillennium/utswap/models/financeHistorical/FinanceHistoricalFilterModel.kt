package com.zillennium.utswap.models.financeHistorical

import java.io.Serializable

class FinanceHistoricalFilterModel(
    var titleHistorical: String,
    var statusHistorical: Int,
    var imageHistorical: Int,
    var isCheck: Boolean = false,
): Serializable