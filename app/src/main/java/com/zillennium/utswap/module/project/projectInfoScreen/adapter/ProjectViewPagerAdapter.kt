package com.zillennium.utswap.module.project.projectInfoScreen.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.asksira.loopingviewpager.LoopingPagerAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseRecyclerViewAdapterGeneric
import com.zillennium.utswap.bases.mvp.BaseViewHolder
import com.zillennium.utswap.databinding.ItemListProjectInfoSliderImageBinding


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

abstract class ProjectLoopingPagerAdapter(
    itemList: List<String>,
    isInfinite: Boolean,private var onclickAdapter: ProjectViewPagerAdapter.OnclickAdapter
) : LoopingPagerAdapter<String>(itemList,isInfinite) {
    override fun inflateView(
        viewType: Int,
        container: ViewGroup,
        listPosition: Int
    ): View {
        return LayoutInflater.from(container.context)
            .inflate(R.layout.item_list_project_info_slider_image, container, false)
    }

    override fun bindView(
        convertView: View,
        listPosition: Int,
        viewType: Int
    ) {
        val imageView = convertView.findViewById<ImageView>(R.id.ivImage)
        val itemImage = itemList?.get(listPosition) as String
        Glide.with(imageView)
            .asBitmap()
            .load(itemImage)
            .apply(RequestOptions.fitCenterTransform())
            .centerCrop()
            .override(imageView.width, imageView.height)
            .into(imageView)

        imageView.setOnClickListener {
            itemImage.let { it1 -> onBannerItemClick(it1, listPosition) }
           // onclickAdapter.onClickMe(itemImage, listPosition, convertView)
        }
    }

    override fun getCount(): Int {
        return itemList?.size ?: 0
    }

    override fun getItemPosition(`object`: Any): Int {
        return (`object` as? Int) ?: 0
    }

    abstract fun onBannerItemClick(id:String, position: Int)
}