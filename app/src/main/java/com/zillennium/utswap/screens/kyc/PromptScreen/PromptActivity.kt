package com.zillennium.utswap.screens.kyc.PromptScreen

import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import com.zillennium.utswap.R
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivitySecurityNewPasswordBinding

class PromptActivity :
    BaseMvpActivity<PromptView.View, PromptView.Presenter, ActivitySecurityNewPasswordBinding>(),
    PromptView.View {

    override var mPresenter: PromptView.Presenter = PromptPresenter()
    override val layoutResource: Int = R.layout.activity_kyc_prompt

    override fun initView() {
        super.initView()
        try {
            binding.apply {

                imgBack.setOnClickListener { finish() }

                btnNext.setOnClickListener {
           }

            }
            // Code
        } catch (error: Exception) {
            // Must be safe
        }
    }
}