package com.zillennium.utswap.screens.kyc.kycFragment.idTypeScreen.fragment.nationalID

import android.content.Intent
import androidx.navigation.fragment.findNavController
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.FragmentKycNationalIdBinding
import com.zillennium.utswap.screens.kyc.kycFragment.idTypeScreen.camera.idCardCameraFragment.IDCardCameraFragment

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
                    findNavController().navigate(R.id.action_to_id_card_camera_kyc_fragment)
//                    val intent = Intent(UTSwapApp.instance, IDCardCameraFragment::class.java)
//                    intent.putExtra("sample", R.drawable.ic_national_id_front)
//                    startActivity(intent)
                }

                btnCameraBack.setOnClickListener {
                    findNavController().navigate(R.id.action_to_id_card_camera_kyc_fragment)
//                    val intent = Intent(UTSwapApp.instance, IDCardCameraFragment::class.java)
//                    intent.putExtra("sample", R.drawable.ic_national_id_back)
//                    startActivity(intent)
                }

            }
        } catch (error: Exception) {
            // Must be safe
        }
    }
}