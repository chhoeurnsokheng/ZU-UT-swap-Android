package com.zillennium.utswap.module.main.trade.tradeExchangeScreen.fragment.orderBook

import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.zillennium.utswap.Datas.GlobalVariable.SessionVariable
import com.zillennium.utswap.R
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
            }else{
                mPresenter.closeTradeOrderBookTable()
            }
        }

        fetchTradeOrderBookTable.observe(this@OrderBookFragment){

            println("========= Table Sell")

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
                orderBookBid = OrderBookBidAdapter()
                orderBookAsk?.items = orderBookAskList
                binding.rvAsk.adapter = orderBookAsk
            }else{
                SessionVariable.marketPriceSell.value = ""
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
                orderBookAsk = OrderBookAskAdapter()
                orderBookBid?.items = orderBookBidList
                binding.rvBid.adapter = orderBookBid
            }else{
                SessionVariable.marketPriceBuy.value = ""
            }

        }
    }

    private fun onOtherActivity(){
        binding.apply {
            val linearLayoutManager = LinearLayoutManager(requireContext())
            rvBid.layoutManager = linearLayoutManager
            orderBookBid = OrderBookBidAdapter()
            rvBid.adapter = orderBookBid
//            val tradeOrderBookBidAdapter = TradeOrderBookBidAdapter(
//                OrderBookListBidData.LIST_OF_ORDER_BOOK_BID()
//            )
//            rvBid.adapter = tradeOrderBookBidAdapter

            val linearLayoutManagerAsk = LinearLayoutManager(requireContext())
            rvAsk.layoutManager = linearLayoutManagerAsk
            orderBookAsk = OrderBookAskAdapter()
            rvAsk.adapter = orderBookAsk
//            val tradeOrderBookAskAdapter = TradeOrderBookAskAdapter(
//                OrderBookListAskData.LIST_OF_ORDER_BOOK_ASK()
//            )
//            rvAsk.adapter = tradeOrderBookAskAdapter
        }
    }
}
