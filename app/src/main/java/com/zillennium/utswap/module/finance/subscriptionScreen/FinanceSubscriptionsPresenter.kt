package com.zillennium.utswap.module.finance.subscriptionScreen

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl

class FinanceSubscriptionsPresenter : BaseMvpPresenterImpl<FinanceSubscriptionsView.View>(),
        FinanceSubscriptionsView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}