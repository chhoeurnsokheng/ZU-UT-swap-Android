package com.zillennium.utswap.screens.navbar.navbar

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Handler
import android.util.Log
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.animation.LinearInterpolator
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.graphics.alpha
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.zillennium.utswap.Datas.GlobalVariable.SessionVariable
import com.zillennium.utswap.Datas.StoredPreferences.KYCPreferences
import com.zillennium.utswap.Datas.StoredPreferences.SessionPreferences
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivityMainBinding
import com.zillennium.utswap.module.kyc.kycActivity.KYCActivity
import com.zillennium.utswap.module.kyc.kycFragment.fundPasswordScreen.FundPasswordFragment
import com.zillennium.utswap.module.main.MainPresenter
import com.zillennium.utswap.module.main.MainView
import com.zillennium.utswap.module.main.home.HomeFragment
import com.zillennium.utswap.module.main.news.NewsFragment
import com.zillennium.utswap.module.main.portfolio.PortfolioFragment
import com.zillennium.utswap.module.main.trade.tradeScreen.TradeFragment
import com.zillennium.utswap.module.security.securityActivity.signInScreen.SignInActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.toDuration


class MainActivity :
    BaseMvpActivity<MainView.View, MainView.Presenter, ActivityMainBinding>(),
    MainView.View {

    override var mPresenter: MainView.Presenter = MainPresenter()
    override val layoutResource: Int = R.layout.activity_main

    private var doubleBackToExitPressedOnce = false
    private var statusKYC = ""

    override fun initView() {
        super.initView()
        onCheckSession()
        onSetUpNavBar()

    }



    object kyc {
        var statusKycSubmit = ""
    }

    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
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
                        if (SessionPreferences().SESSION_KYC_SUBMIT_STATUS == true) {
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

//                            btnVerify.animate().alpha(0.2f).duration = 500
//                            lifecycleScope.launch {
//                                delay(800)
//                                btnVerify.animate().alpha(1f).duration = 500
//                                delay(800)
//                                btnVerify.animate().alpha(0.2f).duration = 500
//                                delay(800)
//                                btnVerify.animate().alpha(1f).duration = 500
//                            }


                        } else if (SessionPreferences().SESSION_KYC == false) {
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
                    if (SessionPreferences().SESSION_KYC == true) {
                        layAuth.visibility = GONE
                        layVerify.visibility = GONE
                    } else {
                        layAuth.visibility = GONE
                        layVerify.visibility = VISIBLE

                        /*SessionVariable.SESSION_KYC_STATUS.observe(this@MainActivity){
                            if (SessionVariable.SESSION_KYC_STATUS.value ==1){
                                layVerify.visibility = GONE
                                layAuth.visibility = GONE
                            }
                        }*/


                    }
                    if (SessionPreferences().SESSION_TOKEN == null) {
                        layAuth.visibility = VISIBLE
                        layVerify.visibility = GONE

                    } else {
                        layAuth.visibility = GONE

                    }
                }

                /* if (SessionPreferences().SESSION_KYC_SUBMIT_STATUS == true){
                       layVerify.visibility =GONE
                   }else{
                       layVerify.visibility =View.VISIBLE
                   }*/

/*
                SessionVariable.SESSION_KYC.observe(this@MainActivity) {
                    if(SessionVariable.SESSION_KYC.value == false && SessionVariable.SESSION_STATUS.value == true){
                        layVerify.visibility = VISIBLE
                    }else{
                        layVerify.visibility = GONE
                    }
                }*/

                layAuth.setOnClickListener {
                    val intent = Intent(UTSwapApp.instance, SignInActivity::class.java)
                    startActivity(intent)
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
    var prevMenuItem: MenuItem? = null

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

                navView.setOnNavigationItemSelectedListener { item ->
                    when (item.itemId) {
                        R.id.navigation_navbar_home -> {
                            fragmentManager.beginTransaction().hide(activeFragment)
                                .show(homeFragment).commit()
                            activeFragment = homeFragment
                            navView.menu.getItem(1).isCheckable = true

                        }
                        R.id.navigation_navbar_portfolio -> {
                            SessionVariable.SESSION_KYC_STATUS.observe(this@MainActivity) {
                                if (SessionVariable.SESSION_STATUS.value == true) {
                                    if (SessionPreferences().SESSION_KYC == false) {
                                        val intent = Intent(UTSwapApp.instance, KYCActivity::class.java).putExtra(
                                            "KYCStatus",
                                            statusKYC
                                        )
                                        startActivity(intent)

                                    } else if (SessionPreferences().SESSION_KYC == true) {
                                        fragmentManager.beginTransaction().hide(activeFragment)
                                            .show(portfolioFragment).commit()
                                        activeFragment = portfolioFragment
                                    }
                                } else {
                                    navView.menu.getItem(1).isCheckable = false
                                    val intent =
                                        Intent(UTSwapApp.instance, SignInActivity::class.java)
                                    startActivity(intent)
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
    override fun onResume() {
        super.onResume()
        binding.navView.selectedItemId = R.id.navigation_navbar_home
    }
}