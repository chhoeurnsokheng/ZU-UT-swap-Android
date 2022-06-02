package com.zillennium.utswap.screens.navbar.homeTab.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.models.HomeMenuModel

class HomeMenuAdapter(arrayList: ArrayList<HomeMenuModel>, itemListHomeGrid: Int) :
    RecyclerView.Adapter<HomeMenuAdapter.ViewHolder>() {
    private val listdata: ArrayList<HomeMenuModel> = arrayList

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var txtTitle: TextView = view.findViewById<View>(R.id.title_home) as TextView
        var ivImage: ImageView = view.findViewById<View>(R.id.image_homeview) as ImageView
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(viewGroup.context).inflate(R.layout.item_list_home_grid, viewGroup, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val homeInfoDetailList: HomeMenuModel = listdata[position]
        holder.txtTitle.text = homeInfoDetailList.titleHome
        holder.ivImage.setImageResource(homeInfoDetailList.imageHome)
    }

    override fun getItemCount(): Int {
        return listdata.size
    }
}