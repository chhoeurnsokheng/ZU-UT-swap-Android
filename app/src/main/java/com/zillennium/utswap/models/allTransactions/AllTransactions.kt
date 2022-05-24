package com.zillennium.utswap.models.allTransactions

import java.io.Serializable

class AllTransactions (var txtDate: String,
                       var txtTime: String,
                       var txtVolume: String,
                       var txtPrice: String,
                       var txtGross: String
) :
    Serializable