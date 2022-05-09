package com.zillennium.utswap.screens.security.signUpScreen

import android.content.Intent
import android.view.View
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivitySecurityNewPasswordBinding
import com.zillennium.utswap.databinding.ActivitySecuritySignUpBinding
import com.zillennium.utswap.screens.kyc.kycApplicationApprovedScreen.KycApplicationApprovedActivity
import com.zillennium.utswap.screens.security.verificationScreen.VerificationActivity
import java.io.IOException

class SignUpActivity :
    BaseMvpActivity<SignUpView.View, SignUpView.Presenter, ActivitySecuritySignUpBinding>(),
    SignUpView.View {

    override var mPresenter: SignUpView.Presenter = SignUpPresenter()
    override val layoutResource: Int = R.layout.activity_security_sign_up

    override fun initView() {
        super.initView()
        try {
            binding.apply {

                imgBack.setOnClickListener { finish() }

                btnSignup.setOnClickListener {
                    val intent = Intent(UTSwapApp.instance, KycApplicationApprovedActivity::class.java)
                    startActivity(intent)
                }

            }
            // Code
        } catch (error: Exception) {
            // Must be safe
        }
    }
}