package com.zillennium.utswap.module.main.portfolio.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseRecyclerViewAdapterGeneric
import com.zillennium.utswap.bases.mvp.BaseViewHolder
import com.zillennium.utswap.databinding.ItemListFinanceDepositPaymentBinding
import com.zillennium.utswap.databinding.ItemListPortfolioPriceBinding
import com.zillennium.utswap.models.portfolio.Portfolio
import com.zillennium.utswap.module.finance.depositScreen.adapter.DepositAdapter
import com.zillennium.utswap.module.main.portfolio.PortfolioFragment.MyAxisFormatter.items
import com.zillennium.utswap.module.main.trade.tradeExchangeScreen.TradeExchangeActivity
import com.zillennium.utswap.utils.UtilKt
import kotlin.math.roundToInt

class PriceAdapter(var itemList:List<Portfolio.GetPortfolioDashBoard>, val onClickListener: OnClickPortfolio) :  RecyclerView.Adapter<PriceAdapter.ItemViewHolder>(){




    inner class ItemViewHolder(root: ItemListPortfolioPriceBinding) : BaseViewHolder<ItemListPortfolioPriceBinding>(root){
        fun bind(price: Portfolio.GetPortfolioDashBoard){
            binding.apply {
                txtTitleProject.text = price.mkt_project_name
                txtBuy.text = price.mkt_project_buy_price?.let { UtilKt().formatValue(it.toDouble(), "###,###.##") }
                txtMkt.text = price.mkt_project_new_price?.let { UtilKt().formatValue(it.toDouble(), "###,###.##") }

                val  buyPrice= (price.mkt_project_buy_price?.toDouble()!! * 100.0).roundToInt() / 100.0
                val    mktPrice= (price.mkt_project_new_price?.toDouble()!! * 100.0).roundToInt() / 100.0

                if (mktPrice < buyPrice){
                    txtMkt.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.danger))
                }else if (mktPrice == buyPrice){
                    txtMkt.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.black))
                }else if (mktPrice>buyPrice){
                    txtMkt.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.success))
                }
                layoutPrice.setOnClickListener {

                    if (price.project_id == null){
                        onClickListener.ClickPortfolioProjectID(true)
                    }
                    else if (price.market_name == null){
                        onClickListener.ClickPortfolioProjectID(true)
                    }else{
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





    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemListPortfolioPriceBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }
}


interface OnClickPortfolio {
    fun ClickPortfolioProjectID(isNull:Boolean)
}