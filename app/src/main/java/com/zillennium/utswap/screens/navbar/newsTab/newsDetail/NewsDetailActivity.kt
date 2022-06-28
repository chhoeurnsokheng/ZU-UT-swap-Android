package com.zillennium.utswap.screens.navbar.newsTab.newsDetail

import android.content.Intent
import com.zillennium.utswap.R
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivityNewsDetailBinding
import com.zillennium.utswap.databinding.ActivitySimpleBinding

class NewsDetailActivity:
    BaseMvpActivity<NewsDetailView.View, NewsDetailView.Presenter, ActivityNewsDetailBinding>(),
    NewsDetailView.View {

    override var mPresenter: NewsDetailView.Presenter = NewsDetailPresenter()
    override val layoutResource: Int = R.layout.activity_news_detail

    override fun initView() {
        super.initView()
        try {
            binding.apply {
                imgClose.setOnClickListener {
                    finish()
                }

                var str: String = "Have you booked your land plots at Laoka (Mondulkiri) at ONLY \$11,000 / plot? We are accepting booking at this special price until Saturday, 05th February 2022. \n"+ "\n** P.S. Over 100 plots are SOLD.\n"+ "\n Contact us for more information. \n"+"\nSuscipit nulla porro aliquid sunt facilis. Dolores corrupti nobis quis consequatur. Minima autem qui ut qui amet dolores et. Voluptate debitis mollitia doloribus recusandae in vel vitae. Nisi nam quasi fugit molestias dolorem.\n"+"\nAtque consequatur facere laudantium rerum eos consequuntur quam similique. Ducimus facere numquam illo aliquid cumque impedit non. Ea iusto dolorum temporibus recusandae quisquam omnis.\n"+"\nSoluta officia quisquam ea placeat cupiditate. Qui officia eius molestiae quidem delectus impedit quae sed. Qui et et provident et aperiam corrupti. Illum quia ut incidunt aut qui fuga. Mollitia architecto quisquam qui quia voluptates explicabo iusto. Aliquam id voluptatibus quo totam eius sed quaerat. Est ut dolorem adipisci repudiandae. Magnam perspiciatis sit ad at nam. Esse magnam assumenda nisi ex recusandae odio.\n "+"\nQui alias minus facilis et officia omnis voluptates sunt. Rerum fugiat mollitia ipsam voluptatem amet dolores iusto. Qui dolore tenetur voluptatem ex est minus. Vel autem non delectus itaque quo magni impedit. Non eaque repellat rerum vero velit ea quos."

                txtContent.text = str

                btnShare.setOnClickListener {
                    // Share data to other application
                    val shareIntent = Intent().apply {
                        this.action = Intent.ACTION_SEND
                        this.type = "text/plain"
                        this.putExtra(Intent.EXTRA_TEXT,"https://utswap.io/Article/detail/id/23")
                    }
                    startActivity(shareIntent)
                }
            }
        } catch (error: Exception) {
            // Must be safe
        }
    }
}