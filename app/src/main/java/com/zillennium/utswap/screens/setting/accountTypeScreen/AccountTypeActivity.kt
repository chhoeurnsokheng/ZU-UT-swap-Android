package com.zillennium.utswap.screens.setting.accountTypeScreen

import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.BaseDialogActivity
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivitySettingAccountTypeBinding
import java.io.IOException

class AccountTypeActivity :
    BaseMvpActivity<AccountTypeView.View, AccountTypeView.Presenter, ActivitySettingAccountTypeBinding>(),
    AccountTypeView.View {

    override var mPresenter: AccountTypeView.Presenter = AccountTypePresenter()
    override val layoutResource: Int = R.layout.activity_setting_account_type

    override fun initView() {
        super.initView()
        try {
            binding.apply {

                btnStandardDetails.setOnClickListener {
                    BaseDialogActivity.showDialog(
                        UTSwapApp.instance,
                        R.layout.dialog_details
                    )
                }

                btnPremiumDetails.setOnClickListener {
                    BaseDialogActivity.showDialog(
                        UTSwapApp.instance,
                        R.layout.dialog_details
                    )
                }

                btnProfessionalDetails.setOnClickListener {
                    BaseDialogActivity.showDialog(
                        UTSwapApp.instance,
                        R.layout.dialog_details
                    )
                }

                btnPremiumUpgrade.setOnClickListener {
                    BaseDialogActivity.showDialog(
                        UTSwapApp.instance,
                        R.layout.dialog_payment
                    )
                }

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