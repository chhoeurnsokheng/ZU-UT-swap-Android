package com.zillennium.utswap.module.kyc.kycFragment.employmentInfoScreen

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.zillennium.utswap.Datas.StoredPreferences.KYCPreferences
import com.zillennium.utswap.Datas.StoredPreferences.SessionPreferences
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.FragmentKycEmploymentInfoBinding
import com.zillennium.utswap.utils.validate


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
            toolBar()
            KYCPreferences().OCCUPATION = ""
            KYCPreferences().COMPANY = ""
            KYCPreferences().EMAIL= ""
            info.company = ""
            info.email_emp = ""
            info.occupation = " "
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

                if(validate().isValidPhoneNumber(SessionPreferences().SESSION_USERNAME.toString())){
                    layEmail.visibility = View.VISIBLE
                }

                btnNext.setOnClickListener {

                    var isHaveError = false
                    if (etOccupation.text.toString().isEmpty()) {
                        txtErrorOccupation.visibility = View.VISIBLE
                        etOccupation.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.red_ee1111))
                        isHaveError = true
                    }
                    if (etCompany.text.toString().isEmpty()) {
                        txtErrorCompany.visibility = View.VISIBLE
                        etCompany.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.red_ee1111))
                        isHaveError = true
                    }

                    if(etemail.text.toString().isEmpty()){
                        txtErrorEmail.visibility = View.VISIBLE
                        etemail.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.red_ee1111))
                        isHaveError = true
//                        if(!validate().isValidEmail(info.email_emp.trim())){
//                            txtErrorEmail.visibility = View.VISIBLE
//                            etemail.backgroundTintList =
//                                ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.red_ee1111))
//                            isHaveError = true
//                        }
                    }

//                    if(info.phone_number.isNotEmpty()){
//                        if(!validate().isValidPhoneNumber(info.phone_number.trim())){
//                            txtErrorPhone.visibility = View.VISIBLE
//                            etphoneNumber.backgroundTintList =
//                                ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.red_ee1111))
//                            isHaveError = true
//                        }
//                    }

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
                        etOccupation.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.secondary_text))
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
                        etCompany.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.secondary_text))
                    }
                })

                etphoneNumber.addTextChangedListener(object: TextWatcher{
                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                        txtErrorPhone.visibility = View.GONE
                        etphoneNumber.backgroundTintList =
                            ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.secondary_text))
                    }

                    override fun onTextChanged(charSequence: CharSequence?, p1: Int, p2: Int, p3: Int) {
                        info.phone_number = charSequence.toString()
                    }

                    override fun afterTextChanged(p0: Editable?) {

                    }

                })

                etemail.addTextChangedListener(object : TextWatcher{
                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                        txtErrorEmail.visibility = View.GONE
                        etemail.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.secondary_text))
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

    private fun toolBar(){
        activity.let {
            binding.apply {
                includeLayout.apply {
                    tbTitle.text = "3/4"
                    cdBack.setOnClickListener {
                        requireActivity().finish()
                    }
                }
            }
        }
    }


}