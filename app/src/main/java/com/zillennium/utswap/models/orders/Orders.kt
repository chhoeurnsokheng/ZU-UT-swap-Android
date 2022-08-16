package com.zillennium.utswap.models.orders

import java.io.Serializable

class Orders (var txtStatus: String,
              var txtUT: Int,
              var txtDate: String,
              var txtPrice: Double
) :
    Serializable
