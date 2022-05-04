package com.zillennium.utswap.screens.trade.tradeExchangeScreen

import android.annotation.SuppressLint
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.example.utswapapp.simple.SimplePresenter
import com.zillennium.utswap.R
import com.zillennium.utswap.databinding.ActivitySimpleBinding
import com.zillennium.utswap.databinding.ActivityTradeExchangeBinding
import java.io.IOException

class TradeExchangeActivity :
    BaseMvpActivity<TradeExchangeView.View, TradeExchangeView.Presenter, ActivityTradeExchangeBinding>(),
    TradeExchangeView.View {

    override var mPresenter: TradeExchangeView.Presenter = TradeExchangePresenter()
    override val layoutResource: Int = R.layout.activity_trade_exchange

    @SuppressLint("SetJavaScriptEnabled")
    override fun initView() {
        super.initView()
        try {
            binding.apply {

                val width = windowManager.defaultDisplay.width;
                val params = webView.layoutParams
                params.height = (width * 0.75).toInt()
                webView.layoutParams = params

                val webSettings = webView.settings
                webSettings.javaScriptEnabled = true
                webSettings.domStorageEnabled = true
                webSettings.loadWithOverviewMode = true
                webSettings.useWideViewPort = true
                webSettings.builtInZoomControls = true
                webSettings.displayZoomControls = false
                webSettings.setSupportZoom(true)
                webSettings.defaultTextEncodingName = "utf-8"
                webSettings.allowFileAccess = true
                webSettings.databaseEnabled = true
                webSettings.domStorageEnabled = true
                webSettings.cacheMode = WebSettings.LOAD_DEFAULT
                webSettings.loadWithOverviewMode = false
                webSettings.useWideViewPort = true
                webSettings.loadWithOverviewMode = true
                webSettings.loadsImagesAutomatically = true

                webView.webChromeClient = WebChromeClient()
                webView.webViewClient = object : WebViewClient() {
                    override fun onPageFinished(view: WebView, url: String) {
                        injectCssIntoWebView(
                            webView,
                            arrayOf(
                                ".header {display: none}",
                                "#main-wrapper { top: -70px }"
                            )
                        )
                        super.onPageFinished(view, url)
                    }
                }
//              webView.addJavascriptInterface(AndroidJSInterface, "Android");

                webView.loadUrl("https://utswap.io/Trade/tradingview/market/utsr_usd")



                // Set Passed Back
                ivBack.setOnClickListener {
                    onBackPressed()
                }
            }
        } catch (error: Exception) {
            // Must be safe
        }

    }
    private val CREATE_CUSTOM_SHEET = """if (typeof(document.head) != 'undefined' && typeof(customSheet) == 'undefined') {
        var customSheet = (function() {
        var style = document.createElement(\"style\");
        style.appendChild(document.createTextNode(\"\"));
        document.head.appendChild(style);
        return style.sheet;
        })();
    }"""

    fun injectCssIntoWebView(webView: WebView, cssRules: Array<String>) {
        val jsUrl = StringBuilder("javascript:")
        jsUrl
            .append(CREATE_CUSTOM_SHEET)
            .append("if (typeof(customSheet) != 'undefined') {")
        for ((cnt, cssRule) in cssRules.withIndex()) {
            jsUrl
                .append("customSheet.insertRule('")
                .append(cssRule)
                .append("', ")
                .append(cnt)
                .append(");")
        }
        jsUrl.append("}")
        webView.loadUrl(jsUrl.toString())
    }
}