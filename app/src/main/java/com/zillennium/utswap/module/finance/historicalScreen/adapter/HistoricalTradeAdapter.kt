package com.zillennium.utswap.module.finance.historicalScreen.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.zillennium.utswap.bases.mvp.BaseRecyclerViewAdapterGeneric
import com.zillennium.utswap.bases.mvp.BaseViewHolder
import com.zillennium.utswap.databinding.ItemListFinanceHistoricalTradeBinding
import com.zillennium.utswap.models.financeHistorical.Historical
import com.zillennium.utswap.models.financeHistorical.HistoricalTradeModel
import com.zillennium.utswap.utils.groupingSeparator

class HistoricalTradeAdapter: BaseRecyclerViewAdapterGeneric<Historical.DataTradeTransaction, HistoricalTradeAdapter.ItemViewHolder>(){

    override fun onCreateItemHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ) = ItemViewHolder(ItemListFinanceHistoricalTradeBinding.inflate(inflater, parent, false))

    override fun onBindItemHolder(
        holder: HistoricalTradeAdapter.ItemViewHolder,
        position: Int,
        context: Context
    ) {
        holder.bidData(items[position])
    }

    inner class ItemViewHolder(root: ItemListFinanceHistoricalTradeBinding) : BaseViewHolder<ItemListFinanceHistoricalTradeBinding>(root){
        fun bidData(trade: Historical.DataTradeTransaction){
            binding.apply {
                txtTitleTrade.text = trade.market
                txtUtAmountTrade.text = trade.volume + " UT"
                txtOpenAmount.text = "$" + trade.opened_price?.let { groupingSeparator(it.toDouble()) }
                txtCloseAmount.text = "$" + trade.closed_price?.let { groupingSeparator(it.toDouble()) }
                txtHighAmount.text = "$" + trade.high_value?.let { groupingSeparator(it.toDouble()) }
                txtLowAmount.text = "$" + trade.min_value?.let { groupingSeparator(it.toDouble()) }
            }
        }
    }
}