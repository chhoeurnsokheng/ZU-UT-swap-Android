package com.zillennium.utswap.screens.navbar.tradeTab.tradeExchangeScreen

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl

class TradeExchangePresenter : BaseMvpPresenterImpl<TradeExchangeView.View>(),
    TradeExchangeView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}