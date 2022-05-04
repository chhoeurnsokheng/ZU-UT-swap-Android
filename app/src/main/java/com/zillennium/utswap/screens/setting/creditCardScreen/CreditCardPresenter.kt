package com.zillennium.utswap.screens.setting.creditCardScreen

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl

class CreditCardPresenter : BaseMvpPresenterImpl<CreditCardView.View>(),
    CreditCardView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}