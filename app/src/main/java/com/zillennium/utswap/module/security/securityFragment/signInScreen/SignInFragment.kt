package com.zillennium.utswap.module.security.securityFragment.signInScreen

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Handler
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
import androidx.navigation.fragment.findNavController
import com.zillennium.utswap.Datas.GlobalVariable.SessionVariable
import com.zillennium.utswap.Datas.StoredPreferences.SessionPreferences
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.FragmentSecuritySignInBinding
import com.zillennium.utswap.models.user.VerifiyCode
import com.zillennium.utswap.module.security.securityActivity.registerScreen.RegisterActivity
import com.zillennium.utswap.module.security.securityActivity.resetPasswordScreen.ResetPasswordActivity
import com.zillennium.utswap.module.security.securityFragment.signInScreen.CheckNetworkConnection.CheckNetworkConnection
import com.zillennium.utswap.utils.validate


class SignInFragment : BaseMvpFragment<SignInView.View, SignInView.Presenter, FragmentSecuritySignInBinding>(),
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
        //super.initView()
        try {
//            callNetworkConnection()
//            IdNeteworkConnection()
            binding.apply {
                imgBack.setOnClickListener {
                    SessionPreferences().removeValue("SESSION_USERNAME")
                    SessionPreferences().removeValue("SESSION_PASSWORD")
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
                    var isHaveError = false

                    txtMessage.text = "Invalid Email or Password"

                    if(!validate().isValidEmail(textInputEmail.text.toString().trim()) && !validate().isValidPhoneNumber(textInputEmail.text.toString().trim())){
                        txtMessage.text = "Please Enter Email or Phone Number"
                        txtMessage.visibility = View.VISIBLE
                        textInputEmail.backgroundTintList =
                            ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.danger))
                        isHaveError = true
                        return@setOnClickListener
                    }

                    if(textInputPassword.text.toString().length < 8){
                        txtMessage.text = "Please Enter a Password Longer Than 8 Digits"
                        txtMessage.visibility = View.VISIBLE
                        textInputPassword.backgroundTintList =
                            ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.danger))
                        isHaveError = true
                        return@setOnClickListener
                    }

                    if (!isHaveError){

                        pbSignIn.visibility = View.VISIBLE
                        btnSignIn.isClickable = false
                        btnSignIn.alpha = 0.6F

                        Handler().postDelayed({
                            val status: Int = 0
                            if(status == 1 || (textInputEmail.text.toString().trim() ==  "utswap@gmail.com" && textInputPassword.text.toString().trim() == "12345678")){
                                txtMessage.visibility = View.VISIBLE
                                txtMessage.background.setTint(ContextCompat.getColor(UTSwapApp.instance, R.color.success))
                                txtMessage.text = "Successfully logged in"
                                SessionPreferences().SESSION_USERNAME = textInputEmail.text.toString().trim()
                                SessionPreferences().SESSION_PASSWORD = textInputPassword.text.toString().trim()
                                //findNavController().navigate(R.id.action_to_verification_security_fragment)

                                SessionPreferences().SESSION_STATUS = true
                                SessionVariable.SESSION_STATUS.value = true
                                hideKeyboard()
                                activity?.finish()
                            }
                            if(status == 1 || (textInputEmail.text.toString().trim() ==  "0123456789" && textInputPassword.text.toString().trim() == "12345678")){
                            txtMessage.visibility = View.VISIBLE
                            txtMessage.background.setTint(ContextCompat.getColor(UTSwapApp.instance, R.color.success))
                            txtMessage.text = "Successfully logged in"
                            SessionPreferences().SESSION_USERNAME = textInputEmail.text.toString().trim()
                            SessionPreferences().SESSION_PASSWORD = textInputPassword.text.toString().trim()
                            //findNavController().navigate(R.id.action_to_verification_security_fragment)

                            SessionPreferences().SESSION_STATUS = true
                            SessionVariable.SESSION_STATUS.value = true
                            hideKeyboard()
                            activity?.finish()
                        } else{
                                txtMessage.visibility = View.VISIBLE
                                txtMessage.text = "Invalid email and password"
                            }

                            pbSignIn.visibility = View.GONE
                            btnSignIn.isClickable = true
                            btnSignIn.alpha = 1F

                        }, 3000)



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
                        txtMessage.visibility = View.INVISIBLE
                        textInputEmail.backgroundTintList =
                            ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.secondary_text))
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
                            ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.secondary_text))

                    }
                })


            }
            // Code
        } catch (error: Exception) {
            // Must be safe
        }
    }

    override fun onGetVerifySuccess(data: VerifiyCode) {}

    override fun onGetVerifyFail() { }


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


