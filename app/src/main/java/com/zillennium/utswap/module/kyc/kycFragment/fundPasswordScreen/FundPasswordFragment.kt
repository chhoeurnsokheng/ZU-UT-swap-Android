package com.zillennium.utswap.module.kyc.kycFragment.fundPasswordScreen

import android.annotation.SuppressLint
import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.children
import androidx.navigation.fragment.findNavController
import com.zillennium.utswap.Datas.StoredPreferences.KYCPreferences
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.FragmentKycFundPasswordBinding

class FundPasswordFragment :
    BaseMvpFragment<FundPasswordView.View, FundPasswordView.Presenter, FragmentKycFundPasswordBinding>(),
    FundPasswordView.View {

    override var mPresenter: FundPasswordView.Presenter = FundPasswordPresenter()
    override val layoutResource: Int = R.layout.fragment_kyc_fund_password

    private var clickCountPassword = 1
    private var clickCountConfirmPassword = 1

    @SuppressLint("UseCompatLoadingForDrawables", "SetTextI18n")
    override fun initView() {
        super.initView()
//        try {
            binding.apply {

                imgBack.setOnClickListener {
                    findNavController().popBackStack()
                }

                /* Fund Password Code */
                numberVerification.setOnClickListener {
                    editFundPassword.requestFocus()
                    val inputManager1 =
                        requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    inputManager1.showSoftInput(editFundPassword, InputMethodManager.SHOW_IMPLICIT)

                    if(editFundPassword.text.isEmpty()){
                        val textInputLast = numberVerification.getChildAt(0) as TextView
                        textInputLast.text = "|"
                        textInputLast.transformationMethod = HideReturnsTransformationMethod.getInstance()
                    }

                    if(editConfirmFundPassword.text.isEmpty()){
                        val confirmTextInputLast = confirmNumberVerification.getChildAt(0) as TextView
                        confirmTextInputLast.text = ""
                    }
                }

                editFundPassword.addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(ch: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                    override fun onTextChanged(chr: CharSequence?, p1: Int, p2: Int, p3: Int) {

                        for(child in numberVerification.children){
                            val children = child as TextView
                            children.background = ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.bg_border_bottom)
                            children.text = ""
                            showPassword(clickCountPassword)
                        }

                        if(chr?.length!! <= 3){
                            val textInputLast = chr.length.let { numberVerification.getChildAt(it.toInt()) } as TextView
                            textInputLast.text = "|"
                            textInputLast.transformationMethod = HideReturnsTransformationMethod.getInstance()
                        }

                        for (index in chr?.indices!!) {
                            val textInput = numberVerification.getChildAt(index) as TextView
                            textInput.text = chr[index].toString()
                            if(index == numberVerification.childCount - 1){
                                confirmNumberVerification.callOnClick()
                            }
                        }
                    }
                    override fun afterTextChanged(p0: Editable?) {}
                })

                /* Confirm Fund Password code */
                confirmNumberVerification.setOnClickListener {
                    editConfirmFundPassword.requestFocus()
                    val inputManager2 =
                        requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    inputManager2.showSoftInput(editConfirmFundPassword, InputMethodManager.SHOW_IMPLICIT)

                    if(editConfirmFundPassword.text.isEmpty()){
                        val confirmTextInputLast = confirmNumberVerification.getChildAt(0) as TextView
                        confirmTextInputLast.text = "|"
                        confirmTextInputLast.transformationMethod = HideReturnsTransformationMethod.getInstance()
                    }

                    if(editFundPassword.text.isEmpty()){
                        val textInputLast = numberVerification.getChildAt(0) as TextView
                        textInputLast.text = ""
                    }
                }

                editConfirmFundPassword.addTextChangedListener(object: TextWatcher{
                    override fun beforeTextChanged(ch: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                    override fun onTextChanged(chr: CharSequence?, p1: Int, p2: Int, p3: Int) {

                        for(child in confirmNumberVerification.children){
                            val children = child as TextView
                            children.background = ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.bg_border_bottom)
                            children.text = ""
                            showConfirmPassword(clickCountConfirmPassword)
                        }

                        if(chr?.length!! <= 3){
                            val textInputLast = chr.length.let { confirmNumberVerification.getChildAt(it.toInt()) } as TextView
                            textInputLast.text = "|"
                            textInputLast.transformationMethod = HideReturnsTransformationMethod.getInstance()
                        }

                        for (index in chr?.indices!!){
                            val textInput2 = confirmNumberVerification.getChildAt(index) as TextView
                            textInput2.text = chr[index].toString()
                            if(index == numberVerification.childCount - 1){
                                val inputManager2 =
                                    requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                                inputManager2.hideSoftInputFromWindow(view?.windowToken, 0)
                            }
                        }
                    }

                    override fun afterTextChanged(p0: Editable?) {}
                })

                btnNext.setOnClickListener {
                    if (editFundPassword.text.toString() == editConfirmFundPassword.text.toString() && editFundPassword.length() == 4 && editConfirmFundPassword.length() == 4){
                        KYCPreferences().FUND_PASSWORD = editFundPassword.text.toString()
                        findNavController().navigate(R.id.action_to_contract_kyc_fragment)
                    }else{
                        for(child in numberVerification.children){
                            child.background = ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.bg_border_bottom_red)
                        }
                        for(child in confirmNumberVerification.children){
                            child.background = ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.bg_border_bottom_red)
                        }
                    }
                }

//                if(!KYCPreferences().FUND_PASSWORD.isNullOrEmpty()){
//                    editFundPassword.setText(KYCPreferences().FUND_PASSWORD.toString())
//                    editConfirmFundPassword.setText(KYCPreferences().FUND_PASSWORD.toString())
//                }

                imgShowPassword.setOnClickListener{
                    clickCountPassword++
                    showPassword(clickCountPassword)
                }

                imgShowConfirmPassword.setOnClickListener {
                    clickCountConfirmPassword++
                    showConfirmPassword(clickCountConfirmPassword)
                }

                imgShowPassword.callOnClick()
                imgShowConfirmPassword.callOnClick()
            }

//        } catch (error: Exception) {
//            // Must be safe
//        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
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

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun showConfirmPassword(clickConfirmPassword: Int) {
        binding.apply {
            if (clickConfirmPassword % 2 == 0) {
                for (child in confirmNumberVerification.children) {
                    val children = child as TextView
                    children.transformationMethod = PasswordTransformationMethod.getInstance()
                }
                imgShowConfirmPassword.setImageResource(R.drawable.ic_baseline_remove_red_eye_24)
            } else {
                for (child in confirmNumberVerification.children) {
                    val children = child as TextView
                    children.transformationMethod = HideReturnsTransformationMethod.getInstance()
                }
                imgShowConfirmPassword.setImageResource(R.drawable.ic_baseline_visibility_off_24)
            }
        }

    }
}