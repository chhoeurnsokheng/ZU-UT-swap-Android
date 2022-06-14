package com.zillennium.utswap.screens.account.addNumber

import android.content.Intent
import android.content.res.ColorStateList
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.zillennium.utswap.Datas.GlobalVariable.SettingVariable
import com.zillennium.utswap.Datas.StoredPreferences.KYCPreferences
import com.zillennium.utswap.Datas.StoredPreferences.SessionPreferences
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivityAddNumberBinding
import com.zillennium.utswap.screens.account.verificationAccunt.VerificationAccountActivity
import com.zillennium.utswap.screens.kyc.kycFragment.idVerificationScreen.IDVerificationFragment
import com.zillennium.utswap.utils.groupingSeparator
import com.zillennium.utswap.utils.groupingSeparatorPhoneNumber

class AddNumberActivity :
    BaseMvpActivity<AddNumberView.View, AddNumberView.Presenter, ActivityAddNumberBinding>(),
    AddNumberView.View {

    override var mPresenter: AddNumberView.Presenter = AddNumberPresenter()
    override val layoutResource: Int = R.layout.activity_add_number

    override fun initView() {
        super.initView()
        try {
            binding.apply {
                imgClose.setOnClickListener {
                    finish()
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

                        val intent = Intent(UTSwapApp.instance, VerificationAccountActivity::class.java)
                        intent.putExtra(Intent.EXTRA_TEXT, "Account");
                        startActivity(intent)
                    }
                }

                etInputPhoneNumber.addTextChangedListener(object : TextWatcher{
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

    override fun onDestroy() {
        super.onDestroy()
    }
}