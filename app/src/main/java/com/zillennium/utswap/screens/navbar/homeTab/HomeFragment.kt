package com.zillennium.utswap.screens.navbar.homeTab

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.BlurMaskFilter
import android.graphics.MaskFilter
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.zillennium.utswap.Datas.GlobalVariable.SessionVariable
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.FragmentNavbarHomeBinding
import com.zillennium.utswap.models.HomeMenuModel
import com.zillennium.utswap.models.HomeRecentNewsModel
import com.zillennium.utswap.models.HomeWatchlistModel
import com.zillennium.utswap.screens.finance.depositScreen.DepositActivity
import com.zillennium.utswap.screens.finance.transferScreen.TransferActivity
import com.zillennium.utswap.screens.navbar.homeTab.adapter.HomeMenuAdapter
import com.zillennium.utswap.screens.navbar.homeTab.adapter.HomeRecentNewsAdapter
import com.zillennium.utswap.screens.navbar.homeTab.adapter.HomeWatchlistAdapter
import com.zillennium.utswap.screens.navbar.homeTab.bottomSheet.HomeFinanceBottomSheet
import com.zillennium.utswap.screens.finance.withdrawScreen.WithdrawActivity
import com.zillennium.utswap.screens.finance.withdrawScreen.addBank.AddBankActivity
import com.zillennium.utswap.screens.navbar.portfolioTab.PortfolioFragment
import com.zillennium.utswap.screens.navbar.tradeTab.tradeExchangeScreen.TradeExchangeActivity
import com.zillennium.utswap.screens.project.projectScreen.ProjectActivity


