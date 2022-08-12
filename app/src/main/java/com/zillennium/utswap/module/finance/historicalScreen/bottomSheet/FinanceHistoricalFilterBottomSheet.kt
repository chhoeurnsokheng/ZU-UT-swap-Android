package com.zillennium.utswap.module.finance.historicalScreen.bottomSheet

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog

import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.zillennium.utswap.Datas.GlobalVariable.SettingVariable
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.databinding.BottomSheetFinanceHistoricalFilterBinding
import com.zillennium.utswap.models.financeHistorical.Historical
import com.zillennium.utswap.module.finance.historicalScreen.adapter.FinanceHistoricalFilterAdapter
import com.zillennium.utswap.utils.Constants
import kotlin.collections.ArrayList

class FinanceHistoricalFilterBottomSheet(
    var listMarketName: ArrayList<Historical.GetMarketNameData>,
    private var historicalFilterSelect: String,
    var filterListener: CallBackFilterListener
)  : BottomSheetDialogFragment() {

    private var binding: BottomSheetFinanceHistoricalFilterBinding? = null
    private var financeHistoricalFilterList = ArrayList<Historical.GetMarketNameData>()

    override fun getTheme(): Int {
        return R.style.BottomSheetStyle
    }

    interface CallBackFilterListener {
        fun onFilterChangeSelect(historicalFilterSelect: String, marketName: String)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        if (dialog is BottomSheetDialog) {
            (dialog as BottomSheetDialog).behavior.skipCollapsed = true
            (dialog as BottomSheetDialog).behavior.state = BottomSheetBehavior.STATE_EXPANDED
        }

        binding = DataBindingUtil.inflate(inflater, R.layout.bottom_sheet_finance_historical_filter, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            (view.parent as View).setBackgroundColor(ContextCompat.getColor(UTSwapApp.instance, R.color.transparent))
            financeHistoricalFilterList.addAll(listMarketName)
            getListName()
        }
    }

    private fun getListName(){
        binding?.apply {
            rvFilterHistorical.layoutManager = LinearLayoutManager(UTSwapApp.instance)
            layoutTxtAllProject.visibility = View.GONE

            when (historicalFilterSelect) {
                Constants.HistoricalTransaction.MyTransactions -> {
                    val historicalAdapter = FinanceHistoricalFilterAdapter(onClickAdapter,)
                    historicalAdapter.items = financeHistoricalFilterList
                    rvFilterHistorical.adapter = historicalAdapter
                }
                Constants.HistoricalTransaction.Trade -> {
                    layoutTxtAllProject.visibility = View.VISIBLE
                    if (SettingVariable.finance_historical_filter.value == "All Projects"){
                        layIconCheckHistorical.visibility = View.VISIBLE
                    }
                    val historicalAdapter = FinanceHistoricalFilterAdapter(onClickAdapter)
                    historicalAdapter.items = financeHistoricalFilterList
                    rvFilterHistorical.adapter = historicalAdapter
                }
                Constants.HistoricalTransaction.AllTransactions -> {
                    val historicalAdapter = FinanceHistoricalFilterAdapter(onClickAdapter)
                    historicalAdapter.items = financeHistoricalFilterList
                    rvFilterHistorical.adapter = historicalAdapter
                }
            }
            onClickAllProjects()
        }
    }

    private fun onClickAllProjects(){
        binding?.apply {
            layoutTxtAllProject.setOnClickListener{
                filterListener.onFilterChangeSelect("All Projects", "")
                dismiss()
            }
        }
    }

    private var onClickAdapter: FinanceHistoricalFilterAdapter.OnClickFilterHistory =
        object : FinanceHistoricalFilterAdapter.OnClickFilterHistory {
            override fun onClickMe(financeHistoricalFilterModel: Historical.GetMarketNameData) {
                financeHistoricalFilterModel.name?.let { financeHistoricalFilterModel.market_name?.let { it1 ->
                    filterListener.onFilterChangeSelect(it, it1) } }
                SettingVariable.finance_historical_filter.value = financeHistoricalFilterModel.name
                dismiss()

            }
        }
}