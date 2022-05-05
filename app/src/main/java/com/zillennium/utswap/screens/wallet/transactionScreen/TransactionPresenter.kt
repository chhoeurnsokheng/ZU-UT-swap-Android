package com.zillennium.utswap.screens.wallet.transactionScreen

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl

class TransactionPresenter : BaseMvpPresenterImpl<TransactionView.View>(),
    TransactionView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}