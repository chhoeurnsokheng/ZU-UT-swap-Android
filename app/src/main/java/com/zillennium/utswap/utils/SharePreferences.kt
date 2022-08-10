package com.zillennium.utswap.utils

import android.content.Context
import android.content.SharedPreferences
import com.zillennium.utswap.UTSwapApp

object SharePreferences {
    var mContext = UTSwapApp.instance
    private val preferences: SharedPreferences
        get() = mContext.getSharedPreferences(
            Constants.PREFERENCE_NAME,
            Context.MODE_PRIVATE
        )

    fun setDeviceToken(deviceToken: String, context: Context) {
        preferences.apply {
            edit().putString(Constants.DEVICE_TOKEN, deviceToken).apply()
        }
    }

    fun getDeviceToken(): String {
        if (preferences.getString(Constants.DEVICE_TOKEN, "") == null) return ""
        return preferences.getString(Constants.DEVICE_TOKEN, "").toString()
    }
}