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
import com.zillennium.utswap.databinding.ItemListPortfolioPriceBinding
import com.zillennium.utswap.models.portfolio.Portfolio
import com.zillennium.utswap.utils.UtilKt

class BalanceAdapter: BaseRecyclerViewAdapterGeneric<Portfolio.GetPortfolioDashBoard, BalanceAdapter.ItemViewHolder>(){

    override fun onCreateItemHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ) = ItemViewHolder(ItemListPortfolioPriceBinding.inflate(inflater, parent, false))

    override fun onBindItemHolder(
        holder: BalanceAdapter.ItemViewHolder,
        position: Int,
        context: Context
    ) {
        holder.bidData(items[position])
    }

    inner class ItemViewHolder(root: ItemListPortfolioPriceBinding) : BaseViewHolder<ItemListPortfolioPriceBinding>(root){
        fun bidData(balance: Portfolio.GetPortfolioDashBoard){
            binding.apply {
                txtTitleProject.text = balance.mkt_project_name
                txtBuy.text = balance.vol?.let { UtilKt().formatValue(it.toDouble(), "###,###.##") }
                txtMkt.text = balance.value?.let { UtilKt().formatValue(it, "###,###.##") }
                txtMkt.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.black_222222))

            }
        }
    }
}
