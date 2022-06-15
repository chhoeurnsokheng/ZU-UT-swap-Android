package com.zillennium.utswap.screens.navbar.newsTab

import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.FragmentNewsTabBinding
import com.zillennium.utswap.models.ProjectModel
import com.zillennium.utswap.models.newsTab.News
import com.zillennium.utswap.screens.navbar.newsTab.adapter.NewsAdapter
import com.zillennium.utswap.screens.navbar.newsTab.newsDetail.NewsDetailActivity
import com.zillennium.utswap.screens.navbar.projectTab.projectInfoScreen.ProjectInfoActivity
import com.zillennium.utswap.screens.navbar.projectTab.projectScreen.adapter.ProjectAdapter

class NewsTabFragment :
    BaseMvpFragment<NewsTabView.View, NewsTabView.Presenter, FragmentNewsTabBinding>(),
    NewsTabView.View {

    override var mPresenter: NewsTabView.Presenter = NewsTabPresenter()
    override val layoutResource: Int = R.layout.fragment_news_tab

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
