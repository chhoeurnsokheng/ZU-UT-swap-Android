package com.zillennium.utswap.screens.navbar.tradeTab.fragment.orders

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.zillennium.utswap.Datas.ListDatas.ordersData.OrdersData
import com.zillennium.utswap.Datas.StoredPreferences.SessionPreferences
import com.zillennium.utswap.R
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.FragmentOrdersBinding
import com.zillennium.utswap.models.orders.Orders
import com.zillennium.utswap.screens.navbar.tradeTab.fragment.orders.adapter.OrdersAdapter
import com.zillennium.utswap.screens.navbar.tradeTab.fragment.orders.dialog.DeleteOrdersDialog
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class OrdersFragment :
    BaseMvpFragment<OrdersView.View, OrdersView.Presenter, FragmentOrdersBinding>(),
    OrdersView.View {

    override var mPresenter: OrdersView.Presenter = OrdersPresenter()
    override val layoutResource: Int = R.layout.fragment_orders
    private var ordersAdapter: OrdersAdapter? = null
    var clickFilter = 1
    var clickSort = 1

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("NotifyDataSetChanged", "UseCompatLoadingForDrawables")
    override fun initView() {
        super.initView()
        try {
            binding.apply {

                if(SessionPreferences().SESSION_STATUS  == true && SessionPreferences().SESSION_KYC  == true){
                    txtMessage.visibility = View.GONE
                    linearOrderHistory.visibility = View.VISIBLE
                }

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
                    btnAll.background = resources.getDrawable(R.drawable.bg_circular)
                    btnBuy.background = resources.getDrawable(R.drawable.bg_border_red_transparent)
                    btnSell.background = resources.getDrawable(R.drawable.bg_border_green_correct)
                    txtAll.setTextColor(Color.parseColor("#FFFFFF"))
                    txtBuy.setTextColor(Color.parseColor("#08B471"))
                    txtSell.setTextColor(Color.parseColor("#FF0000"))

                    ordersAdapter!!.notifyDataSetChanged()

                    ordersAdapter = OrdersAdapter(
                        OrdersData.LIST_OF_ORDERS(), onClickDelete
                    )

                    rvOrders.adapter = ordersAdapter
                }

                btnBuy.setOnClickListener{
                    btnAll.background = resources.getDrawable(R.drawable.bg_circular_border_blue)
                    btnBuy.background = resources.getDrawable(R.drawable.bg_circular)
                    btnBuy.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.success))
                    btnSell.background = resources.getDrawable(R.drawable.bg_border_red_transparent)
                    txtAll.setTextColor(Color.parseColor("#252552"))
                    txtBuy.setTextColor(Color.parseColor("#FFFFFF"))
                    txtSell.setTextColor(Color.parseColor("#FF0000"))

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
                    btnAll.background = resources.getDrawable(R.drawable.bg_circular_border_blue)
                    btnSell.background = resources.getDrawable(R.drawable.bg_circular)
                    btnSell.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.red))
                    btnBuy.background = resources.getDrawable(R.drawable.bg_border_green_correct)
                    txtAll.setTextColor(Color.parseColor("#252552"))
                    txtBuy.setTextColor(Color.parseColor("#08B471"))
                    txtSell.setTextColor(Color.parseColor("#FFFFFF"))

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
                    btnLatest.background = resources.getDrawable(R.drawable.bg_circular)
                    btnOldest.background = resources.getDrawable(R.drawable.bg_circular_border_blue)
                    btnBigToSmall.background = resources.getDrawable(R.drawable.bg_circular_border_blue)
                    btnSmallToBig.background = resources.getDrawable(R.drawable.bg_circular_border_blue)
                    txtLatest.setTextColor(Color.parseColor("#FFFFFF"))
                    txtBigToSmall.setTextColor(Color.parseColor("#252552"))
                    txtSmallToBig.setTextColor(Color.parseColor("#252552"))
                    txtOldest.setTextColor(Color.parseColor("#252552"))

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
                    btnOldest.background = resources.getDrawable(R.drawable.bg_circular)
                    btnLatest.background = resources.getDrawable(R.drawable.bg_circular_border_blue)
                    btnBigToSmall.background = resources.getDrawable(R.drawable.bg_circular_border_blue)
                    btnSmallToBig.background = resources.getDrawable(R.drawable.bg_circular_border_blue)
                    txtLatest.setTextColor(Color.parseColor("#252552"))
                    txtBigToSmall.setTextColor(Color.parseColor("#252552"))
                    txtSmallToBig.setTextColor(Color.parseColor("#252552"))
                    txtOldest.setTextColor(Color.parseColor("#FFFFFF"))

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
                    btnSmallToBig.background = resources.getDrawable(R.drawable.bg_circular)
                    btnLatest.background = resources.getDrawable(R.drawable.bg_circular_border_blue)
                    btnBigToSmall.background = resources.getDrawable(R.drawable.bg_circular_border_blue)
                    btnOldest.background = resources.getDrawable(R.drawable.bg_circular_border_blue)
                    txtLatest.setTextColor(Color.parseColor("#252552"))
                    txtBigToSmall.setTextColor(Color.parseColor("#252552"))
                    txtSmallToBig.setTextColor(Color.parseColor("#FFFFFF"))
                    txtOldest.setTextColor(Color.parseColor("#252552"))

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
                    btnBigToSmall.background = resources.getDrawable(R.drawable.bg_circular)
                    btnLatest.background = resources.getDrawable(R.drawable.bg_circular_border_blue)
                    btnSmallToBig.background = resources.getDrawable(R.drawable.bg_circular_border_blue)
                    btnOldest.background = resources.getDrawable(R.drawable.bg_circular_border_blue)
                    txtLatest.setTextColor(Color.parseColor("#252552"))
                    txtBigToSmall.setTextColor(Color.parseColor("#FFFFFF"))
                    txtSmallToBig.setTextColor(Color.parseColor("#252552"))
                    txtOldest.setTextColor(Color.parseColor("#252552"))

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
                icFilter.imageTintList =  ColorStateList.valueOf(R.color.color_main)
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