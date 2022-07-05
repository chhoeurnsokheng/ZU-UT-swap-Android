package com.zillennium.utswap.module.main.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.models.HomeTabSlideImageModel

class HomeTabSlideImageAdapter(private val arrayList: ArrayList<HomeTabSlideImageModel >, private val onclickAdapter: OnclickAdapter): RecyclerView.Adapter<HomeTabSlideImageAdapter.ViewHolder>(){

    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view) {
        var imgView: ImageView = view.findViewById(R.id.iv_Image) as ImageView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_home_tap_slider_image, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val imgList: HomeTabSlideImageModel = arrayList[position]
        Glide.with(UTSwapApp.instance). load(imgList.imgSlider).diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(holder.imgView)

        holder.itemView.setOnClickListener{
            onclickAdapter.onClickSlidetab(imgList)
        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    interface OnclickAdapter {
        fun onClickSlidetab(homeTabSlideImageModel: HomeTabSlideImageModel)
    }
}



