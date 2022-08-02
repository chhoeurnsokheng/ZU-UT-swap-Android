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

    companion object {
        var telegram = SystemPreferences().APP_TELEGRAM
        var telephone = SystemPreferences().APP_PHONE
    }

    override fun initView() {
        super.initView()
        mPresenter.getCustomerSupport()
        binding.apply {
            imgClose.setOnClickListener {
                finish()
            }

            btnTelegram.setOnClickListener {
                startActivity(
                    Intent(
                        "android.intent.action.VIEW",
                        Uri.parse(telegram)
                    )
                )
            }

            btnCallUs.setOnClickListener {
                startActivity(
                    Intent(
                        "android.intent.action.VIEW",
                        Uri.parse("tel: $telephone")
                    )
                )
            }
        }
    }

    override fun onGetCustomerSupportSuccess(data: CustomerSupport.CustomerSupportData) {

        if(data.contact_cellphone!!.isNotEmpty()){
            telephone = data.contact_cellphone
            SystemPreferences().APP_PHONE = data.contact_cellphone
        }

        if(data.contact_telegram!!.isNotEmpty()){
            telegram = data.contact_telegram
            SystemPreferences().APP_TELEGRAM = data.contact_telegram
        }
    }

}