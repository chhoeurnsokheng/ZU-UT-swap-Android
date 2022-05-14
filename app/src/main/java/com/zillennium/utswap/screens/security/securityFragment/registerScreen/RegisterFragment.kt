package com.zillennium.utswap.screens.security.securityFragment.registerScreen

import android.content.Intent
import android.content.res.ColorStateList
import android.text.Editable
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.zillennium.utswap.Datas.StoredPreferences.KYCPreferences
import com.zillennium.utswap.Datas.StoredPreferences.SessionPreferences
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.FragmentSecurityRegisterBinding

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



                btnSignup.setOnClickListener {
                    var isHaveError = false
                    txtMessage.text = "Invalid Email or Password"

                    if (inputEmail.text.toString().isEmpty()) {
                        txtMessage.visibility = View.VISIBLE
                        inputEmail.backgroundTintList =
                            ColorStateList.valueOf(resources.getColor(R.color.red))
                        isHaveError = true
                    }

                    if (inputPassword.text.toString().isEmpty()) {
                        txtMessage.visibility = View.VISIBLE
                        inputPassword.backgroundTintList =
                            ColorStateList.valueOf(resources.getColor(R.color.red))
                        isHaveError = true
                    }
                    if (inputConfirmPassword.text.toString().isEmpty()) {
                        txtMessage.visibility = View.VISIBLE
                        inputConfirmPassword.backgroundTintList =
                            ColorStateList.valueOf(resources.getColor(R.color.red))
                        isHaveError = true
                    }

                    if (isHaveError) {
                        return@setOnClickListener
                    } else {
                        if(inputPassword.text.toString() != inputConfirmPassword.text.toString()){
                            txtMessage.text = "Password didn't match"
                            txtMessage.visibility = View.VISIBLE
                        }else{
                            txtMessage.visibility = View.GONE
                            KYCPreferences().FIRST_NAME = inputPassword.text.toString()

                            val title = ""
                            val bundle = bundleOf("register" to title)
                            findNavController().navigate(R.id.action_to_verification_security_fragment, bundle)
                        }
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
                            ColorStateList.valueOf(resources.getColor(R.color.secondary_text))
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
                            ColorStateList.valueOf(resources.getColor(R.color.secondary_text))

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
                            ColorStateList.valueOf(resources.getColor(R.color.secondary_text))
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

        }

    }
}
