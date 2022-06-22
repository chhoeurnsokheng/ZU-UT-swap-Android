package com.zillennium.utswap.screens.account.referralInformationScreen

import android.content.ClipData
import android.content.Context
import android.os.Build
import android.text.ClipboardManager
import android.widget.Toast
import com.zillennium.utswap.R
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivityAccountReferralInformationBinding


class ReferralInformationActivity :
    BaseMvpActivity<ReferralInformationView.View, ReferralInformationView.Presenter, ActivityAccountReferralInformationBinding>(),
    ReferralInformationView.View {

    override var mPresenter: ReferralInformationView.Presenter = ReferralInformationPresenter()
    override val layoutResource: Int = R.layout.activity_account_referral_information

    override fun initView() {
        super.initView()
        try {
            binding.apply {

                imgClose.setOnClickListener {
                    finish()
                }

                layReferralId.setOnClickListener {
                    val sdk = Build.VERSION.SDK_INT
                    if (sdk < Build.VERSION_CODES.HONEYCOMB) {
                        val clipboard =
                            getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                        clipboard.text = "PQRSTU"
                    } else {
                        val clipboard =
                            getSystemService(Context.CLIPBOARD_SERVICE) as android.content.ClipboardManager
                        val clip = ClipData.newPlainText("text label", "PQRSTU")
                        clipboard.setPrimaryClip(clip)
                    }
                    Toast.makeText(this@ReferralInformationActivity,"Reference ID was Copied",Toast.LENGTH_SHORT).show()
                }

                layReferralLink.setOnClickListener {
                    val sdk = Build.VERSION.SDK_INT
                    if (sdk < Build.VERSION_CODES.HONEYCOMB) {
                        val clipboard =
                            getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                        clipboard.text = "https://utswap.io/referral/PQRSTU"
                    } else {
                        val clipboard =
                            getSystemService(Context.CLIPBOARD_SERVICE) as android.content.ClipboardManager
                        val clip = ClipData.newPlainText("text label", "https://utswap.io/referral/PQRSTU")
                        clipboard.setPrimaryClip(clip)
                    }
                    Toast.makeText(this@ReferralInformationActivity,"Reference Link was Copied",Toast.LENGTH_SHORT).show()
                }

            }
            // Code
        } catch (error: Exception) {
            // Must be safe
        }
    }
}