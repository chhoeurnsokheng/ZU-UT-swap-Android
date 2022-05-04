package com.zillennium.utswap.screens.kyc.selfieHoldingScreen

import android.content.Intent
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivityKycSelfieHoldingBinding
import com.zillennium.utswap.screens.kyc.employmentInfoScreen.EmploymentInfoActivity
import com.zillennium.utswap.screens.kyc.idTypeScreen.camera.selfieCameraActivity.SelfieCameraActivity
import java.io.IOException

class SelfieHoldingActivity :
    BaseMvpActivity<SelfieHoldingView.View, SelfieHoldingView.Presenter, ActivityKycSelfieHoldingBinding>(),
    SelfieHoldingView.View {

    override var mPresenter: SelfieHoldingView.Presenter = SelfieHoldingPresenter()
    override val layoutResource: Int = R.layout.activity_kyc_selfie_holding

    override fun initView() {
        super.initView()
        try {
            binding.apply {

                btnCameraSelfie.setOnClickListener {
                    val intent = Intent(UTSwapApp.instance, SelfieCameraActivity::class.java)
                    startActivity(intent)
                }

                btnNext.setOnClickListener {
                    val intent = Intent(UTSwapApp.instance, EmploymentInfoActivity::class.java)
                    startActivity(intent)
                }

                ivBack.setOnClickListener {
                    onBackPressed()
                }
            }

        } catch (error: Exception) {
            // Must be safe
        }
    }
}