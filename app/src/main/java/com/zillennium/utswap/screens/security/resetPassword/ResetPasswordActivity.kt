package com.zillennium.utswap.screens.security.resetPassword

import android.content.Intent
import com.zillennium.utswap.databinding.ActivitySecurityResetPasswordBinding

import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.screens.security.verificationScreen.VerificationActivity
import java.io.IOException

class ResetPasswordActivity :
    BaseMvpActivity<ResetPasswordView.View, ResetPasswordView.Presenter, ActivitySecurityResetPasswordBinding>(),
    ResetPasswordView.View {

    override var mPresenter: ResetPasswordView.Presenter = ResetPasswordPresenter()
    override val layoutResource: Int = R.layout.activity_security_reset_password

    override fun initView() {
        super.initView()
        try {
            binding.apply {

                btnNext.setOnClickListener {
                    val intent = Intent(UTSwapApp.instance, VerificationActivity::class.java)
                    startActivity(intent)
                }

            }
            // Code
        } catch (error: Exception) {
            // Must be safe
        }
    }
}