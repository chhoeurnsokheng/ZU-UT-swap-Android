package com.zillennium.utswap.module.security.securityFragment.registerScreen

import android.content.Context
import android.content.res.ColorStateList
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.zillennium.utswap.Datas.StoredPreferences.SessionPreferences
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.FragmentSecurityRegisterBinding
import com.zillennium.utswap.utils.validate

class RegisterFragment :
    BaseMvpFragment<RegisterView.View, RegisterView.Presenter, FragmentSecurityRegisterBinding>(),
    RegisterView.View {

    override var mPresenter: RegisterView.Presenter = RegisterPresenter()
    override val layoutResource: Int = R.layout.fragment_security_register

    override fun initView() {
        super.initView()
        try {
            binding.apply {

                btnBack.setOnClickListener {
                    activity?.finish()
                }

                layView.setOnClickListener {
                    val inputManager =
                        requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    inputManager.hideSoftInputFromWindow(view?.windowToken, 0)
                }

                laySignUp.setOnClickListener {
                    val inputManager =
                        requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    inputManager.hideSoftInputFromWindow(view?.windowToken, 0)
                }


                btnSignup.setOnClickListener {

                    var isHaveError = false
                    txtMessage.text = "Invalid. Please Try Again"
                    txtMessage.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.danger))

                    if(!validate().isValidEmail(inputEmail.text.toString().trim()) && !validate().isValidPhoneNumber(inputEmail.text.toString().trim())){
                        txtMessage.text = "Please Enter Email or Phone Number"
                        txtMessage.visibility = View.VISIBLE
                        inputEmail.backgroundTintList =
                            ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.danger))
                        isHaveError = true
                        return@setOnClickListener
                    }

                    if (inputPassword.text.toString().length < 8) {
                        txtMessage.text = "Please Enter a Password Longer Than 8 Digits"
                        txtMessage.visibility = View.VISIBLE
                        inputPassword.backgroundTintList =
                            ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.danger))
                        isHaveError = true
                        return@setOnClickListener
                    }

                    if (inputConfirmPassword.text.toString().length < 8) {
                        txtMessage.text = "Invalid. Please Try Again"
                        txtMessage.visibility = View.VISIBLE
                        inputConfirmPassword.backgroundTintList =
                            ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.danger))
                        isHaveError = true
                        return@setOnClickListener
                    }

                    if(inputConfirmPassword.text.toString().trim() != inputPassword.text.toString().trim()){
                        txtMessage.text = "Password didn't match"
                        txtMessage.visibility = View.VISIBLE
                        inputPassword.backgroundTintList =
                            ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.danger))
                        inputConfirmPassword.backgroundTintList =
                            ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.danger))
                        isHaveError = true
                        return@setOnClickListener
                    }

                    if (!isHaveError) {

                        pbSignup.visibility = View.VISIBLE
                        btnSignup.isClickable = false
                        btnSignup.alpha = 0.6F

                        Handler().postDelayed({

                            if(inputEmail.text.toString().trim() == "utswap@gmail.com"){
                                txtMessage.visibility = View.VISIBLE
                                txtMessage.text = "Email/Phone Number Unavailable"
                            }else{
                                txtMessage.visibility = View.GONE
                                SessionPreferences().SESSION_USERNAME = inputEmail.text.toString().trim()
                                SessionPreferences().SESSION_PASSWORD = inputPassword.text.toString().trim()
                                findNavController().navigate(R.id.action_to_verification_security_fragment)
                            }

                            pbSignup.visibility = View.GONE
                            btnSignup.isClickable = true
                            btnSignup.alpha = 1F
                        }, 3000)


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
                        txtMessage.visibility = View.GONE
                        inputEmail.backgroundTintList =
                            ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.secondary_text))
                    }
                })

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
                        txtMessage.visibility = View.GONE
                        inputPassword.backgroundTintList =
                            ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.secondary_text))

                    }
                })

                showPassBtn.setOnClickListener { ShowHidePassword() }

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
                        txtMessage.visibility = View.GONE
                        inputConfirmPassword.backgroundTintList =
                            ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.secondary_text))
                    }
                })

                showConfirmPassBtn.setOnClickListener { ShowHidePassConfirmPassword() }

            }
            // Code
        } catch (error: Exception) {
            // Must be safe
        }
    }

    //Show Hide Password
    fun ShowHidePassword() {
        binding.apply{
            if (inputPassword.transformationMethod.equals(PasswordTransformationMethod.getInstance())) {
                showPassBtn.setImageResource(R.drawable.ic_baseline_visibility_off_24)

                //Show Password
                inputPassword.transformationMethod= HideReturnsTransformationMethod.getInstance()
            } else {
                showPassBtn.setImageResource(R.drawable.ic_baseline_visibility_24)

                //Hide Password
                inputPassword.transformationMethod= PasswordTransformationMethod.getInstance()
            }
            inputPassword.requestFocus()
            inputPassword.setSelection(inputPassword.text.length)
        }
    }

    //Show Hide confirm password
    fun ShowHidePassConfirmPassword() {
        binding.apply{
            if (inputConfirmPassword.transformationMethod.equals(PasswordTransformationMethod.getInstance())
            ) {
                showConfirmPassBtn.setImageResource(R.drawable.ic_baseline_visibility_off_24)

                //Show Password
                inputConfirmPassword.transformationMethod= HideReturnsTransformationMethod.getInstance()
            } else {
                showConfirmPassBtn.setImageResource(R.drawable.ic_baseline_visibility_24)

                //Hide Password
                inputConfirmPassword.transformationMethod= PasswordTransformationMethod.getInstance()
            }
            inputConfirmPassword.requestFocus()
            inputConfirmPassword.setSelection(inputConfirmPassword.text.length)

        }

    }
}
