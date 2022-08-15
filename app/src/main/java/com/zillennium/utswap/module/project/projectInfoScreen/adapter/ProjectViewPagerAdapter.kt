package com.zillennium.utswap.module.project.projectInfoScreen.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.imageview.ShapeableImageView
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseRecyclerViewAdapterGeneric
import com.zillennium.utswap.bases.mvp.BaseViewHolder
import com.zillennium.utswap.databinding.ItemListProjectInfoSliderImageBinding
import com.zillennium.utswap.models.ProjectInfoSlideImageModel


class ProjectViewPagerAdapter(private var onclickAdapter: OnclickAdapter) :
    BaseRecyclerViewAdapterGeneric<String, ProjectViewPagerAdapter.ProjectViewPagerViewHolder>() {
    inner class ProjectViewPagerViewHolder(root: ItemListProjectInfoSliderImageBinding) :
        BaseViewHolder<ItemListProjectInfoSliderImageBinding>(root) {
        fun bindData(projectInfoDetailData: String) {
            println("test$projectInfoDetailData")
            binding.apply {
                Glide.with(UTSwapApp.instance)
                    .asBitmap()
                    .load(projectInfoDetailData)
                    .placeholder(R.drawable.ic_placeholder)
                    .fitCenter()
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .into(ivImage)

                itemView.setOnClickListener {
                    onclickAdapter.onClickMe(projectInfoDetailData.toString(), position, itemView)
                }

            }
        }

    }

    override fun onCreateItemHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ) = ProjectViewPagerViewHolder(
        ItemListProjectInfoSliderImageBinding.inflate(
            inflater,
            parent,
            false
        )
    )

    override fun onBindItemHolder(
        holder: ProjectViewPagerViewHolder,
        position: Int,
        context: Context
    ) {
        holder.bindData(items[position])
    }

    interface OnclickAdapter {
        fun onClickMe(
            projectInfoSlideImageModel: String,
            position: Int,
            view: View
        )
    }

}
