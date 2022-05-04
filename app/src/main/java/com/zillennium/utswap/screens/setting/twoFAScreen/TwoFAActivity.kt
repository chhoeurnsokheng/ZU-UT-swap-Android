package com.zillennium.utswap.screens.setting.twoFAScreen

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.TextView
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.BasePassedActivity
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivitySettingTwofaBinding
import java.io.IOException

class TwoFAActivity :
    BaseMvpActivity<TwoFAView.View, TwoFAView.Presenter, ActivitySettingTwofaBinding>(),
    TwoFAView.View {

    override var mPresenter: TwoFAView.Presenter = TwoFAPresenter()
    override val layoutResource: Int = R.layout.activity_setting_twofa

    override fun initView() {
        super.initView()
        try {
            binding.apply {

                // Set Link Play Store

                // Set Link Play Store
                btnPlaystore.setOnClickListener {
                    val appPackageName = "com.google.android.apps.authenticator2" // getPackageName() from Context or Activity object
                    try {
                        startActivity(
                            Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("market://details?id=$appPackageName")
                            )
                        )
                    } catch (anfe: ActivityNotFoundException) {
                        startActivity(
                            Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")
                            )
                        )
                    }
                }

                // Set Click Copy Code
                layCopycode.setOnClickListener { view: View ->
                    val txtCode = view.findViewById<TextView>(R.id.txt_code)
                    BasePassedActivity.onPassedCopy(UTSwapApp.instance, view, txtCode.text.toString())
                }

                // Set Passed Back
                ivBack.setOnClickListener {
                    onBackPressed()
                }

            }
            // Code
        } catch (error: Exception) {
            // Must be safe
        }
    }
}