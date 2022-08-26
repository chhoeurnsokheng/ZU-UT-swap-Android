package com.zillennium.utswap.module.main.trade.tradeExchangeScreen.fragment.orders

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.os.Build
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidstudy.networkmanager.Tovuti
import com.zillennium.utswap.Datas.GlobalVariable.SessionVariable
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.FragmentExchangeOrdersBinding
import com.zillennium.utswap.models.tradingList.TradingList
import com.zillennium.utswap.module.main.trade.tradeExchangeScreen.dialog.SuccessPlaceOrderDialog
import com.zillennium.utswap.module.main.trade.tradeExchangeScreen.fragment.orders.adapter.OrderAdapter
import com.zillennium.utswap.module.main.trade.tradeExchangeScreen.fragment.orders.dialog.DeleteOrdersDialog
import com.zillennium.utswap.utils.Constants

class OrdersFragment :
    BaseMvpFragment<OrdersView.View, OrdersView.Presenter, FragmentExchangeOrdersBinding>(),
    OrdersView.View {

    override var mPresenter: OrdersView.Presenter = OrdersPresenter()
    override val layoutResource: Int = R.layout.fragment_exchange_orders
    private var ordersAdapter: OrderAdapter? = null
    private var filter: Int = 0 //0: no filter
                                // 1: filter by buy , 2: filter by sell,

    private var sort: Int = 0 // 0: sort from latest to oldest
                              // 1: sort from oldest to latest

    private var listOrder =  ArrayList<TradingList.TradeOrderPendingEntrust>()
    private var page: Int = 1
    private var filterPageBuy: Int =1
    private var filterPageSell: Int = 1
    private var sortString: String = "desc"

    var handler = Handler()
    var runnable: Runnable? = null
    var delay = 5000

    @RequiresApi(Build.VERSION_CODES.O)
    override fun initView() {
        super.initView()
        onOtherActivity()
        onCallApi()
        SessionVariable.refreshOrderPending.observe(this@OrdersFragment){
            if(it){
                onSwipeRefresh()
                SessionVariable.refreshOrderPending.value = false
            }
        }

//        SessionVariable.createPendingOrder.observe(this@OrdersFragment){
//            if(it){
//                onSwipeRefresh()
//                SessionVariable.createPendingOrder.value = false
//            }
//        }
    }

    @SuppressLint("NotifyDataSetChanged")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onGetOrderPendingSuccess(data: TradingList.TradeOrderPendingRes) {
        binding.apply {

            progressBarReadMore.visibility = View.GONE
            layLoading.visibility = View.GONE
            progressBar.visibility = View.GONE

            if(data.data?.entrust?.isNotEmpty() == true){
                txtNoData.visibility = View.GONE
                rvOrders.visibility = View.VISIBLE

                listOrder.addAll(data.data?.entrust!!)

                ordersAdapter = OrderAdapter(
                    onClickDelete
                )

                ordersAdapter?.items = listOrder

                rvOrders.adapter = ordersAdapter

                ordersAdapter?.notifyDataSetChanged()
            }else{
                txtNoData.visibility = View.VISIBLE
                rvOrders.visibility = View.GONE
                layLoading.visibility = View.GONE
                readMore.visibility = View.GONE
            }

            if(filter == 1){
                if(filterPageBuy == data.data?.TOTAL_PAGE){
                    layLoading.visibility = View.GONE
                    readMore.visibility = View.GONE
                }
                else if(filterPageBuy > data.data?.TOTAL_PAGE!!){
                    layLoading.visibility = View.GONE
                    readMore.visibility = View.GONE
                    txtNoData.visibility = View.VISIBLE
                }
                else{
                    layLoading.visibility = View.VISIBLE
                    readMore.visibility = View.VISIBLE
                    filterPageBuy += 1
                }
            }else if(filter == 2){
                if(filterPageSell == data.data?.TOTAL_PAGE){
                    layLoading.visibility = View.GONE
                    readMore.visibility = View.GONE
                }else if(filterPageSell > data.data?.TOTAL_PAGE!!){
                    layLoading.visibility = View.GONE
                    readMore.visibility = View.GONE
                    txtNoData.visibility = View.VISIBLE
                }else{
                    layLoading.visibility = View.VISIBLE
                    readMore.visibility = View.VISIBLE
                    filterPageSell += 1
                }
            }else{
                if(page == data.data?.TOTAL_PAGE){
                    layLoading.visibility = View.GONE
                    readMore.visibility = View.GONE
                }else if(page > data.data?.TOTAL_PAGE!!){
                    layLoading.visibility = View.GONE
                    readMore.visibility = View.GONE
                    txtNoData.visibility = View.VISIBLE
                }else{
                    layLoading.visibility = View.VISIBLE
                    readMore.visibility = View.VISIBLE
                    page += 1
                }
            }
        }
    }

    override fun onGetOrderPendingFail(data: TradingList.TradeOrderPendingRes) {

    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("NotifyDataSetChanged", "UseCompatLoadingForDrawables")
    private fun onOtherActivity(){
        binding.apply {
            imgFilter.setOnClickListener {
                filter = when(filter){
                    0-> 1
                    1-> 2
                    else -> 0
                }
                page = 1
                filterPageSell = 1
                filterPageBuy = 1
                listOrder.clear()
                progressBar.visibility = View.VISIBLE
                getFilter(filter)
            }

            imgSort.setOnClickListener {
                sort = when(sort){
                    0 -> 1
                    else -> 0
                }
                page = 1
                filterPageSell = 1
                filterPageBuy = 1
                listOrder.clear()
                progressBar.visibility = View.VISIBLE
                getSort(sort)
            }

            val linearLayoutManager = LinearLayoutManager(requireContext())
            rvOrders.layoutManager = linearLayoutManager

            readMore.setOnClickListener {
               when (filter) {
                   0 -> {
                       mPresenter.onGetOrderPending(TradingList.TradeOrderPendingObj(Constants.OrderBookTable.marketNameOrderBook,filter,page,sortString),UTSwapApp.instance)
                   }
                   1 -> {
                       mPresenter.onGetOrderPending(TradingList.TradeOrderPendingObj(Constants.OrderBookTable.marketNameOrderBook,filter,filterPageBuy,sortString),UTSwapApp.instance)
                   }
                   else -> {
                       mPresenter.onGetOrderPending(TradingList.TradeOrderPendingObj(Constants.OrderBookTable.marketNameOrderBook,filter,filterPageSell,sortString),UTSwapApp.instance)
                   }
               }
                progressBarReadMore.visibility = View.VISIBLE
            }
        }
    }

    private fun onCallApi(){
        Tovuti.from(UTSwapApp.instance).monitor{ _, isConnected, _ ->
            if(isConnected)
            {
                mPresenter.onGetOrderPending(TradingList.TradeOrderPendingObj(Constants.OrderBookTable.marketNameOrderBook,filter,page,sortString),UTSwapApp.instance)
            }
        }
    }

    private fun getFilter(filter: Int){
        binding.apply {
            when(filter){
                0 -> {
                    icFilter.imageTintList = ColorStateList.valueOf(
                        ContextCompat.getColor(
                            UTSwapApp.instance,
                            R.color.gray_888888
                        )
                    )
                    txtFilter.setTextColor(resources.getColor(R.color.gray_888888))
                    mPresenter.onGetOrderPending(TradingList.TradeOrderPendingObj(Constants.OrderBookTable.marketNameOrderBook,filter,1,sortString),UTSwapApp.instance)
                }
                1 -> {
                    txtFilter.setTextColor(resources.getColor(R.color.primary))
                    icFilter.imageTintList = ColorStateList.valueOf(
                        ContextCompat.getColor(
                            UTSwapApp.instance,
                            R.color.primary
                        )
                    )
                    mPresenter.onGetOrderPending(TradingList.TradeOrderPendingObj(Constants.OrderBookTable.marketNameOrderBook,1,1,sortString),UTSwapApp.instance)
                }
                2 -> {
                    icFilter.imageTintList = ColorStateList.valueOf(
                        ContextCompat.getColor(
                            UTSwapApp.instance,
                            R.color.primary
                        )
                    )
                    txtFilter.setTextColor(resources.getColor(R.color.primary))
                    mPresenter.onGetOrderPending(TradingList.TradeOrderPendingObj(Constants.OrderBookTable.marketNameOrderBook,2,1,sortString),UTSwapApp.instance)
                }
            }
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun getSort(sort: Int){
        binding.apply {
            when(sort){
                0 -> {
                    sortString = "desc"
                    mPresenter.onGetOrderPending(TradingList.TradeOrderPendingObj(Constants.OrderBookTable.marketNameOrderBook,filter,1,sortString),UTSwapApp.instance)
                    icSort.setImageDrawable(UTSwapApp.instance.getDrawable(R.drawable.ic_sort))
                    icSort.rotation = 0f
                }
                1 -> {
                    sortString = "asc"
                    mPresenter.onGetOrderPending(TradingList.TradeOrderPendingObj(Constants.OrderBookTable.marketNameOrderBook,filter,1,sortString),UTSwapApp.instance)
                    icSort.setImageDrawable(UTSwapApp.instance.getDrawable(R.drawable.ic_sort))
                    icSort.rotation = 180f
                }
            }
        }
    }

    private val onClickDelete: OrderAdapter.OnClickDelete = object : OrderAdapter.OnClickDelete{
        override fun clickMe(tradeOrder: TradingList.TradeOrderPendingEntrust) {
            val deleteOrdersDialog: DeleteOrdersDialog =
                DeleteOrdersDialog.newInstance(tradeOrder.id.toString())
            activity?.supportFragmentManager?.let { deleteOrdersDialog.show(it, "asaf") }
        }

    }

    private fun onSwipeRefresh(){
        binding.apply {
            page = 1
            filterPageBuy = 1
            filterPageSell = 1
            listOrder.clear()
            filter = 0
            sortString = "desc"

            icFilter.imageTintList = ColorStateList.valueOf(
                ContextCompat.getColor(
                    UTSwapApp.instance,
                    R.color.backgroundHint
                )
            )
            txtFilter.setTextColor(resources.getColor(R.color.gray_888888))
            icSort.setImageDrawable(UTSwapApp.instance.getDrawable(R.drawable.ic_sort))
            icSort.rotation = 0f
            onCallApi()
        }
    }

//    override fun onResume() {
//        handler.postDelayed(Runnable {
//            runnable?.let { handler.postDelayed(it, delay.toLong()) }
//            mPresenter.onGetOrderPending(TradingList.TradeOrderPendingObj(Constants.OrderBookTable.marketNameOrderBook,filter,page,sortString),UTSwapApp.instance)
//        }.also { runnable = it }, delay.toLong())
//        super.onResume()
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        runnable?.let { handler.removeCallbacks(it) }; //stop handler when activity not visible super.onPause();
//    }
}