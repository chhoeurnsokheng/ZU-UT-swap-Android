package com.zillennium.utswap.module.main.home


import android.content.Intent
import android.graphics.BlurMaskFilter
import android.graphics.MaskFilter
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.zillennium.utswap.Datas.GlobalVariable.SessionVariable
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.FragmentHomeBinding
import com.zillennium.utswap.models.HomeMenuModel
import com.zillennium.utswap.models.home.BannerObj
import com.zillennium.utswap.models.newsService.News
import com.zillennium.utswap.module.account.accountScreen.AccountActivity
import com.zillennium.utswap.module.finance.depositScreen.DepositActivity
import com.zillennium.utswap.module.finance.transferScreen.TransferActivity
import com.zillennium.utswap.module.finance.withdrawScreen.WithdrawActivity
import com.zillennium.utswap.module.main.home.adapter.HomeMenuAdapter
import com.zillennium.utswap.module.main.home.adapter.HomeRecentNewsAdapter
import com.zillennium.utswap.module.main.home.adapter.HomeWatchlistAdapter
import com.zillennium.utswap.module.main.home.bottomSheet.HomeFinanceBottomSheet
import com.zillennium.utswap.module.main.news.newsDetail.NewsDetailActivity
import com.zillennium.utswap.module.project.projectScreen.ProjectActivity
import com.zillennium.utswap.module.security.securityActivity.signInScreen.SignInActivity
import com.zillennium.utswap.module.system.notification.NotificationActivity
import com.zillennium.utswap.utils.formatter.NumberFormatter


class HomeFragment() : BaseMvpFragment<HomeView.View, HomeView.Presenter, FragmentHomeBinding>(),
    HomeView.View {

    override var mPresenter: HomeView.Presenter = HomePresenter()
    override val layoutResource: Int = R.layout.fragment_home
    private var homeAdapter: HomeMenuAdapter? = null
    var blurCondition = true
    val blurMask: MaskFilter = BlurMaskFilter(50f, BlurMaskFilter.Blur.NORMAL)

    private val HomeArrayList = ArrayList<HomeMenuModel>()
    var bannerLoopingPagerAdapter: BannerLoopingPagerAdapter? = null
    var homeRecentNewsAdapter :HomeRecentNewsAdapter? = null
    var homeWatchlistAdapter:HomeWatchlistAdapter? = null
    var isUserSwipe = false
    var currentPosition = 0


    override fun initView() {
        super.initView()
        mPresenter.getBanner(requireActivity())
        onSwipeRefresh()
        binding.apply {
            swipeRefresh.setColorSchemeColors(
                ContextCompat.getColor(
                    UTSwapApp.instance,
                    R.color.primary
                )
            )
        }
        try {
            binding.apply {

                //check share preference
                SessionVariable.SESSION_STATUS.observe(this@HomeFragment) {

                    txtTotalBalance.visibility = View.VISIBLE
                    linearLayoutBalance.visibility = View.VISIBLE
                    rvHomeWatchlist.visibility = View.VISIBLE
                    linearLayoutWatchlist.visibility = View.VISIBLE
                    if (SessionVariable.SESSION_STATUS.value == true) {
                        imgMenu.setOnClickListener {
                            val intent = Intent(UTSwapApp.instance, AccountActivity::class.java)
                            startActivity(intent)
                            requireActivity().overridePendingTransition(
                                R.anim.slide_in_left,
                                R.anim.slide_out_right
                            )
                        }
                    } else {
                        txtTotalBalance.visibility = View.GONE
                        linearLayoutBalance.visibility = View.GONE
                        rvHomeWatchlist.visibility = View.GONE
                        linearLayoutWatchlist.visibility = View.GONE
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
                        txtCountNotification.visibility = View.VISIBLE
                        imgNotification.setOnClickListener {
                            val intent =
                                Intent(UTSwapApp.instance, NotificationActivity::class.java)
                            startActivity(intent)
                        }
                    } else {
                        onHomeMenuGrid(false)
                        txtCountNotification.visibility = View.INVISIBLE
                        imgNotification.setOnClickListener {
                            val intent = Intent(UTSwapApp.instance, SignInActivity::class.java)
                            startActivity(intent)
                        }
                    }
                }

            }


        } catch (error: Exception) {
            // Must be safe
        }
    }

    override fun onGetBannerSuccess(data: BannerObj.Banner) {
        mPresenter.getNewsHome(requireActivity())

        binding.apply {
            swipeRefresh.isRefreshing = false
            bannerLoopingPagerAdapter = object : BannerLoopingPagerAdapter(
                data.data, false
            ) {
                override fun onBannerItemClick(id: String, position: Int) {
                    if (position != null) {
                        NewsDetailActivity.launchNewsDetailsActivity(requireActivity(), id)
                    }

                }
            }

            bannerLoopingPagerAdapter?.apply {
                bannerImage.adapter = bannerLoopingPagerAdapter
            }

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

                            } else if (currentPosition > position) {

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


        }
    }

    override fun onGetBannerFail(message: String) {
        binding.apply {
            swipeRefresh.isRefreshing = false
        }
    }

    override fun onGetNewsHomeSuccess(data: News.NewsRes) {

        binding.apply {
            swipeRefresh.isRefreshing = false
            mPresenter.getWishListAndBalance(requireActivity())
            rvHomeNews.layoutManager = LinearLayoutManager(UTSwapApp.instance)
            homeRecentNewsAdapter  = data.data?.NEW?.let { HomeRecentNewsAdapter(it) }
            rvHomeNews.adapter = homeRecentNewsAdapter
            homeRecentNewsAdapter?.notifyDataSetChanged()
            layNewsLoading.setOnClickListener {
                activity?.findViewById<com.google.android.material.bottomnavigation.BottomNavigationView>(
                    R.id.nav_view
                )?.selectedItemId = R.id.navigation_navbar_news
            }
        }

    }

    override fun onGetNewsHomeFail(message: String) {
        binding.apply {
            swipeRefresh.isRefreshing = false
        }
    }

    override fun onGetWishListAndBalanceSuccess(data: BannerObj.whistListRes) {

        if (data.data?.watch_lists?.isEmpty() == true) {
            binding.apply {
                linearLayoutWatchlist.visibility = View.GONE
                rvHomeWatchlist.visibility = View.GONE
            }
        } else {
            binding.apply {
                homeWatchlistAdapter = data.data?.watch_lists?.let { HomeWatchlistAdapter(it) }
                rvHomeWatchlist.layoutManager = LinearLayoutManager(UTSwapApp.instance, LinearLayoutManager.HORIZONTAL, false)
                rvHomeWatchlist.adapter = homeWatchlistAdapter
            }
        }

        binding.apply {

            if (data.data?.total_user_balance ==0.0){
                tradingBalance.text =  "$" + "0.00"
            }else{
                tradingBalance.text =  "$" + NumberFormatter.formatNumber(data.data?.total_user_balance?: 0.0)

            }

            swipeRefresh.isRefreshing = false
        }
    }

    override fun onGetWishListAndBalanceFail(message: String) {
        binding.apply {
            swipeRefresh.isRefreshing = false
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

    private fun onSwipeRefresh() {
        binding.apply {
            swipeRefresh.setOnRefreshListener {
                mPresenter.getNewsHome(requireActivity())
                mPresenter.getBanner(requireActivity())
                mPresenter.getBanner(requireActivity())
            }
        }
    }

    private fun showBalanceClick() {
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

