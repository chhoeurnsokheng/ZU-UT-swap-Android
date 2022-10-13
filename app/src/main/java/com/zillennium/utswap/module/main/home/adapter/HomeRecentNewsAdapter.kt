package com.zillennium.utswap.module.main.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zillennium.utswap.R
import com.zillennium.utswap.databinding.ItemListNavbarNewsBinding
import com.zillennium.utswap.models.newsService.News
import com.zillennium.utswap.module.main.news.newsDetail.NewsDetailActivity


class HomeRecentNewsAdapter(private val item: List<News.NewsNew>) :
    RecyclerView.Adapter<HomeRecentNewsAdapter.HomeRecentNewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeRecentNewsViewHolder {
        return HomeRecentNewsViewHolder(
            ItemListNavbarNewsBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: HomeRecentNewsViewHolder, position: Int) {
         holder.bind(item[position])
    }

    class HomeRecentNewsViewHolder(val binding: ItemListNavbarNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: News.NewsNew) {
            binding.apply {
                Glide.with(imgNews)
                    .load(data.img)
                    .placeholder(R.drawable.ic_placeholder)
                    .fitCenter()
                    .into(imgNews)
                txtTitle.text = data.title
                txtDate.text = data.addtime
                linerLayoutNews.setOnClickListener {
                    NewsDetailActivity.launchNewsDetailsActivity(root.context,data.id)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return item.size
    }

}