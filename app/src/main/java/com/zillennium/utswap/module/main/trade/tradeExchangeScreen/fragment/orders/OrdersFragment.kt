package com.zillennium.utswap.module.main.trade.tradeExchangeScreen.fragment.orders

import android.annotation.SuppressLint
import android.os.Build
import android.os.Handler
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidstudy.networkmanager.Tovuti
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.FragmentExchangeOrdersBinding
import com.zillennium.utswap.models.tradingList.TradingList
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
    private var countLoop: Int = 2
    private var totalPageTrans: Int  = 1

    @RequiresApi(Build.VERSION_CODES.O)
    override fun initView() {
        super.initView()
        onOtherActivity()
        onCallApi()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onGetOrderPendingSuccess(data: TradingList.TradeOrderPendingRes) {
        binding.apply {
            if(data.data?.entrust?.isNotEmpty() == true){
                txtNoData.visibility = View.GONE

                listOrder.addAll(data.data?.entrust!!)

                ordersAdapter = OrderAdapter(
                   onClickDelete
                )

                ordersAdapter?.items = listOrder

                rvOrders.adapter = ordersAdapter
            }else{
                txtNoData.visibility = View.VISIBLE
                rvOrders.visibility = View.GONE
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
                //getFilter(filter)
            }

            imgSort.setOnClickListener {
                sort = when(sort){
                    0 -> 1
                    else -> 0
                }
                //getSort(sort)
            }

            val linearLayoutManager = LinearLayoutManager(requireContext())
            rvOrders.layoutManager = linearLayoutManager
        }
    }

    private fun onCallApi(){
        Tovuti.from(UTSwapApp.instance).monitor{ _, isConnected, _ ->
            if(isConnected)
            {
                mPresenter.onGetOrderPending(TradingList.TradeOrderPendingObj(Constants.OrderBookTable.marketNameOrderBook),UTSwapApp.instance)
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
//    private fun getFilter(filter: Int){
//        binding.apply {
//            when(filter){
//                0 -> {
//                    ordersAdapter?.notifyDataSetChanged()
//
//                    ordersAdapter = OrdersAdapter(
//                        listOrder, onClickDelete
//                    )
//                    rvOrders.adapter = ordersAdapter
//                    icFilter.imageTintList =  ColorStateList.valueOf(UTSwapApp.instance.getColor(R.color.backgroundHint))
//                    txtFilter.setTextColor(Color.parseColor("#808080"))
//                }
//                1 -> {
//                    val list = arrayListOf<Orders>()
//
//                    OrdersData.LIST_OF_ORDERS().map {
//                        if(it.txtStatus == "Limit / Buy"){
//                            list.add(Orders(it.txtStatus, it.txtUT,it.txtDate, it.txtPrice))
//                        }
//                    }
//
//
//                    ordersAdapter?.notifyDataSetChanged()
//
//                    ordersAdapter = OrdersAdapter(
//                        list,
//                        onClickDelete
//                    )
//
//                    rvOrders.adapter = ordersAdapter
//                    icFilter.imageTintList =  ColorStateList.valueOf(UTSwapApp.instance.getColor(R.color.primary))
//                    txtFilter.setTextColor(Color.parseColor("#1B2266"))
//
//                }
//                2 -> {
//                    val list = arrayListOf<Orders>()
//
//                    OrdersData.LIST_OF_ORDERS().map {
//                        if(it.txtStatus == "Limit / Sell"){
//                            list.add(Orders(it.txtStatus, it.txtUT,it.txtDate, it.txtPrice))
//                        }
//                    }
//
//                    ordersAdapter?.notifyDataSetChanged()
//
//                    ordersAdapter = OrdersAdapter(
//                        list,
//                        onClickDelete
//                    )
//
//                    rvOrders.adapter = ordersAdapter
//                    icFilter.imageTintList =  ColorStateList.valueOf(UTSwapApp.instance.getColor(R.color.primary))
//                    txtFilter.setTextColor(Color.parseColor("#1B2266"))
//                }
//            }
//        }
//    }

    @RequiresApi(Build.VERSION_CODES.O)
//    private fun getSort(sort: Int){
//        binding.apply {
//            when(sort){
//                0 -> {
//                    val list = arrayListOf<Orders>()
//
//                    OrdersData.LIST_OF_ORDERS().map {
//                        list.add(Orders(it.txtStatus, it.txtUT,it.txtDate, it.txtPrice))
//                    }
//
//                    val dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
//                    list.sortByDescending {
//                        LocalDate.parse(
//                            it.txtDate,
//                            dateTimeFormatter
//                        )
//                    }
//
//                    ordersAdapter!!.notifyDataSetChanged()
//
//                    ordersAdapter = OrdersAdapter(
//                        list,
//                        onClickDelete
//                    )
//
//                    rvOrders.adapter = ordersAdapter
//                    icSort.setImageDrawable(UTSwapApp.instance.getDrawable(R.drawable.ic_sort))
//                }
//
//                1 -> {
//                    val list = arrayListOf<Orders>()
//
//                    OrdersData.LIST_OF_ORDERS().map {
//                        list.add(Orders(it.txtStatus, it.txtUT,it.txtDate, it.txtPrice))
//                    }
//
//                    val dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
//                    list.sortBy {
//                        LocalDate.parse(
//                            it.txtDate,
//                            dateTimeFormatter
//                        )
//                    }
//
//                    ordersAdapter?.notifyDataSetChanged()
//
//                    ordersAdapter = OrdersAdapter(
//                        list,
//                        onClickDelete
//                    )
//
//                    rvOrders.adapter = ordersAdapter
//                    icSort.setImageDrawable(UTSwapApp.instance.getDrawable(R.drawable.ic_sort_up))
//                }
//            }
//        }
//    }

    private val onClickDelete: OrderAdapter.OnClickDelete = object : OrderAdapter.OnClickDelete{
        override fun clickMe() {
            val deleteOrdersDialog: DeleteOrdersDialog =
                DeleteOrdersDialog.newInstance()
            activity?.supportFragmentManager?.let { deleteOrdersDialog.show(it, "asaf") }
        }

    }
}