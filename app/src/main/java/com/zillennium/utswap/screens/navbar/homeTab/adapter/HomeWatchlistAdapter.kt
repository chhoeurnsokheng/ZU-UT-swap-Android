package com.zillennium.utswap.screens.navbar.homeTab.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.models.HomeWatchlistModel

class HomeWatchlistAdapter(arrayList: ArrayList<HomeWatchlistModel>):
    RecyclerView.Adapter<HomeWatchlistAdapter.ViewHolder>() {

    private val listData: ArrayList<HomeWatchlistModel> = arrayList

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var txtLocationTitle: TextView = view.findViewById<View>(R.id.location_project) as TextView
        var txtLastValue: TextView = view.findViewById<View>(R.id.last_value) as TextView
        var txtChangeValue: TextView = view.findViewById<View>(R.id.change_value) as TextView
        var layoutWatchlistCard: LinearLayout = view.findViewById<View>(R.id.layout_watchlist_card) as LinearLayout
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.item_list_home_watchlist, viewGroup, false)
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val homeWatchlistList: HomeWatchlistModel = listData[position]
        holder.txtLocationTitle.text = homeWatchlistList.locationProject
        holder.txtLastValue.text = homeWatchlistList.lastValue.toString()
        if(homeWatchlistList.changeValue < 0){
            holder.txtChangeValue.text = "-" + homeWatchlistList.changeValue.toString() + "%"
            holder.txtChangeValue.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.red))
        }else {
            holder.txtChangeValue.text = "+" + homeWatchlistList.changeValue + "%"
            holder.txtChangeValue.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.success))
        }
    }

    override fun getItemCount(): Int {
        return listData.size
    }
}