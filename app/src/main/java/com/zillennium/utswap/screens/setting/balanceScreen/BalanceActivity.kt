package com.zillennium.utswap.screens.setting.balanceScreen

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.widget.DatePicker
import androidx.recyclerview.widget.LinearLayoutManager
import com.zillennium.utswap.R
import com.zillennium.utswap.bases.BasePassedActivity
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivityWalletBalanceBinding
import com.zillennium.utswap.datas.balanceData.DataBalanceHistory
import com.zillennium.utswap.models.BalanceHistory.BalanceHistory
import com.zillennium.utswap.screens.setting.balanceScreen.adapter.BalanceHistoryAdapter
import com.zillennium.utswap.screens.setting.balanceScreen.dialog.BalanceHistoryDetailDialog
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class BalanceActivity :
    BaseMvpActivity<BalanceView.View, BalanceView.Presenter, ActivityWalletBalanceBinding>(),
    BalanceView.View {

    override var mPresenter: BalanceView.Presenter = BalancePresenter()
    override val layoutResource: Int = R.layout.activity_wallet_balance

    override fun initView() {
        super.initView()
        try {
            binding.apply {
                setAdapter()

                val calendar = Calendar.getInstance()

                val dateStart =
                    OnDateSetListener { _: DatePicker?, year: Int, month: Int, day: Int ->
                        calendar[Calendar.YEAR] = year
                        calendar[Calendar.MONDAY] = month
                        calendar[Calendar.DAY_OF_MONTH] = day
                        val format = "MM/dd/yyyy"
                        val simpleDateFormat =
                            SimpleDateFormat(format, Locale.US)
                        edtxtFrom.setText(simpleDateFormat.format(calendar.time))
                    }

                val dateEnd =
                    OnDateSetListener { _: DatePicker?, year: Int, month: Int, day: Int ->
                        calendar[Calendar.YEAR] = year
                        calendar[Calendar.MONDAY] = month
                        calendar[Calendar.DAY_OF_MONTH] = day
                        val format = "MM/dd/yyyy"
                        val simpleDateFormat =
                            SimpleDateFormat(format, Locale.US)
                        edtxtTo.setText(simpleDateFormat.format(calendar.time))
                    }

                edtxtFrom.setOnClickListener {
                    DatePickerDialog(
                        this@BalanceActivity,
                        dateStart,
                        calendar[Calendar.YEAR],
                        calendar[Calendar.MONTH],
                        calendar[Calendar.DAY_OF_MONTH]
                    ).show()
                }

                edtxtTo.setOnClickListener {
                    DatePickerDialog(
                        this@BalanceActivity,
                        dateEnd,
                        calendar[Calendar.YEAR],
                        calendar[Calendar.MONTH],
                        calendar[Calendar.DAY_OF_MONTH]
                    ).show()
                }

                // Set Passed Back
                BasePassedActivity.onPassedBack(this@BalanceActivity, ivBack)
            }
            // Code
        } catch (error: IOException) {
            // Must be safe
        }
    }

    fun setAdapter() {
        binding.apply {
            val linearLayoutManager = LinearLayoutManager(baseContext)
            listBalanceHistory.layoutManager = linearLayoutManager
            val balanceHistoryAdapter = BalanceHistoryAdapter(
                DataBalanceHistory.LIST_OF_BALANCE_HISTORY(),
                onclickBalanceHistory
            )
            listBalanceHistory.adapter = balanceHistoryAdapter
        }
    }

    private val onclickBalanceHistory: BalanceHistoryAdapter.OnclickBalanceHistory = object :
        BalanceHistoryAdapter.OnclickBalanceHistory {
        override fun onClickMe(balanceHistory: BalanceHistory?) {
            val balanceHistoryDetailDialog: BalanceHistoryDetailDialog =
                BalanceHistoryDetailDialog.newInstance(
                    balanceHistory?.transaction,
                    balanceHistory?.transactionDetail,
                    balanceHistory?.date,
                    balanceHistory?.moneyOut,
                    balanceHistory?.balance,
                    balanceHistory?.moneyIn,
                    balanceHistory?.imagePath!!
                )
            balanceHistoryDetailDialog.show(supportFragmentManager, "balanceHistoryDetailDialog")
        }
    }
}