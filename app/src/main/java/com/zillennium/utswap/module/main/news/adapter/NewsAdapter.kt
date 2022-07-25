package com.zillennium.utswap.module.main.news.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zillennium.utswap.R
import com.zillennium.utswap.models.newsService.News


class NewsAdapter (
    arrayList: List<News.NewsData>,
    onClickNews: OnClickNews
) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    private var arrayList: List<News.NewsData> = ArrayList()
    private var onClickNews: OnClickNews
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_navbar_news, parent, false)
        return ViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal val imgNews: ImageView = itemView.findViewById(R.id.img_news)
        internal val line: View = itemView.findViewById(R.id.line)
        internal val txtTitle: TextView = itemView.findViewById(R.id.txt_title)
        internal val txtDate: TextView = itemView.findViewById(R.id.txt_date)
        internal val linearLayoutNews: LinearLayout = itemView.findViewById(R.id.linerLayout_news)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val news: News.NewsData = arrayList[position]

        holder.txtDate.text = news.addtime.toString()
        holder.txtTitle.text = news.title.toString()

        if (arrayList.size == 1) {
            holder.line.visibility = View.GONE
        } else {
            when (position) {
                arrayList.size - 1 -> {
                    holder.line.visibility = View.GONE
                }
                0 -> {
                    holder.line.visibility = View.VISIBLE
                }
                else -> {
                    holder.line.visibility = View.VISIBLE
                }
            }
        }

        holder.linearLayoutNews.setOnClickListener {
            onClickNews.clickNews(news.id.toString())
        }
    }

    interface OnClickNews{
        fun clickNews(id: String)
    }

    init {
        this.arrayList = arrayList
        this.onClickNews = onClickNews
    }
}