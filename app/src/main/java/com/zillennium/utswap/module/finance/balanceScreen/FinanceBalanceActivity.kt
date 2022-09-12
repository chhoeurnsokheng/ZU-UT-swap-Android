package com.zillennium.utswap.module.finance.balanceScreen

import android.app.DownloadManager
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.os.Handler
import android.view.View
import android.webkit.CookieManager
import android.webkit.URLUtil
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidstudy.networkmanager.Tovuti
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivityFinanceBalanceBinding
import com.zillennium.utswap.models.financeBalance.BalanceFinance
import com.zillennium.utswap.module.finance.balanceScreen.adapter.FinanceBalanceAdapter
import com.zillennium.utswap.module.finance.balanceScreen.bottomSheet.FinanceExportFileBottomSheet
import com.zillennium.utswap.module.finance.balanceScreen.bottomSheet.FinanceFilterBottomSheet
import com.zillennium.utswap.module.finance.balanceScreen.bottomSheet.FinanceSelectDateRangeBottomSheet
import com.zillennium.utswap.module.finance.balanceScreen.dialog.FinanceBalanceDialog
import com.zillennium.utswap.module.finance.depositScreen.depositSuccessfully.DepositSuccessfullyActivity
import com.zillennium.utswap.screens.navbar.navbar.MainActivity
import com.zillennium.utswap.utils.ClientClearData
import com.zillennium.utswap.utils.UtilKt

