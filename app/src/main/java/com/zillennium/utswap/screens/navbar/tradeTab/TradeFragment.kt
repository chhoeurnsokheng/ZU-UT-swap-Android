package com.zillennium.utswap.screens.navbar.tradeTab

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.FragmentNavbarTradeBinding
import com.zillennium.utswap.screens.project.projecrDetailScreen.ProjectDetailAdapter

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
                rvTrade.layoutManager = LinearLayoutManager(UTSwapApp.instance)
                rvTrade.adapter = TradeAdapter(tradeArrayList)


            }

        } catch (error: Exception) {
            // Must be safe
        }
    }
}