package com.zillennium.utswap.screens.wallet.MyWallet

import android.annotation.SuppressLint
import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivityWalletTransactionBinding
import com.zillennium.utswap.screens.wallet.MyWallet.adapter.MyWalletGroupAdapter
import com.zillennium.utswap.screens.wallet.MyWallet.dialog.ExportTransactionBottomSheetDialog
import com.zillennium.utswap.screens.wallet.MyWallet.dialog.FilterBottomSheetDialog
import com.zillennium.utswap.screens.wallet.MyWallet.model.MyWalletGroup
import com.zillennium.utswap.screens.wallet.MyWallet.model.MyWalletItem
import com.zillennium.utswap.screens.wallet.depositScreen.DepositActivity
import com.zillennium.utswap.screens.wallet.transferScreen.TransferActivity
import com.zillennium.utswap.screens.wallet.withdrawalScreen.WithdrawalActivity
import java.io.IOException

class MyWalletActivity :
    BaseMvpActivity<MyWalletView.View, MyWalletView.Presenter, ActivityWalletTransactionBinding>(),
    MyWalletView.View {

    override var mPresenter: MyWalletView.Presenter = MyWalletPresenter()
    override val layoutResource: Int = R.layout.activity_wallet_transaction

    private var count = 2

    override fun initView() {
        super.initView()
        try {
            binding.apply {

                imgBack.setOnClickListener { finish() }

                imgFilter.setOnClickListener {
                    val a = FilterBottomSheetDialog.newInstance()
                    a.show(supportFragmentManager, "Open_fragment")
                }

                imgExport.setOnClickListener {
                    val a =
                        ExportTransactionBottomSheetDialog.newInstance()
                    a.show(supportFragmentManager, "Open_fragment")
                }

                imgWithdrawal.setOnClickListener {
                    val intent = Intent(UTSwapApp.instance, WithdrawalActivity::class.java)
                    startActivity(intent)
                }

                imgTransfer.setOnClickListener {
                    val intent = Intent(UTSwapApp.instance, TransferActivity::class.java)
                    startActivity(intent)
                }

                imgDeposit.setOnClickListener {
                    val intent = Intent(UTSwapApp.instance, DepositActivity::class.java)
                    startActivity(intent)
                }

                imgDropDown.setOnClickListener { view: View? ->
                    openDropDown(count)
                    count++
                }

            }
            // Code
        } catch (error: IOException) {
            // Must be safe
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    fun openDropDown(count: Int) {
        binding.apply {
            if (count % 2 == 0) {
                linearAllWallet.visibility = View.GONE
                imgDropDown.setImageDrawable(getDrawable(R.drawable.ic_baseline_keyboard_arrow_down_24))
            } else {
                linearAllWallet.visibility = View.VISIBLE
                imgDropDown.setImageDrawable(getDrawable(R.drawable.ic_baseline_keyboard_arrow_up_24))
            }
        }
    }

    fun setAdapter() {
        binding.apply {
            val arrayDay = ArrayList<String>()
            var dayCurrent = ""
            val dataGroupList = ArrayList<MyWalletGroup>()
            var dataItemList = ArrayList<MyWalletItem?>()
            val dataBalance = DataMyWallet.LIST_OF_BALANCE_HISTORY()
            for (i in dataBalance.indices) {
                val data = dataBalance[i]
                if (i == 0 || i == dataBalance.size || dayCurrent == "" || data.date !== dayCurrent) {
                    if (i != 0) {
                        dataGroupList.add(MyWalletGroup(dayCurrent, dataItemList))
                        dataItemList = ArrayList()
                    }
                }
                dayCurrent = data.date
                dataItemList.add(data)
            }
            val linearLayoutManager = LinearLayoutManager(UTSwapApp.instance)
            listTransaction.layoutManager = linearLayoutManager
            val myWalletGroupAdapter = MyWalletGroupAdapter(dataGroupList)
            listTransaction.adapter = myWalletGroupAdapter
        }

    }
}