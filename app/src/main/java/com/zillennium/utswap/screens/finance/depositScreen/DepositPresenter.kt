package com.zillennium.utswap.screens.finance.depositScreen

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl

class DepositPresenter : BaseMvpPresenterImpl<DepositView.View>(),
        DepositView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}