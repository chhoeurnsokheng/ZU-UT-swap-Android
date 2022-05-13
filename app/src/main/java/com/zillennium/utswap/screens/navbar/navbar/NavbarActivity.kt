package com.zillennium.utswap.screens.navbar.navbar

import android.content.Intent
import android.util.Log
import android.view.View.GONE
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.zillennium.utswap.R
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivityNavbarBinding
import com.zillennium.utswap.Datas.StoredPreferences.KYCPreferences
import com.zillennium.utswap.Datas.StoredPreferences.SessionPreferences
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.screens.kyc.kycActivity.KYCActivity
import com.zillennium.utswap.screens.security.securityActivity.registerScreen.RegisterActivity
import com.zillennium.utswap.screens.security.securityActivity.signInScreen.SignInActivity
import com.zillennium.utswap.screens.security.securityFragment.signInScreen.SignInFragment

class NavbarActivity :
    BaseMvpActivity<NavbarView.View, NavbarView.Presenter, ActivityNavbarBinding>(),
    NavbarView.View {

    override var mPresenter: NavbarView.Presenter = NavbarPresenter()
    override val layoutResource: Int = R.layout.activity_navbar

    override fun initView() {
        super.initView()
        try {
            binding.apply {

                SessionPreferences().removeValue("SESSION_USERNAME")
                SessionPreferences().SESSION_KYC = false

                if(!SessionPreferences().SESSION_USERNAME.isNullOrEmpty()){
                    layAuth.visibility = GONE
                }

                if(SessionPreferences().SESSION_KYC!!){
                    layVerify.visibility = GONE
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
                //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
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
                    KYCPreferences().clearValue()
                    val intent = Intent(UTSwapApp.instance, KYCActivity::class.java)
                    startActivity(intent)
                }
            }


        } catch (error: Exception) {
            // Must be safe
        }
    }

    override fun onTopResumedActivityChanged(isTopResumedActivity: Boolean) {
        super.onTopResumedActivityChanged(isTopResumedActivity)
        binding.apply {
            Log.d("1234567898", SessionPreferences().SESSION_USERNAME.toString())
            if(!SessionPreferences().SESSION_USERNAME.isNullOrEmpty()){
                layAuth.visibility = GONE
            }

            if(SessionPreferences().SESSION_KYC!!){
                layVerify.visibility = GONE
            }
        }
    }
}