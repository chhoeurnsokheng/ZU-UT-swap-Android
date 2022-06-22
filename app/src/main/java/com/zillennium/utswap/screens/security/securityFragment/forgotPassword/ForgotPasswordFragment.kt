package com.zillennium.utswap.screens.security.securityFragment.forgotPassword

import android.content.res.ColorStateList
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.FragmentAccountForgotPasswordBinding
import com.zillennium.utswap.utils.validate


class ForgotPasswordFragment :
    BaseMvpFragment<ForgotPasswordView.View, ForgotPasswordView.Presenter, FragmentAccountForgotPasswordBinding>(),
    ForgotPasswordView.View {

    override var mPresenter: ForgotPasswordView.Presenter = ForgotPasswordPresenter()
    override val layoutResource: Int = R.layout.fragment_account_forgot_password

    override fun initView() {
        super.initView()
        try {
            binding.apply {
                imgClose.setOnClickListener {
                    findNavController().popBackStack()
                }

                val bundle = Bundle()

                when (arguments?.getString("title")) {
                    "forgot login password" -> {
                        title.text = "Forgot Login Password"
                        bundle.putString("title", "Forgot Login Password")
                        title.visibility = View.VISIBLE
                    }
                    "forgot fund password" -> {
                        title.text = "Forgot Fund Password"
                        bundle.putString("title", "Forgot Fund Password")
                        title.visibility = View.VISIBLE
                    }
                    else -> {
                        title.visibility = View.GONE
                    }
                }

                btnNext.setOnClickListener {
                    var isHaveError = false

                    if(inputEmail.text.isNullOrEmpty()) {
                        textEmpty.text = "Field can't be empty"
                        textEmpty.visibility = View.VISIBLE
                        inputEmail.backgroundTintList = ColorStateList.valueOf(
                            ContextCompat.getColor(
                                UTSwapApp.instance, R.color.main_red))
                        isHaveError = true
                        return@setOnClickListener
                    }

                    if(!validate().isValidEmail(inputEmail.text.toString().trim()) && !validate().isValidPhoneNumber(inputEmail.text.toString().trim())){
                        textEmpty.text = "Please input a valid email/phone number."
                        textEmpty.visibility = View.VISIBLE
                        inputEmail.backgroundTintList =
                            ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.main_red))
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
                            findNavController().navigate(R.id.action_to_verification_security_fragment,bundle)
                        }else{
                            textEmpty.visibility = View.VISIBLE
                            textEmpty.text = "Invalid email or phone number"
                            inputEmail.backgroundTintList = ColorStateList.valueOf(
                                ContextCompat.getColor(
                                    UTSwapApp.instance, R.color.main_red))
                        }

                        btnNext.isClickable = true
                        btnNext.alpha = 1F
                        pbNext.visibility = View.GONE
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
                        inputEmail.backgroundTintList = ColorStateList.valueOf(
                            ContextCompat.getColor(
                                UTSwapApp.instance, R.color.secondary_text))
                    }
                })
            }
            // Code
        } catch (error: Exception) {
            // Must be safe
        }
    }
}