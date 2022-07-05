package com.zillennium.utswap.module.main.trade.tradeScreen

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