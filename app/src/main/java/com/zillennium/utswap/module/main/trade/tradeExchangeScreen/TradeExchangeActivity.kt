package com.zillennium.utswap.module.main.trade.tradeExchangeScreen

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Paint
import android.os.Handler
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.view.View
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.zillennium.utswap.Datas.GlobalVariable.SessionVariable
import com.zillennium.utswap.Datas.StoredPreferences.SessionPreferences
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivityTradeExchangeBinding
import com.zillennium.utswap.models.TradeModel
import com.zillennium.utswap.models.tradingList.TradingList
import com.zillennium.utswap.models.userService.User
import com.zillennium.utswap.module.kyc.kycActivity.KYCActivity
import com.zillennium.utswap.module.main.trade.tradeExchangeScreen.dialog.*
import com.zillennium.utswap.module.main.trade.tradeExchangeScreen.fragment.Transactions.TransactionsFragment
import com.zillennium.utswap.module.main.trade.tradeExchangeScreen.fragment.allTransactions.AllTransactionsFragment
import com.zillennium.utswap.module.main.trade.tradeExchangeScreen.fragment.chart.ChartFragment
import com.zillennium.utswap.module.main.trade.tradeExchangeScreen.fragment.orderBook.OrderBookFragment
import com.zillennium.utswap.module.main.trade.tradeExchangeScreen.fragment.orders.OrdersFragment
import com.zillennium.utswap.module.project.projectInfoScreen.ProjectInfoActivity
import com.zillennium.utswap.module.security.securityActivity.signInScreen.SignInActivity
import com.zillennium.utswap.utils.Constants
import com.zillennium.utswap.utils.DecimalDigitsInputFilter
import com.zillennium.utswap.utils.groupingSeparator
import com.zillennium.utswap.utils.groupingSeparatorInt
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class TradeExchangeActivity :
    BaseMvpActivity<TradeExchangeView.View, TradeExchangeView.Presenter, ActivityTradeExchangeBinding>(),
    TradeExchangeView.View {

    override var mPresenter: TradeExchangeView.Presenter = TradeExchangePresenter()
    override val layoutResource: Int = R.layout.activity_trade_exchange

    val NUM_PAGES = 2
    private var pageAdapter: FragmentStateAdapter? = null
    private var pageTableAdapter: FragmentStateAdapter? = null
    private var buySellBottomSheet: BuyAndSellBottomSheetDialog? = null
    private var kycSubmit: Boolean? = false
    private var kycComplete: Boolean? = false
    private var positionFragment = 0

    val NUM_PAGES_TABLE = 3
    var remember: Boolean? = null

    var click = true
    private var mBottomSheetBehavior: BottomSheetBehavior<*>? = null

    override var fetchTradeDetailData: MutableLiveData<TradingList.TradingListSummary> = MutableLiveData()

    var handler = Handler()
    var runnable: Runnable? = null
    var delay = 1000

    companion object {
        fun launchTradeExchangeActivity(context: Context, trade: TradeModel?) {
            val intent = Intent(context, TradeExchangeActivity::class.java)
            intent.putExtra(Constants.TradeExchange.ProjectName, trade?.project_name)
            intent.putExtra(Constants.TradeExchange.MarketName, trade?.market_name)
            intent.putExtra(Constants.TradeExchange.ProjectId, trade?.project_id)
            intent.putExtra(Constants.TradeExchange.MarketId, trade?.market_id)
            Constants.OrderBookTable.marketNameOrderBook = trade?.market_name.toString()
            Constants.OrderBookTable.marketIdChart = trade?.market_id.toString()
            Constants.OrderBookTable.projectName = trade?.project_name.toString()
            context.startActivity(intent)
        }
        fun launchTradeExchangeActivityFromWishList(context: Context, projectName: String?, marketName: String?,projectId: String?,marketId: String?) {
            val intent = Intent(context, TradeExchangeActivity::class.java)
            intent.putExtra(Constants.TradeExchange.ProjectName, projectName)
            intent.putExtra(Constants.TradeExchange.MarketName,marketName)
            intent.putExtra(Constants.TradeExchange.ProjectId, projectId)
            Constants.OrderBookTable.marketNameOrderBook = marketName.toString()
            Constants.OrderBookTable.projectName = projectName.toString()
            Constants.OrderBookTable.marketIdChart = marketId.toString()
            context.startActivity(intent)
        }

        fun launchTradeExchangeActivityFromProjectDetail(context: Context, projectName: String?, marketName: String?, projectId: String?,marketId: String?){
            val intent = Intent(context, TradeExchangeActivity::class.java)
            intent.putExtra(Constants.TradeExchange.ProjectName, projectName)
            intent.putExtra(Constants.TradeExchange.MarketName,marketName)
            intent.putExtra(Constants.TradeExchange.ProjectId, projectId)
            Constants.OrderBookTable.marketNameOrderBook = marketName.toString()
            Constants.OrderBookTable.projectName = projectName.toString()
            Constants.OrderBookTable.marketIdChart = marketId.toString()
            context.startActivity(intent)
        }
    }

    @SuppressLint("SetTextI18n", "ClickableViewAccessibility")
    override fun initView() {
        super.initView()
        onSwipeRefresh()

        initData()

        SessionVariable.refreshOrderPending.observe(this@TradeExchangeActivity){
            if(it){

            }else{
                binding.swipeRefresh.isRefreshing = false
            }
        }

        //waiting screen
        SessionVariable.waitingPlaceOrder.observe(this@TradeExchangeActivity){
            if(it){
                binding.progressBar.visibility = View.VISIBLE
                window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
            }else{
                binding.progressBar.visibility = View.GONE
                window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
            }
        }

        //clear token
        SessionVariable.CLEAR_TOKEN_TRADE_EXCHANGE.observe(this@TradeExchangeActivity){
            if(it){
                clearData()
                OrderBookFragment().clearData()
            }
        }

        //cancel place order
        SessionVariable.cancelPlaceOrder.observe(this@TradeExchangeActivity){
            if(it){
                val cancelOrderDialog = CancelOrderDialog()
                this@TradeExchangeActivity.supportFragmentManager.let { it1 -> cancelOrderDialog.show(it1, "asaf") }
                SessionVariable.cancelPlaceOrder.value = false
                SessionVariable.waitingPlaceOrder.value = false
            }
        }

        SessionVariable.callDialogSuccessPlaceOrder.observe(this@TradeExchangeActivity){
            if(it){
                val successPlaceOrderDialog = SuccessPlaceOrderDialog()
                this@TradeExchangeActivity.supportFragmentManager.let { it1 -> successPlaceOrderDialog.show(it1, "asaf") }
                SessionVariable.callDialogSuccessPlaceOrder.value = false
                SessionVariable.waitingPlaceOrder.value = false
            }
        }

        SessionVariable.callDialogErrorCreateOrder.observe(this@TradeExchangeActivity){
            if (it){
                val errorPlaceOrderDialog = ErrorPlaceOrderDialog()
                this@TradeExchangeActivity.supportFragmentManager.let { it1 -> errorPlaceOrderDialog.show(it1, "asaf") }
                SessionVariable.callDialogErrorCreateOrder.value = false
                SessionVariable.waitingPlaceOrder.value = false
            }
        }

        SessionVariable.refreshMatchingTransaction.observe(this@TradeExchangeActivity){
            if(it){

            }else{
                binding.swipeRefresh.isRefreshing = false
            }
        }

        onCallApi()

//        Tovuti.from(UTSwapApp.instance).monitor{ _, isConnected, _ ->
//            if(isConnected)
//            {
//                mPresenter.onCheckKYCStatus()
//                mPresenter.startTradeDetailSocket(intent?.getStringExtra(Constants.TradeExchange.MarketName).toString())
//                mPresenter.onCheckFavoriteProject(TradingList.TradeFavoriteProjectObj(intent?.getStringExtra(Constants.TradeExchange.ProjectId).toString().toInt()),UTSwapApp.instance)
//                mPresenter.getAvailableBalance(TradingList.AvailableBalanceObj(intent?.getStringExtra(Constants.TradeExchange.MarketName).toString()),UTSwapApp.instance)
//                mPresenter.getMarketOpen(intent?.getStringExtra(Constants.TradeExchange.MarketName).toString(),UTSwapApp.instance)
//            }else{
//                clearData()
//            }
//        }

        try {
            toolBar()

            fetchTradeDetailData.observe(this@TradeExchangeActivity){

                binding.swipeRefresh.isRefreshing = false

                binding.apply {
                    txtHigh.text = ""
                    txtLast.text = ""
                    txtLow.text = ""
                    txtVolume.text = ""
                    txtTargetPrice.text = ""
                    txtChange.text = ""
                }

                binding.apply {

                    if(it.info?.new_price == false)
                    {
                        txtLast.text = resources.getString(R.string.empty_price_order_book)
                    }else{
                        txtLast.text = it.info?.new_price.toString()
                    }

                    if(it.info?.max_price == false)
                    {
                        txtHigh.text = resources.getString(R.string.empty_price_order_book)
                    }else{
                        txtHigh.text = it.info?.max_price.toString()
                    }

                    if(it.info?.min_price == false)
                    {
                        txtLow.text = resources.getString(R.string.empty_price_order_book)
                    }else{
                        txtLow.text = it.info?.min_price.toString()
                    }

                    if(it.info?.target_price == false)
                    {
                        txtTargetPrice.text = resources.getString(R.string.empty_price_order_book)
                    }else{
                        txtTargetPrice.text = it.info?.target_price.toString()
                    }

                    txtVolume.text = groupingSeparatorInt(it.info?.volume.toString().toDouble())

                    //change
                    if(it.info?.change.toString().toDouble() < 0){
                        txtChange.text = it.info?.change.toString() + "%"
                        txtChange.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.danger))
                    }else if(it.info?.change.toString().toDouble() == 0.00)
                    {
                        txtChange.text = it.info?.change.toString() + "%"
                        txtChange.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.black_222222))
                    }else {
                        txtChange.text = "+" + it.info?.change.toString() + "%"
                        txtChange.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.success))
                    }
                }

            }

            binding.apply {
                swipeRefresh.setColorSchemeColors(ContextCompat.getColor(UTSwapApp.instance, R.color.primary))

                btnVerifyKyc.setOnClickListener {
                    val intent = Intent(this@TradeExchangeActivity, KYCActivity::class.java).putExtra(
                        "KYCStatus", "Pending"
                    )
                    startActivity(intent)
                }
                btnBuyBottomSheetClick.setOnClickListener {
                    buySellBottomSheet = BuyAndSellBottomSheetDialog(object :
                        BuyAndSellBottomSheetDialog.OnDismissListener {
                        override fun onDismiss(isDismiss: Boolean) {
                            kycPending()
                        }
                    })
                    buySellBottomSheet?.show(this@TradeExchangeActivity.supportFragmentManager, "")
                }
                btnSellBottomSheetClick.setOnClickListener {
                    buySellBottomSheet = BuyAndSellBottomSheetDialog(object :
                        BuyAndSellBottomSheetDialog.OnDismissListener {
                        override fun onDismiss(isDismiss: Boolean) {
                            kycPending()

                        }
                    })
                    buySellBottomSheet?.show(this@TradeExchangeActivity.supportFragmentManager, "")

                }
                SessionVariable.SESSION_STATUS.observe(this@TradeExchangeActivity) {
                    onCheckSessionStatusAndKYC()
                }

                SessionVariable.SESSION_KYC.observe(this@TradeExchangeActivity) {
                    onCheckSessionStatusAndKYC()
                }
                SessionVariable.SESSION_KYC_SUBMIT_STATUS.observe(this@TradeExchangeActivity) {
                    onCheckSessionStatusAndKYC()
                }

                //add to favorite
                includeLayout.imgRemember.setOnClickListener {
                    if (remember == true) {
                        includeLayout.imgRemember.imageTintList = ColorStateList.valueOf(
                            ContextCompat.getColor(
                                UTSwapApp.instance,
                                R.color.dark_gray
                            )
                        )
                        mPresenter.addFavoriteProject(TradingList.TradeAddFavoriteObj(0,intent?.getStringExtra(Constants.TradeExchange.ProjectId).toString().toInt()),UTSwapApp.instance)
                        remember = false
                    } else {
                        includeLayout.imgRemember.imageTintList = ColorStateList.valueOf(
                            ContextCompat.getColor(
                                UTSwapApp.instance,
                                R.color.warning
                            )
                        )
                        mPresenter.addFavoriteProject(TradingList.TradeAddFavoriteObj(1,intent?.getStringExtra(Constants.TradeExchange.ProjectId).toString().toInt()),UTSwapApp.instance)
                        remember = true
                    }
                }

                SessionVariable.SESSION_KYC_STATUS.observe(this@TradeExchangeActivity) {
                    if (SessionVariable.SESSION_KYC.value == false && SessionVariable.SESSION_STATUS.value == true) {
                        when (SessionVariable.SESSION_KYC_STATUS.value) {
                            2 -> {
                                layKycStatus.visibility = View.VISIBLE
                                layKycStatus.backgroundTintList = ColorStateList.valueOf(
                                    ContextCompat.getColor(UTSwapApp.instance, R.color.warning)
                                )
                                txtStatus.text = "Pending Review."
                                btnVerify.isClickable = false
                                btnVerify.backgroundTintList = ColorStateList.valueOf(
                                    ContextCompat.getColor(
                                        UTSwapApp.instance,
                                        R.color.gray_999999
                                    )
                                )
                                Handler().postDelayed({
                                    SessionVariable.SESSION_KYC_STATUS.value = 1
                                }, 5000)

                            }
                            1 -> {
                                layKycStatus.visibility = View.VISIBLE
                                layKycStatus.backgroundTintList = ColorStateList.valueOf(
                                    ContextCompat.getColor(
                                        UTSwapApp.instance,
                                        R.color.danger
                                    )
                                )
                                txtStatus.text = "Invalid Verification. Please Try Again."
                                btnVerify.isClickable = true
                                btnVerify.backgroundTintList = ColorStateList.valueOf(
                                    ContextCompat.getColor(
                                        UTSwapApp.instance,
                                        R.color.primary
                                    )
                                )
                            }
                            else -> {
                                layKycStatus.visibility = View.GONE
                                SessionVariable.SESSION_KYC.value = true
                            }
                        }
                    }
                }

                persistentBottomSheet.txtAvailable.paintFlags =
                    persistentBottomSheet.txtAvailable.paintFlags or Paint.UNDERLINE_TEXT_FLAG
//                persistentBottomSheet.txtAvailableClick.paintFlags =
//                    persistentBottomSheet.txtAvailableClick.paintFlags or Paint.UNDERLINE_TEXT_FLAG
                persistentBottomSheet.txtUt.paintFlags =
                    persistentBottomSheet.txtUt.paintFlags or Paint.UNDERLINE_TEXT_FLAG
//                persistentBottomSheet.txtUtClick.paintFlags =
//                    persistentBottomSheet.txtUtClick.paintFlags or Paint.UNDERLINE_TEXT_FLAG


                pageAdapter = ScreenSlidePageAdapter(this@TradeExchangeActivity, NUM_PAGES)
                vpVerify.adapter = pageAdapter
                vpVerify.isUserInputEnabled = false
                vpVerify.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {

                })

                orderBook.setOnClickListener { view ->
                    onChangeTabs(view)
                    vpVerify.setCurrentItem(0, false)
                }
                chart.setOnClickListener { view ->
                    onChangeTabs(view)
                    vpVerify.setCurrentItem(1, false)
                }

                pageTableAdapter =
                    ScreenSlidePageTableAdapter(this@TradeExchangeActivity, NUM_PAGES_TABLE)
                vpTable.adapter = pageTableAdapter
                vpTable.isUserInputEnabled = false
                vpTable.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {

                })

                txtOrders.setOnClickListener { view ->
                    onChangeTabsTable(view)
                    vpTable.setCurrentItem(0, false)
//                    page = 1
//                    SessionVariable.request.value = false
//                    SessionVariable.requestPage.value = false
                    positionFragment = 0
                }
                txtTransactions.setOnClickListener { view ->
                    onChangeTabsTable(view)
                    vpTable.setCurrentItem(1, false)
//                    if(!orderScroll){
//                        page = 1
//                        SessionVariable.requestPage.value = true
//                    }
//                    SessionVariable.request.value = false
                    positionFragment = 1
                }

                layAuth.setOnClickListener {
                    val intent = Intent(UTSwapApp.instance, SignInActivity::class.java)
                    startActivity(intent)
                }

                btnVerify.setOnClickListener {
                    val intent = Intent(UTSwapApp.instance, KYCActivity::class.java)
                    startActivity(intent)
                }

                // Bottom sheet persistent
                mBottomSheetBehavior =
                    BottomSheetBehavior.from(persistentBottomSheet.bottomSheetBuySell)

                (mBottomSheetBehavior as BottomSheetBehavior<*>).addBottomSheetCallback(object :
                    BottomSheetBehavior.BottomSheetCallback() {
                    override fun onStateChanged(bottomSheet: View, newState: Int) {
                        /*if (mBottomSheetBehavior?.state == BottomSheetBehavior.STATE_DRAGGING) {
                            persistentBottomSheet.layBuyAndSellClick.visibility = View.GONE
                        } else if (mBottomSheetBehavior?.state == BottomSheetBehavior.STATE_COLLAPSED) {
                            persistentBottomSheet.layBuyAndSellClick.visibility = View.VISIBLE
                        }*/
                    }

                    override fun onSlide(bottomSheet: View, slideOffset: Float) {}
                })

                /*persistentBottomSheet.btnBuyBottomSheetClick.setOnClickListener {
                    if (mBottomSheetBehavior?.state == BottomSheetBehavior.STATE_COLLAPSED) {
                        persistentBottomSheet.layBuyAndSellClick.visibility = View.GONE
                        mBottomSheetBehavior?.state = BottomSheetBehavior.STATE_EXPANDED
                    }
                }
                persistentBottomSheet.btnSellBottomSheetClick.setOnClickListener {
                    if (mBottomSheetBehavior?.state == BottomSheetBehavior.STATE_COLLAPSED) {
                        persistentBottomSheet.layBuyAndSellClick.visibility = View.GONE
                        mBottomSheetBehavior?.state = BottomSheetBehavior.STATE_EXPANDED

                    }
                }*/

                persistentBottomSheet.btnBuyBottomSheet.setOnClickListener {
                    var isHaveError = false
                    if (persistentBottomSheet.etVolume.text.isEmpty()) {
                        persistentBottomSheet.etVolume.background =
                            ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.bg_error_red)
                        isHaveError = true
                    }

                    if (persistentBottomSheet.linearPrice.visibility == View.VISIBLE) {
                        if (persistentBottomSheet.etPriceOfVolume.text.isEmpty()) {
                            persistentBottomSheet.etPriceOfVolume.background =
                                ContextCompat.getDrawable(
                                    UTSwapApp.instance,
                                    R.drawable.bg_error_red
                                )
                            isHaveError = true
                        }
                    }

                    if (isHaveError) return@setOnClickListener

                    if (click) {
                        mBottomSheetBehavior?.state = BottomSheetBehavior.STATE_COLLAPSED
                        val buyDialog: BuyDialog =
                            BuyDialog.newInstance(
                                persistentBottomSheet.etVolume.text.toString(),
                                persistentBottomSheet.etPriceOfVolume.text.toString(),
                                "limit"
                            )
                        buyDialog.show(supportFragmentManager, "limitBuy")
                    } else {
                        mBottomSheetBehavior?.state = BottomSheetBehavior.STATE_COLLAPSED
                        val marketDialog: MarketDialog =
                            MarketDialog.newInstance(
                                persistentBottomSheet.etVolume.text.toString(),
                                "BUY",
                                "market"
                            )
                        marketDialog.show(supportFragmentManager, "marketBuy")
                    }

                }
                persistentBottomSheet.btnSellBottomSheet.setOnClickListener {
                    var isHaveError = false
                    if (persistentBottomSheet.etVolume.text.isEmpty()) {
                        persistentBottomSheet.etVolume.background =
                            ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.bg_error_red)
                        isHaveError = true
                    }

                    if (persistentBottomSheet.linearPrice.visibility == View.VISIBLE) {
                        if (persistentBottomSheet.etPriceOfVolume.text.isEmpty()) {
                            persistentBottomSheet.etPriceOfVolume.background =
                                ContextCompat.getDrawable(
                                    UTSwapApp.instance,
                                    R.drawable.bg_error_red
                                )
                            isHaveError = true
                        }
                    }

                    if (isHaveError) return@setOnClickListener

                    if (click) {
                        mBottomSheetBehavior?.state = BottomSheetBehavior.STATE_COLLAPSED
                        val sellDialog: SellDialog =
                            SellDialog.newInstance(
                                persistentBottomSheet.etVolume.text.toString(),
                                persistentBottomSheet.etPriceOfVolume.text.toString()
                            )
                        sellDialog.show(supportFragmentManager, "limitSell")
                    } else {
                        mBottomSheetBehavior?.state = BottomSheetBehavior.STATE_COLLAPSED
                        val marketDialog: MarketDialog =
                            MarketDialog.newInstance(
                                persistentBottomSheet.etVolume.text.toString(),
                                "SELL",
                                "market"
                            )
                        marketDialog.show(supportFragmentManager, "marketSell")
                    }
                }

                persistentBottomSheet.etVolume.addTextChangedListener(object : TextWatcher {

                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                    }

                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                        persistentBottomSheet.etVolume.background = ContextCompat.getDrawable(
                            UTSwapApp.instance,
                            R.drawable.outline_edittext_change_color_focus
                        )
                    }

                    override fun afterTextChanged(p0: Editable?) {}
                })
                persistentBottomSheet.etPriceOfVolume.filters = arrayOf<InputFilter>(
                    DecimalDigitsInputFilter(10, 2)
                )
                persistentBottomSheet.etPriceOfVolume.addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                    }

                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                        persistentBottomSheet.etPriceOfVolume.background =
                            ContextCompat.getDrawable(
                                UTSwapApp.instance,
                                R.drawable.outline_edittext_change_color_focus
                            )
                    }

                    override fun afterTextChanged(p0: Editable?) {

                    }
                })

                persistentBottomSheet.btnMarket.setOnClickListener {
                    persistentBottomSheet.linearPrice.visibility = View.GONE
                    persistentBottomSheet.btnMarket.background =
                        ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.bg_circular)
                    persistentBottomSheet.btnMarket.backgroundTintList = ColorStateList.valueOf(
                        ContextCompat.getColor(
                            UTSwapApp.instance,
                            R.color.primary
                        )
                    )
                    persistentBottomSheet.txtMarket.setTextColor(
                        ContextCompat.getColor(
                            UTSwapApp.instance,
                            R.color.white
                        )
                    )
                    persistentBottomSheet.txtLimit.setTextColor(
                        ContextCompat.getColor(
                            UTSwapApp.instance,
                            R.color.primary
                        )
                    )
                    persistentBottomSheet.btnLimit.backgroundTintList = ColorStateList.valueOf(
                        ContextCompat.getColor(
                            UTSwapApp.instance,
                            R.color.gray
                        )
                    )
                    persistentBottomSheet.etVolume.background = ContextCompat.getDrawable(
                        UTSwapApp.instance,
                        R.drawable.outline_edittext_change_color_focus
                    )
                    click = false
                }
                persistentBottomSheet.btnLimit.setOnClickListener {
                    persistentBottomSheet.linearPrice.visibility = View.VISIBLE
                    persistentBottomSheet.btnLimit.background =
                        ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.bg_circular)
                    persistentBottomSheet.btnLimit.backgroundTintList = ColorStateList.valueOf(
                        ContextCompat.getColor(
                            UTSwapApp.instance,
                            R.color.primary
                        )
                    )
                    persistentBottomSheet.txtLimit.setTextColor(
                        ContextCompat.getColor(
                            UTSwapApp.instance,
                            R.color.white
                        )
                    )
                    persistentBottomSheet.txtMarket.setTextColor(
                        ContextCompat.getColor(
                            UTSwapApp.instance,
                            R.color.primary
                        )
                    )
                    persistentBottomSheet.btnMarket.backgroundTintList = ColorStateList.valueOf(
                        ContextCompat.getColor(
                            UTSwapApp.instance,
                            R.color.gray
                        )
                    )
                    persistentBottomSheet.etPriceOfVolume.background = ContextCompat.getDrawable(
                        UTSwapApp.instance,
                        R.drawable.outline_edittext_change_color_focus
                    )
                    persistentBottomSheet.etVolume.background = ContextCompat.getDrawable(
                        UTSwapApp.instance,
                        R.drawable.outline_edittext_change_color_focus
                    )
                    click = true
                }
