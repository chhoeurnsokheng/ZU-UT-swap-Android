package com.zillennium.utswap.screens.navbar.newsTab

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.zillennium.utswap.Datas.GlobalVariable.SessionVariable
import com.zillennium.utswap.Datas.StoredPreferences.SessionPreferences
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.FragmentNavbarNewsBinding
import com.zillennium.utswap.models.newsTab.News
import com.zillennium.utswap.screens.account.accountScreen.AccountActivity
import com.zillennium.utswap.screens.navbar.newsTab.adapter.NewsAdapter
import com.zillennium.utswap.screens.navbar.newsTab.newsDetail.NewsDetailActivity
import com.zillennium.utswap.screens.security.securityActivity.signInScreen.SignInActivity
import com.zillennium.utswap.screens.system.notification.NotificationActivity

class NewsTabFragment :
    BaseMvpFragment<NewsTabView.View, NewsTabView.Presenter, FragmentNavbarNewsBinding>(),
    NewsTabView.View {

    override var mPresenter: NewsTabView.Presenter = NewsTabPresenter()
    override val layoutResource: Int = R.layout.fragment_navbar_news

    private var newsList = ArrayList<News>()
    private var newsAdapter: NewsAdapter? = null

    override fun initView() {
        super.initView()
        try {
            binding.apply {

                //check share preference
                SessionVariable.SESSION_STATUS.observe(this@NewsTabFragment) {
                    if(SessionVariable.SESSION_STATUS.value == true){
                        imgMenu.setOnClickListener {
                            val intent = Intent(UTSwapApp.instance, AccountActivity::class.java)
                            startActivity(intent)
                            requireActivity().overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
                        }
                    }else{
                        imgMenu.setOnClickListener {
                            val intent = Intent(UTSwapApp.instance,SignInActivity::class.java)
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
