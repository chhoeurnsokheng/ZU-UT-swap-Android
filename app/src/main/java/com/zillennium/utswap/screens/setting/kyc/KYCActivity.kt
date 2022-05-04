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

//                val view: View = findViewById(R.id.toolbar)

                // Set Passed Back
                ivBack.setOnClickListener {
                    onBackPressed()
                }
            }
            // Code
        } catch (unused: Exception) {
            Log.d("123456789", "hello world")
            // Must be safe
        }
    }
}