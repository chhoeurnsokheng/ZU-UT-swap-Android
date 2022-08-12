package com.zillennium.utswap.module.finance.historicalScreen


import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Handler
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidstudy.networkmanager.Tovuti
import com.zillennium.utswap.Datas.GlobalVariable.SettingVariable
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivityFinanceHistoricalBinding
import com.zillennium.utswap.models.financeHistorical.Historical
import com.zillennium.utswap.module.finance.historicalScreen.adapter.CustomDropDownAdapter
import com.zillennium.utswap.module.finance.historicalScreen.adapter.HistoricalAllTransactionsAdapter
import com.zillennium.utswap.module.finance.historicalScreen.adapter.HistoricalMyTransactionsAdapter
import com.zillennium.utswap.module.finance.historicalScreen.adapter.HistoricalTradeDateAdapter
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
    private var financeHistoricalTradeTransaction: ArrayList<Historical.TradeTransactionDate> = arrayListOf()
    private var financeHistoricalAllTransaction: ArrayList<Historical.DataAllTransaction> = arrayListOf()

    private var historicalMyTransactionsAdapter: HistoricalMyTransactionsAdapter? = null
    private var historicalTradeTransactionAdapter: HistoricalTradeDateAdapter? = null
    private var historicalAllTransactionAdapter: HistoricalAllTransactionsAdapter? = null

    var dateGroup = ""
    var childData: ArrayList<Historical.DataTradeDateTransaction> = arrayListOf()

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



    private var lastPosition = 0

    @RequiresApi(Build.VERSION_CODES.O)
    override fun initView() {
        super.initView()
        try {
            binding.apply {

                rvFinanceHistorical.layoutManager = LinearLayoutManager(UTSwapApp.instance)

                historicalAllTransactionAdapter = HistoricalAllTransactionsAdapter()

                onCheckInternet()
                filterType()
                onSwipeRefresh()
                loadMoreData()


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

                                dateGroup = ""
                                childData.clear()

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

                                pageTrans = 1

                                loadMoreData()
                                invisibleText()
                                requestData()
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
                                dateGroup = ""
                                childData.clear()
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
                                dateGroup = ""
                                childData.clear()
                                onClearRecycleView()
                                dateStart = startDate
                                dateEnd = endDate
                                if (endDate.isNotEmpty()){
                                    txtSelectedDateStart.visibility = View.VISIBLE
                                    txtSelectedDateEnd.visibility = View.VISIBLE
                                    txtSelectedDateStart.text = dateStart
                                    txtSelectedDateEnd.text = dateEnd
                                    txtSelectDateFromTo.text = "To"
                                }

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
                        requestData()
                        binding.layMyTransactions.visibility = View.VISIBLE
                    }
                    Constants.HistoricalTransaction.Trade -> {
                        binding.layTrade.visibility = View.VISIBLE
                        requestData()
                    }
                    Constants.HistoricalTransaction.AllTransactions -> {
                        requestData()
                        binding.layAllTransaction.visibility = View.VISIBLE
                    }
                }
                binding.progressBar.visibility = View.GONE
                binding.swipeRefresh.visibility = View.VISIBLE
                binding.layNewsLoading.visibility = View.GONE
               // requestData()
            }else{
                onClearRecycleView()
                invisibleLayoutTransaction()
                invisibleText()
                binding.swipeRefresh.visibility = View.GONE
                binding.progressBar.visibility = View.VISIBLE
            }
        }
    }

    private fun requestData() {
        mPresenter.onGetMarketName()
//        when(historicalTransactionSelected) {
//            Constants.HistoricalTransaction.MyTransactions -> {
//                mPresenter.onGetMarketName()
//            }
//            Constants.HistoricalTransaction.Trade -> {
//                mPresenter.onGetMarketName()
//            }
//            Constants.HistoricalTransaction.AllTransactions -> {
//                mPresenter.onGetMarketName()
//            }
//        }
    }

    private fun checkingScrollCondition() {
        binding.apply {
            when (historicalTransactionSelected) {
                Constants.HistoricalTransaction.MyTransactions -> {

                    if (lastPosition == financeHistoricalMyTransaction.size - 1 && pageTrans < totalPageTrans){
                        layNewsLoading.visibility = View.VISIBLE
                        pageTrans++
                        mPresenter.onGetUserTransaction(Historical.UserTransactionObject(marketNameFilter, dateStart, dateEnd, historicalType, pageTrans.toString()), UTSwapApp.instance)
                    }
                }
                Constants.HistoricalTransaction.Trade -> {
                    if (lastPosition == financeHistoricalTradeTransaction.size - 1 && pageTrans < totalPageTrans){
                        layNewsLoading.visibility = View.VISIBLE
                        pageTrans++
                        mPresenter.onGetTradeTransaction(Historical.TradeTransactionObject(marketNameFilter, dateStart, dateEnd, pageTrans.toString()), UTSwapApp.instance)
                    }
                }
                Constants.HistoricalTransaction.AllTransactions -> {
                    if (lastPosition == financeHistoricalAllTransaction.size - 1 && pageTrans < totalPageTrans){
                        layNewsLoading.visibility = View.VISIBLE
                        pageTrans++
                        mPresenter.onGetAllTransaction(Historical.AllTransactionObject(marketNameFilter, dateStart, dateEnd, pageTrans.toString()), UTSwapApp.instance)
                    }
                }
            }
        }
    }

    /* Auto loading data */
    private fun loadMoreData(){
        binding.apply {
            rvFinanceHistorical.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (dy > 0){
                        lastPosition = (rvFinanceHistorical.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()

                        checkingScrollCondition()
                        requestData()
                    }
                }
            })
        }
    }

    /* Get Data from API */
    override fun onGetMarketNameSuccess(data: List<Historical.GetMarketNameData>) {
        binding.apply {
            if (data.isNotEmpty()) {
                when(historicalTransactionSelected) {
                    Constants.HistoricalTransaction.MyTransactions -> {
                        filterBottomSheetList.clear()
                        filterBottomSheetList.addAll(data)
                        marketNameFilter = data[0].market_name.toString()
                        marketNameDisplay = data[0].name.toString()
                        SettingVariable.finance_historical_filter.value = data[0].name.toString()

                        bodyHistoryTransaction()
                    }
                    Constants.HistoricalTransaction.Trade -> {
                        filterBottomSheetList.clear()
                        filterBottomSheetList.addAll(data)
                        marketNameFilter = ""
                        marketNameDisplay = "All Projects"
                        SettingVariable.finance_historical_filter.value = "All Projects"

                        bodyHistoryTransaction()
                    }
                    Constants.HistoricalTransaction.AllTransactions -> {
                        filterBottomSheetList.clear()
                        filterBottomSheetList.addAll(data)
                        marketNameFilter = data[0].market_name.toString()
                        marketNameDisplay = data[0].name.toString()
                        SettingVariable.finance_historical_filter.value = data[0].name.toString()

                        bodyHistoryTransaction()
                    }
                }
            } else {
                Toast.makeText(UTSwapApp.instance, "No Transaction", Toast.LENGTH_LONG).show()
            }
        }
    }
    override fun onGetMarketNameFail(data: Historical.GetMarketName) {}


    override fun onGetUserTransactionSuccess(data: Historical.UserTransactionData) {
        binding.apply {
            loadingProgressBar.visibility = View.GONE
            invisibleText()
            layMyTransactions.visibility = View.VISIBLE
            layNewsLoading.visibility = View.GONE
            swipeRefresh.isRefreshing = false

            totalPageTrans = data.TOTAL_PAGE!!
            if(totalPageTrans >= pageTrans){
                if (data.TRANSACTION.isNotEmpty()) {
                    layMyTransactions.visibility = View.VISIBLE
                    financeHistoricalMyTransaction.addAll(data.TRANSACTION)
                    historicalMyTransactionsAdapter = HistoricalMyTransactionsAdapter(financeHistoricalMyTransaction)
                    rvFinanceHistorical.adapter = historicalMyTransactionsAdapter
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
            loadingProgressBar.visibility = View.GONE
            txtNoData.visibility = View.VISIBLE
            swipeRefresh.isRefreshing = false
        }
    }
    override fun onGetTradeTransactionSuccess(data: Historical.TradeTransactionData) {
        binding.apply {
            loadingProgressBar.visibility = View.GONE
            invisibleText()
            layTrade.visibility = View.VISIBLE
            layNewsLoading.visibility = View.GONE
            swipeRefresh.isRefreshing = false

            totalPageTrans = data.TOTAL_PAGE!!
//            println("Pages ::: Trade $pageTrans")
            if(totalPageTrans >= pageTrans) {
                if (data.TRADE_TRANSACTION.isNotEmpty()) {
                    layTrade.visibility = View.VISIBLE

                    for(tradeTransaction in data.TRADE_TRANSACTION){
                        if(dateGroup == ""){
                            dateGroup = tradeTransaction.addtimeReadble.toString()
                        }
                        if(dateGroup != tradeTransaction.addtimeReadble){

                            if(financeHistoricalTradeTransaction.isNotEmpty()){
                                if(financeHistoricalTradeTransaction.last().TRADE_DATE == dateGroup){
                                    financeHistoricalTradeTransaction.removeLast()
                                }
                            }

                            financeHistoricalTradeTransaction.add(Historical.TradeTransactionDate(dateGroup, childData))
                            dateGroup = tradeTransaction.addtimeReadble.toString()
                            childData = arrayListOf()
                        }
                        childData.add(Historical.DataTradeDateTransaction(
                            tradeTransaction.market,
                            tradeTransaction.opened_price,
                            tradeTransaction.closed_price,
                            tradeTransaction.min_value,
                            tradeTransaction.high_value,
                            tradeTransaction.volume,
                            tradeTransaction.addtimeReadble
                        ))
                    }

                    if(childData.isNotEmpty()){
                        financeHistoricalTradeTransaction.add(Historical.TradeTransactionDate(dateGroup, childData))
                    }

                    historicalTradeTransactionAdapter  = HistoricalTradeDateAdapter(financeHistoricalTradeTransaction)
                    rvFinanceHistorical.adapter = historicalTradeTransactionAdapter
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
            loadingProgressBar.visibility = View.GONE
            txtNoData.visibility = View.VISIBLE
            swipeRefresh.isRefreshing = false
        }
    }
    override fun onGetAllTransactionSuccess(data: Historical.AllTransactionData) {
        binding.apply {
            loadingProgressBar.visibility = View.GONE
            invisibleText()
            layAllTransaction.visibility = View.VISIBLE
            swipeRefresh.isRefreshing = false
            layNewsLoading.visibility = View.GONE

            totalPageTrans = data.TOTAL_PAGE!!
//            println("Pages ::: All $pageTrans")
            if(totalPageTrans >= pageTrans) {
                if (data.TRADE_TRANSACTION.isNotEmpty()){
                    if (pageTrans == 1){
                        financeHistoricalAllTransaction.addAll(data.TRADE_TRANSACTION)
                        pageTrans++
                        mPresenter.onGetAllTransaction(Historical.AllTransactionObject(marketNameFilter, dateStart, dateEnd, pageTrans.toString()), UTSwapApp.instance)
                    }else{
                        layAllTransaction.visibility = View.VISIBLE
                        financeHistoricalAllTransaction.addAll(data.TRADE_TRANSACTION)
                        historicalAllTransactionAdapter?.items = financeHistoricalAllTransaction
                        rvFinanceHistorical.adapter = historicalAllTransactionAdapter
                    }
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
            loadingProgressBar.visibility = View.GONE
            txtNoData.visibility = View.VISIBLE
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

    /* Body */
    private fun bodyHistoryTransaction(){
        binding.apply {
            when (historicalTransactionSelected){
                Constants.HistoricalTransaction.MyTransactions -> {
                    pageTrans = 1
                    txtHistoryMyTrans.text = marketNameDisplay
                    layMyTransactions.visibility = View.VISIBLE
                    loadingProgressBar.visibility = View.VISIBLE
                    mPresenter.onGetUserTransaction(Historical.UserTransactionObject(marketNameFilter, dateStart, dateEnd, historicalType, pageTrans.toString()), UTSwapApp.instance)
                }
                Constants.HistoricalTransaction.Trade -> {
                    pageTrans = 1
                    dateGroup = ""
                    childData.clear()
                    txtTradeTitle.text = marketNameDisplay
                    layTrade.visibility = View.VISIBLE
                    loadingProgressBar.visibility = View.VISIBLE
                    mPresenter.onGetTradeTransaction(Historical.TradeTransactionObject(marketNameFilter, dateStart, dateEnd, pageTrans.toString()), UTSwapApp.instance)
                }
                Constants.HistoricalTransaction.AllTransactions -> {
                    pageTrans = 1
                    txtAllTransTitle.text = marketNameDisplay
                    layAllTransaction.visibility = View.VISIBLE
                    loadingProgressBar.visibility = View.VISIBLE
                    mPresenter.onGetAllTransaction(Historical.AllTransactionObject(marketNameFilter, dateStart, dateEnd, pageTrans.toString()), UTSwapApp.instance)
                }
            }
        }
    }

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
    /* Swipe Refresh */
    private fun onSwipeRefresh(){
        binding.apply {
            //swipe refresh to get page 1 again
            swipeRefresh.setOnRefreshListener {
                onClearRecycleView()
                pageTrans = 1
                txtNoData.visibility = View.GONE
                when (historicalTransactionSelected) {
                    Constants.HistoricalTransaction.MyTransactions -> {
                        invisibleText()
                        mPresenter.onGetUserTransaction(Historical.UserTransactionObject(marketNameFilter, dateStart, dateEnd, historicalType, "1"), UTSwapApp.instance)
                    }
                    Constants.HistoricalTransaction.Trade -> {
                        invisibleText()
                        dateGroup = ""
                        childData.clear()
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
            txtNoData.visibility = View.GONE
            layNewsLoading.visibility = View.GONE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        SettingVariable.finance_historical_filter.value = ""
    }
}
