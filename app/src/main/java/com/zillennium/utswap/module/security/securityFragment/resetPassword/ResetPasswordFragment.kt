package com.zillennium.utswap.module.security.securityFragment.resetPassword

import android.content.res.ColorStateList
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.zillennium.utswap.Datas.StoredPreferences.SessionPreferences

import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.R
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.FragmentSecurityResetPasswordBinding
import com.zillennium.utswap.utils.validate


class ResetPasswordFragment :
    BaseMvpFragment<ResetPasswordView.View, ResetPasswordView.Presenter, FragmentSecurityResetPasswordBinding>(),
    ResetPasswordView.View {

    override var mPresenter: ResetPasswordView.Presenter = ResetPasswordPresenter()
    override val layoutResource: Int = R.layout.fragment_security_reset_password

    override fun initView() {
        super.initView()

        try {
            binding.apply {
                imgBack.setOnClickListener {
                    activity?.finish()
                }
                btnNext.setOnClickListener {
                    var isHaveError = false

                    if(inputEmail.text.isNullOrEmpty()) {
                        textEmpty.text = "Field can't be empty"
                        textEmpty.visibility = View.VISIBLE
                        inputEmail.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.danger))
                        isHaveError = true
                        return@setOnClickListener
                    }

                    if(!validate().isValidEmail(inputEmail.text.toString().trim()) && !validate().isValidPhoneNumber(inputEmail.text.toString().trim())){
                        textEmpty.text = "Please input a valid email/phone number."
                        textEmpty.visibility = View.VISIBLE
                        inputEmail.backgroundTintList =
                            ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.danger))
                        isHaveError = true
                        return@setOnClickListener
                    }

                    pbNext.visibility = View.VISIBLE
                    btnNext.isClickable = false
                    btnNext.alpha = 0.6F

                    Handler().postDelayed({

                        val status: Int = 0
                        if(status == 1 || inputEmail.text.toString().trim() == "utswap@gmail.com"){
                            textEmpty.visibility = View.INVISIBLE
                            SessionPreferences().SESSION_USERNAME = inputEmail.text.toString().trim()
                            findNavController().navigate(R.id.action_to_verification_security_fragment)
                        }else{
                            textEmpty.visibility = View.VISIBLE
                            textEmpty.text = "Invalid email or phone number"
                            inputEmail.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.danger))
                        }

                        pbNext.visibility = View.GONE
                        btnNext.isClickable = true
                        btnNext.alpha = 1F
                    }, 3000)

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
                        inputEmail.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.secondary_text))
                    }
                })
            }
            // Code
        } catch (error: Exception) {
            // Must be safe
        }
    }
}
