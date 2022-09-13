package com.zillennium.utswap.module.main.news

import android.content.Intent
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
    private var listNews = ArrayList<News.NewsNew>()
    private var totalPage: Int? = 1
    private var page = 1
    private var lastPosition = 0
    private var isCancelRequest = true

    override fun initView() {
        super.initView()
        onCheckPreference()
//        onCallApi()
        onOrderActivity()
        onSwipeRefresh()
        loadMoreData()
        initRecyclerView()
    }

    private fun onCallApi() {
        Tovuti.from(UTSwapApp.instance).monitor { _, isConnected, _ ->
            if (isConnected) {
                binding.progressBar.visibility = View.VISIBLE
                mPresenter.onGetNews(UTSwapApp.instance, News.NewsObj(page))
            }
        }
    }

    override fun onGetNewsSuccess(data: News.NewsData) {
        if (page == 1) {
            listNews.clear()
        }
        binding.apply {
            progressBar.visibility = View.GONE
            pgLoadMore.visibility = View.GONE
            swipeRefresh.isRefreshing = false
            totalPage = data.TOTALPAGE
            if (data.NEW?.isNotEmpty() == true) {
                listNews.addAll(data.NEW ?: arrayListOf())
                newsAdapter?.items = listNews
                newsAdapter?.notifyDataSetChanged()

            }
            if (page == totalPage) {
                isCancelRequest = false
            }
        }
    }

    override fun onGetNewsFail() {
        binding.apply {
            progressBar.visibility = View.VISIBLE
            swipeRefresh.isRefreshing = false
        }
    }


    private fun onCheckPreference() {
        binding.apply {
            SessionVariable.SESSION_STATUS.observe(this@NewsFragment) {
                if (SessionVariable.SESSION_STATUS.value == true) {
                    imgMenu.setOnClickListener {
                        val intent = Intent(UTSwapApp.instance, AccountActivity::class.java)
                        startActivity(intent)
                        requireActivity().overridePendingTransition(
                            R.anim.slide_in_left,
                            R.anim.slide_out_right
                        )
                    }
                    imgNotification.setOnClickListener {
                        val intent = Intent(UTSwapApp.instance, NotificationActivity::class.java)
                        startActivity(intent)
                    }
                    tvBadgeNumber.visibility = View.VISIBLE
                } else {
                    imgMenu.setOnClickListener {
                        val intent = Intent(UTSwapApp.instance, SignInActivity::class.java)
                        startActivity(intent)
                    }
                    imgNotification.setOnClickListener {
                        val intent = Intent(UTSwapApp.instance, SignInActivity::class.java)
                        startActivity(intent)
                    }
                    tvBadgeNumber.visibility = View.GONE

                }
            }

        }


    }

    fun setBadgeNumberNews() {
        binding.apply {
            SessionVariable.BADGE_NUMBER.observe(this@NewsFragment) {
                if (SessionVariable.BADGE_NUMBER.value?.isNotEmpty() == true && SessionVariable.BADGE_NUMBER.value != "0") {
                    tvBadgeNumber.visibility = View.VISIBLE
                    if (it.toInt() > 9) {
                        tvBadgeNumber.text = "9+"
                    } else {
                        tvBadgeNumber.text = it
                    }
                } else {
                    tvBadgeNumber.visibility = View.INVISIBLE

                }
            }
        }
    }

    private fun onOrderActivity() {
        binding.apply {

            swipeRefresh.setColorSchemeColors(
                ContextCompat.getColor(
                    UTSwapApp.instance,
                    R.color.primary
                )
            )

            imgMenu.setOnClickListener {
                val intent = Intent(UTSwapApp.instance, SignInActivity::class.java)
                startActivity(intent)
            }

            imgNotification.setOnClickListener {
                val intent = Intent(UTSwapApp.instance, NotificationActivity::class.java)
                startActivity(intent)
            }

        }
    }

    override fun onResume() {
        super.onResume()
        if (isCancelRequest) {
            requestData()
        }
    }

    private fun requestData() {
        SessionVariable.SESSION_STATUS.observe(this) {
            if (it) {
                activity?.let { context ->
                    mPresenter.onGetNews(context, News.NewsObj(page))
                }
            } else {
                mPresenter.getNewsWithoutToken(page)

            }
        }

    }

    private fun loadMoreData() {
        binding.rvNews.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) {
                    lastPosition =
                        (binding.rvNews.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
                    if (lastPosition == listNews.size - 1 && page < (totalPage ?: 1)) {
                        binding.pgLoadMore.visibility = View.VISIBLE
                        page++
                        requestData()
                    }

                }
            }
        })
    }

    private fun initRecyclerView() {
        binding.apply {
            val linearLayoutManager = LinearLayoutManager(requireContext())
            rvNews.layoutManager = linearLayoutManager
            newsAdapter = NewsAdapter(listener = object : NewsAdapter.Listener {
                override fun clickNews(id: String) {
                    NewsDetailActivity.launchNewsDetailsActivity(requireActivity(), id)
                }
            })
            rvNews.adapter = newsAdapter

        }
    }

    private fun onSwipeRefresh() {
        binding.apply {
            swipeRefresh.setOnRefreshListener {
                isCancelRequest = true
                page = 1
                (activity as MainActivity).onRefreshData()
                requestData()
            }
        }
    }
}
