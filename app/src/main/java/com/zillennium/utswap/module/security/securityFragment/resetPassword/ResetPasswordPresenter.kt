package com.zillennium.utswap.module.security.securityFragment.resetPassword

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenterImpl

class ResetPasswordPresenter : BaseMvpPresenterImpl<ResetPasswordView.View>(),
    ResetPasswordView.Presenter {
    override fun initViewPresenter(context: Context, bundle: Bundle?) {
        mBundle = bundle
        mContext = context
        mView?.initView()
    }
}