package com.zillennium.utswap.screens.account.documentsScreen

import android.content.Intent
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivityAccountDocumentsBinding
import com.zillennium.utswap.screens.account.documentsScreen.termsConditions.TermsConditionsActivity

class DocumentsActivity :
    BaseMvpActivity<DocumentsView.View, DocumentsView.Presenter, ActivityAccountDocumentsBinding>(),
    DocumentsView.View {

    override var mPresenter: DocumentsView.Presenter = DocumentsPresenter()
    override val layoutResource: Int = R.layout.activity_account_documents

    override fun initView() {
        super.initView()
        try {
            binding.apply {
                imgClose.setOnClickListener {
                    finish()
                }

                txtTermsConditions.setOnClickListener {
                    val intent = Intent(UTSwapApp.instance, TermsConditionsActivity::class.java)
                    startActivity(intent)
                }
            }
            // Code
        } catch (error: Exception) {
            // Must be safe
        }
    }
}