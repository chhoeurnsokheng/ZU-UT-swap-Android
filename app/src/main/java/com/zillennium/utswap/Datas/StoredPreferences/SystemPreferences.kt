package com.zillennium.utswap.Datas.StoredPreferences

import android.os.Build
import com.zillennium.utswap.BuildConfig
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.BaseStoredPreferences

class SystemPreferences: BaseStoredPreferences(UTSwapApp.instance, "SystemPreferences") {

    // Information App
    var APP_NAME by stringPref(defaultValue = "UT Swap")
    var APP_CURRENT_VERSION by stringPref()
    var APP_LAST_VERSION by stringPref()
    var APP_WEBSITE by stringPref(defaultValue =  "https://utswap.io/")
    var APP_WELCOME by stringPref()
    var APP_STORE by stringPref()
    var APP_PLAY_STORE by stringPref()
    var APP_WINDOWS by stringPref()
    var APP_GALLERY by stringPref()
    var APP_FACEBOOK by stringPref(defaultValue =  "101543225693802")
    var APP_INSTAGRAM by stringPref(defaultValue =  "zillion_united")
    var APP_TELEGRAM by stringPref(defaultValue = "https://t.me/codono")
    var APP_PHONE by stringPref(defaultValue = "+85512678987")
    var APP_TWITTER by stringPref(defaultValue = "https://twitter.com/codono")

    // Device and Permission Phone
    var DEVICE_ID by stringPref(defaultValue = Build.ID)
    var DEVICE_NAME by stringPref(defaultValue = Build.DEVICE)
    var DEVICE_BRAND by stringPref(defaultValue = Build.BRAND)
    var DEVICE_MODEL by stringPref(defaultValue = Build.MODEL)
    var DEVICE_VERSION by stringPref(defaultValue = Build.VERSION.RELEASE)
//    var DEVICE_SDK by stringPref(defaultValue = Build.VERSION.SDK)
    var DEVICE_OS by stringPref(defaultValue = System.getProperty("os.version"))
    var APP_VERSION by stringPref(defaultValue = BuildConfig.VERSION_NAME)
    var APP_VERSION_CODE by intPref(defaultValue = BuildConfig.VERSION_CODE)
    var PERMISSION_NOTIFICATION by stringPref()
    var PERMISSION_CAMERA by stringPref()
    var PERMISSION_FILE_AND_MEDIA by stringPref()

    // Authentication
    var AUTH_ID by stringPref()
    var AUTH_TOKEN by stringPref()
    var AUTH_NAME by stringPref()
    var AUTH_EMAIL by stringPref()
    var AUTH_PHONE by stringPref()
    var AUTH_COMPANY by stringPref()
    var AUTH_OCCUPATION by stringPref()
    var AUTH_BANK_NAME by stringPref()
    var AUTH_BANK_NUMBER by stringPref()
    var AUTH_BANK_BRAND by stringPref()
    var AUTH_BANK_CITY by stringPref()
    var AUTH_ADDRESS by stringPref()
    var AUTH_TYPE by stringPref()
    var AUTH_VERIFY by booleanPref(defaultValue = false)
    var AUTH_BALANCE_TOTAL by longPref()
    var AUTH_BALANCE_AVAILABLE by longPref()
    var AUTH_BALANCE_LOCK_UP by longPref()
    var AUTH_BALANCE_PENDING by longPref()
    var AUTH_DATE_LOGIN by longPref()
    var AUTH_DATE_LOCK_SCREEN by longPref()
    var AUTH_DATE_FUND by longPref()
    var AUTH_LANGUAGE by longPref()
    var AUTH_EXPIRE by longPref()

    // Base Url of APIs
    var API_DEBUG by stringPref(defaultValue = "https://sandbox.utswap.io/")
    var API_HOSTING by stringPref(defaultValue = "https://www.utswap.io/")
    var API_WORDPRESS by stringPref()
    var API_FIREBASE by stringPref()
    var API_AWS by stringPref()
    var API_TIMEOUT by intPref(defaultValue = 30)

}