class FinanceBalanceActivity :
    BaseMvpActivity<FinanceBalanceView.View, FinanceBalanceView.Presenter, ActivityFinanceBalanceBinding>(),
    FinanceBalanceView.View {

    override var mPresenter: FinanceBalanceView.Presenter = FinanceBalancePresenter()
    override val layoutResource: Int = R.layout.activity_finance_balance

    private var financeBalanceList: ArrayList<BalanceFinance.GetBalanceSearchDateTransactionData>? = arrayListOf()

    private var balanceFilterSelect = ""
    private var selectedDateStart = ""
    private var selectedDateEnd = ""
    private var selectStartDateExport = ""
    private var selectEndDateExport = ""
    private var pageBalance = 1

    private var totalPageBalance = 1
    private var countLoop = 2

    private val progressDialog = ProgressDialog(UTSwapApp.instance)

    @RequiresApi(Build.VERSION_CODES.O)
    override fun initView() {
        super.initView()

        toolBar()

        try {
            binding.apply {

                onCallApi()
                onSwapRefresh()
                loadMoreData()

                swipeRefreshBalance.setColorSchemeColors(ContextCompat.getColor(UTSwapApp.instance, R.color.primary))

                /* Export as File bottom sheet dialog */
                exportAsPdf.setOnClickListener {
                    if (selectedDateStart.isNotEmpty() && selectedDateEnd.isNotEmpty()){
                        mPresenter.onGetExportBalance(BalanceFinance.ExportFinanceBalanceObject(selectedDateStart, selectedDateEnd, balanceFilterSelect), UTSwapApp.instance)
                    } else{
                        val financeExportBalance = FinanceExportFileBottomSheet(
                            object : FinanceExportFileBottomSheet.CallBackExportBalance{
                                override fun onExportBalance(
                                    startDateExport: String,
                                    endDateExport: String
                                ) {
                                    selectStartDateExport = startDateExport
                                    selectEndDateExport = endDateExport

                                    mPresenter.onGetExportBalance(BalanceFinance.ExportFinanceBalanceObject(selectStartDateExport, selectEndDateExport, balanceFilterSelect), UTSwapApp.instance)
                                }
                            }
                        )
                        financeExportBalance.show(
                            supportFragmentManager, "Export File User Balance"
                        )
                    }
                }

                /* Filter bottom sheet dialog */
                filterButton.setOnClickListener {
                    val financeFilterBottomSheet = FinanceFilterBottomSheet(
                        balanceFilterSelect,
                        object: FinanceFilterBottomSheet.CallBackFilterListener{
                            override fun onChangeFilterSelected(balanceFilterSelected: String) {
                                balanceFilterSelect = balanceFilterSelected
                                onClearList()
                                invisibleText()
                                pageBalance = 1
                                countLoop = 2
                                loadingProgressBar.visibility = View.VISIBLE
                                mPresenter.onGetUserBalanceFilterDate(BalanceFinance.GetBalanceSearchDateObject(selectedDateStart, selectedDateEnd, balanceFilterSelect, pageBalance), UTSwapApp.instance)
                            }
                        }
                    )
                    financeFilterBottomSheet.show(supportFragmentManager, "Filter User Balance")
                }

                /* Select Date Range bottom sheet dialog */
                selectDateRange.setOnClickListener {
                    val financeSelectDateRangeBottomSheetBalance = FinanceSelectDateRangeBottomSheet(
                        object: FinanceSelectDateRangeBottomSheet.CallBackSelectDateRangeBalance{
                            override fun onChangeFilterSelectDateRange(
                                selectDateStart: String,
                                selectDateEnd: String
                            ) {
                                selectedDateStart = selectDateStart
                                selectedDateEnd = selectDateEnd
                                invisibleText()
                                onClearList()
                                pageBalance = 1
                                countLoop = 2
                                loadingProgressBar.visibility = View.VISIBLE

                                if (selectDateEnd.isNotEmpty()){
                                    txtBalanceDateStart.visibility = View.VISIBLE
                                    txtBalanceDateEnd.visibility = View.VISIBLE
                                    txtBalanceDateTo.visibility = View.VISIBLE
                                    txtBalanceSelectDateFromTo.visibility = View.GONE
                                    txtBalanceDateStart.text = selectDateStart
                                    txtBalanceDateEnd.text = selectDateEnd
                                }

                                mPresenter.onGetUserBalanceFilterDate(BalanceFinance.GetBalanceSearchDateObject(selectedDateStart, selectedDateEnd, balanceFilterSelect, pageBalance), UTSwapApp.instance)

                            }
                        }
                    )
                    financeSelectDateRangeBottomSheetBalance.show(
                        supportFragmentManager, "Select Date Range User Balance"
                    )
                }

                readMore.setOnClickListener{
                    mPresenter.onGetUserBalanceFilterDate(BalanceFinance.GetBalanceSearchDateObject(selectedDateStart, selectedDateEnd, balanceFilterSelect, pageBalance), UTSwapApp.instance)
                }

            }
        } catch (error: Exception) {
            // Must be safe
        }
    }

    private fun onCallApi(){
        Tovuti.from(UTSwapApp.instance).monitor{ _, isConnected, _ ->
            if(isConnected) {
                mPresenter.onGetUserBalanceInfo(UTSwapApp.instance)
                binding.progressBar.visibility = View.GONE
                binding.loadingProgressBar.visibility = View.VISIBLE
                binding.swipeRefreshBalance.visibility = View.VISIBLE
            }else{
                binding.swipeRefreshBalance.visibility = View.GONE
                binding.progressBar.visibility = View.VISIBLE
            }
        }
    }

    /* Get Data From API */
    override fun onGetUserBalanceInfoSuccess(data: BalanceFinance.GetUserBalanceInfoData) {
        binding.apply {
            pageBalance = 1
            countLoop = 2
            txtTotalAmount.text = "$ " + data.total_balance?.let { UtilKt().formatValue(it, "###,###.##") }
            txtAvailableAmount.text = "$ " + data.available_balance?.let { UtilKt().formatValue(it.toDouble(), "###,###.##") }
            txtPendingAmount.text = "$ " + data.pending?.let { UtilKt().formatValue(it.toDouble(), "###,###.##") }
            mPresenter.onGetUserBalanceFilterDate(BalanceFinance.GetBalanceSearchDateObject(selectedDateStart, selectedDateEnd, balanceFilterSelect, pageBalance), UTSwapApp.instance)
        }
    }
    override fun onGetUserBalanceInfoFail(data: BalanceFinance.GetUserBalanceInfo) {}
    override fun onGetUserBalanceFilterDateSuccess(data: BalanceFinance.GetBalanceSearchDateData) {
        binding.apply {
            loadingProgressBar.visibility = View.GONE
            invisibleText()
            swipeRefreshBalance.isRefreshing = false

            totalPageBalance = data.total_page!!

            if (totalPageBalance >= pageBalance){
                if (data.transaction.isNotEmpty()){
                    financeBalanceList?.addAll(data.transaction)
                    rvFinanceBalance.layoutManager = LinearLayoutManager(UTSwapApp.instance)
                    val financeBalanceAdapter = FinanceBalanceAdapter(onClickAdapter)
                    financeBalanceAdapter.items = financeBalanceList!!
                    rvFinanceBalance.adapter = financeBalanceAdapter
                }
                if (totalPageBalance == pageBalance){
                    txtEnd.visibility = View.GONE
                }else{
                    // Loading More
                    Handler().postDelayed({
                        layNewsLoading.visibility = View.VISIBLE
                    }, 500)
                    pageBalance++
                }
            }else{
                onClearList()
                layNewsLoading.visibility =View.GONE
                txtNoData.visibility = View.VISIBLE
            }
        }
    }
    override fun onGetUserBalanceFilterDateFail(data: BalanceFinance.GetBalanceSearchDate) {
        binding.swipeRefreshBalance.isRefreshing = false
    }
    override fun onGetExportBalanceSuccess(data: BalanceFinance.ExportFinanceBalanceData) {
        binding.apply {
            // This is download in browser
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(data.FILE_PATH))
            startActivity(browserIntent)
            
//            val request = DownloadManager.Request(Uri.parse(data.FILE_PATH))
//            val title = URLUtil.guessFileName(data.FILE_PATH, null, null)
//            request.setTitle(title)
//            request.setDescription("Downloading File please wait ...")
//            val cookie = CookieManager.getInstance().getCookie(data.FILE_PATH)
//            request.addRequestHeader("cookie", cookie)
//            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED) // This will show notification on top when downloading the file.
//            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, title) // Title for notification.
//
//            val downloadManager = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager //This will start downloading
//            downloadManager.enqueue(request)
//
//            Toast.makeText(UTSwapApp.instance, "Downloading File.", Toast.LENGTH_SHORT).show()

        }
    }
    override fun onGetExportBalanceFail(data: BalanceFinance.ExportFinanceBalance) {}
    override fun onUserExpiredToken() {
        ClientClearData.clearDataUser()
        startActivity(Intent(this@FinanceBalanceActivity, MainActivity::class.java))
        finish()
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }

    /* On click item recyclerview */
    private val onClickAdapter: FinanceBalanceAdapter.OnClickAdapter =
        object : FinanceBalanceAdapter.OnClickAdapter {
            override fun onClickMe(financeUserBalanceTransaction: BalanceFinance.GetBalanceSearchDateTransactionData) {
                val financeBalanceDialog: FinanceBalanceDialog = FinanceBalanceDialog.newInstance(
                    financeUserBalanceTransaction.remark,
                    financeUserBalanceTransaction.addtimeReadable,
                    financeUserBalanceTransaction.id,
                    financeUserBalanceTransaction.type,
                    financeUserBalanceTransaction.total,
                    financeUserBalanceTransaction.balance,
                    financeUserBalanceTransaction.fee_admin,
                    financeUserBalanceTransaction.fee,
                )
                financeBalanceDialog.show(supportFragmentManager, "Balance Data Detail")
            }
        }

    /* Auto Loading */
    private fun loadMoreData(){
        binding.apply {
            nestedRvBalance.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
                if (v.getChildAt(v.childCount - 1) != null) {
                    if (scrollY > oldScrollY) {
                        if (countLoop <= totalPageBalance) {
                            if (scrollY >= (v.getChildAt(v.childCount - 1).measuredHeight - v.measuredHeight)) {
                                Handler().postDelayed({
                                    readMore.performClick()
                                }, 500)
                                countLoop++
                            }
                        }
                    }
                }
            })
        }
    }

    private fun onSwapRefresh(){
        binding.apply {
            swipeRefreshBalance.setOnRefreshListener {
                onClearList()
                invisibleText()
                mPresenter.onGetUserBalanceInfo(UTSwapApp.instance)
            }
        }
    }

    private fun onClearList(){
        financeBalanceList?.clear()
        binding.rvFinanceBalance.adapter?.notifyDataSetChanged()
    }

    private fun invisibleText(){
        binding.apply {
            txtNoData.visibility = View.GONE
            layNewsLoading.visibility = View.GONE
        }
    }

    private fun toolBar() {
        setSupportActionBar(binding.includeLayout.tb)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_left)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.includeLayout.apply {
            tbTitle.setText(R.string.balance)
            tbTitle.setTextColor(ContextCompat.getColor(applicationContext, R.color.primary))
            tb.setNavigationOnClickListener {
                finish()
                if (DepositSuccessfullyActivity.backToHome){
                    startActivity(Intent(this@FinanceBalanceActivity,MainActivity::class.java))
                }
            }
        }
    }

}