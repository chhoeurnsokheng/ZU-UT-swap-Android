package com.zillennium.utswap.screens.security.signInScreen

import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivitySecuritySignInBinding
import com.zillennium.utswap.screens.navbar.navbar.NavbarActivity
import java.io.IOException

class SignInActivity :
    BaseMvpActivity<SignInView.View, SignInView.Presenter, ActivitySecuritySignInBinding>(),
    SignInView.View {

    override var mPresenter: SignInView.Presenter = SignInPresenter()
    override val layoutResource: Int = R.layout.activity_security_sign_in

    var doubleBackToExitPressedOnce = false

    override fun initView() {
        super.initView()
        try {
            binding.apply {

                imgBack.setOnClickListener { finish() }

                btnSignIn.setOnClickListener {
                    val intent = Intent(UTSwapApp.instance, NavbarActivity::class.java)
                    startActivity(intent)
                }

                btnForgot.setOnClickListener {
                    val intent = Intent(UTSwapApp.instance, SignInActivity::class.java)
                    startActivity(intent)
                }

//        Glide.with(imgCapCha.getContext())
//                .load("https://utswap.io/Verify/code")
//                .into(imgCapCha);
//
//        imgCapCha.setOnClickListener(view -> {
//            Glide.with(imgCapCha.getContext())
//                    .load("https://utswap.io/Verify/code")
//                    .into(imgCapCha);
//        });

            }
            // Code
        } catch (error: IOException) {
            // Must be safe
        }
    }

    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }
        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show()
        Handler(Looper.getMainLooper()).postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
    }

    private fun getColoredSpanned(text: String, color: String): String? {
        return "<font color=$color>$text</font>"
    }
}