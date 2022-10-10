package com.zillennium.utswap.module.finance.transferScreen

import android.content.Context
import android.content.Intent
import android.view.View
import com.zillennium.utswap.Datas.GlobalVariable.SessionVariable
import com.zillennium.utswap.R
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivityTransferReviewBinding
import com.zillennium.utswap.module.security.securityDialog.FundPasswordDialog
import com.zillennium.utswap.utils.Constants
import com.zillennium.utswap.utils.UtilKt

class TransferActivityReview :
    BaseMvpActivity<TransferActivityReviewView.View, TransferActivityReviewView.Presenter, ActivityTransferReviewBinding>(),
    TransferActivityReviewView.View {
    override val layoutResource: Int = R.layout.activity_transfer_review
    override var mPresenter: TransferActivityReviewView.Presenter =
        TransferActivityReviewPresenter()
    var phoneNumber = ""
    var accountName = ""
    var transferAmount = ""
    var fee = ""
    var total = ""
    private var currencyTransfer = "usd"
   var receiverTransfer =""
    companion object {

        fun lunchTransferActivityReviewActivity(
            context: Context,
            phoneNumber: String?,
            accountName: String,
            transferAmount: String?,
            fee: String?,
            total: String?,receiverTransfer:String?
        ) {
            val intent = Intent(context, TransferActivityReview::class.java)
            intent.putExtra(Constants.Transfer.phoneNumber, phoneNumber)
            intent.putExtra(Constants.Transfer.accountName, accountName)
            intent.putExtra(Constants.Transfer.transferAmount, transferAmount)
            intent.putExtra(Constants.Transfer.fee, fee)
            intent.putExtra(Constants.Transfer.total, total)
            intent.putExtra(Constants.Transfer.receiverTransfer, receiverTransfer)
            context.startActivity(intent)
        }
    }

    override fun initView() {
        super.initView()
        toolBar()
        FundPasswordDialog.backToTransferScreen  = true
        binding.apply {
            if (intent.hasExtra(Constants.Transfer.phoneNumber)) {
                phoneNumber = intent.extras?.getString(Constants.Transfer.phoneNumber).toString()
                txtPhoneNumber.text = phoneNumber
            }
            if (intent.hasExtra(Constants.Transfer.accountName)) {
                accountName = intent.extras?.getString(Constants.Transfer.accountName).toString()
                txtAccountName.text = accountName
            }

            if (intent.hasExtra(Constants.Transfer.transferAmount)) {
                transferAmount= intent.extras?.getString(Constants.Transfer.transferAmount).toString()
                if (transferAmount.isEmpty()){
                    txtAmount.text = "$"+ "0"
                }else{
                    txtAmount.text =     "$ " +  UtilKt().formatValue(transferAmount.toDouble(), "###,###.##")
                }

            }
            if (intent.hasExtra(Constants.Transfer.fee)) {
                fee= intent.extras?.getString(Constants.Transfer.fee).toString()
                if (fee.toDouble() ==0.0){
                    layoutFee.visibility =View.GONE
                }else{
                    txtFee.text =     "$ " +  UtilKt().formatValue(fee.toDouble(), "###,###.##")
                }

            }
            if (intent.hasExtra(Constants.Transfer.total)) {
                total= intent.extras?.getString(Constants.Transfer.total).toString()
                txtTotalBalance.text = "$" + total

                if (total.isEmpty()){
                    txtTotalBalance.text = "$" + "0"

                }else{
                    txtTotalBalance.text ="$" +UtilKt().formatValue(total.toDouble(), "###,###.##")
                }

            }
            if (intent.hasExtra(Constants.Transfer.receiverTransfer)) {
                receiverTransfer = intent.extras?.getString(Constants.Transfer.receiverTransfer).toString()
            }
            btnTransfer.setOnClickListener {

                val fundPasswordDialog: FundPasswordDialog = FundPasswordDialog.transferInstance(transferAmount, currencyTransfer, receiverTransfer, Constants.FundPasswordType.transfer)
                fundPasswordDialog.show(supportFragmentManager, "Fund Password Dialog Transfer")
            }
        }

    }

    private fun toolBar() {
        setSupportActionBar(binding.includeLayout.tb)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back_primary)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.includeLayout.apply {
            tbTitle.setText(R.string.transfer_review)
            tb.setNavigationOnClickListener {
                finish()
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        SessionVariable.successTransfer.value = false
    }
    override fun onBackPressed() {
        super.onBackPressed()
        SessionVariable.successTransfer.value = false

    }
}