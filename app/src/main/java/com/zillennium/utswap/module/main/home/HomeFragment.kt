package com.zillennium.utswap.module.main.home


import android.content.Intent
import android.graphics.BlurMaskFilter
import android.graphics.MaskFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.zillennium.utswap.Datas.GlobalVariable.SessionVariable
import com.zillennium.utswap.Datas.StoredPreferences.SessionPreferences
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


class HomeFragment : BaseMvpFragment<HomeView.View, HomeView.Presenter, FragmentHomeBinding>(),
    HomeView.View {

    override var mPresenter: HomeView.Presenter = HomePresenter()
    override val layoutResource: Int = R.layout.fragment_home
    private var homeAdapter: HomeMenuAdapter? = null
    var blurCondition = true
    val blurMask: MaskFilter = BlurMaskFilter(50f, BlurMaskFilter.Blur.NORMAL)

    private val HomeArrayList = ArrayList<HomeMenuModel>()
    var bannerLoopingPagerAdapter: BannerLoopingPagerAdapter? = null
    var isUserSwipe = false
    var currentPosition = 0

    override fun initView() {
        super.initView()
        try {
            binding.apply {

                //check share preference
                SessionVariable.SESSION_STATUS.observe(this@HomeFragment) {

                    txtTotalBalance.visibility = View.VISIBLE
                    linearLayoutBalance.visibility = View.VISIBLE
                    rvHomeWatchlist.visibility = View.VISIBLE
                    linearLayoutWatchlist.visibility = View.VISIBLE
                    if(SessionVariable.SESSION_STATUS.value == true){

                    }else{
                        txtTotalBalance.visibility = View.GONE
                        linearLayoutBalance.visibility = View.GONE
                        rvHomeWatchlist.visibility = View.GONE
                        linearLayoutWatchlist.visibility = View.GONE

                    }
                }
                imgMenu.setOnClickListener {
                    if(SessionPreferences().SESSION_TOKEN != null) {
                        val intent = Intent(UTSwapApp.instance, AccountActivity::class.java)
                        startActivity(intent)
                        requireActivity().overridePendingTransition(
                            R.anim.slide_in_left,
                            R.anim.slide_out_right
                        )
                    } else {
                        val intent = Intent(UTSwapApp.instance, SignInActivity::class.java)
                        startActivity(intent)

                    }
                }
                /*imgMenu.setOnClickListener {
                    val intent = Intent(UTSwapApp.instance, SignInActivity::class.java)
                    startActivity(intent)
                }*/



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
                    if (SessionVariable.SESSION_STATUS.value == true && (SessionPreferences().SESSION_KYC == true)) {
                        onHomeMenuGrid(true)
                        txtCountNotification.visibility =View.VISIBLE
                        imgNotification.setOnClickListener {
                            val intent = Intent(UTSwapApp.instance, NotificationActivity::class.java)
                            startActivity(intent)
                        }
                    } else {
                        onHomeMenuGrid(false)
                        txtCountNotification.visibility =View.INVISIBLE
                        imgNotification.setOnClickListener {
                            val intent = Intent(UTSwapApp.instance, SignInActivity::class.java)
                            startActivity(intent)
                        }
                    }
                }

               val   homeTabSlideImage = arrayListOf(
                    HomeTabSlideImageModel(  "https://utswap.io/Upload/article/62b28f4e18eb0.jpg"),
                    HomeTabSlideImageModel("https://utswap.io/Upload/article/62bad61c5d0e5.jpg"),
                   HomeTabSlideImageModel(  "https://utswap.io/Upload/article/62b28f4e18eb0.jpg"),
                   HomeTabSlideImageModel(  "https://utswap.io/Upload/article/62b28f4e18eb0.jpg"),
                   HomeTabSlideImageModel(  "https://utswap.io/Upload/article/62b28f4e18eb0.jpg"),
                )

                bannerLoopingPagerAdapter = object : BannerLoopingPagerAdapter(
                    requireActivity(),homeTabSlideImage,false
                ){
                    override fun onBannerItemClick(data: HomeTabSlideImageModel, position: Int) {}

                }

                bannerLoopingPagerAdapter?.apply {
                    bannerImage.adapter = bannerLoopingPagerAdapter
//                    AspectRatioUtil.apply(
//                        10,
//                        22,
//                        UtilConvert.convertDpToPixel(32f, UTSwapApp.instance).roundToInt(),
//                        requireActivity(),
//                        bannerImage
//                    )
                }

//                indicator.attachToPager(bannerImage)
//                indicator.visibleDotCount = if ((homeTabSlideImage.size ?: 0) > 6) { 7
//                } else {
//                    if ((homeTabSlideImage.size)?.rem(2) ?: 0 == 0) {
//                        (homeTabSlideImage.size ?: 2) - 1
//                    } else {
//                        homeTabSlideImage.size ?: 0
//                    }
//                }
                indicator.setViewPager(bannerImage)
                bannerImage.setLooperPic(true)

                bannerImage.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                    override fun onPageScrolled(
                        position: Int,
                        positionOffset: Float,
                        positionOffsetPixels: Int,
                    ) {
                    }

                    override fun onPageSelected(position: Int) {
                        try {
                            if (isUserSwipe) {
                                if (currentPosition < position) {
//                                    homeTabSlideImage.get(position)?.title?.apply {
//                                      //  callback.onSwipeBanner("LEFT-$this")
//                                    }
                                } else if (currentPosition > position) {
//                                    homeTabSlideImage.get(position)?.title?.apply {
//                                       // callback.onSwipeBanner("RIGHT-$this")
//                                    }
                                }
                                isUserSwipe = false
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }


                    }

                    override fun onPageScrollStateChanged(state: Int) {

                    }
                })
                
                bannerImage.setOnTouchListener { _, event ->
                    when (event.action) {
                        MotionEvent.ACTION_DOWN -> {

                        }
                        MotionEvent.ACTION_UP -> {
                            isUserSwipe = true
                        }
                    }
                    false
                }




                /* bottom sheet dialog on finance button */


                /* Watchlist Recycle View */
                val locationProject = arrayOf(
                    "Siem Reap 17140 Siem Reap 17140",
                    "Muk Kampul 16644",
                    "KT",
                    "New Airport",
                    "Siem Reap 17140",
                    "Muk Kampul 16644",
                    "KT 1665",
                    "New Airport 38Ha"
                )
                val lastValue = doubleArrayOf(
                    3.68,
                    13.38,
                    29.06,
                    41.20,
                    19.64,
                    1.68,
                    2.30,
                    1.68
                )
                val changeValue = doubleArrayOf(
                    1.05,
                    -20.33,
                    12.00,
                    -21.68,
                    12.65,
                    -20.50,
                    12.38,
                    -21.78,
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

            when (title) {
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

        binding.apply {
            HomeArrayList.clear()
            HomeArrayList.add(HomeMenuModel(R.drawable.ic_deposit, "Deposit", enabled))
            HomeArrayList.add(HomeMenuModel(R.drawable.ic_withdraw, "Withdraw", enabled))
            HomeArrayList.add(HomeMenuModel(R.drawable.ic_transfer, "Transfer", enabled))
            HomeArrayList.add(HomeMenuModel(R.drawable.ic_finance, "Finance", enabled))
            HomeArrayList.add(HomeMenuModel(R.drawable.ic_land_ut, "Projects", true))

            homeAdapter = HomeMenuAdapter(HomeArrayList, R.layout.item_list_home_grid, onclickHome)

            rvHomeMenu.layoutManager = GridLayoutManager(UTSwapApp.instance, 3)

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

