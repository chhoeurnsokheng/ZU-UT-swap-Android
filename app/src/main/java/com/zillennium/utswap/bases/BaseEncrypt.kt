package com.zillennium.utswap.bases

import okio.HashingSource.md5
import java.util.*


//class BaseEncrypt {
//
//    public fun makeSign(params: Array<> , key: String): String {
//
//        val ksort = params.sort(0, 2)
//        val signType = params['sign_type']
//        var string = toUrlParams(params).toString() + "&key=$key"
//
//        // Hash string
//        if (signType == "MD5"){
//            string = md5(string);
//        }else if (signType == "HMAC-SHA256"){
//
//        }
//        return string
//    }
//
//
//    private fun toUrlParams(params: Arrays)
//    {
//        var buff = "";
//        foreach ($params as $k => $v){
//        if($k != "sign" && $v != "" && !is_array($v)){
//        $buff .= $k . "=" . $v . "&";
//    }
//    }
//        $buff = trim($buff, "&");
//        return $buff;
//    }
//
//}