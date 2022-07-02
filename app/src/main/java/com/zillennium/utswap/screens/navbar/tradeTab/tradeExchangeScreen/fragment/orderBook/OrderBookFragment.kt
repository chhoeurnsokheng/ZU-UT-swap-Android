package com.zillennium.utswap.screens.navbar.tradeTab.tradeExchangeScreen.fragment.orderBook

import androidx.recyclerview.widget.LinearLayoutManager
import com.zillennium.utswap.Datas.ListDatas.orderBookListAskData.OrderBookListAskData
import com.zillennium.utswap.Datas.ListDatas.orderBookListBidData.OrderBookListBidData
import com.zillennium.utswap.R
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.FragmentExchangeOrderBookBinding
import com.zillennium.utswap.screens.navbar.tradeTab.tradeExchangeScreen.fragment.orderBook.adapter.TradeOrderBookAskAdapter
import com.zillennium.utswap.screens.navbar.tradeTab.tradeExchangeScreen.fragment.orderBook.adapter.TradeOrderBookBidAdapter

class OrderBookFragment :
    BaseMvpFragment<OrderBookView.View, OrderBookView.Presenter, FragmentExchangeOrderBookBinding>(),
    OrderBookView.View {

    override var mPresenter: OrderBookView.Presenter = OrderBookPresenter()
    override val layoutResource: Int = R.layout.fragment_exchange_order_book


    override fun initView() {
        super.initView()
        try {
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
