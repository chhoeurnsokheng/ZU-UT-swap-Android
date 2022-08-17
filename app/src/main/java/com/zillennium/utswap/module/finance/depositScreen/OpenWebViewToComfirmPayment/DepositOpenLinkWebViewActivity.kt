package com.zillennium.utswap.module.finance.depositScreen.OpenWebViewToComfirmPayment

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.content.ContextCompat
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.DepositOpenLinkWebviewActivityBinding
import com.zillennium.utswap.module.project.projectInfoScreen.ProjectInfoActivity
import com.zillennium.utswap.utils.Constants

/**
 * Created by Sokheng Chhoeurn on 16/8/22.
 * Build in Mac
 */
class DepositOpenLinkWebViewActivity :
    BaseMvpActivity<DepositopenLinkView.View, DepositopenLinkView.Presenter, DepositOpenLinkWebviewActivityBinding>(),
    DepositopenLinkView.View {

    override val layoutResource: Int = R.layout.deposit_open_link_webview_activity
    override var mPresenter: DepositopenLinkView.Presenter = DepositopenLinkPresenter()
    var payment_link = ""
    companion object {
        fun launchDepositOpenLinkWebViewActivity(context: Context, payment_link: String?) {
            val intent = Intent(context, DepositOpenLinkWebViewActivity::class.java)
            intent.putExtra(Constants.Deposit.Payment_Link, payment_link)
            context.startActivity(intent)
        }
    }

    override fun initView() {
        super.initView()
        openWebView()
        toolBar()

    }

    private fun openWebView() {
        binding.apply {

            webView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH)
            webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                webView.setLayerType(View.LAYER_TYPE_HARDWARE, null)
            } else {
                webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
            }

            webView.settings.javaScriptEnabled = true
            webView.webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                    if (url != null) {
                        view?.loadUrl(url)
                    }
                    return true
                }
            }

            if (intent.hasExtra(Constants.Deposit.Payment_Link)) {

                 payment_link = intent.getStringExtra(Constants.Deposit.Payment_Link).toString()
                //  binding.progressBar.visibility = View.VISIBLE
                if (payment_link != null) {
                    binding.progressBar.visibility = View.GONE
                    webView.loadUrl(payment_link)
                }
            }

        }
    }


    private fun toolBar() {
        setSupportActionBar(binding.includeLayout.tb)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_left)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.includeLayout.apply {

            tbTitle.text = "Deposit Review" // "${R.string.deposit_review}"
            tbTitle.setTextColor(ContextCompat.getColor(applicationContext, R.color.primary))
            tb.setOnClickListener {
                finish()
            }
        }
    }

}