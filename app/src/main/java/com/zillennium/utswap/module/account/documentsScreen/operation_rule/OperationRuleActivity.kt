package com.zillennium.utswap.module.account.documentsScreen.operation_rule

import android.os.Bundle
import com.zillennium.utswap.R
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivityOperationRuleBinding

class OperationRuleActivity : BaseMvpActivity<OperationRuleView.View, OperationRuleView.Presenter, ActivityOperationRuleBinding>(),
    OperationRuleView.View  {

    override var mPresenter: OperationRuleView.Presenter = OperationRulePresenter()
    override val layoutResource: Int = R.layout.activity_operation_rule


    override fun initView() {
        super.initView()
        toolBar()
        binding.apply {
            pdfView.fromAsset("OPERATIONAL RULES- EN.pdf").load()
        }
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
