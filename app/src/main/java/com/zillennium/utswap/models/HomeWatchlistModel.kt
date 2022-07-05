package com.zillennium.utswap.models

import java.io.Serializable

class HomeWatchlistModel(
    var locationProject: String,
    var lastValue: Double,
    var changeValue: Double
) : Serializable