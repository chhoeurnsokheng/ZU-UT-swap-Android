package com.zillennium.utswap.screens.finance.historicalScreen


import android.annotation.SuppressLint
import android.os.Build
import android.view.View
import android.widget.AdapterView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.zillennium.utswap.Datas.GlobalVariable.SettingVariable
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivityFinanceHistoricalBinding
import com.zillennium.utswap.models.financeHistorical.HistoricalAllTransactionsModel
import com.zillennium.utswap.models.financeHistorical.HistoricalMyTransactionsModel
import com.zillennium.utswap.models.financeHistorical.HistoricalTradeModel
import com.zillennium.utswap.screens.finance.balanceScreen.bottomSheet.FinanceExportFileBottomSheet
import com.zillennium.utswap.screens.finance.historicalScreen.adapter.CustomDropDownAdapter
import com.zillennium.utswap.screens.finance.historicalScreen.adapter.HistoricalAllTransactionsAdapter
import com.zillennium.utswap.screens.finance.historicalScreen.adapter.HistoricalMyTransactionsAdapter
import com.zillennium.utswap.screens.finance.historicalScreen.adapter.HistoricalTradeAdapter
import com.zillennium.utswap.screens.finance.historicalScreen.bottomSheet.FinanceHistoricalFilterBottomSheet
import com.zillennium.utswap.screens.finance.historicalScreen.bottomSheet.FinanceHistoricalSelectDateRangeBottomSheet
import com.zillennium.utswap.screens.finance.historicalScreen.bottomSheet.FinanceHistoricalTransactionBottomSheet
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class FinanceHistoricalActivity :
    BaseMvpActivity<FinanceHistoricalView.View, FinanceHistoricalView.Presenter, ActivityFinanceHistoricalBinding>(),
    FinanceHistoricalView.View {

    override var mPresenter: FinanceHistoricalView.Presenter = FinanceHistoricalPresenter()
    override val layoutResource: Int = R.layout.activity_finance_historical

    private val financeHistoricalMyTransactionArrayList = ArrayList<HistoricalMyTransactionsModel>()
    private val financeHistoricalTradeArrayList = ArrayList<HistoricalTradeModel>()
    private val financeHistoricalAllTransactionArrayList = ArrayList<HistoricalAllTransactionsModel>()

    private var historicalMyTransactionsAdapter: HistoricalMyTransactionsAdapter? = null
    private var historicalTradeAdapter: HistoricalTradeAdapter? = null
    private var historicalAllTransactionsAdapter: HistoricalAllTransactionsAdapter? = null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun initView() {
        super.initView()
        try {
            binding.apply {

                /* Back button */
                backImage.setOnClickListener {
                    finish()
                }

                /* Historical Transaction Filter */
                layHistoricalTransaction.setOnClickListener {
                    FinanceHistoricalTransactionBottomSheet.newInstance(txtTransactionHistorical.text.toString())
                        .show(supportFragmentManager, "Filter Transaction")
                }

                /* Filter Button */
                layFilterButton.setOnClickListener {
                    FinanceHistoricalFilterBottomSheet.newInstance()
                        .show(supportFragmentManager, "Filter UT")
                }

                /* Select Date Range */
                laySelectDateRange.setOnClickListener {
                    FinanceHistoricalSelectDateRangeBottomSheet.newInstance()
                        .show(supportFragmentManager, "Select date range")
                }

                /* Export Icon */
                exportAsPdf.setOnClickListener {
                    FinanceExportFileBottomSheet.newInstance().show(
                        supportFragmentManager, "Export File"
                    )
                }

                /* Spinner */
                val items = listOf("ALL", "BUY", "SELL")
//                val adapterSpinner = ArrayAdapter(this@FinanceHistoricalActivity,  R.layout.item_list_finance_historical_spinner,items)
//                adapterSpinner.setDropDownViewResource(R.layout.item_list_finance_historical_spinner_dropdown);
                historicalSpinnerDropdown.adapter = CustomDropDownAdapter(this@FinanceHistoricalActivity, items)
                historicalSpinnerDropdown.onItemSelectedListener = object :
                    AdapterView.OnItemSelectedListener{
                    override fun onItemSelected(adapterView: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                        SettingVariable.finance_historical_spinner_item.value = position


                        if (position == 0){

                        }
                    }

                    override fun onNothingSelected(p0: AdapterView<*>?) {

                    }
                }

                /* count list */
                btnNext.isEnabled = true
                btnBack.isEnabled = false


                val dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MMMM-yyyy")
                rvFinanceHistorical.layoutManager = LinearLayoutManager(UTSwapApp.instance)

                SettingVariable.finance_historical_selected.observe(this@FinanceHistoricalActivity){
                    txtTransactionHistorical.text = SettingVariable.finance_historical_selected.value.toString()

                    layMyTransactions.visibility = View.GONE
                    layTrade.visibility = View.GONE
                    layAllTransaction.visibility = View.GONE

                    when (SettingVariable.finance_historical_selected.value.toString()){
                        "My Transactions" -> {
                            layMyTransactions.visibility = View.VISIBLE

                            financeHistoricalMyTransactionArrayList.clear()

                            financeHistoricalMyTransactionArrayList.add(HistoricalMyTransactionsModel(R.drawable.ic_money_in, "Sold 388 UT Muk Kampul 16644 @ 1.30", "19-May-2022", 517.07, 1, "UT Muk Kampul"))
                            financeHistoricalMyTransactionArrayList.add(HistoricalMyTransactionsModel(R.drawable.ic_money_out,"Bought 5 UT Pochentong 555 @ 4.15",  "17-May-2022", -120.90, 2, "UT Pochentong 555"))
                            financeHistoricalMyTransactionArrayList.add(HistoricalMyTransactionsModel(R.drawable.ic_hourglass, "Subscribed 450 UT New Airport 38Ha @ 1.30", "09-May-2022", -5437.07, 2, "UT New Airport"))
                            financeHistoricalMyTransactionArrayList.add(HistoricalMyTransactionsModel(R.drawable.ic_money_in,"Sold 230 UT Siem Reap 17140 @ 17.80", "02-May-2022", 200.50, 1, "UT Siem Reap"))
                            financeHistoricalMyTransactionArrayList.add(HistoricalMyTransactionsModel(R.drawable.ic_money_out,"Bought 70 UT Muk Kampul 16644 @ 14.50", "01-May-2022", -704.03, 2, "UT Muk Kampul"))
                            financeHistoricalMyTransactionArrayList.add(HistoricalMyTransactionsModel(R.drawable.ic_hourglass,"Subscribed 40 UT Sihanoukville 1665 @ 24.02",  "28-April-2022", -320.05, 2, "UT Sihanoukville"))
                            financeHistoricalMyTransactionArrayList.add(HistoricalMyTransactionsModel(R.drawable.ic_money_out,"Bought 73 UT Veng Sreng 2719 @ 1.42",  "20-April-2022", -546.30, 2, "UT Veng Sreng"))
                            financeHistoricalMyTransactionArrayList.add(HistoricalMyTransactionsModel(R.drawable.ic_money_in, "Sold 388 UT Muk Kampul 16644 @ 1.30", "19-April-2022", 517.07, 1, "UT Muk Kampul"))
                            financeHistoricalMyTransactionArrayList.add(HistoricalMyTransactionsModel(R.drawable.ic_money_in,"Sold 5 UT Pochentong 555 @ 4.15",  "02-April-2022", 250.90, 1, "UT Pochentong 555"))
                            financeHistoricalMyTransactionArrayList.add(HistoricalMyTransactionsModel(R.drawable.ic_hourglass, "Subscribed 450 UT New Airport 38Ha @ 1.30", "01-April-2022", -5437.07, 2, "UT New Airport"))
                            financeHistoricalMyTransactionArrayList.add(HistoricalMyTransactionsModel(R.drawable.ic_money_in,"Sold 230 UT Siem Reap 17140 @ 17.80", "27-March-2022", 200.50, 1, "UT Siem Reap"))
                            financeHistoricalMyTransactionArrayList.add(HistoricalMyTransactionsModel(R.drawable.ic_money_out,"Bought 70 UT Muk Kampul 16644 @ 14.50", "23-March-2022", -704.03, 2, "UT Muk Kampul"))
                            financeHistoricalMyTransactionArrayList.add(HistoricalMyTransactionsModel(R.drawable.ic_hourglass,"Subscribed 40 UT Sihanoukville 1665 @ 24.02",  "16-March-2022", -320.05, 2, "UT Sihanoukville"))
                            financeHistoricalMyTransactionArrayList.add(HistoricalMyTransactionsModel(R.drawable.ic_money_in,"Sold 73 UT Veng Sreng 2719 @ 1.42",  "06-March-2022", 546.30, 1, "UT Veng Sreng"))
                            financeHistoricalMyTransactionArrayList.add(HistoricalMyTransactionsModel(R.drawable.ic_money_in,"Sold 20 UT Sihanoukville 1665 @ 12.02",  "05-March-2022", 520.05, 1, "UT Sihanoukville"))
                            financeHistoricalMyTransactionArrayList.add(HistoricalMyTransactionsModel(R.drawable.ic_money_out,"Bought 73 UT Veng Sreng 2719 @ 1.42",  "02-March-2022", -546.30, 2, "UT Veng Sreng"))

                            /* Sorted Date */
                            financeHistoricalMyTransactionArrayList.sortByDescending {
                                LocalDate.parse(
                                    it.dateMyTrans,
                                    dateTimeFormatter
                                )
                            }

                            rvFinanceHistorical.adapter = HistoricalMyTransactionsAdapter(financeHistoricalMyTransactionArrayList)

                            SettingVariable.finance_historical_filter.observe(this@FinanceHistoricalActivity){
                                filter()
                            }
                            SettingVariable.finance_historical_spinner_item.observe(this@FinanceHistoricalActivity){
                                filter()
                            }
                        }
                        "Trade" -> {
                            layTrade.visibility = View.VISIBLE
                            financeHistoricalTradeArrayList.clear()

                            financeHistoricalTradeArrayList.add(HistoricalTradeModel("Pochentong 555", 268000, 33.99, 33.99, 33.99, 33.99, "02-June-2022"))
                            financeHistoricalTradeArrayList.add(HistoricalTradeModel("Siem Reap 17140", 268000, 3.99, 3.99, 3.99, 3.99, "02-May-2022"))
                            financeHistoricalTradeArrayList.add(HistoricalTradeModel("Muk Kampul 16644", 268000, 33.99, 33.99, 33.99, 33.99, "03-June-2022"))
                            financeHistoricalTradeArrayList.add(HistoricalTradeModel("KT 1665", 268000, 3.99, 3.99, 3.99, 3.99, "5-July-2022"))
                            financeHistoricalTradeArrayList.add(HistoricalTradeModel("Veng Sreng 2719", 268000, 3.99, 3.99, 3.99, 3.99, "10-June-2022"))
                            financeHistoricalTradeArrayList.add(HistoricalTradeModel("New Airport 38Ha", 268000, 33.99, 33.99, 33.99, 33.99, "07-May-2022"))
                            financeHistoricalTradeArrayList.add(HistoricalTradeModel("Pochentong 555", 268000, 33.99, 33.99, 33.99, 33.99, "10-June-2022"))
                            financeHistoricalTradeArrayList.add(HistoricalTradeModel("Siem Reap 17140", 268000, 3.99, 3.99, 3.99, 3.99, "10-August-2022"))
                            financeHistoricalTradeArrayList.add(HistoricalTradeModel("Muk Kampul 16644", 268000, 33.99, 33.99, 33.99, 33.99,"03-June-2022"))
                            financeHistoricalTradeArrayList.add(HistoricalTradeModel("KT 1665", 268000, 3.99, 3.99, 3.99, 3.99, "19-April-2022"))
                            financeHistoricalTradeArrayList.add(HistoricalTradeModel("Veng Sreng 2719", 268000, 3.99, 3.99, 3.99, 3.99, "07-May-2022"))
                            financeHistoricalTradeArrayList.add(HistoricalTradeModel("New Airport 38Ha", 268000, 33.99, 33.99, 33.99, 33.99, "30-May_2022"))
                            financeHistoricalTradeArrayList.add(HistoricalTradeModel("KT 1665", 268000, 3.99, 3.99, 3.99, 3.99, "10-April-2022"))
                            financeHistoricalTradeArrayList.add(HistoricalTradeModel("Veng Sreng 2719", 268000, 3.99, 3.99, 3.99, 3.99, "5-July-2022"))
                            financeHistoricalTradeArrayList.add(HistoricalTradeModel("New Airport 38Ha", 268000, 33.99, 33.99, 33.99, 33.99, "6-July-2022"))

                            rvFinanceHistorical.adapter = HistoricalTradeAdapter(financeHistoricalTradeArrayList)

                            SettingVariable.finance_historical_filter.observe(this@FinanceHistoricalActivity){
                                filter()
                            }
                        }
                        "All Transactions" -> {
                            layAllTransaction.visibility = View.VISIBLE
                            financeHistoricalAllTransactionArrayList.clear()

                            financeHistoricalAllTransactionArrayList.add(HistoricalAllTransactionsModel("02-June-2022", 151, 3.97, 599.00, "Pochentong 555"))
                            financeHistoricalAllTransactionArrayList.add(HistoricalAllTransactionsModel("02-June-2022", 50, 3.99, 199.00, "Siem Reap 17140"))
                            financeHistoricalAllTransactionArrayList.add(HistoricalAllTransactionsModel("02-June-2022", 51, 3.99, 250.00, "Muk Kampul 16644"))
                            financeHistoricalAllTransactionArrayList.add(HistoricalAllTransactionsModel("02-June-2022", 10, 3.97, 27.00, "KT 1665"))
                            financeHistoricalAllTransactionArrayList.add(HistoricalAllTransactionsModel("31-May-2022", 277, 3.97, 39.00, "Veng Sreng 2719"))
                            financeHistoricalAllTransactionArrayList.add(HistoricalAllTransactionsModel("31-May-2022", 20, 3.97, 79.00, "New Airport 38Ha"))
                            financeHistoricalAllTransactionArrayList.add(HistoricalAllTransactionsModel("31-May-2022", 485, 3.97, 1930.00, "Pochentong 555"))
                            financeHistoricalAllTransactionArrayList.add(HistoricalAllTransactionsModel("31-May-2022", 15, 3.97, 59.00,"Siem Reap 17140"))
                            financeHistoricalAllTransactionArrayList.add(HistoricalAllTransactionsModel("30-June-2022", 500, 3.97, 1985.00, "Muk Kampul 16644"))
                            financeHistoricalAllTransactionArrayList.add(HistoricalAllTransactionsModel("30-June-2022", 700, 3.97, 2779.00, "KT 1665"))
                            financeHistoricalAllTransactionArrayList.add(HistoricalAllTransactionsModel("29-June-2022", 51, 3.99, 250.00, "Veng Sreng 2719"))
                            financeHistoricalAllTransactionArrayList.add(HistoricalAllTransactionsModel("29-June-2022", 10, 3.97, 27.00, "Siem Reap 17140"))
                            financeHistoricalAllTransactionArrayList.add(HistoricalAllTransactionsModel("28-May-2022", 277, 3.97, 39.00, "KT 1665"))
                            financeHistoricalAllTransactionArrayList.add(HistoricalAllTransactionsModel("28-May-2022", 20, 3.97, 79.00, "New Airport 38Ha"))

                            /* Sorted Date */
                            financeHistoricalAllTransactionArrayList.sortByDescending {
                                LocalDate.parse(
                                    it.dateAllTrans,
                                    dateTimeFormatter
                                )
                            }

                            rvFinanceHistorical.adapter  = HistoricalAllTransactionsAdapter(financeHistoricalAllTransactionArrayList)

                            SettingVariable.finance_historical_filter.observe(this@FinanceHistoricalActivity){
                               filter()
                            }
                        }
                    }
                }
            }
        } catch (error: Exception) {
            // Must be safe
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    private fun filter() {
        when(SettingVariable.finance_historical_selected.value.toString()){
            "My Transactions" -> {
                val list = java.util.ArrayList<HistoricalMyTransactionsModel>()
                val listDate = java.util.ArrayList<HistoricalMyTransactionsModel>()
                val listSpinner = java.util.ArrayList<HistoricalMyTransactionsModel>()
                val spinnerFilter = SettingVariable.finance_historical_spinner_item.value
                val filter = SettingVariable.finance_historical_filter.value
                val dateStart = SettingVariable.finance_historical_date_start.value
                val dateEnd = SettingVariable.finance_historical_date_end.value

                binding.apply {

                    if (!filter.isNullOrEmpty()) {
                        txtHistoryMyTrans.text = filter
                        if (filter == "UT All Projects") {
                            list.addAll(financeHistoricalMyTransactionArrayList)
                            layListNumber.visibility = View.VISIBLE
                        } else {
                            layListNumber.visibility = View.GONE
                            financeHistoricalMyTransactionArrayList.map {
                                if (filter == it.titleCheckMyTrans) {
                                    list.add(it)
                                }
                            }
                        }
                    }

                    if (spinnerFilter != 0){
                        if (spinnerFilter == 1){
                            list.map {
                                if (spinnerFilter == it.statusMyTrans) {
                                    listSpinner.add(it)
                                }
                            }
                            list.clear()
                            list.addAll(listSpinner)
                        }
                        else if (spinnerFilter == 2){
                            list.map {
                                if (spinnerFilter == it.statusMyTrans) {
                                    listSpinner.add(it)
                                }
                            }
                            list.clear()
                            list.addAll(listSpinner)
                        }

                    }

                    if(!dateStart.isNullOrEmpty() && !dateEnd.isNullOrEmpty()){

                        val dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MMMM-yyyy")

                        val dateStartFormat: LocalDate = LocalDate.parse(dateStart, dateTimeFormatter)
                        val dateEndFormat: LocalDate = LocalDate.parse(dateEnd, dateTimeFormatter)

                        list.map {
                            val date : LocalDate = LocalDate.parse(it.dateMyTrans, dateTimeFormatter)
                            if(date in dateStartFormat..dateEndFormat){
                                listDate.add(it)
                            }
                        }

                        list.clear()
                        list.addAll(listDate)
                    }


                    rvFinanceHistorical.layoutManager = LinearLayoutManager(UTSwapApp.instance)
                    historicalMyTransactionsAdapter = HistoricalMyTransactionsAdapter(list)
                    rvFinanceHistorical.adapter = historicalMyTransactionsAdapter
                }
            }
            "Trade" -> {
                val list = java.util.ArrayList<HistoricalTradeModel>()
                val listDate = java.util.ArrayList<HistoricalTradeModel>()
                val filter = SettingVariable.finance_historical_filter.value
                val dateStart = SettingVariable.finance_historical_date_start.value
                val dateEnd = SettingVariable.finance_historical_date_end.value

                binding.apply {
                    if (!filter.isNullOrEmpty()) {
                        txtTradeTitle.text = filter

                        if (filter == "All Projects") {
                            layListNumber.visibility = View.VISIBLE
                            list.addAll(financeHistoricalTradeArrayList)
                        } else {
                            layListNumber.visibility = View.GONE
                            financeHistoricalTradeArrayList.map {
                                if (filter == it.titleTrade) {
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
                            val date : LocalDate = LocalDate.parse(it.dateTrade, dateTimeFormatter)
                            if(date in dateStartFormat..dateEndFormat){
                                listDate.add(it)
                            }
                        }

                        list.clear()
                        list.addAll(listDate)
                    }

                    rvFinanceHistorical.layoutManager = LinearLayoutManager(UTSwapApp.instance)
                    historicalTradeAdapter = HistoricalTradeAdapter(list)
                    rvFinanceHistorical.adapter = historicalTradeAdapter
                }
            }
            "All Transactions" -> {
                val list = java.util.ArrayList<HistoricalAllTransactionsModel>()
                val listDate = java.util.ArrayList<HistoricalAllTransactionsModel>()
                val filter = SettingVariable.finance_historical_filter.value

                val dateStart = SettingVariable.finance_historical_date_start.value
                val dateEnd = SettingVariable.finance_historical_date_end.value

                binding.apply {
                    if (!filter.isNullOrEmpty()) {
                        txtAllTransTitle.text = filter

                        if (filter == "All Projects") {
                            layListNumber.visibility = View.VISIBLE
                            list.addAll(financeHistoricalAllTransactionArrayList)
                        } else {
                            layListNumber.visibility = View.GONE
                            financeHistoricalAllTransactionArrayList.map {
                                if (filter == it.titleAllTrans) {
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
                            val date : LocalDate = LocalDate.parse(it.dateAllTrans, dateTimeFormatter)
                            if(date in dateStartFormat..dateEndFormat){
                                listDate.add(it)
                            }
                        }

                        list.clear()
                        list.addAll(listDate)
                    }

                    rvFinanceHistorical.layoutManager = LinearLayoutManager(UTSwapApp.instance)
                    historicalAllTransactionsAdapter = HistoricalAllTransactionsAdapter(list)
                    rvFinanceHistorical.adapter = historicalAllTransactionsAdapter
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        SettingVariable.finance_historical_date_start.value = ""
        SettingVariable.finance_historical_date_end.value = ""
        SettingVariable.finance_historical_spinner_item.value = 0
        SettingVariable.finance_historical_spinner_item.removeObservers(this@FinanceHistoricalActivity)
        SettingVariable.finance_historical_filter.removeObservers(this@FinanceHistoricalActivity)
        SettingVariable.finance_historical_selected.removeObservers(this@FinanceHistoricalActivity)
        SettingVariable.finance_historical_date_end.removeObservers(this@FinanceHistoricalActivity)
    }
}