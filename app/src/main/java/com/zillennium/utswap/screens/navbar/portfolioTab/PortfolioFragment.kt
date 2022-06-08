package com.zillennium.utswap.screens.navbar.portfolioTab

import android.annotation.SuppressLint
import android.graphics.BlurMaskFilter
import android.graphics.MaskFilter
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.anychart.AnyChart
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.zillennium.utswap.Datas.GlobalVariable.SettingVariable
import com.zillennium.utswap.Datas.StoredPreferences.SessionPreferences
import com.zillennium.utswap.R
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.FragmentNavbarPortfolioBinding
import com.zillennium.utswap.models.portfolio.*
import com.zillennium.utswap.screens.navbar.portfolioTab.adapter.*
import com.zillennium.utswap.screens.navbar.portfolioTab.dialog.FilterPortfolioDialogBottomSheet


class PortfolioFragment :
    BaseMvpFragment<PortfolioView.View, PortfolioView.Presenter, FragmentNavbarPortfolioBinding>(),
    PortfolioView.View{

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
    var click: Int = 2
    var sortChange: String = "sortDescend"
    var sortWeight: String = "sortDescend"
    var sortBalance: String = "sortDescend"

    @SuppressLint("UseCompatLoadingForDrawables", "NotifyDataSetChanged")
    override fun initView() {
        super.initView()
        try {
            binding.apply {

                if(SessionPreferences().SESSION_STATUS  == true && SessionPreferences().SESSION_KYC  == true){
                    txtMessage.visibility = View.GONE
                    linearLayoutPortfolio.visibility = View.VISIBLE
                }

                //pie chart
                val pie = AnyChart.pie()

                val data: MutableList<DataEntry> = ArrayList()
                data.add(ValueDataEntry("UT Projects", 12000))
                data.add(ValueDataEntry("Trading Balance", 3400))

                pie.data(data)
                anyChartViewPieChart.setChart(pie)

                /* Show or Hide Trading Balance */
                val blurMask: MaskFilter = BlurMaskFilter(50f, BlurMaskFilter.Blur.NORMAL)
                txtBalance.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
                txtBalance.getPaint().setMaskFilter(blurMask)

                imgVisibility.setOnClickListener {
                    blurCondition = !blurCondition

                    if (blurCondition) {
                        txtBalance.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
                        txtBalance.getPaint().setMaskFilter(null)
                        imgVisibility.setImageResource(R.drawable.ic_baseline_remove_red_eye_24)
                    } else {
                        txtBalance.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
                        txtBalance.getPaint().setMaskFilter(blurMask)
                        imgVisibility.setImageResource(R.drawable.ic_baseline_visibility_off_24)
                    }
                }

                val linearLayoutManager = LinearLayoutManager(requireContext())
                rvFilter.layoutManager = linearLayoutManager

               SettingVariable.portfolio_selected.observe(this@PortfolioFragment) {

                   btnFilter.hint = SettingVariable.portfolio_selected.value.toString()



                   when(SettingVariable.portfolio_selected.value.toString()){
                       "Change" -> {
                           //list data of performance
                           changeList.clear()
                           changeList.add(Change("Pochentong 555",1.68))
                           changeList.add(Change("Siem Reap 17140",-0.48))
                           changeList.add(Change("Muk Kampul 16644",-1.56))
                           changeList.add(Change("KT 1665",0.16))
                           changeList.add(Change("Veng Sreng 2719",1.05))

                           linearLayoutChange.visibility = View.VISIBLE
                           linearLayoutPrice.visibility = View.GONE
                           linearLayoutPerformance.visibility = View.GONE
                           linearLayoutBalance.visibility = View.GONE
                           linearLayoutWeight.visibility = View.GONE

                           changeAdapter = ChangeAdapter(changeList)

                           rvFilter.adapter = changeAdapter

                           txtTradingBalance.text = "$6 420.99"
                           click = 2

                           txtChange.setOnClickListener {
                                if(sortChange == "sortDescend")
                                {
                                    sortChange = "sortAscend"

                                    val list = arrayListOf<Change>()

                                    changeList.map {
                                        list.add(Change(it.projectName,it.txtPercent))
                                    }

                                    list.sortByDescending {
                                        it.txtPercent
                                    }

                                    changeAdapter!!.notifyDataSetChanged()

                                    changeAdapter = ChangeAdapter(list)

                                    rvFilter.adapter = changeAdapter
                                }else{
                                    clickCountSortChange(click)
                                    click++
                                }
                           }
                       }
                       "Weight" -> {
                           //list data of performance
                           weightList.clear()
                           weightList.add(Weight("Pochentong 555",12.00))
                           weightList.add(Weight("Siem Reap 17140",25.00))
                           weightList.add(Weight("Muk Kampul 16644",5.00))
                           weightList.add(Weight("KT 1665",15.00))
                           weightList.add(Weight("Veng Sreng 2719",13.00))

                           weightAdapter = WeightAdapter(weightList)

                           rvFilter.adapter = weightAdapter

                           linearLayoutPrice.visibility = View.GONE
                           linearLayoutPerformance.visibility = View.GONE
                           linearLayoutBalance.visibility = View.GONE
                           linearLayoutWeight.visibility = View.VISIBLE
                           linearLayoutChange.visibility = View.GONE

                           txtTradingBalance.text = "16.8%"
                           click = 2

                           txtWeight.setOnClickListener {
                               if(sortWeight == "sortDescend")
                               {
                                   sortWeight = "sortAscend"

                                   val list = arrayListOf<Weight>()

                                   weightList.map {
                                       list.add(Weight(it.projectName,it.txtPercent))
                                   }

                                   list.sortByDescending {
                                       it.txtPercent
                                   }

                                   weightAdapter!!.notifyDataSetChanged()
                                   weightAdapter = WeightAdapter(list)

                                   rvFilter.adapter = weightAdapter
                               }else{
                                   clickCountSortWeight(click)
                                   click++
                               }
                           }

                       }
                       "Balance" -> {
                           //list data of balance
                           balanceList.clear()
                           balanceList.add(Balance("Pochentong 555",18, 74.34))
                           balanceList.add(Balance("Siem Reap 17140",98, 65.66))
                           balanceList.add(Balance("Muk Kampul 16644",10000, 112600.00))
                           balanceList.add(Balance("KT 1665",2000, 4940.00))
                           balanceList.add(Balance("Veng Sreng 2719",18, 159.30))

                           balanceAdapter = BalanceAdapter(balanceList)

                           rvFilter.adapter = balanceAdapter

                           linearLayoutPrice.visibility = View.GONE
                           linearLayoutPerformance.visibility = View.GONE
                           linearLayoutBalance.visibility = View.VISIBLE
                           linearLayoutWeight.visibility = View.GONE
                           linearLayoutChange.visibility = View.GONE

                           txtTradingBalance.text = "$6 420.99"
                           click = 2

                           txtValueBalance.setOnClickListener {
                               if(sortBalance == "sortDescend")
                               {
                                   sortBalance = "sortAscend"

                                   val list = arrayListOf<Balance>()

                                   balanceList.map {
                                       list.add(Balance(it.projectName,it.ut,it.value))
                                   }

                                   list.sortByDescending {
                                       it.value
                                   }

                                   balanceAdapter!!.notifyDataSetChanged()
                                   balanceAdapter = BalanceAdapter(list)
                                   rvFilter.adapter = balanceAdapter
                               }else{
                                   clickCountSortBalance(click)
                                   click++
                               }
                           }
                       }
                       "Price" -> {
                           //list data of price
                           priceList.clear()
                           priceList.add(Price("Pochentong 555",0.67,0.68))
                           priceList.add(Price("Siem Reap 17140",1.30,1.31))
                           priceList.add(Price("Muk Kampul 16644",9.08,9.06))
                           priceList.add(Price("KT 1665",18.88,18.83))
                           priceList.add(Price("Veng Sreng 2719",0.67,0.68))

                           linearLayoutPrice.visibility = View.VISIBLE
                           linearLayoutPerformance.visibility = View.GONE
                           linearLayoutBalance.visibility = View.GONE
                           linearLayoutWeight.visibility = View.GONE
                           linearLayoutChange.visibility = View.GONE

                           rvFilter.adapter = PriceAdapter(priceList)

                           txtTradingBalance.text = "$6 420.99"
                       }
                       "Performance" -> {
                           //list data of performance
                           performanceList.clear()
                           performanceList.add(Performance("Pochentong 555",1.68))
                           performanceList.add(Performance("Siem Reap 17140",-0.48))
                           performanceList.add(Performance("Muk Kampul 16644",-1.56))
                           performanceList.add(Performance("KT 1665",0.16))
                           performanceList.add(Performance("Veng Sreng 2719",1.05))

                           linearLayoutPrice.visibility = View.GONE
                           linearLayoutPerformance.visibility = View.VISIBLE
                           linearLayoutBalance.visibility = View.GONE
                           linearLayoutWeight.visibility = View.GONE
                           linearLayoutChange.visibility = View.GONE

                           performanceAdapter = PerformanceAdapter(performanceList)

                           rvFilter.adapter = performanceAdapter

                           txtTradingBalance.text = "$6 420.99"
                           click = 2

                           txtPerformance.text = "Performance"

                           txtPerformance.setOnClickListener {
                               if(txtPerformance.text.toString() == "Performance")
                               {
                                   txtPerformance.text = "Change"

                                   val list = arrayListOf<Performance>()

                                   performanceList.map {
                                       list.add(Performance(it.projectName,it.txtPercent))
                                   }

                                   list.sortByDescending {
                                       it.txtPercent
                                   }

                                   performanceAdapter!!.notifyDataSetChanged()
                                   performanceAdapter = PerformanceAdapter(list)
                                   rvFilter.adapter = performanceAdapter
                               }else if(txtPerformance.text.toString() == "Change"){
                                   clickCountSortPerformance(click)
                                   click++
                               }
                           }
                       }
                   }

               }

                btnFilter.setOnClickListener {
                    val filterPortfolioDialogBottomSheet: FilterPortfolioDialogBottomSheet = FilterPortfolioDialogBottomSheet.newInstance(btnFilter.hint.toString())
                    filterPortfolioDialogBottomSheet.show(requireActivity().supportFragmentManager, "filter_portfolio")

                }
            }

        } catch (error: Exception) {
            // Must be safe
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun clickCountSortPerformance(click: Int)
    {
        binding.apply {
            if(click % 2 == 0)
            {
                val list = arrayListOf<Performance>()

                performanceList.map {
                    list.add(Performance(it.projectName,it.txtPercent))
                }

                list.sortBy{
                    it.txtPercent
                }

                performanceAdapter!!.notifyDataSetChanged()
                performanceAdapter = PerformanceAdapter(list)
                rvFilter.adapter = performanceAdapter
            }else{
                txtPerformance.text = "Performance"

                performanceAdapter!!.notifyDataSetChanged()
                performanceAdapter = PerformanceAdapter(performanceList)
                rvFilter.adapter = performanceAdapter
            }
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun clickCountSortChange(click: Int)
    {
        binding.apply {
            if(click%2 == 0)
            {
                val list = arrayListOf<Change>()

                changeList.map {
                    list.add(Change(it.projectName,it.txtPercent))
                }

                list.sortBy{
                    it.txtPercent
                }

                changeAdapter!!.notifyDataSetChanged()

                changeAdapter = ChangeAdapter(list)
                rvFilter.adapter = changeAdapter
            }else{
                sortChange = "sortDescend"

                changeAdapter!!.notifyDataSetChanged()
                changeAdapter = ChangeAdapter(changeList)
                rvFilter.adapter = changeAdapter
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun clickCountSortWeight(click: Int)
    {
        binding.apply {
            if(click%2 == 0)
            {
                val list = arrayListOf<Weight>()

                weightList.map {
                    list.add(Weight(it.projectName,it.txtPercent))
                }

                list.sortBy{
                    it.txtPercent
                }

                weightAdapter!!.notifyDataSetChanged()

                weightAdapter = WeightAdapter(list)
                rvFilter.adapter = weightAdapter
            }else{
                sortWeight = "sortDescend"

                weightAdapter!!.notifyDataSetChanged()
                weightAdapter = WeightAdapter(weightList)
                rvFilter.adapter = weightAdapter
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun clickCountSortBalance(click: Int)
    {
        binding.apply {
            if(click%2 == 0)
            {
                val list = arrayListOf<Balance>()

                balanceList.map {
                    list.add(Balance(it.projectName,it.ut,it.value))
                }

                list.sortBy {
                    it.value
                }

                balanceAdapter!!.notifyDataSetChanged()
                balanceAdapter = BalanceAdapter(list)
                rvFilter.adapter = balanceAdapter
            }else{
                sortBalance = "sortDescend"

                balanceAdapter!!.notifyDataSetChanged()
                balanceAdapter = BalanceAdapter(balanceList)
                rvFilter.adapter = balanceAdapter
            }
        }
    }

}