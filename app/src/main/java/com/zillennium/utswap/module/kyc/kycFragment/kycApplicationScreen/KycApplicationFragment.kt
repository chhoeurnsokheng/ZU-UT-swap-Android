package com.zillennium.utswap.module.kyc.kycFragment.kycApplicationScreen

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.zillennium.utswap.Datas.StoredPreferences.KYCPreferences
import com.zillennium.utswap.R
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.FragmentKycApplicationBinding
import com.zillennium.utswap.screens.navbar.navbar.MainActivity

class KycApplicationFragment :
    BaseMvpFragment<KycApplicationView.View, KycApplicationView.Presenter, FragmentKycApplicationBinding>(),
    KycApplicationView.View {

    override var mPresenter: KycApplicationView.Presenter = KycApplicationPresenter()
    override val layoutResource: Int = R.layout.fragment_kyc_application

    var status_kyc_submit:Boolean? = null
    var status_kyc_approved:Boolean? = null

    override fun initView() {
        super.initView()
        try {

            binding.apply {
                status_kyc_submit = KYCPreferences().status_kyc_submit
                status_kyc_approved = KYCPreferences().status_kyc_approved
                imgBack.setOnClickListener {
                    findNavController().popBackStack()
                }
                if (status_kyc_approved==true){
                    imgPending.setImageResource(R.drawable.ic_baseline_check_circle_24)
                    txtPending.text = "Approved"
                    txtMessageKycSuccess.visibility = View.VISIBLE
                    btnAccept.visibility =View.VISIBLE
                    btnAccept.setOnClickListener {
                        startActivity(Intent(requireActivity(),MainActivity::class.java))
                    }

                }
//                if (status_kyc_submit ==true){}
                btnAcceptHome.setOnClickListener {
                    startActivity(Intent(requireActivity(),MainActivity::class.java))
                }
            }

        } catch (error: Exception) {
            // Must be safe
        }
    }

}