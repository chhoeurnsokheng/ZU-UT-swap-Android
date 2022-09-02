package com.zillennium.utswap.module.main.portfolio

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.BlurMaskFilter
import android.graphics.MaskFilter
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidstudy.networkmanager.Tovuti
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.zillennium.utswap.Datas.GlobalVariable.SessionVariable
import com.zillennium.utswap.Datas.GlobalVariable.SettingVariable
import com.zillennium.utswap.Datas.StoredPreferences.SessionPreferences
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.FragmentNavbarPortfolioBinding
import com.zillennium.utswap.models.financeHistorical.Historical
import com.zillennium.utswap.models.home.BannerObj
import com.zillennium.utswap.models.portfolio.*
import com.zillennium.utswap.module.account.accountScreen.AccountActivity
import com.zillennium.utswap.module.main.portfolio.adapter.*
import com.zillennium.utswap.module.main.portfolio.dialog.FilterPortfolioDialogBottomSheet
import com.zillennium.utswap.module.security.securityActivity.signInScreen.SignInActivity
import com.zillennium.utswap.module.system.notification.NotificationActivity
import com.zillennium.utswap.utils.Constants
import com.zillennium.utswap.utils.UtilKt
import okhttp3.internal.notify

class PortfolioFragment :
    BaseMvpFragment<PortfolioView.View, PortfolioView.Presenter, FragmentNavbarPortfolioBinding>(),
    PortfolioView.View {

    override var mPresenter: PortfolioView.Presenter = PortfolioPresenter()
    override val layoutResource: Int = R.layout.fragment_navbar_portfolio

    private var performanceList: ArrayList<Portfolio.GetPortfolioDashBoard> = arrayListOf()
    private var weightList: ArrayList<Portfolio.GetPortfolioDashBoard> = arrayListOf()
    private var balanceList: ArrayList<Portfolio.GetPortfolioDashBoard> = arrayListOf()
    private var priceList: ArrayList<Portfolio.GetPortfolioDashBoard> = arrayListOf()
    private var changeList: ArrayList<Portfolio.GetPortfolioDashBoard> = arrayListOf()

    private var portfolioSelectType = Constants.PortfolioFilter.Change
    private var typePortfolio = 2

    var blurCondition = true
    private val blurMask: MaskFilter = BlurMaskFilter(50f, BlurMaskFilter.Blur.NORMAL)
    private var filter: Int = 0 // 0 = no sort, 1 = asc sort, 2 = desc sort

    private var dataSets = ArrayList<ILineDataSet>()
    private var data: LineData? = null


    @SuppressLint("UseCompatLoadingForDrawables", "NotifyDataSetChanged", "SetTextI18n")
    override fun initView() {
        super.initView()
        try {
            binding.apply {

                mPresenter.getPortfolioDashboardChart(requireActivity())


                SessionVariable.SESSION_STATUS.observe(this@PortfolioFragment){
                    requestData()
                }


                onCallApi()
                onCheckUserKYC()
                onLayoutHeader()
                onUserBalancePortfolio()
                onGetDiagram()

                layFilter.setOnClickListener {
                    layFilter.isEnabled = false
                    layFilter.postDelayed({ layFilter.isEnabled = true }, 1000)
                    val filterPortfolioDialogBottomSheet = FilterPortfolioDialogBottomSheet(
                        portfolioSelectType,
                        object : FilterPortfolioDialogBottomSheet.CallBackTypeListener {
                            override fun onChangeTypeSelected(portfolioSelectedType: String) {
                                portfolioSelectType = portfolioSelectedType

                                btnFilter.text = portfolioSelectType
                                onClearList()
                                invisibleLayout()
                                onStoreTypeStringToInt()
                                loadingProgressBar.visibility = View.VISIBLE
                                layTradingBalance.visibility = View.GONE

                                mPresenter.onGetPortfolio(
                                    Portfolio.GetPortfolioObject(typePortfolio),
                                    requireActivity()
                                )
                            }
                        }
                    )
                    filterPortfolioDialogBottomSheet.show(
                        requireActivity().supportFragmentManager,
                        "filter_portfolio"
                    )
                }
            }

        } catch (error: Exception) {
            // Must be safe
        }
    }

    private fun requestData(){
        binding.btnFilter.text = portfolioSelectType
        mPresenter.onGetPortfolio(Portfolio.GetPortfolioObject(typePortfolio), requireActivity())
    }
    private fun onCallApi(){

        binding.apply {
            Tovuti.from(UTSwapApp.instance).monitor { _, isConnected, _ ->
                if (isConnected) {
                    onStoreTypeStringToInt()
                    loadingProgressBar.visibility = View.VISIBLE
                    layTradingBalance.visibility = View.GONE
                    btnFilter.text = portfolioSelectType
                    mPresenter.onGetPortfolio(
                        Portfolio.GetPortfolioObject(typePortfolio),
                        requireActivity()
                    )
                }
            }
        }
    }

    override fun onGetPortfolioSuccess(data: Portfolio.GetPortfolioData) {
        mPresenter.getPortfolioDashboardChart(requireActivity())
        binding.apply {
            loadingProgressBar.visibility = View.GONE
            layTradingBalance.visibility = View.VISIBLE
            txtBalance.text =
                "$ " + data.total_user_balance?.let { UtilKt().formatValue(it, "###,###.##") }

            filter = 0

            invisibleLayout()
            lineChart.visibility = View.GONE
            chartPie.visibility = View.GONE
            onClearList()

            if (data.profolio_dashboard.isNotEmpty()) {
                when (portfolioSelectType) {
                    Constants.PortfolioFilter.Change -> {
                        linearLayoutChange.visibility = View.VISIBLE

                        changeList.addAll(data.profolio_dashboard)
                        rvFilter.layoutManager = LinearLayoutManager(UTSwapApp.instance)
                        val changePortfolioAdapter = ChangeAdapter()
                        changePortfolioAdapter.items = changeList
                        rvFilter.adapter = changePortfolioAdapter

                        imgSortChange.setImageResource(R.drawable.ic_sort_arrow_up_down)
                        laySortChange.setOnClickListener {

                            val list = arrayListOf<Portfolio.GetPortfolioDashBoard>()
                            list.addAll(changeList)
                            filter++

                            when (filter) {
                                2 -> {
                                    filter = 2
                                    imgSortChange.setImageResource(R.drawable.ic_sort_arrow_up_down_selected)
                                    imgSortChange.rotation = 180f
                                    list.sortByDescending {
                                        it.mkt_project_change
                                    }
                                }
                                1 -> {
                                    filter = 1
                                    imgSortChange.setImageResource(R.drawable.ic_sort_arrow_up_down_selected)
                                    imgSortChange.rotation = 0f
                                    list.sortBy {
                                        it.mkt_project_change
                                    }
                                }
                                else -> {
                                    filter = 0
                                    imgSortChange.setImageResource(R.drawable.ic_sort_arrow_up_down)
                                }
                            }

                            changePortfolioAdapter.items = list
                            rvFilter.adapter = changePortfolioAdapter
                        }

                        lineChart.visibility = View.VISIBLE
                        txtTradingBalance.text = "$ " + data.total_market_value?.let {
                            UtilKt().formatValue(
                                it,
                                "###,###.##"
                            )
                        }

                    }
                    Constants.PortfolioFilter.Performance -> {
                        linearLayoutPerformance.visibility = View.VISIBLE

                        performanceList.addAll(data.profolio_dashboard)
                        rvFilter.layoutManager = LinearLayoutManager(UTSwapApp.instance)
                        val performancePortfolioAdapter = PerformanceAdapter()
                        performancePortfolioAdapter.items = performanceList
                        rvFilter.adapter = performancePortfolioAdapter

                        txtPerformance.text = Constants.PortfolioFilter.Performance

                        imgSortPerformance.setImageResource(R.drawable.ic_sort_arrow_up_down)
                        laySortPerformance.setOnClickListener {
                            val list = arrayListOf<Portfolio.GetPortfolioDashBoard>()
                            list.addAll(performanceList)
                            filter++

                            when (filter) {
                                2 -> {
                                    filter = 2
                                    imgSortPerformance.setImageResource(R.drawable.ic_sort_arrow_up_down_selected)
                                    imgSortPerformance.rotation = 180f
                                    list.sortByDescending {
                                        it.mkt_project_perf
                                    }
                                }
                                1 -> {
                                    filter = 1
                                    imgSortPerformance.setImageResource(R.drawable.ic_sort_arrow_up_down_selected)
                                    imgSortPerformance.rotation = 0f
                                    list.sortBy {
                                        it.mkt_project_perf
                                    }
                                }
                                else -> {
                                    filter = 0
                                    imgSortPerformance.setImageResource(R.drawable.ic_sort_arrow_up_down)
                                }
                            }

                            performancePortfolioAdapter.items = list
                            rvFilter.adapter = performancePortfolioAdapter
                        }

                        lineChart.visibility = View.VISIBLE
                        txtTradingBalance.text = "$ " + data.total_market_value?.let {
                            UtilKt().formatValue(
                                it,
                                "###,###.##"
                            )
                        }
                    }
                    Constants.PortfolioFilter.Price -> {
                        linearLayoutPrice.visibility = View.VISIBLE

                        priceList.addAll(data.profolio_dashboard)
                        rvFilter.layoutManager = LinearLayoutManager(UTSwapApp.instance)
                        val pricePortfolioAdapter = PriceAdapter()
                        pricePortfolioAdapter.items = priceList
                        rvFilter.adapter = pricePortfolioAdapter

                        lineChart.visibility = View.VISIBLE
                        txtTradingBalance.text = "$ " + data.total_market_value?.let {
                            UtilKt().formatValue(
                                it,
                                "###,###.##"
                            )
                        }
                    }
                    Constants.PortfolioFilter.Balance -> {
                        linearLayoutBalance.visibility = View.VISIBLE

                        balanceList.addAll(data.profolio_dashboard)
                        rvFilter.layoutManager = LinearLayoutManager(UTSwapApp.instance)
                        val balancePortfolioAdapter = BalanceAdapter()
                        balancePortfolioAdapter.items = balanceList
                        rvFilter.adapter = balancePortfolioAdapter

                        imgSortBalance.setImageResource(R.drawable.ic_sort_arrow_up_down)
                        layBalance.setOnClickListener {

                            val list = arrayListOf<Portfolio.GetPortfolioDashBoard>()
                            list.addAll(balanceList)
                            filter++

                            when (filter) {
                                2 -> {
                                    filter = 2
                                    imgSortBalance.setImageResource(R.drawable.ic_sort_arrow_up_down_selected)
                                    imgSortBalance.rotation = 180f
                                    list.sortByDescending {
                                        it.value
                                    }
                                }
                                1 -> {
                                    filter = 1
                                    imgSortBalance.setImageResource(R.drawable.ic_sort_arrow_up_down_selected)
                                    imgSortBalance.rotation = 0f
                                    list.sortBy {
                                        it.value
                                    }
                                }
                                else -> {
                                    filter = 0
                                    imgSortBalance.setImageResource(R.drawable.ic_sort_arrow_up_down)
                                }
                            }

                            balancePortfolioAdapter.items = list
                            rvFilter.adapter = balancePortfolioAdapter

                        }

                        lineChart.visibility = View.VISIBLE
                        txtTradingBalance.text = "$ " + data.total_market_value?.let {
                            UtilKt().formatValue(
                                it,
                                "###,###.##"
                            )
                        }
                    }
                    Constants.PortfolioFilter.Weight -> {
                        linearLayoutWeight.visibility = View.VISIBLE

                        weightList.addAll(data.profolio_dashboard)
                        rvFilter.layoutManager = LinearLayoutManager(UTSwapApp.instance)
                        val weightPortfolioAdapter = WeightAdapter()
                        weightPortfolioAdapter.items = weightList
                        rvFilter.adapter = weightPortfolioAdapter

                        imgSortWeight.setImageResource(R.drawable.ic_sort_arrow_up_down)
                        layWeight.setOnClickListener {

                            val list = arrayListOf<Portfolio.GetPortfolioDashBoard>()
                            list.addAll(weightList)
                            filter++

                            when (filter) {
                                2 -> {
                                    filter = 2
                                    imgSortWeight.setImageResource(R.drawable.ic_sort_arrow_up_down_selected)
                                    imgSortWeight.rotation = 180f
                                    list.sortByDescending {
                                        it.weight
                                    }
                                }
                                1 -> {
                                    filter = 1
                                    imgSortWeight.setImageResource(R.drawable.ic_sort_arrow_up_down_selected)
                                    imgSortWeight.rotation = 0f
                                    list.sortBy {
                                        it.weight
                                    }
                                }
                                else -> {
                                    filter = 0
                                    imgSortWeight.setImageResource(R.drawable.ic_sort_arrow_up_down)
                                }
                            }

                            weightPortfolioAdapter.items = list
                            rvFilter.adapter = weightPortfolioAdapter
                        }

                        chartPie.visibility = View.VISIBLE
                        txtTradingBalance.text = "$ " + data.total_market_value?.let {
                            UtilKt().formatValue(
                                it,
                                "###,###.##"
                            )
                        }
                    }
                }
            }
        }
    }

    override fun onGetPortfolioFail(data: Portfolio.GetPortfolio) {}
    override fun getPortfolioDashboardChartSuccess(dataSuccess: Portfolio.GetPortfolioDashboardChartRes) {

//        binding.apply {
//            val yValues = ArrayList<Entry>()
//            /*yValues.addAll(listOf(Entry(dataSuccess.data.maxOf { it.x }, dataSuccess.data.maxOf { it.y })))*/
//            yValues.add(Entry(0f, 60f))
//            yValues.add(Entry(1f, 50f))
//            yValues.add(Entry(2f, 70f))
//            yValues.add(Entry(3f, 30f))
//            yValues.add(Entry(4f, 50f))
//            yValues.add(Entry(5f, 60f))
//            yValues.add(Entry(6f, 65f))
//            val set1 = LineDataSet(yValues, "")
//
//            set1.fillAlpha = 110
//            set1.color = R.color.primary
//
//            dataSets.add(set1)
//
//            data = LineData(dataSets)
//
//            lineChart.data = data
//
//            //pass value to pie chart of another class
//            val listData = ArrayList<Double>()
//
//            listData.add(83.20)
//            listData.add(16.80)
//            listData.add(12.0)
//
//            chartPie.setDataOfChart(listData)
//
//            //set attribute of line chart
//            lineChart.description.isEnabled = false
//            lineChart.axisLeft.isEnabled = false
//            lineChart.xAxis.isEnabled = false
//            data!!.setDrawValues(false)
//            lineChart.legend.isEnabled = false
//            set1.color = ContextCompat.getColor(UTSwapApp.instance, R.color.simple_green)
//            lineChart.isDragEnabled = true
//            lineChart.setScaleEnabled(true)
//        }


    }

    override fun getPortfolioDashboardChartFailed(data: String) {}

    private fun onUserBalancePortfolio() {
        binding.apply {
            /* Show or Hide Trading Balance */
            txtBalance.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
            txtBalance.paint.maskFilter = blurMask

            imgVisibility.setOnClickListener {
                showBalanceClick()
            }
            txtBalance.setOnClickListener {
                showBalanceClick()
            }
        }
    }

    private fun onLayoutHeader() {
        binding.apply {
            //check share preference
            SessionVariable.SESSION_STATUS.observe(this@PortfolioFragment) {
                if (SessionVariable.SESSION_STATUS.value == true) {
                    imgMenu.setOnClickListener {
                        val intent = Intent(UTSwapApp.instance, AccountActivity::class.java)
                        startActivity(intent)
                        requireActivity().overridePendingTransition(
                            R.anim.slide_in_left,
                            R.anim.slide_out_right
                        )
                    }
                } else {
                    imgMenu.setOnClickListener {
                        val intent = Intent(UTSwapApp.instance, SignInActivity::class.java)
                        startActivity(intent)
                    }
                }
            }

            SessionVariable.SESSION_STATUS.observe(this@PortfolioFragment) {
                if (SessionVariable.SESSION_STATUS.value == true) {
                    imgNotification.setOnClickListener {
                        val intent =
                            Intent(UTSwapApp.instance, NotificationActivity::class.java)
                        startActivity(intent)
                    }
                    txtCountNotification.visibility = View.VISIBLE

                } else {
                    imgNotification.setOnClickListener {
                        val intent = Intent(UTSwapApp.instance, SignInActivity::class.java)
                        startActivity(intent)
                    }
                    txtCountNotification.visibility = View.GONE
                }
            }

            imgMenu.setOnClickListener {
                val intent = Intent(UTSwapApp.instance, SignInActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun onCheckUserKYC() {
        binding.apply {
            if (SessionPreferences().SESSION_STATUS == true && SessionPreferences().SESSION_KYC == true) {
                txtMessage.visibility = View.GONE
                linearLayoutPortfolio.visibility = View.VISIBLE
            }
        }
    }

    private fun onGetDiagram() {

             binding.apply {
                 val yValues = ArrayList<Entry>()

                 yValues.add(Entry(0f, 60f))
                 yValues.add(Entry(1f, 50f))
                 yValues.add(Entry(2f, 70f))
                 yValues.add(Entry(3f, 30f))
                 yValues.add(Entry(4f, 50f))
                 yValues.add(Entry(5f, 60f))
                 yValues.add(Entry(6f, 65f))

                 val set1 = LineDataSet(yValues, "")

                 set1.fillAlpha = 110
                 set1.color = R.color.primary

                 dataSets.add(set1)

                 data = LineData(dataSets)

                 lineChart.data = data

                 //pass value to pie chart of another class
                 val listData = ArrayList<Double>()

                 listData.add(83.20)
                 listData.add(16.80)
                 listData.add(12.0)

                 chartPie.setDataOfChart(listData)

                 //set attribute of line chart
                 lineChart.description.isEnabled = false
                 lineChart.axisLeft.isEnabled = false
                 lineChart.xAxis.isEnabled = false
                 data!!.setDrawValues(false)
                 lineChart.legend.isEnabled = false
                 set1.color = ContextCompat.getColor(UTSwapApp.instance, R.color.simple_green)
                 lineChart.isDragEnabled = true
                 lineChart.setScaleEnabled(true)
             }
    }

    private fun onClearList() {
        changeList.clear()
        performanceList.clear()
        priceList.clear()
        balanceList.clear()
        weightList.clear()
        binding.rvFilter.adapter?.notifyDataSetChanged()
    }

    private fun invisibleLayout() {
        binding.apply {
            linearLayoutChange.visibility = View.GONE
            linearLayoutPrice.visibility = View.GONE
            linearLayoutPerformance.visibility = View.GONE
            linearLayoutBalance.visibility = View.GONE
            linearLayoutWeight.visibility = View.GONE
        }
    }

    private fun onStoreTypeStringToInt() {
        binding.apply {
            when (portfolioSelectType) {
                Constants.PortfolioFilter.Change -> typePortfolio = 2
                Constants.PortfolioFilter.Performance -> typePortfolio = 3
                Constants.PortfolioFilter.Price -> typePortfolio = 1
                Constants.PortfolioFilter.Balance -> typePortfolio = 5
                Constants.PortfolioFilter.Weight -> typePortfolio = 4
            }
        }
    }

    private fun showBalanceClick() {
        binding.apply {
            blurCondition = !blurCondition

            if (blurCondition) {
                txtBalance.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
                txtBalance.paint.maskFilter = null
                imgVisibility.setImageResource(R.drawable.ic_baseline_remove_red_eye_24)

                lineChart.axisRight.isEnabled = false

            } else {
                txtBalance.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
                txtBalance.paint.maskFilter = blurMask
                imgVisibility.setImageResource(R.drawable.ic_baseline_visibility_off_24)

                lineChart.axisRight.isEnabled = true
            }
        }
    }
}