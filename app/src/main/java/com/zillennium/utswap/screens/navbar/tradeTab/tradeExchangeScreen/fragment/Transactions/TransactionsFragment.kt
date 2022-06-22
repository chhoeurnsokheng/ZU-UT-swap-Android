package com.zillennium.utswap.screens.navbar.tradeTab.tradeExchangeScreen.fragment.Transactions

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.zillennium.utswap.Datas.ListDatas.transactionsData.TransactionsData
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.FragmentExchangeTransactionsBinding
import com.zillennium.utswap.models.orders.Orders
import com.zillennium.utswap.screens.navbar.tradeTab.tradeDetailScreen.TransactionDetailActivity
import com.zillennium.utswap.screens.navbar.tradeTab.tradeExchangeScreen.fragment.Transactions.adapter.TransactionsAdapter
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class TransactionsFragment :
    BaseMvpFragment<TransactionsView.View, TransactionsView.Presenter, FragmentExchangeTransactionsBinding>(),
    TransactionsView.View {

    override var mPresenter: TransactionsView.Presenter = TransactionsPresenter()
    override val layoutResource: Int = R.layout.fragment_exchange_transactions
    private var transactionsAdapter: TransactionsAdapter? = null
    var clickFilter = 1
    var clickSort = 1

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
                    btnAll.background = ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.bg_circular)
                    btnBuy.background = ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.bg_border_red_transparent)
                    btnSell.background = ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.bg_border_green_correct)
                    txtAll.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.white))
                    txtBuy.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.success))
                    txtSell.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.main_red))

                    transactionsAdapter!!.notifyDataSetChanged()

                    transactionsAdapter = TransactionsAdapter(
                        TransactionsData.LIST_OF_TRANSACTIONS(),onClickTransactions
                    )

                    rvTransactions.adapter = transactionsAdapter
                }

                btnBuy.setOnClickListener{
                    btnAll.background = ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.bg_circular_border_blue)
                    btnBuy.background = ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.bg_circular)
                    btnBuy.backgroundTintList = ColorStateList.valueOf(
                        ContextCompat.getColor(
                            UTSwapApp.instance, R.color.success))
                    btnSell.background = ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.bg_border_red_transparent)
                    txtAll.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.color_main))
                    txtBuy.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.white))
                    txtSell.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.main_red))

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
                    btnAll.background = ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.bg_circular_border_blue)
                    btnSell.background = ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.bg_circular)
                    btnSell.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.red))
                    btnBuy.background = ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.bg_border_green_correct)
                    txtAll.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.color_main))
                    txtBuy.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.success))
                    txtSell.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.white))

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
                    btnLatest.background = ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.bg_circular)
                    btnOldest.background = ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.bg_circular_border_blue)
                    btnBigToSmall.background = ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.bg_circular_border_blue)
                    btnSmallToBig.background = ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.bg_circular_border_blue)
                    txtLatest.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.white))
                    txtBigToSmall.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.color_main))
                    txtSmallToBig.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.color_main))
                    txtOldest.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.color_main))

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
                    btnOldest.background = ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.bg_circular)
                    btnLatest.background = ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.bg_circular_border_blue)
                    btnBigToSmall.background = ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.bg_circular_border_blue)
                    btnSmallToBig.background = ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.bg_circular_border_blue)
                    txtLatest.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.color_main))
                    txtBigToSmall.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.color_main))
                    txtSmallToBig.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.color_main))
                    txtOldest.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.white))

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
                    btnSmallToBig.background = ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.bg_circular)
                    btnLatest.background = ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.bg_circular_border_blue)
                    btnBigToSmall.background = ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.bg_circular_border_blue)
                    btnOldest.background = ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.bg_circular_border_blue)
                    txtLatest.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.color_main))
                    txtBigToSmall.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.color_main))
                    txtSmallToBig.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.white))
                    txtOldest.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.color_main))

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
                    btnBigToSmall.background = ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.bg_circular)
                    btnLatest.background = ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.bg_circular_border_blue)
                    btnSmallToBig.background = ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.bg_circular_border_blue)
                    btnOldest.background = ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.bg_circular_border_blue)
                    txtLatest.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.color_main))
                    txtBigToSmall.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.white))
                    txtSmallToBig.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.color_main))
                    txtOldest.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.color_main))

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