package com.zillennium.utswap.module.account.customerSupportScreen

import android.content.Intent
import android.net.Uri
import com.zillennium.utswap.Datas.StoredPreferences.SystemPreferences
import com.zillennium.utswap.R
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivityAccountCustomerSupportBinding
import com.zillennium.utswap.models.customerSupport.CustomerSupport

class CustomerSupportActivity :
    BaseMvpActivity<CustomerSupportView.View, CustomerSupportView.Presenter, ActivityAccountCustomerSupportBinding>(),
    CustomerSupportView.View {

    override var mPresenter: CustomerSupportView.Presenter = CustomerSupportPresenter()
    override val layoutResource: Int = R.layout.activity_account_customer_support

    override fun initView() {
        super.initView()
        try {
            binding.apply {
                mPresenter.getCustomerSupport()
                imgClose.setOnClickListener {
                    finish()
                }
            }
            // Code
        } catch (error: Exception) {
            // Must be safe
        }
    }

    override fun onGetCustomerSupportSuccess(data: CustomerSupport.CustomerSupportData) {
        binding.apply {
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
                        Uri.parse("tel: ${SystemPreferences().APP_PHONE}")
                    )
                )
            }
        }
    }

}