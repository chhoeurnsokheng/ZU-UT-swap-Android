package com.zillennium.utswap.module.main.home


import android.content.Intent
import android.graphics.BlurMaskFilter
import android.graphics.MaskFilter
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.zillennium.utswap.Datas.GlobalVariable.SessionVariable
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.FragmentHomeBinding
import com.zillennium.utswap.models.HomeMenuModel
import com.zillennium.utswap.models.HomeRecentNewsModel
import com.zillennium.utswap.models.HomeTabSlideImageModel
import com.zillennium.utswap.models.HomeWatchlistModel
import com.zillennium.utswap.module.account.accountScreen.AccountActivity
import com.zillennium.utswap.module.finance.depositScreen.DepositActivity
import com.zillennium.utswap.module.finance.transferScreen.TransferActivity
import com.zillennium.utswap.module.finance.withdrawScreen.WithdrawActivity
import com.zillennium.utswap.module.main.home.adapter.HomeMenuAdapter
import com.zillennium.utswap.module.main.home.adapter.HomeRecentNewsAdapter
import com.zillennium.utswap.module.main.home.adapter.HomeTabSlideImageAdapter
import com.zillennium.utswap.module.main.home.adapter.HomeWatchlistAdapter
import com.zillennium.utswap.module.main.home.bottomSheet.HomeFinanceBottomSheet
import com.zillennium.utswap.module.main.news.newsDetail.NewsDetailActivity
import com.zillennium.utswap.module.main.trade.tradeExchangeScreen.TradeExchangeActivity
import com.zillennium.utswap.module.project.projectScreen.ProjectActivity
import com.zillennium.utswap.module.security.securityActivity.signInScreen.SignInActivity
import com.zillennium.utswap.module.system.notification.NotificationActivity


