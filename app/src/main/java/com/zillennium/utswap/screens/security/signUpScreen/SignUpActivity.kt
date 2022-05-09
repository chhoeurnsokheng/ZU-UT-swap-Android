package com.zillennium.utswap.screens.security.signUpScreen

import android.content.Intent
import android.content.res.ColorStateList
import android.text.Editable
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivitySecurityNewPasswordBinding
import com.zillennium.utswap.databinding.ActivitySecuritySignUpBinding
import com.zillennium.utswap.screens.kyc.verificationScreen.VerificationActivity
import com.zillennium.utswap.screens.security.signInScreen.SignInActivity
import java.io.IOException
import java.util.regex.Pattern

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
                    if (!validEmail() or !validPassword() or !validConfirmPassword()) {
                        true
                    } else {
                        val intent = Intent(UTSwapApp.instance, VerificationActivity::class.java)
                        startActivity(intent)
                    }
                }

            }
            // Code
        } catch (error: Exception) {
            // Must be safe
        }
    }

    private fun validEmail(): Boolean {
        binding.apply {
            val Email = inputEmail.text.toString().trim()
            if (Email.isEmpty()) {
                txtMessage.visibility = View.VISIBLE
                inputEmail.backgroundTintList =
                    ColorStateList.valueOf(resources.getColor(R.color.main_red))
                return false
            } else {
                txtMessage.visibility = View.GONE
                inputEmail.backgroundTintList =
                    ColorStateList.valueOf(resources.getColor(R.color.secondary_text))
                return true
            }

        }
    }

    private fun validPassword(): Boolean {
        binding.apply {
            val password = inputPassword.text.toString().trim()
            if (password.isEmpty()) {
                txtMessage.visibility = View.VISIBLE
                inputPassword.backgroundTintList =
                    ColorStateList.valueOf(resources.getColor(R.color.main_red))
                return false
            } else {
                txtMessage.visibility = View.GONE
                inputPassword.backgroundTintList =
                    ColorStateList.valueOf(resources.getColor(R.color.secondary_text))
                return true
            }
        }
    }


    private fun validConfirmPassword(): Boolean {
        binding.apply {
            val ConfirmPassword = inputConfirmPassword.text.toString().trim()
            if (ConfirmPassword.isEmpty()) {
                txtMessage.visibility = View.VISIBLE
                inputConfirmPassword.backgroundTintList =
                    ColorStateList.valueOf(resources.getColor(R.color.main_red))
                return false
            } else {
                txtMessage.visibility = View.GONE
                inputConfirmPassword.backgroundTintList =
                    ColorStateList.valueOf(resources.getColor(R.color.secondary_text))
                return true
            }
        }
    }

    //Show Hide password
    fun ShowHidePassword(view: View) {
        binding.apply {
            if (view.id == R.id.show_pass_btn) {
                if (inputPassword.transformationMethod.equals(PasswordTransformationMethod.getInstance())) {
                    showPassBtn.setImageResource(R.drawable.ic_baseline_visibility_off_24)
                    //Show Password
                    inputPassword.transformationMethod =
                        HideReturnsTransformationMethod.getInstance()
                } else {
                    showPassBtn.setImageResource(R.drawable.ic_baseline_visibility_24)
                    //Hide Password
                    inputPassword.transformationMethod = PasswordTransformationMethod.getInstance()
                }
            }
        }
    }

    //Show Hide confirm password
    fun ShowHidePassConfirmPassword(view: View) {
        binding.apply {
            if (view.id == R.id.show_confirm_pass_btn) {
                if (inputConfirmPassword.transformationMethod.equals(PasswordTransformationMethod.getInstance())
                ) {
                    showConfirmPassBtn.setImageResource(R.drawable.ic_baseline_visibility_off_24)
                    //Show Password
                    inputConfirmPassword.transformationMethod =
                        HideReturnsTransformationMethod.getInstance()
                } else {
                    showConfirmPassBtn.setImageResource(R.drawable.ic_baseline_visibility_24)
                    //Hide Password
                    inputConfirmPassword.transformationMethod =
                        PasswordTransformationMethod.getInstance()
                }
            }

        }

    }
}