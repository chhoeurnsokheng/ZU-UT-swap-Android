package com.zillennium.utswap.module.finance.depositScreen.depositSuccessfully

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivityDepositSuccessfullyBinding
import com.zillennium.utswap.models.deposite.DataQueryOrderObj
import com.zillennium.utswap.models.deposite.DepositObj
import com.zillennium.utswap.module.finance.balanceScreen.FinanceBalanceActivity
import com.zillennium.utswap.module.finance.depositScreen.DepositActivity
import com.zillennium.utswap.module.finance.depositScreen.OpenWebViewToComfirmPayment.DepositopenLinkPresenter
import com.zillennium.utswap.module.project.projectInfoScreen.ProjectInfoActivity
import com.zillennium.utswap.utils.Constants
import com.zillennium.utswap.utils.Util

/**
 * Created by Sokheng Chhoeurn on 23/8/22.
 * Build in Mac
 */

class DepositSuccessfullyActivity:BaseMvpActivity<DepositSuccessfullyView.View,DepositSuccessfullyView.Presenter,ActivityDepositSuccessfullyBinding>(),DepositSuccessfullyView.View{
    override val layoutResource: Int = R.layout.activity_deposit_successfully
    override var mPresenter: DepositSuccessfullyView.Presenter = DepositSuccessfullyPresenter()
    var transaction_id = ""
    var totalBalance = ""
    companion object {
        var backToHome = true
        fun lunchDepositSuccessfullyActivity(context: Context, transaction_id:String?,totalBalance:String?){
            val intent = Intent(context, DepositSuccessfullyActivity::class.java)
            intent.putExtra(Constants.Deposit.TRANSATION_ID, transaction_id)
            intent.putExtra(Constants.Deposit.TOTAL_BALANCE, totalBalance)
            context.startActivity(intent)
        }
    }

    override fun initView() {
        super.initView()
        toolBar()
        handleUI()
    }

   private fun handleUI(){
       binding.apply {
           btnDone.setOnClickListener {
               backToHome
            startActivity(Intent(this@DepositSuccessfullyActivity, FinanceBalanceActivity::class.java))
           }
           if (intent.hasExtra(Constants.Deposit.TRANSATION_ID)) {
               transaction_id = intent.getStringExtra(Constants.Deposit.TRANSATION_ID).toString()
               transactionId.text = "Transaction ID: "+ "$transaction_id"
           }
           if (intent.hasExtra(Constants.Deposit.TOTAL_BALANCE)) {
               totalBalance = intent.getStringExtra(Constants.Deposit.TOTAL_BALANCE).toString()
               txtTotalBalance.text =  Util().getHtmlText("#888888", "You have made a deposit $${totalBalance} with your"
                       , "Bank Account", "#1B2266")

           }
       }
   }

    private fun toolBar() {

        binding.includeLayout.apply {
            tbTitle.setText(R.string.success)
            tbTitle.setTextColor(ContextCompat.getColor(applicationContext, R.color.simple_green))
            tb.setNavigationOnClickListener {
                finish()
            }
        }
    }
}