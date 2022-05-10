package com.zillennium.utswap.screens.security.newPasswordScreen

import android.content.Intent
import android.content.res.ColorStateList
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivitySecurityNewPasswordBinding
import com.zillennium.utswap.screens.navbar.navbar.NavbarActivity
import com.zillennium.utswap.screens.security.signInScreen.SignInActivity
import java.io.IOException

class NewPasswordActivity :
    BaseMvpActivity<NewPasswordView.View, NewPasswordView.Presenter, ActivitySecurityNewPasswordBinding>(),
    NewPasswordView.View {

    override var mPresenter: NewPasswordView.Presenter = NewPasswordPresenter()
    override val layoutResource: Int = R.layout.activity_security_new_password

    override fun initView() {
        super.initView()
        try {
            binding.apply {

                imgBack.setOnClickListener { finish() }

                btnNext.setOnClickListener {
//                    val pass = inputPassword.text.toString()
//                    val confirmPass = inputConfirmPassword.text.toString()

//                    /** is password not much */
//                    if (pass != confirmPass) {
//                        txtErrorPasswordMessage.visibility = View.VISIBLE
//                        inputPassword.backgroundTintList =
//                            ColorStateList.valueOf(resources.getColor(R.color.main_red))
//                        inputConfirmPassword.backgroundTintList =
//                            ColorStateList.valueOf(resources.getColor(R.color.main_red))
//                        false
//                    } else {
//                        inputPassword.backgroundTintList =
//                            ColorStateList.valueOf(resources.getColor(R.color.secondary_text))
//                        inputConfirmPassword.backgroundTintList =
//                            ColorStateList.valueOf(resources.getColor(R.color.secondary_text))
//                        txtErrorPasswordMessage.visibility = View.GONE
//                        val intent = Intent(UTSwapApp.instance, SignInActivity::class.java)
//                        startActivity(intent)
//                        true
//                    }
                }

            }
            // Code
        } catch (error: Exception) {
            // Must be safe
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