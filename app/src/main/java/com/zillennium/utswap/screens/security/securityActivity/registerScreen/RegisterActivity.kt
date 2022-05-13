package com.zillennium.utswap.screens.security.securityActivity.registerScreen

import com.zillennium.utswap.R
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivitySecurityRegisterBinding
import com.zillennium.utswap.screens.security.securityActivity.signInScreen.SIgnInPresenter
import com.zillennium.utswap.screens.security.securityActivity.signInScreen.SignInView

class RegisterActivity :
    BaseMvpActivity<SignInView.View, SignInView.Presenter, ActivitySecurityRegisterBinding>(),
    SignInView.View {

    override var mPresenter: SignInView.Presenter = SIgnInPresenter()
    override val layoutResource: Int = R.layout.activity_security_register

    override fun initView() {
        super.initView()
        try {

        } catch (error: Exception) {
            // Must be safe
        }
    }

}