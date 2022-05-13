package com.zillennium.utswap.screens.security.securityActivity.registerScreen

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl
import com.zillennium.utswap.screens.security.securityActivity.signInScreen.SignInView

class RegisterPresenter : BaseMvpPresenterImpl<SignInView.View>(),
    SignInView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}