package com.zillennium.utswap.Datas.StoredPreferences

import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.BaseStoredPreferences

class SessionPreferences: BaseStoredPreferences(UTSwapApp.instance, "SessionPreferences") {

    var SESSION_ID by stringPref()
    var SESSION_USERNAME by stringPref()
    var SESSION_PASSWORD by stringPref()
    var SESSION_STATUS by booleanPref()
    var SESSION_KYC by booleanPref()
    var SESSION_KYC_STATUS by intPref() // 0 = hide, 1 = Invalid, 2 = Pending
    var SESSION_KYC_SUBMIT_STATUS by booleanPref()
    var SESSION_TOKEN by stringPref()
    var SESSION_EXPIRED by stringPref()
    var SESSION_X_TOKEN_API by stringPref(defaultValue = "")
    var SESSION_SECURE_KEY by stringPref()
    var SESSION_SECURE_KEY_FORGOT_PASSWORD by stringPref()
    var SESSION_SECURE_KEY_ADD_PHONE by stringPref()

    //store phone number that user add, and image profile user set
    var SESSION_PHONE_NUMBER by stringPref(defaultValue = "")
    var SESSION_USER_PROFILE by stringPref(defaultValue = "")

}