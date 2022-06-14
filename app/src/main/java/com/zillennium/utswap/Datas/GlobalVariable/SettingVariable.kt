package com.zillennium.utswap.Datas.GlobalVariable

import androidx.lifecycle.MutableLiveData

class SettingVariable {
    companion object{
        val portfolio_selected: MutableLiveData<String> = MutableLiveData("Performance")
        val finance_subscription_filter: MutableLiveData<String> = MutableLiveData("All Projects")
        val finance_subscription_date_start: MutableLiveData<String> = MutableLiveData()
        val finance_subscription_date_end: MutableLiveData<String> = MutableLiveData()
    }
}