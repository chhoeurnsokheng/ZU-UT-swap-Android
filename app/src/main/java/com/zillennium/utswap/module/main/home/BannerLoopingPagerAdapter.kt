package com.zillennium.utswap.module.main.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.asksira.loopingviewpager.LoopingPagerAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions.fitCenterTransform
import com.zillennium.utswap.R
import com.zillennium.utswap.models.HomeTabSlideImageModel

/**
 * Created by Sokheng Chhoeurn on 19/7/22.
 * Build in Mac
 */


abstract class BannerLoopingPagerAdapter(
    context: Context,
    itemList: List<HomeTabSlideImageModel>,
    isInfinite: Boolean
) : LoopingPagerAdapter<HomeTabSlideImageModel>(itemList,isInfinite) {
    override fun inflateView(
        viewType: Int,
        container: ViewGroup,
        listPosition: Int
    ): View {
        return LayoutInflater.from(container.context)
            .inflate(R.layout.item_banner_home_pager, container, false)
    }

    override fun bindView(
        convertView: View,
        listPosition: Int,
        viewType: Int
    ) {
        val imageView = convertView.findViewById<ImageView>(R.id.img)
        val itemImage = itemList?.get(listPosition) as HomeTabSlideImageModel
        Glide.with(imageView)
            .asBitmap()
            .load(itemImage.imgSlider)
            .apply(fitCenterTransform())
            .centerCrop()
            .override(imageView.width, imageView.height)
            .into(imageView)


        imageView.setOnClickListener {
            onBannerItemClick(itemImage, listPosition)
        }
    }

    override fun getCount(): Int {
        return itemList?.size ?: 0
    }

    override fun getItemPosition(`object`: Any): Int {
        return (`object` as? Int) ?: 0
    }

    abstract fun onBannerItemClick(data: HomeTabSlideImageModel, position: Int)
}