class HomeFragment :
    BaseMvpFragment<HomeView.View, HomeView.Presenter, FragmentNavbarHomeBinding>(),
    HomeView.View {

    override var mPresenter: HomeView.Presenter = HomePresenter()
    override val layoutResource: Int = R.layout.fragment_navbar_home
    private var homeAdapter: HomeMenuAdapter? = null
    var blurCondition = true

    private val HomeArrayList = ArrayList<HomeMenuModel>()

    override fun initView() {
        super.initView()
        try {
            binding.apply {

                /* Show or Hide Trading Balance */
                val blurMask: MaskFilter = BlurMaskFilter(50f, BlurMaskFilter.Blur.NORMAL)
                tradingBalance.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
                tradingBalance.paint.maskFilter = blurMask

                buttonShowToggle.setOnClickListener {
                    blurCondition = !blurCondition

                    if (blurCondition) {
                        tradingBalance.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
                        tradingBalance.paint.maskFilter = null
                        eyeImage.setImageResource(R.drawable.ic_baseline_remove_red_eye_24)
                    } else {
                        tradingBalance.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
                        tradingBalance.paint.maskFilter = blurMask
                        eyeImage.setImageResource(R.drawable.ic_baseline_visibility_off_24)
                    }
                }

                /* Home Menu Grid */
                onHomeMenuGrid(SessionVariable.SESSION_STATUS.value.toString().toBoolean())
                SessionVariable.SESSION_STATUS.observe(this@HomeFragment) {
                    if (SessionVariable.SESSION_STATUS.value == true) {
                        onHomeMenuGrid(true)
                    } else {
                        onHomeMenuGrid(false)
                    }
                }

                /* bottom sheet dialog on finance button */



                /* Watchlist Recycle View */
                val locationProject = arrayOf(
                    "Siem Reap 17140",
                    "Muk Kampul 16644",
                    "KT 1665",
                    "New Airport 38Ha",
                    "Siem Reap 17140",
                    "Muk Kampul 16644",
                    "KT 1665",
                    "New Airport 38Ha"
                )
                val lastValue = doubleArrayOf(
                    0.68,
                    1.38,
                    9.06,
                    1.20,
                    19.68,
                    1.68,
                    2.30,
                    1.68
                )
                val changeValue = doubleArrayOf(
                    1.05,
                    -0.33,
                    1.00,
                    -1.68,
                    1.65,
                    -0.50,
                    1.38,
                    -1.78,
                )

                val homeWatchlist = ArrayList<HomeWatchlistModel>()

                for (i in locationProject.indices) {
                    val watchlist = HomeWatchlistModel(
                        locationProject[i],
                        lastValue[i],
                        changeValue[i]
                    )
                    homeWatchlist.add(watchlist)
                }

                rvHomeWatchlist.layoutManager =
                    LinearLayoutManager(UTSwapApp.instance, LinearLayoutManager.HORIZONTAL, false)
                rvHomeWatchlist.adapter = HomeWatchlistAdapter(homeWatchlist, onClickWatch)


                /* Recent News Recycle View */
                val imageNews = arrayOf(
                    "https://utswap.io/Upload/issue/62258e1d402b7.png",
                    "https://utswap.io/Upload/issue/62258e6ce881f.jpg",
                    "https://utswap.io/Upload/issue/62258de873321.jpg",
                    "https://utswap.io/Upload/issue/62258dc331263.jpg",
                    "https://utswap.io/Upload/issue/62258d2401bb7.jpg"
                )
                val titleNews = arrayOf(
                    "Laoka, Mondulkiri Land Plot Special Price",
                    "Siem Reap 17140",
                    "Muk Kampul 16644",
                    "Veng Sreng 2719",
                    "Pochentong 555",
                )
                val dateNews = arrayOf(
                    "05 April 2021",
                    "01 May 2022",
                    "03 June 2022",
                    "02 July 2022",
                    "02 August 2022",
                )

                val homeRecentNewsList = ArrayList<HomeRecentNewsModel>()

                for (i in imageNews.indices) {
                    val recentNews = HomeRecentNewsModel(
                        imageNews[i],
                        titleNews[i],
                        dateNews[i]
                    )
                    homeRecentNewsList.add(recentNews)
                }

                rvHomeNews.layoutManager = LinearLayoutManager(UTSwapApp.instance)
                rvHomeNews.adapter = HomeRecentNewsAdapter(homeRecentNewsList)

            }


        } catch (error: Exception) {
            // Must be safe
        }
    }

    private fun showBottomSheetDialog() {
        HomeFinanceBottomSheet().show(
            requireActivity().supportFragmentManager,
            HomeFinanceBottomSheet.TAG
        )
    }

    //click to move to new screen
    val onclickHome: HomeMenuAdapter.OnclickHome = object : HomeMenuAdapter.OnclickHome {
        override fun ClickDeposit(title: String) {

            when (title.toString()) {
                "Portfolio" -> {
                    activity?.findViewById<com.google.android.material.bottomnavigation.BottomNavigationView>(R.id.nav_view)?.selectedItemId = R.id.navigation_navbar_portfolio
                }
                "Trade" -> {
                    activity?.findViewById<com.google.android.material.bottomnavigation.BottomNavigationView>(R.id.nav_view)?.selectedItemId = R.id.navigation_navbar_trade
                }
                "Projects" -> {
                    val intent = Intent(UTSwapApp.instance, ProjectActivity::class.java)
                    startActivity(intent)
                }
                "Deposit" -> {
                    val intent = Intent(UTSwapApp.instance, DepositActivity::class.java)
                    startActivity(intent)
                }
                "Withdraw" -> {
                    val intent = Intent(UTSwapApp.instance, WithdrawActivity::class.java)
                    startActivity(intent)
                }
                "Transfer" -> {
                    val intent = Intent(UTSwapApp.instance, TransferActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }

    //click to trade exchange
    val onClickWatch: HomeWatchlistAdapter.OnclickWatch = object : HomeWatchlistAdapter.OnclickWatch {
        override fun ClickWatch() {
            val intent: Intent = Intent(UTSwapApp.instance, TradeExchangeActivity::class.java)
            startActivity(intent)
        }

    }

    private fun onHomeMenuGrid(enabled: Boolean){

        HomeArrayList.clear()
        HomeArrayList.add(HomeMenuModel(R.drawable.ic_portfolio, "Portfolio", enabled))
        HomeArrayList.add(HomeMenuModel(R.drawable.ic_trade, "Trade", true))
        HomeArrayList.add(HomeMenuModel(R.drawable.ic_land_ut, "Projects", enabled))
        HomeArrayList.add(HomeMenuModel(R.drawable.ic_deposit, "Deposit", enabled))
        HomeArrayList.add(HomeMenuModel(R.drawable.ic_withdraw, "Withdraw", enabled))
        HomeArrayList.add(HomeMenuModel(R.drawable.ic_transfer, "Transfer", enabled))

        binding.apply {
            rvHomeMenu.layoutManager = GridLayoutManager(UTSwapApp.instance, 3)
            homeAdapter = HomeMenuAdapter(HomeArrayList, R.layout.item_list_home_grid, onclickHome)
            rvHomeMenu.adapter = homeAdapter

            if(enabled){
                imgFinance.imageTintList = ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.color_main))
                txtFinance.setTextColor(ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.color_main)))
                financeBottom.isEnabled = true
                financeBottom.setOnClickListener {
                    showBottomSheetDialog()
                }
            }else{
                imgFinance.imageTintList = ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.gray_999999))
                txtFinance.setTextColor(ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.gray_999999)))
                financeBottom.isEnabled = false
                financeBottom.setOnClickListener {}
            }
        }


    }

}