package com.zillennium.utswap.screens.navbar.navbar

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.google.firebase.FirebaseApp
import com.google.firebase.analytics.FirebaseAnalytics
import com.zillennium.utswap.Datas.GlobalVariable.SessionVariable
import com.zillennium.utswap.Datas.StoredPreferences.KYCPreferences
import com.zillennium.utswap.Datas.StoredPreferences.SessionPreferences
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivityMainBinding
import com.zillennium.utswap.databinding.DepositOpenLinkWebviewActivityBinding
import com.zillennium.utswap.models.userService.User
import com.zillennium.utswap.module.finance.depositScreen.DepositActivity
import com.zillennium.utswap.module.finance.depositScreen.OpenWebViewToComfirmPayment.DepositOpenLinkWebViewActivity
import com.zillennium.utswap.module.kyc.kycActivity.KYCActivity
import com.zillennium.utswap.module.main.MainPresenter
import com.zillennium.utswap.module.main.MainView
import com.zillennium.utswap.module.main.home.HomeFragment
import com.zillennium.utswap.module.main.news.NewsFragment
import com.zillennium.utswap.module.main.portfolio.PortfolioFragment
import com.zillennium.utswap.module.main.trade.tradeScreen.TradeFragment
import com.zillennium.utswap.module.security.securityActivity.signInScreen.SignInActivity


