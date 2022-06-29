package com.zillennium.utswap.screens.security.securityFragment.newPasswordScreen

import android.content.Intent
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
import com.zillennium.utswap.databinding.FragmentSecurityNewPasswordBinding
import com.zillennium.utswap.screens.security.securityActivity.signInScreen.SignInActivity

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

                when (arguments?.getString("title")) {
                    "change login password"->{
                        title.text = "Change Login Password"
                        title.visibility = View.VISIBLE
                    }
                    else -> {
                        title.visibility = View.GONE
                    }
                }

                btnNext.setOnClickListener {
                    var isHaveError = false
                    txtPasswordMessage.text = "Invalid Email or Password"

                    if(inputConfirmPassword.text.toString().trim() != inputPassword.text.toString().trim()){
                        txtPasswordMessage.text = "Password didn't match"
                        txtPasswordMessage.visibility = View.VISIBLE
                        inputPassword.backgroundTintList =
                            ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.danger))
                        inputConfirmPassword.backgroundTintList =
                            ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.danger))
                        isHaveError = true
                    }

                    if (inputConfirmPassword.text.toString().length < 8) {
                        txtPasswordMessage.text = "Please Enter a Confirm Password Longer Than 8 Digits"
                        txtPasswordMessage.visibility = View.VISIBLE
                        inputConfirmPassword.backgroundTintList =
                            ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.danger))
                        isHaveError = true
                    }

                    if (inputPassword.text.toString().length < 8) {
                        txtPasswordMessage.text = "Please Enter a Password Longer Than 8 Digits"
                        txtPasswordMessage.visibility = View.VISIBLE
                        inputPassword.backgroundTintList =
                            ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.danger))
                        isHaveError = true
                    }

                    if (!isHaveError) {
                        pbNext.visibility = View.VISIBLE
                        btnNext.isClickable = false
                        btnNext.alpha = 0.6F

                        Handler().postDelayed({
                            txtPasswordMessage.visibility = View.GONE
                            inputPassword.backgroundTintList =
                                ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.secondary_text))
                            inputConfirmPassword.backgroundTintList =
                                ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.secondary_text))
//                            SessionPreferences().SESSION_STATUS = true

                            pbNext.visibility = View.GONE
                            btnNext.isClickable = true
                            btnNext.alpha = 1F


                            when (arguments?.getString("title")) {
                                "change login password"->{
                                    activity?.finish()
                                }
                                else -> {
                                    activity?.finish()
                                    val intent = Intent(UTSwapApp.instance, SignInActivity::class.java)
                                    startActivity(intent)
                                }
                            }
                        }, 3000)
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
                            ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.secondary_text))

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
                            ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.secondary_text))
                    }
                })

                showPassBtn.setOnClickListener { view ->
                    ShowHidePassword(view)
                }

                showConfirmPassBtn.setOnClickListener { view ->
                    ShowHideConfirmPassword(view)
                }

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
                inputPassword.requestFocus()
                inputPassword.setSelection(inputPassword.text.length)
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
                inputConfirmPassword.requestFocus()
                inputConfirmPassword.setSelection(inputConfirmPassword.text.length)
            }

        }

    }
}