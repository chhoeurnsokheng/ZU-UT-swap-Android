package com.zillennium.utswap.screens.finance.withdrawActivity

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.android.material.internal.ContextUtils.getActivity
import com.zillennium.utswap.Datas.GlobalVariable.SessionVariable
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivityFinanceWithdrawBinding
import com.zillennium.utswap.models.FinanceBankModel
import com.zillennium.utswap.models.WithdrawAddbankModel
import com.zillennium.utswap.screens.finance.depositActivity.depositBottomSheet.BottomSheetFinanceDepositPayment

import com.zillennium.utswap.screens.finance.withdrawActivity.addBank.AddBankActivity
import com.zillennium.utswap.screens.finance.withdrawActivity.withdrawBottomSheet.BottomSheetFinanceWithdraw
import com.zillennium.utswap.screens.security.securityDialog.FundPasswordDialog

class WithdrawActivity :
    BaseMvpActivity<WithdrawView.View, WithdrawView.Presenter, ActivityFinanceWithdrawBinding>(),
    WithdrawView.View {
    private var SECOND_ACTIVITY_REQUEST_CODE = 0
    override var mPresenter: WithdrawView.Presenter = WithdrawPresenter()
    override val layoutResource: Int = R.layout.activity_finance_withdraw


    override fun initView() {
        super.initView()
        try {
            binding.apply {
                nextBtnFinace.isEnabled = false
                addBankAccount.setOnClickListener {
                    val intent = Intent(this@WithdrawActivity, AddBankActivity::class.java)
                    startActivity(intent)
                }


                SessionVariable.SESSION_BANK.observe(this@WithdrawActivity) {
                    //Get data from bottom sheet add bank screen

                    val value = SessionVariable.SESSION_BANK.value?.get(0)
                    if (tvUser.toString() != "" && tvTitleBank.toString() != "") {
                        tvUser.text = value?.bank_fullname
                        tvTitleBank.text = value?.bank_name

                        when(value?.bank_name){
                            "ABA Pay" -> {
                               ivBank.setImageDrawable(resources.getDrawable(R.drawable.aba_bank))
                            }
                            "Acleda Bank" -> {
                                ivBank.setImageDrawable(resources.getDrawable(R.drawable.acleda))
                            }
                            "Sathapana" -> {
                                ivBank.setImageDrawable(resources.getDrawable(R.drawable.sathapana))
                            }
                        }

                        addBankAccount.visibility = View.GONE
                        receiveAdd.visibility = View.VISIBLE
                        abaUserInfo.visibility = View.VISIBLE
                    }
                }

                etMountPayment.addTextChangedListener(object : TextWatcher{
                    override fun beforeTextChanged(
                        s: CharSequence?,
                        start: Int,
                        count: Int,
                        after: Int
                    ) {

                    }

                    override fun onTextChanged(
                        s: CharSequence?,
                        start: Int,
                        before: Int,
                        count: Int
                    ) {
                        nextBtnFinace.isEnabled = true
                        layTransactions.visibility = View.VISIBLE

//                        nextBtnFinace.setOnClickListener{
//                            val intent = Intent(this@WithdrawActivity, FundPasswordDialog::class.java)
//                            startActivity(intent)
//                        }
                    }

                    override fun afterTextChanged(s: Editable?) {

                    }
                })

            }

        } catch (error: Exception) {
            // Must be safe
        }
    }


//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        binding.apply {
//            val message = data!!.getStringExtra("key")
//            if (requestCode === SECOND_ACTIVITY_REQUEST_CODE) {
//                if (resultCode === RESULT_OK) {
//                    val financeWithdraw = BottomSheetFinanceWithdraw.newInstance(message.toString())
//                    financeWithdraw.show(this@WithdrawActivity.supportFragmentManager, "withdraw Dialog")
//
//                }
//            }
//            super.onActivityResult(requestCode, resultCode, data)
//        }
//    }
}