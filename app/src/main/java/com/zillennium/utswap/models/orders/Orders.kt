package com.zillennium.utswap.models.orders

import java.io.Serializable

class Orders (var txtStatus: String,
              var txtUT: String,
              var txtDate: String,
              var txtPrice: Double
) :
    Serializable
{

}