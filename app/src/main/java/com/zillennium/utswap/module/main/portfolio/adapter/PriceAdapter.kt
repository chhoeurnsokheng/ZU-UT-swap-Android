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
import com.zillennium.utswap.module.main.trade.tradeExchangeScreen.TradeExchangeActivity
import com.zillennium.utswap.utils.UtilKt
import kotlin.math.roundToInt

class PriceAdapter : BaseRecyclerViewAdapterGeneric<Portfolio.GetPortfolioDashBoard, PriceAdapter.ItemViewHolder>(){

    override fun onCreateItemHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ) = ItemViewHolder(ItemListPortfolioPriceBinding.inflate(inflater, parent, false))

    override fun onBindItemHolder(
        holder: PriceAdapter.ItemViewHolder,
        position: Int,
        context: Context
    ) {
        holder.bidData(items[position])
    }

    inner class ItemViewHolder(root: ItemListPortfolioPriceBinding) : BaseViewHolder<ItemListPortfolioPriceBinding>(root){
        fun bidData(price: Portfolio.GetPortfolioDashBoard){
            binding.apply {
                txtTitleProject.text = price.mkt_project_name
                txtBuy.text = price.mkt_project_buy_price?.let { UtilKt().formatValue(it.toDouble(), "###,###.##") }
                txtMkt.text = price.mkt_project_mkt_price?.let { UtilKt().formatValue(it.toDouble(), "###,###.##") }

                val mktPrice = (price.mkt_project_mkt_price?.toDouble()!! * 100.0).roundToInt() / 100.0
                val buyPrice = (price.mkt_project_buy_price?.toDouble()!! * 100.0).roundToInt() / 100.0

                if (mktPrice < buyPrice){
                    txtMkt.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.danger))
                }else if (mktPrice == buyPrice){
                    txtMkt.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.primary))
                }
                layoutPrice.setOnClickListener {
                    TradeExchangeActivity.launchTradeExchangeActivityFromWishList(
                        root.context,
                        price.mkt_project_name,
                        price.market_name,price.project_id,
                        price.market_id.toString()
                    )
                }


            }
        }
    }
}
