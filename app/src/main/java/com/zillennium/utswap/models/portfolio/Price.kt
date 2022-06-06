package com.zillennium.utswap.models.portfolio

import java.io.Serializable

class Price (var projectName: String,
             var txtBuy: Double,
             var txtMkt: Double
) :
    Serializable