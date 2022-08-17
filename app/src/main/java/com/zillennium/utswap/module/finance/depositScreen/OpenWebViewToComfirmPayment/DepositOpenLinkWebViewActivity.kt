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
        //    binding.progressBar.visibility = View.VISIBLE
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

            var payment_Link = intent?.getStringExtra(Constants.Deposit.Payment_Link)
//            val uri: Uri = Uri.parse(payment_Link)
//            startActivity(Intent(Intent.ACTION_VIEW, uri))

             //   webView.loadUrl("https://devwebpayment.kesspay.io/pay/2034526845778c12e3cb061d?access_token=0ad4c6e4ff1d525bdd08fb8f185e33d69de5aa9cc702a7737baf7d150357dab1&access_key=d70a999937aaa903f74ed5f733c4721a9b88e2ab597fb61ee91d07abbf0c3097")
            if (intent.hasExtra(Constants.Deposit.Payment_Link)) {
                val payment_link = intent?.getStringExtra(Constants.Deposit.Payment_Link)
                if (payment_link != null) {
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