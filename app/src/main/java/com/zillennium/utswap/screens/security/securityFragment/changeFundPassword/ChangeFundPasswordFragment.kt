package com.zillennium.utswap.screens.security.securityFragment.changeFundPassword

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.children
import androidx.navigation.fragment.findNavController
import com.zillennium.utswap.Datas.GlobalVariable.SessionVariable
import com.zillennium.utswap.Datas.StoredPreferences.SessionPreferences
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.FragmentAccountChangeFundPasswordBinding


class ChangeFundPasswordFragment :
    BaseMvpFragment<ChangeFundPasswordView.View, ChangeFundPasswordView.Presenter, FragmentAccountChangeFundPasswordBinding>(),
    ChangeFundPasswordView.View {

    override var mPresenter: ChangeFundPasswordView.Presenter = ChangeFundPasswordPresenter()
    override val layoutResource: Int = R.layout.fragment_account_change_fund_password

    private var clickCountPassword = 1

    override fun initView() {
        super.initView()
        try {
            binding.apply {

                imgClose.setOnClickListener {
                    activity?.finish()
                }

                /* Fund Password Code */
                numberVerification.setOnClickListener {
                    editFundPassword.requestFocus()
                    val inputManager1 = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    inputManager1.showSoftInput(editFundPassword, InputMethodManager.SHOW_IMPLICIT)

                    if(editFundPassword.text.isEmpty()){
                        val textInputLast = numberVerification.getChildAt(0) as TextView
                        textInputLast.text = "|"
                        textInputLast.transformationMethod = HideReturnsTransformationMethod.getInstance()
                    }
                }

                txtForgotPassword.setOnClickListener {
                    findNavController().navigate(R.id.action_to_forgot_fund_password_fragment)
                }

                imgShowPassword.setOnClickListener{
                    clickCountPassword++
                    showPassword(clickCountPassword)
                }

                imgShowPassword.callOnClick()

                editFundPassword.addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(ch: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                    override fun onTextChanged(chr: CharSequence?, p1: Int, p2: Int, p3: Int) {

                        for (child in numberVerification.children) {
                            val children = child as TextView
                            children.background = ContextCompat.getDrawable(
                                UTSwapApp.instance,
                                R.drawable.bg_border_bottom
                            )
                            children.text = ""
                            showPassword(clickCountPassword)
                        }

                        if (chr?.length!! <= 3) {
                            val textInputLast =
                                chr.length.let { numberVerification.getChildAt(it.toInt()) } as TextView
                            textInputLast.text = "|"
                            textInputLast.transformationMethod =
                                HideReturnsTransformationMethod.getInstance()
                        }

                        for (index in chr?.indices!!) {
                            val textInput = numberVerification.getChildAt(index) as TextView
                            textInput.text = chr[index].toString()
                            if (index == numberVerification.childCount - 1) {

                            }
                        }
                    }

                    override fun afterTextChanged(p0: Editable?) {
                    }
                })

                btnNext.setOnClickListener {

                    if (editFundPassword.text.isNotEmpty() && editFundPassword.length() == 4){
                        pbNext.visibility = View.VISIBLE
                        btnNext.isClickable = false
                        btnNext.alpha = 0.6F
                        Handler().postDelayed({
                            if (editFundPassword.length() == 4 && editFundPassword.text.toString() == "1111"){
                                findNavController().navigate(R.id.action_to_new_fund_password)
                            }else{
                                for(child in numberVerification.children){
                                    child.background = ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.bg_border_bottom_red)
                                }
                            }
                            pbNext.visibility = View.GONE
                            btnNext.isClickable = true
                            btnNext.alpha = 1F
                        },3000)
                    }else{
                        for(child in numberVerification.children){
                            child.background = ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.bg_border_bottom_red)
                        }
                    }
                }
            }
            // Code
        } catch (error: Exception) {
            // Must be safe
        }
    }

    private fun showPassword(clickPassword: Int) {
        binding.apply {
            if (clickPassword % 2 == 0) {
                numberVerification
                for (child in numberVerification.children) {
                    val children = child as TextView
                    children.transformationMethod = PasswordTransformationMethod.getInstance()
                }
                imgShowPassword.setImageResource(R.drawable.ic_baseline_remove_red_eye_24)
            } else {
                for (child in numberVerification.children) {
                    val children = child as TextView
                    children.transformationMethod = HideReturnsTransformationMethod.getInstance()
                }
                imgShowPassword.setImageResource(R.drawable.ic_baseline_visibility_off_24)
            }
        }
    }
}