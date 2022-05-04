package com.zillennium.utswap.screens.setting.loginPasswordScreen

import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.R
import com.zillennium.utswap.databinding.ActivitySettingLoginPasswordBinding
import java.io.IOException

class LoginPasswordActivity :
    BaseMvpActivity<LoginPasswordView.View, LoginPasswordView.Presenter, ActivitySettingLoginPasswordBinding>(),
    LoginPasswordView.View {

    override var mPresenter: LoginPasswordView.Presenter = LoginPasswordPresenter()
    override val layoutResource: Int = R.layout.activity_setting_login_password

    override fun initView() {
        super.initView()
        try {
            binding.apply {
                ivBack.setOnClickListener {
                    onBackPressed()
                }
            }
        } catch (error: Exception) {
            // Must be safe
        }
    }
}