package com.zillennium.utswap.screens.security.signInScreen

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.color.ColorRoles
import com.zillennium.utswap.Datas.StoredPreferences.KYCPreferences
import com.zillennium.utswap.Datas.StoredPreferences.SessionPreferences
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.BaseStoredPreferences
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivitySecuritySignInBinding
import com.zillennium.utswap.screens.navbar.navbar.NavbarActivity
import com.zillennium.utswap.screens.security.resetPassword.ResetPasswordActivity
import com.zillennium.utswap.screens.security.signInScreen.CheckNetworkConnection.CheckNetworkConnection
import com.zillennium.utswap.screens.security.signUpScreen.SignUpActivity
import com.zillennium.utswap.screens.security.verificationScreen.VerificationActivity
import java.util.prefs.Preferences
import kotlin.reflect.KProperty


class SignInActivity :
    BaseMvpActivity<SignInView.View, SignInView.Presenter, ActivitySecuritySignInBinding>(),
    SignInView.View {

    object info {
        var email_signIn = ""
    }

    private lateinit var imageWifi: ImageView
    private lateinit var textView: TextView
    private lateinit var mainWifi: LinearLayout
    private lateinit var checkNetworkConnection: CheckNetworkConnection
    override var mPresenter: SignInView.Presenter = SignInPresenter()
    override val layoutResource: Int = R.layout.activity_security_sign_in

    var doubleBackToExitPressedOnce = false

    override fun initView() {
        super.initView()
        try {
//            callNetworkConnection()
//            IdNeteworkConnection()
            binding.apply {
                imgBack.setOnClickListener { finish() }

                btnForgot.setOnClickListener {
                    val intent = Intent(UTSwapApp.instance, ResetPasswordActivity::class.java)
                    startActivity(intent)
                }
                btnRegister.setOnClickListener {
                    val intent = Intent(UTSwapApp.instance, SignUpActivity::class.java)
                    startActivity(intent)
                }
                btnSignIn.setOnClickListener {

                    var isHaveError = false
                    txtMessage.text = "Invalid Email or Password"
                    if (textInputEmail.text.toString().isEmpty()) {
                        txtMessage.visibility = View.VISIBLE
                        textInputEmail.backgroundTintList =
                            ColorStateList.valueOf(resources.getColor(R.color.red))
                        isHaveError = true
                    }
                    if (textInputPassword.text.toString().isEmpty()) {
                        txtMessage.visibility = View.VISIBLE
                        textInputPassword.backgroundTintList =
                            ColorStateList.valueOf(resources.getColor(R.color.red))
                        isHaveError = true
                    } else {
                        SessionPreferences().SESSION_USERNAME = textInputEmail.text.toString()
                        txtMessage.visibility = View.GONE
<<<<<<< HEAD
                        onBackPressed()
//                        val intent = Intent(UTSwapApp.instance, SignUpActivity::class.java)
//                        startActivity(intent)
                    }
                }
            textInputEmail.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    charSequence: CharSequence,
                    i: Int,
                    i1: Int,
                    i2: Int
                ) {
                }
=======
                        val intent = Intent(UTSwapApp.instance, SignUpActivity::class.java)
                        startActivity(intent)
                    }
                }
                textInputEmail.addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(
                        charSequence: CharSequence,
                        i: Int,
                        i1: Int,
                        i2: Int
                    ) {
                    }
>>>>>>> 7adb8ff77b8393266910f3dd9a841578947cf7cd

                    override fun onTextChanged(
                        charSequence: CharSequence,
                        i: Int,
                        i1: Int,
                        i2: Int
                    ) {
                    }

                    override fun afterTextChanged(editable: Editable) {
                        txtMessage.visibility = View.GONE
                        textInputEmail.backgroundTintList =
                            ColorStateList.valueOf(resources.getColor(R.color.secondary_text))
                    }
                })

                textInputPassword.addTextChangedListener(object : TextWatcher {
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
                        textInputPassword.backgroundTintList =
                            ColorStateList.valueOf(resources.getColor(R.color.secondary_text))

                    }
                })

