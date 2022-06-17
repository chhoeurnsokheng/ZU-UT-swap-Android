package com.zillennium.utswap.screens.project.projectInfoScreen.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.models.ProjectInfoSlideImageModel

class ProjectViewPagerAdapter(
    private val arrayList: ArrayList<ProjectInfoSlideImageModel>,
    private val onclickAdapter: OnclickAdapter
) : RecyclerView.Adapter<ProjectViewPagerAdapter.ViewPagerViewHolder>() {

    inner class ViewPagerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var imageViews: ImageView = view.findViewById<View>(R.id.ivImage) as ImageView
    }

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): ProjectViewPagerAdapter.ViewPagerViewHolder {
        return ViewPagerViewHolder(
            LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.item_list_project_info_slider_image, viewGroup, false)
        )
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        val imageList: ProjectInfoSlideImageModel = arrayList[position]
        Glide.with(UTSwapApp.instance)
            .load(imageList.imageSlider)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
            .into(holder.imageViews)

        holder.itemView.setOnClickListener {
            onclickAdapter.onClickMe(imageList)
        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    interface OnclickAdapter {
        fun onClickMe(projectInfoSlideImageModel: ProjectInfoSlideImageModel)
    }
}