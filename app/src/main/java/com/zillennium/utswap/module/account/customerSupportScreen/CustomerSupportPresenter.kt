package com.zillennium.utswap.module.account.customerSupportScreen

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl

class CustomerSupportPresenter : BaseMvpPresenterImpl<CustomerSupportView.View>(),
    CustomerSupportView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}