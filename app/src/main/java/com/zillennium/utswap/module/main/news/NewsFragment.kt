package com.zillennium.utswap.module.main.news

import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidstudy.networkmanager.Tovuti
import com.zillennium.utswap.Datas.GlobalVariable.SessionVariable
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.FragmentNewsBinding
import com.zillennium.utswap.models.newsService.News
import com.zillennium.utswap.module.account.accountScreen.AccountActivity
import com.zillennium.utswap.module.main.news.adapter.NewsAdapter
import com.zillennium.utswap.module.main.news.newsDetail.NewsDetailActivity
import com.zillennium.utswap.module.security.securityActivity.signInScreen.SignInActivity
import com.zillennium.utswap.module.system.notification.NotificationActivity
import com.zillennium.utswap.screens.navbar.navbar.MainActivity


class NewsFragment :
    BaseMvpFragment<NewsView.View, NewsView.Presenter, FragmentNewsBinding>(),
    NewsView.View {

    override var mPresenter: NewsView.Presenter = NewsPresenter()
    override val layoutResource: Int = R.layout.fragment_news

    private var newsAdapter: NewsAdapter? = null
    private var listNews =  ArrayList<News.NewsNew>()
    private var totalPage: Int?  = null
    private var page: Int? = 1

    override fun initView() {
        super.initView()
        onCheckPreference()
        onCallApi()
        onOrderActivity()
        onSwipeRefresh()

        SessionVariable.USER_EXPIRE_TOKEN.observe(this@NewsFragment){
            if(it == true){
                (activity as MainActivity).onRefreshData()
                page = 1
                listNews.clear()
                binding.txtEnd.visibility = View.GONE
                binding.layNewsLoading.visibility = View.GONE
                mPresenter.onGetNews(UTSwapApp.instance, News.NewsObj(page))
            }
        }
    }

    private fun onCallApi(){
        Tovuti.from(UTSwapApp.instance).monitor{ _, isConnected, _ ->
            if(isConnected)
            {
                binding.progressBar.visibility = View.VISIBLE
                mPresenter.onGetNews(UTSwapApp.instance, News.NewsObj(page))
            }
        }
    }

    override fun onGetNewsSuccess(data: News.NewsData) {
        binding.apply {

            progressBar.visibility = View.GONE
            progressBarReadMore.visibility = View.GONE
            swipeRefresh.isRefreshing = false
            layNewsLoading.visibility = View.GONE

            totalPage = data.TOTALPAGE

            if(data.NEW!!.isNotEmpty())
            {
                listNews.addAll(data.NEW!!)

                newsAdapter = NewsAdapter(listener = object : NewsAdapter.Listener{
                    override fun clickNews(id: String) {
                        NewsDetailActivity.launchNewsDetailsActivity(requireActivity(), id)
//                        val intent = Intent(UTSwapApp.instance, NewsDetailActivity::class.java)
//                        intent.putExtra("id",id)
//                        startActivity(intent)
                    }
                })
                newsAdapter!!.items = listNews
                rvNews.adapter = newsAdapter
            }

            if(page!! == totalPage)
            {
                txtEnd.visibility = View.GONE

            }else{
                layNewsLoading.visibility = View.VISIBLE
                page = page!! + 1
            }
        }
    }

    override fun onGetNewsFail(data: News.NewsData) {
        binding.apply {
            progressBar.visibility = View.VISIBLE
            swipeRefresh.isRefreshing = false
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

                    page = 1
                    listNews.clear()
                    txtEnd.visibility = View.GONE
                    mPresenter.onGetNews(UTSwapApp.instance, News.NewsObj(page))
                    layNewsLoading.visibility = View.GONE
                    binding.progressBar.visibility = View.VISIBLE
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

                    page = 1
                    listNews.clear()
                    txtEnd.visibility = View.GONE
                    mPresenter.onGetNews(UTSwapApp.instance, News.NewsObj(page))
                    layNewsLoading.visibility = View.GONE
                    binding.progressBar.visibility = View.VISIBLE
                }
            }

        }

        SessionVariable.SESSION_KYC.observe(this@NewsFragment) {
            if(it == false){
                page = 1
                listNews.clear()
                binding.txtEnd.visibility = View.GONE
                mPresenter.onGetNews(UTSwapApp.instance, News.NewsObj(page))
                binding.layNewsLoading.visibility = View.GONE
                binding.progressBar.visibility = View.VISIBLE
            }
        }
    }

    private fun onOrderActivity(){
        binding.apply {

            swipeRefresh.setColorSchemeColors(ContextCompat.getColor(UTSwapApp.instance, R.color.primary))

            imgMenu.setOnClickListener {
                val intent = Intent(UTSwapApp.instance, SignInActivity::class.java)
                startActivity(intent)
            }

            imgNotification.setOnClickListener {
                val intent = Intent(UTSwapApp.instance, NotificationActivity::class.java)
                startActivity(intent)
            }

            readMore.setOnClickListener {
                mPresenter.onGetNews(UTSwapApp.instance, News.NewsObj(page))
                progressBarReadMore.visibility = View.VISIBLE
            }

            //set layout manager to recycle view
            val linearLayoutManager = LinearLayoutManager(requireContext())
            rvNews.layoutManager = linearLayoutManager
        }
    }

    private fun onSwipeRefresh(){
        binding.apply {
            //swipe refresh to get page 1 again
            swipeRefresh.setOnRefreshListener {
                page = 1
                listNews.clear()
                txtEnd.visibility = View.GONE
                layNewsLoading.visibility = View.GONE
                mPresenter.onGetNews(UTSwapApp.instance, News.NewsObj(page))
            }
        }
    }
}
