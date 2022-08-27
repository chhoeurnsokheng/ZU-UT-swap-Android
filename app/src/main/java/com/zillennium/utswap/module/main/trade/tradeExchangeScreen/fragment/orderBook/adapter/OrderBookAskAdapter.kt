package com.zillennium.utswap.module.main.trade.tradeExchangeScreen.fragment.orderBook.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.zillennium.utswap.bases.mvp.BaseRecyclerViewAdapterGeneric
import com.zillennium.utswap.bases.mvp.BaseViewHolder
import com.zillennium.utswap.databinding.ItemListExchangeOrderBookAskBinding
import com.zillennium.utswap.models.tradingList.TradeOrderBookAsk
import com.zillennium.utswap.utils.groupingSeparatorInt

class OrderBookAskAdapter:BaseRecyclerViewAdapterGeneric<TradeOrderBookAsk, OrderBookAskAdapter.ItemViewHolder>() {
    inner class ItemViewHolder(root: ItemListExchangeOrderBookAskBinding): BaseViewHolder<ItemListExchangeOrderBookAskBinding>(root)
    {
        fun bindData(orderBookList: TradeOrderBookAsk){
            binding.apply {
                txtVolumeAsk.text = groupingSeparatorInt(orderBookList.volumeAsk.toDouble())
                txtAsk.text = orderBookList.priceAsk
            }
        }
    }

    override fun onCreateItemHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    )= ItemViewHolder(ItemListExchangeOrderBookAskBinding.inflate(inflater,parent,false))


    override fun onBindItemHolder(holder: ItemViewHolder, position: Int, context: Context) {
        holder.bindData(items[position])
    }
}