package com.zillennium.utswap.module.main.trade.tradeExchangeScreen.fragment.chart

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl

class ChartPresenter : BaseMvpPresenterImpl<ChartView.View>(),
    ChartView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}