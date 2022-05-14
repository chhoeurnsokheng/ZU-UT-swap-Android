package com.zillennium.utswap.screens.security.securityFragment.newPasswordScreen

import android.content.Intent
import android.content.res.ColorStateList
import android.text.Editable
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import androidx.navigation.fragment.findNavController
import com.zillennium.utswap.Datas.StoredPreferences.SessionPreferences
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.FragmentSecurityNewPasswordBinding
import com.zillennium.utswap.screens.security.securityFragment.signInScreen.SignInFragment

class NewPasswordFragment :
    BaseMvpFragment<NewPasswordView.View, NewPasswordView.Presenter, FragmentSecurityNewPasswordBinding>(),
    NewPasswordView.View {

    override var mPresenter: NewPasswordView.Presenter = NewPasswordPresenter()
    override val layoutResource: Int = R.layout.fragment_security_new_password

    override fun initView() {
        super.initView()
        try {
            binding.apply {

                imgBack.setOnClickListener {
                    findNavController().popBackStack()
                }

                btnNext.setOnClickListener {
                    var isHaveError = false
                    txtPasswordMessage.text = "Invalid Email or Password"

                    if (inputPassword.text.toString().isEmpty()) {
                        txtPasswordMessage.visibility = View.VISIBLE
                        inputPassword.backgroundTintList =
                            ColorStateList.valueOf(resources.getColor(R.color.red))
                        isHaveError = true
                    }

                    if (inputConfirmPassword.text.toString().isEmpty()) {
                        txtPasswordMessage.visibility = View.VISIBLE
                        inputConfirmPassword.backgroundTintList =
                            ColorStateList.valueOf(resources.getColor(R.color.red))
                        isHaveError = true
                    }
                    if (isHaveError) {
                        return@setOnClickListener
                    } else {
                        if(inputPassword.text.toString() != inputConfirmPassword.text.toString()){
                            txtPasswordMessage.text = "Password didn't match"
                            txtPasswordMessage.visibility = View.VISIBLE
                        }else{
                           txtPasswordMessage.visibility = View.GONE

                            SessionPreferences().SESSION_STATUS = true
                            activity?.finish()
                        }
                    }
                }
                inputPassword.addTextChangedListener(object : TextWatcher {
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
                        inputPassword.backgroundTintList =
                            ColorStateList.valueOf(resources.getColor(R.color.secondary_text))

                    }
                })

                inputConfirmPassword.addTextChangedListener(object : TextWatcher {
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
                        inputConfirmPassword.backgroundTintList =
                            ColorStateList.valueOf(resources.getColor(R.color.secondary_text))
                    }
                })
            }
            // Code
        } catch (error: Exception) {
            // Must be safe
        }
    }

    //Show Hide Password
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

    //Show Hide Confirm Password
    fun ShowHideConfirmPassword(view: View) {
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