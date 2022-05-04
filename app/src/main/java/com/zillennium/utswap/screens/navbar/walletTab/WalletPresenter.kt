package com.zillennium.utswap.screens.navbar.walletTab

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl

class WalletPresenter : BaseMvpPresenterImpl<WalletView.View>(),
    WalletView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}