package com.zillennium.utswap.screens.kyc.PromptScreen

import android.content.Intent
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivityKycPromptBinding
import com.zillennium.utswap.databinding.ActivitySecurityNewPasswordBinding
import com.zillennium.utswap.screens.kyc.idTypeScreen.IdTypeActivity
import com.zillennium.utswap.screens.kyc.idTypeScreen.camera.idCardCameraActivity.IDCardCameraActivity
import com.zillennium.utswap.screens.navbar.navbar.NavbarActivity

class PromptActivity :
    BaseMvpActivity<PromptView.View, PromptView.Presenter, ActivityKycPromptBinding>(),
    PromptView.View {

    override var mPresenter: PromptView.Presenter = PromptPresenter()
    override val layoutResource: Int = R.layout.activity_kyc_prompt

    override fun initView() {
        super.initView()
        try {
            binding.apply {

                imgBack.setOnClickListener { finish() }

                btnYes.setOnClickListener {
                    val intent = Intent(UTSwapApp.instance, IdTypeActivity::class.java)
                    startActivity(intent)
                }

                btnLatter.setOnClickListener {
                    val intent = Intent(UTSwapApp.instance, NavbarActivity::class.java)
                    startActivity(intent)
                }
            }
            // Code
        } catch (error: Exception) {
            // Must be safe
        }
    }
}