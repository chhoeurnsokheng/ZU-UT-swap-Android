package com.zillennium.utswap.screens.navbar.tradeTab.tradeScreen

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl

class TradePresenter : BaseMvpPresenterImpl<TradeView.View>(),
    TradeView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}