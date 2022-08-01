package com.zillennium.utswap.module.kyc.kycFragment.idTypeScreen.fragment.passport

import android.view.View
import androidx.core.net.toUri
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.zillennium.utswap.Datas.StoredPreferences.KYCPreferences
import com.zillennium.utswap.R
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.FragmentKycPassportBinding
import com.zillennium.utswap.module.kyc.kycFragment.idTypeScreen.IdTypeFragment

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
                    val bundle = bundleOf("title" to "passport_front")
                    findNavController().navigate(R.id.action_to_id_card_camera_kyc_fragment, bundle)
                }

                imgDeleteFront.setOnClickListener {
                    imgPassport.setImageResource(R.drawable.ic_passport_front)
                    imgLogoCamera.visibility = View.VISIBLE

                    imgDeleteFront.visibility = View.GONE
                    btnPassport.isClickable = true
                    KYCPreferences().removeValue("PASSPORT_FRONT")
                    (parentFragment as IdTypeFragment).checkValidation()
                    val bundle = bundleOf("title" to "passport_front")
                    findNavController().navigate(R.id.action_to_id_card_camera_kyc_fragment, bundle)
                }

            }
        } catch (error: Exception) {
            // Must be safe
        }
    }

    override fun onResume() {
        super.onResume()
        binding.apply {
            if (!KYCPreferences().PASSPORT_FRONT.isNullOrEmpty()) {
                imgPassport.setImageURI(KYCPreferences().PASSPORT_FRONT?.toUri())
                btnPassport.isClickable = false
                imgLogoCamera.visibility = View.GONE
              //  imgLogoCorrect.visibility = View.VISIBLE
                imgDeleteFront.visibility = View.VISIBLE
            }else{
                imgPassport.setImageResource(R.drawable.ic_passport_front)
                imgLogoCamera.visibility = View.VISIBLE
              //  imgLogoCorrect.visibility = View.GONE
                imgDeleteFront.visibility = View.GONE
                btnPassport.isClickable = true
            }
        }
    }
}