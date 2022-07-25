package com.zillennium.utswap.module.kyc.kycFragment.selfieHoldingScreen

import android.view.View
import androidx.core.net.toUri
import androidx.navigation.fragment.findNavController
import com.zillennium.utswap.Datas.StoredPreferences.KYCPreferences
import com.zillennium.utswap.R
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.FragmentKycSelfieHoldingBinding

class SelfieHoldingFragment :
    BaseMvpFragment<SelfieHoldingView.View, SelfieHoldingView.Presenter, FragmentKycSelfieHoldingBinding>(),
    SelfieHoldingView.View {

    override var mPresenter: SelfieHoldingView.Presenter = SelfieHoldingPresenter()
    override val layoutResource: Int = R.layout.fragment_kyc_selfie_holding

    override fun initView() {
        super.initView()
        try {
            binding.apply {
                ivBack.setOnClickListener {
                    findNavController().popBackStack()
                }
                btnCameraSelfie.setOnClickListener {
                    findNavController().navigate(R.id.action_to_selfie_camera_kyc_fragment)
                }
                btnNext.setOnClickListener {
                    findNavController().navigate(R.id.action_to_employment_kyc_fragment)
                }

                binding.imgDelete.setOnClickListener {
                    imgDelete.visibility = View.GONE
                    btnCameraSelfie.isClickable = true
                    btnCameraSelfie.visibility = View.VISIBLE
                    ivSelfie.setImageResource(R.drawable.sample_selfit)
                    btnNext.isClickable = false
                    btnNext.visibility = View.GONE
                }



            }

        } catch (error: Exception) {
            // Must be safe
        }
    }
    override fun onResume() {
        super.onResume()
        if (!KYCPreferences().SELFIE_HOLDING.isNullOrEmpty()) {
            binding.apply {
                ivSelfie.setImageURI(KYCPreferences().SELFIE_HOLDING?.toUri())

                imgDelete.visibility = View.VISIBLE
                btnCameraSelfie.isClickable = false
                btnCameraSelfie.visibility = View.GONE
                btnNext.isClickable = true
                btnNext.visibility = View.VISIBLE
            }
        }
    }
}