package com.zillennium.utswap.Datas.StoredPreferences

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.BaseStoredPreferences

class SystemPreferences: BaseStoredPreferences(UTSwapApp.instance, "SystemPreferences") {

    // Information App
    var APP_NAME by stringPref("APP_NAME", "UT Swap")
    var APP_CURRENT_VERSION by stringPref()
    var APP_LAST_VERSION by stringPref()
    var APP_WEBSITE by stringPref("APP_WEBSITE", "https://utswap.io/")
    var APP_WELCOME by stringPref()
    var APP_STORE by stringPref()
    var APP_PLAY_STORE by stringPref()
    var APP_WINDOWS by stringPref()
    var APP_GALLERY by stringPref()
    var APP_FACEBOOK by stringPref("APP_FACEBOOK", "101543225693802")
    var APP_INSTAGRAM by stringPref("APP_INSTAGRAM", "zillion_united")
    var APP_TELEGRAM by stringPref("APP_TELEGRAM", "https://t.me/+VfhwdqEfvU8006HH")
    var APP_PHONE by stringPref(defaultValue = "0239999999")
    var APP_TWITTER by stringPref(defaultValue = "zillionunited")

    // Device and Permission Phone
    var DEVICE_NAME by stringPref()
    var DEVICE_VERSION by stringPref()
    var DEVICE_MODEL by stringPref()
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
    var API_DEBUG by stringPref("https://sandbox.utswap.io/")
    var API_HOSTING by stringPref("https://www.utswap.io/")
    var API_WORDPRESS by stringPref()
    var API_FIREBASE by stringPref()
    var API_AWS by stringPref()
    var API_TIMEOUT by intPref(defaultValue = 30)

}