package com.zillennium.utswap.module.security.securityActivity.resetPasswordScreen

import android.content.Context
import android.os.Bundle
import com.zillennium.utswap.bases.mvp.BaseMvpPresenter
import com.zillennium.utswap.bases.mvp.BaseMvpView

class ResetPasswordView {
    interface View : BaseMvpView {
        override fun initView()
    }

    interface Presenter : BaseMvpPresenter<View> {
        override fun initViewPresenter(context: Context, bundle: Bundle?)
    }
}