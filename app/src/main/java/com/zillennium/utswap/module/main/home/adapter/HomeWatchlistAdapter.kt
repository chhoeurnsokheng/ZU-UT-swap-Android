package com.zillennium.utswap.module.main.home.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.databinding.ItemListHomeWatchlistBinding
import com.zillennium.utswap.models.HomeWatchlistModel
import com.zillennium.utswap.models.home.BannerObj
import com.zillennium.utswap.models.newsService.News
import com.zillennium.utswap.module.main.trade.tradeExchangeScreen.TradeExchangeActivity
import com.zillennium.utswap.utils.dpToPx
//
//class HomeWatchlistAdapter(arrayList: ArrayList<HomeWatchlistModel>, onClickWatch: OnclickWatch):
//    RecyclerView.Adapter<HomeWatchlistAdapter.ViewHolder>() {
//
//    private val listData: ArrayList<HomeWatchlistModel> = arrayList
//    private val onClickWatch = onClickWatch
//
//    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//        var txtLocationTitle: TextView = view.findViewById<View>(R.id.location_project) as TextView
//        var txtLastValue: TextView = view.findViewById<View>(R.id.last_value) as TextView
//        var txtChangeValue: TextView = view.findViewById<View>(R.id.change_value) as TextView
//        var layoutWatchlistCard: CardView = view.findViewById<View>(R.id.layout_watchlist_card) as CardView
//        var layoutItem: LinearLayout = view.findViewById<View>(R.id.lay_item) as LinearLayout
//    }
//
//    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
//        return ViewHolder(
//            LayoutInflater.from(viewGroup.context)
//                .inflate(R.layout.item_list_home_watchlist, viewGroup, false)
//        )
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val homeWatchlistList: HomeWatchlistModel = listData[position]
//        holder.txtLocationTitle.text = homeWatchlistList.locationProject
//        holder.txtLastValue.text = homeWatchlistList.lastValue.toString()
//        if(homeWatchlistList.changeValue < 0){
//            holder.txtChangeValue.text = homeWatchlistList.changeValue.toString() + "%"
//            holder.txtChangeValue.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.danger))
//        }else {
//            holder.txtChangeValue.text = "+" + homeWatchlistList.changeValue + "%"
//            holder.txtChangeValue.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.success))
//        }
//
//
//
//        when(position){
//            0 -> {
//                holder.layoutItem.setPadding(dpToPx(10), 0, 0, 0)
//            }
//
//            listData.size - 1 -> {
//                holder.layoutItem.setPadding(0, 0, dpToPx(10), 0)
//            }
//        }
//
//
//        holder.layoutWatchlistCard.setOnClickListener {
//            onClickWatch.ClickWatch()
//        }
//
//    }
//
//    override fun getItemCount(): Int {
//        return listData.size
//    }
//
//    interface OnclickWatch {
//        fun ClickWatch()
//    }
//
//
//}


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
                        TradeExchangeActivity.launchTradeExchangeActivity(root.context,data.name)
                     }
                }
            }
    }
    override fun getItemCount(): Int {
       return item.size
    }
}