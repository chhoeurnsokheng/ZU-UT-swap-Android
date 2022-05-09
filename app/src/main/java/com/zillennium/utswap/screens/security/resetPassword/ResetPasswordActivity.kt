package com.zillennium.utswap.screens.security.resetPassword

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.view.View
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivitySecurityResetPasswordBinding
import com.zillennium.utswap.screens.kyc.termConditionScreen.TermConditionActivity
import com.zillennium.utswap.screens.security.signInScreen.SignInActivity
import com.zillennium.utswap.screens.security.verificationScreen.VerificationActivity


class ResetPasswordActivity :
    BaseMvpActivity<ResetPasswordView.View, ResetPasswordView.Presenter, ActivitySecurityResetPasswordBinding>(),
    ResetPasswordView.View {

    override var mPresenter: ResetPasswordView.Presenter = ResetPasswordPresenter()
    override val layoutResource: Int = R.layout.activity_security_reset_password

    override fun initView() {
        super.initView()
        try {
            binding.apply {
                imgBack.setOnClickListener { finish() }
                btnNext.setOnClickListener {
                    if (!resetPassWord()) {
                        false
                    } else {
                        val intent = Intent(UTSwapApp.instance, VerificationActivity::class.java)
                        startActivity(intent)
                        true
                    }

                }

            }
            // Code
        } catch (error: Exception) {
            // Must be safe
        }
    }

    private fun resetPassWord(): Boolean {
        binding.apply {
            val resetPassword = inputPassword.text.toString().trim()

            if (resetPassword.isEmpty()) {
                textEmpty.visibility = View.VISIBLE
                inputPassword.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.main_red))
                return false
            } else {
                textEmpty.visibility = View.GONE
                inputPassword.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.secondary_text))
                return true
            }
        }
    }
}