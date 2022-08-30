package com.zillennium.utswap.module.security.securityFragment.newPasswordScreen

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
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.zillennium.utswap.Datas.StoredPreferences.SessionPreferences
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.FragmentSecurityNewPasswordBinding
import com.zillennium.utswap.models.userService.User
import com.zillennium.utswap.module.security.securityActivity.signInScreen.SignInActivity
import com.zillennium.utswap.utils.Constants

class NewPasswordFragment :
    BaseMvpFragment<NewPasswordView.View, NewPasswordView.Presenter, FragmentSecurityNewPasswordBinding>(),
    NewPasswordView.View {

    override var mPresenter: NewPasswordView.Presenter = NewPasswordPresenter()
    override val layoutResource: Int = R.layout.fragment_security_new_password

    override fun initView() {
        super.initView()

        onOtherActivity()
        onsetTitle()
        onSubmitNewPassword()
    }

    private fun onOtherActivity(){
        binding.apply {

            imgBack.setOnClickListener {
                findNavController().popBackStack()
                hideKeyboard()
            }

            etPassword.addTextChangedListener(object : TextWatcher {
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
                    txtPasswordMessage.visibility = View.GONE
                    etPassword.backgroundTintList =
                        ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.secondary_text))

                }
            })

            etConfirmPassword.addTextChangedListener(object : TextWatcher {
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
                    txtPasswordMessage.visibility = View.GONE
                    etConfirmPassword.backgroundTintList =
                        ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.secondary_text))
                }
            })

            showPassBtn.setOnClickListener { view ->
                ShowHidePassword(view)
            }

            showConfirmPassBtn.setOnClickListener { view ->
                ShowHideConfirmPassword(view)
            }
        }
    }

    private fun onsetTitle(){
        binding.apply {
            when (arguments?.getString("title")) {
                Constants.FundPassword.ChangeLoginPassword ->{
                    title.text = resources.getString(R.string.change_login_password)
                    title.visibility = View.VISIBLE
                }
                else -> {
                    title.visibility = View.GONE
                }
            }
        }
    }

    private fun onSubmitNewPassword(){
        binding.apply {
            btnNext.setOnClickListener {
                var isHaveError = false
                txtPasswordMessage.text = resources.getString(R.string.invalid_email_or_phone)

                if(etConfirmPassword.text.toString().trim() != etPassword.text.toString().trim()){
                    txtPasswordMessage.text = resources.getString(R.string.password_does_not_match)
                    txtPasswordMessage.visibility = View.VISIBLE
                    etPassword.backgroundTintList =
                        ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.danger))
                    etConfirmPassword.backgroundTintList =
                        ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.danger))
                    isHaveError = true
                }

                if (etConfirmPassword.text.toString().length < 8) {
                    txtPasswordMessage.text = resources.getString(R.string.please_enter_a_password_longer_than_8_digits)
                    txtPasswordMessage.visibility = View.VISIBLE
                    etConfirmPassword.backgroundTintList =
                        ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.danger))
                    isHaveError = true
                }

                if (etPassword.text.toString().length < 8) {
                    txtPasswordMessage.text = resources.getString(R.string.please_enter_a_password_longer_than_8_digits)
                    txtPasswordMessage.visibility = View.VISIBLE
                    etPassword.backgroundTintList =
                        ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.danger))
                    isHaveError = true
                }

                if (!isHaveError) {

                    onProgressBar(true)
                    mPresenter.onEnterNewPassword(User.EnterNewPasswordObject(SessionPreferences().SESSION_SECURE_KEY_FORGOT_PASSWORD,etPassword.text.toString(),etConfirmPassword.text.toString()),UTSwapApp.instance)

                }
            }
        }
    }

    override fun onChangePasswordSuccess(body: User.EnterNewPasswordRes) {
        onProgressBar(false)
        activity?.finish()
        val intent = Intent(UTSwapApp.instance, SignInActivity::class.java)
        startActivity(intent)
    }

    override fun onChangePasswordFail(body: User.EnterNewPasswordRes) {
        onProgressBar(false)
        binding.apply {
            txtPasswordMessage.visibility = View.VISIBLE
            if(body.message.toString() == "SOMETHING WENT WRONG!")
            {
                txtPasswordMessage.text = resources.getString(R.string.new_password_cannot_be_the_same_as_old)
            }else{
                txtPasswordMessage.text = body.message.toString()
            }

            etConfirmPassword.backgroundTintList =
                ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.danger))
            etPassword.backgroundTintList =
                ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.danger))
        }
    }

    private fun onProgressBar(status: Boolean){
        binding.apply {
            if(status){
                pbNext.visibility = View.VISIBLE
                btnNext.isClickable = false
                btnNext.alpha = 0.6F

                etConfirmPassword.backgroundTintList =
                    ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.secondary_text))
                etPassword.backgroundTintList =
                    ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.secondary_text))
                txtPasswordMessage.visibility = View.INVISIBLE
            }else{
                pbNext.visibility = View.GONE
                btnNext.isClickable = true
                btnNext.alpha = 1F

                etConfirmPassword.backgroundTintList =
                    ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.secondary_text))
                etPassword.backgroundTintList =
                    ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.secondary_text))
                txtPasswordMessage.visibility = View.INVISIBLE
            }
        }
    }

    //Show Hide Password
    fun ShowHidePassword(view: View) {
        binding.apply {
            if (view.id == R.id.show_pass_btn) {
                if (etPassword.transformationMethod.equals(PasswordTransformationMethod.getInstance())) {
                    showPassBtn.setImageResource(R.drawable.ic_baseline_visibility_24)
                    //Show Password
                    etPassword.transformationMethod =
                        HideReturnsTransformationMethod.getInstance()
                } else {
                    showPassBtn.setImageResource(R.drawable.ic_baseline_visibility_off_24)
                    //Hide Password
                    etPassword.transformationMethod = PasswordTransformationMethod.getInstance()
                }
                etPassword.requestFocus()
                etPassword.setSelection(etPassword.text.length)
            }
        }
    }

    //Show Hide Confirm Password
    fun ShowHideConfirmPassword(view: View) {
        binding.apply {
            if (view.id == R.id.show_confirm_pass_btn) {
                if (etConfirmPassword.transformationMethod.equals(PasswordTransformationMethod.getInstance())
                ) {
                    showConfirmPassBtn.setImageResource(R.drawable.ic_baseline_visibility_24)
                    //Show Password
                    etConfirmPassword.transformationMethod =
                        HideReturnsTransformationMethod.getInstance()
                } else {
                    showConfirmPassBtn.setImageResource(R.drawable.ic_baseline_visibility_off_24)
                    //Hide Password
                    etConfirmPassword.transformationMethod =
                        PasswordTransformationMethod.getInstance()
                }
                etConfirmPassword.requestFocus()
                etConfirmPassword.setSelection(etConfirmPassword.text.length)
            }

        }

    }

    private fun hideKeyboard() {
        val inputManager =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(view?.windowToken, 0)
    }
}