package com.zillennium.utswap.api.manager

import com.zillennium.utswap.api.ApiSettings
import com.zillennium.utswap.models.customerSupport.CustomerSupport
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class ApiCustomerSupportImp : ApiManager() {
    fun customerSupport(): Observable<CustomerSupport.CustomerSupportRes> =
        mCustomerSupport.customerSupport().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}