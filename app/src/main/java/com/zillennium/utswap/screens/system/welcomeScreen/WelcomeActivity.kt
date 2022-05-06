package com.zillennium.utswap.screens.system.welcomeScreen

import android.content.Intent
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivitySystemWelcomeBinding
import com.zillennium.utswap.screens.security.signInScreen.SignInActivity
import com.zillennium.utswap.screens.security.signUpScreen.SignUpActivity

class WelcomeActivity :
    BaseMvpActivity<WelcomeView.View, WelcomeView.Presenter, ActivitySystemWelcomeBinding>(),
    WelcomeView.View {

    override var mPresenter: WelcomeView.Presenter = WelcomePresenter()
    override val layoutResource: Int = R.layout.activity_system_welcome

    override fun initView() {
        super.initView()
        try {
            binding.apply {

                supportActionBar?.hide()

                btnSignIn.setOnClickListener {
                    val intent = Intent(UTSwapApp.instance, SignInActivity::class.java)
                    startActivity(intent)
                }

                btnRegister.setOnClickListener {
                    val intent = Intent(UTSwapApp.instance, SignUpActivity::class.java)
                    startActivity(intent)
                }

            }
            // Code
        } catch (error: Exception) {
            // Must be safe
        }
    }
}