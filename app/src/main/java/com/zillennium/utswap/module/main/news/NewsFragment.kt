package com.zillennium.utswap.module.main.news

import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidstudy.networkmanager.Tovuti
import com.zillennium.utswap.Datas.GlobalVariable.SessionVariable
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.FragmentNewsBinding
import com.zillennium.utswap.models.HomeMenuModel
import com.zillennium.utswap.models.newsService.News
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

    private var newsAdapter: NewsAdapter? = null
    private var listNews =  ArrayList<News.NewsData>()
    private var totalPage: Int?  = 3
    private var page: Int? = 1

    override fun initView() {
        super.initView()

        onCheckPreference()

        onCallApi()

        onOrderActivity()
    }

    private fun onCallApi(){
        Tovuti.from(UTSwapApp.instance).monitor{ _, isConnected, _ ->
            if(isConnected)
            {
                mPresenter.onGetNews(UTSwapApp.instance)
                binding.progressBar.visibility = View.VISIBLE
            }
        }
    }

    override fun onGetNewsSuccess(data: News.NewsRes) {
        binding.apply {

            page = page!! + 1

            val linearLayoutManager = LinearLayoutManager(requireContext())
            rvNews.layoutManager = linearLayoutManager

            listNews.addAll(data.data!!)

            newsAdapter = NewsAdapter(listNews as ArrayList,onClickNews)

            rvNews.adapter = newsAdapter

            progressBar.visibility = View.GONE
            progressBarReadMore.visibility = View.GONE
        }
    }

    override fun onGetNewsFail(data: News.NewsRes) {
        binding.apply {
            progressBar.visibility = View.GONE
        }
    }

    private fun onCheckPreference(){
        binding.apply {
            SessionVariable.SESSION_STATUS.observe(this@NewsFragment) {
                if(SessionVariable.SESSION_STATUS.value == true){
                    imgMenu.setOnClickListener {
                        val intent = Intent(UTSwapApp.instance, AccountActivity::class.java)
                        startActivity(intent)
                        requireActivity().overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
                    }
                    imgNotification.setOnClickListener {
                        val intent = Intent(UTSwapApp.instance, NotificationActivity::class.java)
                        startActivity(intent)
                    }
                    txtCountNotification.visibility =View.VISIBLE
                }else{
                    imgMenu.setOnClickListener {
                        val intent = Intent(UTSwapApp.instance, SignInActivity::class.java)
                        startActivity(intent)
                    }
                    imgNotification.setOnClickListener {
                        val intent = Intent(UTSwapApp.instance, SignInActivity::class.java)
                        startActivity(intent)
                    }
                    txtCountNotification.visibility = View.GONE
                }
            }
        }
    }

    private fun onOrderActivity(){
        binding.apply {
            imgMenu.setOnClickListener {
                val intent = Intent(UTSwapApp.instance, SignInActivity::class.java)
                startActivity(intent)
            }

            imgNotification.setOnClickListener {
                val intent = Intent(UTSwapApp.instance, NotificationActivity::class.java)
                startActivity(intent)
            }

            readMore.setOnClickListener {
                try {
                   if(page!! <= totalPage!!){
                       mPresenter.onGetNews(UTSwapApp.instance)
                       progressBarReadMore.visibility = View.VISIBLE
                   }else{
                       readMore.visibility = View.INVISIBLE
                       progressBarReadMore.visibility = View.GONE
                       Toast.makeText(UTSwapApp.instance, "No more content!!", Toast.LENGTH_LONG).show()
                   }
                }catch (e: Exception){}

            }
        }
    }

    private val onClickNews: NewsAdapter.OnClickNews = object :
        NewsAdapter.OnClickNews {
        override fun clickNews(id: String) {
            val intent = Intent(UTSwapApp.instance, NewsDetailActivity::class.java)
            intent.putExtra("id",id)
            startActivity(intent)
        }
    }
}
