package com.zillennium.utswap.module.security.securityActivity.registerScreen

import com.zillennium.utswap.R
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivitySecurityRegisterBinding
import com.zillennium.utswap.module.security.securityActivity.signInScreen.SIgnInPresenter
import com.zillennium.utswap.module.security.securityActivity.signInScreen.SignInView

class RegisterActivity :
    BaseMvpActivity<RegisterView.View, RegisterView.Presenter, ActivitySecurityRegisterBinding>(),
    RegisterView.View {

    var fromVerify = false

    override var mPresenter: RegisterView.Presenter = RegisterPresenter()
    override val layoutResource: Int = R.layout.activity_security_register

    override fun initView() {
        super.initView()
        try {

        } catch (error: Exception) {
            // Must be safe
        }
    }

}