//        Glide.with(imgCapCha.getContext())
//                .load("https://utswap.io/Verify/code")
//                .into(imgCapCha);
//
//        imgCapCha.setOnClickListener(view -> {
//            Glide.with(imgCapCha.getContext())
//                    .load("https://utswap.io/Verify/code")
//                    .into(imgCapCha);
//        });

            }
            // Code
        } catch (error: Exception) {
            // Must be safe
        }
    }
//        @SuppressLint("ServiceCast")
//        private fun checkWifiOnAndConnected(): Boolean {
//        val wifiMgr = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
//        return if (wifiMgr.isWifiEnabled) { // Wi-Fi adapter is ON
//            val wifiInfo = wifiMgr.connectionInfo
//            return wifiInfo.networkId != -1
//            // Connected to an access point
//        } else {
//            return false // Wi-Fi adapter is OFF
//        }
//    }


//private fun IdNeteworkConnection() {
//    imageWifi = findViewById(R.id.imageWifi)
//    textView = findViewById(R.id.textView)
//    mainWifi = findViewById(R.id.main_Wifi)
//}
//
//    @SuppressLint("ResourceType")
//    private fun callNetworkConnection() {
//        checkNetworkConnection = CheckNetworkConnection(application)
//        checkNetworkConnection.observe(this) { isConnected ->
//            if (isConnected) {
//                imageWifi.setImageResource(R.drawable.ic_baseline_wifi_24)
//                textView.text = "Connected"
//                textView.setTextColor(Color.parseColor("#ffffff"))
//                mainWifi.setBackgroundColor(Color.GREEN)
//            } else {
//                imageWifi.setImageResource(R.drawable.ic_baseline_wifi_off_24)
//                textView.text = "Please Check your connection"
//                textView.setTextColor(Color.parseColor("#ffffff"))
//                mainWifi.setBackgroundColor(Color.RED)
//            }
//        }
//    }

<<<<<<< HEAD
//override fun onBackPressed() {
//    if (doubleBackToExitPressedOnce) {
//        super.onBackPressed()
//        return
//    }
//    this.doubleBackToExitPressedOnce = true
//    Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show()
//    Handler(Looper.getMainLooper()).postDelayed(
//        { doubleBackToExitPressedOnce = false },
//        2000
//    )
//}

fun ShowHidePass(view: View) {
    binding.apply {
        if (view.id == R.id.show_pass_btn) {
            if (textInputPassword.transformationMethod
                    .equals(PasswordTransformationMethod.getInstance())
            ) {
                showPassBtn.setImageResource(R.drawable.ic_baseline_visibility_off_24)
                //Show Password
                textInputPassword.transformationMethod =
                    HideReturnsTransformationMethod.getInstance()
            } else {
                showPassBtn.setImageResource(R.drawable.ic_baseline_visibility_24)
                //Hide Password
                textInputPassword.transformationMethod = PasswordTransformationMethod.getInstance()
            }
        }
    }
}
}
=======
    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }
        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show()
        Handler(Looper.getMainLooper()).postDelayed(
            { doubleBackToExitPressedOnce = false },
            2000
        )
    }

    fun ShowHidePass(view: View) {
        binding.apply {
            if (view.id == R.id.show_pass_btn) {
                if (textInputPassword.transformationMethod
                        .equals(PasswordTransformationMethod.getInstance())
                ) {
                    showPassBtn.setImageResource(R.drawable.ic_baseline_visibility_off_24)
                    //Show Password
                    textInputPassword.transformationMethod =
                        HideReturnsTransformationMethod.getInstance()
                } else {
                    showPassBtn.setImageResource(R.drawable.ic_baseline_visibility_24)
                    //Hide Password
                    textInputPassword.transformationMethod =
                        PasswordTransformationMethod.getInstance()
                }
            }
        }
    }
}
>>>>>>> 7adb8ff77b8393266910f3dd9a841578947cf7cd
