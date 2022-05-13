package com.zillennium.utswap.screens.security.securityFragment.signInScreen

import android.content.Intent
import android.content.res.ColorStateList
import android.text.Editable
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.zillennium.utswap.Datas.StoredPreferences.SessionPreferences
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.FragmentSecuritySignInBinding
import com.zillennium.utswap.screens.security.securityActivity.registerScreen.RegisterActivity
import com.zillennium.utswap.screens.security.securityActivity.resetPasswordScreen.ResetPasswordActivity
import com.zillennium.utswap.screens.security.securityFragment.resetPassword.ResetPasswordFragment
import com.zillennium.utswap.screens.security.securityFragment.signInScreen.CheckNetworkConnection.CheckNetworkConnection


class SignInFragment :
    BaseMvpFragment<SignInView.View, SignInView.Presenter, FragmentSecuritySignInBinding>(),
    SignInView.View {

    object info {
        var email_signIn = ""
    }

    private lateinit var imageWifi: ImageView
    private lateinit var textView: TextView
    private lateinit var mainWifi: LinearLayout
    private lateinit var checkNetworkConnection: CheckNetworkConnection
    override var mPresenter: SignInView.Presenter = SignInPresenter()
    override val layoutResource: Int = R.layout.fragment_security_sign_in

    var doubleBackToExitPressedOnce = false

    override fun initView() {
        super.initView()
        try {
//            callNetworkConnection()
//            IdNeteworkConnection()
            binding.apply {
                imgBack.setOnClickListener {
                    activity?.finish()
                }


//                   if (!checkWifiOnAndConnected()) {
//
//                    }

                btnForgot.setOnClickListener {
                    activity?.finish()
                    val intent = Intent(UTSwapApp.instance, ResetPasswordActivity::class.java)
                    startActivity(intent)
                }
                btnRegister.setOnClickListener {
                    activity?.finish()
                    val intent = Intent(UTSwapApp.instance, RegisterActivity::class.java)
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
                        txtMessage.visibility = View.GONE
                        SessionPreferences().SESSION_USERNAME = textInputEmail.text.toString()
//                        findNavController().popBackStack()
                        findNavController().navigate(R.id.action_to_verification_security_fragment)
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

//    override fun onBackPressed() {
//        if (doubleBackToExitPressedOnce) {
//            super.onBackPressed()
//            return
//        }
//        this.doubleBackToExitPressedOnce = true
//        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show()
//        Handler(Looper.getMainLooper()).postDelayed(
//            { doubleBackToExitPressedOnce = false },
//            2000
//        )
//    }

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