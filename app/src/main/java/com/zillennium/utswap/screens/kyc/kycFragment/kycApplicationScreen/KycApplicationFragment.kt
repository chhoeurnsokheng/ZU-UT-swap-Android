package com.zillennium.utswap.screens.kyc.kycFragment.kycApplicationScreen

import androidx.navigation.fragment.findNavController
import com.zillennium.utswap.Datas.StoredPreferences.SessionPreferences
import com.zillennium.utswap.R
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.FragmentKycApplicationBinding

class KycApplicationFragment :
    BaseMvpFragment<KycApplicationView.View, KycApplicationView.Presenter, FragmentKycApplicationBinding>(),
    KycApplicationView.View {

    override var mPresenter: KycApplicationView.Presenter = KycApplicationPresenter()
    override val layoutResource: Int = R.layout.fragment_kyc_application

    override fun initView() {
        super.initView()
        try {
            binding.apply {

                imgBack.setOnClickListener {
                    findNavController().popBackStack()
                }

                btnAccept.setOnClickListener {
                    SessionPreferences().SESSION_KYC = true
                    activity?.finish()

                }


            }

        } catch (error: Exception) {
            // Must be safe
        }
    }
}