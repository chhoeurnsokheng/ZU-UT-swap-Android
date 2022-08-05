package com.zillennium.utswap.module.finance.historicalScreen


import android.content.Intent
import android.net.Uri
import android.os.Build
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidstudy.networkmanager.Tovuti
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivityFinanceHistoricalBinding
import com.zillennium.utswap.models.financeHistorical.Historical
import com.zillennium.utswap.module.finance.historicalScreen.adapter.CustomDropDownAdapter
import com.zillennium.utswap.module.finance.historicalScreen.adapter.HistoricalAllTransactionsAdapter
import com.zillennium.utswap.module.finance.historicalScreen.adapter.HistoricalMyTransactionsAdapter
import com.zillennium.utswap.module.finance.historicalScreen.adapter.HistoricalTradeAdapter
import com.zillennium.utswap.module.finance.historicalScreen.bottomSheet.FinanceHistoricalExportFileBottomSheet
import com.zillennium.utswap.module.finance.historicalScreen.bottomSheet.FinanceHistoricalFilterBottomSheet
import com.zillennium.utswap.module.finance.historicalScreen.bottomSheet.FinanceHistoricalSelectDateRangeBottomSheet
import com.zillennium.utswap.module.finance.historicalScreen.bottomSheet.FinanceHistoricalTransactionBottomSheet
import com.zillennium.utswap.utils.Constants


