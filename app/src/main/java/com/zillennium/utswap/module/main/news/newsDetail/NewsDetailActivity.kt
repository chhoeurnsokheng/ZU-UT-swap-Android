package com.zillennium.utswap.module.main.news.newsDetail

import android.content.Intent
import android.text.Html
import android.view.View
import androidx.core.content.ContextCompat
import com.androidstudy.networkmanager.Tovuti
import com.bumptech.glide.Glide
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivityNewsDetailBinding
import com.zillennium.utswap.databinding.LayoutBaseNoInternetBinding
import com.zillennium.utswap.models.newsService.News
import com.zillennium.utswap.utils.DialogUtil
import com.zillennium.utswap.utils.NoInternetLayoutUtil

class NewsDetailActivity :
    BaseMvpActivity<NewsDetailView.View, NewsDetailView.Presenter, ActivityNewsDetailBinding>(),
    NewsDetailView.View {

    override var mPresenter: NewsDetailView.Presenter = NewsDetailPresenter()
    override val layoutResource: Int = R.layout.activity_news_detail

    private var id: String? = null

    override fun initView() {
        super.initView()

        val intent = intent
        id = intent.getStringExtra("id")

        toolBar()

        onCallApi()

        NoInternetLayoutUtil().noInternetLayoutUtil(binding.rlNoInt)
    }

    private fun onCallApi(){
        Tovuti.from(UTSwapApp.instance).monitor{ _, isConnected, _ ->
            if(isConnected)
            {
                mPresenter.onGetNewsDetail(id!!)
                binding.progressBar.visibility = View.VISIBLE
            }
        }
    }

    override fun onGetNewsSuccess(data: News.NewsDetailData) {
        binding.apply {
            progressBar.visibility = View.GONE

            txtTitle.text = data.title.toString()
            txtDate.text = data.date.toString()
            txtContent.text = Html.fromHtml(data.content.toString())
            Glide.with(imgNews.context)
                .load(data.image.toString())
                .into(imgNews)
            view.visibility = View.VISIBLE
        }
    }

    override fun onGetNewsFail(data: News.NewsDetailData) {
       binding.apply {
           progressBar.visibility = View.GONE
       }
    }

    private fun toolBar() {
        setSupportActionBar(binding.includeLayout.tb)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_left)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.includeLayout.apply {
            tbTitleLeft.visibility = View.VISIBLE
            tbTitleLeft.setOnClickListener {
                // Share data to other application
                val shareIntent = Intent().apply {
                    this.action = Intent.ACTION_SEND
                    this.type = "text/plain"
                    this.putExtra(Intent.EXTRA_TEXT, "https://utswap.io/Article/detail/id/23")
                }
                startActivity(shareIntent)
            }
            tb.setNavigationOnClickListener {
                finish()
            }
        }
    }
}