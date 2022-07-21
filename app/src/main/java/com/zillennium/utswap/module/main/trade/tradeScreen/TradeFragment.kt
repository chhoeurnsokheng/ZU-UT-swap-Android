package com.zillennium.utswap.module.main.trade.tradeScreen

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.zillennium.utswap.Datas.GlobalVariable.SessionVariable
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.FragmentNavbarTradeBinding
import com.zillennium.utswap.models.TradeModel
import com.zillennium.utswap.module.account.accountScreen.AccountActivity
import com.zillennium.utswap.module.main.trade.tradeExchangeScreen.TradeExchangeActivity
import com.zillennium.utswap.module.main.trade.tradeScreen.adapter.TradeAdapter
import com.zillennium.utswap.module.project.projectInfoScreen.ProjectInfoActivity
import com.zillennium.utswap.module.project.subscriptionScreen.SubscriptionActivity
import com.zillennium.utswap.module.security.securityActivity.signInScreen.SignInActivity
import com.zillennium.utswap.module.system.notification.NotificationActivity


class TradeFragment :
    BaseMvpFragment<TradeView.View, TradeView.Presenter, FragmentNavbarTradeBinding>(),
    TradeView.View {

    override var mPresenter: TradeView.Presenter = TradePresenter()
    override val layoutResource: Int = R.layout.fragment_navbar_trade
    private var tradeArrayList = ArrayList<TradeModel>()
    private var tradeAdapter: TradeAdapter? = null

    private var search: String = ""
    private var filter: Int = 0 // 0 = no sort,
    // 1 = asc change, 2 = desc change,
    // 3 = asc last, 4 = desc last,
    // 5 asc volume, 6 desc volume

    override fun initView() {
        super.initView()
        try {
            binding.apply {

                //check share preference
                SessionVariable.SESSION_STATUS.observe(this@TradeFragment) {
                    if(SessionVariable.SESSION_STATUS.value == true){
                        imgMenu.setOnClickListener {
                            val intent = Intent(UTSwapApp.instance, AccountActivity::class.java)
                            startActivity(intent)
                            requireActivity().overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
                        }
                        imgNotification.setOnClickListener {
                            val intent = Intent(UTSwapApp.instance, NotificationActivity::class.java)
                            startActivity(intent)
                        }
                        txtCountNotification.visibility =View.VISIBLE

                    }else{
                        imgMenu.setOnClickListener {
                            val intent = Intent(UTSwapApp.instance, SignInActivity::class.java)
                            startActivity(intent)
                        }
                        imgNotification.setOnClickListener {
                            val intent = Intent(UTSwapApp.instance, SignInActivity::class.java)
                            startActivity(intent)
                        }
                        txtCountNotification.visibility= View.GONE
                    }
                }


                val project = arrayOf(
                    "Siem Reap 17140",
                    "Muk Kampul 16644",
                    "KT 1665",
                    "New Airport 38Ha",
                    "Siem Reap 17140",
                    "Muk Kampul 16644",
                    "KT 1665",
                    "New Airport 38Ha"
                )
                val change = doubleArrayOf(
                    -1.68,
                    1.68,
                    -1.68,
                    1.68,
                    -1.68,
                    1.68,
                    -1.68,
                    1.68
                )
                val last = doubleArrayOf(
                    19.68,
                    1.68,
                    1.68,
                    1.68,
                    19.68,
                    1.68,
                    1.68,
                    1.68
                )
                val volume = intArrayOf(
                    16800,
                    168,
                    168420,
                    168,
                    16800,
                    168,
                    168420,
                    168
                )

                for (i in project.indices) {
                    val trade = TradeModel(
                        project[i],
                        change[i],
                        last[i],
                        volume[i]
                    )
                    tradeArrayList.add(trade)
                }
                for (i in project.indices) {
                    val trade = TradeModel(
                        project[i],
                        change[i],
                        last[i],
                        volume[i]
                    )
                    tradeArrayList.add(trade)
                }

                rvTrade.layoutManager = LinearLayoutManager(UTSwapApp.instance)
                tradeAdapter = TradeAdapter(tradeArrayList, onclickTrade)
                rvTrade.adapter = tradeAdapter

                etSearch.setOnFocusChangeListener { _, hasFocus ->
                    if (hasFocus) {
                        laySearch.backgroundTintList =
                            ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.primary))
                    } else {
                        laySearch.backgroundTintList =
                            ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.light_gray))
                    }
                }

                etSearch.addTextChangedListener(object: TextWatcher {
                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                    }

                    override fun onTextChanged(char: CharSequence, p1: Int, p2: Int, p3: Int) {
                        search = char.toString()
                        getFilterData()
                    }

                    override fun afterTextChanged(p0: Editable?) {
                    }

                })

                layProject.setOnClickListener {
                    filter = 0
                    getFilterData()
                }

                layChange.setOnClickListener {
                    filter = when(filter){
                        2 -> 0
                        1 -> 2
                        else -> 1
                    }
                    getFilterData()
                }

                layLast.setOnClickListener {
                    filter = when(filter){
                        4 -> 0
                        3 -> 4
                        else -> 3
                    }
                    getFilterData()
                }

                layVolume.setOnClickListener {
                    filter = when(filter){
                        6 -> 0
                        5 -> 6
                        else -> 5
                    }
                    getFilterData()
                }

                txtSubscribe.setOnClickListener {
                    val intent: Intent = Intent(UTSwapApp.instance, SubscriptionActivity::class.java)
                    startActivity(intent)
//                    Navigation.findNavController(requireView()).navigate(R.id.action_to_navigation_navbar_project_subscription)
                }

                txtDetail.setOnClickListener {
                    val intent: Intent = Intent(UTSwapApp.instance, ProjectInfoActivity::class.java)
                    startActivity(intent)
//                    Navigation.findNavController(requireView()).navigate(R.id.action_to_navigation_navbar_project_info)
                }

                imgMenu.setOnClickListener {
                    val intent = Intent(UTSwapApp.instance, SignInActivity::class.java)
                    startActivity(intent)
                }

                icSearch.setOnClickListener {
                    linearLayoutSearch.visibility = View.VISIBLE
                }

                txtCancel.setOnClickListener {
                    linearLayoutSearch.visibility = View.GONE
                    etSearch.text.clear()
                    hideKeyboard()
                }
            }

        } catch (error: Exception) {
            // Must be safe
        }
    }

    private val onclickTrade: TradeAdapter.OnclickTrade = object : TradeAdapter.OnclickTrade {
        override fun clickMe() {
//            findNavController().navigate(R.id.action_to_trade_detail)
//            Navigation.findNavController(requireView()).navigate(R.id.trade_detail)
            val intent = Intent(UTSwapApp.instance, TradeExchangeActivity::class.java)
            startActivity(intent)
        }
    }

    private fun getFilterData(){

        val tradeData = ArrayList<TradeModel>()
        binding.apply {

            if(search.isNotEmpty()){
                tradeData.clear()
                tradeArrayList.map {
                    if(it.project.contains(search, ignoreCase = true)){
                        tradeData.add(it)
                    }
                }
            }else{
                tradeData.addAll(tradeArrayList)
            }

            onClearFilter()
            when(filter){
                6 -> {
                    tradeData.sortByDescending { it.volume }
                    iconVolume.setImageResource(R.drawable.ic_sort_arrow_up_down_selected)
                    iconVolume.rotation = 180f
                }
                5 -> {
                    tradeData.sortBy { it.volume }
                    iconVolume.setImageResource(R.drawable.ic_sort_arrow_up_down_selected)
                    iconVolume.rotation = 0f
                }
                4 -> {
                    tradeData.sortByDescending { it.last }
                    iconLast.setImageResource(R.drawable.ic_sort_arrow_up_down_selected)
                    iconLast.rotation = 180f
                }
                3 -> {
                    tradeData.sortBy { it.last }
                    iconLast.setImageResource(R.drawable.ic_sort_arrow_up_down_selected)
                    iconLast.rotation = 0f
                }
                2 -> {
                    tradeData.sortByDescending { it.change }
                    iconChange.setImageResource(R.drawable.ic_sort_arrow_up_down_selected)
                    iconChange.rotation = 180f
                }
                1 -> {
                    tradeData.sortBy { it.change }
                    iconChange.setImageResource(R.drawable.ic_sort_arrow_up_down_selected)
                    iconChange.rotation = 0f
                }
                else -> {
                    onClearFilter()
                }
            }

            tradeAdapter = TradeAdapter(tradeData, onclickTrade)
            rvTrade.adapter = tradeAdapter

        }

    }

    private fun onClearFilter(){
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
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}