package com.zillennium.utswap.screens.navbar.navbar

import android.content.Context
import android.content.Intent
import android.os.Handler
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.zillennium.utswap.Datas.GlobalVariable.SessionVariable
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
import com.zillennium.utswap.utils.Constants


class MainActivity :
    BaseMvpActivity<MainView.View, MainView.Presenter, ActivityMainBinding>(),
    MainView.View {

    override var mPresenter: MainView.Presenter = MainPresenter()
    override val layoutResource: Int = R.layout.activity_main

    private var doubleBackToExitPressedOnce = false
    var statusKYC  = FundPasswordFragment.status

    companion object {
        fun launchTradeExchangeActivity(context: Context, projectName: String?) {
            val intent = Intent(context, MainActivity::class.java)
            intent.putExtra(Constants.TradeExchange.Status_submit, projectName)
            context.startActivity(intent)
        }
    }

    override fun initView() {
        super.initView()
        onCheckSession()
        onSetUpNavBar()
    }



    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }
        doubleBackToExitPressedOnce = true
        Toast.makeText(UTSwapApp.instance, "Please click BACK again to exit", Toast.LENGTH_SHORT).show()
        Handler().postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)
    }

    private fun onCheckSession(){
        try {
            binding.apply {

                SessionVariable.SESSION_STATUS.observe(this@MainActivity) {
                    if(SessionVariable.SESSION_STATUS.value == true){
                        layAuth.visibility = GONE
                        layVerify.visibility = View.VISIBLE
                        btnVerify.visibility = VISIBLE
                    } else{
                        layAuth.visibility = VISIBLE
                        btnVerify.visibility = GONE
                    }
                }

                if (intent.hasExtra(Constants.TradeExchange.Status_submit)) {
                    val name = intent?.getStringExtra(Constants.TradeExchange.Status_submit)
                    if (name=="1"){
                        layAuth.visibility =View.GONE
                    }
                }
                layAuth.setOnClickListener {
                    val intent = Intent(UTSwapApp.instance, SignInActivity::class.java)
                    startActivity(intent)
                }

                btnVerify.setOnClickListener {
                    val intent = Intent(UTSwapApp.instance, KYCActivity::class.java)
                    startActivity(intent)
                }
            }
        }catch (e: Exception){

        }
    }

    private fun onSetUpNavBar(){
        try {
            binding.apply {

                val appBarConfiguration = AppBarConfiguration.Builder(
                    R.id.navigation_navbar_home,
                    R.id.navigation_navbar_portfolio,
                    R.id.navigation_navbar_trade,
                    R.id.navigation_navbar_news,
                )
                    .build()

                val navController = findNavController(this@MainActivity, R.id.nav_host_fragment_activity_navbar_home)

                // This Theme haven't use NoActionBar
                setupWithNavController(navView, navController)

                val homeFragment = HomeFragment()
                val portfolioFragment = PortfolioFragment()
                val tradeFragment = TradeFragment()
                val newsTabFragment = NewsFragment()
                val fragmentManager = supportFragmentManager
                var activeFragment: Fragment = tradeFragment

                fragmentManager.beginTransaction().apply {
                    add(R.id.nav_host_fragment_activity_navbar_home, homeFragment, "HomeFragment").hide(homeFragment)
                    add(R.id.nav_host_fragment_activity_navbar_home, portfolioFragment, "PortfolioFragment").hide(portfolioFragment)
                    add(R.id.nav_host_fragment_activity_navbar_home, tradeFragment, "TradeFragment").hide(tradeFragment)
                    add(R.id.nav_host_fragment_activity_navbar_home, newsTabFragment, "NewsFragment").hide(newsTabFragment)
                }.commit()

                navView.setOnNavigationItemSelectedListener { item ->
                    when (item.itemId) {
                        R.id.navigation_navbar_home -> {
                            fragmentManager.beginTransaction().hide(activeFragment).show(homeFragment).commit()
                            activeFragment = homeFragment
                        }
                        R.id.navigation_navbar_portfolio -> {
                            SessionVariable.SESSION_KYC_STATUS.observe(this@MainActivity){
                                if (SessionVariable.SESSION_STATUS.value ==true){
                                    fragmentManager.beginTransaction().hide(activeFragment).show(portfolioFragment).commit()
                                    activeFragment = portfolioFragment
                                }else{
                                    val intent = Intent(UTSwapApp.instance, SignInActivity::class.java)
                                    startActivity(intent)
                                }
                            }

                        }
                        R.id.navigation_navbar_trade -> {
                            fragmentManager.beginTransaction().hide(activeFragment).show(tradeFragment).commit()
                            activeFragment = tradeFragment
                        }
                        R.id.navigation_navbar_news -> {
                            fragmentManager.beginTransaction().hide(activeFragment).show(newsTabFragment).commit()
                            activeFragment = newsTabFragment
                        }
                    }
                    true
                }

                fragmentManager.beginTransaction().hide(activeFragment).show(homeFragment).commit()
                activeFragment = tradeFragment
                navView.selectedItemId = R.id.navigation_navbar_home
            }
        }catch (e: Exception){

        }
    }
}