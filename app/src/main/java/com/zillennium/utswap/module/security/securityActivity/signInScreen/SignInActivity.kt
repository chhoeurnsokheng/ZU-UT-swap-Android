package com.zillennium.utswap.module.security.securityActivity.signInScreen

import com.zillennium.utswap.R
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivitySecuritySignInBinding

class SignInActivity :
    BaseMvpActivity<SignInView.View, SignInView.Presenter, ActivitySecuritySignInBinding>(),
    SignInView.View {

    override var mPresenter: SignInView.Presenter = SIgnInPresenter()
    override val layoutResource: Int = R.layout.activity_security_sign_in

    override fun initView() {
        super.initView()
        try {

        } catch (error: Exception) {
            // Must be safe
        }
    }

}