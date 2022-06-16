package com.zillennium.utswap.screens.security.securityActivity.changeLoginPassword

import com.zillennium.utswap.R
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivityAccountChangeLoginPasswordBinding


class ChangeLoginPasswordActivity :
    BaseMvpActivity<ChangeLoginPasswordView.View, ChangeLoginPasswordView.Presenter, ActivityAccountChangeLoginPasswordBinding>(),
    ChangeLoginPasswordView.View {

    override var mPresenter: ChangeLoginPasswordView.Presenter = ChangeLoginPasswordPresenter()
    override val layoutResource: Int = R.layout.activity_account_change_login_password

    override fun initView() {
        super.initView()
        try {

            // Code
        } catch (error: Exception) {
            // Must be safe
        }
    }
}