package com.zillennium.utswap.module.account.documentsScreen.operation_rule

import android.os.Bundle
import com.zillennium.utswap.R
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivityOperationRuleBinding

class OperationRuleActivity : BaseMvpActivity<OperationRuleView.View, OperationRuleView.Presenter, ActivityOperationRuleBinding>(),
    OperationRuleView.View  {

    override var mPresenter: OperationRuleView.Presenter = OperationRulePresenter()
    override val layoutResource: Int = R.layout.activity_operation_rule
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
            tbTitle.setText(R.string.privacy_policy)
            tb.setNavigationOnClickListener {
                finish()
            }
        }
    }
}