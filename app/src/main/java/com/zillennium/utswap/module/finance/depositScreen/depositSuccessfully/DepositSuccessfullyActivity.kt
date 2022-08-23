package com.zillennium.utswap.module.finance.depositScreen.depositSuccessfully

import com.zillennium.utswap.R
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivityDepositSuccessfullyBinding
import com.zillennium.utswap.module.finance.depositScreen.DepositActivity
import com.zillennium.utswap.module.finance.depositScreen.OpenWebViewToComfirmPayment.DepositopenLinkPresenter

/**
 * Created by Sokheng Chhoeurn on 23/8/22.
 * Build in Mac
 */

class DepositSuccessfullyActivity:BaseMvpActivity<DepositSuccessfullyView.View,DepositSuccessfullyView.Presenter,ActivityDepositSuccessfullyBinding>(),DepositSuccessfullyView.View{
    override val layoutResource: Int = R.layout.activity_deposit_successfully
    override var mPresenter: DepositSuccessfullyView.Presenter = DepositSuccessfullyPresenter()


    override fun initView() {
        super.initView()

    }



}