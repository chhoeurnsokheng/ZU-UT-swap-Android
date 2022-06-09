package com.zillennium.utswap.screens.finance.balanceActivity

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.databinding.ActivityFinanceBalanceBinding
import com.zillennium.utswap.models.FinanceBalanceModel
import com.zillennium.utswap.screens.finance.balanceActivity.adapter.FinanceBalanceAdapter
import com.zillennium.utswap.screens.finance.balanceActivity.bottomSheet.FinanceExportFileBottomSheet
import com.zillennium.utswap.screens.finance.balanceActivity.bottomSheet.FinanceFilterBottonSheet
import com.zillennium.utswap.screens.finance.balanceActivity.bottomSheet.FinanceSelectDateRangeBottonSheet
import com.zillennium.utswap.screens.finance.balanceActivity.dialog.FinanceBalanceDialog
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class FinanceBalanceActivity :
    BaseMvpActivity<FinanceBalanceView.View, FinanceBalanceView.Presenter, ActivityFinanceBalanceBinding>(),
    FinanceBalanceView.View {

    override var mPresenter: FinanceBalanceView.Presenter = FinanceBalancePresenter()
    override val layoutResource: Int = R.layout.activity_finance_balance

    @RequiresApi(Build.VERSION_CODES.O)
    override fun initView() {
        super.initView()
        try {
            binding.apply {

                backImage.setOnClickListener {
                    finish()
                }

                /* Export as File bottom sheet dialog */
                exportAsPdf.setOnClickListener {
                    FinanceExportFileBottomSheet.newInstance().show(
                        supportFragmentManager,"Export File"
                    )
                }

                /* Filter bottom sheet dialog */
                filterButton.setOnClickListener {
                    FinanceFilterBottonSheet.newInstance().show(
                        supportFragmentManager, "Filter"
                    )
                }

                /* Select Date Range bottom sheet dialog */
                selectDateRange.setOnClickListener {
                    FinanceSelectDateRangeBottonSheet.newInstance().show(
                        supportFragmentManager, "Select Date Range")
                }

                /* Recycle view of transaction balance */
                val imageBalance = arrayOf(
                    R.drawable.ic_money_out,
                    R.drawable.ic_money_in,
                    R.drawable.ic_hourglass,
                    R.drawable.ic_balance_withdraw,
                    R.drawable.ic_balance_deposit,
                    R.drawable.ic_money_out,
                )

                val titleTransaction = arrayOf(
                    "Bought 29 UT New Airport 38Ha @ 17.83",
                    "Sold 388 UT Muk Kampul 16644 @ 1.30",
                    "Subscribed 29 UT New Airport 38Ha @",
                    "Sold 388 UT Muk Kampul 16644 @ 1.30",
                    "Withdraw $168.99",
                    "Deposit $420.99",
                )

                val dateTransaction = arrayOf(
                    "19-May-2022",
                    "18-May-2022",
                    "17-May-2022",
                    "15-May-2022",
                    "13-May-2022",
                    "12-May-2022",
                )

                val amountBalance = arrayOf(
                    -517.07,
                    5437.07,
                    -178.99,
                    420.99,
                    -168.99,
                    420.99,
                )

                val status = arrayOf(
                    0,
                    1,
                    0,
                    1,
                    0,
                    1
                )

                val financeBalanceArrayList = ArrayList<FinanceBalanceModel>()

                for (i in dateTransaction.indices) {
                    val financeBalance = FinanceBalanceModel(
                        imageBalance[i],
                        titleTransaction[i],
                        dateTransaction[i],
                        amountBalance[i],
                        status[i]
                    )
                    financeBalanceArrayList.add(financeBalance)
                }

                for (i in dateTransaction.indices) {
                    val financeBalance = FinanceBalanceModel(
                        imageBalance[i],
                        titleTransaction[i],
                        dateTransaction[i],
                        amountBalance[i],
                        status[i]
                    )
                    financeBalanceArrayList.add(financeBalance)
                }

                /* Sorted Date */
                val dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MMMM-yyyy")
                financeBalanceArrayList.sortByDescending {
                    LocalDate.parse(
                        it.dateTransaction,
                        dateTimeFormatter
                    )
                }

                rvFinanceBalance.layoutManager = LinearLayoutManager(UTSwapApp.instance)
                rvFinanceBalance.adapter = FinanceBalanceAdapter(financeBalanceArrayList, onClickAdapter)



            }
        } catch (error: Exception) {
            // Must be safe
        }
    }

    private val onClickAdapter: FinanceBalanceAdapter.OnClickAdapter = object : FinanceBalanceAdapter.OnClickAdapter {
        override fun onClickMe(financeBalanceModel: FinanceBalanceModel) {
            val financeBalanceDialog: FinanceBalanceDialog = FinanceBalanceDialog.newInstance(
                financeBalanceModel.titleTransaction,
                financeBalanceModel.dateTransaction,
                financeBalanceModel.amountBalance,
                financeBalanceModel.status,
            )
            financeBalanceDialog.show(supportFragmentManager, "Balance Data Detail")
        }

    }
}