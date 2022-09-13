package com.zillennium.utswap.utils

import com.zillennium.utswap.Datas.GlobalVariable.SessionVariable
import com.zillennium.utswap.Datas.StoredPreferences.SessionPreferences

object ClientClearData {
    fun clearDataUser(){
        SessionVariable.SESSION_STATUS.value = false
        SessionVariable.SESSION_KYC.value = false
        SessionVariable.SESSION_KYC_STATUS.value = 0
        SessionVariable.USER_EXPIRE_TOKEN.value = true

        SessionPreferences().removeValue("SESSION_ID")
        SessionPreferences().removeValue("SESSION_TOKEN")
        SessionPreferences().removeValue("SESSION_USERNAME")
        SessionPreferences().removeValue("SESSION_KYC")
        SessionPreferences().removeValue("SESSION_X_TOKEN_API")
        SessionPreferences().removeValue("SESSION_STATUS")
        SessionPreferences().removeValue("SESSION_KYC_SUBMIT_STATUS")
        SessionPreferences().removeValue("SESSION_KYC_STATUS")
    }
}