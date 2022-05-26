package com.zillennium.utswap.screens.security.securityFragment.PromptScreen

import android.content.Intent
import androidx.navigation.fragment.findNavController
import com.zillennium.utswap.Datas.StoredPreferences.SessionPreferences
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.FragmentSecurityPromptBinding
import com.zillennium.utswap.screens.navbar.projectTab.projectActivity.ProjectMainActivity

class PromptFragment :
    BaseMvpFragment<PromptView.View, PromptView.Presenter, FragmentSecurityPromptBinding>(),
    PromptView.View {

    override var mPresenter: PromptView.Presenter = PromptPresenter()
    override val layoutResource: Int = R.layout.fragment_security_prompt

    override fun initView() {
        super.initView()
        try {
            binding.apply {

                imgBack.setOnClickListener {
                    findNavController().popBackStack()
                }

                btnYes.setOnClickListener {
                    SessionPreferences().SESSION_STATUS = true
                    activity?.finish()
                    val intent = Intent(UTSwapApp.instance.baseContext, ProjectMainActivity::class.java)
                    startActivity(intent)
                }

                btnLatter.setOnClickListener {
                    SessionPreferences().SESSION_STATUS = true
                    activity?.finish()
                }
            }
            // Code
        } catch (error: Exception) {
            // Must be safe
        }
    }
}