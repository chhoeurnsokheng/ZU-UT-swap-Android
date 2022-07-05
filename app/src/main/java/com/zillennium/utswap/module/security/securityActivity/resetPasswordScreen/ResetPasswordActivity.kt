package com.zillennium.utswap.module.security.securityActivity.resetPasswordScreen

import com.zillennium.utswap.R
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivitySecurityResetPasswordBinding
import com.zillennium.utswap.module.security.securityActivity.signInScreen.SIgnInPresenter
import com.zillennium.utswap.module.security.securityActivity.signInScreen.SignInView

class ResetPasswordActivity :
    BaseMvpActivity<SignInView.View, SignInView.Presenter, ActivitySecurityResetPasswordBinding>(),
    SignInView.View {

    override var mPresenter: SignInView.Presenter = SIgnInPresenter()
    override val layoutResource: Int = R.layout.activity_security_reset_password

    override fun initView() {
        super.initView()
        try {

        } catch (error: Exception) {
            // Must be safe
        }
    }

}