package com.zillennium.utswap.module.kyc.kycFragment.kycApplicationScreen

import android.app.Activity
import android.content.Intent
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.zillennium.utswap.Datas.StoredPreferences.KYCPreferences
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
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

    var status_kyc_submit: Boolean? = null
    var status_kyc_approved: Boolean? = null
    private var dataNotification = ""

    override fun initView() {
        super.initView()
        try {
            binding.apply {
                activity?.let { activity ->
                    (activity as AppCompatActivity?)?.apply {
                        setSupportActionBar(binding.includeLayout.tb)
                        supportActionBar?.setDisplayHomeAsUpEnabled(true)
                        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back_primary)
                        supportActionBar?.setDisplayShowTitleEnabled(false)
                    }
                    includeLayout.apply {
                        tbTitle.setText(R.string.kyc_application)
                        tb.setNavigationOnClickListener {
                            if ((activity as KYCActivity).kycStatus == "Pending" || dataNotification == "KYC Approved" || dataNotification == "KYC Rejected") {
                                activity.setResult(Activity.RESULT_OK)
                                activity.finish()

                            } else {
                                findNavController().popBackStack()
                            }
                        }
                    }
                }

//                tvFullName.text = "${IDVerificationFragment.sureName} ${IDVerificationFragment.name}"
                status_kyc_submit = KYCPreferences().status_kyc_submit
                status_kyc_approved = KYCPreferences().status_kyc_approved
                if (activity?.intent?.hasExtra("fromNotification") == true) {
                    dataNotification =
                        activity?.intent?.getStringExtra("fromNotification").toString()
                }
                activity?.let {
                    activity?.onBackPressedDispatcher?.addCallback(
                        it,
                        object : OnBackPressedCallback(true) {
                            override fun handleOnBackPressed() {
                                activity?.setResult(Activity.RESULT_OK)
                                activity?.finish()
                            }
                        })
                }

                if (dataNotification == "KYC Approved") {
                    imgPending.setImageResource(R.drawable.ic_baseline_check_circle_24)
                    tvPending.text = "Approved"
                    txtMessageKycSuccess.visibility = View.VISIBLE
                    btnAcceptHome.backgroundTintList = ContextCompat.getColorStateList(UTSwapApp.instance, R.color.success)
                    linePending.backgroundTintList = ContextCompat.getColorStateList(UTSwapApp.instance, R.color.success)
                    tvKycDes.text = "Your KYC application has been approved and verified by our team."
                    /*btnAccept.visibility = View.VISIBLE
                    btnAccept.setOnClickListener {
                        startActivity(Intent(requireActivity(), MainActivity::class.java))
                    }*/

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
                        startActivity(
                            Intent(activity, MainActivity::class.java)
                                .putExtra("goHome", "goHome")
                        )
                    }
                }
            }


        } catch (error: Exception) {
            // Must be safe
        }
    }


}