package com.zillennium.utswap.module.finance.historicalScreen.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.ui.unit.Constraints
import androidx.core.content.ContextCompat
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseRecyclerViewAdapterGeneric
import com.zillennium.utswap.bases.mvp.BaseViewHolder
import com.zillennium.utswap.databinding.ItemListFinanceHistoricalMyTransactionsBinding
import com.zillennium.utswap.models.financeHistorical.Historical
import com.zillennium.utswap.models.financeHistorical.HistoricalMyTransactionsModel
import com.zillennium.utswap.utils.Constants
import com.zillennium.utswap.utils.UtilKt
import com.zillennium.utswap.utils.groupingSeparator

class HistoricalMyTransactionsAdapter: BaseRecyclerViewAdapterGeneric<Historical.DataTransaction, HistoricalMyTransactionsAdapter.ItemViewHolder>(){

    override fun onCreateItemHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ) = ItemViewHolder(ItemListFinanceHistoricalMyTransactionsBinding.inflate(inflater, parent, false))

    override fun onBindItemHolder(
        holder: HistoricalMyTransactionsAdapter.ItemViewHolder,
        position: Int,
        context: Context
    ) {
        holder.bidData(items[position])
    }

    inner class ItemViewHolder(root: ItemListFinanceHistoricalMyTransactionsBinding) : BaseViewHolder<ItemListFinanceHistoricalMyTransactionsBinding>(root){
        fun bidData(myTransaction: Historical.DataTransaction){
            binding.apply {
                titleTransaction.text = myTransaction.remark
                dateTransaction.text = myTransaction.addtimeReable
                if(myTransaction.type == "buy"){
                    if(myTransaction.name.toString() == "issue"){
                        imageBalance.setImageResource(Constants.HistoricalMyTransactionIcon.SubScription)
                    }else{
                        imageBalance.setImageResource(Constants.HistoricalMyTransactionIcon.Buy)
                    }

                    amountBalance.text = "$" + myTransaction.mum_a!!.let { UtilKt().formatValue(it.toDouble(), "###,###.#") }
                    amountBalance.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.red_ee1111))
                }else if (myTransaction.type == "sell"){
                    imageBalance.setImageResource(Constants.HistoricalMyTransactionIcon.Sell)
                    amountBalance.text = "$" + myTransaction.mum_a!!.let { UtilKt().formatValue(it.toDouble(), "###,###.#") }
                    amountBalance.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.success))
                }
            }
        }
    }
}