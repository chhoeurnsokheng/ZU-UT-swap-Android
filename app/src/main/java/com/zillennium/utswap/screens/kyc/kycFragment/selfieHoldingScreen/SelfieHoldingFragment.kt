package com.zillennium.utswap.screens.kyc.kycFragment.selfieHoldingScreen

import android.content.Intent
import androidx.navigation.fragment.findNavController
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.FragmentKycSelfieHoldingBinding
import com.zillennium.utswap.screens.kyc.kycFragment.employmentInfoScreen.EmploymentInfoFragment
import com.zillennium.utswap.screens.kyc.kycFragment.idTypeScreen.camera.selfieCameraFragment.SelfieCameraFragment

class SelfieHoldingFragment :
    BaseMvpFragment<SelfieHoldingView.View, SelfieHoldingView.Presenter, FragmentKycSelfieHoldingBinding>(),
    SelfieHoldingView.View {

    override var mPresenter: SelfieHoldingView.Presenter = SelfieHoldingPresenter()
    override val layoutResource: Int = R.layout.fragment_kyc_selfie_holding

    override fun initView() {
        super.initView()
        try {
            binding.apply {

                btnCameraSelfie.setOnClickListener {
                    val intent = Intent(UTSwapApp.instance, SelfieCameraFragment::class.java)
                    startActivity(intent)
                }

                btnNext.setOnClickListener {
//                    val intent = Intent(UTSwapApp.instance, EmploymentInfoFragment::class.java)
//                    startActivity(intent)
                    findNavController().navigate(R.id.action_to_employment_kyc_fragment)
                }

                ivBack.setOnClickListener {
                    findNavController().popBackStack()
                }
            }

        } catch (error: Exception) {
            // Must be safe
        }
    }
}