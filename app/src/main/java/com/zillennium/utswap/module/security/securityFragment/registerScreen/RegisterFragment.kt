package com.zillennium.utswap.module.security.securityFragment.registerScreen

import android.content.Context
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
import com.zillennium.utswap.databinding.FragmentSecurityRegisterBinding
import com.zillennium.utswap.models.userService.User
import com.zillennium.utswap.utils.Constants
import com.zillennium.utswap.utils.validate

class RegisterFragment :
    BaseMvpFragment<RegisterView.View, RegisterView.Presenter, FragmentSecurityRegisterBinding>(),
    RegisterView.View {

    override var mPresenter: RegisterView.Presenter = RegisterPresenter()
    override val layoutResource: Int = R.layout.fragment_security_register

    override fun initView() {
        super.initView()

        onOtherActivity()
        onSubmitRegister()
    }

    private fun onOtherActivity(){
        binding.apply {
            btnBack.setOnClickListener {
                val inputManager =
                    requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputManager.hideSoftInputFromWindow(view?.windowToken, 0)
                activity?.finish()
            }

            layView.setOnClickListener {
                val inputManager =
                    requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputManager.hideSoftInputFromWindow(view?.windowToken, 0)
            }

            laySignUp.setOnClickListener {
                val inputManager =
                    requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputManager.hideSoftInputFromWindow(view?.windowToken, 0)
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
                    txtMessage.visibility = View.GONE
                    etEmail.backgroundTintList =
                        ColorStateList.valueOf(
                            ContextCompat.getColor(
                                UTSwapApp.instance,
                                R.color.secondary_text
                            )
                        )
                }
            })

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
                    txtMessage.visibility = View.GONE
                    etPassword.backgroundTintList =
                        ColorStateList.valueOf(
                            ContextCompat.getColor(
                                UTSwapApp.instance,
                                R.color.secondary_text
                            )
                        )

                }
            })

            showPassBtn.setOnClickListener { ShowHidePassword() }

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
                    txtMessage.visibility = View.GONE
                    etConfirmPassword.backgroundTintList =
                        ColorStateList.valueOf(
                            ContextCompat.getColor(
                                UTSwapApp.instance,
                                R.color.secondary_text
                            )
                        )
                }
            })

            showConfirmPassBtn.setOnClickListener { ShowHidePassConfirmPassword() }
        }
    }

    private fun onSubmitRegister(){
        binding.apply {
            btnSignup.setOnClickListener {

                val isHaveError = false
                txtMessage.text = resources.getString(R.string.invalid_try_again)
                txtMessage.backgroundTintList = ColorStateList.valueOf(
                    ContextCompat.getColor(
                        UTSwapApp.instance,
                        R.color.danger
                    )
                )

                if (!validate().isValidEmail(
                        etEmail.text.toString().trim()
                    ) && !validate().isValidPhoneNumber(etEmail.text.toString().trim())
                ) {
                    txtMessage.text = resources.getString(R.string.please_enter_email_or_phone_number)
                    txtMessage.visibility = View.VISIBLE
                    etEmail.backgroundTintList =
                        ColorStateList.valueOf(
                            ContextCompat.getColor(
                                UTSwapApp.instance,
                                R.color.danger
                            )
                        )
                    return@setOnClickListener
                }

                if (etPassword.text.toString().length < 8) {
                    txtMessage.text = resources.getString(R.string.please_enter_a_password_longer_than_8_digits)
                    txtMessage.visibility = View.VISIBLE
                    etPassword.backgroundTintList =
                        ColorStateList.valueOf(
                            ContextCompat.getColor(
                                UTSwapApp.instance,
                                R.color.danger
                            )
                        )
                    return@setOnClickListener
                }

                if (etConfirmPassword.text.toString().length < 8) {
                    txtMessage.text = resources.getString(R.string.invalid_try_again)
                    txtMessage.visibility = View.VISIBLE
                    etConfirmPassword.backgroundTintList =
                        ColorStateList.valueOf(
                            ContextCompat.getColor(
                                UTSwapApp.instance,
                                R.color.danger
                            )
                        )
                    return@setOnClickListener
                }

                if (etConfirmPassword.text.toString().trim() != etConfirmPassword.text.toString()
                        .trim()
                ) {
                    txtMessage.text = resources.getString(R.string.password_did_not_match)
                    txtMessage.visibility = View.VISIBLE
                    etPassword.backgroundTintList =
                        ColorStateList.valueOf(
                            ContextCompat.getColor(
                                UTSwapApp.instance,
                                R.color.danger
                            )
                        )
                    etConfirmPassword.backgroundTintList =
                        ColorStateList.valueOf(
                            ContextCompat.getColor(
                                UTSwapApp.instance,
                                R.color.danger
                            )
                        )
                    return@setOnClickListener
                }

                if (!isHaveError) {

                    onProgressBar(true)
                    mPresenter.onSubmitRegister(User.RegisterObject(etEmail.text.toString(),etPassword.text.toString(),etConfirmPassword.text.toString(),""))
                }
            }
        }
    }

    override fun onRegisterSuccess(data: User.RegisterRes) {
        binding.apply {
            onProgressBar(false)
            val key = data.data?.secure_key.toString()
            SessionPreferences().SESSION_SECURE_KEY = key

            Constants.RegisterData.username = etEmail.text.toString()
            Constants.RegisterData.password = etConfirmPassword.text.toString()

            hideKeyboard()

            findNavController().navigate(R.id.action_to_term_condition_security_fragment)
        }
    }

    override fun onRegisterFail(data: User.RegisterRes) {
        binding.apply {
            onProgressBar(false)
            txtMessage.visibility = View.VISIBLE
            txtMessage.text = data.message.toString()
            if(data.message.toString() == "Password incorrect!")
            {
                etPassword.backgroundTintList =
                    ColorStateList.valueOf(
                        ContextCompat.getColor(
                            UTSwapApp.instance,
                            R.color.danger
                        )
                    )
                etConfirmPassword.backgroundTintList =
                    ColorStateList.valueOf(
                        ContextCompat.getColor(
                            UTSwapApp.instance,
                            R.color.danger
                        )
                    )
                etEmail.backgroundTintList =
                    ColorStateList.valueOf(
                        ContextCompat.getColor(
                            UTSwapApp.instance,
                            R.color.secondary_text
                        )
                    )
            }else{
                etEmail.backgroundTintList =
                    ColorStateList.valueOf(
                        ContextCompat.getColor(
                            UTSwapApp.instance,
                            R.color.danger
                        )
                    )
                etPassword.backgroundTintList =
                    ColorStateList.valueOf(
                        ContextCompat.getColor(
                            UTSwapApp.instance,
                            R.color.secondary_text
                        )
                    )
                etConfirmPassword.backgroundTintList =
                    ColorStateList.valueOf(
                        ContextCompat.getColor(
                            UTSwapApp.instance,
                            R.color.secondary_text
                        )
                    )
            }
        }
        hideKeyboard()
    }

    private fun onProgressBar(status: Boolean){
        binding.apply {
            if(status){
                pbSignup.visibility = View.VISIBLE
                btnSignup.isClickable = false
                btnSignup.alpha = 0.6F
            }else{
                pbSignup.visibility = View.GONE
                btnSignup.isClickable = true
                btnSignup.alpha = 1F
            }
            etEmail.backgroundTintList =
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        UTSwapApp.instance,
                        R.color.secondary_text
                    )
                )
            etPassword.backgroundTintList =
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        UTSwapApp.instance,
                        R.color.secondary_text
                    )
                )
            etConfirmPassword.backgroundTintList =
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        UTSwapApp.instance,
                        R.color.secondary_text
                    )
                )
        }
    }

    //Show Hide Password
    fun ShowHidePassword() {
        binding.apply {
            if (etPassword.transformationMethod.equals(PasswordTransformationMethod.getInstance())) {
                showPassBtn.setImageResource(R.drawable.ic_baseline_visibility_off_24)

                //Show Password
                etPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
            } else {
                showPassBtn.setImageResource(
                    R.drawable.ic_baseline_visibility_24
                )

                //Hide Password
                etPassword.transformationMethod = PasswordTransformationMethod.getInstance()
            }
            etPassword.requestFocus()
            etPassword.setSelection(etPassword.text.length)
        }
    }

    //Show Hide confirm password
    fun ShowHidePassConfirmPassword() {
        binding.apply {
            if (etConfirmPassword.transformationMethod.equals(PasswordTransformationMethod.getInstance())
            ) {
                showConfirmPassBtn.setImageResource(R.drawable.ic_baseline_visibility_off_24)

                //Show Password
                etConfirmPassword.transformationMethod =
                    HideReturnsTransformationMethod.getInstance()
            } else {

                showConfirmPassBtn.setImageResource(R.drawable.ic_baseline_visibility_24)

                //Hide Password
                etConfirmPassword.transformationMethod =
                    PasswordTransformationMethod.getInstance()
            }
            etConfirmPassword.requestFocus()
            etConfirmPassword.setSelection(etConfirmPassword.text.length)

        }

    }

    private fun hideKeyboard() {
        val inputManager =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(view?.windowToken, 0)
    }

}
