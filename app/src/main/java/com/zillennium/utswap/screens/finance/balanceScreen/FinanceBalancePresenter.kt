package com.zillennium.utswap.screens.finance.balanceScreen

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl

class FinanceBalancePresenter : BaseMvpPresenterImpl<FinanceBalanceView.View>(),
    FinanceBalanceView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}