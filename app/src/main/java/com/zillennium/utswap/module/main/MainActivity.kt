package com.zillennium.utswap.screens.navbar.navbar



import android.content.Intent
import android.net.Uri
import android.os.Handler
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.google.android.play.core.appupdate.AppUpdateInfo
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.model.UpdateAvailability
import com.zillennium.utswap.BuildConfig
import com.zillennium.utswap.Datas.GlobalVariable.SessionVariable
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivityMainBinding
import com.zillennium.utswap.models.home.ForceUpdate
import com.zillennium.utswap.module.kyc.kycActivity.KYCActivity
import com.zillennium.utswap.module.kyc.kycFragment.fundPasswordScreen.FundPasswordFragment
import com.zillennium.utswap.module.main.MainPresenter
import com.zillennium.utswap.module.main.MainView
import com.zillennium.utswap.module.main.home.HomeFragment
import com.zillennium.utswap.module.main.news.NewsFragment
import com.zillennium.utswap.module.main.portfolio.PortfolioFragment
import com.zillennium.utswap.module.main.trade.tradeScreen.TradeFragment
import com.zillennium.utswap.module.security.securityActivity.signInScreen.SignInActivity
import com.zillennium.utswap.utils.DialogUtil
import com.zillennium.utswap.utils.DialogUtilKyc


class MainActivity : BaseMvpActivity<MainView.View, MainView.Presenter, ActivityMainBinding>(),
    MainView.View {

    override var mPresenter: MainView.Presenter = MainPresenter()
    override val layoutResource: Int = com.zillennium.utswap.R.layout.activity_main

    private var doubleBackToExitPressedOnce = false
    var statusKYC  = FundPasswordFragment.status

    override fun initView() {
        super.initView()
        onCheckSession()
        onSetUpNavBar()
        mPresenter.checkForceUpdate(this)
    }

    override fun onGetForceUpdateSuccess(data: ForceUpdate.ForceUpdateRes) {
        if (BuildConfig.VERSION_NAME <data.data?.version.toString()){
            DialogUtilKyc().customDialog(
                com.zillennium.utswap.R.drawable.ic_force_update,
                "New version available",
                "Looks like you have an older version of the app. Please update to get latest features and best experience.",
                "UPDATE NOW",
                object : DialogUtil.OnAlertDialogClick {
                    override fun onLabelCancelClick() {
                        val uri: Uri = Uri.parse(data.data!!.app_url?.android)
                        startActivity(Intent(Intent.ACTION_VIEW, uri))
                    }
                },
                this
            )
        }

    }

    override fun onGetForceUpdateFailed(data: String) {}

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

//                if (statusKYC == "1"){
//                    binding.apply {
//                        btnVerify.visibility= View.GONE
//                        layVerify.visibility = View.GONE
//                    }
//                }else{
//                    btnVerify.visibility= View.VISIBLE
//                    layVerify.visibility = View.VISIBLE
//                }

                SessionVariable.SESSION_STATUS.observe(this@MainActivity) {
                    if(SessionVariable.SESSION_STATUS.value == true ){
                        layAuth.visibility = GONE
                        layVerify.visibility = VISIBLE
                        btnVerify.visibility = VISIBLE
                    }else{
                        layAuth.visibility = VISIBLE
                        btnVerify.visibility = GONE
                        layVerify.visibility = GONE
                    }
                }

                SessionVariable.SESSION_KYC.observe(this@MainActivity) {
                    if(SessionVariable.SESSION_KYC.value == false && SessionVariable.SESSION_STATUS.value == true){
                        layVerify.visibility = VISIBLE
                    }else{
                        layVerify.visibility = GONE
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

    //Waiting upload app to Store to config more
    fun UpdateApp() {
        val appUpdateManager = AppUpdateManagerFactory.create(this)
        val appUpdateInfoTask: com.google.android.play.core.tasks.Task<AppUpdateInfo> = appUpdateManager.appUpdateInfo
        // Checks that the platform will allow the specified type of update.
        appUpdateInfoTask.addOnSuccessListener { result: AppUpdateInfo ->
            if (result.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE) {

        }
    }
    }


    private fun onSetUpNavBar(){
        try {
            binding.apply {

                val appBarConfiguration = AppBarConfiguration.Builder(
                    com.zillennium.utswap.R.id.navigation_navbar_home,
                    com.zillennium.utswap.R.id.navigation_navbar_portfolio,
                    com.zillennium.utswap.R.id.navigation_navbar_trade,
                    com.zillennium.utswap.R.id.navigation_navbar_news,
                )
                    .build()

                val navController = findNavController(this@MainActivity, com.zillennium.utswap.R.id.nav_host_fragment_activity_navbar_home)

                // This Theme haven't use NoActionBar
                setupWithNavController(navView, navController)

                val homeFragment = HomeFragment()
                val portfolioFragment = PortfolioFragment()
                val tradeFragment = TradeFragment()
                val newsTabFragment = NewsFragment()
                val fragmentManager = supportFragmentManager
                var activeFragment: Fragment = tradeFragment

                fragmentManager.beginTransaction().apply {
                    add(com.zillennium.utswap.R.id.nav_host_fragment_activity_navbar_home, homeFragment, "HomeFragment").hide(homeFragment)
                    add(com.zillennium.utswap.R.id.nav_host_fragment_activity_navbar_home, portfolioFragment, "PortfolioFragment").hide(portfolioFragment)
                    add(com.zillennium.utswap.R.id.nav_host_fragment_activity_navbar_home, tradeFragment, "TradeFragment").hide(tradeFragment)
                    add(com.zillennium.utswap.R.id.nav_host_fragment_activity_navbar_home, newsTabFragment, "NewsFragment").hide(newsTabFragment)
                }.commit()

                navView.setOnNavigationItemSelectedListener { item ->
                    when (item.itemId) {
                        com.zillennium.utswap.R.id.navigation_navbar_home -> {
                            fragmentManager.beginTransaction().hide(activeFragment).show(homeFragment).commit()
                            activeFragment = homeFragment
                        }
                        com.zillennium.utswap.R.id.navigation_navbar_portfolio -> {
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
                        com.zillennium.utswap.R.id.navigation_navbar_trade -> {
                            fragmentManager.beginTransaction().hide(activeFragment).show(tradeFragment).commit()
                            activeFragment = tradeFragment
                        }
                        com.zillennium.utswap.R.id.navigation_navbar_news -> {
                            fragmentManager.beginTransaction().hide(activeFragment).show(newsTabFragment).commit()
                            activeFragment = newsTabFragment
                        }
                    }
                    true
                }

                fragmentManager.beginTransaction().hide(activeFragment).show(homeFragment).commit()
                activeFragment = tradeFragment
                navView.selectedItemId = com.zillennium.utswap.R.id.navigation_navbar_home
            }
        }catch (e: Exception){

        }
    }
}