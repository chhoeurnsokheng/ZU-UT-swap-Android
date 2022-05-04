package com.zillennium.utswap.screens.kyc.kycApplicationScreen

import android.content.Intent
import android.view.View
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivityKycApplicationBinding
import com.zillennium.utswap.screens.navbar.navbar.NavbarActivity
import java.io.IOException

class KycApplicationActivity :
    BaseMvpActivity<KycApplicationView.View, KycApplicationView.Presenter, ActivityKycApplicationBinding>(),
    KycApplicationView.View {

    override var mPresenter: KycApplicationView.Presenter = KycApplicationPresenter()
    override val layoutResource: Int = R.layout.activity_kyc_application

    override fun initView() {
        super.initView()
        try {
            binding.apply {

                imgBack.setOnClickListener { finish() }

                btnAccept.setOnClickListener(View.OnClickListener {
                    val intent = Intent(UTSwapApp.instance, NavbarActivity::class.java)
                    startActivity(intent)
                })
            }

        } catch (error: Exception) {
            // Must be safe
        }
    }
}