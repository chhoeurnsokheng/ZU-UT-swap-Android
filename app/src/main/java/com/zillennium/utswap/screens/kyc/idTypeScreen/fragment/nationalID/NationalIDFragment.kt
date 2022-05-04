package com.zillennium.utswap.screens.kyc.idTypeScreen.fragment.nationalID

import android.content.Intent
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.FragmentKycNationalIdBinding
import com.zillennium.utswap.screens.kyc.idTypeScreen.camera.idCardCameraActivity.IDCardCameraActivity
import java.io.IOException

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
                    val intent = Intent(UTSwapApp.instance, IDCardCameraActivity::class.java)
                    intent.putExtra("sample", R.drawable.ic_national_id_front)
                    startActivity(intent)
                }

                btnCameraBack.setOnClickListener {
                    val intent = Intent(UTSwapApp.instance, IDCardCameraActivity::class.java)
                    intent.putExtra("sample", R.drawable.ic_national_id_back)
                    startActivity(intent)
                }
            }
        } catch (error: IOException) {
            // Must be safe
        }
    }
}