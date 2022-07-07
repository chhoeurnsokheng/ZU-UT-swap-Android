package com.zillennium.utswap.module.main.news

import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import com.zillennium.utswap.Datas.GlobalVariable.SessionVariable
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.FragmentNewsBinding
import com.zillennium.utswap.models.newsTab.News
import com.zillennium.utswap.module.account.accountScreen.AccountActivity
import com.zillennium.utswap.module.main.news.adapter.NewsAdapter
import com.zillennium.utswap.module.main.news.newsDetail.NewsDetailActivity
import com.zillennium.utswap.module.security.securityActivity.signInScreen.SignInActivity
import com.zillennium.utswap.module.system.notification.NotificationActivity


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

                //check share preference
                SessionVariable.SESSION_STATUS.observe(this@NewsFragment) {
                    if(SessionVariable.SESSION_STATUS.value == true){
                        imgMenu.setOnClickListener {
                            val intent = Intent(UTSwapApp.instance, AccountActivity::class.java)
                            startActivity(intent)
                            requireActivity().overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
                        }
                    }else{
                        imgMenu.setOnClickListener {
                            val intent = Intent(UTSwapApp.instance, SignInActivity::class.java)
                            startActivity(intent)
                        }
                    }
                }

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

                imgMenu.setOnClickListener {
                    val intent = Intent(UTSwapApp.instance, SignInActivity::class.java)
                    startActivity(intent)
                }

                imgNotification.setOnClickListener {
                    val intent = Intent(UTSwapApp.instance, NotificationActivity::class.java)
                    startActivity(intent)
                }
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
