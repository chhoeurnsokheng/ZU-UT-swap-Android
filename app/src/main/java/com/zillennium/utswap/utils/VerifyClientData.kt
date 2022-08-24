package com.zillennium.utswap.utils

import java.math.BigInteger
import java.security.MessageDigest

object VerifyClientData {
    public fun makeSign(params: Map<String, String>, key: String): String{

        val signType = params.getValue("sign_type")
        var string = toUrlParams(params.toSortedMap())
        string = "$string"+"key=$key"
        // Hash string
        when(signType){
            "MD5" -> string = md5(string)
            "HMAC-SHA256" -> {}
            else -> string = md5(string)
        }
        return string
    }

    private fun md5(text: String): String {
        val md = MessageDigest.getInstance("MD5")
        val bigInt = BigInteger(1, md.digest(text.toByteArray(Charsets.UTF_8)))
        return String.format("%032x", bigInt)
    }

    private fun toUrlParams(params: Map<String, String>): String? {
        var buff : String? = ""
        for (param in params){
            if(param.key != "sign" && param.value != ""){
                buff = buff + param.key + "=" + param.value + "&"
            }
        }
        return buff
    }

}