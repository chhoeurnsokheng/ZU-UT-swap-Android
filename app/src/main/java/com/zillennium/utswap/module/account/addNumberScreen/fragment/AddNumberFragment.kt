package com.zillennium.utswap.module.account.addNumberScreen.fragment

import android.content.Context
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
import com.zillennium.utswap.databinding.FragmentAccountAddNumberBinding
import com.zillennium.utswap.models.userService.User
import com.zillennium.utswap.utils.Constants
import com.zillennium.utswap.utils.validate

class AddNumberFragment :
    BaseMvpFragment<AddNumberView.View, AddNumberView.Presenter, FragmentAccountAddNumberBinding>(),
    AddNumberView.View {

    override var mPresenter: AddNumberView.Presenter = AddNumberPresenter()
    override val layoutResource: Int = R.layout.fragment_account_add_number

    override fun initView() {
        super.initView()

        onOtherActivity()
        onAddPhoneNumber()
    }

    private fun onOtherActivity(){
        binding.apply {
            imgClose.setOnClickListener {
                activity?.finish()
                hideKeyboard()
            }

            etInputPhoneNumber.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    txtMessage.visibility = View.INVISIBLE
                    etInputPhoneNumber.background = ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.outline_edittext_add_phone_number)
                }

                override fun afterTextChanged(p0: Editable?) {

                }

            })
        }
    }

    private fun onAddPhoneNumber(){
        binding.apply {
            btnNext.setOnClickListener {
                var isHaveError = false

                if (etInputPhoneNumber.text.toString().isEmpty() ) {
                    etInputPhoneNumber.background = ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.outline_edittext_error_corner_16dp)
                    isHaveError = true
                }

                if(!validate().isValidPhoneNumber(etInputPhoneNumber.text.toString())){
                    txtMessage.text = resources.getString(R.string.please_enter_valid_phone_number)
                    etInputPhoneNumber.background = ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.outline_edittext_error_corner_16dp)
                    isHaveError = true
                }

                if (isHaveError) {
                    return@setOnClickListener
                } else {
//                    pbNext.visibility = View.VISIBLE
//                    btnNext.isClickable = false
//                    btnNext.alpha = 0.6F
//
//                    SettingVariable.phoneNumber.value = etInputPhoneNumber.text.toString()
//                    SessionPreferences().SESSION_PHONE_NUMBER = etInputPhoneNumber.text.toString()
//
//                    Handler().postDelayed({
//                        pbNext.visibility = View.GONE
//                        btnNext.isClickable = true
//                        btnNext.alpha = 1F
//
//                        findNavController().navigate(R.id.action_to_verification_security_fragment)
//                    },3000)
                    onProgressBar(true)

                    mPresenter.onAddPhoneNumber(User.AddPhoneNumberObject(etInputPhoneNumber.text.toString()),UTSwapApp.instance)
                }
            }
        }
    }

    override fun onAddPhoneNumberSuccess(body: User.AddPhoneNumberRes) {
        onProgressBar(false)
        binding.apply {
            SessionPreferences().SESSION_SECURE_KEY_ADD_PHONE =  body.data?.secure_key.toString()
            Constants.AddPhoneNumber.cellPhone = etInputPhoneNumber.text.toString()

            findNavController().navigate(R.id.action_to_verification_security_fragment)
        }
    }

    override fun onAddPhoneNumberFail(body: User.AddPhoneNumberRes) {
        onProgressBar(false)
        binding.apply {
            txtMessage.visibility = View.VISIBLE
            txtMessage.text = resources.getString(R.string.phone_number_already_used)
            etInputPhoneNumber.background = ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.outline_edittext_error_corner_16dp)
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
            txtMessage.visibility = View.INVISIBLE
            etInputPhoneNumber.background = ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.outline_edittext_add_phone_number)
        }
    }

    private fun hideKeyboard() {
        val inputManager =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(view?.windowToken, 0)
    }
}