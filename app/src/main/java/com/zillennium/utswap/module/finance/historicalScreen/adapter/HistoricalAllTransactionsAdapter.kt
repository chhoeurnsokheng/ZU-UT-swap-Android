package com.zillennium.utswap.module.finance.historicalScreen.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.zillennium.utswap.bases.mvp.BaseRecyclerViewAdapterGeneric
import com.zillennium.utswap.bases.mvp.BaseViewHolder
import com.zillennium.utswap.databinding.ItemListFinanceHistoricalAllTransactionsBinding
import com.zillennium.utswap.models.financeHistorical.Historical
import com.zillennium.utswap.models.financeHistorical.HistoricalAllTransactionsModel
import com.zillennium.utswap.utils.groupingSeparator

class HistoricalAllTransactionsAdapter : BaseRecyclerViewAdapterGeneric<Historical.DataAllTransaction, HistoricalAllTransactionsAdapter.ItemViewHolder>(){

    override fun onCreateItemHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ) = ItemViewHolder(ItemListFinanceHistoricalAllTransactionsBinding.inflate(inflater, parent, false))

    override fun onBindItemHolder(
        holder: HistoricalAllTransactionsAdapter.ItemViewHolder,
        position: Int,
        context: Context
    ) {
        holder.bidData(items[position])
    }

    inner class ItemViewHolder(root : ItemListFinanceHistoricalAllTransactionsBinding) : BaseViewHolder<ItemListFinanceHistoricalAllTransactionsBinding>(root){
        fun bidData(allTransaction : Historical.DataAllTransaction){
            binding.apply {
                txtDateAllTrans.text = allTransaction.addtime
                txtVolumeAllTrans.text = allTransaction.num
                txtPriceAllTrans.text = "$" + allTransaction.price
                txtGrossAllTrans.text = "$" + allTransaction.gross
            }
        }
    }
}