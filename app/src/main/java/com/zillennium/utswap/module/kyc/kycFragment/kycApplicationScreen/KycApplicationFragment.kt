package com.zillennium.utswap.module.kyc.kycFragment.kycApplicationScreen

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.zillennium.utswap.Datas.StoredPreferences.KYCPreferences
import com.zillennium.utswap.R
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.FragmentKycApplicationBinding
import com.zillennium.utswap.module.kyc.kycActivity.KYCActivity
import com.zillennium.utswap.module.kyc.kycFragment.employmentInfoScreen.EmploymentInfoFragment
import com.zillennium.utswap.module.kyc.kycFragment.idTypeScreen.camera.idCardCameraFragment.IDCardCameraFragment
import com.zillennium.utswap.module.kyc.kycFragment.idVerificationScreen.IDVerificationFragment
import com.zillennium.utswap.screens.navbar.navbar.MainActivity

class KycApplicationFragment :
    BaseMvpFragment<KycApplicationView.View, KycApplicationView.Presenter, FragmentKycApplicationBinding>(),
    KycApplicationView.View {

    override var mPresenter: KycApplicationView.Presenter = KycApplicationPresenter()
    override val layoutResource: Int = R.layout.fragment_kyc_application

    var status_kyc_submit:Boolean? = null
    var status_kyc_approved:Boolean? = null
    private var dataNotification = ""

    override fun initView() {
        super.initView()
        try {

            binding.apply {
//                tvFullName.text = "${IDVerificationFragment.sureName} ${IDVerificationFragment.name}"
                status_kyc_submit = KYCPreferences().status_kyc_submit
                status_kyc_approved = KYCPreferences().status_kyc_approved
                if (activity?.intent?.hasExtra("fromNotification") == true) {
                    dataNotification = activity?.intent?.getStringExtra("fromNotification").toString()
                }
                imgBack.setOnClickListener {
                    if ((activity as KYCActivity).kycStatus == "Pending" || dataNotification == "KYC") {
                        activity?.finish()
                    } else {
                        findNavController().popBackStack()
                    }
                }
                activity?.let {
                    activity?.onBackPressedDispatcher?.addCallback(it, object : OnBackPressedCallback(true){
                        override fun handleOnBackPressed() {
                            activity?.finish()
                        }
                    })
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
                    IDVerificationFragment.apply {
                        provice = ""
                        district = ""
                        commune = ""
                        name = ""
                        sureName = ""
                        gender = ""
                        date = ""
                        houseNumber = ""
                        proCode = ""
                        disCode = ""
                        comCode = ""
                    }
                    IDCardCameraFragment.apply {
                        imageFront = ""
                        imageBack = ""
                    }
                    EmploymentInfoFragment.apply {
                        occupation = ""
                        company = ""
                    }
                    if ((activity as KYCActivity).kycStatus == "Pending") {
                        activity?.finish()
                    } else {
                        startActivity(Intent(requireActivity(),MainActivity::class.java))
                    }
                }
            }

        } catch (error: Exception) {
            // Must be safe
        }
    }

}