package com.zillennium.utswap.module.finance.historicalScreen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zillennium.utswap.bases.mvp.BaseViewHolder
import com.zillennium.utswap.databinding.ItemListFinanceHistoricalTradeDateBinding
import com.zillennium.utswap.models.financeHistorical.Historical


class HistoricalTradeDateAdapter(var item: List<Historical.TradeTransactionDate>) :
    RecyclerView.Adapter<HistoricalTradeDateAdapter.ItemViewHolder>() {


    inner class ItemViewHolder(root: ItemListFinanceHistoricalTradeDateBinding) :
        BaseViewHolder<ItemListFinanceHistoricalTradeDateBinding>(root) {
        fun bidData(trade: Historical.TradeTransactionDate) {
            binding.apply {

                txtDateTrade.text = trade.TRADE_DATE

                val historicalMyTransactionsListAdapter = HistoricalTradeListAdapter()
                historicalMyTransactionsListAdapter.items = trade.TRADE_DATE_ITEMS
                rvFinanceHistorical.adapter = historicalMyTransactionsListAdapter
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemListFinanceHistoricalTradeDateBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bidData(item[position])
    }

    override fun getItemCount(): Int {
        return item.size
    }
}
