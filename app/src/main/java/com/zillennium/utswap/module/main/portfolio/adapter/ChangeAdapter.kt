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

class ChangeAdapter: BaseRecyclerViewAdapterGeneric<Portfolio.GetPortfolioDashBoard, ChangeAdapter.ItemViewHolder>(){

    override fun onCreateItemHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ) = ItemViewHolder(ItemListPortfolioPerformanceBinding.inflate(inflater, parent, false))

    override fun onBindItemHolder(
        holder: ChangeAdapter.ItemViewHolder,
        position: Int,
        context: Context
    ) {
        holder.bidData(items[position])
    }

    inner class ItemViewHolder(root: ItemListPortfolioPerformanceBinding) : BaseViewHolder<ItemListPortfolioPerformanceBinding>(root){
        fun bidData(change: Portfolio.GetPortfolioDashBoard){
            binding.apply {
                txtTitleProject.text = change.mkt_project_name
                txtPercent.text = change.mkt_project_change.toString()

                /*if (change.mkt_project_change?.toDouble()!! > 0) {
                    txtPercent.text = "+" + change.mkt_project_change.toString()
                    txtPercent.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.simple_green))
                    percent.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.simple_green))
                }else if (change.mkt_project_change?.toDouble()!! < 0){
                    txtPercent.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.danger))
                    percent.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.danger))
                }else {
                    txtPercent.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.black_333333))
                    percent.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.black_333333))
                }*/

            }
        }
    }
}
