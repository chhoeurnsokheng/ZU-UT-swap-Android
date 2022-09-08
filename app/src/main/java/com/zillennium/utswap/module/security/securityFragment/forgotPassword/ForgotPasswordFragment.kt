package com.zillennium.utswap.module.security.securityFragment.forgotPassword

import android.content.Context
import android.content.res.ColorStateList
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.zillennium.utswap.Datas.StoredPreferences.SessionPreferences
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.FragmentAccountForgotPasswordBinding
import com.zillennium.utswap.models.userService.User
import com.zillennium.utswap.utils.Constants
import com.zillennium.utswap.utils.validate


class ForgotPasswordFragment :
    BaseMvpFragment<ForgotPasswordView.View, ForgotPasswordView.Presenter, FragmentAccountForgotPasswordBinding>(),
    ForgotPasswordView.View {

    override var mPresenter: ForgotPasswordView.Presenter = ForgotPasswordPresenter()
    override val layoutResource: Int = R.layout.fragment_account_forgot_password
    private var bundle: Bundle? = null

    override fun initView() {
        super.initView()
        bundle = Bundle()
        onOtherActivity()
        onSetTitle()
        onSubmitForgotPassword()
    }

    private fun onOtherActivity(){
        binding.apply {
            imgClose.setOnClickListener {
                findNavController().popBackStack()
                hideKeyboard()
            }

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
                    textEmpty.visibility = View.GONE
                    etEmail.backgroundTintList = ColorStateList.valueOf(
                        ContextCompat.getColor(
                            UTSwapApp.instance, R.color.secondary_text))
                }
            })
        }
    }

    private fun onSetTitle(){
        binding.apply {
            when (arguments?.getString("title")) {
                "forgot login password" -> {
                    title.text = resources.getString(R.string.forgot_login_password)
                    bundle?.putString("title", "Forgot Login Password")
                    title.visibility = View.VISIBLE
                }
                "forgot fund password" -> {
                    title.text = resources.getString(R.string.forgot_fund_password)
                    bundle?.putString("title", "Forgot Fund Password")
                    title.visibility = View.VISIBLE
                }
                else -> {
                    title.visibility = View.GONE
                }
            }
        }
    }

    private fun onSubmitForgotPassword(){
        binding.apply {
            btnNext.setOnClickListener {

                if(etEmail.text.isNullOrEmpty()) {
                    textEmpty.text = resources.getString(R.string.field_can_t_be_empty)
                    textEmpty.visibility = View.VISIBLE
                    etEmail.backgroundTintList = ColorStateList.valueOf(
                        ContextCompat.getColor(
                            UTSwapApp.instance, R.color.danger))
                    return@setOnClickListener
                }

                if(!validate().isValidEmail(etEmail.text.toString().trim()) && !validate().isValidPhoneNumber(etEmail.text.toString().trim())){
                    textEmpty.text = resources.getString(R.string.please_input_a_valid_email_phone)
                    textEmpty.visibility = View.VISIBLE
                    etEmail.backgroundTintList =
                        ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.danger))
                    return@setOnClickListener
                }

                onProgressBar(true)
                mPresenter.forgotPassword(User.ForgotPasswordObject(etEmail.text.toString()),UTSwapApp.instance)

            }
        }
    }

    override fun onForgotLoginPasswordSuccess(data: User.ForgotPasswordRes) {
        onProgressBar(false)
        binding.apply {
            val key = data.data?.secure_key.toString()
            SessionPreferences().SESSION_SECURE_KEY_FORGOT_PASSWORD = key

            Constants.RegisterData.username = etEmail.text.toString()
            findNavController().navigate(R.id.action_to_verification_security_fragment,bundle)
        }
    }

    override fun onForgotLoginPasswordFail(data: User.ForgotPasswordRes) {
        onProgressBar(false)
        binding.apply {
            textEmpty.visibility = View.VISIBLE
            if(data.message.toString() == "VALIDATING USERNAME!")
            {
                textEmpty.text = resources.getString(R.string.please_enter_valid_email_or_phone)
            }else{
                textEmpty.text = data.message.toString()
            }
            etEmail.backgroundTintList =
                ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.danger))
        }
    }

    private fun onProgressBar(status: Boolean){
        binding.apply {
            if(status){
                pbNext.visibility = View.VISIBLE
                btnNext.isClickable = false
                btnNext.alpha = 0.6F
            }else{
                pbNext.visibility = View.GONE
                btnNext.isClickable = true
                btnNext.alpha = 1F
            }
            etEmail.backgroundTintList =
                ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.secondary_text))
            textEmpty.visibility = View.GONE
        }
    }

    private fun hideKeyboard() {
        val inputManager =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(view?.windowToken, 0)
    }
}