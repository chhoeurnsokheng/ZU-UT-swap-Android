package com.zillennium.utswap.screens.finance.depositActivity.fragment

import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.FragmentFinanceNavbarDepositBinding
import com.zillennium.utswap.models.DepositModel
import com.zillennium.utswap.screens.finance.depositActivity.adapter.DepositAdapter
import com.zillennium.utswap.screens.navbar.homeTab.HomeFragment
import com.zillennium.utswap.screens.navbar.portfolioTab.PortfolioFragment
import java.util.*

class DepositFragment :
    BaseMvpFragment<DepositView.View, DepositView.Presenter, FragmentFinanceNavbarDepositBinding>(),
    DepositView.View {
    override var mPresenter: DepositView.Presenter = DepositPresenter()
    override val layoutResource: Int = R.layout.fragment_finance_navbar_deposit



    override fun initView() {
        super.initView()
        try {
            binding.apply {
                imgMenu.setOnClickListener {
                    val intent = Intent(this@DepositFragment.requireActivity(), HomeFragment::class.java)
                    startActivity(intent)
                }

                imgNotification.setOnClickListener {

                }

            }

        } catch (error: Exception) {
            // Must be safe
        }
    }
}