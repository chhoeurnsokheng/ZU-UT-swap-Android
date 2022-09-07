package com.zillennium.utswap.module.account.addNumberScreen.fragment

import android.content.Context
import android.content.Intent
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
import com.zillennium.utswap.screens.navbar.navbar.MainActivity
import com.zillennium.utswap.utils.ClientClearData
import com.zillennium.utswap.utils.Constants
import com.zillennium.utswap.utils.validate

class AddNumberFragment :
    BaseMvpFragment<AddNumberView.View, AddNumberView.Presenter, FragmentAccountAddNumberBinding>(),
    AddNumberView.View {

    override var mPresenter: AddNumberView.Presenter = AddNumberPresenter()
    override val layoutResource: Int = R.layout.fragment_account_add_number

    private var countLoop = 0

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

                private val EMPTY_STRING = ""
                private val WHITE_SPACE = " "
                private var lastSource = EMPTY_STRING

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    txtMessage.visibility = View.INVISIBLE
                    etInputPhoneNumber.background = ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.outline_edittext_add_phone_number)
                }

                override fun afterTextChanged(s: Editable?) {
                    var source = s.toString()
                    if (lastSource != source) {
                        source = source.replace(WHITE_SPACE, EMPTY_STRING)
                        val stringBuilder = StringBuilder()
                        countLoop++
                        for (i in source.indices) {
                            if (i > 0 && i % 3 == 0 && i != 9) {
                                stringBuilder.append(WHITE_SPACE)
                            }
                            stringBuilder.append(source[i])
                        }
                        lastSource = stringBuilder.toString()
                        s?.replace(0, s.length, lastSource)
                    }
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
                    txtMessage.visibility = View.VISIBLE
                    txtMessage.text = resources.getString(R.string.please_enter_phone)
                    isHaveError = true
                }

                if(etInputPhoneNumber.text.toString().length < 11)
                {
                    etInputPhoneNumber.background = ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.outline_edittext_error_corner_16dp)
                    txtMessage.visibility = View.VISIBLE
                    txtMessage.text = resources.getString(R.string.please_enter_valid_phone_number)
                    isHaveError = true
                }

                if(etInputPhoneNumber.text.toString().isNotEmpty()){
                    if(etInputPhoneNumber.text.toString()[0] != '0'){
                        etInputPhoneNumber.background = ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.outline_edittext_error_corner_16dp)
                        txtMessage.visibility = View.VISIBLE
                        txtMessage.text = resources.getString(R.string.please_enter_valid_phone_number)
                        isHaveError = true
                    }
                }

                if(!validate().isValidPhoneNumber(etInputPhoneNumber.text.toString())){
                    txtMessage.visibility = View.VISIBLE
                    txtMessage.text = resources.getString(R.string.please_enter_valid_phone_number)
                    etInputPhoneNumber.background = ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.outline_edittext_error_corner_16dp)
                    isHaveError = true
                }

                if (isHaveError) {
                    return@setOnClickListener
                } else {
                    onProgressBar(true)
                    val phoneString = etInputPhoneNumber.text.toString().trim().replace(" ","")

                    mPresenter.onAddPhoneNumber(User.AddPhoneNumberObject(phoneString),UTSwapApp.instance)
                }
            }
        }
    }

    override fun onAddPhoneNumberSuccess(body: User.AddPhoneNumberRes) {
        onProgressBar(false)
        binding.apply {
            SessionPreferences().SESSION_SECURE_KEY_ADD_PHONE =  body.data?.secure_key.toString()
            Constants.AddPhoneNumber.cellPhone = etInputPhoneNumber.text.toString().trim().replace(" ","")

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
            etInputPhoneNumber.background = ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.outline_edittext_add_phone_number)
        }
    }

    private fun hideKeyboard() {
        val inputManager =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(view?.windowToken, 0)
    }
}