package com.zillennium.utswap.module.main.trade.tradeExchangeScreen.fragment.Transactions

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.zillennium.utswap.Datas.GlobalVariable.SessionVariable
import com.zillennium.utswap.Datas.ListDatas.transactionsData.TransactionsData
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.FragmentExchangeTransactionsBinding
import com.zillennium.utswap.models.orders.Orders
import com.zillennium.utswap.models.tradingList.TradingList
import com.zillennium.utswap.module.main.trade.tradeDetailScreen.TransactionDetailActivity
import com.zillennium.utswap.module.main.trade.tradeExchangeScreen.TradeExchangeActivity
import com.zillennium.utswap.module.main.trade.tradeExchangeScreen.fragment.Transactions.adapter.TransactionAdapter
import com.zillennium.utswap.utils.Constants
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class TransactionsFragment :
    BaseMvpFragment<TransactionsView.View, TransactionsView.Presenter, FragmentExchangeTransactionsBinding>(),
    TransactionsView.View {

    override var mPresenter: TransactionsView.Presenter = TransactionsPresenter()
    override val layoutResource: Int = R.layout.fragment_exchange_transactions
    private var transactionsAdapter: TransactionAdapter? = null

    private var filter: Int = 0 //0: no filter
                                // 1: filter by buy , 2: filter by sell,

    private var sort: Int = 0 // 0: sort from latest to oldest
                             // 1: sort from oldest to latest

    private var listMatchingTransaction =  ArrayList<TradingList.TradeMatchingTransactionEntrust>()
    private var page: Int = 1
    private var totalPage = 1
    private var sortString: String= ""
    private var lastPosition = 0



    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("UseCompatLoadingForDrawables", "NotifyDataSetChanged")
    override fun initView() {
        super.initView()
        onOtherActivity()
        page = TradeExchangeActivity.page
        onCallApi(page)
        SessionVariable.requestPage.observe(this@TransactionsFragment){
            if(it){
                if (page <= totalPage) {
                    onCallApi(page)
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun onGetMatchingTransactionSuccess(data: TradingList.TradeMatchingTransactionRes) {
        binding.apply {
            if (page == 1) {
                listMatchingTransaction.clear()
            }

            totalPage = data.data?.TOTAL_PAGE!!

            if(data.data?.entrust?.isNotEmpty() == true){

                txtNoData.visibility = View.GONE

                page++

                listMatchingTransaction.addAll(data.data?.entrust!!)

                transactionsAdapter = TransactionAdapter(
                    onClickTransactions
                )

                transactionsAdapter?.items = listMatchingTransaction

                rvTransactions.adapter = transactionsAdapter

                progressBar.visibility = View.GONE

                transactionsAdapter?.notifyDataSetChanged()
            }else{
                txtNoData.visibility = View.VISIBLE
                rvTransactions.visibility = View.GONE
            }
        }
    }

    override fun onGetMatchingTransactionFail(data: TradingList.TradeMatchingTransactionRes) {

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun onOtherActivity(){
        binding.apply {
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

            rvTransactions.isNestedScrollingEnabled = false
        }
    }

    fun onCallApi(page: Int){
        mPresenter.onGetMatchingTransaction(TradingList.TradeMatchingTransactionObj(Constants.OrderBookTable.marketNameOrderBook,0,page,sortString),UTSwapApp.instance)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getFilter(filter: Int){
        binding.apply {
            when(filter){
                0 -> {
                    transactionsAdapter!!.notifyDataSetChanged()

                    transactionsAdapter = TransactionAdapter(
                        onClickTransactions
                    )
                    rvTransactions.adapter = transactionsAdapter
                    icFilter.imageTintList =  ColorStateList.valueOf(UTSwapApp.instance.getColor(R.color.backgroundHint))
                    txtFilter.setTextColor(Color.parseColor("#808080"))
                }
                1 -> {
                    val list = arrayListOf<Orders>()

                    TransactionsData.LIST_OF_TRANSACTIONS().map {
                        if(it.txtStatus == "BUY"){
                            list.add(Orders(it.txtStatus, it.txtUT,it.txtDate, it.txtPrice))
                        }
                    }


                    transactionsAdapter!!.notifyDataSetChanged()

                    transactionsAdapter = TransactionAdapter(
                        onClickTransactions
                    )

                    rvTransactions.adapter = transactionsAdapter
                    icFilter.imageTintList =  ColorStateList.valueOf(UTSwapApp.instance.getColor(R.color.primary))
                    txtFilter.setTextColor(Color.parseColor("#1B2266"))

                }
                2 -> {
                    val list = arrayListOf<Orders>()

                    TransactionsData.LIST_OF_TRANSACTIONS().map {
                        if(it.txtStatus == "SELL"){
                            list.add(Orders(it.txtStatus, it.txtUT,it.txtDate, it.txtPrice))
                        }
                    }

                    transactionsAdapter!!.notifyDataSetChanged()

                    transactionsAdapter = TransactionAdapter(
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

                    transactionsAdapter = TransactionAdapter(
                        onClickTransactions
                    )

                    rvTransactions.adapter = transactionsAdapter
                    icSort.setImageDrawable(UTSwapApp.instance.getDrawable(R.drawable.ic_sort))
                }

                1 -> {
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

                    transactionsAdapter = TransactionAdapter(
                        onClickTransactions
                    )

                    rvTransactions.adapter = transactionsAdapter
                    icSort.setImageDrawable(UTSwapApp.instance.getDrawable(R.drawable.ic_sort_up))
                }
            }
        }
    }

    private val onClickTransactions: TransactionAdapter.OnClickTransactions = object : TransactionAdapter.OnClickTransactions{
        override fun onClickMe(orders: TradingList.TradeMatchingTransactionEntrust) {
            val i = Intent(UTSwapApp.instance, TransactionDetailActivity::class.java)
            i.putExtra("date", orders.addtime)
            i.putExtra("price", orders.price)
            i.putExtra("status", orders.type)
            i.putExtra("ut", orders.num)
            startActivity(i)
        }


    }
}