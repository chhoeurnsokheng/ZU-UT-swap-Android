package com.zillennium.utswap.screens.kyc.kycFragment.employmentInfoScreen

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.ColorStateList
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.navigation.fragment.findNavController
import com.zillennium.utswap.Datas.StoredPreferences.KYCPreferences
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.FragmentKycEmploymentInfoBinding
import com.zillennium.utswap.screens.kyc.kycFragment.declarationScreen.DeclarationFragment


class EmploymentInfoFragment :
    BaseMvpFragment<EmploymentInfoView.View, EmploymentInfoView.Presenter, FragmentKycEmploymentInfoBinding>(),
    EmploymentInfoView.View {

    override var mPresenter: EmploymentInfoView.Presenter = EmploymentInfoPresenter()
    override val layoutResource: Int = R.layout.fragment_kyc_employment_info

    object info {
        var occupation = ""
        var company = ""
        var phone_number = ""
        var email_emp = ""
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun initView() {
        super.initView()
        try {
            binding.apply {

                /* if Data already input */
                if (!KYCPreferences().OCCUPATION.isNullOrEmpty()) {
                    info.occupation = KYCPreferences().OCCUPATION.toString()
                    etOccupation.setText(info.occupation)
                }
                if (!KYCPreferences().COMPANY.isNullOrEmpty()) {
                    info.company = KYCPreferences().OCCUPATION.toString()
                    etCompany.setText(info.company)
                }
                if (!KYCPreferences().PHONE_NUMBER.isNullOrEmpty()) {
                    info.phone_number = KYCPreferences().PHONE_NUMBER.toString()
                    etphoneNumber.setText(info.phone_number)
                }
                if (!KYCPreferences().EMAIL.isNullOrEmpty()) {
                    info.email_emp = KYCPreferences().EMAIL.toString()
                    etemail.setText(info.email_emp)
                }

                imgBack.setOnClickListener {
                    findNavController().popBackStack()
                }

                btnNext.setOnClickListener {

                    var isHaveError = false
                    if (info.occupation.isEmpty()) {
                        txtErrorOccupation.visibility = View.VISIBLE
                        etOccupation.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.red))
                        isHaveError = true
                    }
                    if (info.occupation.isEmpty()) {
                        txtErrorCompany.visibility = View.VISIBLE
                        etCompany.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.red))
                        isHaveError = true
                    }
                    if (isHaveError) {
                        return@setOnClickListener
                    } else {

                        KYCPreferences().OCCUPATION = info.occupation
                        KYCPreferences().COMPANY = info.company
                        KYCPreferences().PHONE_NUMBER = info.phone_number
                        KYCPreferences().EMAIL = info.email_emp

                        findNavController().navigate(R.id.action_to_declaration_kyc_fragment)
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
                        info.occupation = charSequence.toString()
                    }

                    override fun afterTextChanged(editable: Editable) {
                        txtErrorOccupation.visibility = View.GONE
                        etOccupation.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.secondary_text))
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
                        info.company = charSequence.toString()
                    }

                    override fun afterTextChanged(editable: Editable) {
                        txtErrorCompany.visibility = View.GONE
                        etCompany.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.secondary_text))
                    }
                })

                etphoneNumber.addTextChangedListener(object: TextWatcher{
                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                    }

                    override fun onTextChanged(charSequence: CharSequence?, p1: Int, p2: Int, p3: Int) {
                        info.phone_number = charSequence.toString()
                    }

                    override fun afterTextChanged(p0: Editable?) {

                    }

                })

                etemail.addTextChangedListener(object : TextWatcher{
                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                    }

                    override fun onTextChanged(charSequence: CharSequence?, p1: Int, p2: Int, p3: Int) {
                        info.email_emp = charSequence.toString()
                    }

                    override fun afterTextChanged(p0: Editable?) {

                    }

                })
            }

        } catch (error: Exception) {
            // Must be safe
        }
    }
}