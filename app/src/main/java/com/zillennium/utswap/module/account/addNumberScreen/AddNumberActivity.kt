package com.zillennium.utswap.module.account.addNumberScreen

import com.zillennium.utswap.R
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivityAccountAddNumberBinding


class AddNumberActivity :
    BaseMvpActivity<AddNumberView.View, AddNumberView.Presenter, ActivityAccountAddNumberBinding>(),
    AddNumberView.View {

    override var mPresenter: AddNumberView.Presenter = AddNumberPresenter()
    override val layoutResource: Int = R.layout.activity_account_add_number

    override fun initView() {
        super.initView()
        try {
            // Code
        } catch (error: Exception) {
            // Must be safe
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}