package com.zillennium.utswap.screens.security.resetPassword

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
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
                    var isHaveError = false
                    if (inputEmail.text.toString().isEmpty()) {
                        textEmpty.visibility = View.VISIBLE
                        inputEmail.backgroundTintList =
                            ColorStateList.valueOf(resources.getColor(R.color.red))
                        isHaveError = true
                    }
                    else{
                        val intent = Intent(UTSwapApp.instance, VerificationActivity::class.java)
                        startActivity(intent)
                    }

                }
                inputEmail.addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(
                        charSequence: CharSequence,
                        i: Int,
                        i1: Int,
                        i2: Int
                    ) {
                    }

                    override fun onTextChanged(
                        charSequence: CharSequence,
                        i: Int,
                        i1: Int,
                        i2: Int
                    ) {
                    }

                    override fun afterTextChanged(editable: Editable) {
                        textEmpty.visibility = View.GONE
                        inputEmail.backgroundTintList =
                            ColorStateList.valueOf(resources.getColor(R.color.secondary_text))
                    }
                })
            }
            // Code
        } catch (error: Exception) {
            // Must be safe
        }
    }
}