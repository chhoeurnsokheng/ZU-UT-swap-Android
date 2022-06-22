package com.zillennium.utswap.screens.account.lockTimeOutScreen

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl

class LockTimeOutPresenter : BaseMvpPresenterImpl<LockTimeOutView.View>(),
    LockTimeOutView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}