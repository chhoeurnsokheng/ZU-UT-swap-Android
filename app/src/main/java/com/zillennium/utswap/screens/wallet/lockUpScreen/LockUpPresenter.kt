package com.zillennium.utswap.screens.wallet.lockUpScreen

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl

class LockUpPresenter : BaseMvpPresenterImpl<LockUpView.View>(),
    LockUpView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}