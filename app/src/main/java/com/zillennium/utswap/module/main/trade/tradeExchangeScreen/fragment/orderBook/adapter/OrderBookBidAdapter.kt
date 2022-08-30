package com.zillennium.utswap.module.main.trade.tradeExchangeScreen.fragment.orderBook.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.zillennium.utswap.bases.mvp.BaseRecyclerViewAdapterGeneric
import com.zillennium.utswap.bases.mvp.BaseViewHolder
import com.zillennium.utswap.databinding.ItemListExchangeOrderBookBidBinding
import com.zillennium.utswap.models.tradingList.TradeOrderBookBid
import com.zillennium.utswap.utils.groupingSeparatorInt

class OrderBookBidAdapter: BaseRecyclerViewAdapterGeneric<TradeOrderBookBid,OrderBookBidAdapter.ItemViewHolder>() {
    inner class ItemViewHolder(root: ItemListExchangeOrderBookBidBinding): BaseViewHolder<ItemListExchangeOrderBookBidBinding>(root)
    {
        fun bindData(orderBookList: TradeOrderBookBid){
            binding.apply {
                txtBid.text = orderBookList.priceBid
                txtVolume.text = groupingSeparatorInt(orderBookList.volumeBid.toDouble())

            }
        }
    }

    override fun onCreateItemHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    )= ItemViewHolder(ItemListExchangeOrderBookBidBinding.inflate(inflater,parent,false))

    override fun onBindItemHolder(holder: ItemViewHolder, position: Int, context: Context) {
        holder.bindData(items[position])
    }
}