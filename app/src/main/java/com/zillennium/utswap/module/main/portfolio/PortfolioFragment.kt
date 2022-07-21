package com.zillennium.utswap.module.main.portfolio

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.BlurMaskFilter
import android.graphics.MaskFilter
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
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
import com.zillennium.utswap.models.portfolio.*
import com.zillennium.utswap.module.account.accountScreen.AccountActivity
import com.zillennium.utswap.module.main.portfolio.adapter.*
import com.zillennium.utswap.module.main.portfolio.dialog.FilterPortfolioDialogBottomSheet
import com.zillennium.utswap.module.security.securityActivity.signInScreen.SignInActivity
import com.zillennium.utswap.module.system.notification.NotificationActivity

class PortfolioFragment :
    BaseMvpFragment<PortfolioView.View, PortfolioView.Presenter, FragmentNavbarPortfolioBinding>(),
    PortfolioView.View {

    override var mPresenter: PortfolioView.Presenter = PortfolioPresenter()
    override val layoutResource: Int = R.layout.fragment_navbar_portfolio

    private var performanceList = ArrayList<Performance>()
    private var weightList = ArrayList<Weight>()
    private var balanceList = ArrayList<Balance>()
    private var priceList = ArrayList<Price>()
    private var changeList = ArrayList<Change>()
    private var changeAdapter: ChangeAdapter? = null
    private var balanceAdapter: BalanceAdapter? = null
    private var performanceAdapter: PerformanceAdapter? = null
    private var weightAdapter: WeightAdapter? = null

    var blurCondition = true
    val blurMask: MaskFilter = BlurMaskFilter(50f, BlurMaskFilter.Blur.NORMAL)
    private var filter: Int = 0 // 0 = no sort, 1 = asc sort, 2 = desc sort

    private var dataSets = ArrayList<ILineDataSet>()
    private var data: LineData? = null

    @SuppressLint("UseCompatLoadingForDrawables", "NotifyDataSetChanged", "SetTextI18n")
    override fun initView() {
        super.initView()
        try {
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

                if (SessionPreferences().SESSION_STATUS == true && SessionPreferences().SESSION_KYC == true) {
                    txtMessage.visibility = View.GONE
                    linearLayoutPortfolio.visibility = View.VISIBLE
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
                //pass value to pie chart of another class
                var listData = ArrayList<Double>()

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

                /* Show or Hide Trading Balance */
                txtBalance.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
                txtBalance.paint.maskFilter = blurMask

                imgVisibility.setOnClickListener {
                    showBalanceClick()
                }
                txtBalance.setOnClickListener {
                    showBalanceClick()
                }

                val linearLayoutManager = LinearLayoutManager(requireContext())
                rvFilter.layoutManager = linearLayoutManager

                SettingVariable.portfolio_selected.observe(this@PortfolioFragment) {

                    btnFilter.text = SettingVariable.portfolio_selected.value.toString()

                    filter = 0

                    linearLayoutChange.visibility = View.GONE
                    linearLayoutPrice.visibility = View.GONE
                    linearLayoutPerformance.visibility = View.GONE
                    linearLayoutBalance.visibility = View.GONE
                    linearLayoutWeight.visibility = View.GONE

                    lineChart.visibility = View.GONE
                    chartPie.visibility = View.GONE

                    linearLayoutChange.visibility = View.GONE
                    linearLayoutPrice.visibility = View.GONE
                    linearLayoutPerformance.visibility = View.GONE
                    linearLayoutBalance.visibility = View.GONE
                    linearLayoutWeight.visibility = View.GONE

                    when (SettingVariable.portfolio_selected.value.toString()) {
                        "Change" -> {
                            //list data of performance
                            changeList.clear()
                            changeList.add(Change("Pochentong 555", 1.68))
                            changeList.add(Change("Siem Reap 17140", -0.48))
                            changeList.add(Change("Muk Kampul 16644", -1.56))
                            changeList.add(Change("KT 1665", 0.16))
                            changeList.add(Change("Veng Sreng 2719", 1.05))

                            linearLayoutChange.visibility = View.VISIBLE

                            changeAdapter = ChangeAdapter(changeList)

                            rvFilter.adapter = changeAdapter

                            txtTradingBalance.text = "$6 420.99"

                            imgSortChange.setImageResource(R.drawable.ic_sort_arrow_up_down)
                            laySortChange.setOnClickListener {

                                val list = arrayListOf<Change>()
                                list.addAll(changeList)
                                filter++

                                when (filter) {
                                    2 -> {
                                        filter = 2
                                        imgSortChange.setImageResource(R.drawable.ic_sort_arrow_up_down_selected)
                                        imgSortChange.rotation = 180f
                                        list.sortByDescending {
                                            it.txtPercent
                                        }
                                    }
                                    1 -> {
                                        filter = 1
                                        imgSortChange.setImageResource(R.drawable.ic_sort_arrow_up_down_selected)
                                        imgSortChange.rotation = 0f
                                        list.sortBy {
                                            it.txtPercent
                                        }
                                    }
                                    else -> {
                                        filter = 0
                                        imgSortChange.setImageResource(R.drawable.ic_sort_arrow_up_down)
                                    }
                                }

                                changeAdapter = ChangeAdapter(list)
                                rvFilter.adapter = changeAdapter

                            }

                            lineChart.visibility = View.VISIBLE
                        }
                        "Weight" -> {
                            //list data of performance
                            weightList.clear()
                            weightList.add(Weight("Pochentong 555", 12.00))
                            weightList.add(Weight("Siem Reap 17140", 25.00))
                            weightList.add(Weight("Muk Kampul 16644", 5.00))
                            weightList.add(Weight("KT 1665", 15.00))
                            weightList.add(Weight("Veng Sreng 2719", 13.00))

                            weightAdapter = WeightAdapter(weightList)

                            rvFilter.adapter = weightAdapter

                            linearLayoutWeight.visibility = View.VISIBLE

                            txtTradingBalance.text = "16.8%"

                            imgSortWeight.setImageResource(R.drawable.ic_sort_arrow_up_down)
                            layWeight.setOnClickListener {

                                val list = arrayListOf<Weight>()
                                list.addAll(weightList)
                                filter++

                                when (filter) {
                                    2 -> {
                                        filter = 2
                                        imgSortWeight.setImageResource(R.drawable.ic_sort_arrow_up_down_selected)
                                        imgSortWeight.rotation = 180f
                                        list.sortByDescending {
                                            it.txtPercent
                                        }
                                    }
                                    1 -> {
                                        filter = 1
                                        imgSortWeight.setImageResource(R.drawable.ic_sort_arrow_up_down_selected)
                                        imgSortWeight.rotation = 0f
                                        list.sortBy {
                                            it.txtPercent
                                        }
                                    }
                                    else -> {
                                        filter = 0
                                        imgSortWeight.setImageResource(R.drawable.ic_sort_arrow_up_down)
                                    }
                                }

                                weightAdapter = WeightAdapter(list)
                                rvFilter.adapter = weightAdapter

                            }

                            chartPie.visibility = View.VISIBLE
                        }
                        "Balance" -> {
                            //list data of balance
                            balanceList.clear()
                            balanceList.add(Balance("Pochentong 555", 18, 74.34))
                            balanceList.add(Balance("Siem Reap 17140", 98, 65.66))
                            balanceList.add(Balance("Muk Kampul 16644", 10000, 112600.00))
                            balanceList.add(Balance("KT 1665", 2000, 4940.00))
                            balanceList.add(Balance("Veng Sreng 2719", 18, 159.30))

                            balanceAdapter = BalanceAdapter(balanceList)

                            rvFilter.adapter = balanceAdapter

                            linearLayoutBalance.visibility = View.VISIBLE

                            txtTradingBalance.text = "$6 420.99"

                            imgSortBalance.setImageResource(R.drawable.ic_sort_arrow_up_down)
                            layBalance.setOnClickListener {

                                val list = arrayListOf<Balance>()
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

                                balanceAdapter = BalanceAdapter(list)
                                rvFilter.adapter = balanceAdapter

                            }

                            lineChart.visibility = View.VISIBLE
                        }
                        "Price" -> {
                            //list data of price
                            priceList.clear()
                            priceList.add(Price("Pochentong 555", 0.67, 0.68))
                            priceList.add(Price("Siem Reap 17140", 1.30, 1.31))
                            priceList.add(Price("Muk Kampul 16644", 9.08, 9.06))
                            priceList.add(Price("KT 1665", 18.88, 18.83))
                            priceList.add(Price("Veng Sreng 2719", 0.67, 0.68))

                            linearLayoutPrice.visibility = View.VISIBLE

                            rvFilter.adapter = PriceAdapter(priceList)

                            txtTradingBalance.text = "$6 420.99"

                            lineChart.visibility = View.VISIBLE
                        }
                        "Performance" -> {
                            //list data of performance
                            performanceList.clear()
                            performanceList.add(Performance("Pochentong 555", 1.68))
                            performanceList.add(Performance("Siem Reap 17140", -0.48))
                            performanceList.add(Performance("Muk Kampul 16644", -1.56))
                            performanceList.add(Performance("KT 1665", 0.16))
                            performanceList.add(Performance("Veng Sreng 2719", 1.05))

                            linearLayoutPerformance.visibility = View.VISIBLE

                            performanceAdapter = PerformanceAdapter(performanceList)

                            rvFilter.adapter = performanceAdapter

                            txtTradingBalance.text = "$6 420.99"

                            txtPerformance.text = "Performance"

                            imgSortPerformance.setImageResource(R.drawable.ic_sort_arrow_up_down)
                            laySortPerformance.setOnClickListener {
                                val list = arrayListOf<Performance>()
                                list.addAll(performanceList)
                                filter++

                                when (filter) {
                                    2 -> {
                                        filter = 2
                                        imgSortPerformance.setImageResource(R.drawable.ic_sort_arrow_up_down_selected)
                                        imgSortPerformance.rotation = 180f
                                        list.sortByDescending {
                                            it.txtPercent
                                        }
                                    }
                                    1 -> {
                                        filter = 1
                                        imgSortPerformance.setImageResource(R.drawable.ic_sort_arrow_up_down_selected)
                                        imgSortPerformance.rotation = 0f
                                        list.sortBy {
                                            it.txtPercent
                                        }
                                    }
                                    else -> {
                                        filter = 0
                                        imgSortPerformance.setImageResource(R.drawable.ic_sort_arrow_up_down)
                                    }
                                }

                                performanceAdapter = PerformanceAdapter(list)
                                rvFilter.adapter = performanceAdapter
                            }

                            lineChart.visibility = View.VISIBLE
                        }
                    }

                }

                layFilter.setOnClickListener {
                    layFilter.isEnabled = false
                    layFilter.postDelayed(Runnable { layFilter.isEnabled = true }, 300)
                    val filterPortfolioDialogBottomSheet: FilterPortfolioDialogBottomSheet =
                        FilterPortfolioDialogBottomSheet.newInstance(btnFilter.text.toString())
                    filterPortfolioDialogBottomSheet.show(
                        requireActivity().supportFragmentManager,
                        "filter_portfolio"
                    )
                }

                imgMenu.setOnClickListener {
                    val intent = Intent(UTSwapApp.instance, SignInActivity::class.java)
                    startActivity(intent)
                }
            }

        } catch (error: Exception) {
            // Must be safe
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