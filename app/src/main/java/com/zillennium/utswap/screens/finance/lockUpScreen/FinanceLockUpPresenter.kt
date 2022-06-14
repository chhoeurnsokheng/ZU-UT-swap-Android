package com.zillennium.utswap.screens.finance.lockUpScreen

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl

class FinanceLockUpPresenter : BaseMvpPresenterImpl<FinanceLockUpView.View>(),
    FinanceLockUpView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}