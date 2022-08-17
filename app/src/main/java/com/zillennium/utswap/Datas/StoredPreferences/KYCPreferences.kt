package com.zillennium.utswap.Datas.StoredPreferences

import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.BaseStoredPreferences

class KYCPreferences : BaseStoredPreferences(UTSwapApp.instance, "KYCPreferences") {

    // list of app specific preferences
    var FIRST_NAME by stringPref()
    var LAST_NAME by stringPref()
    var BIRTHDAY by stringPref()
    var GENDER by stringPref()
    var GENDER_AS_SHORT_LETTER by stringPref()

    var CITY_PROVINCE by stringPref()
    var DISTRICT_KHAN by stringPref()
    var COMMUNE_SANGKAT by stringPref()
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

    var TERNCONDITION by stringPref()
    var  status_kyc_submit     by booleanPref()
    var   status_kyc_approved     by booleanPref()
    var DO_KYC_STATUS by stringPref()
    var ID_CARD_INFOR by stringPref()


}
