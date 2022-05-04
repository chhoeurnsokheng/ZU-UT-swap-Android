package com.zillennium.utswap.screens.security.newPasswordScreen

import android.content.Intent
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivitySecurityNewPasswordBinding
import com.zillennium.utswap.screens.navbar.navbar.NavbarActivity
import java.io.IOException

class NewPasswordActivity :
    BaseMvpActivity<NewPasswordView.View, NewPasswordView.Presenter, ActivitySecurityNewPasswordBinding>(),
    NewPasswordView.View {

    override var mPresenter: NewPasswordView.Presenter = NewPasswordPresenter()
    override val layoutResource: Int = R.layout.activity_security_new_password

    override fun initView() {
        super.initView()
        try {
            binding.apply {

                imgBack.setOnClickListener { finish() }

                btnNext.setOnClickListener {
                    val intent = Intent(UTSwapApp.instance, NavbarActivity::class.java)
                    startActivity(intent)
                }

            }
            // Code
        } catch (error: Exception) {
            // Must be safe
        }
    }
}