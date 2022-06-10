package com.zillennium.utswap.screens.navbar.tradeTab.tradeExchangeScreen.fragment.Transactions.fragment

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl

class TransactionDetailPresenter : BaseMvpPresenterImpl<TransactionDetailView.View>(),
    TransactionDetailView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}