package com.zillennium.utswap.screens.kyc.kycApplicationApprovedScreen

import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.R
import com.zillennium.utswap.databinding.ActivityKycApplicationApprovedBinding

class KycApplicationApprovedActivity :
    BaseMvpActivity<KycApplicationApprovedView.View, KycApplicationApprovedView.Presenter, ActivityKycApplicationApprovedBinding>(),
    KycApplicationApprovedView.View {

    override var mPresenter: KycApplicationApprovedView.Presenter = KycApplicationApprovedPresenter()
    override val layoutResource: Int = R.layout.activity_kyc_application_approved

    override fun initView() {
        super.initView()
        try {
            binding.apply {

                imgBack.setOnClickListener { finish() }

            }
        } catch (error: Exception) {
            // Must be safe
        }
    }
}