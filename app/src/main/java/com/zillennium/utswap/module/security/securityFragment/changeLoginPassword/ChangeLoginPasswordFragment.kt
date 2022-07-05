package com.zillennium.utswap.module.security.securityFragment.changeLoginPassword

import android.content.res.ColorStateList
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.FragmentAccountChangeLoginPasswordBinding


class ChangeLoginPasswordFragment :
    BaseMvpFragment<ChangeLoginPasswordView.View, ChangeLoginPasswordView.Presenter, FragmentAccountChangeLoginPasswordBinding>(),
    ChangeLoginPasswordView.View {

    override var mPresenter: ChangeLoginPasswordView.Presenter = ChangeLoginPasswordPresenter()
    override val layoutResource: Int = R.layout.fragment_account_change_login_password

    override fun initView() {
        super.initView()
        try {
            binding.apply {
                imgClose.setOnClickListener {
                    activity?.finish()
                }

                btnNext.setOnClickListener {
                    var isHaveError = false

                    if(etConfirmPassword.text.toString().trim() != etNewPassword.text.toString().trim()){
                        txtPasswordMessage.text = "Password didn't match"
                        txtPasswordMessage.visibility = View.VISIBLE
                        etConfirmPassword.backgroundTintList =
                            ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.danger))
                        etNewPassword.backgroundTintList =
                            ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.danger))
                        isHaveError = true
                    }

                    if (etConfirmPassword.text.toString().length < 8) {
                        txtPasswordMessage.text = "Please Enter a Confirm Password Longer Than 8 Digits"
                        txtPasswordMessage.visibility = View.VISIBLE
                        etConfirmPassword.backgroundTintList =
                            ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.danger))
                        isHaveError = true
                    }

                    if(etNewPassword.text.toString().trim() == etOldPassword.text.toString().trim())
                    {
                        txtPasswordMessage.text = "Old Password And New Password Must Not Be The Same"
                        txtPasswordMessage.visibility = View.VISIBLE
                        etNewPassword.backgroundTintList =
                                ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.danger))
                        etOldPassword.backgroundTintList =
                                ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.danger))
                        isHaveError = true
                    }

                    if (etOldPassword.text.toString().length < 8) {
                        txtPasswordMessage.text = "Your Old Password Longer Than 8 Digits"
                        txtPasswordMessage.visibility = View.VISIBLE
                        etOldPassword.backgroundTintList =
                            ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.danger))
                        isHaveError = true
                    }

                    if (etNewPassword.text.toString().length < 8) {
                        txtPasswordMessage.text = "Please Enter a Password Longer Than 8 Digits"
                        txtPasswordMessage.visibility = View.VISIBLE
                        etNewPassword.backgroundTintList =
                            ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.danger))
                        isHaveError = true
                    }

                    if (!isHaveError) {
                        pbNext.visibility = View.VISIBLE
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

                            pbNext.visibility = View.GONE
                            btnNext.isClickable = true
                            btnNext.alpha = 1F

                            findNavController().navigate(R.id.action_to_verification_security_fragment)
                        }, 3000)
                    }
                }

                txtForgotPassword.setOnClickListener {
                    findNavController().navigate(R.id.action_to_forgot_password_fragment)
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