class HomeFragment :
    BaseMvpFragment<HomeView.View, HomeView.Presenter, FragmentHomeBinding>(),
    HomeView.View {

    override var mPresenter: HomeView.Presenter = HomePresenter()
    override val layoutResource: Int = R.layout.fragment_home
    private var homeAdapter: HomeMenuAdapter? = null
    var blurCondition = true
    val blurMask: MaskFilter = BlurMaskFilter(50f, BlurMaskFilter.Blur.NORMAL)

    private val HomeArrayList = ArrayList<HomeMenuModel>()

    override fun initView() {
        super.initView()
        try {
            binding.apply {

                //check share preference
                SessionVariable.SESSION_STATUS.observe(this@HomeFragment) {
                    if(SessionVariable.SESSION_STATUS.value == true){
                        imgMenu.setOnClickListener {
                            val intent = Intent(UTSwapApp.instance, AccountActivity::class.java)
                            startActivity(intent)
                            requireActivity().overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
                        }
                    }else{
                        imgMenu.setOnClickListener {
                            val intent = Intent(UTSwapApp.instance, SignInActivity::class.java)
                            startActivity(intent)
                        }
                    }
                }

                imgMenu.setOnClickListener {
                    val intent = Intent(UTSwapApp.instance, SignInActivity::class.java)
                    startActivity(intent)
                }

                imgNotification.setOnClickListener {
                    val intent = Intent(UTSwapApp.instance, NotificationActivity::class.java)
                    startActivity(intent)
                }

                /* Show or Hide Trading Balance */
                tradingBalance.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
                tradingBalance.paint.maskFilter = blurMask

                buttonShowToggle.setOnClickListener {
                    showBalanceClick()
                }
                tradingBalance.setOnClickListener {
                    showBalanceClick()
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
                /* Image Slider with View Pager and TabLayout */
                val img_Slider = arrayOf(
                    "https://utswap.io/Upload/article/62a956f59997f.jpg",
                    "https://utswap.io/Upload/article/62b28f4e18eb0.jpg",
                    "https://utswap.io/Upload/article/62bad61c5d0e5.jpg",

                    )
                val homeTabSlideImage = arrayListOf<HomeTabSlideImageModel>()

                for (i in img_Slider.indices) {
                    val homeTabImage = HomeTabSlideImageModel(
                        img_Slider[i],
                    )
                    homeTabSlideImage.add(homeTabImage)
                }

                val adapterHomeSlideImage = HomeTabSlideImageAdapter(homeTabSlideImage, onclickAdapter)
                vpSlideImage.adapter = adapterHomeSlideImage

                /*  TabLayout dot */
                TabLayoutMediator(tabLayoutDot, vpSlideImage) { tab, position ->

                }.attach()
                vpSlideImage.registerOnPageChangeCallback(object :
                    ViewPager2.OnPageChangeCallback() {})


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

                rvHomeWatchlist.layoutManager = LinearLayoutManager(UTSwapApp.instance, LinearLayoutManager.HORIZONTAL, false)
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
                rvHomeNews.adapter = HomeRecentNewsAdapter(homeRecentNewsList, onClickNews)


                layNewsLoading.setOnClickListener {
                    activity?.findViewById<com.google.android.material.bottomnavigation.BottomNavigationView>(
                        R.id.nav_view
                    )?.selectedItemId = R.id.navigation_navbar_news
                }

            }


        } catch (error: Exception) {
            // Must be safe
        }
    }

    //click to move to new screen
    val onclickHome: HomeMenuAdapter.OnclickHome = object : HomeMenuAdapter.OnclickHome {
        override fun ClickDeposit(title: String) {

            when (title.toString()) {
                "Projects" -> {
                    val intent = Intent(UTSwapApp.instance, ProjectActivity::class.java)
                    startActivity(intent)
                }
                "Finance" -> {
                    HomeFinanceBottomSheet().show(
                        requireActivity().supportFragmentManager,
                        HomeFinanceBottomSheet.TAG
                    )
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

    // Onclick Slide Image
    private val onclickAdapter: HomeTabSlideImageAdapter.OnclickAdapter =
        object : HomeTabSlideImageAdapter.OnclickAdapter {
            override fun onClickSlidetab(homeTabSlideImageModel: HomeTabSlideImageModel) {
                val intent = Intent(UTSwapApp.instance, NewsDetailActivity::class.java)
                startActivity(intent)
            }
        }


    //click to trade exchange
    val onClickWatch: HomeWatchlistAdapter.OnclickWatch =
        object : HomeWatchlistAdapter.OnclickWatch {
            override fun ClickWatch() {
                val intent: Intent = Intent(UTSwapApp.instance, TradeExchangeActivity::class.java)
                startActivity(intent)
            }
        }

    val onClickNews: HomeRecentNewsAdapter.onclickNews =
        object : HomeRecentNewsAdapter.onclickNews {
            override fun ClickNews() {
                val intent: Intent = Intent(UTSwapApp.instance, NewsDetailActivity::class.java)
                startActivity(intent)
            }

        }

    private fun onHomeMenuGrid(enabled: Boolean) {

        HomeArrayList.clear()
//        HomeArrayList.add(HomeMenuModel(R.drawable.ic_portfolio, "Portfolio", enabled))
//        HomeArrayList.add(HomeMenuModel(R.drawable.ic_trade, "Trade", true))
        HomeArrayList.add(HomeMenuModel(R.drawable.ic_deposit, "Deposit", enabled))
        HomeArrayList.add(HomeMenuModel(R.drawable.ic_withdraw, "Withdraw", enabled))
        HomeArrayList.add(HomeMenuModel(R.drawable.ic_transfer, "Transfer", enabled))
        HomeArrayList.add(HomeMenuModel(R.drawable.ic_finance, "Finance", enabled))
        HomeArrayList.add(HomeMenuModel(R.drawable.ic_land_ut, "Projects", enabled))

        binding.apply {
            rvHomeMenu.layoutManager = GridLayoutManager(UTSwapApp.instance, 3)
            homeAdapter = HomeMenuAdapter(HomeArrayList, R.layout.item_list_home_grid, onclickHome)
            rvHomeMenu.adapter = homeAdapter
        }


    }

    private fun showBalanceClick(){
        binding.apply {

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
    }
}