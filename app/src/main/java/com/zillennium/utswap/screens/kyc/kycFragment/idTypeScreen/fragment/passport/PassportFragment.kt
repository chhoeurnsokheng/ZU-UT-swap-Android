package com.zillennium.utswap.screens.kyc.kycFragment.idTypeScreen.fragment.passport

import android.content.Intent
import androidx.navigation.fragment.findNavController
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.FragmentKycPassportBinding
import com.zillennium.utswap.screens.kyc.kycFragment.idTypeScreen.camera.idCardCameraFragment.IDCardCameraFragment

class PassportFragment :
    BaseMvpFragment<PassportView.View, PassportView.Presenter, FragmentKycPassportBinding>(),
    PassportView.View {

    override var mPresenter: PassportView.Presenter = PassportPresenter()
    override val layoutResource: Int = R.layout.fragment_kyc_passport

    override fun initView() {
        super.initView()
        try {
            binding.apply {

                btnPassport.setOnClickListener {
                    findNavController().navigate(R.id.action_to_selfie_camera_kyc_fragment)
                }

            }
        } catch (error: Exception) {
            // Must be safe
        }
    }
}