package com.zillennium.utswap.screens.security.resetPassword

import android.content.Intent
import android.view.View
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout
import com.zillennium.utswap.databinding.ActivitySecurityResetPasswordBinding

import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.screens.navbar.navbar.NavbarActivity
import com.zillennium.utswap.screens.security.verificationScreen.VerificationActivity
import java.io.IOException

class ResetPasswordActivity :
    BaseMvpActivity<ResetPasswordView.View, ResetPasswordView.Presenter, ActivitySecurityResetPasswordBinding>(),
    ResetPasswordView.View {
    lateinit var textInputEmail:TextInputLayout
    override var mPresenter: ResetPasswordView.Presenter = ResetPasswordPresenter()
    override val layoutResource: Int = R.layout.activity_security_reset_password

    override fun initView() {
        super.initView()

        try {

            ResetPasswordShowEmptyMessage()

            binding.apply {
                imgBack.setOnClickListener { finish() }

//                btnNext.setOnClickListener {
//                    val intent = Intent(UTSwapApp.instance, VerificationActivity::class.java)
//                    startActivity(intent)
//                }

            }
            // Code
        } catch (error: Exception) {
            // Must be safe
        }
    }
    private fun ResetPasswordShowEmptyMessage(){
        textInputEmail = findViewById(R.id.textInputEmailPhone)
    }
    // Floating label show error message in email or phone
    fun validateInputEmail(): Boolean {
        val emailInput = textInputEmail.editText!!.text.toString().trim { it <= ' ' }
        return if (emailInput.isEmpty()) {
            textInputEmail.error = "Empty field not allowed!"
            false
        } else {
            textInputEmail.error = null
            true
        }
    }

    //Button Next
    fun confirmInputEmail(view: View?) {
        if (!validateInputEmail()) {
            return
        }
        val intent = Intent(this, VerificationActivity::class.java)
        startActivity(intent)
        Toast.makeText(this, "Success Complete", Toast.LENGTH_SHORT).show()
    }
}
