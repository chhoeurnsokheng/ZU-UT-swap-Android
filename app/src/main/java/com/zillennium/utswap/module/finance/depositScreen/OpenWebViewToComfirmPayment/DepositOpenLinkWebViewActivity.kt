package com.zillennium.utswap.module.finance.depositScreen.OpenWebViewToComfirmPayment

import android.content.Context
import android.content.Intent
import com.zillennium.utswap.R
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.DepositOpenLinkWebviewActivityBinding
import com.zillennium.utswap.module.project.projectInfoScreen.ProjectInfoActivity
import com.zillennium.utswap.utils.Constants

/**
 * Created by Sokheng Chhoeurn on 16/8/22.
 * Build in Mac
 */
class DepositOpenLinkWebViewActivity :
    BaseMvpActivity<DepositopenLinkView.View, DepositopenLinkView.Presenter, DepositOpenLinkWebviewActivityBinding>(),DepositopenLinkView.View{

    override val layoutResource: Int = R.layout.deposit_open_link_webview_activity
    override var mPresenter: DepositopenLinkView.Presenter = DepositopenLinkPresenter()

    companion object {
        fun launchDepositOpenLinkWebViewActivity(context: Context, payment_link:String?) {
            val intent = Intent(context, ProjectInfoActivity::class.java)
            intent.putExtra(Constants.Deposit.Payment_Link, payment_link)
            context.startActivity(intent)
        }
    }

    override fun initView() {
        super.initView()


    }
}