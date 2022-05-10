package com.zillennium.utswap.screens.navbar.navbar

import android.os.Bundle
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.zillennium.utswap.R
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivityNavbarBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.io.IOException

class NavbarActivity :
    BaseMvpActivity<NavbarView.View, NavbarView.Presenter, ActivityNavbarBinding>(),
    NavbarView.View {

    override var mPresenter: NavbarView.Presenter = NavbarPresenter()
    override val layoutResource: Int = R.layout.activity_navbar

    override fun initView() {
        super.initView()
        try {
            //
            val navView = findViewById<BottomNavigationView>(R.id.nav_view)

            // Passing each menu ID as a set of Ids because each
            // menu should be considered as top level destinations.

            // Passing each menu ID as a set of Ids because each
            // menu should be considered as top level destinations.
            val appBarConfiguration = AppBarConfiguration.Builder(
                R.id.navigation_navbar_home,
                R.id.navigation_navbar_portfolio,
                R.id.navigation_navbar_trade,
                R.id.navigation_navbar_project,
            )
                .build()
            val navController = findNavController(this, R.id.nav_host_fragment_activity_navbar_home)
            // This Theme haven't use NoActionBar
            //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
            // This Theme haven't use NoActionBar
            //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
            setupWithNavController(navView, navController)
        } catch (error: Exception) {
            // Must be safe
        }
    }
}