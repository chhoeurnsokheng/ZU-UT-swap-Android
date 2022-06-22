package com.zillennium.utswap.screens.navbar.tradeTab.tradeExchangeScreen

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Paint
import android.os.Handler
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.zillennium.utswap.Datas.GlobalVariable.SessionVariable
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivityTradeExchangeBinding
import com.zillennium.utswap.screens.kyc.kycActivity.KYCActivity
import com.zillennium.utswap.screens.project.projectInfoScreen.ProjectInfoActivity
import com.zillennium.utswap.screens.navbar.tradeTab.tradeExchangeScreen.fragment.Transactions.TransactionsFragment
import com.zillennium.utswap.screens.navbar.tradeTab.tradeExchangeScreen.fragment.allTransactions.AllTransactionsFragment
import com.zillennium.utswap.screens.navbar.tradeTab.tradeExchangeScreen.fragment.chart.ChartFragment
import com.zillennium.utswap.screens.navbar.tradeTab.tradeExchangeScreen.fragment.orderBook.OrderBookFragment
import com.zillennium.utswap.screens.navbar.tradeTab.tradeExchangeScreen.fragment.orders.OrdersFragment
import com.zillennium.utswap.screens.navbar.tradeTab.tradeExchangeScreen.dialog.BuyAndSellBottomSheetDialog
import com.zillennium.utswap.screens.security.securityActivity.registerScreen.RegisterActivity
import com.zillennium.utswap.screens.security.securityActivity.signInScreen.SignInActivity


