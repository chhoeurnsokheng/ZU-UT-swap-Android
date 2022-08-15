package com.zillennium.utswap.module.account.documentsScreen.contract

import android.os.Bundle
import com.zillennium.utswap.R
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivityContractBinding

class ContractActivity :  BaseMvpActivity<ContractView.View, ContractView.Presenter, ActivityContractBinding>(),
    ContractView.View {
    override val layoutResource: Int = R.layout.activity_contract
    override var mPresenter: ContractView.Presenter = ContractPresenter()

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
            tbTitle.setText(R.string.contract)
            tb.setNavigationOnClickListener {
                finish()
            }
        }
    }
}