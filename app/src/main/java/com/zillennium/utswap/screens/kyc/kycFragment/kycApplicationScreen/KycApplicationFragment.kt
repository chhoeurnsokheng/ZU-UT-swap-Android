package com.zillennium.utswap.screens.kyc.kycFragment.kycApplicationScreen

import androidx.navigation.fragment.findNavController
import com.zillennium.utswap.Datas.GlobalVariable.SessionVariable
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
                    SessionPreferences().SESSION_KYC = false
                    SessionVariable.SESSION_KYC.value = false

                    if(SessionVariable.SESSION_KYC_STATUS.value == 1){
                        SessionPreferences().SESSION_KYC_STATUS = 0
                        SessionVariable.SESSION_KYC_STATUS.value = 0
                    }else{
                        SessionPreferences().SESSION_KYC_STATUS = 2
                        SessionVariable.SESSION_KYC_STATUS.value = 2
                    }

                    activity?.finish()

                }


            }

        } catch (error: Exception) {
            // Must be safe
        }
    }
}