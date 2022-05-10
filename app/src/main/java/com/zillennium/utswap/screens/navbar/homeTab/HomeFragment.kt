package com.zillennium.utswap.screens.navbar.homeTab

import com.zillennium.utswap.R
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.FragmentNavbarHomeBinding

class HomeFragment :
    BaseMvpFragment<HomeView.View, HomeView.Presenter, FragmentNavbarHomeBinding>(),
    HomeView.View {

    override var mPresenter: HomeView.Presenter = HomePresenter()
    override val layoutResource: Int = R.layout.fragment_navbar_home

    override fun initView() {
        super.initView()
        try {
            binding.apply {



            }

        } catch (error: Exception) {
            // Must be safe
        }
    }
}