package com.zillennium.utswap.module.main.trade.tradeExchangeScreen.fragment.orders

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.zillennium.utswap.Datas.ListDatas.ordersData.OrdersData
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.FragmentExchangeOrdersBinding
import com.zillennium.utswap.models.orders.Orders
import com.zillennium.utswap.module.main.trade.tradeExchangeScreen.fragment.orders.adapter.OrdersAdapter
import com.zillennium.utswap.module.main.trade.tradeExchangeScreen.fragment.orders.dialog.DeleteOrdersDialog
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class OrdersFragment :
    BaseMvpFragment<OrdersView.View, OrdersView.Presenter, FragmentExchangeOrdersBinding>(),
    OrdersView.View {

    override var mPresenter: OrdersView.Presenter = OrdersPresenter()
    override val layoutResource: Int = R.layout.fragment_exchange_orders
    private var ordersAdapter: OrdersAdapter? = null
    private var filter: Int = 0 //0: no filter
                                // 1: filter by buy , 2: filter by sell,

    private var sort: Int = 0 // 0: sort from latest to oldest
                              // 1: sort from oldest to latest

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("NotifyDataSetChanged", "UseCompatLoadingForDrawables")
    override fun initView() {
        super.initView()
        try {
            binding.apply {

//                if(SessionPreferences().SESSION_STATUS  == true && SessionPreferences().SESSION_KYC  == true){
//                    txtMessage.visibility = View.GONE
//                    linearOrderHistory.visibility = View.VISIBLE
//                }

                imgFilter.setOnClickListener {
                    filter = when(filter){
                        0-> 1
                        1-> 2
                        else -> 0
                    }
                    getFilter(filter)
                }

                imgSort.setOnClickListener {
                    sort = when(sort){
                        0 -> 1
                        else -> 0
                    }
                    getSort(sort)
                }

                val linearLayoutManager = LinearLayoutManager(requireContext())
                rvOrders.layoutManager = linearLayoutManager

                ordersAdapter = OrdersAdapter(
                    OrdersData.LIST_OF_ORDERS(),onClickDelete
                )

                rvOrders.adapter = ordersAdapter
            }
        } catch (error: Exception) {
            // Must be safe
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getFilter(filter: Int){
        binding.apply {
            when(filter){
                0 -> {
                    ordersAdapter?.notifyDataSetChanged()

                    ordersAdapter = OrdersAdapter(
                        OrdersData.LIST_OF_ORDERS(), onClickDelete
                    )
                    rvOrders.adapter = ordersAdapter
                    icFilter.imageTintList =  ColorStateList.valueOf(UTSwapApp.instance.getColor(R.color.backgroundHint))
                    txtFilter.setTextColor(Color.parseColor("#808080"))
                }
                1 -> {
                    val list = arrayListOf<Orders>()

                    OrdersData.LIST_OF_ORDERS().map {
                        if(it.txtStatus == "BUY"){
                            list.add(Orders(it.txtStatus, it.txtUT,it.txtDate, it.txtPrice))
                        }
                    }


                    ordersAdapter?.notifyDataSetChanged()

                    ordersAdapter = OrdersAdapter(
                        list,
                        onClickDelete
                    )

                    rvOrders.adapter = ordersAdapter
                    icFilter.imageTintList =  ColorStateList.valueOf(UTSwapApp.instance.getColor(R.color.primary))
                    txtFilter.setTextColor(Color.parseColor("#1B2266"))

                }
                2 -> {
                    val list = arrayListOf<Orders>()

                    OrdersData.LIST_OF_ORDERS().map {
                        if(it.txtStatus == "SELL"){
                            list.add(Orders(it.txtStatus, it.txtUT,it.txtDate, it.txtPrice))
                        }
                    }

                    ordersAdapter?.notifyDataSetChanged()

                    ordersAdapter = OrdersAdapter(
                        list,
                        onClickDelete
                    )

                    rvOrders.adapter = ordersAdapter
                    icFilter.imageTintList =  ColorStateList.valueOf(UTSwapApp.instance.getColor(R.color.primary))
                    txtFilter.setTextColor(Color.parseColor("#1B2266"))
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("NotifyDataSetChanged", "UseCompatLoadingForDrawables")
    private fun getSort(sort: Int){
        binding.apply {
            when(sort){
                0 -> {
                    val list = arrayListOf<Orders>()

                    OrdersData.LIST_OF_ORDERS().map {
                        list.add(Orders(it.txtStatus, it.txtUT,it.txtDate, it.txtPrice))
                    }

                    val dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
                    list.sortByDescending {
                        LocalDate.parse(
                            it.txtDate,
                            dateTimeFormatter
                        )
                    }

                    ordersAdapter!!.notifyDataSetChanged()

                    ordersAdapter = OrdersAdapter(
                        list,
                        onClickDelete
                    )

                    rvOrders.adapter = ordersAdapter
                    icSort.setImageDrawable(UTSwapApp.instance.getDrawable(R.drawable.ic_sort))
                }

                1 -> {
                    val list = arrayListOf<Orders>()

                    OrdersData.LIST_OF_ORDERS().map {
                        list.add(Orders(it.txtStatus, it.txtUT,it.txtDate, it.txtPrice))
                    }

                    val dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
                    list.sortBy {
                        LocalDate.parse(
                            it.txtDate,
                            dateTimeFormatter
                        )
                    }

                    ordersAdapter?.notifyDataSetChanged()

                    ordersAdapter = OrdersAdapter(
                        list,
                        onClickDelete
                    )

                    rvOrders.adapter = ordersAdapter
                    icSort.setImageDrawable(UTSwapApp.instance.getDrawable(R.drawable.ic_sort_up))
                }
            }
        }
    }

    private val onClickDelete: OrdersAdapter.OnClickDelete = object : OrdersAdapter.OnClickDelete{
        override fun clickMe() {
            val deleteOrdersDialog: DeleteOrdersDialog =
                DeleteOrdersDialog.newInstance()
            activity?.supportFragmentManager?.let { deleteOrdersDialog.show(it, "asaf") }
        }

    }
}