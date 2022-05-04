package com.zillennium.utswap.screens.setting.fundPasswordScreen

import android.content.Intent
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivitySettingCreditCardBinding
import com.zillennium.utswap.databinding.ActivitySettingFundPasswordBinding
import com.zillennium.utswap.screens.setting.addCardScreen.AddCardActivity
import java.io.IOException

class FundPasswordActivity :
    BaseMvpActivity<FundPasswordView.View, FundPasswordView.Presenter, ActivitySettingFundPasswordBinding>(),
    FundPasswordView.View {

    override var mPresenter: FundPasswordView.Presenter = FundPasswordPresenter()
    override val layoutResource: Int = R.layout.activity_setting_fund_password

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