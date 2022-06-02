package com.zillennium.utswap.screens.navbar.tradeTab

import android.content.Intent
import android.content.res.ColorStateList
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.FragmentNavbarTradeBinding
import com.zillennium.utswap.screens.navbar.tradeTab.fragment.tradeDetail.TradeDetailFragment

class TradeFragment :
    BaseMvpFragment<TradeView.View, TradeView.Presenter, FragmentNavbarTradeBinding>(),
    TradeView.View {

    override var mPresenter: TradeView.Presenter = TradePresenter()
    override val layoutResource: Int = R.layout.fragment_navbar_trade

    override fun initView() {
        super.initView()
        try {
            binding.apply {

                imgMenu.setOnClickListener {

                }

                imgNotification.setOnClickListener {

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

                val tradeArrayList = ArrayList<Trade>()

                for (i in project.indices) {
                    val trade = Trade(
                        project[i],
                        change[i],
                        last[i],
                        volume[i]
                    )
                    tradeArrayList.add(trade)
                }
                for (i in project.indices) {
                    val trade = Trade(
                        project[i],
                        change[i],
                        last[i],
                        volume[i]
                    )
                    tradeArrayList.add(trade)
                }
//                rvTrade.layoutManager = GridLayoutManager(UTSwapApp.instance, 2)
                rvTrade.layoutManager = LinearLayoutManager(UTSwapApp.instance)
                rvTrade.adapter = TradeAdapter(tradeArrayList, onclickTrade)


                etSearch.setOnFocusChangeListener { _, hasFocus ->
                    if (hasFocus) {
                        imgSearch.imageTintList =
                            ColorStateList.valueOf(resources.getColor(R.color.color_main))
                        laySearch.backgroundTintList =
                            ColorStateList.valueOf(resources.getColor(R.color.color_main))
                    } else {
                        imgSearch.imageTintList =
                            ColorStateList.valueOf(resources.getColor(R.color.light_gray))
                        laySearch.backgroundTintList =
                            ColorStateList.valueOf(resources.getColor(R.color.light_gray))
                    }
                }

                etSearch.addTextChangedListener(object: TextWatcher {
                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                    }

                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                    }

                    override fun afterTextChanged(p0: Editable?) {

                    }

                })
                rvTrade.adapter = TradeAdapter(tradeArrayList,onclickTrade)

                txtSubscribe.setOnClickListener {
                    Navigation.findNavController(requireView()).navigate(R.id.action_to_navigation_navbar_project_subscription)
                }

                txtDetail.setOnClickListener {
                    Navigation.findNavController(requireView()).navigate(R.id.action_to_navigation_navbar_project_info)
                }
            }

        } catch (error: Exception) {
            // Must be safe
        }
    }

    private val onclickTrade: TradeAdapter.OnclickTrade = object : TradeAdapter.OnclickTrade{
        override fun clickMe() {
            findNavController().navigate(R.id.action_to_trade_detail)
//            Navigation.findNavController(requireView()).navigate(R.id.trade_detail)
        }

    }

//    fun Fragment.hideKeyboard() {
//        view?.let { activity?.hideKeyboard(it) }
//    }
//
//    fun Context.hideKeyboard(view: View) {
//        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
//        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
//    }
}