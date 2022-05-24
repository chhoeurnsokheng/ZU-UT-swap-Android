package com.zillennium.utswap.screens.navbar.tradeTab.fragment.orderBook

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl

class OrderBookPresenter : BaseMvpPresenterImpl<OrderBookView.View>(),
    OrderBookView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}