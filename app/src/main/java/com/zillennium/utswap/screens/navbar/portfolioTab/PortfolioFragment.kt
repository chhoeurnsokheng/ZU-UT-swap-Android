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
    private var filter: Int = 0 // 0 = no sort, 1 = asc sort, 2 = desc sort

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
                txtBalance.paint.maskFilter = blurMask

                imgVisibility.setOnClickListener {
                    blurCondition = !blurCondition

                    if (blurCondition) {
                        txtBalance.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
                        txtBalance.paint.maskFilter = null
                        imgVisibility.setImageResource(R.drawable.ic_baseline_remove_red_eye_24)
                    } else {
                        txtBalance.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
                        txtBalance.paint.maskFilter = blurMask
                        imgVisibility.setImageResource(R.drawable.ic_baseline_visibility_off_24)
                    }
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

                           changeAdapter = ChangeAdapter(changeList)

                           rvFilter.adapter = changeAdapter

                           txtTradingBalance.text = "$6 420.99"

                           imgSortChange.setImageResource(R.drawable.ic_sort_arrow_up_down)
                           laySortChange.setOnClickListener {

                               val list = arrayListOf<Change>()
                               list.addAll(changeList)
                               filter++

                               when(filter){
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

                           linearLayoutWeight.visibility = View.VISIBLE

                           txtTradingBalance.text = "16.8%"

                           imgSortWeight.setImageResource(R.drawable.ic_sort_arrow_up_down)
                           layWeight.setOnClickListener {

                               val list = arrayListOf<Weight>()
                               list.addAll(weightList)
                               filter++

                               when(filter){
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

                           linearLayoutBalance.visibility = View.VISIBLE

                           txtTradingBalance.text = "$6 420.99"

                           imgSortBalance.setImageResource(R.drawable.ic_sort_arrow_up_down)
                           layBalance.setOnClickListener {

                               val list = arrayListOf<Balance>()
                               list.addAll(balanceList)
                               filter++

                               when(filter){
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

                               when(filter){
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
                       }
                   }

               }

                layFilter.setOnClickListener {
                    val filterPortfolioDialogBottomSheet: FilterPortfolioDialogBottomSheet = FilterPortfolioDialogBottomSheet.newInstance(btnFilter.text.toString())
                    filterPortfolioDialogBottomSheet.show(requireActivity().supportFragmentManager, "filter_portfolio")

                }
            }

        } catch (error: Exception) {
            // Must be safe
        }
    }

}