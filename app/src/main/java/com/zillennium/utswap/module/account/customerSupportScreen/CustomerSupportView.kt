package com.zillennium.utswap.module.account.customerSupportScreen

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenter
import com.zillennium.utswap.bases.mvp.BaseMvpView
import com.zillennium.utswap.models.customerSupport.CustomerSupport

class CustomerSupportView {
    interface View : BaseMvpView {
        override fun initView()
        fun onGetCustomerSupportSuccess(data: CustomerSupport.CustomerSupportData)
//        fun onGetCustomerSupportFail(data: CustomerSupport.CustomerSupportRes)
    }

    interface Presenter : BaseMvpPresenter<View> {
        override fun initViewPresenter(context: Context, bundle: Bundle?)
        fun getCustomerSupport()
    }
}