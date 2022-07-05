package com.zillennium.utswap.module.main.news

import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.FragmentNewsBinding
import com.zillennium.utswap.models.newsTab.News
import com.zillennium.utswap.module.main.news.adapter.NewsAdapter
import com.zillennium.utswap.module.main.news.newsDetail.NewsDetailActivity

class NewsFragment :
    BaseMvpFragment<NewsView.View, NewsView.Presenter, FragmentNewsBinding>(),
    NewsView.View {

    override var mPresenter: NewsView.Presenter = NewsPresenter()
    override val layoutResource: Int = R.layout.fragment_news

    private var newsList = ArrayList<News>()
    private var newsAdapter: NewsAdapter? = null

    override fun initView() {
        super.initView()
        try {
            binding.apply {

                newsList.add(News(R.drawable.image_one,"11 April 2022","Laoka, Mondulkiri Land Plot Special Price"))
                newsList.add(News(R.drawable.image_one,"11 April 2022","Laoka, Mondulkiri Land Plot Special Price"))
                newsList.add(News(R.drawable.image_one,"11 April 2022","Laoka, Mondulkiri Land Plot Special Price"))
                newsList.add(News(R.drawable.image_one,"11 April 2022","Laoka, Mondulkiri Land Plot Special Price"))
                newsList.add(News(R.drawable.image_one,"11 April 2022","Laoka, Mondulkiri Land Plot Special Price"))
                newsList.add(News(R.drawable.image_one,"11 April 2022","Laoka, Mondulkiri Land Plot Special Price"))
                newsList.add(News(R.drawable.image_one,"11 April 2022","Laoka, Mondulkiri Land Plot Special Price"))
                newsList.add(News(R.drawable.image_one,"11 April 2022","Laoka, Mondulkiri Land Plot Special Price"))
                newsList.add(News(R.drawable.image_one,"11 April 2022","Laoka, Mondulkiri Land Plot Special Price"))
                newsList.add(News(R.drawable.image_one,"11 April 2022","Laoka, Mondulkiri Land Plot Special Price"))
                newsList.add(News(R.drawable.image_one,"11 April 2022","Laoka, Mondulkiri Land Plot Special Price"))
                newsList.add(News(R.drawable.image_one,"11 April 2022","Laoka, Mondulkiri Land Plot Special Price"))

                val linearLayoutManager = LinearLayoutManager(requireContext())
                rvNews.layoutManager = linearLayoutManager
                newsAdapter = NewsAdapter(newsList,onClickNews)

                rvNews.adapter = newsAdapter
            }
        } catch (error: Exception) {
            // Must be safe
        }
    }

    private val onClickNews: NewsAdapter.OnClickNews = object :
        NewsAdapter.OnClickNews {
        override fun clickNews() {
            val intent = Intent(UTSwapApp.instance, NewsDetailActivity::class.java)
            startActivity(intent)
        }
    }
}
