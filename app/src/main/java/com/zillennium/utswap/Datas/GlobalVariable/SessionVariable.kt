package com.zillennium.utswap.Datas.GlobalVariable

import androidx.lifecycle.MutableLiveData
import com.zillennium.utswap.Datas.StoredPreferences.SessionPreferences
import com.zillennium.utswap.models.FinanceBankModel
import com.zillennium.utswap.models.WithdrawAddbankModel

class SessionVariable {
    companion object {
        var SESSION_STATUS = MutableLiveData<Boolean>(SessionPreferences().SESSION_STATUS)
        var SESSION_KYC = MutableLiveData<Boolean>(SessionPreferences().SESSION_KYC)
        var SESSION_KYC_STATUS = MutableLiveData<Int>(SessionPreferences().SESSION_KYC_STATUS)
        var SESSION_PHONE_NUMBER = MutableLiveData<String>(SessionPreferences().SESSION_PHONE_NUMBER)
        var SESSION_BANK : MutableLiveData<List<FinanceBankModel>> = MutableLiveData<List<FinanceBankModel>>()
        var SESSION_KYC_SUBMIT_STATUS = MutableLiveData<Boolean>(SessionPreferences().SESSION_KYC_SUBMIT_STATUS)
        var BADGE_NUMBER = MutableLiveData<String>(SessionPreferences().BADGE_NUMBER)


        var successTransfer: MutableLiveData<Boolean> = MutableLiveData()


        var requestOrderBookSocket: MutableLiveData<Boolean> = MutableLiveData()
        var requestTradingList: MutableLiveData<Boolean> = MutableLiveData()


        var refreshOrderPending: MutableLiveData<Boolean> = MutableLiveData()
        var refreshMatchingTransaction: MutableLiveData<Boolean> = MutableLiveData()

        var marketPriceSell: MutableLiveData<String> = MutableLiveData()
        var marketPriceBuy: MutableLiveData<String> = MutableLiveData()

        var callDialogErrorCreateOrder: MutableLiveData<Boolean> = MutableLiveData()
        var callDialogSuccessPlaceOrder: MutableLiveData<Boolean> = MutableLiveData()

        var realTimeWatchList: MutableLiveData<Boolean> = MutableLiveData()
        var waitingPlaceOrder: MutableLiveData<Boolean> = MutableLiveData()
        var cancelPlaceOrder: MutableLiveData<Boolean> = MutableLiveData()
        var marketOpen: MutableLiveData<Boolean> = MutableLiveData()
    }
}