package com.zillennium.utswap.screens.navbar.homeTab.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.models.HomeRecentNewsModel

class HomeRecentNewsAdapter(arrayList: ArrayList<HomeRecentNewsModel>):
    RecyclerView.Adapter<HomeRecentNewsAdapter.ViewHolder>() {

    val listData: ArrayList<HomeRecentNewsModel> = arrayList

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var imageImageView: ImageView = view.findViewById<View>(R.id.image_view) as ImageView
        var txtTitleProject: TextView = view.findViewById<View>(R.id.title_project) as TextView
        var txtNewDate: TextView = view.findViewById<View>(R.id.news_date) as TextView
        var layoutRecentNewsCard: LinearLayout = view.findViewById<View>(R.id.linear_news_card) as LinearLayout
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.item_list_home_recent_news, viewGroup, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val homeRecentNewsList: HomeRecentNewsModel = listData[position]
        Glide.with(UTSwapApp.instance)
            .load(homeRecentNewsList.imageLocation)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
            .into(holder.imageImageView)
        holder.txtTitleProject.text = homeRecentNewsList.titleNews
        holder.txtNewDate.text = homeRecentNewsList.dateNews
    }

    override fun getItemCount(): Int {
        val limit = 3
        return Math.min(listData.size, limit)
    }
}