package com.zillennium.utswap.screens.setting.creditCardScreen

import android.content.Intent
import android.view.View
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.BasePassedActivity
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivitySettingCreditCardBinding
import com.zillennium.utswap.screens.setting.addCardScreen.AddCardActivity
import java.io.IOException

class CreditCardActivity :
    BaseMvpActivity<CreditCardView.View, CreditCardView.Presenter, ActivitySettingCreditCardBinding>(),
    CreditCardView.View {

    override var mPresenter: CreditCardView.Presenter = CreditCardPresenter()
    override val layoutResource: Int = R.layout.activity_setting_credit_card

    override fun initView() {
        super.initView()
        try {
            binding.apply {

                btnAddCard.setOnClickListener {
                    val intent = Intent(UTSwapApp.instance, AddCardActivity::class.java)
                    startActivity(intent)
                }

                // Set Passed Back
                ivBack.setOnClickListener {
                    onBackPressed()
                }

            }
            // Code
        } catch (error: IOException) {
            // Must be safe
        }
    }
}