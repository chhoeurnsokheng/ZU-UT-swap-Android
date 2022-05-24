package com.zillennium.utswap.screens.navbar.tradeTab

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.FragmentNavbarTradeBinding
import com.zillennium.utswap.screens.navbar.tradeTab.fragment.orders.adapter.OrdersAdapter
import com.zillennium.utswap.screens.navbar.tradeTab.fragment.orders.dialog.DeleteOrdersDialog
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
                rvTrade.adapter = TradeAdapter(tradeArrayList,onclickTrade)
            }

        } catch (error: Exception) {
            // Must be safe
        }
    }

    private val onclickTrade: TradeAdapter.OnclickTrade = object : TradeAdapter.OnclickTrade{
        override fun clickMe() {
            Navigation.findNavController(requireView()).navigate(R.id.trade_detail)
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