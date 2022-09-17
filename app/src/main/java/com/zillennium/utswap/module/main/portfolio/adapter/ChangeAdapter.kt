package com.zillennium.utswap.module.main.portfolio.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseViewHolder
import com.zillennium.utswap.databinding.ItemListPortfolioPerformanceBinding
import com.zillennium.utswap.databinding.ItemListPortfolioPriceBinding
import com.zillennium.utswap.models.portfolio.Portfolio
import com.zillennium.utswap.module.main.trade.tradeExchangeScreen.TradeExchangeActivity

class ChangeAdapter (var itemList:List<Portfolio.GetPortfolioDashBoard>, val onClickListener: OnClickPortfolio) :  RecyclerView.Adapter<ChangeAdapter.ItemViewHolder>(){



    inner class ItemViewHolder(root: ItemListPortfolioPerformanceBinding) : BaseViewHolder<ItemListPortfolioPerformanceBinding>(root){
        fun bidData(change: Portfolio.GetPortfolioDashBoard){
            binding.apply {
                txtTitleProject.text = change.mkt_project_name
                txtPercent.text = change.mkt_project_change.toString()

                if (change.mkt_project_change?.toDouble()!! > 0) {
                    txtPercent.text = "+" + change.mkt_project_change.toString()
                    txtPercent.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.simple_green))
                    percent.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.simple_green))
                }else if (change.mkt_project_change?.toDouble()!! < 0){
                    txtPercent.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.danger))
                    percent.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.danger))
                }else {
                    txtPercent.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.black_333333))
                    percent.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.black_333333))
                }
                layoutProject.setOnClickListener {

                    if (change.project_id == null){
                        onClickListener.ClickPortfolioProjectID(true)
                    }
                   else if (change.market_name == null){
                        onClickListener.ClickPortfolioProjectID(true)
                    }else{
                        TradeExchangeActivity.launchTradeExchangeActivityFromWishList(
                            root.context,
                            change.mkt_project_name,
                            change.market_name,change.project_id,
                            change.market_id.toString()
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
        holder.bidData(itemList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemListPortfolioPerformanceBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }
}
