package com.zillennium.utswap.screens.navbar.homeTab

import android.content.Intent
import android.graphics.BlurMaskFilter
import android.graphics.MaskFilter
import android.icu.text.CaseMap
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.FragmentNavbarHomeBinding
import com.zillennium.utswap.screens.navbar.homeTab.adapter.HomeMenuAdapter
import com.zillennium.utswap.screens.navbar.homeTab.adapter.HomeRecentNewsAdapter
import com.zillennium.utswap.screens.navbar.homeTab.adapter.HomeWatchlistAdapter
import com.zillennium.utswap.screens.navbar.homeTab.bottomSheet.HomeFinanceBottomSheet
import com.zillennium.utswap.models.HomeMenuModel
import com.zillennium.utswap.models.HomeRecentNewsModel
import com.zillennium.utswap.models.HomeWatchlistModel
import com.zillennium.utswap.screens.finance.depositActivity.DepositActivity
import com.zillennium.utswap.screens.navbar.portfolioTab.PortfolioFragment


class HomeFragment :
    BaseMvpFragment<HomeView.View, HomeView.Presenter, FragmentNavbarHomeBinding>(),
    HomeView.View {

    override var mPresenter: HomeView.Presenter = HomePresenter()
    override val layoutResource: Int = R.layout.fragment_navbar_home
    private var homeAdapter: HomeMenuAdapter? = null
    var blurCondition = true

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
                rvHomeMenu.visibility = View.VISIBLE

                val imageHome = arrayOf(
                    R.drawable.ic_portfolio,
                    R.drawable.ic_trade,
                    R.drawable.ic_news,
                    R.drawable.ic_deposit,
                    R.drawable.ic_withdraw,
                    R.drawable.ic_transfer
                )
                val titleHome = arrayOf(
                    "Portfolio",
                    "Trade",
                    "News",
                    "Deposit",
                    "Withdraw",
                    "Transfer"
                )
                val HomeArrayList = ArrayList<HomeMenuModel>()

                for (i in imageHome.indices) {
                    val home = HomeMenuModel(
                        imageHome[i],
                        titleHome[i]
                    )
                    HomeArrayList.add(home)
                }

                rvHomeMenu.layoutManager = GridLayoutManager(UTSwapApp.instance, 3)
                homeAdapter = HomeMenuAdapter(HomeArrayList, R.layout.item_list_home_grid, onclickHome)
                rvHomeMenu.adapter = homeAdapter
//                rvHomeMenu.adapter = HomeMenuAdapter(HomeArrayList, R.layout.item_list_home_grid)


                /* bottom sheet dialog on finance button */
                financeBottom.setOnClickListener {
                    showBottomSheetDialog()
                }

//                rvHomeMenu.setOnClickListener {
//                    val intent = Intent(this@HomeFragment.requireContext(), DepositActivity::class.java)
//                    startActivity(intent)
//                }

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
                rvHomeWatchlist.adapter = HomeWatchlistAdapter(homeWatchlist)


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
        override fun ClickDeposit(title: String?) {

            when (title.toString()) {
                "Portfolio" -> {}
                "Trade" -> {}
                "News" -> {}
                "Deposit" -> {val intent = Intent(UTSwapApp.instance, DepositActivity::class.java)
                startActivity(intent)}
                "Withdraw" -> {}
                "Transfer" -> {}
                else -> {
                    val intent = Intent(UTSwapApp.instance, PortfolioFragment::class.java)
                    startActivity(intent)
                }
            }
        }
    }
}