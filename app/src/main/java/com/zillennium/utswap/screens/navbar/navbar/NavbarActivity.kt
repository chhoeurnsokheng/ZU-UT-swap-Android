package com.zillennium.utswap.screens.navbar.navbar

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.se.omapi.Session
import android.util.Log
import android.view.View.GONE
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.zillennium.utswap.R
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivityNavbarBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.zillennium.utswap.Datas.StoredPreferences.KYCPreferences
import com.zillennium.utswap.Datas.StoredPreferences.SessionPreferences
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.screens.kyc.idTypeScreen.IdTypeActivity
import com.zillennium.utswap.screens.kyc.termConditionScreen.TermConditionActivity
import com.zillennium.utswap.screens.security.signInScreen.SignInActivity
import com.zillennium.utswap.screens.security.signUpScreen.SignUpActivity
import com.zillennium.utswap.screens.setting.kyc.KYCActivity
import java.io.IOException

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
                    val intent = Intent(UTSwapApp.instance, SignUpActivity::class.java)
                    startActivity(intent)
                }

                btnVerify.setOnClickListener {
                    KYCPreferences().clearValue()
                    val intent = Intent(UTSwapApp.instance, IdTypeActivity::class.java)
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
            if(!SessionPreferences().SESSION_USERNAME.isNullOrEmpty()){
                layAuth.visibility = GONE
            }

            if(SessionPreferences().SESSION_KYC!!){
                layVerify.visibility = GONE
            }
        }
    }
}