package com.zillennium.utswap.module.kyc.kycFragment.idTypeScreen.fragment.nationalID

import android.view.View
import androidx.core.net.toUri
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.zillennium.utswap.Datas.StoredPreferences.KYCPreferences
import com.zillennium.utswap.R
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.FragmentKycNationalIdBinding
import com.zillennium.utswap.module.kyc.kycFragment.idTypeScreen.IdTypeFragment
import com.zillennium.utswap.module.kyc.kycFragment.idTypeScreen.camera.idCardCameraFragment.IDCardCameraFragment


class NationalIDFragment :
    BaseMvpFragment<NationalIDView.View, NationalIDView.Presenter, FragmentKycNationalIdBinding>(),
    NationalIDView.View {

    override var mPresenter: NationalIDView.Presenter = NationalIDPresenter()
    override val layoutResource: Int = R.layout.fragment_kyc_national_id

    override fun initView() {
        super.initView()
        try {
            binding.apply {

                btnCameraFront.setOnClickListener {
                    val bundle = bundleOf("title" to "id_card_front")
                    findNavController().navigate(R.id.action_to_id_card_camera_kyc_fragment, bundle)
                }

                btnCameraBack.setOnClickListener {
                    val bundle = bundleOf("title" to "id_card_back")
                    findNavController().navigate(R.id.action_to_id_card_camera_kyc_fragment, bundle)
                }

                imgDeleteFront.setOnClickListener {
                    KYCPreferences().removeValue("NATIONAL_ID_FRONT")
                    imgNationalFront.setImageResource(R.drawable.ic_national_id_front)
                    imgLogoCameraFront.visibility = View.VISIBLE
                    imgNationalFronGone.visibility =View.VISIBLE
                    imgDeleteFront.visibility = View.GONE
                    btnCameraFront.isClickable = true
                    (parentFragment as IdTypeFragment).checkValidation()
                }

                imgDeleteBack.setOnClickListener {
                    KYCPreferences().removeValue("NATIONAL_ID_BACK")
                    imgNationalBack.setImageResource(R.drawable.ic_national_id_back)
                    imgLogoCameraBack.visibility = View.VISIBLE
                    imgNationalBackGone.visibility =View.VISIBLE
                    imgDeleteBack.visibility = View.GONE
                    btnCameraBack.isClickable = true
                    (parentFragment as IdTypeFragment).checkValidation()
                }

            }
        } catch (error: Exception) {
            // Must be safe
        }
    }

    override fun onResume() {
        super.onResume()
        binding.apply {
            if (IDCardCameraFragment.imageFront.isNotEmpty()) {
                Glide.with(imgNationalFront)
                    .asBitmap()
                    .load(IDCardCameraFragment.imageFront)
                    .into(imgNationalFront)
//                imgNationalFront.setImageURI(KYCPreferences().NATIONAL_ID_FRONT?.toUri())
                 imgNationalFronGone.visibility =View.GONE
                btnCameraFront.isClickable = false
                imgLogoCameraFront.visibility = View.GONE
                imgDeleteFront.visibility = View.VISIBLE
            } else {
                imgNationalFront.setImageResource(R.drawable.ic_national_id_front)
                imgLogoCameraFront.visibility = View.VISIBLE
                imgDeleteFront.visibility = View.GONE
                btnCameraFront.isClickable = true
            }


            if (IDCardCameraFragment.imageBack.isNotEmpty()) {
                Glide.with(imgNationalBack)
                    .asBitmap()
                    .load(IDCardCameraFragment.imageBack)
                    .into(imgNationalBack)
//                imgNationalBack.setImageURI(KYCPreferences().NATIONAL_ID_BACK?.toUri())
                imgNationalBackGone.visibility =View.GONE
                btnCameraBack.isClickable = false
                imgLogoCameraBack.visibility = View.GONE
                imgDeleteBack.visibility = View.VISIBLE
            } else {
                imgNationalBack.setImageResource(R.drawable.ic_national_id_back)
                imgLogoCameraBack.visibility = View.VISIBLE
                imgDeleteBack.visibility = View.GONE
                btnCameraBack.isClickable = true
            }
        }

    }

}