class FinanceHistoricalActivity :
    BaseMvpActivity<FinanceHistoricalView.View, FinanceHistoricalView.Presenter, ActivityFinanceHistoricalBinding>(),
    FinanceHistoricalView.View {

    override var mPresenter: FinanceHistoricalView.Presenter = FinanceHistoricalPresenter()
    override val layoutResource: Int = R.layout.activity_finance_historical

    private var filterBottomSheetList: ArrayList<Historical.GetMarketNameData> = arrayListOf()
    private var financeHistoricalMyTransaction: ArrayList<Historical.DataTransaction> = arrayListOf()
    private var financeHistoricalTradeTransaction: ArrayList<Historical.DataTradeTransaction> = arrayListOf()
    private var financeHistoricalAllTransaction: ArrayList<Historical.DataAllTransaction> = arrayListOf()

    private var historicalTransactionSelected = Constants.HistoricalTransaction.MyTransactions
    private var marketNameFilter: String = ""
    private var marketNameDisplay: String = ""
    private var historicalType:String = ""
    private var dateStart: String = ""
    private var dateEnd: String = ""
    private var totalPageTrans: Int  = 1
    private var pageTrans: Int = 1

    private var selectStartDateExport: String = ""
    private var selectEndDateExport: String = ""
    private var storeFileLink: String = ""

    @RequiresApi(Build.VERSION_CODES.O)
    override fun initView() {
        super.initView()
        try {
            binding.apply {

                onCheckInternet()
                onCallApi()
                filterType()
                onSwipeRefresh()

                swipeRefresh.setColorSchemeColors(ContextCompat.getColor(UTSwapApp.instance, R.color.primary))

                /* Back button */
                backImage.setOnClickListener { finish() }

                /* Historical Transaction Filter */
                layHistoricalTransaction.setOnClickListener {
                    val financeHistoricalFilterBottomSheet = FinanceHistoricalTransactionBottomSheet(
                        historicalTransactionSelected,
                        object: FinanceHistoricalTransactionBottomSheet.CallBackListener{
                            override fun onChangeSelect(historicalTransactionSelect: String) {

                                onClearRecycleView()
                                invisibleLayoutTransaction()

                                this@FinanceHistoricalActivity.historicalTransactionSelected = historicalTransactionSelect
                                txtTransactionHistorical.text = this@FinanceHistoricalActivity.historicalTransactionSelected

                                dateStart = ""
                                dateEnd = ""
                                selectStartDateExport = ""
                                selectEndDateExport = ""
                                historicalType = "0"

                                txtSelectedDateStart.visibility = View.GONE
                                txtSelectedDateEnd.visibility = View.GONE
                                txtSelectedDateStart.text = ""
                                txtSelectedDateEnd.text = ""
                                txtSelectDateFromTo.text = "Select Date Range"

                                invisibleText()
                                bodyHistoryTransaction()
                            }
                        }
                    )
                    financeHistoricalFilterBottomSheet.show(supportFragmentManager, "Filter Transaction")
                }

                /* Filter button */
                layFilterButton.setOnClickListener {
                    val financeHistoricalFilterBottomSheet = FinanceHistoricalFilterBottomSheet(
                        filterBottomSheetList,
                        historicalTransactionSelected,
                        object: FinanceHistoricalFilterBottomSheet.CallBackFilterListener{
                            override fun onFilterChangeSelect(historicalFilterSelect: String, marketName: String) {
                                onClearRecycleView()
                                marketNameDisplay = historicalFilterSelect
                                marketNameFilter = marketName
                                invisibleText()
                                bodyHistoryTransaction()

                            }
                        }
                    )
                    financeHistoricalFilterBottomSheet.show(supportFragmentManager, "Filter History UT")
                }

                /* Select Date Range */
                laySelectDateRange.setOnClickListener {
                    val financeHistoricalSelectDateRangeBottomSheet = FinanceHistoricalSelectDateRangeBottomSheet(
                        dateStart,
                        dateEnd,
                        object: FinanceHistoricalSelectDateRangeBottomSheet.CallBackDateListener{
                            override fun onSelectDateChangeSelect(startDate: String, endDate: String) {
                                onClearRecycleView()
                                dateStart = startDate
                                dateEnd = endDate
                                txtSelectedDateStart.visibility = View.VISIBLE
                                txtSelectedDateEnd.visibility = View.VISIBLE
                                txtSelectedDateStart.text = dateStart
                                txtSelectedDateEnd.text = dateEnd
                                txtSelectDateFromTo.text = "To"
                                bodyHistoryTransaction()
                            }
                        }
                    )
                    financeHistoricalSelectDateRangeBottomSheet.show(supportFragmentManager, "Select date range")
                }

                /* Export Button */
                exportAsPdf.setOnClickListener {
                    val financeHistoricalExportFileBottomSheet = FinanceHistoricalExportFileBottomSheet(
                        selectStartDateExport,
                        selectEndDateExport,
                        object : FinanceHistoricalExportFileBottomSheet.CallBackDateExportListener {
                            override fun onExportDate(
                                startDateExport: String,
                                endDateExport: String
                            ) {
                                selectStartDateExport = startDateExport
                                selectEndDateExport = endDateExport
                                mPresenter.onExportHistorical(Historical.exportHistoricalObject(marketNameFilter, selectStartDateExport, selectEndDateExport), UTSwapApp.instance)
                            }
                        }
                    )
                    financeHistoricalExportFileBottomSheet.show(supportFragmentManager, "Export File")
                }

                readMore.setOnClickListener {
                    when (historicalTransactionSelected) {
                        Constants.HistoricalTransaction.MyTransactions -> {
                            mPresenter.onGetUserTransaction(Historical.UserTransactionObject(marketNameFilter, dateStart, dateEnd, historicalType, pageTrans.toString()), UTSwapApp.instance)
                            progressBarReadMore.visibility = View.VISIBLE
                        }
                        Constants.HistoricalTransaction.Trade -> {

                            mPresenter.onGetTradeTransaction(Historical.TradeTransactionObject(marketNameFilter, dateStart, dateEnd, pageTrans.toString()), UTSwapApp.instance)
                            progressBarReadMore.visibility = View.VISIBLE
                        }
                        Constants.HistoricalTransaction.AllTransactions -> {
                            mPresenter.onGetAllTransaction(Historical.AllTransactionObject(marketNameFilter, dateStart, dateEnd, pageTrans.toString()), UTSwapApp.instance)
                            progressBarReadMore.visibility = View.VISIBLE
                        }
                    }
                }
            }
        } catch (error: Exception) {
            // Must be safe
        }
    }

    private fun onCheckInternet(){
        Tovuti.from(UTSwapApp.instance).monitor{ _, isConnected, _ ->
            if(isConnected)
            {
                when(historicalTransactionSelected) {
                    Constants.HistoricalTransaction.MyTransactions -> {
                        binding.layMyTransactions.visibility = View.VISIBLE
                    }
                    Constants.HistoricalTransaction.Trade -> {
                        binding.layTrade.visibility = View.VISIBLE
                    }
                    Constants.HistoricalTransaction.AllTransactions -> {
                        binding.layAllTransaction.visibility = View.VISIBLE
                    }
                }
                binding.progressBar.visibility = View.GONE
                binding.swipeRefresh.visibility = View.VISIBLE
                onCallApi()
            }else{
                onClearRecycleView()
                invisibleLayoutTransaction()
                invisibleText()
                binding.swipeRefresh.visibility = View.GONE
                binding.progressBar.visibility = View.VISIBLE
            }
        }
    }

    private fun onCallApi() {
        when(historicalTransactionSelected) {
            Constants.HistoricalTransaction.MyTransactions -> {
                mPresenter.onGetMarketName()
            }
        }
    }

    /* Body */
    private fun bodyHistoryTransaction(){
        binding.apply {
            when (historicalTransactionSelected){
                Constants.HistoricalTransaction.MyTransactions -> {
                    pageTrans = 1
                    txtHistoryMyTrans.text = marketNameDisplay
                    mPresenter.onGetUserTransaction(Historical.UserTransactionObject(marketNameFilter, dateStart, dateEnd, historicalType, pageTrans.toString()), UTSwapApp.instance)
                }
                Constants.HistoricalTransaction.Trade -> {
                    pageTrans = 1
                    txtTradeTitle.text = marketNameDisplay
                    mPresenter.onGetTradeTransaction(Historical.TradeTransactionObject(marketNameFilter, dateStart, dateEnd, pageTrans.toString()), UTSwapApp.instance)
                }
                Constants.HistoricalTransaction.AllTransactions -> {
                    pageTrans = 1
                    txtAllTransTitle.text = marketNameDisplay
                    mPresenter.onGetAllTransaction(Historical.AllTransactionObject(marketNameFilter, dateStart, dateEnd, pageTrans.toString()), UTSwapApp.instance)
                }
            }
        }
    }

    /* Get Data from API */
    override fun onGetMarketNameSuccess(data: List<Historical.GetMarketNameData>) {
        binding.apply {
            if (data.isNotEmpty()) {
                filterBottomSheetList.addAll(data)
                marketNameFilter = data[0].market_name.toString()
                marketNameDisplay = data[0].name.toString()

                bodyHistoryTransaction()

            } else {
                Toast.makeText(UTSwapApp.instance, "No Transaction", Toast.LENGTH_LONG).show()
            }
        }
    }
    override fun onGetMarketNameFail(data: Historical.GetMarketName) {}
    override fun onGetUserTransactionSuccess(data: Historical.UserTransactionData) {
        binding.apply {
            invisibleText()
            layMyTransactions.visibility = View.VISIBLE
            progressBarReadMore.visibility = View.GONE
            swipeRefresh.isRefreshing = false

            totalPageTrans = data.TOTAL_PAGE!!

            if(totalPageTrans >= pageTrans){
                if (data.TRANSACTION.isNotEmpty()) {
                    layMyTransactions.visibility = View.VISIBLE
                    financeHistoricalMyTransaction.addAll(data.TRANSACTION)
                    rvFinanceHistorical.layoutManager = LinearLayoutManager(UTSwapApp.instance)
                    val historicalMyTransactionsAdapter = HistoricalMyTransactionsAdapter()
                    historicalMyTransactionsAdapter.items = financeHistoricalMyTransaction
                    rvFinanceHistorical.adapter = historicalMyTransactionsAdapter
                }

                if(totalPageTrans == pageTrans){
                    // Text End
                    txtEnd.visibility = View.VISIBLE
                }else{
                    // Read More
                    layNewsLoading.visibility = View.VISIBLE
                    pageTrans++
                }
            }else{
                onClearRecycleView()
                txtNoData.visibility = View.VISIBLE
            }
        }
    }
    override fun onGetUserTransactionFail(data: Historical.UserTransaction) {
        binding.apply {
            onClearRecycleView()
            swipeRefresh.isRefreshing = false
        }
    }
    override fun onGetTradeTransactionSuccess(data: Historical.TradeTransactionData) {
        binding.apply {
            invisibleText()
            layTrade.visibility = View.VISIBLE
            progressBarReadMore.visibility = View.GONE
            swipeRefresh.isRefreshing = false

            totalPageTrans = data.TOTAL_PAGE!!

            if(totalPageTrans >= pageTrans) {
                if (data.TRADE_TRANSACTION.isNotEmpty()) {
                    layTrade.visibility = View.VISIBLE
                    financeHistoricalTradeTransaction.addAll(data.TRADE_TRANSACTION)
                    rvFinanceHistorical.layoutManager = LinearLayoutManager(UTSwapApp.instance)
                    val historicalTradeTransactionAdapter = HistoricalTradeAdapter()
                    historicalTradeTransactionAdapter.items = financeHistoricalTradeTransaction
                    rvFinanceHistorical.adapter = historicalTradeTransactionAdapter
                }

                if(totalPageTrans == pageTrans){
                    // Text End
                    txtEnd.visibility = View.VISIBLE
                }else{
                    // Read More
                    layNewsLoading.visibility = View.VISIBLE
                    pageTrans++
                }
            }else{
                onClearRecycleView()
                txtNoData.visibility = View.VISIBLE
            }
        }
    }
    override fun onGetTradeTransactionFail(data: Historical.TradeTransaction) {
        binding.apply {
            onClearRecycleView()
            swipeRefresh.isRefreshing = false
        }
    }
    override fun onGetAllTransactionSuccess(data: Historical.AllTransactionData) {
        binding.apply {
            invisibleText()
            layAllTransaction.visibility = View.VISIBLE
            swipeRefresh.isRefreshing = false
            progressBarReadMore.visibility = View.GONE

            totalPageTrans = data.TOTAL_PAGE!!

            if(totalPageTrans >= pageTrans) {
                if (data.TRADE_TRANSACTION.isNotEmpty()){
                    layAllTransaction.visibility = View.VISIBLE
                    financeHistoricalAllTransaction.addAll(data.TRADE_TRANSACTION)
                    rvFinanceHistorical.layoutManager = LinearLayoutManager(UTSwapApp.instance)
                    val historicalAllTransactionAdapter = HistoricalAllTransactionsAdapter()
                    historicalAllTransactionAdapter.items = financeHistoricalAllTransaction
                    rvFinanceHistorical.adapter = historicalAllTransactionAdapter
                }

                if(totalPageTrans == pageTrans){
                    // Text End
                    txtEnd.visibility = View.VISIBLE
                }else{
                    // Read More
                    layNewsLoading.visibility = View.VISIBLE
                    pageTrans++
                }
            } else {
                onClearRecycleView()
                layAllTransaction.visibility = View.VISIBLE
                txtNoData.visibility = View.VISIBLE
            }
        }
    }
    override fun onGetAllTransactionFail(data: Historical.AllTransaction) {
        binding.apply {
            onClearRecycleView()
            swipeRefresh.isRefreshing = false
        }
    }
    override fun onExportHistoricalSuccess(data: Historical.DataExportHistorical) {
        binding.apply {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(data.FILE_PATH))
            startActivity(browserIntent)
        }
    }
    override fun onExportHistoricalFail(data: Historical.exportHistorical) {}

    /* Spinner */
    private fun filterType(){
        binding.apply {
            val data = ArrayList<String>()
            data.add("ALL")
            data.add("BUY")
            data.add("SELL")

            historicalSpinnerDropdown.adapter =
                CustomDropDownAdapter(this@FinanceHistoricalActivity, data)
            historicalSpinnerDropdown.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    adapterView: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    onClearRecycleView()
                    if(historicalType == ""){
                        historicalType = position.toString()
                    }else{
                        historicalType = position.toString()
                        bodyHistoryTransaction()
                    }

                }

                override fun onNothingSelected(p0: AdapterView<*>?) {}
            }
        }
    }

    private fun onSwipeRefresh(){
        binding.apply {
            //swipe refresh to get page 1 again
            swipeRefresh.setOnRefreshListener {
                onClearRecycleView()
                pageTrans = 1
                txtEnd.visibility = View.GONE
                txtNoData.visibility = View.GONE
                when (historicalTransactionSelected) {
                    Constants.HistoricalTransaction.MyTransactions -> {
                        invisibleText()
                        mPresenter.onGetUserTransaction(Historical.UserTransactionObject(marketNameFilter, dateStart, dateEnd, historicalType, "1"), UTSwapApp.instance)
                    }
                    Constants.HistoricalTransaction.Trade -> {
                        invisibleText()
                        mPresenter.onGetTradeTransaction(Historical.TradeTransactionObject(marketNameFilter, dateStart, dateEnd, "1"), UTSwapApp.instance)
                    }
                    Constants.HistoricalTransaction.AllTransactions -> {
                        invisibleText()
                        mPresenter.onGetAllTransaction(Historical.AllTransactionObject(marketNameFilter, dateStart, dateEnd,"1"), UTSwapApp.instance)
                    }
                }
            }
        }
    }

    /* Clear RecyclerView */
    private fun onClearRecycleView(){
        binding.apply {
            financeHistoricalMyTransaction.clear()
            financeHistoricalTradeTransaction.clear()
            financeHistoricalAllTransaction.clear()
            rvFinanceHistorical.adapter?.notifyDataSetChanged()
        }
    }

    private fun invisibleLayoutTransaction(){
        binding.apply {
            layMyTransactions.visibility = View.GONE
            layTrade.visibility = View.GONE
            layAllTransaction.visibility = View.GONE
        }
    }

    private fun invisibleText(){
        binding.apply {
            txtEnd.visibility = View.GONE
            txtNoData.visibility = View.GONE
            layNewsLoading.visibility = View.GONE
        }
    }

}
