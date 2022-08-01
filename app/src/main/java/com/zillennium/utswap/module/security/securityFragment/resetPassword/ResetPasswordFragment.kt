package com.zillennium.utswap.module.security.securityFragment.resetPassword

import android.content.Context
import android.content.res.ColorStateList
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.zillennium.utswap.Datas.StoredPreferences.SessionPreferences
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.R
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.FragmentSecurityResetPasswordBinding
import com.zillennium.utswap.models.userService.User
import com.zillennium.utswap.utils.Constants
import com.zillennium.utswap.utils.validate


class ResetPasswordFragment :
    BaseMvpFragment<ResetPasswordView.View, ResetPasswordView.Presenter, FragmentSecurityResetPasswordBinding>(),
    ResetPasswordView.View {

    override var mPresenter: ResetPasswordView.Presenter = ResetPasswordPresenter()
    override val layoutResource: Int = R.layout.fragment_security_reset_password

    override fun initView() {
        super.initView()

        onOtherActivity()
        onSubmitResetPassword()
    }

    private fun onOtherActivity(){
        binding.apply {
            imgBack.setOnClickListener {
                activity?.finish()
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
                    etEmail.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.secondary_text))
                }
            })
        }
    }

    private fun onSubmitResetPassword(){
        binding.apply {
            btnNext.setOnClickListener {

                if(etEmail.text.isNullOrEmpty()) {
                    textEmpty.text = resources.getString(R.string.field_can_t_be_empty)
                    textEmpty.visibility = View.VISIBLE
                    etEmail.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.danger))
                    return@setOnClickListener
                }

                if(!validate().isValidEmail(etEmail.text.toString().trim()) && !validate().isValidPhoneNumber(etEmail.text.toString().trim())){
                    textEmpty.text = resources.getString(R.string.please_enter_email_or_phone_number)
                    textEmpty.visibility = View.VISIBLE
                    etEmail.backgroundTintList =
                        ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.danger))
                    return@setOnClickListener
                }

                onProgressBar(true)
                mPresenter.onResetPassword(User.ForgotPasswordObject(etEmail.text.toString()),UTSwapApp.instance)

            }
        }
    }

    override fun onResetSuccess(body: User.ForgotPasswordRes) {
        binding.apply {
            onProgressBar(false)
            val key = body.data?.secure_key.toString()
            SessionPreferences().SESSION_SECURE_KEY_FORGOT_PASSWORD = key

            Constants.RegisterData.username = etEmail.text.toString()

            findNavController().navigate(R.id.action_to_verification_security_fragment)
        }
    }

    override fun onResetFail(body: User.ForgotPasswordRes) {
        onProgressBar(false)
        binding.apply {
            textEmpty.visibility = View.VISIBLE
            textEmpty.text = body.message.toString()
        }

    }

    private fun onProgressBar(status: Boolean){
        binding.apply {
            if(status){
                pbNext.visibility = View.VISIBLE
                btnNext.isClickable = false
                btnNext.alpha = 0.6F
                textEmpty.visibility = View.GONE
            }else{
                pbNext.visibility = View.GONE
                btnNext.isClickable = true
                btnNext.alpha = 1F
                textEmpty.visibility = View.GONE
            }
        }
    }

    private fun hideKeyboard() {
        val inputManager =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(view?.windowToken, 0)
    }
}
