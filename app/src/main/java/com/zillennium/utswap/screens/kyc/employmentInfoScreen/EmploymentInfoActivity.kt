package com.zillennium.utswap.screens.kyc.employmentInfoScreen

import android.annotation.SuppressLint
import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivityKycEmploymentInfoBinding
import com.zillennium.utswap.screens.kyc.addressInfoScreen.AddressInfoActivity
import java.io.IOException

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

                imgBack.setOnClickListener { finish() }

                btnNext.setOnClickListener {
                    var isHaveError = false
                    if (etOccupation.text.toString().isEmpty()) {
                        txtErrorOccupation.visibility = View.VISIBLE
                        etOccupation.background =
                            getDrawable(R.drawable.outline_edittext_error_normal)
                        isHaveError = true
                    }
                    if (etCompany.text.toString().isEmpty()) {
                        txtErrorCompany.visibility = View.VISIBLE
                        etCompany.background = getDrawable(R.drawable.outline_edittext_error_normal)
                        isHaveError = true
                    }
                    if (isHaveError) {
                        return@setOnClickListener
                    } else {
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

                    @SuppressLint("UseCompatLoadingForDrawables")
                    override fun onTextChanged(
                        charSequence: CharSequence,
                        i: Int,
                        i1: Int,
                        i2: Int
                    ) {
                        txtErrorOccupation.visibility = View.GONE
                        etOccupation.background =
                            getDrawable(R.drawable.outline_edittext_change_color_focus)
                    }

                    override fun afterTextChanged(editable: Editable) {}
                })

                etCompany.addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(
                        charSequence: CharSequence,
                        i: Int,
                        i1: Int,
                        i2: Int
                    ) {
                    }

                    @SuppressLint("UseCompatLoadingForDrawables")
                    override fun onTextChanged(
                        charSequence: CharSequence,
                        i: Int,
                        i1: Int,
                        i2: Int
                    ) {
                        txtErrorCompany.visibility = View.GONE
                        etCompany.background =
                            getDrawable(R.drawable.outline_edittext_change_color_focus)
                    }

                    override fun afterTextChanged(editable: Editable) {}
                })
            }

        } catch (error: IOException) {
            // Must be safe
        }
    }
}