package com.zillennium.utswap.screens.setting.kyc

import android.util.Log
import android.view.View
import com.zillennium.utswap.R
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivitySettingKycBinding


class KYCActivity :
    BaseMvpActivity<KYCView.View, KYCView.Presenter, ActivitySettingKycBinding>(),
    KYCView.View {

    override var mPresenter: KYCView.Presenter = KYCPresenter()
    override val layoutResource: Int = R.layout.activity_setting_kyc

    override fun initView() {
        super.initView()
        try {
            binding.apply {

                // Set Passed Back
                ivBack.setOnClickListener {
                    onBackPressed()
                }
            }
            // Code
        } catch (error: Exception) {
            // Must be safe
        }
    }
}