package com.zillennium.utswap.module.finance.balanceScreen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.zillennium.utswap.Datas.GlobalVariable.SettingVariable
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.databinding.ActivityFinanceBalanceBinding
import com.zillennium.utswap.models.FinanceBalanceModel
import com.zillennium.utswap.module.finance.balanceScreen.adapter.FinanceBalanceAdapter
import com.zillennium.utswap.module.finance.balanceScreen.bottomSheet.FinanceExportFileBottomSheet
import com.zillennium.utswap.module.finance.balanceScreen.bottomSheet.FinanceFilterBottomSheet
import com.zillennium.utswap.module.finance.balanceScreen.bottomSheet.FinanceSelectDateRangeBottonSheet
import com.zillennium.utswap.module.finance.balanceScreen.dialog.FinanceBalanceDialog
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class FinanceBalanceActivity :
    BaseMvpActivity<FinanceBalanceView.View, FinanceBalanceView.Presenter, ActivityFinanceBalanceBinding>(),
    FinanceBalanceView.View {

    override var mPresenter: FinanceBalanceView.Presenter = FinanceBalancePresenter()
    override val layoutResource: Int = R.layout.activity_finance_balance

    val filter = SettingVariable.balance_filter

    @RequiresApi(Build.VERSION_CODES.O)
    override fun initView() {
        super.initView()


        toolBar()



        try {
            binding.apply {

                /* Export as File bottom sheet dialog */
                exportAsPdf.setOnClickListener {
                    FinanceExportFileBottomSheet.newInstance().show(
                        supportFragmentManager, "Export File"
                    )
                }

                /* Filter bottom sheet dialog */
                filterButton.setOnClickListener {
                    FinanceFilterBottomSheet.newInstance().show(
                        supportFragmentManager, "Filter"
                    )
                }

                /* Select Date Range bottom sheet dialog */
                selectDateRange.setOnClickListener {
                    FinanceSelectDateRangeBottonSheet.newInstance().show(
                        supportFragmentManager, "Select Date Range"
                    )
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
                    1,
                    1,
                    3,
                    4,
                    5,
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


                filter.observe(this@FinanceBalanceActivity) {
                    if (filter.value != 0) {
                        financeBalanceArrayList.clear()
                        for (i in dateTransaction.indices) {
                            if (filter.value == status[i]) {
                                val financeBalance = FinanceBalanceModel(
                                    imageBalance[i],
                                    titleTransaction[i],
                                    dateTransaction[i],
                                    amountBalance[i],
                                    status[i]
                                )
                                financeBalanceArrayList.add(financeBalance)
                            }
                            if (filter.value == status[i]) {
                                val financeBalance = FinanceBalanceModel(
                                    imageBalance[i],
                                    titleTransaction[i],
                                    dateTransaction[i],
                                    amountBalance[i],
                                    status[i]
                                )
                                financeBalanceArrayList.add(financeBalance)
                            }

                            rvFinanceBalance.layoutManager = LinearLayoutManager(UTSwapApp.instance)
                            rvFinanceBalance.adapter =
                                FinanceBalanceAdapter(financeBalanceArrayList, onClickAdapter)
                        }
                    }
                }

                rvFinanceBalance.layoutManager = LinearLayoutManager(UTSwapApp.instance)
                rvFinanceBalance.adapter =
                    FinanceBalanceAdapter(financeBalanceArrayList, onClickAdapter)


            }
        } catch (error: Exception) {
            // Must be safe
        }
    }

    private val onClickAdapter: FinanceBalanceAdapter.OnClickAdapter =
        object : FinanceBalanceAdapter.OnClickAdapter {
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

    override fun onDestroy() {
        super.onDestroy()
        filter.value = 0
        filter.removeObservers(this@FinanceBalanceActivity)
    }


    private fun toolBar() {
        setSupportActionBar(binding.includeLayout.tb)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_left)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.includeLayout.apply {
            tbTitle.setText(R.string.terms_and_conditions)
            tbTitle.setTextColor(ContextCompat.getColor(applicationContext, R.color.primary))
            tb.setNavigationOnClickListener {
                finish()
            }
        }
    }

}