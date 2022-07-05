package com.zillennium.utswap.module.finance.addCardScreen

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl

class DepositAddCardPresenter : BaseMvpPresenterImpl<DepositAddCardView.View>(),
        DepositAddCardView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}