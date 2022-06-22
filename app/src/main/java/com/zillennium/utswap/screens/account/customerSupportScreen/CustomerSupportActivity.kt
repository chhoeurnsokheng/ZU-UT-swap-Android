package com.zillennium.utswap.screens.account.customerSupportScreen

import android.content.Intent
import android.net.Uri
import com.zillennium.utswap.Datas.StoredPreferences.SystemPreferences
import com.zillennium.utswap.R
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivityAccountCustomerSupportBinding

class CustomerSupportActivity :
    BaseMvpActivity<CustomerSupportView.View, CustomerSupportView.Presenter, ActivityAccountCustomerSupportBinding>(),
    CustomerSupportView.View {

    override var mPresenter: CustomerSupportView.Presenter = CustomerSupportPresenter()
    override val layoutResource: Int = R.layout.activity_account_customer_support

    override fun initView() {
        super.initView()
        try {
            binding.apply {
                imgClose.setOnClickListener {
                    finish()
                }

                btnTelegram.setOnClickListener {
                    startActivity(
                        Intent(
                            "android.intent.action.VIEW",
                            Uri.parse(SystemPreferences().APP_TELEGRAM)
                        )
                    )
                }

                btnCallUs.setOnClickListener {
                    startActivity(
                        Intent(
                            "android.intent.action.VIEW",
                            Uri.parse("tel: 0239999999")
                        )
                    )
                }
            }
            // Code
        } catch (error: Exception) {
            // Must be safe
        }
    }
}