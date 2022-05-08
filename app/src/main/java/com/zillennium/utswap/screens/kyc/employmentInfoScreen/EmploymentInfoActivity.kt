package com.zillennium.utswap.screens.kyc.employmentInfoScreen

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.ColorStateList
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.core.view.isVisible
import com.zillennium.utswap.Datas.StoredPreferences.KYCPreferences
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivityKycEmploymentInfoBinding
import com.zillennium.utswap.screens.kyc.addressInfoScreen.AddressInfoActivity
import java.io.IOException

object info {
    var occupation = ""
    var company = ""
    var phone_number = ""
    var email_emp = ""
}

class EmploymentInfoActivity :
    BaseMvpActivity<EmploymentInfoView.View, EmploymentInfoView.Presenter, ActivityKycEmploymentInfoBinding>(),
    EmploymentInfoView.View {

    override var mPresenter: EmploymentInfoView.Presenter = EmploymentInfoPresenter()
    override val layoutResource: Int = R.layout.activity_kyc_employment_info

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun initView() {
        super.initView()
        try {
            binding.apply {

                /* if Data already input */
                if (KYCPreferences().OCCUPATION.isNullOrEmpty()) {
                    etOccupation.setText(KYCPreferences().OCCUPATION)
                }
                if (KYCPreferences().COMPANY.isNullOrEmpty()) {
                    etCompany.setText(KYCPreferences().COMPANY)
                }
                if (KYCPreferences().PHONE_NUMBER.isNullOrEmpty()) {
                    txtPhoneNumber.visibility = View.VISIBLE
                    etphoneNumber.visibility = View.VISIBLE
                    etphoneNumber.setText(KYCPreferences().PHONE_NUMBER)
                }
                if (KYCPreferences().EMAIL.isNullOrEmpty()) {
                    etemail.setText(KYCPreferences().EMAIL)
                    txtEmail.visibility = View.VISIBLE
                    etemail.visibility = View.VISIBLE
                }else{
                    txtEmail.visibility = View.GONE
                    etemail.visibility = View.GONE
                }

                imgBack.setOnClickListener { finish() }

                btnNext.setOnClickListener {

                    var isHaveError = false
                    if (etOccupation.text.toString().isEmpty()) {
                        txtErrorOccupation.visibility = View.VISIBLE
                        etOccupation.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.red)))
                        isHaveError = true
                    }
                    if (etCompany.text.toString().isEmpty()) {
                        txtErrorCompany.visibility = View.VISIBLE
                        etCompany.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.red)))
                        isHaveError = true
                    }
                    if (isHaveError) {
                        return@setOnClickListener
                    } else {

                        KYCPreferences().OCCUPATION = info.occupation
                        KYCPreferences().COMPANY = info.company
                        KYCPreferences().PHONE_NUMBER = info.phone_number
                        KYCPreferences().EMAIL = info.email_emp

                        val intent = Intent(UTSwapApp.instance, AddressInfoActivity::class.java)
                        startActivity(intent)
                    }
                }

                etOccupation.addTextChangedListener(object : TextWatcher {
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
                        txtErrorOccupation.visibility = View.GONE
                        etOccupation.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.secondary_text)))
                    }
                })

                etCompany.addTextChangedListener(object : TextWatcher {
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
                        txtErrorCompany.visibility = View.GONE
                        etCompany.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.secondary_text)))
                    }
                })
            }

        } catch (error: Exception) {
            // Must be safe
        }
    }
}