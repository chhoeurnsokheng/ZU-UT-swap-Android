package com.zillennium.utswap.Datas.GlobalVariable

import androidx.lifecycle.MutableLiveData

class SettingVariable {
    companion object{
        val portfolio_selected: MutableLiveData<String> = MutableLiveData("Price")

        val balance_filter: MutableLiveData<Int> = MutableLiveData(0)
        val balance_date_start: MutableLiveData<String> = MutableLiveData()
        val balance_date_end: MutableLiveData<String> = MutableLiveData()

        val finance_lock_up_selected: MutableLiveData<String> = MutableLiveData("Buy Back")

        val finance_subscription_filter: MutableLiveData<String> = MutableLiveData("")
        val finance_subscription_filter_project_name: MutableLiveData<String> = MutableLiveData("All Projects")

        val finance_subscription_date_start: MutableLiveData<String> = MutableLiveData()
        val finance_subscription_date_end: MutableLiveData<String> = MutableLiveData()

        val phoneNumber: MutableLiveData<String> = MutableLiveData("")

        val finance_historical_selected: MutableLiveData<String> = MutableLiveData("My Transactions")
        val finance_historical_filter: MutableLiveData<String> = MutableLiveData("UT All Projects")
        val finance_historical_date_start: MutableLiveData<String> = MutableLiveData()
        val finance_historical_date_end: MutableLiveData<String> = MutableLiveData()
        val finance_historical_spinner_item: MutableLiveData<Int> = MutableLiveData(0)

    }
}