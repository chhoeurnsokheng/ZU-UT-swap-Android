package com.zillennium.utswap.screens.wallet.MyWallet

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl

class MyWalletPresenter : BaseMvpPresenterImpl<MyWalletView.View>(),
    MyWalletView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}