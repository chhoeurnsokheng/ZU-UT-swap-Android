package com.zillennium.utswap.module.account.accountKycPending

import com.zillennium.utswap.R
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivityAccountKycPendingBinding

class AccountKycPendingActivity :
    BaseMvpActivity<AccountKycPendingView.View, AccountKycPendingView.Presenter, ActivityAccountKycPendingBinding>(),
    AccountKycPendingView.View {

    override var mPresenter: AccountKycPendingView.Presenter = AccountKycPendingPresenter()
    override val layoutResource: Int = R.layout.activity_account_kyc_pending

    override fun initView() {
        super.initView()
        try {
            binding.apply {
                btnBack.setOnClickListener {
                    finish()
                }
            }
            // Code
        } catch (error: Exception) {
            // Must be safe
        }
    }
}