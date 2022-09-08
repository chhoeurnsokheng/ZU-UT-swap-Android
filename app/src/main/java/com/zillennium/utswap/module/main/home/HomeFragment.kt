package com.zillennium.utswap.module.main.home


import android.content.Intent
import android.graphics.BlurMaskFilter
import android.graphics.MaskFilter
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.zillennium.CheckUserLoginClearToken
import com.zillennium.utswap.Datas.GlobalVariable.SessionVariable
import com.zillennium.utswap.Datas.StoredPreferences.SessionPreferences
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
import com.zillennium.utswap.screens.navbar.navbar.MainActivity
import com.zillennium.utswap.utils.Constants
import com.zillennium.utswap.utils.UtilKt

class HomeFragment : BaseMvpFragment<HomeView.View, HomeView.Presenter, FragmentHomeBinding>(),
    HomeView.View {

    override var mPresenter: HomeView.Presenter = HomePresenter()
    override val layoutResource: Int = R.layout.fragment_home
    private var homeAdapter: HomeMenuAdapter? = null
    var blurCondition = true
    val blurMask: MaskFilter = BlurMaskFilter(50f, BlurMaskFilter.Blur.NORMAL)

    private val HomeArrayList = ArrayList<HomeMenuModel>()
    var bannerLoopingPagerAdapter: BannerLoopingPagerAdapter? = null
    var homeRecentNewsAdapter: HomeRecentNewsAdapter? = null
    var homeWatchlistAdapter: HomeWatchlistAdapter? = null
    var newsList = ArrayList<News.NewsNew>()
    var isUserSwipe = false
    var currentPosition = 0

    override fun initView() {
        super.initView()

        SessionVariable.realTimeWatchList.value = true
        mPresenter.getBanner(requireActivity())
        mPresenter.getNewsHome(requireActivity())
        onSwipeRefresh()
        SessionVariable.SESSION_STATUS.observe(this){
            requestData()
        }
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
                //real time watchlist
                SessionVariable.realTimeWatchList.observe(this@HomeFragment){
                    if(it){
                        rvHomeWatchlist.visibility = View.VISIBLE
                        linearLayoutWatchlist.visibility = View.VISIBLE
                        mPresenter.getWatchListAndBalance(requireActivity())
                        SessionVariable.realTimeWatchList.value = false
                    }
                }

                //check share preference
                SessionVariable.SESSION_STATUS.observe(this@HomeFragment) {

                    txtTotalBalance.visibility = View.VISIBLE
                    linearLayoutBalance.visibility = View.VISIBLE
                    rvHomeWatchlist.visibility = View.VISIBLE
                    linearLayoutWatchlist.visibility = View.VISIBLE
                    if (SessionVariable.SESSION_STATUS.value == true) {
                        mPresenter.getWatchListAndBalance(requireActivity())
                    } else {
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
                        }
                    }

                    imgMenu.setOnClickListener {
                        if (SessionPreferences().SESSION_TOKEN != null) {
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
                    imgNotification.setOnClickListener {
                        SessionVariable.SESSION_STATUS.observe(this@HomeFragment) {
                            if (SessionVariable.SESSION_STATUS.value == true) {
                                val intent =
                                    Intent(UTSwapApp.instance, NotificationActivity::class.java)
                                startActivity(intent)

                            } else {
                                tvBadgeNumber.visibility = View.INVISIBLE
                                val intent = Intent(UTSwapApp.instance, SignInActivity::class.java)
                                startActivity(intent)
                            }
                        }

                    }


                }


            }
        } catch (error: Exception) {

        }
    }
    private fun requestData() {
        mPresenter.getNewsHome(requireActivity())
        mPresenter.getWatchListAndBalance(requireActivity())
        mPresenter.getBanner(requireActivity())

    }

    fun setBadgeNumber() {
        binding.apply {
            SessionVariable.BADGE_NUMBER.observe(this@HomeFragment) {
                if (SessionVariable.BADGE_NUMBER.value?.isNotEmpty() == true && SessionVariable.BADGE_NUMBER.value != "0") {
                    tvBadgeNumber.visibility = View.VISIBLE
                    if (it.toInt() > 9) {
                        tvBadgeNumber.text = "9+"
                    } else {
                        tvBadgeNumber.text = it
                    }
                } else {
                    tvBadgeNumber.visibility = View.INVISIBLE

                }
            }
        }
    }

    fun actionAfterKYC() {
        binding.apply {
            if ((activity as MainActivity).kcyComplete == true) {
                onHomeMenuGrid(true)
            } else {
                onHomeMenuGrid(false)
            }
        }
    }

    private fun checkUserLogin(){
        onHomeMenuGrid(false)
        binding.apply {
            linearLayoutWatchlist.visibility =View.GONE
            linearLayoutBalance.visibility =View.GONE
            txtTotalBalance .visibility =View.GONE
            imgMenu.setOnClickListener {
                val intent = Intent(UTSwapApp.instance, SignInActivity::class.java)
                startActivity(intent)
            }
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
                    NewsDetailActivity.launchNewsDetailsActivity(requireActivity(), id)
                }
            }

            bannerLoopingPagerAdapter?.apply {
                bannerImage.adapter = bannerLoopingPagerAdapter
            }

            indicator.attachTo(bannerImage)
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
        if (data.message== "Please sign in"){
            checkUserLogin()
            CheckUserLoginClearToken.clearTokenExpired()
            mPresenter.getNewsHomeToken(requireContext())
        }
        newsList.clear()
        data.data?.NEW?.forEachIndexed { index, itemWishList ->

            if (index <= 2) {
                newsList.add(itemWishList)
            }
        }
        binding.apply {
            swipeRefresh.isRefreshing = false

            rvHomeNews.layoutManager = LinearLayoutManager(UTSwapApp.instance)
            homeRecentNewsAdapter = HomeRecentNewsAdapter(newsList)
            //data.data?.NEW?.let { HomeRecentNewsAdapter(it) }
            rvHomeNews.adapter = homeRecentNewsAdapter
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

    override fun onGetNewsHomeNoTokenSuccess(data: News.NewsRes) {
        newsList.clear()
        data.data?.NEW?.forEachIndexed { index, itemWishList ->

            if (index <= 2) {
                newsList.add(itemWishList)
            }
        }
        binding.apply {
            swipeRefresh.isRefreshing = false

            rvHomeNews.layoutManager = LinearLayoutManager(UTSwapApp.instance)
            homeRecentNewsAdapter = HomeRecentNewsAdapter(newsList)
            //data.data?.NEW?.let { HomeRecentNewsAdapter(it) }
            rvHomeNews.adapter = homeRecentNewsAdapter
            layNewsLoading.setOnClickListener {
                activity?.findViewById<com.google.android.material.bottomnavigation.BottomNavigationView>(
                    R.id.nav_view
                )?.selectedItemId = R.id.navigation_navbar_news
            }
        }

    }

    override fun onGetNewsHomeNoTokenFail(message: String) {}

    override fun onGetWishListAndBalanceSuccess(data: BannerObj.whistListRes) {
            if (data.message== "Please sign in"){
                checkUserLogin()
            }

        if (data.data?.watch_lists?.size == 0) {
            binding.apply {
                linearLayoutWatchlist.visibility = View.GONE
                rvHomeWatchlist.visibility = View.GONE

                Constants.WatchList.itemWatchList = arrayListOf()
            }
        } else {
            binding.rvHomeWatchlist.apply {
                homeWatchlistAdapter = data.data?.watch_lists?.let { HomeWatchlistAdapter(it) }
                adapter = homeWatchlistAdapter
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                //  addItemDecoration(SpaceDecoration(resources.getDimensionPixelSize(R.dimen.dimen_2)))

                Constants.WatchList.itemWatchList =
                    data.data?.watch_lists as ArrayList<BannerObj.ItemWishList>
            }
        }

        binding.apply {

            if (data.data?.total_user_balance == 0.0) {
                tradingBalance.text = "$ " + "0.00"
            } else {
                tradingBalance.text = "$ " + "" + "" + data.data?.total_user_balance?.let {
                    UtilKt().formatDecimal(
                        "#,###.00",
                        it
                    )
                }


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

     fun onHomeMenuGrid(enabled: Boolean) {

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
                (activity as MainActivity).onRefreshData()
                requestData()

            }
        }
    }


    private fun showBalanceClick() {
        binding.apply {

            if (blurCondition) {
                tradingBalance.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
                tradingBalance.paint.maskFilter = null
                eyeImage.setImageResource(R.drawable.ic_baseline_remove_red_eye_24)
                blurCondition = false

            } else {
                tradingBalance.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
                tradingBalance.paint.maskFilter = blurMask
                eyeImage.setImageResource(R.drawable.ic_baseline_visibility_off_24)
                blurCondition = true

            }
        }
    }
}

