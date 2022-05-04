package com.zillennium.utswap.screens.kyc.idTypeScreen.fragment.passport

import android.content.Intent
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.FragmentKycPassportBinding
import com.zillennium.utswap.screens.kyc.idTypeScreen.camera.idCardCameraActivity.IDCardCameraActivity
import java.io.IOException

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
                    val intent = Intent(UTSwapApp.instance, IDCardCameraActivity::class.java)
                    intent.putExtra("sample", R.drawable.ic_passport_front)
                    startActivity(intent)
                }

            }
        } catch (error: Exception) {
            // Must be safe
        }
    }
}