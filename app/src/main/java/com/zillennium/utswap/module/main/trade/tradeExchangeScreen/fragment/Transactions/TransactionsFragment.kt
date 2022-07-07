package com.zillennium.utswap.module.main.trade.tradeExchangeScreen.fragment.Transactions

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.zillennium.utswap.Datas.ListDatas.ordersData.OrdersData
import com.zillennium.utswap.Datas.ListDatas.transactionsData.TransactionsData
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.FragmentExchangeTransactionsBinding
import com.zillennium.utswap.models.orders.Orders
import com.zillennium.utswap.module.main.trade.tradeDetailScreen.TransactionDetailActivity
import com.zillennium.utswap.module.main.trade.tradeExchangeScreen.fragment.Transactions.adapter.TransactionsAdapter
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class TransactionsFragment :
    BaseMvpFragment<TransactionsView.View, TransactionsView.Presenter, FragmentExchangeTransactionsBinding>(),
    TransactionsView.View {

    override var mPresenter: TransactionsView.Presenter = TransactionsPresenter()
    override val layoutResource: Int = R.layout.fragment_exchange_transactions
    private var transactionsAdapter: TransactionsAdapter? = null

    private var filter: Int = 0 //0: no filter
                                // 1: filter by buy , 2: filter by sell,

    private var sort: Int = 0 // 0: sort from latest to oldest
                             // 1: sort from oldest to latest

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("UseCompatLoadingForDrawables", "NotifyDataSetChanged")
    override fun initView() {
        super.initView()
        try {
            binding.apply {
//                if(SessionPreferences().SESSION_STATUS == true && SessionPreferences().SESSION_KYC  == true){
//                    txtMessage.visibility = View.GONE
//                    linearTransactionsHistory.visibility = View.VISIBLE
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
                rvTransactions.layoutManager = linearLayoutManager

                transactionsAdapter = TransactionsAdapter(
                    TransactionsData.LIST_OF_TRANSACTIONS(),onClickTransactions
                )

                rvTransactions.adapter = transactionsAdapter
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
                    transactionsAdapter!!.notifyDataSetChanged()

                    transactionsAdapter = TransactionsAdapter(
                        OrdersData.LIST_OF_ORDERS(),
                        onClickTransactions
                    )
                    rvTransactions.adapter = transactionsAdapter
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


                    transactionsAdapter!!.notifyDataSetChanged()

                    transactionsAdapter = TransactionsAdapter(
                        list,
                        onClickTransactions
                    )

                    rvTransactions.adapter = transactionsAdapter
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

                    transactionsAdapter!!.notifyDataSetChanged()

                    transactionsAdapter = TransactionsAdapter(
                        list,
                        onClickTransactions
                    )

                    rvTransactions.adapter = transactionsAdapter
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

                    transactionsAdapter!!.notifyDataSetChanged()

                    transactionsAdapter = TransactionsAdapter(
                        list,
                        onClickTransactions
                    )

                    rvTransactions.adapter = transactionsAdapter
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

                    transactionsAdapter!!.notifyDataSetChanged()

                    transactionsAdapter = TransactionsAdapter(
                        list,
                        onClickTransactions
                    )

                    rvTransactions.adapter = transactionsAdapter
                    icSort.setImageDrawable(UTSwapApp.instance.getDrawable(R.drawable.ic_sort_up))
                }
            }
        }
    }

    private val onClickTransactions: TransactionsAdapter.OnClickTransactions = object : TransactionsAdapter.OnClickTransactions{
        override fun onClickMe(orders: Orders) {
//            val bundle = bundleOf( "ut" to orders.txtUT)
//            findNavController().navigate(R.id.action_to_navigation_navbar_transaction_detail,bundle)

            val i = Intent(UTSwapApp.instance, TransactionDetailActivity::class.java)
            i.putExtra("date", orders.txtDate)
            i.putExtra("price", orders.txtPrice)
            i.putExtra("status", orders.txtStatus)
            i.putExtra("ut", orders.txtUT)
            startActivity(i)
        }

    }
}