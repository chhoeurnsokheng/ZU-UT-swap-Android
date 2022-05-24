package com.zillennium.utswap.screens.navbar.tradeTab.fragment.tradeDetail

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl

class TradeDetailPresenter : BaseMvpPresenterImpl<TradeDetailView.View>(),
    TradeDetailView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}