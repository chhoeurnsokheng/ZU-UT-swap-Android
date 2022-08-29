@file:Suppress("DEPRECATION")

package com.zillennium.utswap.utils

import android.annotation.SuppressLint
import android.content.Context
import android.net.wifi.WifiManager
import android.provider.Settings
import android.text.format.Formatter.formatIpAddress


object MobileSetting {
    @SuppressLint("HardwareIds")
    fun getDeviceID(context: Context) : String? {
        return Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
    }

    fun getIpDevice(context: Context): String? {
        val wm = context.getSystemService(Context.WIFI_SERVICE) as WifiManager
        return formatIpAddress(wm.connectionInfo.ipAddress)
    }
}