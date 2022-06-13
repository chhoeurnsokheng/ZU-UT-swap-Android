package com.zillennium.utswap.Datas.GlobalVariable

import androidx.lifecycle.MutableLiveData

class SettingVariable {
    companion object{
        val portfolio_selected: MutableLiveData<String> = MutableLiveData("Performance")
        val finance_lock_up_selected: MutableLiveData<String> = MutableLiveData("Buy Back")


    }
}