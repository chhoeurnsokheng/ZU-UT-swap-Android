package com.zillennium.utswap.module.splashScreen

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Handler
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivitySplashScreenBinding
import com.zillennium.utswap.module.main.MainActivity


@SuppressLint("CustomSplashScreen")
class SplashScreenActivity :
    BaseMvpActivity<SplashScreenView.View, SplashScreenView.Presenter, ActivitySplashScreenBinding>(),
    SplashScreenView.View {

    override var mPresenter: SplashScreenView.Presenter = SplashScreenPresenter()
    override val layoutResource: Int = R.layout.activity_splash_screen

    override fun initView() {
        super.initView()
        try {
            binding.apply {
                Handler().postDelayed({
                    val intent = Intent(UTSwapApp.instance, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }, 3000)
            }
            // Code
        } catch (error: Exception) {
            // Must be safe
        }
    }


}