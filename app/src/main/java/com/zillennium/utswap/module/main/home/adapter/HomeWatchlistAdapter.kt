package com.zillennium.utswap.module.main.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.zillennium.utswap.R
import com.zillennium.utswap.databinding.ItemListHomeWatchlistBinding
import com.zillennium.utswap.models.home.BannerObj
import com.zillennium.utswap.module.main.trade.tradeExchangeScreen.TradeExchangeActivity


class HomeWatchlistAdapter(private val item:List<BannerObj.ItemWishList>): RecyclerView.Adapter<HomeWatchlistAdapter.WishListViewHolder>(){


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WishListViewHolder {
       return WishListViewHolder(ItemListHomeWatchlistBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: WishListViewHolder, position: Int) {
        holder.bind(item[position])
    }
    class WishListViewHolder(val binding:ItemListHomeWatchlistBinding):RecyclerView.ViewHolder(binding.root) {
            fun bind(data:BannerObj.ItemWishList){
                binding.apply {
                    locationProject.text = data.name

                     val changeValue = data.change?.toDouble()

                    if (changeValue != null) {
                        if (changeValue>=0){
                            txtChangeValue.text = "+" + data.change + "%"
                            txtChangeValue.setTextColor(ContextCompat.getColor(root.context, R.color.simple_green))
                        }
                        if (changeValue ==0.0){
                            txtChangeValue.text = "0.00%"
                            txtChangeValue.setTextColor(ContextCompat.getColor(root.context, R.color.simple_green))
                        }
                        if (changeValue<0){
                            txtChangeValue.text = data.change + "%"
                            txtChangeValue.setTextColor(ContextCompat.getColor(root.context, R.color.red_ee1111))
                        }
                    }

                    txtLastValue.text =  data.Last.toString()

                     layItem.setOnClickListener {

                         TradeExchangeActivity.launchTradeExchangeActivityFromWishList(
                             root.context,
                             data.name,
                             data.coinname+"_"+data.buycoin,
                             data.id
                         )
                     }
                }
            }
    }
    override fun getItemCount(): Int {
       return item.size
    }
}