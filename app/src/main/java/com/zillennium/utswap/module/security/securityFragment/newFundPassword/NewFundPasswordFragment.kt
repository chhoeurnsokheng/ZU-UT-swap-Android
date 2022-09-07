package com.zillennium.utswap.module.security.securityFragment.newFundPassword


import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
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
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.FragmentAccountNewFundPasswordBinding
import com.zillennium.utswap.models.userService.User
import com.zillennium.utswap.screens.navbar.navbar.MainActivity
import com.zillennium.utswap.utils.ClientClearData

class NewFundPasswordFragment:
    BaseMvpFragment<NewFundPasswordView.View, NewFundPasswordView.Presenter, FragmentAccountNewFundPasswordBinding>(),
    NewFundPasswordView.View {

    override var mPresenter: NewFundPasswordView.Presenter = NewFundPasswordPresenter()
    override val layoutResource: Int = R.layout.fragment_account_new_fund_password

    private var clickCountPassword = 1
    private var clickCountConfirmPassword = 1

    override fun initView() {
        super.initView()
        onOtherActivity()
        onSubmitNewPassword()
    }

    private fun onOtherActivity(){
        binding.apply {
            imgClose.setOnClickListener {
                findNavController().popBackStack()
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

                    for (child in numberVerification.children) {
                        child.background = ContextCompat.getDrawable(
                            UTSwapApp.instance,
                            R.drawable.bg_border_bottom
                        )
                        child as TextView
                        child.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.black))
                    }

                    for (child in confirmNumberVerification.children) {
                        child.background = ContextCompat.getDrawable(
                            UTSwapApp.instance,
                            R.drawable.bg_border_bottom
                        )
                        child as TextView
                        child.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.black))
                    }

                    for (child in numberVerification.children) {
                        child.background = ContextCompat.getDrawable(
                            UTSwapApp.instance,
                            R.drawable.bg_border_bottom
                        )
                        child as TextView
                        child.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.black))
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
                    txtMessage.visibility = View.INVISIBLE
                }
                override fun afterTextChanged(p0: Editable?) {}
            })

            /* Confirm Fund Password code */
            confirmNumberVerification.setOnClickListener {
                editConfirmFundPassword.requestFocus()
                val inputManager2 = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
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

            editConfirmFundPassword.addTextChangedListener(object: TextWatcher {
                override fun beforeTextChanged(ch: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun onTextChanged(chr: CharSequence?, p1: Int, p2: Int, p3: Int) {

                    for(child in confirmNumberVerification.children){
                        val children = child as TextView
                        children.background = ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.bg_border_bottom)
                        children.text = ""
                        showConfirmPassword(clickCountConfirmPassword)
                    }

                    for (child in numberVerification.children) {
                        child.background = ContextCompat.getDrawable(
                            UTSwapApp.instance,
                            R.drawable.bg_border_bottom
                        )
                        child as TextView
                        child.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.black))
                    }

                    for (child in confirmNumberVerification.children) {
                        child.background = ContextCompat.getDrawable(
                            UTSwapApp.instance,
                            R.drawable.bg_border_bottom
                        )
                        child as TextView
                        child.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.black))
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
                            val inputManager2 = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                            inputManager2.hideSoftInputFromWindow(view?.windowToken, 0)
                        }
                    }
                    txtMessage.visibility = View.INVISIBLE
                }

                override fun afterTextChanged(p0: Editable?) {}
            })

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
    }

    private fun onSubmitNewPassword(){
        binding.apply {
            btnNext.setOnClickListener {
                if (editFundPassword.length() == 4 && editConfirmFundPassword.length() == 4){
                    if(editFundPassword.text.toString() != editConfirmFundPassword.text.toString()){
                        txtMessage.visibility = View.VISIBLE
                        txtMessage.text = resources.getString(R.string.password_did_not_match)

                        for (child in numberVerification.children) {
                            child.background = ContextCompat.getDrawable(
                                UTSwapApp.instance,
                                R.drawable.bg_border_bottom_red
                            )
                            child as TextView
                            child.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.danger))
                        }

                        for (child in confirmNumberVerification.children) {
                            child.background = ContextCompat.getDrawable(
                                UTSwapApp.instance,
                                R.drawable.bg_border_bottom_red
                            )
                            child as TextView
                            child.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.danger))
                        }
                    }else{
                        onProgressBar(true)
                        for (child in numberVerification.children) {
                            child.background = ContextCompat.getDrawable(
                                UTSwapApp.instance,
                                R.drawable.bg_border_bottom
                            )
                            child as TextView
                            child.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.black))
                        }

                        for (child in confirmNumberVerification.children) {
                            child.background = ContextCompat.getDrawable(
                                UTSwapApp.instance,
                                R.drawable.bg_border_bottom
                            )
                            child as TextView
                            child.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.black))
                        }

                        mPresenter.onSubmitNewPassword(User.ChangeFundPasswordObject(editFundPassword.text.toString(),editConfirmFundPassword.text.toString()),UTSwapApp.instance)
                    }
                }else{
                    txtMessage.visibility = View.VISIBLE
                    txtMessage.text = resources.getString(R.string.please_enter_4_digits_fund_password)

                    for (child in numberVerification.children) {
                        child.background = ContextCompat.getDrawable(
                            UTSwapApp.instance,
                            R.drawable.bg_border_bottom_red
                        )
                        child as TextView
                        child.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.danger))
                    }

                    for (child in confirmNumberVerification.children) {
                        child.background = ContextCompat.getDrawable(
                            UTSwapApp.instance,
                            R.drawable.bg_border_bottom_red
                        )
                        child as TextView
                        child.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.danger))
                    }
                }
            }
        }
    }

    override fun onChangePasswordSuccess(body: User.ChangeFundPasswordRes) {
        onProgressBar(false)
        binding.apply {
            for (child in numberVerification.children) {
                child.background = ContextCompat.getDrawable(
                    UTSwapApp.instance,
                    R.drawable.bg_border_bottom
                )
                child as TextView
                child.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.black))
            }

            for (child in confirmNumberVerification.children) {
                child.background = ContextCompat.getDrawable(
                    UTSwapApp.instance,
                    R.drawable.bg_border_bottom
                )
                child as TextView
                child.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.black))
            }

            findNavController().navigate(R.id.action_to_change_fund_password_success)
        }
    }

    override fun onChangePasswordFail(body: User.ChangeFundPasswordRes) {
        onProgressBar(false)
        binding.apply {
            txtMessage.visibility = View.VISIBLE
            if(body.message.toString() == "No changes were made!")
            {
                txtMessage.text = resources.getString(R.string.new_password_cannot_be_the_same_as_old)
            }else{
                txtMessage.text = body.message.toString()
            }

            for(child in numberVerification.children){
                child.background = ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.bg_border_bottom_red)
            }

            for (child in numberVerification.children) {
                child.background = ContextCompat.getDrawable(
                    UTSwapApp.instance,
                    R.drawable.bg_border_bottom_red
                )
                child as TextView
                child.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.danger))
            }

            for(child in confirmNumberVerification.children){
                child.background = ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.bg_border_bottom_red)
            }

            for (child in confirmNumberVerification.children) {
                child.background = ContextCompat.getDrawable(
                    UTSwapApp.instance,
                    R.drawable.bg_border_bottom_red
                )
                child as TextView
                child.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.danger))
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
            txtMessage.visibility = View.INVISIBLE
            for(child in numberVerification.children){
                child.background = ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.bg_border_bottom)
            }

            for(child in confirmNumberVerification.children){
                child.background = ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.bg_border_bottom)
            }
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
                imgShowPassword.setImageResource(R.drawable.ic_baseline_visibility_off_24)
            } else {
                for (child in numberVerification.children) {
                    val children = child as TextView
                    children.transformationMethod = HideReturnsTransformationMethod.getInstance()
                }
                imgShowPassword.setImageResource(R.drawable.ic_baseline_visibility_24)
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
                imgShowConfirmPassword.setImageResource(R.drawable.ic_baseline_visibility_off_24)
            } else {
                for (child in confirmNumberVerification.children) {
                    val children = child as TextView
                    children.transformationMethod = HideReturnsTransformationMethod.getInstance()
                }
                imgShowConfirmPassword.setImageResource(R.drawable.ic_baseline_visibility_24)
            }
        }

    }
}