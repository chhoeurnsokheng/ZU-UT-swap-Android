package com.zillennium.utswap.module.finance.depositScreen.OpenWebViewToComfirmPayment


import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.webkit.*
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.DepositOpenLinkWebviewActivityBinding
import com.zillennium.utswap.models.deposite.DataQueryOrderObj
import com.zillennium.utswap.models.deposite.DepositObj
import com.zillennium.utswap.module.finance.depositScreen.depositSuccessfully.DepositSuccessfullyActivity
import com.zillennium.utswap.screens.navbar.navbar.MainActivity
import com.zillennium.utswap.utils.Constants
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*


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
    var transaction_id = ""
    var mainHandler = Handler(Looper.getMainLooper())
    var timer = Timer()

    companion object {
        fun launchDepositOpenLinkWebViewActivity(
            context: Context,
            payment_link: String?,
            transaction_id: String?
        ) {
            val intent = Intent(context, DepositOpenLinkWebViewActivity::class.java)
            intent.putExtra(Constants.Deposit.Payment_Link, payment_link)
            intent.putExtra(Constants.Deposit.TRANSATION_ID, transaction_id)
            context.startActivity(intent)
        }
    }

    override fun initView() {
        super.initView()
        openWebView()
        toolBar()
        repeatTimer()
    }

    private fun repeatTimer() {
        timer.scheduleAtFixedRate(
            object : TimerTask() {
                override fun run() {
                    callApi()
                }
            }, 1000, 18000
        )
    }

    private fun callApi() {
        if (intent.hasExtra(Constants.Deposit.TRANSATION_ID)) {
            transaction_id = intent.getStringExtra(Constants.Deposit.TRANSATION_ID).toString()
            mPresenter.getQueryOrder(
                UTSwapApp.instance,
                DepositObj.DataQueryOrderBody(transaction_id)
            )
        }
    }

    override fun getQueryOrderSuccess(data: DataQueryOrderObj.DataQueryOrderRes) {
        if (data.data?.dataQueryOrder?.data?.status == "SUCCESS") {
            timer.cancel()
            Toast.makeText(this@DepositOpenLinkWebViewActivity, "Sussess", Toast.LENGTH_SHORT)
                .show()
            startActivity(Intent(this, DepositSuccessfullyActivity::class.java))
        }
//        else{
//            repeatTimer()
//           // callApi()
////            lifecycleScope.launch {
////                delay(60000)
////                callApi()
////                Toast.makeText(this@DepositOpenLinkWebViewActivity, "Call again", Toast.LENGTH_SHORT).show()
////            }
//        }
    }

    override fun getQueryOrderFail(data: String) {}

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

            webView.webViewClient = object : WebViewClient() {
                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    super.onPageStarted(view, url, favicon)
                    Log.d("Start", "onPageStarted")
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)

                    Log.d("Finished", "onPageFinished")
                }

                override fun shouldOverrideUrlLoading(
                    view: WebView?,
                    request: WebResourceRequest?
                ): Boolean {
                    return false
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
            tbTitle.setText(R.string.deposit_review)
            tbTitle.setTextColor(ContextCompat.getColor(applicationContext, R.color.primary))
            tb.setNavigationOnClickListener {
                finish()
            }
        }
    }

    override fun onBackPressed() {
        binding.apply {
            if (webView.canGoBack()) {
                webView.goBack()
            } else {
                super.onBackPressed()
            }
        }
    }

}