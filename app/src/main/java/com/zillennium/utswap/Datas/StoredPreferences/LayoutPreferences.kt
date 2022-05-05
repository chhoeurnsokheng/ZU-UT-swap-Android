package com.zillennium.utswap.Datas.StoredPreferences

import android.os.health.SystemHealthManager
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.BaseStoredPreferences

class LayoutPreferences: BaseStoredPreferences(UTSwapApp.instance, "LayoutPreferences") {

    var KYC_ADDRESS_INFO by booleanPref(defaultValue = true)
    var KYC_CONTRACT by booleanPref(defaultValue = true)
    var KYC_DECLARATION by booleanPref(defaultValue = true)
    var KYC_EMPLOYMENT_INFO by booleanPref(defaultValue = true)
    var KYC_FUND_PASSWORD by booleanPref(defaultValue = true)
    var KYC_ID_TYPE by booleanPref(defaultValue = true)
    var KYC_ID_VERIFICATION by booleanPref(defaultValue = true)
    var KYC_APPLICATION by booleanPref(defaultValue = true)
    var KYC_SELFIE_HOLDING by booleanPref(defaultValue = true)
    var KYC_TERM_CONDITION by booleanPref(defaultValue = true)
    var KYC_VERIFICATION by booleanPref(defaultValue = true)

    var NAVBAR_MAIN by booleanPref(defaultValue = true)
    var NAVBAR_NEWS by booleanPref(defaultValue = true)
    var NAVBAR_PROJECT by booleanPref(defaultValue = true)
    var NAVBAR_SWAP by booleanPref(defaultValue = true)
    var NAVBAR_TRADE by booleanPref(defaultValue = true)
    var NAVBAR_WALLET by booleanPref(defaultValue = true)

    var NEWS_ARTICLE by booleanPref(defaultValue = true)

    var PRIVACY_TERM by booleanPref(defaultValue = true)

    var PROJECT_DETAIL by booleanPref(defaultValue = true)
    var PROJECT_ICO by booleanPref(defaultValue = true)

    var SECURITY_NEW_PASSWORD by booleanPref(defaultValue = true)
    var SECURITY_RESET_PASSWORD by booleanPref(defaultValue = true)
    var SECURITY_SIGN_IN by booleanPref(defaultValue = true)
    var SECURITY_SIGN_UP by booleanPref(defaultValue = true)
    var SECURITY_VERIFICATION by booleanPref(defaultValue = true)

    var SETTING_ACCOUNT_TYPE by booleanPref(defaultValue = true)
    var SETTING_ADD_CARD by booleanPref(defaultValue = true)
    var SETTING_BALANCE by booleanPref(defaultValue = true)
    var SETTING_CREDIT_CARD by booleanPref(defaultValue = true)
    var SETTING_FUND_PASSWORD by booleanPref(defaultValue = true)
    var SETTING_KYC by booleanPref(defaultValue = true)
    var SETTING_LANGUAGE by booleanPref(defaultValue = true)
    var SETTING_LOCK by booleanPref(defaultValue = true)
    var SETTING_LOGIN_PASSWORD by booleanPref(defaultValue = true)
    var SETTING_LOG by booleanPref(defaultValue = true)
    var SETTING_PROFILE_QRCODE by booleanPref(defaultValue = true)
    var SETTING_PROFILE by booleanPref(defaultValue = true)
    var SETTING_MAIN by booleanPref(defaultValue = true)
    var SETTING_TWO_FA by booleanPref(defaultValue = true)

    var SYSTEM_NOTIFICATION by booleanPref(defaultValue = true)
    var SYSTEM_SCAN_QRCODE by booleanPref(defaultValue = true)
    var SYSTEM_SEARCH by booleanPref(defaultValue = true)
    var SYSTEM_SPLASH by booleanPref(defaultValue = true)
    var SYSTEM_WELCOME by booleanPref(defaultValue = true)

    var TRADE_EXCHANGE by booleanPref(defaultValue = true)

    var WALLET_DEPOSIT by booleanPref(defaultValue = true)
    var WALLET_HISTORICAL by booleanPref(defaultValue = true)
    var WALLET_LOCK_UP by booleanPref(defaultValue = true)
    var WALLET_TRANSACTION by booleanPref(defaultValue = true)
    var WALLET_SUBSCRIPTION by booleanPref(defaultValue = true)
    var WALLET_TRANSFER by booleanPref(defaultValue = true)
    var WALLET_WITHDRAWAL by booleanPref(defaultValue = true)

}