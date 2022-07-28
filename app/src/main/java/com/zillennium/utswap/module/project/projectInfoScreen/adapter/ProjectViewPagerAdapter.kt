package com.zillennium.utswap.module.project.projectInfoScreen.adapter

import android.R.attr.path
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
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
            .asBitmap()
            .load(imageList.imageSlider)
            .apply(RequestOptions().override(200, 200))
            .fitCenter()
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
            .into(holder.imageViews)

        holder.itemView.setOnClickListener {
            onclickAdapter.onClickMe(imageList, position, holder.itemView)
        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    interface OnclickAdapter {
        fun onClickMe(projectInfoSlideImageModel: ProjectInfoSlideImageModel, position: Int, view: View)
    }
}
