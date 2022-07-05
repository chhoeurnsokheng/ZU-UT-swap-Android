package com.zillennium.utswap.module.security.securityFragment.forgotPassword

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl

class ForgotPasswordPresenter : BaseMvpPresenterImpl<ForgotPasswordView.View>(),
    ForgotPasswordView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}