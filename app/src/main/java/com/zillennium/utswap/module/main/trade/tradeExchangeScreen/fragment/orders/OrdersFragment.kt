package com.zillennium.utswap.module.main.trade.tradeExchangeScreen.fragment.orders

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
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
    var clickFilter = 1
    var clickSort = 1

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


                imgFilter.setOnClickListener{
                    countClickFilter(clickFilter)
                    clickFilter++
                }

                imgSort.setOnClickListener {
                    countClickSort(clickSort)
                    clickSort++
                }

                val linearLayoutManager = LinearLayoutManager(requireContext())
                rvOrders.layoutManager = linearLayoutManager

                ordersAdapter = OrdersAdapter(
                    OrdersData.LIST_OF_ORDERS(),onClickDelete
                )

                rvOrders.adapter = ordersAdapter

                btnAll.setOnClickListener{
                    btnAll.background = ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.bg_circular)
                    btnBuy.background = ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.bg_border_red_transparent)
                    btnSell.background = ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.bg_border_green_correct)
                    txtAll.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.white))
                    txtBuy.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.success))
                    txtSell.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.danger))

                    ordersAdapter!!.notifyDataSetChanged()

                    ordersAdapter = OrdersAdapter(
                        OrdersData.LIST_OF_ORDERS(), onClickDelete
                    )

                    rvOrders.adapter = ordersAdapter
                }

                btnBuy.setOnClickListener{
                    btnAll.background = ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.bg_circular_border_blue)
                    btnBuy.background = ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.bg_circular)
                    btnBuy.backgroundTintList = ColorStateList.valueOf(
                        ContextCompat.getColor(
                            UTSwapApp.instance, R.color.success))
                    btnSell.background = ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.bg_border_red_transparent)
                    txtAll.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.primary))
                    txtBuy.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.white))
                    txtSell.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.danger))

                    val list = arrayListOf<Orders>()

                    OrdersData.LIST_OF_ORDERS().map {
                        if(it.txtStatus == "BUY"){
                            list.add(Orders(it.txtStatus, it.txtUT,it.txtDate, it.txtPrice))
                        }
                    }


                    ordersAdapter!!.notifyDataSetChanged()

                    ordersAdapter = OrdersAdapter(
                        list,
                        onClickDelete
                    )

                    rvOrders.adapter = ordersAdapter

                }

                btnSell.setOnClickListener{
                    btnAll.background = ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.bg_circular_border_blue)
                    btnSell.background = ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.bg_circular)
                    btnSell.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.red_ee1111))
                    btnBuy.background = ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.bg_border_green_correct)
                    txtAll.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.primary))
                    txtBuy.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.success))
                    txtSell.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.white))

                    val list = arrayListOf<Orders>()

                    OrdersData.LIST_OF_ORDERS().map {
                        if(it.txtStatus == "SELL"){
                            list.add(Orders(it.txtStatus, it.txtUT,it.txtDate, it.txtPrice))
                        }
                    }

                    ordersAdapter!!.notifyDataSetChanged()

                    ordersAdapter = OrdersAdapter(
                        list,
                        onClickDelete
                    )

                    rvOrders.adapter = ordersAdapter

                }

                btnLatest.setOnClickListener{
                    btnLatest.background = ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.bg_circular)
                    btnOldest.background = ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.bg_circular_border_blue)
                    btnBigToSmall.background = ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.bg_circular_border_blue)
                    btnSmallToBig.background = ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.bg_circular_border_blue)
                    txtLatest.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.white))
                    txtBigToSmall.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.primary))
                    txtSmallToBig.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.primary))
                    txtOldest.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.primary))

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


                }

                btnOldest.setOnClickListener{
                    btnOldest.background = ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.bg_circular)
                    btnLatest.background = ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.bg_circular_border_blue)
                    btnBigToSmall.background = ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.bg_circular_border_blue)
                    btnSmallToBig.background = ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.bg_circular_border_blue)
                    txtLatest.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.primary))
                    txtBigToSmall.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.primary))
                    txtSmallToBig.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.primary))
                    txtOldest.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.white))

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

                    ordersAdapter!!.notifyDataSetChanged()

                    ordersAdapter = OrdersAdapter(
                        list,
                        onClickDelete
                    )

                    rvOrders.adapter = ordersAdapter
                }

                btnSmallToBig.setOnClickListener{
                    btnSmallToBig.background = ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.bg_circular)
                    btnLatest.background = ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.bg_circular_border_blue)
                    btnBigToSmall.background = ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.bg_circular_border_blue)
                    btnOldest.background = ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.bg_circular_border_blue)
                    txtLatest.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.primary))
                    txtBigToSmall.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.primary))
                    txtSmallToBig.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.white))
                    txtOldest.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.primary))

                    val list = arrayListOf<Orders>()

                    OrdersData.LIST_OF_ORDERS().map {
                        list.add(Orders(it.txtStatus, it.txtUT,it.txtDate, it.txtPrice))
                    }

                    list.sortByDescending {
                        it.txtPrice
                    }

                    ordersAdapter!!.notifyDataSetChanged()

                    ordersAdapter = OrdersAdapter(
                        list,
                        onClickDelete
                    )

                    rvOrders.adapter = ordersAdapter
                }

                btnBigToSmall.setOnClickListener{
                    btnBigToSmall.background = ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.bg_circular)
                    btnLatest.background = ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.bg_circular_border_blue)
                    btnSmallToBig.background = ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.bg_circular_border_blue)
                    btnOldest.background = ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.bg_circular_border_blue)
                    txtLatest.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.primary))
                    txtBigToSmall.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.white))
                    txtSmallToBig.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.primary))
                    txtOldest.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.primary))

                    val list = arrayListOf<Orders>()

                    OrdersData.LIST_OF_ORDERS().map {
                        list.add(Orders(it.txtStatus, it.txtUT,it.txtDate, it.txtPrice))
                    }

                    list.sortBy {
                        it.txtPrice
                    }

                    ordersAdapter!!.notifyDataSetChanged()

                    ordersAdapter = OrdersAdapter(
                        list,
                        onClickDelete
                    )

                    rvOrders.adapter = ordersAdapter
                }
            }
        } catch (error: Exception) {
            // Must be safe
        }
    }

    @SuppressLint("ResourceAsColor")
    private fun countClickFilter(click : Int){
        binding.apply {
            if(click % 2 == 0)
            {
                icFilter.imageTintList =  ColorStateList.valueOf(R.color.primary)
                linearContainerFilter.visibility = View.VISIBLE
                linearContainerSort.visibility = View.GONE
            }else{
                linearContainerFilter.visibility = View.GONE
                linearContainerSort.visibility = View.GONE
            }
        }
    }

    private fun countClickSort(click : Int){
        binding.apply {
            if(click % 2 == 0)
            {
                linearContainerSort.visibility = View.VISIBLE
                linearContainerFilter.visibility = View.GONE
            }else{
                linearContainerSort.visibility = View.GONE
                linearContainerFilter.visibility = View.GONE
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