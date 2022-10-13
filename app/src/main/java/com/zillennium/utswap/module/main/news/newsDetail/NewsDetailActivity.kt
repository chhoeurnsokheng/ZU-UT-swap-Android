package com.zillennium.utswap.module.main.news.newsDetail

import android.content.Context
import android.content.Intent
import android.os.Handler
import android.util.Base64
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import androidx.core.content.ContextCompat
import com.androidstudy.networkmanager.Tovuti
import com.bumptech.glide.Glide
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivityNewsDetailBinding
import com.zillennium.utswap.models.newsService.News
import com.zillennium.utswap.utils.AspectRatioUtil
import com.zillennium.utswap.utils.NoInternetLayoutUtil
import com.zillennium.utswap.utils.UtilConvert
import kotlin.math.roundToInt


class NewsDetailActivity :
    BaseMvpActivity<NewsDetailView.View, NewsDetailView.Presenter, ActivityNewsDetailBinding>(),
    NewsDetailView.View {

    override var mPresenter: NewsDetailView.Presenter = NewsDetailPresenter()
    override val layoutResource: Int = R.layout.activity_news_detail

    private var id: String? = null

    companion object {
        fun launchNewsDetailsActivity(context: Context, newsId: String?) {
            val intent = Intent(context, NewsDetailActivity::class.java)
            intent.putExtra("id", newsId)
            context.startActivity(intent)
        }
    }

    override fun initView() {
        super.initView()

        if (intent.hasExtra("id")) {
            id = intent?.getStringExtra("id")
        }

        toolBar()

        onCallApi()

        onSwipeRefresh()

         NoInternetLayoutUtil().noInternetLayoutUtil(binding.rlNoInt)
    }

    private fun onCallApi() {
        Tovuti.from(UTSwapApp.instance).monitor { _, isConnected, _ ->
            if (isConnected) {
                mPresenter.onGetNewsDetail(id!!)
                binding.progressBar.visibility = View.VISIBLE
            }
        }
    }

    override fun onGetNewsSuccess(data: News.NewsDetailData) {
        binding.apply {
            progressBar.visibility = View.GONE
            swipeRefresh.isRefreshing = false

            txtDateTitle.visibility = View.VISIBLE
            txtTitle.text = data.title.toString()
            txtDate.text = data.date?.addtime.toString()
            txtContent.loadData(getHTML(data.content.toString()), "text/html", "base64")
            txtContent.setOnTouchListener(OnTouchListener { v, event -> event.action == MotionEvent.ACTION_MOVE })
            Glide.with(imgNews.context)
                .load(data.image.toString())
                .placeholder(R.drawable.ic_placeholder)
                .into(imgNews)
            view.visibility = View.VISIBLE

            AspectRatioUtil.apply(
                9,
                16,
                UtilConvert.convertDpToPixel(0f, UTSwapApp.instance).roundToInt(),
                this@NewsDetailActivity,
                binding.imgNews
            )

        }
    }

    private fun getHTML(data: String): String {
        val unencodedHtml = "<html><head>\n" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "<link rel=\"stylesheet\" type=\"text/css\" href=\"font.css\">\n" +
                "<style>\n" +
                "   body {\n" +
                "       margin: 24px;\n" +
                "   }\n" +
                "</style>\n" +
                "</head><body>$data</body></html>"
        val encodedHtml = Base64.encodeToString(unencodedHtml.toByteArray(), Base64.NO_WRAP)
        return encodedHtml
    }


    override fun onGetNewsFail(data: News.NewsDetailData) {
        binding.apply {
            progressBar.visibility = View.VISIBLE
            swipeRefresh.isRefreshing = false
        }
    }

    private fun toolBar() {
        setSupportActionBar(binding.includeLayout.tb)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back_primary)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.includeLayout.apply {
            tbTitleLeft.visibility = View.VISIBLE
            tbTitleLeft.setOnClickListener {
                tbTitleLeft.isEnabled = false
                val link = "https://utswap.io/Article/detail/id/$id"
                // Share data to other application
//                val shareIntent = Intent().apply {
//                    this.action = Intent.ACTION_SEND
//                    this.type = "text/plain"
//                    this.putExtra(Intent.EXTRA_TEXT, "https://utswap.io/Article/detail/id/$id")
//
//                }
//                startActivity(shareIntent)
                startShareIntent(link)
                Handler().postDelayed({
                    tbTitleLeft.isEnabled = true
                }, 3000)
            }
            tb.setNavigationOnClickListener {
                finish()
            }

            binding.swipeRefresh.setColorSchemeColors(
                ContextCompat.getColor(
                    UTSwapApp.instance,
                    R.color.primary
                )
            )
        }
    }

    private fun startShareIntent(shortLink: String) {

        val sharingIntent = Intent(Intent.ACTION_SEND)
        sharingIntent.type = "text/plain"
        sharingIntent.putExtra(Intent.EXTRA_TEXT, shortLink)
        sharingIntent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
        startActivity(Intent.createChooser(sharingIntent, shortLink))

    }

    private fun onSwipeRefresh() {
        binding.apply {
            swipeRefresh.setOnRefreshListener {
                onSwipeRefreshCallApi()
            }
        }
    }

    private fun onSwipeRefreshCallApi() {
        Tovuti.from(UTSwapApp.instance).monitor { _, isConnected, _ ->
            if (isConnected) {
                id?.let { mPresenter.onGetNewsDetail(it) }
                binding.swipeRefresh.isRefreshing = true
            }
        }
    }
}