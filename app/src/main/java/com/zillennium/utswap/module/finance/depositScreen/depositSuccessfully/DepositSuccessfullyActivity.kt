package com.zillennium.utswap.module.finance.depositScreen.depositSuccessfully

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat
import com.zillennium.utswap.R
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivityDepositSuccessfullyBinding
import com.zillennium.utswap.models.deposite.DataQueryOrderObj
import com.zillennium.utswap.module.finance.depositScreen.DepositActivity
import com.zillennium.utswap.module.finance.depositScreen.OpenWebViewToComfirmPayment.DepositopenLinkPresenter
import com.zillennium.utswap.module.project.projectInfoScreen.ProjectInfoActivity
import com.zillennium.utswap.utils.Constants

/**
 * Created by Sokheng Chhoeurn on 23/8/22.
 * Build in Mac
 */

class DepositSuccessfullyActivity:BaseMvpActivity<DepositSuccessfullyView.View,DepositSuccessfullyView.Presenter,ActivityDepositSuccessfullyBinding>(),DepositSuccessfullyView.View{
    override val layoutResource: Int = R.layout.activity_deposit_successfully
    override var mPresenter: DepositSuccessfullyView.Presenter = DepositSuccessfullyPresenter()

    companion object {
        fun lunchDepositSuccessfullyActivity(context: Context, transaction_id:String?){
            val intent = Intent(context, ProjectInfoActivity::class.java)
            intent.putExtra(Constants.Deposit.TRANSATION_ID, transaction_id)
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

           }
           transactionId.text = "Transaction ID: "+ ""
       }
   }
    private fun toolBar() {
        setSupportActionBar(binding.includeLayout.tb)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.includeLayout.apply {
            tbTitle.setText(R.string.success)
            tbTitle.setTextColor(ContextCompat.getColor(applicationContext, R.color.simple_green))
            tb.setNavigationOnClickListener {
                finish()
            }
        }
    }
}