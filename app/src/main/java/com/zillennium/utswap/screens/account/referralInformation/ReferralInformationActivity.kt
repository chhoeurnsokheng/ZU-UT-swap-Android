package com.zillennium.utswap.screens.account.referralInformation

import com.zillennium.utswap.R
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivityReferralInformationBinding


class ReferralInformationActivity :
    BaseMvpActivity<ReferralInformationView.View, ReferralInformationView.Presenter, ActivityReferralInformationBinding>(),
    ReferralInformationView.View {

    override var mPresenter: ReferralInformationView.Presenter = ReferralInformationPresenter()
    override val layoutResource: Int = R.layout.activity_referral_information

    override fun initView() {
        super.initView()
        try {
            binding.apply {

                imgClose.setOnClickListener {
                    finish()
                }

            }
            // Code
        } catch (error: Exception) {
            // Must be safe
        }
    }
}