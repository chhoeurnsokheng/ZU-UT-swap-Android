package com.zillennium.utswap.module.main.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zillennium.utswap.databinding.ItemListHomeRecentNewsBinding
import com.zillennium.utswap.models.newsService.News


class HomeRecentNewsAdapter(private val item: List<News.NewsNew>) :
    RecyclerView.Adapter<HomeRecentNewsAdapter.HomeRecentNewsViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeRecentNewsViewHolder {
        return HomeRecentNewsViewHolder(
            ItemListHomeRecentNewsBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: HomeRecentNewsViewHolder, position: Int) {
         holder.bind(item[position])
    }

    class HomeRecentNewsViewHolder(val binding: ItemListHomeRecentNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: News.NewsNew) {
            binding.apply {
                Glide.with(imageView).load(data.img).fitCenter().into(imageView)
                titleProject.text = data.title
                newsDate.text = data.addtime

            }
        }
    }

    override fun getItemCount(): Int {
        return item.size
    }

}