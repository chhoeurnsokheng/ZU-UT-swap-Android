package com.zillennium.utswap.models.user

import java.io.Serializable

/**
 * Created by Sokheng Chhoeurn on 19/7/22.
 * Build in Mac
 */
data class LoginVerifyLoginParam(
    var username: String? = "",
    var password: String? = ""
) : Serializable