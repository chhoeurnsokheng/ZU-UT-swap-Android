package com.zillennium.utswap.screens.setting.profileQRCodeScreen

import com.zillennium.utswap.R
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivitySettingFundPasswordBinding
import com.zillennium.utswap.databinding.ActivitySettingProfileQrcodeBinding
import java.io.IOException

class ProfileQRCodeActivity :
    BaseMvpActivity<ProfileQRCodeView.View, ProfileQRCodeView.Presenter, ActivitySettingProfileQrcodeBinding>(),
    ProfileQRCodeView.View {

    override var mPresenter: ProfileQRCodeView.Presenter = ProfileQRCodePresenter()
    override val layoutResource: Int = R.layout.activity_setting_profile_qrcode

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
        } catch (error: IOException) {
            // Must be safe
        }
    }
}