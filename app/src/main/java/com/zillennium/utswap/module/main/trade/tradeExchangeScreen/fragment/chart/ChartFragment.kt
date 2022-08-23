package com.zillennium.utswap.module.main.trade.tradeExchangeScreen.fragment.chart

import android.annotation.SuppressLint
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.FragmentPortfolioChartBinding
import com.zillennium.utswap.models.tradingList.TradingList
import com.zillennium.utswap.utils.Constants

class ChartFragment :
    BaseMvpFragment<ChartView.View, ChartView.Presenter, FragmentPortfolioChartBinding>(),
    ChartView.View {

    override var mPresenter: ChartView.Presenter = ChartPresenter()
    override val layoutResource: Int = R.layout.fragment_portfolio_chart

    override fun initView() {
        super.initView()
        onOtherActivity()
        mPresenter.getTradeChart(TradingList.TradeChartObj(Integer.parseInt(Constants.OrderBookTable.marketIdChart)),UTSwapApp.instance)
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun onOtherActivity(){
        binding.apply {
            val width = activity?.windowManager?.defaultDisplay?.width
            val params = webView.layoutParams
            if (width != null) {
                params.height = (width * 0.75).toInt()
            }
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
        }
    }

    override fun getTradeChartSuccess(data: TradingList.TradeChartRes) {
        binding.apply {
            //webView.loadUrl("https://utswap.io/Trade/tradingview/market/utsr_usd")
            webView.loadUrl(data.data?.trading_view.toString())
        }
    }

    override fun getTradeChartFail(data: TradingList.TradeChartRes) {

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