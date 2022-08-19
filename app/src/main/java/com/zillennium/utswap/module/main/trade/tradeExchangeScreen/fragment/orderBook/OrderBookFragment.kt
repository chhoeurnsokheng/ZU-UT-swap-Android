package com.zillennium.utswap.module.main.trade.tradeExchangeScreen.fragment.orderBook

import android.os.Handler
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidstudy.networkmanager.Tovuti
import com.zillennium.utswap.Datas.ListDatas.orderBookListAskData.OrderBookListAskData
import com.zillennium.utswap.Datas.ListDatas.orderBookListBidData.OrderBookListBidData
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.FragmentExchangeOrderBookBinding
import com.zillennium.utswap.models.tradingList.TradingList
import com.zillennium.utswap.module.main.trade.tradeExchangeScreen.fragment.orderBook.adapter.TradeOrderBookAskAdapter
import com.zillennium.utswap.module.main.trade.tradeExchangeScreen.fragment.orderBook.adapter.TradeOrderBookBidAdapter
import com.zillennium.utswap.utils.Constants

class OrderBookFragment :
    BaseMvpFragment<OrderBookView.View, OrderBookView.Presenter, FragmentExchangeOrderBookBinding>(),
    OrderBookView.View {

    override var mPresenter: OrderBookView.Presenter = OrderBookPresenter()
    override val layoutResource: Int = R.layout.fragment_exchange_order_book

    override var fetchTradeOrderBookTable: MutableLiveData<TradingList.TradeOrderBookTableRes> = MutableLiveData()


    override fun initView() {
        super.initView()
        try {

            mPresenter.startTradeOrderBookTable(Constants.OrderBookTable.marketNameOrderBook)

//            Handler().postDelayed({
//                mPresenter.startTradeOrderBookTable(Constants.OrderBookTable.marketNameOrderBook)
//            }, 5000)



//            Tovuti.from(UTSwapApp.instance).monitor{ _, isConnected, _ ->
//                if(isConnected)
//                {
//
//                }
//            }

            fetchTradeOrderBookTable.observe(this@OrderBookFragment){
                println("===== table  ===" + it.market.toString())
            }

            binding.apply {
                val linearLayoutManager = LinearLayoutManager(requireContext())
                rvBid.layoutManager = linearLayoutManager
                val tradeOrderBookBidAdapter = TradeOrderBookBidAdapter(
                    OrderBookListBidData.LIST_OF_ORDER_BOOK_BID()
                )
                rvBid.adapter = tradeOrderBookBidAdapter

                val linearLayoutManagerAsk = LinearLayoutManager(requireContext())
                rvAsk.layoutManager = linearLayoutManagerAsk
                val tradeOrderBookAskAdapter = TradeOrderBookAskAdapter(
                    OrderBookListAskData.LIST_OF_ORDER_BOOK_ASK()
                )
                rvAsk.adapter = tradeOrderBookAskAdapter
            }
        } catch (error: Exception) {
            // Must be safe
        }
    }
}
