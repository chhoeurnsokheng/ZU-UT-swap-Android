package com.zillennium.utswap.module.security.securityFragment.changeLoginPassword

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.text.Editable
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.FragmentAccountChangeLoginPasswordBinding
import com.zillennium.utswap.models.userService.User
import com.zillennium.utswap.screens.navbar.navbar.MainActivity
import com.zillennium.utswap.utils.ClientClearData


class ChangeLoginPasswordFragment :
    BaseMvpFragment<ChangeLoginPasswordView.View, ChangeLoginPasswordView.Presenter, FragmentAccountChangeLoginPasswordBinding>(),
    ChangeLoginPasswordView.View {

    override var mPresenter: ChangeLoginPasswordView.Presenter = ChangeLoginPasswordPresenter()
    override val layoutResource: Int = R.layout.fragment_account_change_login_password

    override fun initView() {
        super.initView()
        onOtherActivity()
        onChangePassword()
    }

    private fun onOtherActivity(){
        binding.apply {
            imgClose.setOnClickListener {
                activity?.finish()
                hideKeyboard()
            }

            imgConfirmPassword.setOnClickListener { view ->
                showHideConfirmPassword(view)
            }

            imgNewPassword.setOnClickListener { view ->
                showHideNewPassword(view)
            }

            imgOldPassword.setOnClickListener { view ->
                showHideOldPassword(view)
            }

            txtForgotPassword.setOnClickListener {
                findNavController().navigate(R.id.action_to_forgot_password_fragment)
            }

            etOldPassword.addTextChangedListener(object : TextWatcher {
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
                    etOldPassword.backgroundTintList =
                        ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.secondary_text))

                }
            })

            etNewPassword.addTextChangedListener(object : TextWatcher {
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
                    etNewPassword.backgroundTintList =
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
        }
    }

    private fun onChangePassword(){
        binding.apply {
            btnNext.setOnClickListener {
                var isHaveError = false

                if(etConfirmPassword.text.toString().trim() != etNewPassword.text.toString().trim()){
                    txtPasswordMessage.text = resources.getString(R.string.password_does_not_match)
                    txtPasswordMessage.visibility = View.VISIBLE
                    etConfirmPassword.backgroundTintList =
                        ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.danger))
                    etNewPassword.backgroundTintList =
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

                if(etNewPassword.text.toString().trim() == etOldPassword.text.toString().trim())
                {
                    txtPasswordMessage.text = resources.getString(R.string.new_password_cannot_be_the_same_as_old)
                    txtPasswordMessage.visibility = View.VISIBLE
                    etNewPassword.backgroundTintList =
                        ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.danger))
                    etOldPassword.backgroundTintList =
                        ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.danger))
                    isHaveError = true
                }

                if (etOldPassword.text.toString().length < 8) {
                    txtPasswordMessage.text = resources.getString(R.string.your_old_password_must_longer_than_8_digits)
                    txtPasswordMessage.visibility = View.VISIBLE
                    etOldPassword.backgroundTintList =
                        ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.danger))
                    isHaveError = true
                }

                if (etNewPassword.text.toString().length < 8) {
                    txtPasswordMessage.text = resources.getString(R.string.please_enter_a_password_longer_than_8_digits)
                    txtPasswordMessage.visibility = View.VISIBLE
                    etNewPassword.backgroundTintList =
                        ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.danger))
                    isHaveError = true
                }

                if (!isHaveError) {
                    onProgressBar(true)
                    mPresenter.onChangeLoginPassword(User.ChangeLoginPasswordObject(etOldPassword.text.toString(),etNewPassword.text.toString(),etConfirmPassword.text.toString()),UTSwapApp.instance)
                }
            }
        }
    }

    override fun onChangePasswordSuccess(data: User.ChangeLoginPasswordRes) {
        onProgressBar(false)
        binding.apply {
            findNavController().navigate(R.id.action_to_change_password_success)
            hideKeyboard()
        }
    }

    override fun onChangePasswordFail(data: User.ChangeLoginPasswordRes) {
        onProgressBar(false)
        binding.apply {
            txtPasswordMessage.visibility = View.VISIBLE
            txtPasswordMessage.text = data.message.toString()
            if(data.message.toString() == "OLD PASSWORD IS WRONG!")
            {
                etOldPassword.backgroundTintList =
                    ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.danger))
                txtPasswordMessage.text = resources.getString(R.string.old_password_is_wrong)
            }else if(data.message.toString() == "VERIFY PASSWORD DID NOT MATCH!"){
                etConfirmPassword.backgroundTintList =
                    ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.danger))
                txtPasswordMessage.text = resources.getString(R.string.verify_password_did_not_match)
            }else{
                etOldPassword.backgroundTintList =
                    ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.danger))
                etNewPassword.backgroundTintList =
                    ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.danger))
                etConfirmPassword.backgroundTintList =
                    ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.danger))
            }
        }
    }

    override fun onUserExpiredToken() {
        ClientClearData.clearDataUser()
        startActivity(Intent(requireActivity(), MainActivity::class.java))
        activity?.finish()
        requireActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
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
            etOldPassword.backgroundTintList =
                ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.secondary_text))
            etNewPassword.backgroundTintList =
                ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.secondary_text))
            etConfirmPassword.backgroundTintList =
                ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.secondary_text))
            txtPasswordMessage.visibility = View.GONE
        }
    }

    private fun hideKeyboard() {
        val inputManager =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(view?.windowToken, 0)
    }

    //Show Hide Password
    fun showHideOldPassword(view: View) {
        binding.apply {
            if (view.id == R.id.img_old_password) {
                if (etOldPassword.transformationMethod.equals(PasswordTransformationMethod.getInstance())) {
                    imgOldPassword.setImageResource(R.drawable.ic_baseline_visibility_24)
                    //Show Password
                    etOldPassword.transformationMethod =
                        HideReturnsTransformationMethod.getInstance()
                }else{
                    imgOldPassword.setImageResource(R.drawable.ic_baseline_visibility_off_24)

                    //Hide Password
                    etOldPassword.transformationMethod = PasswordTransformationMethod.getInstance()
                }
                etOldPassword.requestFocus()
                etOldPassword.setSelection(etOldPassword.text.length)
            }
        }
    }

    fun showHideConfirmPassword(view: View) {
        binding.apply {
            if (view.id == R.id.img_confirm_password) {
                if (etConfirmPassword.transformationMethod.equals(PasswordTransformationMethod.getInstance())
                ) {
                    imgConfirmPassword.setImageResource(R.drawable.ic_baseline_visibility_24)
                    //Show Password
                    etConfirmPassword.transformationMethod =
                        HideReturnsTransformationMethod.getInstance()
                }else{
                    imgConfirmPassword.setImageResource(R.drawable.ic_baseline_visibility_off_24)

                    //Hide Password
                    etConfirmPassword.transformationMethod =
                        PasswordTransformationMethod.getInstance()
                }
                etConfirmPassword.requestFocus()
                etConfirmPassword.setSelection(etConfirmPassword.text.length)
            }

        }

    }

    fun showHideNewPassword(view: View) {
        binding.apply {
            if (view.id == R.id.img_new_password) {
                if (etNewPassword.transformationMethod.equals(PasswordTransformationMethod.getInstance())
                ) {
                    imgNewPassword.setImageResource(R.drawable.ic_baseline_visibility_24)
                    //Show Password
                    etNewPassword.transformationMethod =
                        HideReturnsTransformationMethod.getInstance()
                }else{
                    imgNewPassword.setImageResource(R.drawable.ic_baseline_visibility_off_24)

                    //Hide Password
                    etNewPassword.transformationMethod =
                        PasswordTransformationMethod.getInstance()
                }
                etNewPassword.requestFocus()
                etNewPassword.setSelection(etNewPassword.text.length)
            }

        }

    }
}