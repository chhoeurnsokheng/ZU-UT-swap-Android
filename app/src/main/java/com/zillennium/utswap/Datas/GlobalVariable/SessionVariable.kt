package com.zillennium.utswap.Datas.GlobalVariable

import androidx.lifecycle.MutableLiveData
import com.zillennium.utswap.Datas.StoredPreferences.SessionPreferences
import com.zillennium.utswap.models.FinanceBankModel
import com.zillennium.utswap.models.WithdrawAddbankModel

class SessionVariable {
    companion object {
        var SESSION_ID = SessionPreferences().SESSION_ID
        var SESSION_USERNAME = SessionPreferences().SESSION_USERNAME
        var SESSION_PASSWORD = SessionPreferences().SESSION_PASSWORD
        var SESSION_STATUS = MutableLiveData<Boolean>(SessionPreferences().SESSION_STATUS)
        var SESSION_KYC = MutableLiveData<Boolean>(SessionPreferences().SESSION_KYC)
        var SESSION_KYC_STATUS = MutableLiveData<Int>(SessionPreferences().SESSION_KYC_STATUS)
        var SESSION_PHONE_NUMBER = MutableLiveData<String>(SessionPreferences().SESSION_PHONE_NUMBER)
        var SESSION_TOKEN = SessionPreferences().SESSION_TOKEN
        var SESSION_EXPIRED = SessionPreferences().SESSION_EXPIRED
        var SESSION_BANK : MutableLiveData<List<FinanceBankModel>> = MutableLiveData<List<FinanceBankModel>>()
    }
}