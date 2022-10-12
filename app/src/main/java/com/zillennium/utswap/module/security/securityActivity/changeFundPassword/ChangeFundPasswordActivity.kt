package com.zillennium.utswap.module.security.securityActivity.changeFundPassword

import androidx.navigation.findNavController
import com.zillennium.utswap.R
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivityAccountChangeFundPasswordBinding


class ChangeFundPasswordActivity :
    BaseMvpActivity<ChangeFundPasswordView.View, ChangeFundPasswordView.Presenter, ActivityAccountChangeFundPasswordBinding>(),
    ChangeFundPasswordView.View {

    override var mPresenter: ChangeFundPasswordView.Presenter = ChangeFundPasswordPresenter()
    override val layoutResource: Int = R.layout.activity_account_change_fund_password

    override fun initView() {
        super.initView()
        try {

        } catch (error: Exception) {
            // Must be safe
        }
    }

    override fun onBackPressed() {
        if (findNavController(R.id.kyc_nav_host_fragment).currentDestination?.id == R.id.ChangeFundPasswordSuccess){

        } else {
            super.onBackPressed()

        }
    }

}