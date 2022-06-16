package com.zillennium.utswap.screens.account.addNumber.fragment

import android.text.Editable
import android.text.TextWatcher
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.zillennium.utswap.Datas.GlobalVariable.SettingVariable
import com.zillennium.utswap.Datas.StoredPreferences.SessionPreferences
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.FragmentAccountAddNumberBinding

class AddNumberFragment :
    BaseMvpFragment<AddNumberView.View, AddNumberView.Presenter, FragmentAccountAddNumberBinding>(),
    AddNumberView.View {

    override var mPresenter: AddNumberView.Presenter = AddNumberPresenter()
    override val layoutResource: Int = R.layout.fragment_account_add_number

    override fun initView() {
        super.initView()
        try {
            binding.apply {
                imgClose.setOnClickListener {
                    activity?.finish()
                }

                btnNext.setOnClickListener {
                    var isHaveError = false

                    if (etInputPhoneNumber.text.toString().isEmpty() ) {
                        etInputPhoneNumber.background = ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.outline_edittext_error_corner_16dp)
                        isHaveError = true
                    }

                    if (isHaveError) {
                        return@setOnClickListener
                    } else {
                        SettingVariable.phoneNumber.value = etInputPhoneNumber.text.toString()
                        SessionPreferences().SESSION_PHONE_NUMBER = etInputPhoneNumber.text.toString()

                        findNavController().navigate(R.id.action_to_verification_security_fragment)
                    }
                }

                etInputPhoneNumber.addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                    }

                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                        etInputPhoneNumber.background = ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.outline_edittext_add_phone_number)
                    }

                    override fun afterTextChanged(p0: Editable?) {

                    }

                })

            }
            // Code
        } catch (error: Exception) {
            // Must be safe
        }
    }
}