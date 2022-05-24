package com.zillennium.utswap.screens.navbar.tradeTab.fragment.tradeDetail

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.zillennium.utswap.R
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.FragmentTradeDetailBinding

import com.zillennium.utswap.screens.navbar.tradeTab.fragment.Transactions.TransactionsFragment
import com.zillennium.utswap.screens.navbar.tradeTab.fragment.allTransactions.AllTransactionsFragment
import com.zillennium.utswap.screens.navbar.tradeTab.fragment.chart.ChartFragment
import com.zillennium.utswap.screens.navbar.tradeTab.fragment.orderBook.OrderBookFragment
import com.zillennium.utswap.screens.navbar.tradeTab.fragment.orders.OrdersFragment
import com.zillennium.utswap.screens.navbar.tradeTab.fragment.tradeDetail.dialog.BuyAndSellBottomSheetDialog


class TradeDetailFragment :
    BaseMvpFragment<TradeDetailView.View, TradeDetailView.Presenter, FragmentTradeDetailBinding>(),
    TradeDetailView.View {

    override var mPresenter: TradeDetailView.Presenter = TradeDetailPresenter()
    override val layoutResource: Int = R.layout.fragment_trade_detail

    val NUM_PAGES = 2
    private var pageAdapter: FragmentStateAdapter? = null
    private var pageTableAdapter: FragmentStateAdapter? = null
    val NUM_PAGES_TABLE = 3


    @SuppressLint("UseCompatLoadingForDrawables", "ResourceAsColor")
    override fun initView() {
        super.initView()
        try {
            binding.apply {

                pageAdapter = ScreenSlidePageAdapter(this@TradeDetailFragment, NUM_PAGES)
                vpVerify.adapter = pageAdapter
                vpVerify.isUserInputEnabled = false
                vpVerify.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                    override fun onPageScrolled(
                        position: Int,
                        positionOffset: Float,
                        positionOffsetPixels: Int
                    ) {
                        super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                    }

                    override fun onPageSelected(position: Int) {
                        super.onPageSelected(position)
                    }

                    override fun onPageScrollStateChanged(state: Int) {
                        super.onPageScrollStateChanged(state)
                    }
                })

                orderBook.setOnClickListener { view ->
                    onChangeTabs(view)
                    vpVerify.setCurrentItem(0, false)
                }
                chart.setOnClickListener { view ->
                    onChangeTabs(view)
                    vpVerify.setCurrentItem(1, false)
                }

                pageTableAdapter = ScreenSlidePageTableAdapter(this@TradeDetailFragment,NUM_PAGES_TABLE)
                vpTable.adapter = pageTableAdapter
                vpTable.isUserInputEnabled = false
                vpTable.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                    override fun onPageScrolled(
                        position: Int,
                        positionOffset: Float,
                        positionOffsetPixels: Int
                    ) {
                        super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                    }

                    override fun onPageSelected(position: Int) {
                        super.onPageSelected(position)
                    }

                    override fun onPageScrollStateChanged(state: Int) {
                        super.onPageScrollStateChanged(state)
                    }
                })

                txtOrders.setOnClickListener { view ->
                    onChangeTabsTable(view)
                    vpTable.setCurrentItem(0, false)
                }
                txtTransactions.setOnClickListener { view ->
                    onChangeTabsTable(view)
                    vpTable.setCurrentItem(1, false)
                }
                txtAllTransactions.setOnClickListener { view ->
                    onChangeTabsTable(view)
                    vpTable.setCurrentItem(2, false)
                }



                btnBack.setOnClickListener {
                    findNavController().popBackStack()
                }

                btnBuy.setOnClickListener {
                    val buyAndSellBottomSheetDialog: BuyAndSellBottomSheetDialog =
                        BuyAndSellBottomSheetDialog.newInstance(
                        )
                    activity?.let { it1 -> buyAndSellBottomSheetDialog.show(it1.supportFragmentManager, "dgdgdg") }
                }

                btnSell.setOnClickListener {
                    val buyAndSellBottomSheetDialog: BuyAndSellBottomSheetDialog =
                        BuyAndSellBottomSheetDialog.newInstance(
                        )
                    activity?.let { it1 -> buyAndSellBottomSheetDialog.show(it1.supportFragmentManager, "dgdgdg") }
                }

            }
        } catch (error: Exception) {
            // Must be safe
        }
    }



    private fun onChangeTabs(view: View) {
        binding.apply {
            if (view.id == R.id.order_book) {
                tabSelect.animate()?.x(0f)?.duration = 100
                orderBook.setTextColor(resources.getColor(R.color.white))
                chart.setTextColor(resources.getColor(R.color.color_main))
            } else if (view.id == R.id.chart) {
                orderBook.setTextColor(resources.getColor(R.color.color_main))
                chart.setTextColor(resources.getColor(R.color.white))
                val size: Int = chart.width ?: 0
                tabSelect.animate().x(size.toFloat()).duration = 100
            }
        }

    }

    private fun onChangeTabsTable(view: View){
        binding.apply {
            when (view.id) {
                R.id.txt_orders -> {
                    tabSelectOrders.animate()?.x(0f)?.duration = 100
                    txtOrders.setTextColor(resources.getColor(R.color.white))
                    txtAllTransactions.setTextColor(resources.getColor(R.color.color_main))
                    txtTransactions.setTextColor(resources.getColor(R.color.color_main))
                }
                R.id.txt_transactions -> {
                    txtOrders.setTextColor(resources.getColor(R.color.color_main))
                    txtAllTransactions.setTextColor(resources.getColor(R.color.color_main))
                    txtTransactions.setTextColor(resources.getColor(R.color.white))
                    val size: Int = txtTransactions.width ?: 0
                    tabSelectOrders.animate().x(size.toFloat()).duration = 100
                }
                R.id.txt_all_transactions -> {
                    txtOrders.setTextColor(resources.getColor(R.color.color_main))
                    txtTransactions.setTextColor(resources.getColor(R.color.color_main))
                    txtAllTransactions.setTextColor(resources.getColor(R.color.white))
                    val sizeAllTrans: Int = (txtAllTransactions.x.toInt()) ?: 0

                    tabSelectOrders.animate().x(sizeAllTrans.toFloat()).duration = 100
                }
            }
        }
    }

    private class ScreenSlidePageAdapter(idTypeActivity: TradeDetailFragment?, NUM_PAGES: Int) :
        FragmentStateAdapter(idTypeActivity!!) {

        private val NUM_PAGES = NUM_PAGES

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> OrderBookFragment()
                else -> ChartFragment()
            }
        }

        override fun getItemCount(): Int {
            return NUM_PAGES
        }
    }

    private class ScreenSlidePageTableAdapter(idTypeActivity: TradeDetailFragment?, NUM_PAGES_TABLE: Int) :
        FragmentStateAdapter(idTypeActivity!!) {

        private val NUM_PAGES_TABLE = NUM_PAGES_TABLE

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> OrdersFragment()
                1 -> TransactionsFragment()
                else -> AllTransactionsFragment()
            }
        }

        override fun getItemCount(): Int {
            return NUM_PAGES_TABLE
        }
    }

    override fun onResume() {
        super.onResume()

        binding.apply {
            if(vpVerify.currentItem == 1){
                vpVerify.setCurrentItem(0, false)
            }
            if(vpTable.currentItem == 1){
                vpTable.setCurrentItem(0,false)
            }
        }

    }
}