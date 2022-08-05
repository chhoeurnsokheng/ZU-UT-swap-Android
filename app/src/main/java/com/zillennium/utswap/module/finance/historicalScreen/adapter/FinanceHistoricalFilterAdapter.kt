package com.zillennium.utswap.module.finance.historicalScreen.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zillennium.utswap.Datas.GlobalVariable.SettingVariable
import com.zillennium.utswap.bases.mvp.BaseRecyclerViewAdapterGeneric
import com.zillennium.utswap.bases.mvp.BaseViewHolder
import com.zillennium.utswap.databinding.ItemListFinanceHistoricalFilterBinding
import com.zillennium.utswap.models.financeHistorical.Historical

class FinanceHistoricalFilterAdapter(private var onClickFilterHistory: OnClickFilterHistory): BaseRecyclerViewAdapterGeneric<Historical.GetMarketNameData, FinanceHistoricalFilterAdapter.ItemViewHolder>(){

    override fun onCreateItemHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ) = ItemViewHolder(ItemListFinanceHistoricalFilterBinding.inflate(inflater, parent, false))

    override fun onBindItemHolder(
        holder: FinanceHistoricalFilterAdapter.ItemViewHolder,
        position: Int,
        context: Context
    ) {
        holder.bidData(items[position])
    }

    inner class ItemViewHolder(root: ItemListFinanceHistoricalFilterBinding): BaseViewHolder<ItemListFinanceHistoricalFilterBinding>(root)
    {
        fun bidData(history: Historical.GetMarketNameData){
            binding.apply {
                titleFilter.text = history.name
                if (items.size == 1) {
                    line.visibility = View.GONE
                } else {
                    when (position) {
                        items.size - 1 -> {
                            line.visibility = View.GONE
                        }
                        0 -> {
                            line.visibility = View.VISIBLE
                        }
                        else -> {
                            line.visibility = View.VISIBLE
                        }
                    }
                }

                if(SettingVariable.finance_historical_filter.value == history.name){
                    layIconCheck.visibility = View.VISIBLE
                }

                layoutFilter.setOnClickListener {
                    onClickFilterHistory.onClickMe(history)
                }
            }
        }
    }

    interface OnClickFilterHistory {
        fun onClickMe(financeHistoricalFilterModel: Historical.GetMarketNameData)
    }
}