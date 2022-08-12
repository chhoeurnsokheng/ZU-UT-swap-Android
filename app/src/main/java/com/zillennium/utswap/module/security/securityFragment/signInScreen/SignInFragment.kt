package com.zillennium.utswap.module.security.securityFragment.signInScreen

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.text.Editable
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.zillennium.utswap.Datas.GlobalVariable.SessionVariable
import com.zillennium.utswap.Datas.StoredPreferences.SessionPreferences
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.FragmentSecuritySignInBinding
import com.zillennium.utswap.models.userService.User
import com.zillennium.utswap.module.security.securityActivity.registerScreen.RegisterActivity
import com.zillennium.utswap.module.security.securityActivity.resetPasswordScreen.ResetPasswordActivity
import com.zillennium.utswap.module.security.securityFragment.signInScreen.CheckNetworkConnection.CheckNetworkConnection
import com.zillennium.utswap.utils.validate

class SignInFragment :
    BaseMvpFragment<SignInView.View, SignInView.Presenter, FragmentSecuritySignInBinding>(),
    SignInView.View {

    private lateinit var imageWifi: ImageView
    private lateinit var textView: TextView
    private lateinit var mainWifi: LinearLayout
    private lateinit var checkNetworkConnection: CheckNetworkConnection
    override var mPresenter: SignInView.Presenter = SignInPresenter()
    override val layoutResource: Int = R.layout.fragment_security_sign_in

    override fun initView() {
        super.initView()
        //callNetworkConnection()
        //IdNeteworkConnection()
        onOtherActivity()
        onEditTextEmailPhone()
        onEditTextPassword()
        onSubmitSignIn()
    }

    private fun onOtherActivity() {
        binding.apply {
            imgBack.setOnClickListener {
                activity?.finish()
                hideKeyboard()
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
        }
    }

    private fun onEditTextPassword() {
        binding.apply {
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
        }
    }

    private fun onEditTextEmailPhone() {
        binding.apply {
            etEmail.addTextChangedListener(object : TextWatcher {
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
                    etEmail.backgroundTintList =
                        ColorStateList.valueOf(
                            ContextCompat.getColor(
                                UTSwapApp.instance,
                                R.color.secondary_text
                            )
                        )
                }
            })
        }
    }

    private fun onSubmitSignIn() {
        binding.apply {
            btnSignIn.setOnClickListener {
                val isHaveError = false
                val txtEmail = etEmail.text.toString().trim()
                val txtPassword = textInputPassword.text.toString().trim()

                if (!validate().isValidEmail(txtEmail) && !validate().isValidPhoneNumber(txtEmail)) {
                    etEmail.backgroundTintList =
                        ColorStateList.valueOf(
                            ContextCompat.getColor(
                                UTSwapApp.instance,
                                R.color.danger
                            )
                        )
                    onMessage(resources.getString(R.string.please_enter_email_or_phone_number))
                    return@setOnClickListener
                }

                if (txtPassword.length < 6) {
                    textInputPassword.backgroundTintList =
                        ColorStateList.valueOf(
                            ContextCompat.getColor(
                                UTSwapApp.instance,
                                R.color.danger
                            )
                        )
                    onMessage(resources.getString(R.string.please_enter_a_password_longer_than_8_digits))
                    return@setOnClickListener
                }

                if (!isHaveError) {
                    // show progressbar when user click sign in
                    onProgressBar(true)
                    mPresenter.login(User.LoginObject(txtEmail, txtPassword), UTSwapApp.instance)
                }
            }
        }
    }

    override fun loginSuccess(body: User.LoginRes) {
        onProgressBar(false)
        body.data?.status_kyc
        SessionVariable.SESSION_STATUS.value = true

        SessionPreferences().SESSION_STATUS = true
        SessionPreferences().SESSION_TOKEN = body.data?.TOKEN.toString()
        SessionPreferences().SESSION_ID = body.data?.ID.toString()
        SessionPreferences().SESSION_X_TOKEN_API = body.data?.x_api_key.toString()

        if (body.data?.status_kyc == true) {
            SessionPreferences().SESSION_KYC = true
            SessionVariable.SESSION_KYC.value = true
        }
        if (body.data?.status_kyc == false) {
            SessionPreferences().SESSION_KYC = false
            SessionVariable.SESSION_KYC.value = false
        }
        if (body.data?.status_submit_kyc == true) {
            SessionPreferences().SESSION_KYC_SUBMIT_STATUS = true
            SessionVariable.SESSION_KYC_STATUS.value= 1
        } else {
            SessionPreferences().SESSION_KYC_SUBMIT_STATUS = false
            SessionVariable.SESSION_KYC_STATUS.value= 0
        }
        hideKeyboard()

        activity?.finish()
    }

    override fun loginFail(body: User.LoginRes) {
        binding.apply {
            onProgressBar(false)
            etEmail.backgroundTintList =
                ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.danger))
            textInputPassword.backgroundTintList =
                ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.danger))
            onMessage(resources.getString(R.string.phone_email_or_password_is_incorrect))

            hideKeyboard()
        }
    }

    private fun onMessage(message: String) {
        binding.apply {
            txtMessage.text = message
            txtMessage.visibility = View.VISIBLE
        }
    }

    private fun onProgressBar(status: Boolean) {
        binding.apply {
            if (status) {
                pbSignIn.visibility = View.VISIBLE
                btnSignIn.isClickable = false
                btnSignIn.alpha = 0.6F
            } else {
                pbSignIn.visibility = View.GONE
                btnSignIn.isClickable = true
                btnSignIn.alpha = 1F
            }
            etEmail.backgroundTintList =
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        UTSwapApp.instance,
                        R.color.secondary_text
                    )
                )
            textInputPassword.backgroundTintList =
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        UTSwapApp.instance,
                        R.color.secondary_text
                    )
                )
            txtMessage.visibility = View.INVISIBLE
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
                showPassBtn.setImageResource(R.drawable.ic_baseline_visibility_24)
                //Show Password
                textInputPassword.transformationMethod =
                    HideReturnsTransformationMethod.getInstance()
            } else {
                showPassBtn.setImageResource(R.drawable.ic_baseline_visibility_off_24)
                //Hide Password
                textInputPassword.transformationMethod =
                    PasswordTransformationMethod.getInstance()
            }
            textInputPassword.setSelection(textInputPassword.text.length)
        }
    }

    private fun hideKeyboard() {
        val inputManager =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(view?.windowToken, 0)
    }
}


