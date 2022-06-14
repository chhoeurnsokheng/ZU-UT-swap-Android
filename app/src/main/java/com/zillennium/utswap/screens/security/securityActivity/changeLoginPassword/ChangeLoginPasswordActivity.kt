package com.zillennium.utswap.screens.security.securityActivity.changeLoginPassword

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import androidx.core.content.ContextCompat
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivityChangeLoginPasswordBinding
import com.zillennium.utswap.screens.account.verificationAccunt.VerificationAccountActivity
import com.zillennium.utswap.screens.security.securityActivity.forgotPassword.ForgotPasswordActivity


class ChangeLoginPasswordActivity :
    BaseMvpActivity<ChangeLoginPasswordView.View, ChangeLoginPasswordView.Presenter, ActivityChangeLoginPasswordBinding>(),
    ChangeLoginPasswordView.View {

    override var mPresenter: ChangeLoginPasswordView.Presenter = ChangeLoginPasswordPresenter()
    override val layoutResource: Int = R.layout.activity_change_login_password

    override fun initView() {
        super.initView()
        try {
            binding.apply {
                imgClose.setOnClickListener {
                    finish()
                }

                btnNext.setOnClickListener {
                    var isHaveError = false

                    if(etConfirmPassword.text.toString().trim() != etNewPassword.text.toString().trim()){
                        txtPasswordMessage.text = "Password didn't match"
                        txtPasswordMessage.visibility = View.VISIBLE
                        etConfirmPassword.backgroundTintList =
                            ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.main_red))
                        etNewPassword.backgroundTintList =
                            ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.main_red))
                        isHaveError = true
                    }

                    if (etConfirmPassword.text.toString().length < 8) {
                        txtPasswordMessage.text = "Please Enter a Confirm Password Longer Than 8 Digits"
                        txtPasswordMessage.visibility = View.VISIBLE
                        etConfirmPassword.backgroundTintList =
                            ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.main_red))
                        isHaveError = true
                    }

                    if (etOldPassword.text.toString().length < 8) {
                        txtPasswordMessage.text = "Your Old Password Longer Than 8 Digits"
                        txtPasswordMessage.visibility = View.VISIBLE
                        etOldPassword.backgroundTintList =
                            ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.main_red))
                        isHaveError = true
                    }

                    if (etNewPassword.text.toString().length < 8) {
                        txtPasswordMessage.text = "Please Enter a Password Longer Than 8 Digits"
                        txtPasswordMessage.visibility = View.VISIBLE
                        etNewPassword.backgroundTintList =
                            ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.main_red))
                        isHaveError = true
                    }

                    if (!isHaveError) {
                        btnNext.isClickable = false
                        btnNext.alpha = 0.6F

                        Handler().postDelayed({
                            txtPasswordMessage.visibility = View.GONE
                            etNewPassword.backgroundTintList =
                                ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.secondary_text))
                            etOldPassword.backgroundTintList =
                                ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.secondary_text))
                            etConfirmPassword.backgroundTintList =
                                ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.secondary_text))

                            btnNext.isClickable = true
                            btnNext.alpha = 1F

                            val intent = Intent(UTSwapApp.instance, VerificationAccountActivity::class.java)
                            intent.putExtra(Intent.EXTRA_TEXT, "Change Login Password");
                            startActivity(intent)
                        }, 500)
                    }
                }

                txtForgotPassword.setOnClickListener {
                    val intent = Intent(UTSwapApp.instance, ForgotPasswordActivity::class.java)
                    intent.putExtra(Intent.EXTRA_TEXT, "Change Login Password");
                    startActivity(intent)
                }

                etOldPassword.addTextChangedListener(object : TextWatcher {
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
                        txtPasswordMessage.visibility = View.GONE
                        etOldPassword.backgroundTintList =
                            ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.secondary_text))

                    }
                })

                etNewPassword.addTextChangedListener(object : TextWatcher {
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
                        txtPasswordMessage.visibility = View.GONE
                        etNewPassword.backgroundTintList =
                            ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.secondary_text))

                    }
                })

                etConfirmPassword.addTextChangedListener(object : TextWatcher {
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
                        txtPasswordMessage.visibility = View.GONE
                        etConfirmPassword.backgroundTintList =
                            ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.secondary_text))

                    }
                })

                imgConfirmPassword.setOnClickListener { view ->
                    showHideConfirmPassword(view)
                }

                imgNewPassword.setOnClickListener { view ->
                    showHideNewPassword(view)
                }

                imgOldPassword.setOnClickListener { view ->
                    showHideOldPassword(view)
                }
            }
            // Code
        } catch (error: Exception) {
            // Must be safe
        }
    }

    //Show Hide Password
    fun showHideOldPassword(view: View) {
        binding.apply {
            if (view.id == R.id.img_old_password) {
                if (etOldPassword.transformationMethod.equals(PasswordTransformationMethod.getInstance())) {
                    imgOldPassword.setImageResource(R.drawable.ic_baseline_visibility_off_24)
                    //Show Password
                    etOldPassword.transformationMethod =
                        HideReturnsTransformationMethod.getInstance()
                }else{
                    imgOldPassword.setImageResource(R.drawable.ic_baseline_visibility_24)
                    //Hide Password
                    etOldPassword.transformationMethod = PasswordTransformationMethod.getInstance()
                }
                etOldPassword.requestFocus()
                etOldPassword.setSelection(etOldPassword.text.length)
            }
        }
    }

    fun showHideConfirmPassword(view: View) {
        binding.apply {
            if (view.id == R.id.img_confirm_password) {
                if (etConfirmPassword.transformationMethod.equals(PasswordTransformationMethod.getInstance())
                ) {
                    imgConfirmPassword.setImageResource(R.drawable.ic_baseline_visibility_off_24)
                    //Show Password
                    etConfirmPassword.transformationMethod =
                        HideReturnsTransformationMethod.getInstance()
                }else{
                    imgConfirmPassword.setImageResource(R.drawable.ic_baseline_visibility_24)
                    //Hide Password
                    etConfirmPassword.transformationMethod =
                        PasswordTransformationMethod.getInstance()
                }
                etConfirmPassword.requestFocus()
                etConfirmPassword.setSelection(etConfirmPassword.text.length)
            }

        }

    }

    fun showHideNewPassword(view: View) {
        binding.apply {
            if (view.id == R.id.img_new_password) {
                if (etNewPassword.transformationMethod.equals(PasswordTransformationMethod.getInstance())
                ) {
                    imgNewPassword.setImageResource(R.drawable.ic_baseline_visibility_off_24)
                    //Show Password
                    etNewPassword.transformationMethod =
                        HideReturnsTransformationMethod.getInstance()
                }else{
                    imgNewPassword.setImageResource(R.drawable.ic_baseline_visibility_24)
                    //Hide Password
                    etNewPassword.transformationMethod =
                        PasswordTransformationMethod.getInstance()
                }
                etNewPassword.requestFocus()
                etNewPassword.setSelection(etNewPassword.text.length)
            }

        }

    }
}