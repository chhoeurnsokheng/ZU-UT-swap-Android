package com.zillennium.utswap.Datas.StoredPreferences

import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.BaseStoredPreferences

class SessionPreferences: BaseStoredPreferences(UTSwapApp.instance, "SessionPreferences") {

    var SESSION_ID by intPref()
    var SESSION_USERNAME by stringPref()
    var SESSION_PASSWORD by stringPref()
    var SESSION_STATUS by booleanPref(defaultValue = true)
    var SESSION_KYC by booleanPref(defaultValue = true)
    var SESSION_TOKEN by stringPref()
    var SESSION_EXPIRED by stringPref()

}