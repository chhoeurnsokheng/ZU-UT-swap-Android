package com.zillennium.utswap.screens.system.splashScreen

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Handler
import android.util.Log
import androidx.core.app.ActivityCompat
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivitySplashScreenBinding
import com.zillennium.utswap.screens.navbar.navbar.NavbarActivity
import com.zillennium.utswap.screens.system.welcomeScreen.WelcomeActivity


class SplashScreenActivity :
    BaseMvpActivity<SplashScreenView.View, SplashScreenView.Presenter, ActivitySplashScreenBinding>(),
    SplashScreenView.View {

    override var mPresenter: SplashScreenView.Presenter = SplashScreenPresenter()
    override val layoutResource: Int = R.layout.activity_splash_screen

    override fun initView() {
        super.initView()
//        try {
            binding.apply {

                val os = System.getProperty("os.version")   // OS version
                val api = Build.VERSION.SDK                 // API Level
                val device = Build.DEVICE                   // Device
                val model = Build.MODEL                     // Model
                val project= Build.PRODUCT                  // Product
                val brand = Build.BRAND
                val id = Build.ID

                Log.d("imei", "hello")
                Log.d("os", os)
                Log.d("api", api)
                Log.d("device", device)
                Log.d("model", model)
                Log.d("project", project)
                Log.d("brand", brand)
                Log.d("id", id)


                Handler().postDelayed({
                    val intent = Intent(UTSwapApp.instance, WelcomeActivity::class.java)
                    startActivity(intent)
                    finish()
                }, 1000)
            }
            // Code
//        } catch (error: Exception) {
//            // Must be safe
//        }
    }
}