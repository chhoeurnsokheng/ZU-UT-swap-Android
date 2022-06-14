package com.zillennium.utswap.screens.finance.subscriptionsActivity

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.zillennium.utswap.Datas.GlobalVariable.SettingVariable
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivityFinanceSubscriptionsBinding
import com.zillennium.utswap.models.financeSubscription.FinanceSubscriptionsModel
import com.zillennium.utswap.screens.finance.subscriptionsActivity.adapter.FinanceSubscriptionsAdapter
import com.zillennium.utswap.screens.finance.subscriptionsActivity.bottomSheet.FinanceSubscriptionDateRangeBottomSheet
import com.zillennium.utswap.screens.finance.subscriptionsActivity.bottomSheet.FinanceSubscriptionFilterBottomSheet
import com.zillennium.utswap.screens.finance.subscriptionsActivity.dialog.FinanceSubscriptionsDialog
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.*


class FinanceSubscriptionsActivity :
    BaseMvpActivity<FinanceSubscriptionsView.View, FinanceSubscriptionsView.Presenter, ActivityFinanceSubscriptionsBinding>(),
    FinanceSubscriptionsView.View {

    override var mPresenter: FinanceSubscriptionsView.Presenter = FinanceSubscriptionsPresenter()
    override val layoutResource: Int = R.layout.activity_finance_subscriptions
    private val arraySubscription = ArrayList<FinanceSubscriptionsModel>()
    private var financeSubscriptionsAdapter: FinanceSubscriptionsAdapter? = null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun initView() {
        super.initView()
        try {
            binding.apply {

                backImage.setOnClickListener {
                    finish()
                }

                /* Filter */
                filterButton.setOnClickListener {
                    FinanceSubscriptionFilterBottomSheet.newInstance()
                        .show(supportFragmentManager, "Filter Subscription")
                }

                /* Select Date Range */
                selectDateRange.setOnClickListener {
//                    FinanceSubscriptionDateRangeBottomSheet().setOnCallBack(callBack)
                    FinanceSubscriptionDateRangeBottomSheet.newInstance()
                        .show(supportFragmentManager, "Select Date Time Subscription")
                }

                /* Subscription Recycle view */
                val image = arrayOf(
                    R.drawable.ic_locked,
                    R.drawable.ic_unlocked,
                    R.drawable.ic_unlocked,
                    R.drawable.ic_unlocked,
                    R.drawable.ic_unlocked,
                )
                val title = arrayOf(
                    "UT Kampot",
                    "UT Mondolkiri",
                    "UT Pailin",
                    "UT Sihanoukville",
                    "UT Battambang"
                )
                val date = arrayOf(
                    "19-May-2022",
                    "12-May-2022",
                    "10-May-2022",
                    "09-May-2022",
                    "05-May-2022"
                )
                val amount = arrayOf(
                    400.00,
                    555.00,
                    250.00,
                    1250.00,
                    150.00
                )
                val durationDay = arrayOf(
                    "30 day(s) left",
                    "Unlocked",
                    "Unlocked",
                    "Unlocked",
                    "Unlocked",
                )
                val status = arrayOf(
                    1,
                    0,
                    0,
                    0,
                    0
                )

                for (i in date.indices) {
                    val financeSubscriptions = FinanceSubscriptionsModel(
                        image[i],
                        title[i],
                        date[i],
                        amount[i],
                        durationDay[i],
                        status[i]
                    )
                    arraySubscription.add(financeSubscriptions)
                }

                /* Sorted Date */
                /*val dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MMMM-yyyy")
                arraySubscription.sortByDescending {
                    LocalDate.parse(
                        it.dateSubscription,
                        dateTimeFormatter
                    )
                }*/

                rvSubscriptions.layoutManager = LinearLayoutManager(UTSwapApp.instance)
                financeSubscriptionsAdapter =
                    FinanceSubscriptionsAdapter(arraySubscription, onClickAdapter)
                rvSubscriptions.adapter = financeSubscriptionsAdapter


                SettingVariable.finance_subscription_filter.observe(this@FinanceSubscriptionsActivity) {
                    filter()
                }

                SettingVariable.finance_subscription_date_end.observe(this@FinanceSubscriptionsActivity) {
                    filter()
                }
            }

        } catch (error: Exception) {
            // Must be safe
        }
    }

    /* filter based on selected from bottomSheet dialog */
    private fun filterList(text: String) {
        val newArrayList: ArrayList<FinanceSubscriptionsModel> =
            ArrayList<FinanceSubscriptionsModel>()
        for (item in arraySubscription) {
            if (item.titleSubscription.toLowerCase()
                    .contains(text.lowercase(Locale.getDefault()))
            ) {
                newArrayList.add(item)
            }
        }

        financeSubscriptionsAdapter?.filterList(newArrayList)
    }


    private val onClickAdapter: FinanceSubscriptionsAdapter.OnClickAdapter =
        object : FinanceSubscriptionsAdapter.OnClickAdapter {
            override fun onClickMe(financeSubscriptionsModel: FinanceSubscriptionsModel) {
                val financeSubscriptionsDialog: FinanceSubscriptionsDialog =
                    FinanceSubscriptionsDialog.newInstance(
                        financeSubscriptionsModel.titleSubscription,
                        financeSubscriptionsModel.status,
                        financeSubscriptionsModel.amount,
                        financeSubscriptionsModel.durationDay,
                        financeSubscriptionsModel.imageSubscription
                    )
                financeSubscriptionsDialog.show(supportFragmentManager, "Dialog of Subscription")
            }
        }

    @SuppressLint("SetTextI18n")
    private fun filter() {
        val list = ArrayList<FinanceSubscriptionsModel>()
        val listDate = ArrayList<FinanceSubscriptionsModel>()
        val filter = SettingVariable.finance_subscription_filter.value
        val dateStart = SettingVariable.finance_subscription_date_start.value
        val dateEnd = SettingVariable.finance_subscription_date_end.value

        binding.apply {
            if (!filter.isNullOrEmpty()) {
                txtTitleFilter.text = filter

                if (filter == "All Projects") {
                    list.addAll(arraySubscription)
                } else {
                    arraySubscription.map {
                        if(filter == it.titleSubscription){
                            list.add(it)
                        }
                    }
                }
            }

            if(!dateStart.isNullOrEmpty() && !dateEnd.isNullOrEmpty()){

                val dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MMMM-yyyy")

                val dateStartFormat: LocalDate = LocalDate.parse(dateStart, dateTimeFormatter)
                val dateEndFormat: LocalDate = LocalDate.parse(dateEnd, dateTimeFormatter)

                list.map {
                    val date : LocalDate = LocalDate.parse(it.dateSubscription, dateTimeFormatter)
                    if(date in dateStartFormat..dateEndFormat){
                        listDate.add(it)
                    }
                }

                list.clear()
                list.addAll(listDate)
            }

            rvSubscriptions.layoutManager = LinearLayoutManager(UTSwapApp.instance)
            financeSubscriptionsAdapter =
                FinanceSubscriptionsAdapter(list, onClickAdapter)
            rvSubscriptions.adapter = financeSubscriptionsAdapter

        }


    }

    override fun onDestroy() {
        super.onDestroy()
        SettingVariable.finance_subscription_date_start.value = ""
        SettingVariable.finance_subscription_date_end.value = ""
        SettingVariable.finance_subscription_filter.removeObservers(this@FinanceSubscriptionsActivity)
        SettingVariable.finance_subscription_date_end.removeObservers(this@FinanceSubscriptionsActivity)
    }

}