package com.zillennium.utswap.module.finance.depositScreen.OpenWebViewToComfirmPayment


import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.webkit.*
import androidx.core.content.ContextCompat
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.DepositOpenLinkWebviewActivityBinding
import com.zillennium.utswap.models.deposite.DataQueryOrderObj
import com.zillennium.utswap.models.deposite.DepositObj
import com.zillennium.utswap.module.finance.depositScreen.depositSuccessfully.DepositSuccessfullyActivity
import com.zillennium.utswap.utils.Constants
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        toolBar()
        if (intent.hasExtra(Constants.Deposit.Payment_Link)) {
            payment_link = intent.getStringExtra(Constants.Deposit.Payment_Link).toString()
        }
        repeatTimer()
        val webPay: WebView = findViewById(R.id.web_view)
        val webSettings: WebSettings = webPay.settings
        webSettings.javaScriptEnabled = true
        webSettings.javaScriptCanOpenWindowsAutomatically = true
        webSettings.useWideViewPort = true
        webSettings.setAppCacheEnabled(true)
        webSettings.domStorageEnabled = true
        webSettings.loadWithOverviewMode = true
        webSettings.allowFileAccess = true
        webSettings.pluginState = WebSettings.PluginState.ON
        webSettings.allowContentAccess = true
        webSettings.allowUniversalAccessFromFileURLs = true
        webSettings.javaScriptCanOpenWindowsAutomatically = true
        webSettings.loadsImagesAutomatically = true
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN)
        webPay.setScrollbarFadingEnabled(false)
        webSettings.setUseWideViewPort(true)
    //    webSettings.setJavaScriptEnabled(true)
        webPay.setVerticalScrollBarEnabled(false)
        webPay.setHorizontalScrollBarEnabled(false)



        webPay.webViewClient = object : WebViewClient() {

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                binding.progressBar.visibility =View.VISIBLE
            }

            override fun onPageFinished(view: WebView, url: String) {
                super.onPageFinished(view, url)
                binding.progressBar.visibility =View.GONE
            }

            override fun onReceivedError(
                view: WebView,
                request: WebResourceRequest,
                error: WebResourceError,
            ) {
                super.onReceivedError(view, request, error)
            }

            override fun onReceivedHttpError(
                view: WebView,
                request: WebResourceRequest,
                errorResponse: WebResourceResponse,
            ) {
                super.onReceivedHttpError(view, request, errorResponse)
                //error
            }

            //need to checking in here if has schema deeplink
            override fun shouldOverrideUrlLoading(
                view: WebView,
                request: WebResourceRequest,
            ): Boolean {


                val abaScheme = "abamobilebank"
                val acledaScheme = "market"
                val kessChatScheme = "kesspay.io"
                val spnScheme = "spnb"
                //if url
                val url = request.url.authority

                //if schema
                val uri = request.url
                val schema = request.url.scheme

                if (url == "https://qr.alipay.com/9446219319446735") {
                    return true
                } else if (schema == abaScheme) {  //ABA
                    val packagePlayStoreABA = "com.paygo24.ibank"
                    var intent: Intent? =
                        binding.root.context.packageManager?.getLaunchIntentForPackage("com.paygo24.ibank")
                    if (intent != null) {
                        val intent = Intent(Intent.ACTION_VIEW)
                        intent.data = uri
                        startActivity(intent)
                    } else {
                        intent = Intent(Intent.ACTION_VIEW)
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        intent.data = Uri.parse("market://details?id=$packagePlayStoreABA")
                        startActivity(intent)
                    }

                } else if (schema == acledaScheme) {    //Acleda
                    val packagePlayStoreAcleda = "com.domain.acledabankqr"
                    var intent: Intent? =
                        binding.root.context.packageManager?.getLaunchIntentForPackage("$packagePlayStoreAcleda")
                    if (intent != null) {
                        val intent = Intent(Intent.ACTION_VIEW)
                        intent.data = uri
                        startActivity(intent)
                    } else {

                        intent = Intent(Intent.ACTION_VIEW)
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        intent.data = Uri.parse("market://details?id=$packagePlayStoreAcleda")
                        startActivity(intent)
                    }

                } else if (schema == kessChatScheme) { //Kesss
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = uri
                    startActivity(intent)

                } else if (schema == spnScheme) {   //Sathapana
                    val packagePlayStoreSathapana = "kh.com.sathapana.consumer"
                    var intent: Intent? =
                        binding.root.context.packageManager?.getLaunchIntentForPackage("$packagePlayStoreSathapana")
                    if (intent != null) {
                        val intent = Intent(Intent.ACTION_VIEW)
                        intent.data = uri
                        startActivity(intent)
                    } else {
                        intent = Intent(Intent.ACTION_VIEW)
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        intent.data = Uri.parse("market://details?id=$packagePlayStoreSathapana")
                        startActivity(intent)
                    }


                }

                val mUrl: String = request.url.toString()
                return !(mUrl.startsWith("http:") || mUrl.startsWith("https://"))
            }
        }
        webPay.loadUrl(payment_link)

    }

    override fun getQueryOrderSuccess(data: DataQueryOrderObj.DataQueryOrderRes) {
        if (data.data?.dataQueryOrder?.data?.status == "SUCCESS") {
            timer.cancel()
            DepositSuccessfullyActivity.lunchDepositSuccessfullyActivity(
                this, transaction_id,
                data.data?.dataQueryOrder?.data?.total_amount
            )
        }

    }

    override fun getQueryOrderFail(data: String) {}

    private fun repeatTimer() {
        timer.scheduleAtFixedRate(
            object : TimerTask() {
                override fun run() {
                    callApi()
                }
            }, 1000, 17000
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


