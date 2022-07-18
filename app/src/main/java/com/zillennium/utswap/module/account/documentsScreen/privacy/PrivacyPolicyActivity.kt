package com.zillennium.utswap.module.account.documentsScreen.privacy

import android.os.Bundle
import com.zillennium.utswap.R
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivityPrivacyPolicyBinding


class PrivacyPolicyActivity: BaseMvpActivity<PrivacyPolicyView.View, PrivacyPolicyView.Presenter, ActivityPrivacyPolicyBinding>(),
    PrivacyPolicyView.View  {
    override val layoutResource: Int = R.layout.activity_privacy_policy
    override var mPresenter: PrivacyPolicyView.Presenter = PrivacyPolicyPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun initView() {
        super.initView()
        toolBar()
    }
    private fun toolBar(){
        setSupportActionBar(binding.includeLayout.tb)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_left)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.includeLayout.apply {
            tbTitle.setText(R.string.operational_rules)
            tb.setNavigationOnClickListener {
                finish()
            }
        }
    }
}