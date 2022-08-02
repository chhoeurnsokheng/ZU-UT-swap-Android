package com.zillennium.utswap.models.province

import android.view.textclassifier.ConversationActions
import java.io.Serializable

/**
 * Created by Sokheng Chhoeurn on 2/8/22.
 * Build in Mac
 */


data class Province(
    var status: Int? = null,
    var message: String? = null,
    var data: Items? = null
) : Serializable

data class Items(
    var code: String? = null,
  //  var english: String? = null
) : Serializable
