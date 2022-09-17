package com.zillennium.utswap.module.main.portfolio.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp

import com.zillennium.utswap.bases.mvp.BaseViewHolder
import com.zillennium.utswap.databinding.ItemListPortfolioPerformanceBinding
import com.zillennium.utswap.models.portfolio.Portfolio
import com.zillennium.utswap.module.main.trade.tradeExchangeScreen.TradeExchangeActivity

class WeightAdapter(var itemList:List<Portfolio.GetPortfolioDashBoard>, val onClickListener: OnClickPortfolio) :  RecyclerView.Adapter<WeightAdapter.ItemViewHolder>(){



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
                    if (weight.project_id == null){
                        onClickListener.ClickPortfolioProjectID(true)
                    }else if (weight.market_name ==null){
                        onClickListener.ClickPortfolioProjectID(true)
                    }else{
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemListPortfolioPerformanceBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bidData(itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}
