package com.zillennium.utswap.module.main.portfolio.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseRecyclerViewAdapterGeneric
import com.zillennium.utswap.bases.mvp.BaseViewHolder
import com.zillennium.utswap.databinding.ItemListPortfolioPerformanceBinding
import com.zillennium.utswap.models.portfolio.Portfolio
import com.zillennium.utswap.module.main.trade.tradeExchangeScreen.TradeExchangeActivity

class PerformanceAdapter: BaseRecyclerViewAdapterGeneric<Portfolio.GetPortfolioDashBoard, PerformanceAdapter.ItemViewHolder>(){

    override fun onCreateItemHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ) = ItemViewHolder(ItemListPortfolioPerformanceBinding.inflate(inflater, parent, false))

    override fun onBindItemHolder(
        holder: PerformanceAdapter.ItemViewHolder,
        position: Int,
        context: Context
    ) {
        holder.bidData(items[position])
    }

    inner class ItemViewHolder(root: ItemListPortfolioPerformanceBinding) : BaseViewHolder<ItemListPortfolioPerformanceBinding>(root){
        fun bidData(performance: Portfolio.GetPortfolioDashBoard){
            binding.apply {
                txtTitleProject.text = performance.mkt_project_name
                txtPercent.text = performance.mkt_project_perf.toString()

                if (performance.mkt_project_perf?.toDouble()!! > 0) {
                    txtPercent.text = "+" + performance.mkt_project_perf
                    txtPercent.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.simple_green))
                    percent.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.simple_green))
                }else if (performance.mkt_project_perf?.toDouble()!! < 0){
                    txtPercent.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.danger))
                    percent.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.danger))
                }else if (performance.mkt_project_perf?.toDouble() ==0.0){
                    txtPercent.text ="_"
                    percent.visibility = View.INVISIBLE
                    txtPercent.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.black))
                }
                else {
                    txtPercent.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.black_333333))
                    percent.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.black_333333))
                }
                layoutProject.setOnClickListener {
                    TradeExchangeActivity.launchTradeExchangeActivityFromWishList(
                        root.context,
                        performance.mkt_project_name,
                        performance.market_name,performance.project_id,
                        performance.market_id.toString()
                    )
                }

            }
        }
    }
}
