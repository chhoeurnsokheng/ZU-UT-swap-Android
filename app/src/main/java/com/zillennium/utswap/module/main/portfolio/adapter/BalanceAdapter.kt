package com.zillennium.utswap.module.main.portfolio.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseRecyclerViewAdapterGeneric
import com.zillennium.utswap.bases.mvp.BaseViewHolder
import com.zillennium.utswap.databinding.ItemListPortfolioPerformanceBinding
import com.zillennium.utswap.databinding.ItemListPortfolioPriceBinding
import com.zillennium.utswap.models.portfolio.Portfolio
import com.zillennium.utswap.module.main.trade.tradeExchangeScreen.TradeExchangeActivity
import com.zillennium.utswap.utils.UtilKt

class BalanceAdapter(var itemList:List<Portfolio.GetPortfolioDashBoard>, val onClickListener: OnClickPortfolio) :  RecyclerView.Adapter<BalanceAdapter.ItemViewHolder>(){



    inner class ItemViewHolder(root: ItemListPortfolioPriceBinding) : BaseViewHolder<ItemListPortfolioPriceBinding>(root){
        fun bidData(balance: Portfolio.GetPortfolioDashBoard){
            binding.apply {
                txtTitleProject.text = balance.mkt_project_name
                txtBuy.text = balance.vol?.let { UtilKt().formatValue(it.toDouble(), "###,###.##") }
                txtMkt.text = balance.value?.let { UtilKt().formatValue(it, "###,###.##") }
                txtMkt.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.black_222222))

                layoutPrice.setOnClickListener {
                    if (balance.project_id == null){
                        onClickListener.ClickPortfolioProjectID(true)
                    }else if (balance.market_name ==null){
                        onClickListener.ClickPortfolioProjectID(true)
                    }else{
                        TradeExchangeActivity.launchTradeExchangeActivityFromWishList(
                            root.context,
                            balance.mkt_project_name,
                            balance.market_name,balance.project_id,
                            balance.market_id.toString()
                        )
                    }
                }



            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemListPortfolioPriceBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bidData(itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}