//  end of bottom sheet persistent

//                nestedScroll.setOnScrollChangeListener(
//                    NestedScrollView.OnScrollChangeListener { v, _, scrollY, _, _ ->
//                        if (scrollY == (v.getChildAt(0).measuredHeight - v.measuredHeight)) {
//
//                            if(positionFragment == 0){
//                                if(orderPage <= OrdersFragment.totalPage)
//                                {
//                                    orderPage++
//                                    SessionVariable.request.value = true
//
//                                    orderScroll = true
//                                }
//
//                            }else{
//                               if(page <= TransactionsFragment.totalPage){
//                                   page++
//                                   SessionVariable.requestPage.value = true
//                               }
//                            }
//                        }
//                    })
            }
        } catch (error: Exception) {
            // Must be safe
        }
    }

    override fun onCheckKYCSuccess(data: User.KycRes) {
        kycSubmit = data.data?.status_submit_kyc
        kycComplete = data.data?.status_kyc
        onCheckSessionStatusAndKYC()

    }

    override fun onCheckKYCFail() {

    }

    private fun toolBar() {
       // setSupportActionBar(binding.includeLayout.tb)
      //  supportActionBar?.setDisplayHomeAsUpEnabled(true)
      //  supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_left)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.includeLayout.apply {
            if (intent.hasExtra(Constants.TradeExchange.ProjectName)) {
                val name = intent?.getStringExtra(Constants.TradeExchange.ProjectName)
                tbTitle.text = name
            }

            tbTitle.setTextColor(ContextCompat.getColor(applicationContext, R.color.primary))
            tb.setOnClickListener {
                finish()
                onBackPressed()
            }

            binding.includeLayout.tbLeft.setOnClickListener {
                ProjectInfoActivity.launchProjectInfoActivity(
                    root.context,
                    intent?.getStringExtra(Constants.TradeExchange.ProjectId),
//                    intent?.getStringExtra(Constants.TradeExchange.ProjectName)
                )
            }


        }
    }

    private fun onCheckSessionStatusAndKYC() {
        binding.apply {
            if (SessionPreferences().SESSION_TOKEN != null) {
                includeLayout.imgRemember.visibility = View.VISIBLE

                if (kycComplete == true) {
                    layTransactions.visibility = View.VISIBLE
                    layVerify.visibility = View.GONE
                    layAuth.visibility = View.GONE
                    llBottom.visibility = View.VISIBLE
                    llBtnVerify.visibility = View.GONE
                } else if (kycComplete == false && kycSubmit == true) {
                    layVerify.visibility = View.GONE
                    layTransactions.visibility = View.GONE
                    layAuth.visibility = View.GONE
                    llBottom.visibility = View.VISIBLE
                    llBtnVerify.visibility = View.VISIBLE
                } else if (kycComplete == false && kycSubmit == false) {
                    layVerify.visibility = View.VISIBLE
                    layTransactions.visibility = View.GONE
                    layAuth.visibility = View.GONE
                    llBottom.visibility = View.GONE
                }
            } else {
                layTransactions.visibility = View.GONE
                layAuth.visibility = View.VISIBLE
                layVerify.visibility = View.GONE
                llBottom.visibility = View.GONE
                includeLayout.imgRemember.visibility = View.INVISIBLE

            }

            // When did not verify kyc , button kyc show

            /*if (SessionVariable.SESSION_STATUS.value == true && SessionVariable.SESSION_KYC.value == true) {
                includeLayout.imgRemember.visibility = View.VISIBLE
                persistentBottomSheet.root.visibility = View.VISIBLE
                layTransactions.visibility = View.VISIBLE

            } else {
                persistentBottomSheet.root.visibility = View.GONE
                layTransactions.visibility = View.GONE

                if (SessionVariable.SESSION_STATUS.value == false) {
                    layAuth.visibility = View.VISIBLE
                    layVerify.visibility = View.GONE
                    includeLayout.imgRemember.visibility = View.GONE
                }
                if (SessionVariable.SESSION_KYC.value == false) {
                    layAuth.visibility = View.GONE
                    layVerify.visibility = View.VISIBLE
                }

            }*/


        }
    }

    private fun kycPending() {
        binding.apply {
            if (SessionPreferences().SESSION_TOKEN != null) {
                if (SessionPreferences().SESSION_KYC_SUBMIT_STATUS == true) {
                    btnVerifyKyc.animate().alpha(0.1f).duration = 200
                    lifecycleScope.launch {
                        delay(500)
                        btnVerifyKyc.animate().alpha(1f).duration = 200
                        delay(500)
                        btnVerifyKyc.animate().alpha(0.1f).duration = 200
                        delay(500)
                        btnVerifyKyc.animate().alpha(1f).duration = 200
                    }

                }

            }
        }
    }

    override fun onCheckFavoriteProjectSuccess(data: TradingList.TradeFavoriteProjectRes) {
        binding.apply {
            if(data.data?.is_favorite == true)
            {
                includeLayout.imgRemember.imageTintList = ColorStateList.valueOf(
                    ContextCompat.getColor(
                        UTSwapApp.instance,
                        R.color.warning
                    )
                )
                remember = true
            }else{
                includeLayout.imgRemember.imageTintList = ColorStateList.valueOf(
                    ContextCompat.getColor(
                        UTSwapApp.instance,
                        R.color.dark_gray
                    )
                )
                remember = false
            }
        }
    }

    override fun onCheckFavoriteProjectFail(data: TradingList.TradeFavoriteProjectRes) {

    }

    override fun addFavoriteProjectSuccess(data: TradingList.TradeAddFavoriteRes) {
        SessionVariable.realTimeWatchList.value = true
    }

    override fun addFavoriteProjectFail(data: TradingList.TradeAddFavoriteRes) {
        SessionVariable.realTimeWatchList.value = true
    }

    private fun onChangeTabs(view: View) {
        binding.apply {
            if (view.id == R.id.order_book) {
                tabSelect.animate()?.x(0f)?.duration = 100
                orderBook.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.white))
                chart.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.primary))
            } else if (view.id == R.id.chart) {
                orderBook.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.primary))
                chart.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.white))
                val size: Int = chart.width
                tabSelect.animate().x(size.toFloat()).duration = 100
            }
        }

    }

    private fun onChangeTabsTable(view: View) {
        binding.apply {
            when (view.id) {
                R.id.txt_orders -> {
                    tabSelectOrders.animate()?.x(0f)?.duration = 100
                    txtOrders.setTextColor(
                        ContextCompat.getColor(
                            UTSwapApp.instance,
                            R.color.white
                        )
                    )
                    txtTransactions.setTextColor(
                        ContextCompat.getColor(
                            UTSwapApp.instance,
                            R.color.primary
                        )
                    )
                }
                R.id.txt_transactions -> {
                    txtOrders.setTextColor(
                        ContextCompat.getColor(
                            UTSwapApp.instance,
                            R.color.primary
                        )
                    )
                    txtTransactions.setTextColor(
                        ContextCompat.getColor(
                            UTSwapApp.instance,
                            R.color.white
                        )
                    )
                    val size: Int = txtTransactions.width
                    tabSelectOrders.animate().x(size.toFloat()).duration = 100
                }
            }
        }
    }

    private class ScreenSlidePageAdapter(idTypeActivity: TradeExchangeActivity?, NUM_PAGES: Int) :
        FragmentStateAdapter(idTypeActivity!!) {

        private val NUM_PAGES = NUM_PAGES

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> OrderBookFragment()
                else -> ChartFragment()
            }
        }

        override fun getItemCount(): Int {
            return NUM_PAGES
        }
    }

    private class ScreenSlidePageTableAdapter(
        idTypeActivity: TradeExchangeActivity?,
        NUM_PAGES_TABLE: Int
    ) :
        FragmentStateAdapter(idTypeActivity!!) {

        private val NUM_PAGES_TABLE = NUM_PAGES_TABLE

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> OrdersFragment()
                1 -> TransactionsFragment()
                else -> AllTransactionsFragment()
            }
        }

        override fun getItemCount(): Int {
            return NUM_PAGES_TABLE
        }
    }

    private fun onSwipeRefresh(){
        binding.swipeRefresh.setOnRefreshListener {
            SessionVariable.refreshOrderPending.value = true
            SessionVariable.refreshMatchingTransaction.value = true
            SessionVariable.requestOrderBookSocket.value = true
            //mPresenter.startTradeDetailSocket(intent?.getStringExtra(Constants.TradeExchange.MarketName).toString())
            onCallApi()
        }
    }


    //get Available Balance
    override fun getAvailableBalanceSuccess(data: TradingList.AvailableBalanceRes) {
        binding.apply {
            txtAvailableClick.text = ""
            txtUtClick.text = ""
            if(data.data?.usd.toString().isNotEmpty()){
                txtAvailableClick.text = "${data.data?.usd?.let { groupingSeparator(it) }}"
                Constants.TradeExchange.availableBalance = "${data.data?.usd?.let { groupingSeparator(it) }}"
            }

            if(data.data?.xnb.toString().isNotEmpty()){
                txtUtClick.text = "${data.data?.xnb?.let { groupingSeparatorInt(it) }}"
                Constants.TradeExchange.utBalance = "${data.data?.xnb?.let { groupingSeparatorInt(it) }}"
            }

            Constants.TradeExchange.sellFee = data.data?.sell_fee.toString()
            Constants.TradeExchange.buyFee = data.data?.buy_fee.toString()
        }
    }

    override fun getAvailableBalanceFail(data: TradingList.AvailableBalanceRes) {

    }

    override fun getMarketOpenSuccess(data: TradingList.TradeMarketOpenRes) {
        binding.apply {
            //change project is live or close
            if(data.data?.market_open == true){
                includeLayout.btnLive.visibility = View.VISIBLE
                includeLayout.btnLive.backgroundTintList = ColorStateList.valueOf(
                    ContextCompat.getColor(
                        UTSwapApp.instance,
                        R.color.success
                    )
                )

                includeLayout.btnLive.text = resources.getString(R.string.live)

                SessionVariable.marketOpen.value = true
            }else{
                includeLayout.btnLive.visibility = View.VISIBLE
                includeLayout.btnLive.backgroundTintList = ColorStateList.valueOf(
                    ContextCompat.getColor(
                        UTSwapApp.instance,
                        R.color.danger
                    )
                )
                includeLayout.btnLive.text = resources.getString(R.string.close)

                SessionVariable.marketOpen.value = false
            }
        }
    }

    override fun getMarketOpenFail(data: TradingList.TradeMarketOpenRes) {

    }

    override fun onBackPressed() {
        super.onBackPressed()
        clearData()

        OrderBookFragment().clearData()
    }

    override fun onDestroy() {
        super.onDestroy()

        clearData()
        OrderBookFragment().clearData()
    }

    override fun onResume() {
        handler.postDelayed(Runnable {
            runnable?.let { handler.postDelayed(it, delay.toLong()) }
            mPresenter.getAvailableBalance(TradingList.AvailableBalanceObj(intent?.getStringExtra(Constants.TradeExchange.MarketName).toString()),UTSwapApp.instance)
            mPresenter.getMarketOpen(intent?.getStringExtra(Constants.TradeExchange.MarketName).toString(),UTSwapApp.instance)
        }.also { runnable = it }, delay.toLong())
        super.onResume()
    }

    private fun clearData(){
        mPresenter.closeTradeDetailSocket()
        SessionVariable.requestOrderBookSocket.value = false
        SessionVariable.requestTradingList.value = true
        SessionVariable.callDialogErrorCreateOrder.value = false
        SessionVariable.marketPriceSell.value = "0.00"
        SessionVariable.marketPriceBuy.value = "0.00"
        Constants.TradeExchange.sellFee = ""
        Constants.TradeExchange.buyFee = ""
        SessionVariable.CLEAR_TOKEN_TRADE_EXCHANGE.value = false

        runnable?.let { handler.removeCallbacks(it) }
    }

    private fun initData(){
        /** for recall request available balance and order book table socket*/
        SessionVariable.requestOrderBookSocket.value = true
        SessionVariable.refreshOrderPending.value = false
        SessionVariable.refreshMatchingTransaction.value = false
        SessionVariable.callDialogErrorCreateOrder.value = false
        SessionVariable.callDialogSuccessPlaceOrder.value = false
        SessionVariable.waitingPlaceOrder.value = false
        SessionVariable.callDialogSuccessPlaceOrder.value = false
        SessionVariable.cancelPlaceOrder.value = false
        SessionVariable.marketPriceSell.value = "0.00"
        SessionVariable.marketPriceBuy.value = "0.00"
    }

    private fun onCallApi(){
        mPresenter.onCheckKYCStatus()
        mPresenter.startTradeDetailSocket(intent?.getStringExtra(Constants.TradeExchange.MarketName).toString())
        mPresenter.onCheckFavoriteProject(TradingList.TradeFavoriteProjectObj(intent?.getStringExtra(Constants.TradeExchange.ProjectId).toString().toInt()),UTSwapApp.instance)
        mPresenter.getAvailableBalance(TradingList.AvailableBalanceObj(intent?.getStringExtra(Constants.TradeExchange.MarketName).toString()),UTSwapApp.instance)
        mPresenter.getMarketOpen(intent?.getStringExtra(Constants.TradeExchange.MarketName).toString(),UTSwapApp.instance)
    }

}