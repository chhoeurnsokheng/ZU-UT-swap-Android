package com.zillennium.utswap.screens.navbar.navbar

import android.content.Intent
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.zillennium.utswap.Datas.GlobalVariable.SessionVariable
import com.zillennium.utswap.Datas.StoredPreferences.SessionPreferences
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivityNavbarBinding
import com.zillennium.utswap.screens.kyc.kycActivity.KYCActivity
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

//                var homeStatus = false
//                var portfolioStatus = false
//
//
//                navView.setOnNavigationItemSelectedListener { item ->
//
//                    for(child in layFragment.children){
//                        child.visibility = View.GONE
//                    }
//
//                    when (item.itemId) {
//                        R.id.navigation_navbar_home -> {
//                            val nav_host_fragment_activity_navbar_home = findViewById(R.id.nav_host_fragment_activity_navbar_home) as ViewGroup
//                            nav_host_fragment_activity_navbar_home.visibility = View.VISIBLE
//                            if(!homeStatus){
//                                val selectedFragment: Fragment = ProjectMainActivity()
//                                val transaction: FragmentTransaction =
//                                    supportFragmentManager.beginTransaction()
//                                transaction.replace(R.id.nav_host_fragment_activity_navbar_home, selectedFragment)
//                                transaction.commit()
//                                homeStatus = !homeStatus
//                            }
//
//                            Log.d("NavigationBarView", "navigation_navbar_home")
//                        }
//                        R.id.navigation_navbar_portfolio -> {
//                            val nav_host_fragment_activity_navbar_portfolio = findViewById(R.id.nav_host_fragment_activity_navbar_portfolio) as ViewGroup
//                            nav_host_fragment_activity_navbar_portfolio.visibility = View.VISIBLE
//                            if(!portfolioStatus){
//                                val selectedFragment: Fragment = ProjectMainActivity()
//                                val transaction: FragmentTransaction =
//                                    supportFragmentManager.beginTransaction()
//                                transaction.replace(R.id.nav_host_fragment_activity_navbar_portfolio, selectedFragment)
//                                transaction.commit()
//                                portfolioStatus = !portfolioStatus
//                            }
//
//                            Log.d("NavigationBarView", "navigation_navbar_portfolio")
//                        }
//                        R.id.navigation_navbar_trade -> {
//                            Log.d("NavigationBarView", "navigation_navbar_trade")
//                        }
//                        R.id.navigation_navbar_project -> {
//                            Log.d("NavigationBarView", "navigation_navbar_project")
//                        }
//                    }
//                    true
//                }
//                navView.selectedItemId = R.id.navigation_navbar_trade

                val navController = findNavController(this@NavbarActivity, R.id.nav_host_fragment_activity_navbar_home)


                // This Theme haven't use NoActionBar
                //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
                setupWithNavController(navView, navController)

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

    override fun onDestroy() {
        super.onDestroy()
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