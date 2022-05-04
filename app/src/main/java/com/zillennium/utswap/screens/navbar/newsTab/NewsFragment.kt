package com.zillennium.utswap.screens.navbar.newsTab

import android.content.Intent
import android.view.View
import android.widget.LinearLayout
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.FragmentNavbarNewsBinding
import com.zillennium.utswap.screens.news.articleNewsScreen.ArticleNewsActivity
import java.io.IOException

class NewsFragment :
    BaseMvpFragment<NewsView.View, NewsView.Presenter, FragmentNavbarNewsBinding>(),
    NewsView.View {

    override var mPresenter: NewsView.Presenter = NewsPresenter()
    override val layoutResource: Int = R.layout.fragment_navbar_news

    override fun initView() {
        super.initView()
        try {
            binding.apply {

                for (i in 0 until layBreakingNews.childCount) {
                    layBreakingNews.getChildAt(i).setOnClickListener {
                        startActivity(
                            Intent(
                                UTSwapApp.instance,
                                ArticleNewsActivity::class.java
                            )
                        )
                    }
                }

                for (i in 0 until layPopularNews.childCount) {
                    layPopularNews.getChildAt(i).setOnClickListener {
                        startActivity(
                            Intent(
                                UTSwapApp.instance,
                                ArticleNewsActivity::class.java
                            )
                        )
                    }
                }

                for (i in 0 until layLatestNews.childCount) {
                    layLatestNews.getChildAt(i).setOnClickListener {
                        startActivity(
                            Intent(
                                UTSwapApp.instance,
                                ArticleNewsActivity::class.java
                            )
                        )
                    }
                }
            }

        } catch (error: Exception) {
            // Must be safe
        }
    }
}