package com.zillennium.utswap.module.security.securityFragment.signInScreen

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Patterns
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.zillennium.utswap.Datas.GlobalVariable.SessionVariable
import com.zillennium.utswap.Datas.StoredPreferences.SessionPreferences
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.FragmentSecuritySignInBinding
import com.zillennium.utswap.module.security.securityActivity.registerScreen.RegisterActivity
import com.zillennium.utswap.module.security.securityActivity.resetPasswordScreen.ResetPasswordActivity
import com.zillennium.utswap.module.security.securityFragment.signInScreen.CheckNetworkConnection.CheckNetworkConnection
import com.zillennium.utswap.utils.validate
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


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
    private var isValidate = false
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
                    SessionPreferences().removeValue("SESSION_USERNAME")
                    SessionPreferences().removeValue("SESSION_PASSWORD")
                    hideKeyboard()
                    activity?.finish()

                }

                showPassBtn.setOnClickListener {
                    ShowHidePass()
                }

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

                    if (validateEmailPhoneNumber(
                            textInputEmail.text.toString().trim()
                        ) && textInputPassword.text.toString().trim() == "12345678"
                    ) {
                        pbSignIn.visibility = View.VISIBLE
                        btnSignIn.isClickable = false
                        btnSignIn.alpha = 0.6F
                        txtMessage.visibility = View.VISIBLE
                        txtMessage.background.setTint(
                            ContextCompat.getColor(
                                UTSwapApp.instance,
                                R.color.success
                            )
                        )
                        txtMessage.text = "Successfully logged in"
                        SessionPreferences().SESSION_USERNAME =
                            textInputEmail.text.toString().trim()
                        SessionPreferences().SESSION_PASSWORD =
                            textInputPassword.text.toString().trim()
                        //findNavController().navigate(R.id.action_to_verification_security_fragment)

                        SessionPreferences().SESSION_STATUS = true
                        SessionVariable.SESSION_STATUS.value = true
                        hideKeyboard()
                        lifecycleScope.launch {
                            delay(3000)
                            activity?.finish()
                        }
                    } else {
                        txtMessage.text = "Please Enter Email or Phone Number"
                        txtMessage.visibility = View.VISIBLE
                        textInputEmail.backgroundTintList =
                            ColorStateList.valueOf(
                                ContextCompat.getColor(
                                    UTSwapApp.instance,
                                    R.color.danger
                                )
                            )
                        txtMessage.background.setTint(
                            ContextCompat.getColor(
                                UTSwapApp.instance,
                                R.color.danger
                            )
                        )
                        pbSignIn.visibility = View.GONE
                        btnSignIn.isClickable = true
                        btnSignIn.alpha = 1F
                    }


//                    var isHaveError = false

//                    txtMessage.text = "Invalid Email or Password"

                    /*if(!validate().isValidEmail(textInputEmail.text.toString().trim()) && !validate().isValidPhoneNumber(textInputEmail.text.toString().trim())){
                        txtMessage.text = "Please Enter Email or Phone Number"
                        txtMessage.visibility = View.VISIBLE
                        textInputEmail.backgroundTintList =
                            ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.danger))
                        isHaveError = true
                        return@setOnClickListener
                    }*/

//                    if (textInputPassword.text.toString().length < 8) {
//                        txtMessage.text = "Please Enter a Password Longer Than 8 Digits"
//                        txtMessage.visibility = View.VISIBLE
//                        textInputPassword.backgroundTintList =
//                            ColorStateList.valueOf(
//                                ContextCompat.getColor(
//                                    UTSwapApp.instance,
//                                    R.color.danger
//                                )
//                            )
////                        isHaveError = true
//                        return@setOnClickListener
//                    }

                    /*if (!isHaveError) {

                        pbSignIn.visibility = View.VISIBLE
                        btnSignIn.isClickable = false
                        btnSignIn.alpha = 0.6F

                        Handler().postDelayed({
                            val status: Int = 0
                            if (status == 1 || (textInputEmail.text.toString()
                                    .trim() == "utswap@gmail.com" && textInputPassword.text.toString()
                                    .trim() == "12345678")
                            ) {
                                txtMessage.visibility = View.VISIBLE
                                txtMessage.background.setTint(
                                    ContextCompat.getColor(
                                        UTSwapApp.instance,
                                        R.color.success
                                    )
                                )
                                txtMessage.text = "Successfully logged in"
                                SessionPreferences().SESSION_USERNAME =
                                    textInputEmail.text.toString().trim()
                                SessionPreferences().SESSION_PASSWORD =
                                    textInputPassword.text.toString().trim()
                                //findNavController().navigate(R.id.action_to_verification_security_fragment)

                                SessionPreferences().SESSION_STATUS = true
                                SessionVariable.SESSION_STATUS.value = true
                                hideKeyboard()
                                activity?.finish()
                            }
                            if (status == 1 || (textInputEmail.text.toString()
                                    .trim() == "0123456789" && textInputPassword.text.toString()
                                    .trim() == "12345678")
                            ) {
                                txtMessage.visibility = View.VISIBLE
                                txtMessage.background.setTint(
                                    ContextCompat.getColor(
                                        UTSwapApp.instance,
                                        R.color.success
                                    )
                                )
                                txtMessage.text = "Successfully logged in"
                                SessionPreferences().SESSION_USERNAME =
                                    textInputEmail.text.toString().trim()
                                SessionPreferences().SESSION_PASSWORD =
                                    textInputPassword.text.toString().trim()
                                //findNavController().navigate(R.id.action_to_verification_security_fragment)

                                SessionPreferences().SESSION_STATUS = true
                                SessionVariable.SESSION_STATUS.value = true
                                hideKeyboard()
                                activity?.finish()
                            } else {
                                txtMessage.visibility = View.VISIBLE
                                txtMessage.text = "Invalid email and password"
                            }

                            pbSignIn.visibility = View.GONE
                            btnSignIn.isClickable = true
                            btnSignIn.alpha = 1F

                        }, 3000)


                    }*/
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
                        txtMessage.visibility = View.INVISIBLE
                        textInputEmail.backgroundTintList =
                            ColorStateList.valueOf(
                                ContextCompat.getColor(
                                    UTSwapApp.instance,
                                    R.color.secondary_text
                                )
                            )
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
                        txtMessage.visibility = View.INVISIBLE
                        textInputPassword.backgroundTintList =
                            ColorStateList.valueOf(
                                ContextCompat.getColor(
                                    UTSwapApp.instance,
                                    R.color.secondary_text
                                )
                            )

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
//                textView.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.color_main))
//                mainWifi.setBackgroundColor(Color.GREEN)
//            } else {
//                imageWifi.setImageResource(R.drawable.ic_baseline_wifi_off_24)
//                textView.text = "Please Check your connection"
//                textView.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.color_main))
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

    private fun ShowHidePass() {
        binding.apply {
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
            textInputPassword.setSelection(textInputPassword.text.length)
        }
    }

    private fun validateEmailPhoneNumber(phoneOrEmail: String): Boolean {
        var length = phoneOrEmail.length
        if (Patterns.EMAIL_ADDRESS.matcher(phoneOrEmail).matches()) {
            if (phoneOrEmail == "utswap@gmail.com") {
                return true
            }

        } else {
            if (phoneOrEmail.startsWith("0") && length >= 9 && phoneOrEmail.matches("[0-9]*".toRegex())) {
                if (phoneOrEmail == "0123456789") {
                    return true
                }
            }
        }
        return false

    }


    private fun hideKeyboard() {
        val inputManager =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(view?.windowToken, 0)
    }
}


