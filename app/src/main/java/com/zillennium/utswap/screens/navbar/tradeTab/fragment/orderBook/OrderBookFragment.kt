package com.zillennium.utswap.screens.navbar.tradeTab.fragment.orderBook

import androidx.recyclerview.widget.LinearLayoutManager
import com.zillennium.utswap.Datas.ListDatas.balanceData.DataBalanceHistory
import com.zillennium.utswap.Datas.ListDatas.orderBookListAskData.OrderBookListAskData
import com.zillennium.utswap.Datas.ListDatas.orderBookListBidData.OrderBookListBidData
import com.zillennium.utswap.R
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.FragmentOrderBookBinding
import com.zillennium.utswap.screens.navbar.tradeTab.fragment.orderBook.adapter.TradeOrderBookAskAdapter
import com.zillennium.utswap.screens.navbar.tradeTab.fragment.orderBook.adapter.TradeOrderBookBidAdapter
import com.zillennium.utswap.screens.setting.balanceScreen.adapter.BalanceHistoryAdapter

class OrderBookFragment :
    BaseMvpFragment<OrderBookView.View, OrderBookView.Presenter, FragmentOrderBookBinding>(),
    OrderBookView.View {

    override var mPresenter: OrderBookView.Presenter = OrderBookPresenter()
    override val layoutResource: Int = R.layout.fragment_order_book


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
