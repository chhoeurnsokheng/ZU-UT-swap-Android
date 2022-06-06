package com.zillennium.utswap.models.portfolio

import java.io.Serializable

class Balance (var projectName: String,
               var ut: Int,
               var value: Double
) :
    Serializable