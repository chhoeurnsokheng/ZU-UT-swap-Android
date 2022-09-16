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

class WeightAdapter: BaseRecyclerViewAdapterGeneric<Portfolio.GetPortfolioDashBoard, WeightAdapter.ItemViewHolder>(){

    override fun onCreateItemHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ) = ItemViewHolder(ItemListPortfolioPerformanceBinding.inflate(inflater, parent, false))

    override fun onBindItemHolder(
        holder: WeightAdapter.ItemViewHolder,
        position: Int,
        context: Context
    ) {
        holder.bidData(items[position])
    }

    inner class ItemViewHolder(root: ItemListPortfolioPerformanceBinding) : BaseViewHolder<ItemListPortfolioPerformanceBinding>(root){
        fun bidData(weight: Portfolio.GetPortfolioDashBoard){
            binding.apply {
                txtTitleProject.text = weight.mkt_project_name
                txtPercent.text = weight.weight
                txtPercent.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.black_222222))
                percent.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.black_222222))
                if (weight.weight?.toDouble() ==0.0){
                    txtPercent.text = "--"
                    percent.visibility =View.INVISIBLE
                    txtPercent.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.black_222222))
                }
                layoutProject.setOnClickListener {
                    TradeExchangeActivity.launchTradeExchangeActivityFromWishList(
                        root.context,
                        weight.mkt_project_name,
                        weight.market_name,weight.project_id,
                        weight.market_id.toString()
                    )
                }

            }
        }
    }
}
