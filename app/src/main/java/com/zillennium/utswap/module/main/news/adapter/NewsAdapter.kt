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
import com.zillennium.utswap.models.newsTab.News


class NewsAdapter (
    arrayList: ArrayList<News>,
    onClickNews: OnClickNews
) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    private var arrayList: ArrayList<News> = ArrayList()
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
        val news: News = arrayList[position]

        holder.imgNews.setImageResource(news.image)
        holder.txtDate.text = news.txtDate
        holder.txtTitle.text = news.txtTile

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
            onClickNews.clickNews()
        }
    }

    interface OnClickNews{
        fun clickNews()
    }

    init {
        this.arrayList = arrayList
        this.onClickNews = onClickNews
    }
}