class TradeExchangeActivity :
    BaseMvpActivity<TradeExchangeView.View, TradeExchangeView.Presenter, ActivityTradeExchangeBinding>(),
    TradeExchangeView.View {

    override var mPresenter: TradeExchangeView.Presenter = TradeExchangePresenter()
    override val layoutResource: Int = R.layout.activity_trade_exchange

    val NUM_PAGES = 2
    private var pageAdapter: FragmentStateAdapter? = null
    private var pageTableAdapter: FragmentStateAdapter? = null
    val NUM_PAGES_TABLE = 3
    var remember: Boolean = true

//    @SuppressLint("UseCompatLoadingForDrawables", "ResourceAsColor")
    override fun initView() {
        super.initView()
        try {
            binding.apply {
                SessionVariable.SESSION_STATUS.observe(this@TradeExchangeActivity) {
                    onCheckSessionStatusAndKYC()
                }

                SessionVariable.SESSION_KYC.observe(this@TradeExchangeActivity){
                    onCheckSessionStatusAndKYC()
                }

                imgRemember.setOnClickListener {
                    remember = !remember
                    if(remember){
                        imgRemember.imageTintList = ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.orange))
                    }else{
                        imgRemember.imageTintList = ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.dark_gray))
                    }
                }

                SessionVariable.SESSION_KYC_STATUS.observe(this@TradeExchangeActivity){
                    if(SessionVariable.SESSION_KYC.value == false && SessionVariable.SESSION_STATUS.value == true){
                        when(SessionVariable.SESSION_KYC_STATUS.value){
                            2 -> {
                                layKycStatus.visibility = View.VISIBLE
                                layKycStatus.backgroundTintList = ColorStateList.valueOf(
                                    ContextCompat.getColor(UTSwapApp.instance, R.color.orange))
                                txtStatus.text = "Pending Review."
                                btnVerify.isClickable = false
                                btnVerify.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.gray_999999))
                                Handler().postDelayed({
                                    SessionVariable.SESSION_KYC_STATUS.value = 1
                                }, 5000)

                            }
                            1 -> {
                                layKycStatus.visibility = View.VISIBLE
                                layKycStatus.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.main_red))
                                txtStatus.text = "Invalid Verification. Please Try Again."
                                btnVerify.isClickable = true
                                btnVerify.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.color_main))
                            }
                            else -> {
                                layKycStatus.visibility = View.GONE
                                SessionVariable.SESSION_KYC.value = true
                            }
                        }
                    }
                }


                txtAvailable.paintFlags = txtAvailable.paintFlags or Paint.UNDERLINE_TEXT_FLAG
                txtUt.paintFlags = txtUt.paintFlags or Paint.UNDERLINE_TEXT_FLAG

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

                pageTableAdapter = ScreenSlidePageTableAdapter(this@TradeExchangeActivity,NUM_PAGES_TABLE)
                vpTable.adapter = pageTableAdapter
                vpTable.isUserInputEnabled = false
                vpTable.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {

                })

                txtOrders.setOnClickListener { view ->
                    onChangeTabsTable(view)
                    vpTable.setCurrentItem(0, false)
                }
                txtTransactions.setOnClickListener { view ->
                    onChangeTabsTable(view)
                    vpTable.setCurrentItem(1, false)
                }
                txtAllTransactions.setOnClickListener { view ->
                    onChangeTabsTable(view)
                    vpTable.setCurrentItem(2, false)
                }

                btnBack.setOnClickListener {
                    onBackPressed()
                }

                btnBuy.setOnClickListener {
                    val buyAndSellBottomSheetDialog: BuyAndSellBottomSheetDialog =
                        BuyAndSellBottomSheetDialog.newInstance(
                        )
                    buyAndSellBottomSheetDialog.show(supportFragmentManager, "dgdgdg")
                }

                btnSell.setOnClickListener {
                    val buyAndSellBottomSheetDialog: BuyAndSellBottomSheetDialog =
                        BuyAndSellBottomSheetDialog.newInstance(
                        )
                    buyAndSellBottomSheetDialog.show(supportFragmentManager, "dgdgdg")
                }

                layProject.setOnClickListener {
                    val intent: Intent = Intent(UTSwapApp.instance, ProjectInfoActivity::class.java)
                    startActivity(intent)
                }

                btnSignIn.setOnClickListener {
                    val intent = Intent(UTSwapApp.instance, SignInActivity::class.java)
                    startActivity(intent)
                }

                btnRegister.setOnClickListener {
                    val intent = Intent(UTSwapApp.instance, RegisterActivity::class.java)
                    startActivity(intent)
                }

                btnVerify.setOnClickListener {
                    val intent = Intent(UTSwapApp.instance, KYCActivity::class.java)
                    startActivity(intent)
                }
            }
        } catch (error: Exception) {
            // Must be safe
        }
    }

    private fun onCheckSessionStatusAndKYC(){
        binding.apply {
            if(SessionVariable.SESSION_STATUS.value == true && SessionVariable.SESSION_KYC.value == true){
                layBuyAndSell.visibility = View.VISIBLE
                layTransactions.visibility = View.VISIBLE
            }else{
                layBuyAndSell.visibility = View.GONE
                layTransactions.visibility = View.GONE



                if(SessionVariable.SESSION_KYC.value == false){
                    layAuth.visibility = View.GONE
                    layVerify.visibility = View.VISIBLE
                }
                if(SessionVariable.SESSION_STATUS.value == false){
                    layAuth.visibility = View.VISIBLE
                    layVerify.visibility = View.GONE
                }
            }
        }
    }

    private fun onChangeTabs(view: View) {
        binding.apply {
            if (view.id == R.id.order_book) {
                tabSelect.animate()?.x(0f)?.duration = 100
                orderBook.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.white))
                chart.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.color_main))
            } else if (view.id == R.id.chart) {
                orderBook.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.color_main))
                chart.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.white))
                val size: Int = chart.width
                tabSelect.animate().x(size.toFloat()).duration = 100
            }
        }

    }

    private fun onChangeTabsTable(view: View){
        binding.apply {
            when (view.id) {
                R.id.txt_orders -> {
                    tabSelectOrders.animate()?.x(0f)?.duration = 100
                    txtOrders.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.white))
                    txtAllTransactions.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.color_main))
                    txtTransactions.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.color_main))
                }
                R.id.txt_transactions -> {
                    txtOrders.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.color_main))
                    txtAllTransactions.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.color_main))
                    txtTransactions.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.white))
                    val size: Int = txtTransactions.width
                    tabSelectOrders.animate().x(size.toFloat()).duration = 100
                }
                R.id.txt_all_transactions -> {
                    txtOrders.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.color_main))
                    txtTransactions.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.color_main))
                    txtAllTransactions.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.white))
                    val sizeAllTrans: Int = (txtAllTransactions.x.toInt())

                    tabSelectOrders.animate().x(sizeAllTrans.toFloat()).duration = 100
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

    private class ScreenSlidePageTableAdapter(idTypeActivity: TradeExchangeActivity?, NUM_PAGES_TABLE: Int) :
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

//    override fun onResume() {
//        super.onResume()
//        binding.apply {
//            if(vpVerify.currentItem == 1){
//                vpVerify.setCurrentItem(0, false)
//            }
//            if(vpTable.currentItem == 1){
//                vpTable.setCurrentItem(0,false)
//            }
//        }
//
//    }

}