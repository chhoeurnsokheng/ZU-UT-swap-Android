package com.zillennium.utswap.screens.news.articleNewsScreen

import android.os.Bundle
import com.zillennium.utswap.R
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivityNewsArticleBinding
import java.io.IOException

class ArticleNewsActivity :
    BaseMvpActivity<ArticleNewsView.View, ArticleNewsView.Presenter, ActivityNewsArticleBinding>(),
    ArticleNewsView.View {

    override var mPresenter: ArticleNewsView.Presenter = ArticleNewsPresenter()
    override val layoutResource: Int = R.layout.activity_news_article

    override fun initView() {
        super.initView()
        try {
            binding.apply {

                // Set Passed Back
                ivBack.setOnClickListener {
                    onBackPressed()
                }
            }
            // Code
        } catch (error: Exception) {
            // Must be safe
        }
    }
}