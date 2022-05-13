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
                if (KYCPreferences().OCCUPATION.isNullOrEmpty()) {
                    etOccupation.setText(KYCPreferences().OCCUPATION)
                }
                if (KYCPreferences().COMPANY.isNullOrEmpty()) {
                    etCompany.setText(KYCPreferences().COMPANY)
                }
                if (KYCPreferences().PHONE_NUMBER.isNullOrEmpty()) {
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

                imgBack.setOnClickListener {
                    findNavController().popBackStack()
                }

                btnNext.setOnClickListener {

                    var isHaveError = false
                    if (etOccupation.text.toString().isEmpty()) {
                        txtErrorOccupation.visibility = View.VISIBLE
                        etOccupation.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.red))
                        isHaveError = true
                    }
                    if (etCompany.text.toString().isEmpty()) {
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

//                        val intent = Intent(UTSwapApp.instance, DeclarationFragment::class.java)
//                        startActivity(intent)
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
                    }

                    override fun afterTextChanged(editable: Editable) {
                        txtErrorCompany.visibility = View.GONE
                        etCompany.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.secondary_text))
                    }
                })
            }

        } catch (error: Exception) {
            // Must be safe
        }
    }
}