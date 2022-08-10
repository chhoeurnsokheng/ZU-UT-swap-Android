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
import com.zillennium.utswap.models.home.BannerObj

/**
 * Created by Sokheng Chhoeurn on 19/7/22.
 * Build in Mac
 */


abstract class BannerLoopingPagerAdapter(
    itemList: List<BannerObj.ItemsBanner>,
    isInfinite: Boolean
) : LoopingPagerAdapter<BannerObj.ItemsBanner>(itemList,isInfinite) {
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
        val itemImage = itemList?.get(listPosition) as BannerObj.ItemsBanner
        Glide.with(imageView)
            .asBitmap()
            .load(itemImage.banner)
            .apply(fitCenterTransform())
            .centerCrop()
            .override(imageView.width, imageView.height)
            .into(imageView)

        imageView.setOnClickListener {
            itemImage.linkable_id?.let { it1 -> onBannerItemClick(it1, listPosition) }
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