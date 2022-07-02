package com.zillennium.utswap.screens.navbar.homeTab.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.models.HomeWatchlistModel
import com.zillennium.utswap.utils.dpToPx

class HomeWatchlistAdapter(arrayList: ArrayList<HomeWatchlistModel>, onClickWatch: OnclickWatch):
    RecyclerView.Adapter<HomeWatchlistAdapter.ViewHolder>() {

    private val listData: ArrayList<HomeWatchlistModel> = arrayList
    private val onClickWatch = onClickWatch

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var txtLocationTitle: TextView = view.findViewById<View>(R.id.location_project) as TextView
        var txtLastValue: TextView = view.findViewById<View>(R.id.last_value) as TextView
        var txtChangeValue: TextView = view.findViewById<View>(R.id.change_value) as TextView
        var layoutWatchlistCard: CardView = view.findViewById<View>(R.id.layout_watchlist_card) as CardView
        var layoutItem: LinearLayout = view.findViewById<View>(R.id.lay_item) as LinearLayout
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.item_list_home_watchlist, viewGroup, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val homeWatchlistList: HomeWatchlistModel = listData[position]
        holder.txtLocationTitle.text = homeWatchlistList.locationProject
        holder.txtLastValue.text = homeWatchlistList.lastValue.toString()
        if(homeWatchlistList.changeValue < 0){
            holder.txtChangeValue.text = homeWatchlistList.changeValue.toString() + "%"
            holder.txtChangeValue.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.danger))
        }else {
            holder.txtChangeValue.text = "+" + homeWatchlistList.changeValue + "%"
            holder.txtChangeValue.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.success))
        }



        when(position){
            0 -> {
                holder.layoutItem.setPadding(dpToPx(10), 0, 0, 0)
            }

            listData.size - 1 -> {
                holder.layoutItem.setPadding(0, 0, dpToPx(10), 0)
            }
        }


        holder.layoutWatchlistCard.setOnClickListener {
            onClickWatch.ClickWatch()
        }

    }

    override fun getItemCount(): Int {
        return listData.size
    }

    interface OnclickWatch {
        fun ClickWatch()
    }
}