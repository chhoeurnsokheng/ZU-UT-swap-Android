package com.zillennium.utswap.module.main.trade.tradeExchangeScreen.fragment.Transactions

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidstudy.networkmanager.Tovuti
import com.zillennium.utswap.Datas.GlobalVariable.SessionVariable
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.FragmentExchangeTransactionsBinding
import com.zillennium.utswap.models.tradingList.TradingList
import com.zillennium.utswap.module.main.news.newsDetail.NewsDetailActivity
import com.zillennium.utswap.module.main.trade.tradeDetailScreen.TransactionDetailActivity
import com.zillennium.utswap.module.main.trade.tradeExchangeScreen.fragment.Transactions.adapter.TransactionAdapter
import com.zillennium.utswap.utils.Constants


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
    private var filterPageBuy: Int =1
    private var filterPageSell: Int = 1
    private var sortString: String= "desc"

    @RequiresApi(Build.VERSION_CODES.O)
    override fun initView() {
        super.initView()
        onOtherActivity()
        onCallApi()
        SessionVariable.refreshMatchingTransaction.observe(this@TransactionsFragment){
            if(it){
                onSwipeRefresh()
                SessionVariable.refreshMatchingTransaction.value = false
            }
        }

//        SessionVariable.createMatchingTransaction.observe(this@TransactionsFragment){
//            if(it){
//                onSwipeRefresh()
//                SessionVariable.createMatchingTransaction.value = false
//            }
//        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onGetMatchingTransactionSuccess(data: TradingList.TradeMatchingTransactionRes) {
        binding.apply {

            progressBarReadMore.visibility = View.GONE
            layLoading.visibility = View.GONE
            progressBar.visibility = View.GONE

            if(data.data?.entrust?.isNotEmpty() == true){

                txtNoData.visibility = View.GONE
                rvTransactions.visibility = View.VISIBLE

                data.data?.entrust?.let { listMatchingTransaction.addAll(it) }

//                listMatchingTransaction.addAll(data.data?.entrust)

                transactionsAdapter = TransactionAdapter(
                    onClickTransactions
                )

                transactionsAdapter?.items = listMatchingTransaction

                rvTransactions.adapter = transactionsAdapter

                transactionsAdapter?.notifyDataSetChanged()
            }else{
                txtNoData.visibility = View.VISIBLE
                rvTransactions.visibility = View.GONE
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
                page = 1
                filterPageSell = 1
                filterPageBuy = 1
                listMatchingTransaction.clear()
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
                listMatchingTransaction.clear()
                progressBar.visibility = View.VISIBLE
                getSort(sort)
            }

            val linearLayoutManager = LinearLayoutManager(requireContext())
            rvTransactions.layoutManager = linearLayoutManager

            readMore.setOnClickListener {
                when(filter){
                    0 -> {
                        mPresenter.onGetMatchingTransaction(TradingList.TradeMatchingTransactionObj(Constants.OrderBookTable.marketNameOrderBook,filter,page,sortString),UTSwapApp.instance)
                    }
                    1 -> {
                        mPresenter.onGetMatchingTransaction(TradingList.TradeMatchingTransactionObj(Constants.OrderBookTable.marketNameOrderBook,filter,filterPageBuy,sortString),UTSwapApp.instance)
                    }
                    2 -> {
                        mPresenter.onGetMatchingTransaction(TradingList.TradeMatchingTransactionObj(Constants.OrderBookTable.marketNameOrderBook,filter,filterPageSell,sortString),UTSwapApp.instance)
                    }
                }
                progressBarReadMore.visibility = View.VISIBLE
            }
        }
    }

    private fun onCallApi(){
        Tovuti.from(UTSwapApp.instance).monitor { _, isConnected, _ ->
            if (isConnected) {
                mPresenter.onGetMatchingTransaction(TradingList.TradeMatchingTransactionObj(Constants.OrderBookTable.marketNameOrderBook,filter,page,sortString),UTSwapApp.instance)
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
                    mPresenter.onGetMatchingTransaction(TradingList.TradeMatchingTransactionObj(Constants.OrderBookTable.marketNameOrderBook,filter,page,sortString),UTSwapApp.instance)
                }
                1 -> {
                    txtFilter.setTextColor(resources.getColor(R.color.primary))
                    icFilter.imageTintList = ColorStateList.valueOf(
                        ContextCompat.getColor(
                            UTSwapApp.instance,
                            R.color.primary
                        )
                    )
                    mPresenter.onGetMatchingTransaction(TradingList.TradeMatchingTransactionObj(Constants.OrderBookTable.marketNameOrderBook,1,filterPageBuy,sortString),UTSwapApp.instance)
                }
                2 -> {
                    icFilter.imageTintList = ColorStateList.valueOf(
                        ContextCompat.getColor(
                            UTSwapApp.instance,
                            R.color.primary
                        )
                    )
                    txtFilter.setTextColor(resources.getColor(R.color.primary))
                    mPresenter.onGetMatchingTransaction(TradingList.TradeMatchingTransactionObj(Constants.OrderBookTable.marketNameOrderBook,2,filterPageSell,sortString),UTSwapApp.instance)
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
                    mPresenter.onGetMatchingTransaction(TradingList.TradeMatchingTransactionObj(Constants.OrderBookTable.marketNameOrderBook,filter,1,sortString),UTSwapApp.instance)
                    icSort.setImageDrawable(UTSwapApp.instance.getDrawable(R.drawable.ic_sort))
                    icSort.rotation = 0f
                }
                1 -> {
                    sortString = "asc"
                    mPresenter.onGetMatchingTransaction(TradingList.TradeMatchingTransactionObj(Constants.OrderBookTable.marketNameOrderBook,filter,1,sortString),UTSwapApp.instance)
                    icSort.setImageDrawable(UTSwapApp.instance.getDrawable(R.drawable.ic_sort))
                    icSort.rotation = 180f
                }
            }
        }
    }

    private val onClickTransactions: TransactionAdapter.OnClickTransactions = object : TransactionAdapter.OnClickTransactions{
        override fun onClickMe(orders: TradingList.TradeMatchingTransactionEntrust) {
            TransactionDetailActivity.launchTransactionDetailsActivity(requireActivity(), orders.id)
        }


    }

    private fun onSwipeRefresh(){
        binding.apply {
            page = 1
            filterPageBuy = 1
            filterPageSell = 1
            listMatchingTransaction.clear()
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
}