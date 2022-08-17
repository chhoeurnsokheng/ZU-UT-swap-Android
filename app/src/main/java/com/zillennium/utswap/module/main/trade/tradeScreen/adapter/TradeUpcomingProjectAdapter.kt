package com.zillennium.utswap.module.main.trade.tradeScreen.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zillennium.utswap.bases.mvp.BaseRecyclerViewAdapterGeneric
import com.zillennium.utswap.bases.mvp.BaseViewHolder
import com.zillennium.utswap.databinding.ItemListTradeUpcomingProjectBinding
import com.zillennium.utswap.models.tradingList.TradingList

class TradeUpcomingProjectAdapter : BaseRecyclerViewAdapterGeneric<TradingList.TradeUpComingProjectList, TradeUpcomingProjectAdapter.ItemViewHolder>(){
    inner class ItemViewHolder(root: ItemListTradeUpcomingProjectBinding): BaseViewHolder<ItemListTradeUpcomingProjectBinding>(root)
    {
        fun bindData(tradeList: TradingList.TradeUpComingProjectList,position: Int){
            binding.apply {
                txtProject.text = tradeList.project_name

                if(position + 1 == items.size){
                    viewLine.visibility = View.GONE
                }
            }
        }
    }

    override fun onCreateItemHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    )= ItemViewHolder(ItemListTradeUpcomingProjectBinding.inflate(inflater,parent,false))

    override fun onBindItemHolder(holder: ItemViewHolder, position: Int, context: Context) {
        holder.bindData(items[position],position)
    }
}