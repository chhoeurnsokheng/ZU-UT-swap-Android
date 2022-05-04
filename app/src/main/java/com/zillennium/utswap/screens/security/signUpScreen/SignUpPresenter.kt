package com.zillennium.utswap.screens.security.signUpScreen

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl

class SignUpPresenter : BaseMvpPresenterImpl<SignUpView.View>(),
    SignUpView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}