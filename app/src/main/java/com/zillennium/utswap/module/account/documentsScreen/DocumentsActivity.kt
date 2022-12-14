package com.zillennium.utswap.module.account.documentsScreen

import android.content.Intent
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivityAccountDocumentsBinding
import com.zillennium.utswap.module.account.documentsScreen.contract.ContractActivity
import com.zillennium.utswap.module.account.documentsScreen.operation_rule.OperationRuleActivity
import com.zillennium.utswap.module.account.documentsScreen.privacy.PrivacyPolicyActivity
import com.zillennium.utswap.module.account.documentsScreen.termsConditions.TermsConditionsActivity

class DocumentsActivity :
    BaseMvpActivity<DocumentsView.View, DocumentsView.Presenter, ActivityAccountDocumentsBinding>(),
    DocumentsView.View {

    override var mPresenter: DocumentsView.Presenter = DocumentsPresenter()
    override val layoutResource: Int = R.layout.activity_account_documents

    override fun initView() {
        super.initView()
        try {

            toolBar()

            binding.apply {
                txtTermsConditions.setOnClickListener {
                    val intent = Intent(UTSwapApp.instance, TermsConditionsActivity::class.java)
                    startActivity(intent)
                }
                txtContract.setOnClickListener {
                    startActivity(Intent(this@DocumentsActivity,ContractActivity::class.java))
                }
                txtOperationRule.setOnClickListener {
                    startActivity(Intent(this@DocumentsActivity,OperationRuleActivity::class.java))
                }
                txtPrivacyPolicy.setOnClickListener {
                    startActivity(Intent(this@DocumentsActivity,PrivacyPolicyActivity::class.java))
                }
                
            }
            // Code
        } catch (error: Exception) {
            // Must be safe
        }
    }



    private fun toolBar() {
        setSupportActionBar(binding.includeLayout.tb)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_left)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.includeLayout.apply {
            tbTitle.setText(R.string.documents)
            tb.setNavigationOnClickListener {
                finish()
            }
        }
    }


}