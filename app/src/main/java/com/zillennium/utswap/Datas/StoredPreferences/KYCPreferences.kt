package com.zillennium.utswap.Datas.StoredPreferences

import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.BaseStoredPreferences

class KYCPreferences : BaseStoredPreferences(UTSwapApp.instance, "KYCPreferences") {

    // list of app specific preferences
    var FIRST_NAME by stringPref()
    var LAST_NAME by stringPref()
    var BIRTHDAY by stringPref()
    var GENDER by intPref()

    var CITY_PROVINCE by intPref()
    var DISTRICT_KHAN by intPref()
    var COMMUNE_SANGKAT by intPref()
    var ADDRESS by stringPref()

    var OCCUPATION by stringPref()
    var COMPANY by stringPref()
    var PHONE_NUMBER by stringPref()
    var EMAIL by stringPref()

    var FUND_PASSWORD by stringPref()

    var SELFIE_HOLDING by stringPref()
    var NATIONAL_ID_FRONT by stringPref()
    var NATIONAL_ID_BACK by stringPref()
    var PASSPORT_FRONT by stringPref()
}
