package com.zillennium.utswap.utils

import android.util.Log

object LoggerUtil {
    private val isLog = true
    fun debug(tag: String, message: String){
        if(isLog){
            Log.d(tag, message)
        }
    }
}