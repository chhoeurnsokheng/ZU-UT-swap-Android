package com.zillennium.utswap.screens.navbar.navbar

import android.content.Intent
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.zillennium.utswap.Datas.GlobalVariable.SessionVariable
import com.zillennium.utswap.Datas.StoredPreferences.SessionPreferences
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivityNavbarBinding
import com.zillennium.utswap.screens.kyc.kycActivity.KYCActivity
import com.zillennium.utswap.screens.navbar.homeTab.HomeFragment
import com.zillennium.utswap.screens.navbar.portfolioTab.PortfolioFragment
import com.zillennium.utswap.screens.navbar.projectTab.projectScreen.ProjectFragment
import com.zillennium.utswap.screens.navbar.tradeTab.TradeFragment
import com.zillennium.utswap.screens.security.securityActivity.registerScreen.RegisterActivity
import com.zillennium.utswap.screens.security.securityActivity.signInScreen.SignInActivity
import java.lang.Exception


class NavbarActivity :
    BaseMvpActivity<NavbarView.View, NavbarView.Presenter, ActivityNavbarBinding>(),
    NavbarView.View {

    override var mPresenter: NavbarView.Presenter = NavbarPresenter()
    override val layoutResource: Int = R.layout.activity_navbar

    override fun initView() {
        super.initView()
        try {
            binding.apply {

                SessionPreferences().removeValue("SESSION_STATUS")
                SessionPreferences().removeValue("SESSION_KYC")

                SessionVariable.SESSION_STATUS.observe(this@NavbarActivity) {
                    if(SessionVariable.SESSION_STATUS.value == true){
                        layAuth.visibility = GONE
                    }else{
                        layAuth.visibility = VISIBLE
                    }
                }

                if(SessionVariable.SESSION_STATUS.value == true){
                    layAuth.visibility = GONE
                }else{
                    layAuth.visibility = VISIBLE
                }

                SessionVariable.SESSION_KYC.observe(this@NavbarActivity) {
                    if(SessionVariable.SESSION_KYC.value == true){
                        layVerify.visibility = GONE
                    }else{
                        layVerify.visibility = VISIBLE
                    }
                }

                // Passing each menu ID as a set of Ids because each
                // menu should be considered as top level destinations.
                val appBarConfiguration = AppBarConfiguration.Builder(
                    R.id.navigation_navbar_home,
                    R.id.navigation_navbar_portfolio,
                    R.id.navigation_navbar_trade,
                    R.id.navigation_navbar_project,
                )
                    .build()

                val navController = findNavController(this@NavbarActivity, R.id.nav_host_fragment_activity_navbar_home)

                // This Theme haven't use NoActionBar
                setupWithNavController(navView, navController)

//                val HomeFragment = HomeFragment()
//                val PortfolioFragment = PortfolioFragment()
//                val TradeFragment = TradeFragment()
//                val ProjectFragment = ProjectFragment()
//                val fragmentManager = supportFragmentManager
//                var activeFragment: Fragment = TradeFragment
//
//                val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_navbar_home) as NavHostFragment
//                val navController = navHostFragment.navController
//
//                fragmentManager.beginTransaction().apply {
//                    add(R.id.nav_host_fragment_activity_navbar_home, HomeFragment, "HomeFragment").hide(HomeFragment)
//                    add(R.id.nav_host_fragment_activity_navbar_home, PortfolioFragment, "PortfolioFragment").hide(PortfolioFragment)
//                    add(R.id.nav_host_fragment_activity_navbar_home, TradeFragment, "TradeFragment").hide(TradeFragment)
//                    add(R.id.nav_host_fragment_activity_navbar_home, ProjectFragment, "ProjectFragment").hide(ProjectFragment)
//                }.commit()
//
//                navView.setOnNavigationItemSelectedListener { item ->
//                    when (item.itemId) {
//                        R.id.navigation_navbar_home -> {
//                            fragmentManager.beginTransaction().hide(activeFragment).show(HomeFragment).commit()
//                            activeFragment = HomeFragment
//                            true
//                        }
//                        R.id.navigation_navbar_portfolio -> {
//                            fragmentManager.beginTransaction().hide(activeFragment).show(PortfolioFragment).commit()
//                            activeFragment = PortfolioFragment
//                            true
//                        }
//                        R.id.navigation_navbar_trade -> {
//                            fragmentManager.beginTransaction().hide(activeFragment).show(TradeFragment).commit()
//                            activeFragment = TradeFragment
//                            true
//                        }
//                        R.id.navigation_navbar_project -> {
//                            fragmentManager.beginTransaction().hide(activeFragment).show(ProjectFragment).commit()
//                            activeFragment = ProjectFragment
//                            true
//                        }
//                        else -> false
//                    }
//                    true
//                }

//                fragmentManager.beginTransaction().hide(activeFragment).show(TradeFragment).commit()
//                activeFragment = TradeFragment
//                navView.selectedItemId = R.id.navigation_navbar_trade



                btnSignIn.setOnClickListener {
                    val intent = Intent(UTSwapApp.instance, SignInActivity::class.java)
                    startActivity(intent)
                }

                btnRegister.setOnClickListener {
                    val intent = Intent(UTSwapApp.instance, RegisterActivity::class.java)
                    startActivity(intent)
                }

                btnVerify.setOnClickListener {
                    val intent = Intent(UTSwapApp.instance, KYCActivity::class.java)
                    startActivity(intent)
                }
            }


        } catch (error: Exception) {
            // Must be safe
        }
    }


//    override fun onBackPressed() {
//        if (doubleBackToExitPressedOnce) {
//            super.onBackPressed()
//            return
//        }
//        doubleBackToExitPressedOnce = true
//        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show()
//        Handler().postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)
//    }
}