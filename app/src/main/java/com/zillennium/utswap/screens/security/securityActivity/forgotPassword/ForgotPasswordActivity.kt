package com.zillennium.utswap.screens.security.securityActivity.forgotPassword

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.core.content.ContextCompat
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivityForgotPasswordBinding
import com.zillennium.utswap.screens.account.verificationAccunt.VerificationAccountActivity
import com.zillennium.utswap.utils.validate

class ForgotPasswordActivity :
    BaseMvpActivity<ForgotPasswordView.View, ForgotPasswordView.Presenter, ActivityForgotPasswordBinding>(),
    ForgotPasswordView.View {

    override var mPresenter: ForgotPasswordView.Presenter = ForgotPasswordPresenter()
    override val layoutResource: Int = R.layout.activity_forgot_password

    override fun initView() {
        super.initView()
        try {
            binding.apply {
                imgClose.setOnClickListener {
                    finish()
                }

                val intent = intent
                val text = intent.getStringExtra(Intent.EXTRA_TEXT)

                title.text = text.toString()


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

                    btnNext.isClickable = false
                    btnNext.alpha = 0.6F

                    Handler().postDelayed({

                        val status: Int = 0
                        if(status == 1 || inputEmail.text.toString().trim() == "utswap@gmail.com"){
                            textEmpty.visibility = View.INVISIBLE
                            val intent = Intent(UTSwapApp.instance, VerificationAccountActivity::class.java)
                            intent.putExtra(Intent.EXTRA_TEXT, text.toString());
                            startActivity(intent)
                        }else{
                            textEmpty.visibility = View.VISIBLE
                            textEmpty.text = "Invalid email or phone number"
                            inputEmail.backgroundTintList = ColorStateList.valueOf(
                                ContextCompat.getColor(
                                    UTSwapApp.instance, R.color.main_red))
                        }

                        btnNext.isClickable = true
                        btnNext.alpha = 1F
                    }, 500)

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
        } catch (error: Exception) {
            // Must be safe
        }
    }

}