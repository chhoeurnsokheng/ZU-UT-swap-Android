package com.zillennium.utswap.screens.security.securityFragment.changeLoginPassword

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl

class ChangeLoginPasswordPresenter : BaseMvpPresenterImpl<ChangeLoginPasswordView.View>(),
    ChangeLoginPasswordView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}