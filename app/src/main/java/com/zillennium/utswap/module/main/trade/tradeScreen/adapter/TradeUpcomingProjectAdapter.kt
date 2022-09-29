package com.zillennium.utswap.module.main.trade.tradeScreen.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zillennium.utswap.bases.mvp.BaseRecyclerViewAdapterGeneric
import com.zillennium.utswap.bases.mvp.BaseViewHolder
import com.zillennium.utswap.databinding.ItemListTradeUpcomingProjectBinding
import com.zillennium.utswap.models.TradeModel
import com.zillennium.utswap.models.tradingList.TradingList
import com.zillennium.utswap.module.project.projectInfoScreen.ProjectInfoActivity
import com.zillennium.utswap.module.project.subscriptionScreen.SubscriptionActivity

class TradeUpcomingProjectAdapter() : BaseRecyclerViewAdapterGeneric<TradingList.TradeUpComingProjectList, TradeUpcomingProjectAdapter.ItemViewHolder>(){
    inner class ItemViewHolder(root: ItemListTradeUpcomingProjectBinding): BaseViewHolder<ItemListTradeUpcomingProjectBinding>(root)
    {
        fun bindData(tradeList: TradingList.TradeUpComingProjectList,position: Int){
            binding.apply {
                txtProject.text = tradeList.project_name

                txtDetail.setOnClickListener {
                    ProjectInfoActivity.launchProjectInfoActivity(
                        root.context,
                        tradeList.id,
                    )
                }

                txtSubscribe.setOnClickListener {
                    SubscriptionActivity.launchSubscriptionActivity(root.context, tradeList.id, tradeList.project_name)
//                    ProjectInfoActivity.launchProjectInfoActivity(
//                        root.context,
//                        tradeList.id,
//                    )
                }

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

    interface Listener{
        fun clickMe(tradeList: TradingList.TradeUpComingProjectList)
    }
}