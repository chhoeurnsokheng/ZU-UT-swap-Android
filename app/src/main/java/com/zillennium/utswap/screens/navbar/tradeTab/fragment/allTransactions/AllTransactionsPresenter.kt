package com.zillennium.utswap.screens.navbar.tradeTab.fragment.allTransactions

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl

class AllTransactionsPresenter : BaseMvpPresenterImpl<AllTransactionsView.View>(),
    AllTransactionsView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}