package com.zillennium.utswap.models.user

import java.io.Serializable

/**
 * Created by Sokheng Chhoeurn on 19/7/22.
 * Build in Mac
 */
class VerifiyCode(
    var status: Int? = 0,
    var message: String? = "",
    var data: DataOfSecureKey?
) : Serializable

data class DataOfSecureKey(
    var secure_key: String
) : Serializable
