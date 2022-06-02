package com.zillennium.utswap.Datas.GlobalVariable

import android.os.Build
import androidx.annotation.RequiresApi
import com.zillennium.utswap.Datas.StoredPreferences.SystemPreferences

class SystemVariable {
    companion object{
        @RequiresApi(Build.VERSION_CODES.R)
        val device = arrayListOf<String>(
            "DEVICE_ID : ${SystemPreferences().DEVICE_ID}",
            "DEVICE_NAME : ${SystemPreferences().DEVICE_NAME}",
            "DEVICE_BRAND : ${SystemPreferences().DEVICE_BRAND}",
            "DEVICE_MODEL : ${SystemPreferences().DEVICE_MODEL}",
            "DEVICE_VERSION : ${SystemPreferences().DEVICE_VERSION}",
            "DEVICE_SDK : ${SystemPreferences().DEVICE_SDK}",
            "DEVICE_OS : ${SystemPreferences().DEVICE_OS}",
            "APP_VERSION : ${SystemPreferences().APP_VERSION}",
            "APP_VERSION_CODE : ${SystemPreferences().APP_VERSION_CODE}"
        )
    }
}