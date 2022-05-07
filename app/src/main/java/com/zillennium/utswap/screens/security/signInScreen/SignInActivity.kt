package com.zillennium.utswap.screens.security.signInScreen

import android.content.Context
import android.content.Intent
import android.net.wifi.WifiManager
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivitySecuritySignInBinding
import com.zillennium.utswap.screens.navbar.navbar.NavbarActivity
import com.zillennium.utswap.screens.security.resetPassword.ResetPasswordActivity
import com.zillennium.utswap.screens.security.signUpScreen.SignUpActivity


class SignInActivity :
    BaseMvpActivity<SignInView.View, SignInView.Presenter, ActivitySecuritySignInBinding>(),
    SignInView.View {
    lateinit var textInputEmail: TextInputLayout
    lateinit var textInputPassword: TextInputLayout
    lateinit var button: Button

    override var mPresenter: SignInView.Presenter = SignInPresenter()
    override val layoutResource: Int = R.layout.activity_security_sign_in

    var doubleBackToExitPressedOnce = false

    override fun initView() {
        super.initView()
        try {

            ShowEmptyMessage()

            binding.apply {

                if(!checkWifiOnAndConnected()){

                }

                imgBack.setOnClickListener { finish() }

                btnSignIn.setOnClickListener {
                    val intent = Intent(UTSwapApp.instance, NavbarActivity::class.java)
                    startActivity(intent)
                }

                btnForgot.setOnClickListener {
                    val intent = Intent(UTSwapApp.instance, ResetPasswordActivity::class.java)
                    startActivity(intent)
                }

                btnRegister.setOnClickListener {
                    val intent = Intent(UTSwapApp.instance, SignUpActivity::class.java)
                    startActivity(intent)
                }

            }
            // Code
        } catch (error: Exception) {
            // Must be safe
        }
    }


    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }
        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show()
        Handler(Looper.getMainLooper()).postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
    }

    private fun getColoredSpanned(text: String, color: String): String? {
        return "<font color=$color>$text</font>"
    }

    // Floating label show error message in sign in
    private fun ShowEmptyMessage() {
        textInputEmail = findViewById(R.id.textInputEmail)
        textInputPassword = findViewById(R.id.textInputPassword)
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

    // Floating label show error message in password

    fun validateInputPassword(): Boolean {
        val passwordInput = textInputPassword.editText!!.text.toString().trim { it <= ' ' }
        return if (passwordInput.isEmpty()) {
            textInputPassword.error = "Empty field not allowed!"
            false
        }
        else {
            textInputPassword.error = null
            true
        }
    }

    fun confirmInputSignin(view: View?) {
        if (!validateInputEmail() or !validateInputPassword()) {
            return
        }
        val intent = Intent(this, NavbarActivity::class.java)
        startActivity(intent)
        Toast.makeText(this, "Success Complete", Toast.LENGTH_SHORT).show()
    }

    private fun checkWifiOnAndConnected(): Boolean {
        val wifiMgr = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        return if (wifiMgr.isWifiEnabled) { // Wi-Fi adapter is ON
            val wifiInfo = wifiMgr.connectionInfo
            return wifiInfo.networkId != -1
            // Connected to an access point
        } else {
            return false // Wi-Fi adapter is OFF
        }
    }

}
