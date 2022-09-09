package com.zillennium

import com.zillennium.utswap.Datas.GlobalVariable.SessionVariable
import com.zillennium.utswap.Datas.StoredPreferences.SessionPreferences

/**
 * Created by Sokheng Chhoeurn on 6/9/22.
 * Build in Mac
 */

 object CheckUserLoginClearToken {
     fun clearTokenExpired(){
         SessionVariable.SESSION_STATUS.value = false
         SessionVariable.SESSION_KYC.value = false
         SessionVariable.SESSION_KYC_STATUS.value = 0
         SessionPreferences().removeValue("SESSION_TOKEN")
         SessionPreferences().removeValue("SESSION_ID")
         SessionPreferences().removeValue("SESSION_USERNAME")
         SessionPreferences().removeValue("SESSION_KYC")
         SessionPreferences().removeValue("SESSION_X_TOKEN_API")
         SessionPreferences().removeValue("SESSION_STATUS")
         SessionPreferences().removeValue("SESSION_KYC_SUBMIT_STATUS")
         SessionPreferences().removeValue("SESSION_KYC_STATUS")
     }
}