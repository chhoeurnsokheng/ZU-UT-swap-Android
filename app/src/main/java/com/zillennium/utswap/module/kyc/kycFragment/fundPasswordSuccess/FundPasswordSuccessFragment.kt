package com.zillennium.utswap.module.kyc.kycFragment.fundPasswordSuccess

import android.app.Activity
import android.view.KeyEvent
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.LayoutKycFundPasswordSuccessBinding
import com.zillennium.utswap.module.kyc.kycActivity.KYCActivity

class FundPasswordSuccessFragment: BaseMvpFragment<FundPasswordSuccessView.View, FundPasswordSuccessView.Presenter, LayoutKycFundPasswordSuccessBinding>(),
    FundPasswordSuccessView.View {
    override var mPresenter: FundPasswordSuccessView.Presenter = FundPasswordSuccessPresenter()
    override val layoutResource: Int = R.layout.layout_kyc_fund_password_success
    override fun initView() {
        super.initView()
        binding.apply {
            activity?.let { activity ->
                (activity as AppCompatActivity?)?.apply {
                    setSupportActionBar(binding.includeLayout.tb)
                    supportActionBar?.setDisplayHomeAsUpEnabled(false)
                    supportActionBar?.setDisplayShowTitleEnabled(false)
                }
                includeLayout.apply {
                    tbTitle.setText(R.string.success)
                    tbTitle.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.simple_green))
                }
            }
            btnDone.setOnClickListener {
                findNavController().navigate(R.id.action_fundPasswordSuccess_to_KycApplicationKycFragment)

            }

        }
    }

}