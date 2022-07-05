package com.zillennium.utswap.module.main.trade.tradeExchangeScreen.fragment.orders

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl

class OrdersPresenter : BaseMvpPresenterImpl<OrdersView.View>(),
    OrdersView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}