class MainActivity :
    BaseMvpActivity<MainView.View, MainView.Presenter, ActivityMainBinding>(),
    MainView.View {

    override var mPresenter: MainView.Presenter = MainPresenter()
    override val layoutResource: Int = R.layout.activity_main
    private var kcySubmit: Boolean? = false
    private var kcyComplete: Boolean? = false
    private var isSelected = false
    private var isSignInSuccess = true
    private var doubleBackToExitPressedOnce = false
    private var statusKYC = ""

    override fun initView() {
        super.initView()
        onSetUpNavBar()
        handleDeepLink()
        FirebaseAnalytics.getInstance(this)
        handleIntent(intent)
        FirebaseApp.initializeApp(this)

        binding.layAuth.setOnClickListener {
            val intent = Intent(UTSwapApp.instance, SignInActivity::class.java)
            startActivityForResult(intent, 555)
        }
        binding.apply {
            test.setOnClickListener {
                val uri: Uri = Uri.parse("https://sokheng.page.link/Go1D")
                startActivity(Intent(Intent.ACTION_VIEW, uri))
            }
        }
    }
    private fun handleIntent(intent: Intent?) {
        val appLinkAction: String? = intent?.action
        val appLinkData: Uri? = intent?.data
        showDeepLinkOffer(appLinkAction, appLinkData)
    }
    private fun showDeepLinkOffer(appLinkAction: String?, appLinkData: Uri?) {
        // 1
        if (Intent.ACTION_VIEW == appLinkAction && appLinkData != null) {
            // 2
            val promotionCode = appLinkData.getQueryParameter("code")

            if (promotionCode.isNullOrBlank().not()) {

            }
        }
    }
    fun onRefreshData() {
        mPresenter.onCheckKYCStatus()

    }

    override fun onCheckKYCSuccess(data: User.KycRes) {
        kcySubmit = data.data?.status_submit_kyc
        kcyComplete = data.data?.status_kyc
        onCheckSession()

    }

    override fun onCheckKYCFail() {
        binding.layAuth.visibility = VISIBLE
        binding.layVerify.visibility = GONE
    }

    private fun handleDeepLink(){
        val intent = intent
        val action = intent.action
        val data: Uri? = intent.data
        if (data !=null){
            startActivity(Intent(this,DepositActivity::class.java))
        }
    }
    object kyc {
        var statusKycSubmit = ""
    }

    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            finish()
            return
        }
        doubleBackToExitPressedOnce = true
        Toast.makeText(UTSwapApp.instance, "Please click BACK again to exit", Toast.LENGTH_SHORT)
            .show()
        Handler().postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)
    }

    @SuppressLint("ResourceType")
    private fun onCheckSession() {
        try {
            kyc.statusKycSubmit = KYCPreferences().DO_KYC_STATUS.toString()
            Log.d("KYC", "${kyc.statusKycSubmit}")
            binding.apply {
                SessionVariable.SESSION_STATUS.observe(this@MainActivity) {

                    if (SessionPreferences().SESSION_TOKEN != null) {
                        if (kcySubmit == true) {
                            statusKYC = "Pending"
                            btnVerify.text = "KYC Approval is Pending"
                            tvVerify.text = "Your KYC application is being reviewed by our team."
                            btnVerify.visibility = VISIBLE
                            btnVerify.backgroundTintList = ContextCompat.getColorStateList(
                                this@MainActivity,
                                R.color.secondary
                            )
                            btnVerify.setTextColor(
                                ContextCompat.getColor(
                                    this@MainActivity,
                                    R.color.white
                                )
                            )
                        } else if (kcyComplete == false) {
                            btnVerify.visibility = VISIBLE
                            statusKYC = "New"
                            btnVerify.text = "Verify Your Identity"
                            tvVerify.text = "Please verify your identity to start trading."
                            btnVerify.backgroundTintList = ContextCompat.getColorStateList(
                                this@MainActivity,
                                R.color.primary
                            )
                            btnVerify.setTextColor(
                                ContextCompat.getColor(
                                    this@MainActivity,
                                    R.color.white
                                )
                            )

                        }
                        layAuth.visibility = GONE
                    } else {
                        layAuth.visibility = VISIBLE
                        layVerify.visibility = GONE
                    }

                    if (SessionVariable.SESSION_STATUS.value == true) {
                        layAuth.visibility = GONE
                        layVerify.visibility = VISIBLE
                    }
                    if (kcyComplete == true) {
                        layAuth.visibility = GONE
                        layVerify.visibility = GONE
                    } else {
                        layAuth.visibility = GONE
                        layVerify.visibility = VISIBLE


                    }
                    if (SessionPreferences().SESSION_TOKEN == null) {
                        layAuth.visibility = VISIBLE
                        layVerify.visibility = GONE

                    } else {
                        layAuth.visibility = GONE

                    }
                }

                btnVerify.setOnClickListener {
                    val intent = Intent(UTSwapApp.instance, KYCActivity::class.java).putExtra(
                        "KYCStatus",
                        statusKYC
                    )
                    startActivity(intent)
                }
            }
        } catch (e: Exception) {

        }
    }

    private fun onSetUpNavBar() {
        try {
            binding.apply {

                val appBarConfiguration = AppBarConfiguration.Builder(
                    R.id.navigation_navbar_home,
                    R.id.navigation_navbar_portfolio,
                    R.id.navigation_navbar_trade,
                    R.id.navigation_navbar_news,
                )
                    .build()

                val navController = findNavController(
                    this@MainActivity,
                    R.id.nav_host_fragment_activity_navbar_home
                )

                // This Theme haven't use NoActionBar
                setupWithNavController(navView, navController)

                val homeFragment = HomeFragment()
                val portfolioFragment = PortfolioFragment()
                val tradeFragment = TradeFragment()
                val newsTabFragment = NewsFragment()
                val fragmentManager = supportFragmentManager
                var activeFragment: Fragment = tradeFragment

                fragmentManager.beginTransaction().apply {
                    add(
                        R.id.nav_host_fragment_activity_navbar_home,
                        homeFragment,
                        "HomeFragment"
                    ).hide(homeFragment)
                    add(
                        R.id.nav_host_fragment_activity_navbar_home,
                        portfolioFragment,
                        "PortfolioFragment"
                    ).hide(portfolioFragment)
                    add(
                        R.id.nav_host_fragment_activity_navbar_home,
                        tradeFragment,
                        "TradeFragment"
                    ).hide(tradeFragment)
                    add(
                        R.id.nav_host_fragment_activity_navbar_home,
                        newsTabFragment,
                        "NewsFragment"
                    ).hide(newsTabFragment)
                }.commit()

                navView.setOnItemSelectedListener { item ->
                    mPresenter.onCheckKYCStatus()
                    when (item.itemId) {
                        R.id.navigation_navbar_home -> {
                            fragmentManager.beginTransaction().hide(activeFragment)
                                .show(homeFragment).commit()
                            activeFragment = homeFragment
                            isSignInSuccess = true

                        }
                        R.id.navigation_navbar_portfolio -> {
                            SessionVariable.SESSION_KYC_STATUS.observe(this@MainActivity) {
                                if (SessionPreferences().SESSION_TOKEN != null) {
                                    if (kcyComplete == false && isSignInSuccess) {
                                        val intent = Intent(
                                            UTSwapApp.instance,
                                            KYCActivity::class.java).putExtra("KYCStatus", statusKYC)
                                        startActivity(intent)

                                    } else if (kcyComplete == true) {
                                        fragmentManager.beginTransaction().hide(activeFragment)
                                            .show(portfolioFragment).commit()
                                        activeFragment = portfolioFragment
                                    }
                                } else {
                                    val intent =
                                        Intent(UTSwapApp.instance, SignInActivity::class.java)
                                    startActivityForResult(intent, 555)
                                }
                            }


                        }
                        R.id.navigation_navbar_trade -> {
                            fragmentManager.beginTransaction().hide(activeFragment)
                                .show(tradeFragment).commit()
                            activeFragment = tradeFragment

                        }
                        R.id.navigation_navbar_news -> {
                            fragmentManager.beginTransaction().hide(activeFragment)
                                .show(newsTabFragment).commit()
                            activeFragment = newsTabFragment

                        }
                    }
                    true
                }


                fragmentManager.beginTransaction().hide(activeFragment).show(homeFragment).commit()
                activeFragment = tradeFragment
                navView.selectedItemId = R.id.navigation_navbar_home
            }
        } catch (e: Exception) {

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == 555) {
            isSignInSuccess = false
            isSelected = true

        }
    }

    override fun onResume() {
        super.onResume()
        binding.navView.selectedItemId = R.id.navigation_navbar_home
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        intent?.let { newIntent ->
            handleIntent(newIntent)
        }
    }
}