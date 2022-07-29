package com.zillennium.utswap.module.main.news.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.zillennium.utswap.R
import com.zillennium.utswap.bases.mvp.BaseRecyclerViewAdapterGeneric
import com.zillennium.utswap.bases.mvp.BaseViewHolder
import com.zillennium.utswap.databinding.ItemListNavbarNewsBinding
import com.zillennium.utswap.models.newsService.News

class NewsAdapter(private var listener: Listener) : BaseRecyclerViewAdapterGeneric<News.NewsNew, NewsAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(root: ItemListNavbarNewsBinding): BaseViewHolder<ItemListNavbarNewsBinding>(root)
    {
        fun bindData(news: News.NewsNew){
            binding.txtTitle.text = news.title.toString()
            binding.txtDate.text = news.addtime.toString()
            Glide
                .with(binding.imgNews.context)
                .load(news.img.toString())
                .placeholder(R.drawable.ic_placeholder)
                .into(binding.imgNews)

            if (items.size == 1) {
                binding.line.visibility = View.GONE
            } else {
                when (position) {
                    items.size - 1 -> {
                        binding.line.visibility = View.GONE
                    }
                    0 -> {
                        binding.line.visibility = View.VISIBLE
                    }
                    else -> {
                        binding.line.visibility = View.VISIBLE
                    }
                }
            }

            binding.linerLayoutNews.setOnClickListener {
                listener.clickNews(news.id.toString())
            }
        }
    }

    override fun onCreateItemHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ) = ItemViewHolder(ItemListNavbarNewsBinding.inflate(inflater,parent,false))

    override fun onBindItemHolder(holder: NewsAdapter.ItemViewHolder, position: Int, context: Context) {
        holder.bindData(items[position])
    }

    interface Listener{
        fun clickNews(id: String)
    }
}