package com.zillennium.utswap.screens.navbar.projectTab.projectInfoScreen.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.zillennium.utswap.R

class ProjectViewPagerAdapter(private val images: List<Int>): RecyclerView.Adapter<ProjectViewPagerAdapter.ViewPagerViewHolder>() {

    inner class ViewPagerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var imageViews: ImageView = view.findViewById<View>(R.id.ivImage) as ImageView
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ProjectViewPagerAdapter.ViewPagerViewHolder {
        return ViewPagerViewHolder(
            LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.item_list_project_info_slider_image, viewGroup, false)
        )
    }

    override fun onBindViewHolder(holder: ProjectViewPagerAdapter.ViewPagerViewHolder, position: Int) {
        val imageList = images[position]
        holder.imageViews.setImageResource(imageList)
    }

    override fun getItemCount(): Int {
        return images.size
    }
}