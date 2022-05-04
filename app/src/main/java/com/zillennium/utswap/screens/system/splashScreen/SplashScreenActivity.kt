package com.zillennium.utswap.screens.system.splashScreen

import android.content.Intent
import android.os.Handler
import com.zillennium.utswap.R
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivitySplashScreenBinding
import com.zillennium.utswap.screens.system.welcomeScreen.WelcomeActivity
import java.io.IOException

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
                    //Intent intent = new Intent(SplashScreenActivity.this, SigninActivity.class);
                    val intent = Intent(this@SplashScreenActivity, WelcomeActivity::class.java)
                    startActivity(intent)
                    finish()
                }, 5000)
            }
            // Code
        } catch (error: Exception) {
            // Must be safe
        }
    }
}