package com.zillennium.utswap.Datas.GlobalVariable

import androidx.lifecycle.MutableLiveData

class SettingVariable {
    companion object{
        val portfolio_selected: MutableLiveData<String> = MutableLiveData("Performance")

        val balance_filter: MutableLiveData<Int> = MutableLiveData(0)
        val balance_date_start: MutableLiveData<String> = MutableLiveData()
        val balance_date_end: MutableLiveData<String> = MutableLiveData()

        val finance_lock_up_selected: MutableLiveData<String> = MutableLiveData("Buy Back")
    }
}