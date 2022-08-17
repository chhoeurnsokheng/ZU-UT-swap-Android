package com.zillennium.utswap.module.finance.historicalScreen.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.zillennium.utswap.bases.mvp.BaseRecyclerViewAdapterGeneric
import com.zillennium.utswap.bases.mvp.BaseViewHolder
import com.zillennium.utswap.databinding.ItemListFinanceHistoricalTradeDateBinding
import com.zillennium.utswap.models.financeHistorical.Historical


class HistoricalTradeDateAdapter: BaseRecyclerViewAdapterGeneric<Historical.TradeTransactionDate, HistoricalTradeDateAdapter.ItemViewHolder>() {
    override fun onCreateItemHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ) = ItemViewHolder(ItemListFinanceHistoricalTradeDateBinding.inflate(inflater, parent, false))

    override fun onBindItemHolder(
        holder: HistoricalTradeDateAdapter.ItemViewHolder,
        position: Int,
        context: Context
    ) {
        holder.bidData(items[position])
    }

    inner class ItemViewHolder(root: ItemListFinanceHistoricalTradeDateBinding) : BaseViewHolder<ItemListFinanceHistoricalTradeDateBinding>(root){
        fun bidData(trade: Historical.TradeTransactionDate){
            binding.apply {

                txtDateTrade.text = trade.TRADE_DATE

                val historicalMyTransactionsListAdapter = HistoricalTradeListAdapter()
                historicalMyTransactionsListAdapter.items = trade.TRADE_DATE_ITEMS
                rvFinanceHistorical.adapter = historicalMyTransactionsListAdapter
            }
        }
    }
}
