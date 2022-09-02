package com.zillennium.utswap.module.main.trade.tradeExchangeScreen.fragment.orderBook

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidstudy.networkmanager.Tovuti
import com.zillennium.utswap.Datas.GlobalVariable.SessionVariable
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.FragmentExchangeOrderBookBinding
import com.zillennium.utswap.models.tradingList.TradeOrderBookAsk
import com.zillennium.utswap.models.tradingList.TradeOrderBookBid
import com.zillennium.utswap.models.tradingList.TradingList
import com.zillennium.utswap.module.main.trade.tradeExchangeScreen.fragment.orderBook.adapter.OrderBookAskAdapter
import com.zillennium.utswap.module.main.trade.tradeExchangeScreen.fragment.orderBook.adapter.OrderBookBidAdapter
import com.zillennium.utswap.utils.Constants

class OrderBookFragment :
    BaseMvpFragment<OrderBookView.View, OrderBookView.Presenter, FragmentExchangeOrderBookBinding>(),
    OrderBookView.View {

    override var mPresenter: OrderBookView.Presenter = OrderBookPresenter()
    override val layoutResource: Int = R.layout.fragment_exchange_order_book

    private var orderBookAsk: OrderBookAskAdapter? = null
    private var orderBookBid: OrderBookBidAdapter? = null

    private var orderBookBidList = ArrayList<TradeOrderBookBid>()
    private var orderBookAskList = ArrayList<TradeOrderBookAsk>()

    override var fetchTradeOrderBookTable: MutableLiveData<TradingList.TradeOrderBookTableRes> = MutableLiveData()


    override fun initView() {
        super.initView()
        onOtherActivity()
        SessionVariable.requestOrderBookSocket.observe(this@OrderBookFragment){
            if(it){
                mPresenter.startTradeOrderBookTable(Constants.OrderBookTable.marketNameOrderBook)
//                Tovuti.from(UTSwapApp.instance).monitor { _, isConnected, _ ->
//                    if (isConnected) {
//                        orderBookBidList.clear()
//                        orderBookAskList.clear()
//                        orderBookBid?.clear()
//                        orderBookAsk?.clear()
//                        mPresenter.startTradeOrderBookTable(Constants.OrderBookTable.marketNameOrderBook)
//                    }else{
//                        orderBookBidList.clear()
//                        orderBookAskList.clear()
//                        orderBookBid?.clear()
//                        orderBookAsk?.clear()
//                    }
//                }
            }else{
                clearData()
            }
        }

        fetchTradeOrderBookTable.observe(this@OrderBookFragment){

            orderBookBidList.clear()
            orderBookAskList.clear()

            if(!it.sell.isNullOrEmpty())
            {
                for(i in it.sell?.indices!!)
                {
                    orderBookAskList.add(
                        TradeOrderBookAsk(
                            it.sell?.get(i)?.get(0).toString(),
                            it.sell?.get(i)?.get(1).toString()
                        )
                    )
                }
                SessionVariable.marketPriceSell.value = it.sell?.get(0)?.get(0).toString()
                orderBookAsk = OrderBookAskAdapter()
                orderBookAsk?.items = orderBookAskList
                binding.rvAsk.adapter = orderBookAsk
            }else{
                SessionVariable.marketPriceSell.value = "0.00"
                orderBookAsk?.clear()
            }

            if(!it.buy.isNullOrEmpty()){
                for(i in it.buy?.indices!!)
                {
                    orderBookBidList.add(
                        TradeOrderBookBid(
                            it.buy?.get(i)?.get(0).toString(),
                            it.buy?.get(i)?.get(1).toString()
                        )
                    )
                }
                SessionVariable.marketPriceBuy.value = it.buy?.get(0)?.get(0).toString()
                orderBookBid = OrderBookBidAdapter()
                orderBookBid?.items = orderBookBidList
                binding.rvBid.adapter = orderBookBid
            }else{
                SessionVariable.marketPriceBuy.value = "0.00"
                orderBookBid?.clear()
            }

        }
    }

    private fun onOtherActivity(){
        binding.apply {
            orderBookBidList.clear()
            orderBookAskList.clear()
            orderBookBid?.clear()
            orderBookAsk?.clear()

            val linearLayoutManager = LinearLayoutManager(requireContext())
            rvBid.layoutManager = linearLayoutManager
            orderBookBid = OrderBookBidAdapter()
            rvBid.adapter = orderBookBid

            val linearLayoutManagerAsk = LinearLayoutManager(requireContext())
            rvAsk.layoutManager = linearLayoutManagerAsk
            orderBookAsk = OrderBookAskAdapter()
            rvAsk.adapter = orderBookAsk
        }
    }

    fun clearData(){
        orderBookBidList.clear()
        orderBookAskList.clear()
        mPresenter.closeTradeOrderBookTable()
    }
}
