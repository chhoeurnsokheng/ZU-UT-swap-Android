package com.zillennium.utswap.screens.navbar.tradeTab.fragment.Transactions

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.zillennium.utswap.Datas.ListDatas.ordersData.OrdersData
import com.zillennium.utswap.Datas.ListDatas.transactionsData.TransactionsData
import com.zillennium.utswap.Datas.StoredPreferences.SessionPreferences
import com.zillennium.utswap.R
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.FragmentTransactionsBinding
import com.zillennium.utswap.models.orders.Orders
import com.zillennium.utswap.screens.navbar.tradeTab.fragment.Transactions.adapter.TransactionsAdapter
import com.zillennium.utswap.screens.navbar.tradeTab.fragment.orders.adapter.OrdersAdapter
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class TransactionsFragment :
    BaseMvpFragment<TransactionsView.View, TransactionsView.Presenter, FragmentTransactionsBinding>(),
    TransactionsView.View {

    override var mPresenter: TransactionsView.Presenter = TransactionsPresenter()
    override val layoutResource: Int = R.layout.fragment_transactions
    private var transactionsAdapter: TransactionsAdapter? = null
    var clickFilter = 1
    var clickSort = 1

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("UseCompatLoadingForDrawables", "NotifyDataSetChanged")
    override fun initView() {
        super.initView()
        try {
            binding.apply {
                if(SessionPreferences().SESSION_STATUS!!){
                    txtMessage.visibility = View.GONE
                    linearTransactionsHistory.visibility = View.VISIBLE
                }

                if(SessionPreferences().SESSION_KYC!!){
                    txtMessage.visibility = View.GONE
                    linearTransactionsHistory.visibility = View.VISIBLE
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
                rvTransactions.layoutManager = linearLayoutManager

                transactionsAdapter = TransactionsAdapter(
                    TransactionsData.LIST_OF_TRANSACTIONS(),onClickTransactions
                )

                rvTransactions.adapter = transactionsAdapter

                btnAll.setOnClickListener{
                    btnAll.background = resources.getDrawable(R.drawable.bg_circular)
                    btnBuy.background = resources.getDrawable(R.drawable.bg_border_red_transparent)
                    btnSell.background = resources.getDrawable(R.drawable.bg_border_green_correct)
                    txtAll.setTextColor(Color.parseColor("#FFFFFF"))
                    txtBuy.setTextColor(Color.parseColor("#08B471"))
                    txtSell.setTextColor(Color.parseColor("#FF0000"))

                    transactionsAdapter!!.notifyDataSetChanged()

                    transactionsAdapter = TransactionsAdapter(
                        TransactionsData.LIST_OF_TRANSACTIONS(),onClickTransactions
                    )

                    rvTransactions.adapter = transactionsAdapter
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

                    TransactionsData.LIST_OF_TRANSACTIONS().map {
                        if(it.txtStatus == "BUY"){
                            list.add(Orders(it.txtStatus, it.txtUT,it.txtDate, it.txtPrice))
                        }
                    }

                    transactionsAdapter!!.notifyDataSetChanged()

                    transactionsAdapter = TransactionsAdapter(
                        list,onClickTransactions
                    )

                    rvTransactions.adapter = transactionsAdapter

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

                    TransactionsData.LIST_OF_TRANSACTIONS().map {
                        if(it.txtStatus == "SELL"){
                            list.add(Orders(it.txtStatus, it.txtUT,it.txtDate, it.txtPrice))
                        }
                    }

                    transactionsAdapter!!.notifyDataSetChanged()

                    transactionsAdapter = TransactionsAdapter(
                        list,onClickTransactions
                    )

                    rvTransactions.adapter = transactionsAdapter

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

                    TransactionsData.LIST_OF_TRANSACTIONS().map {
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
                        list,onClickTransactions
                    )

                    rvTransactions.adapter = transactionsAdapter


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

                    TransactionsData.LIST_OF_TRANSACTIONS().map {
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
                        list,onClickTransactions
                    )

                    rvTransactions.adapter = transactionsAdapter
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

                    TransactionsData.LIST_OF_TRANSACTIONS().map {
                        list.add(Orders(it.txtStatus, it.txtUT,it.txtDate, it.txtPrice))
                    }

                    list.sortByDescending {
                        it.txtPrice
                    }

                    transactionsAdapter!!.notifyDataSetChanged()

                    transactionsAdapter = TransactionsAdapter(
                        list,onClickTransactions
                    )

                    rvTransactions.adapter = transactionsAdapter
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

                    TransactionsData.LIST_OF_TRANSACTIONS().map {
                        list.add(Orders(it.txtStatus, it.txtUT,it.txtDate, it.txtPrice))
                    }

                    list.sortBy {
                        it.txtPrice
                    }

                    transactionsAdapter!!.notifyDataSetChanged()

                    transactionsAdapter = TransactionsAdapter(
                        list,onClickTransactions
                    )

                    rvTransactions.adapter = transactionsAdapter
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

    private val onClickTransactions: TransactionsAdapter.OnClickTransactions = object : TransactionsAdapter.OnClickTransactions{
        override fun onClickMe(orders: Orders) {
            val bundle = bundleOf("date" to orders.txtDate,"price" to orders.txtPrice.toString(),"status" to orders.txtStatus, "ut" to orders.txtUT)
            findNavController().navigate(R.id.action_to_navigation_navbar_transaction_detail,bundle)
        }

    }
}