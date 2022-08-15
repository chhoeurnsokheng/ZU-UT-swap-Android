package com.zillennium.utswap.module.finance.historicalScreen.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.zillennium.utswap.bases.mvp.BaseRecyclerViewAdapterGeneric
import com.zillennium.utswap.bases.mvp.BaseViewHolder
import com.zillennium.utswap.databinding.ItemListFinanceHistoricalTradeListBinding
import com.zillennium.utswap.models.financeHistorical.Historical
import com.zillennium.utswap.utils.UtilKt
import com.zillennium.utswap.utils.groupingSeparator
import com.zillennium.utswap.utils.groupingSeparatorInt

class HistoricalTradeListAdapter: BaseRecyclerViewAdapterGeneric<Historical.DataTradeDateTransaction, HistoricalTradeListAdapter.ItemViewHolder>(){

    override fun onCreateItemHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ) = ItemViewHolder(ItemListFinanceHistoricalTradeListBinding.inflate(inflater, parent, false))

    override fun onBindItemHolder(
        holder: HistoricalTradeListAdapter.ItemViewHolder,
        position: Int,
        context: Context
    ) {
        holder.bidData(items[position])
    }

    inner class ItemViewHolder(root: ItemListFinanceHistoricalTradeListBinding) : BaseViewHolder<ItemListFinanceHistoricalTradeListBinding>(root){
        fun bidData(trade: Historical.DataTradeDateTransaction){
            binding.apply {
                txtTitleTrade.text = trade.market
                txtUtAmountTrade.text = trade.volume?.let { groupingSeparatorInt(it.toInt()) } + " UT"
                txtOpenAmount.text = "$" + trade.opened_price?.let { UtilKt().formatValue(it.toDouble(), "###,###.#") }
                txtCloseAmount.text = "$" + trade.closed_price?.let { UtilKt().formatValue(it.toDouble(), "###,###.#") }
                txtHighAmount.text = "$" + trade.high_value?.let { UtilKt().formatValue(it.toDouble(), "###,###.#") }
                txtLowAmount.text = "$" + trade.min_value?.let { UtilKt().formatValue(it.toDouble(), "###,###.#") }
            }
        }
    }
}