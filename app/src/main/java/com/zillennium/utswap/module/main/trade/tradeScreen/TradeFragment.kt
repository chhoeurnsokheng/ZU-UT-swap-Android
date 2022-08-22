package com.zillennium.utswap.module.main.trade.tradeScreen

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidstudy.networkmanager.Tovuti
import com.zillennium.utswap.Datas.GlobalVariable.SessionVariable
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.FragmentNavbarTradeBinding
import com.zillennium.utswap.models.TradeModel
import com.zillennium.utswap.models.tradingList.TradingList
import com.zillennium.utswap.module.account.accountScreen.AccountActivity
import com.zillennium.utswap.module.main.trade.tradeExchangeScreen.TradeExchangeActivity
import com.zillennium.utswap.module.main.trade.tradeScreen.adapter.TradeAdapter
import com.zillennium.utswap.module.main.trade.tradeScreen.adapter.TradeUpcomingProjectAdapter
import com.zillennium.utswap.module.security.securityActivity.signInScreen.SignInActivity
import com.zillennium.utswap.module.system.notification.NotificationActivity
import com.zillennium.utswap.screens.navbar.navbar.MainActivity


class TradeFragment :
    BaseMvpFragment<TradeView.View, TradeView.Presenter, FragmentNavbarTradeBinding>(),
    TradeView.View {

    override var mPresenter: TradeView.Presenter = TradePresenter()
    override val layoutResource: Int = R.layout.fragment_navbar_trade
    private var tradeAdapter: TradeAdapter? = null
    private var tradeUpcomingProjectAdapter: TradeUpcomingProjectAdapter? = null
    private var listUpcomingProject = ArrayList<TradingList.TradeUpComingProjectList>()

    private var search: String = ""
    private var filter: Int = 0 // 0 = no sort,
    // 1 = asc change, 2 = desc change,
    // 3 = asc last, 4 = desc last,
    // 5 asc volume, 6 desc volume

    override var fetchTradeData: MutableLiveData<TradingList.TradingListRes> = MutableLiveData()

    private var tradeArrayList = ArrayList<TradeModel>()

    @SuppressLint("NotifyDataSetChanged")
    override fun initView() {
        super.initView()
        onOtherActivity()
        onCallWebSocketAndAPI()
        onCheckPreference()

        fetchTradeData.observe(this@TradeFragment) {
            if (search.isNotEmpty()) {
                search()
            } else {
                tradeArrayList.clear()
                for (i in it.market_trend?.url?.indices!!) {
                    if (!it.market_trend?.url?.get(i)?.get(11)?.toString().isNullOrEmpty()) {
                        tradeArrayList.add(
                            TradeModel(
                                it.market_trend?.url!![i][0].toString(),
                                it.market_trend?.url!![i][13].toString(),
                                it.market_trend?.url!![i][1].toString(),
                                it.market_trend?.url!![i][6].toString(),
                                it.market_trend?.url!![i][8].toString(),
                                it.market_trend?.url!![i][11].toString()
                            )
                        )
                    }
                }

                if (filter != 0) {
                    if (search != "") {
                        search()
                    } else {
                        getFilterData(tradeArrayList)
                    }
                } else {
                    binding.apply {
                        tradeAdapter!!.items = tradeArrayList
                        tradeAdapter!!.notifyDataSetChanged()
                    }
                }
            }
        }
    }

    fun setBadgeNumberTrade() {
        binding.apply {
            SessionVariable.BADGE_NUMBER.observe(this@TradeFragment) {
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

    private fun onOtherActivity() {
        binding.apply {
            rvTrade.layoutManager = LinearLayoutManager(UTSwapApp.instance)
//            tradeAdapter = TradeAdapter(tradeArrayList, onclickTrade)
            tradeAdapter = TradeAdapter(listener = object : TradeAdapter.Listener {
                override fun clickMe(tradeProject: TradeModel) {
                    TradeExchangeActivity.launchTradeExchangeActivity(
                        requireActivity(),
                        tradeProject
                    )
                }

            })
            tradeAdapter!!.items = tradeArrayList
            rvTrade.adapter = tradeAdapter


            etSearch.setOnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    laySearch.backgroundTintList =
                        ColorStateList.valueOf(
                            ContextCompat.getColor(
                                UTSwapApp.instance,
                                R.color.primary
                            )
                        )
                } else {
                    laySearch.backgroundTintList =
                        ColorStateList.valueOf(
                            ContextCompat.getColor(
                                UTSwapApp.instance,
                                R.color.light_gray
                            )
                        )
                }
            }

            etSearch.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {


                }

                override fun onTextChanged(char: CharSequence, p1: Int, p2: Int, p3: Int) {
                    search = char.toString()
                    search()
                }

                override fun afterTextChanged(p0: Editable?) {
                    // hideKeyboard()
                }

            })

            layProject.setOnClickListener {
                filter = 0
                getFilterData(tradeArrayList)
            }

            layChange.setOnClickListener {
                filter = when (filter) {
                    2 -> 0
                    1 -> 2
                    else -> 1
                }
                getFilterData(tradeArrayList)
            }

            layLast.setOnClickListener {
                filter = when (filter) {
                    4 -> 0
                    3 -> 4
                    else -> 3
                }
                getFilterData(tradeArrayList)
            }

            layVolume.setOnClickListener {
                filter = when (filter) {
                    6 -> 0
                    5 -> 6
                    else -> 5
                }
                getFilterData(tradeArrayList)
            }

            imgMenu.setOnClickListener {
                val intent = Intent(UTSwapApp.instance, SignInActivity::class.java)
                startActivity(intent)
            }

            icSearch.setOnClickListener {
                linearLayoutSearch.visibility = View.VISIBLE
                linearLayoutUpcomingProject.visibility = View.GONE

                etSearch.requestFocus()

                val inputMethodManager =
                    UTSwapApp.instance.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
            }

            imgNotification.setOnClickListener {
                SessionVariable.SESSION_STATUS.observe(this@TradeFragment) {
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

            txtCancel.setOnClickListener {
                linearLayoutSearch.visibility = View.GONE
                if (listUpcomingProject.size != 0) {
                    linearLayoutUpcomingProject.visibility = View.VISIBLE
                }
                etSearch.text.clear()
                hideKeyboard()
            }
        }
    }

    private fun onCheckPreference() {
        binding.apply {
            SessionVariable.SESSION_STATUS.observe(this@TradeFragment) {
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
                    imgMenu.setOnClickListener {
                        val intent = Intent(UTSwapApp.instance, SignInActivity::class.java)
                        startActivity(intent)
                    }

                }
            }
        }
    }

    private fun onCallWebSocketAndAPI() {

        Tovuti.from(UTSwapApp.instance).monitor { _, isConnected, _ ->
            if (isConnected) {
                mPresenter.startSocketTrading()
                mPresenter.onGetUpcomingProject()
            }
        }
    }

    override fun onGetUpcomingProjectSuccess(data: TradingList.TradeUpComingProjectRes) {
        binding.apply {

            if (data.data?.project?.isNotEmpty() == true) {
                txtUpcoming.visibility = View.VISIBLE
                linearLayoutUpcomingProject.visibility = View.VISIBLE

                listUpcomingProject.addAll(data.data?.project!!)

                rvUpcomingProject.layoutManager = LinearLayoutManager(UTSwapApp.instance)
                tradeUpcomingProjectAdapter = TradeUpcomingProjectAdapter()
                tradeUpcomingProjectAdapter!!.items = listUpcomingProject
                rvUpcomingProject.adapter = tradeUpcomingProjectAdapter
            } else {
                txtUpcoming.visibility = View.GONE
                linearLayoutUpcomingProject.visibility = View.GONE
            }
        }
    }

    override fun onGetUpcomingProjectFail(data: TradingList.TradeUpComingProjectRes) {
        binding.apply {
            txtUpcoming.visibility = View.GONE
            linearLayoutUpcomingProject.visibility = View.GONE
        }
    }

    private fun search() {
        val tradeData = ArrayList<TradeModel>()
        binding.apply {

            if (search.isNotEmpty()) {
                tradeData.clear()
                tradeArrayList.map {
                    if (it.project_name.contains(search, ignoreCase = true)) {
                        tradeData.add(it)
                    }
                }
            } else {
                tradeData.addAll(tradeArrayList)
            }

            tradeAdapter = TradeAdapter(listener = object : TradeAdapter.Listener {
                override fun clickMe(tradeProject: TradeModel) {
                    TradeExchangeActivity.launchTradeExchangeActivity(
                        requireActivity(),
                        tradeProject
                    )
                }

            })
            tradeAdapter!!.items = tradeData
            rvTrade.adapter = tradeAdapter
        }
    }

    private fun getFilterData(list: ArrayList<TradeModel>) {

        val tradeData = ArrayList<TradeModel>()
        binding.apply {

            tradeData.clear()
            tradeData.addAll(list)

            onClearFilter()
            when (filter) {
                6 -> {
                    tradeData.sortByDescending { it.volume.toDouble() }
                    iconVolume.setImageResource(R.drawable.ic_sort_arrow_up_down_selected)
                    iconVolume.rotation = 180f
                }
                5 -> {
                    tradeData.sortBy { it.volume.toDouble() }
                    iconVolume.setImageResource(R.drawable.ic_sort_arrow_up_down_selected)
                    iconVolume.rotation = 0f
                }
                4 -> {
                    tradeData.sortByDescending { it.last.toDouble() }
                    iconLast.setImageResource(R.drawable.ic_sort_arrow_up_down_selected)
                    iconLast.rotation = 180f
                }
                3 -> {
                    tradeData.sortBy { it.last.toDouble() }
                    iconLast.setImageResource(R.drawable.ic_sort_arrow_up_down_selected)
                    iconLast.rotation = 0f
                }
                2 -> {
                    tradeData.sortByDescending { it.change.toDouble() }
                    iconChange.setImageResource(R.drawable.ic_sort_arrow_up_down_selected)
                    iconChange.rotation = 180f
                }
                1 -> {
                    tradeData.sortBy { it.change.toDouble() }
                    iconChange.setImageResource(R.drawable.ic_sort_arrow_up_down_selected)
                    iconChange.rotation = 0f
                }
                else -> {
                    onClearFilter()
                }
            }

//            tradeAdapter = TradeAdapter(tradeData, onclickTrade)
            tradeAdapter = TradeAdapter(listener = object : TradeAdapter.Listener {
                override fun clickMe(tradeProject: TradeModel) {
                    TradeExchangeActivity.launchTradeExchangeActivity(
                        requireActivity(),
                        tradeProject
                    )
                }

            })
            tradeAdapter!!.items = tradeData
            rvTrade.adapter = tradeAdapter

        }

    }

    private fun onClearFilter() {
        binding.apply {
            iconChange.setImageResource(R.drawable.ic_sort_arrow_up_down)
            iconLast.setImageResource(R.drawable.ic_sort_arrow_up_down)
            iconVolume.setImageResource(R.drawable.ic_sort_arrow_up_down)
        }
    }

    fun Fragment.hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }

    fun Context.hideKeyboard(view: View) {
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

}