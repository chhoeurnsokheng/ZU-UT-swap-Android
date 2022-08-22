package com.zillennium.utswap.module.finance.depositScreen.OpenWebViewToComfirmPayment

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.View
import android.webkit.*
import androidx.core.content.ContextCompat
import com.zillennium.utswap.R
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.DepositOpenLinkWebviewActivityBinding
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
        val mWebChromeClient = object : WebChromeClient() {
            override fun onConsoleMessage(consoleMessage: ConsoleMessage): Boolean {
                Log.d(
                    "MyApplication", consoleMessage.message() + " -- From line "
                            + consoleMessage.lineNumber() + " of "
                            + consoleMessage.sourceId()
                )
                return super.onConsoleMessage(consoleMessage)
            }

            override fun onCloseWindow(window: WebView?) {
                super.onCloseWindow(window)
            }
        }
        binding.apply {

            webView.apply {
                setBackgroundColor(Color.parseColor("#FFFFFF"));
                isVerticalFadingEdgeEnabled = false
                isVerticalScrollBarEnabled = false
                webView.webViewClient = WebViewClient()
                webChromeClient = mWebChromeClient
                settings.javaScriptEnabled = true
                settings.domStorageEnabled = true
                settings.defaultTextEncodingName = "utf-8"
                settings.cacheMode = WebSettings.LOAD_NO_CACHE
                settings.useWideViewPort = true
                settings.domStorageEnabled = true
                settings.loadWithOverviewMode = true
                settings.javaScriptCanOpenWindowsAutomatically = true
                setLayerType(View.LAYER_TYPE_HARDWARE, null)
            }

            if (intent.hasExtra(Constants.Deposit.Payment_Link)) {
                payment_link = intent.getStringExtra(Constants.Deposit.Payment_Link).toString()
                webView.settings.javaScriptEnabled = true
                webView.settings.setSupportZoom(true)
                binding.progressBar.visibility = View.GONE
                webView.loadUrl(payment_link)
            }

        }
    }

    private fun toolBar() {
        setSupportActionBar(binding.includeLayout.tb)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_left)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.includeLayout.apply {
            tbTitle.setText(R.string.deposit_review)
            tbTitle.setTextColor(ContextCompat.getColor(applicationContext, R.color.primary))
            tb.setNavigationOnClickListener {
                finish()
            }
        }
    }


}