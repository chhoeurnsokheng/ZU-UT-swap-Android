package com.zillennium.utswap.module.main.portfolio

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.graphics.BlurMaskFilter
import android.graphics.MaskFilter
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidstudy.networkmanager.Tovuti
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.zillennium.utswap.Datas.GlobalVariable.SessionVariable
import com.zillennium.utswap.Datas.StoredPreferences.SessionPreferences
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.FragmentNavbarPortfolioBinding
import com.zillennium.utswap.models.portfolio.Portfolio
import com.zillennium.utswap.module.account.accountScreen.AccountActivity
import com.zillennium.utswap.module.main.portfolio.adapter.*
import com.zillennium.utswap.module.main.portfolio.dialog.FilterPortfolioDialogBottomSheet
import com.zillennium.utswap.module.security.securityActivity.signInScreen.SignInActivity
import com.zillennium.utswap.module.system.notification.NotificationActivity
import com.zillennium.utswap.utils.Constants
import com.zillennium.utswap.utils.Util
import com.zillennium.utswap.utils.UtilKt
import com.zillennium.utswap.utils.Utils
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

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
    var month: List<String>? = arrayListOf()
    val yValues: ArrayList<Entry> = arrayListOf()

    companion object {
        var month1: List<String> = arrayListOf()
        var month: List<String> = arrayListOf()
        var balance_weight = 0.0
        var ut_projects = 0.0

    }

    @SuppressLint("UseCompatLoadingForDrawables", "NotifyDataSetChanged", "SetTextI18n")
    override fun initView() {
        super.initView()
        try {
            onSwipeRefresh()
            binding.apply {

                mPresenter.getPortfolioDashboardChart(requireActivity())


                SessionVariable.SESSION_STATUS.observe(this@PortfolioFragment) {
                    requestData()
                }

                onCallApi()
                onCheckUserKYC()
                onLayoutHeader()
                onUserBalancePortfolio()

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

        }
    }

    private fun requestData() {
        binding.btnFilter.text = portfolioSelectType
        mPresenter.onGetPortfolio(Portfolio.GetPortfolioObject(typePortfolio), requireActivity())
    }

    private fun onCallApi() {

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

    override fun onGetPortfolioSuccess(data: Portfolio.GetPortfolio) {


        if (data.message == "Please sign in") {
            checkUserLogin()
        }

        balance_weight = data.data?.balance_weight?.toDouble() ?: 0.0
        ut_projects = data.data?.ut_projects?.toDouble() ?: 0.0


        mPresenter.getPortfolioDashboardChart(requireActivity())
        binding.apply {
            swipeRefresh.isRefreshing = false
            loadingProgressBar.visibility = View.GONE
            layTradingBalance.visibility = View.VISIBLE
            txtBalance.text = "$ " + data.data?.total_market_value?.let { UtilKt().formatValue(it, "###,###.##") }

            filter = 0

            invisibleLayout()
            lineChart.visibility = View.GONE
            chartPie.visibility = View.GONE
            onClearList()

            if (data.data?.profolio_dashboard?.isNotEmpty() == true) {
                when (portfolioSelectType) {
                    Constants.PortfolioFilter.Change -> {
                        linearLayoutChange.visibility = View.VISIBLE

                        data.data?.let { changeList.addAll(it.profolio_dashboard) }
                        rvFilter.layoutManager = LinearLayoutManager(UTSwapApp.instance)
                        val changePortfolioAdapter = ChangeAdapter(changeList, onClickListener = object :OnClickPortfolio{
                            override fun ClickPortfolioProjectID(isNull: Boolean) {
                                if (isNull==true){
                                    showAlterProjectCannotShowTradeDetails()
                                }
                            }

                        })

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
                                        it.mkt_project_change?.toDouble()
                                    }
                                }
                                1 -> {
                                    filter = 1
                                    imgSortChange.setImageResource(R.drawable.ic_sort_arrow_up_down_selected)
                                    imgSortChange.rotation = 0f
                                    list.sortedBy {
                                        it.mkt_project_change?.toDouble()
                                    }
                                }
                                else -> {
                                    filter = 0
                                    imgSortChange.setImageResource(R.drawable.ic_sort_arrow_up_down)
                                }
                            }

                            changePortfolioAdapter.itemList = list
                            rvFilter.adapter = changePortfolioAdapter
                        }

                        lineChart.visibility = View.VISIBLE


                        val balance_weight = data.data?.balance_weight?.toDouble()
                        val totalUserBalance = data.data?.total_user_balance
                        val totalTrading = (totalUserBalance?.let { balance_weight?.times(it) })?.div(
                            100
                        )

                        txtTradingBalance.text =  "$ " + data.data?.total_user_balance?.let {
                            UtilKt().formatValue(
                                it,
                                "###,###.##"
                            )
                        }


                    }
                    Constants.PortfolioFilter.Performance -> {
                        linearLayoutPerformance.visibility = View.VISIBLE

                        data.data?.profolio_dashboard?.let { performanceList.addAll(it) }
                        rvFilter.layoutManager = LinearLayoutManager(UTSwapApp.instance)
                        val performancePortfolioAdapter = PerformanceAdapter(performanceList, object :OnClickPortfolio{
                            override fun ClickPortfolioProjectID(isNull: Boolean) {
                                if (isNull==true){
                                    showAlterProjectCannotShowTradeDetails()
                                }
                            }
                        })
                        performancePortfolioAdapter.itemList = performanceList
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
                                        it.mkt_project_perf?.toDouble()
                                    }
                                }
                                1 -> {
                                    filter = 1
                                    imgSortPerformance.setImageResource(R.drawable.ic_sort_arrow_up_down_selected)
                                    imgSortPerformance.rotation = 0f
                                    list.sortBy {
                                        it.mkt_project_perf?.toDouble()
                                    }
                                }
                                else -> {
                                    filter = 0
                                    imgSortPerformance.setImageResource(R.drawable.ic_sort_arrow_up_down)
                                }
                            }

                            performancePortfolioAdapter.itemList = list
                            rvFilter.adapter = performancePortfolioAdapter
                        }

                        lineChart.visibility = View.VISIBLE
                        val balance_weight = data.data?.balance_weight?.toDouble()
                        val totalUserBalance = data.data?.total_user_balance
                        val totalTrading = (totalUserBalance?.let { balance_weight?.times(it) })?.div(
                            100
                        )

                        txtTradingBalance.text = "$ " + data.data?.total_user_balance?.let {
                            UtilKt().formatValue(
                                it,
                                "###,###.##"
                            )
                        }
                    }
                    Constants.PortfolioFilter.Price -> {
                        linearLayoutPrice.visibility = View.VISIBLE

                        data.data?.profolio_dashboard?.let { priceList.addAll(it) }
                        rvFilter.layoutManager = LinearLayoutManager(UTSwapApp.instance)
                        val pricePortfolioAdapter = PriceAdapter(priceList, object :OnClickPortfolio{
                            override fun ClickPortfolioProjectID(isNull: Boolean) {
                                    if (isNull == true){
                                       showAlterProjectCannotShowTradeDetails()
                                    }
                            }

                        }

                        )
                        pricePortfolioAdapter.itemList = priceList
                        rvFilter.adapter = pricePortfolioAdapter

                        lineChart.visibility = View.VISIBLE
                        txtTradingBalance.text = "$ " + data.data?.total_user_balance?.let {
                            UtilKt().formatValue(
                                it,
                                "###,###.##"
                            )
                        }
                    }
                    Constants.PortfolioFilter.Balance -> {
                        linearLayoutBalance.visibility = View.VISIBLE

                        data.data?.profolio_dashboard?.let { balanceList.addAll(it) }
                        rvFilter.layoutManager = LinearLayoutManager(UTSwapApp.instance)
                        val balancePortfolioAdapter = BalanceAdapter(balanceList, object :OnClickPortfolio{
                            override fun ClickPortfolioProjectID(isNull: Boolean) {
                                showAlterProjectCannotShowTradeDetails()
                            }

                        })
                        balancePortfolioAdapter.itemList = balanceList
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

                            balancePortfolioAdapter.itemList = list
                            rvFilter.adapter = balancePortfolioAdapter

                        }

                        lineChart.visibility = View.VISIBLE

                        val balance_weight = data.data?.balance_weight?.toDouble()
                        val totalUserBalance = data.data?.total_user_balance
                        val totalTrading = (totalUserBalance?.let { balance_weight?.times(it) })?.div(
                            100
                        )

                        txtTradingBalance.text = "$ " + data.data?.total_user_balance?.let {
                            UtilKt().formatValue(
                                it,
                                "###,###.##"
                            )
                        }
                    }
                    Constants.PortfolioFilter.Weight -> {
                        linearLayoutWeight.visibility = View.VISIBLE

                        data.data?.profolio_dashboard?.let { weightList.addAll(it) }
                        rvFilter.layoutManager = LinearLayoutManager(UTSwapApp.instance)
                        val weightPortfolioAdapter = WeightAdapter(weightList, object :OnClickPortfolio{
                            override fun ClickPortfolioProjectID(isNull: Boolean) {
                                if (isNull==true){
                                    showAlterProjectCannotShowTradeDetails()
                                }
                            }

                        })
                        weightPortfolioAdapter.itemList = weightList
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

                            weightPortfolioAdapter.itemList = list
                            rvFilter.adapter = weightPortfolioAdapter
                        }

                        chartPie.visibility = View.VISIBLE

                        val balance_weight = data.data?.balance_weight?.toDouble()
                        val totalUserBalance = data.data?.total_user_balance
                        val totalTrading = (totalUserBalance?.let { balance_weight?.times(it) })?.div(
                            100
                        )

                        txtTradingBalance.text = data.data?.balance_weight.toString() + "%"




                    }
                }
            }
        }
    }

    override fun onGetPortfolioFail(data: Portfolio.GetPortfolio) {
        binding.apply {
            swipeRefresh.isRefreshing = false
        }
    }

    override fun getPortfolioDashboardChartSuccess(dataSuccess: Portfolio.GetPortfolioDashboardChartRes) {
        if (dataSuccess.message == "Please sign in") {
            checkUserLogin()
        }
        binding.apply {
            swipeRefresh.isRefreshing = false
            month = dataSuccess.data.map { it.x } as ArrayList<String>


            val listData = ArrayList<Double>()
            ut_projects.let { listData.add(it) }
            balance_weight.let { listData.add(it) }

            chartPie.setDataOfChart(listData)


        }

        val sales = dataSuccess.data.indices.map { Entry(it.toFloat(), dataSuccess.data[it].y) }


        month1 = dataSuccess.data.map {
            it.date
        }

        val weekTwoSales = LineDataSet(sales, "")
        weekTwoSales.lineWidth = 3f

        weekTwoSales.mode = LineDataSet.Mode.CUBIC_BEZIER
        weekTwoSales.color = ContextCompat.getColor(requireActivity(), R.color.primary)
        weekTwoSales.valueTextColor = ContextCompat.getColor(requireActivity(), R.color.gray)
        val dataSet = ArrayList<ILineDataSet>()
        dataSet.add(weekTwoSales)
        weekTwoSales.setDrawValues(false)
        weekTwoSales.valueTextColor = R.color.gray
        weekTwoSales.circleRadius = 6f
        val lineData = LineData(dataSet)
        binding.lineChart.data = lineData
        binding.lineChart.invalidate()
        binding.lineChart.axisRight.textColor = ContextCompat.getColor(requireActivity(), R.color.secondary_text)
        binding.lineChart.axisRight.valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                val v: String = getPriceFormat(Math.round(value).toFloat())
                var result = v
                if (result.contains(".")) {
                    try {
                        result = v.substring(0, v.indexOf(".") + 3)
                    } catch (e: java.lang.Exception) {
                        e.printStackTrace()
                    }
                }
                return result + getPricePrefixFormat(value)
            }
        }

        with(binding.lineChart) {

            description.isEnabled = false
            xAxis.setDrawGridLines(false)
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            xAxis.granularity = 1F
            xAxis.yOffset = 4.0F
            xAxis.valueFormatter = MyAxisFormatter
            axisLeft.isEnabled = false
            axisRight.isEnabled = true
            extraRightOffset = 30f
            legend.orientation = Legend.LegendOrientation.VERTICAL
            legend.verticalAlignment = Legend.LegendVerticalAlignment.TOP
            legend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
                // legend.textSize = 10F
            legend.form = Legend.LegendForm.LINE
        }


        var yMin = 0f


        //right axis
        val rightAxis = binding.lineChart.axisRight
        rightAxis.setDrawAxisLine(false)
        rightAxis.mDecimals = 1
        rightAxis.granularity = 1f
        rightAxis.isGranularityEnabled = true
      //  rightAxis.textSize = 14f


        rightAxis.axisMinimum = yMin
        val labelCount = rightAxis.labelCount
        val density = resources.displayMetrics.density
        val lp = binding.lineChart.layoutParams
        lp.height = (200 * density * labelCount / 7).toInt()
        binding.lineChart.layoutParams = lp
        binding.lineChart.setVisibleXRangeMaximum(6f)

        binding.lineChart.setDrawGridBackground(false)
        binding.apply {
            lineChart.minOffset = 0f
            lineChart.setViewPortOffsets(0f, 20f, 70f, 50f)

        }

    }

    override fun getPortfolioDashboardChartFailed(data: String) {
        binding.apply {
            swipeRefresh.isRefreshing = false
        }
    }


    object MyAxisFormatter : IndexAxisValueFormatter() {

        var items = month1

        override fun getAxisLabel(value: Float, axis: AxisBase?): String? {
            val index = value.toInt()
            return if (index < items.size) {
                items[index]
            } else {
                null
            }
        }
    }
    private fun showAlterProjectCannotShowTradeDetails(){
        val builder = AlertDialog.Builder(requireActivity())
        builder.setTitle("This project is closed .")
        builder.setMessage("Can not open trad Details")

        builder.setPositiveButton("Okay"){dialogInterface, which ->
        }
        val alertDialog: AlertDialog = builder.create()
        alertDialog.show()
    }
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
                            .putExtra(Constants.IntentType.FROM_MAIN_ACTIVITY, Constants.IntentType.FROM_MAIN_ACTIVITY )
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
                        activity?.finish()

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
                    tvBadgeNumber.visibility = View.VISIBLE

                } else {
                    imgNotification.setOnClickListener {
                        val intent = Intent(UTSwapApp.instance, SignInActivity::class.java)
                        startActivity(intent)
                    }
                    tvBadgeNumber.visibility = View.GONE
                }
            }

            imgMenu.setOnClickListener {
                val intent = Intent(UTSwapApp.instance, SignInActivity::class.java)
                startActivity(intent)
                activity?.finish()

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

    private fun onSwipeRefresh() {
        binding.apply {
            swipeRefresh.setOnRefreshListener {
                requestData()
                mPresenter.getPortfolioDashboardChart(requireActivity())
            }
        }
    }




    fun setBadgeNumberPortfolio() {
        binding.apply {
            SessionVariable.BADGE_NUMBER.observe(this@PortfolioFragment) {
                if (SessionVariable.BADGE_NUMBER.value?.isNotEmpty() == true && SessionVariable.BADGE_NUMBER.value != "0") {
                    tvBadgeNumber.visibility = View.VISIBLE
                    if (it.toInt() > 9) {
                        tvBadgeNumber.text = "9+"
                    } else {
                        tvBadgeNumber.text = it
                    }
                } else {
                    tvBadgeNumber.visibility = View.INVISIBLE

                }
            }
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


            if (blurCondition) {
                txtBalance.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
                txtBalance.paint.maskFilter = null
                imgVisibility.setImageResource(R.drawable.ic_baseline_remove_red_eye_24)

                lineChart.axisRight.isEnabled = false
                blurCondition = false

            } else {
                txtBalance.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
                txtBalance.paint.maskFilter = blurMask
                imgVisibility.setImageResource(R.drawable.ic_baseline_visibility_off_24)

                lineChart.axisRight.isEnabled = true
                blurCondition = true
            }
        }
    }

    private fun checkUserLogin() {
        val intent = Intent(requireActivity(), SignInActivity::class.java)
        startActivity(intent)
    }

    private fun getPricePrefixFormat(price: Float): String {
        return if (price > 0.0) {
            if (price < 1000000) {
                "K"
            } else {
                "M"
            }
        } else {
            ""
        }
    }

    private fun getPriceFormat(price: Float): String {
        var bd = BigDecimal(price.toDouble().toString())
        bd = bd.setScale(-1, RoundingMode.HALF_UP)
        val preRe: Double = if (bd.toDouble() < 1000000) {
            bd.toDouble() / 1000
        } else {
            bd.toDouble() / 1000000
        }
        if (preRe % 1 == 0.0) {
            return (preRe.toInt()).toString()
        }
        return preRe.toString() + ""
    }

    fun getDateInMilliSeconds(givenDateString: String?, format: String): Long {
        val sdf = SimpleDateFormat(format, Locale.US)
        var timeInMilliseconds: Long = 1
        try {
            val mDate: Date = sdf.parse(givenDateString)
            timeInMilliseconds = mDate.getTime()
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return timeInMilliseconds
    }
}


