package com.zillennium.utswap.screens.finance.historicalScreen.bottomSheet

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager

import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.zillennium.utswap.Datas.GlobalVariable.SettingVariable
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.databinding.BottomSheetFinanceHistoricalFilterBinding
import com.zillennium.utswap.models.financeHistorical.FinanceHistoricalFilterModel
import com.zillennium.utswap.screens.finance.historicalScreen.adapter.FinanceHistoricalFilterAdapter

class FinanceHistoricalFilterBottomSheet: BottomSheetDialogFragment(), AdapterView.OnItemSelectedListener {

    private var binding: BottomSheetFinanceHistoricalFilterBinding? = null
    private var financeHistoricalFilterList = ArrayList<FinanceHistoricalFilterModel>()

    override fun getTheme(): Int {
        return R.style.BottomSheetStyle
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.bottom_sheet_finance_historical_filter,
                container,
                false
            )
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            (view.parent as View).setBackgroundColor(resources.getColor(android.R.color.transparent))

            rvFilterHistorical.layoutManager = LinearLayoutManager(UTSwapApp.instance)

            SettingVariable.finance_historical_selected.observe(this@FinanceHistoricalFilterBottomSheet){
                when (SettingVariable.finance_historical_selected.value.toString()) {
                    "My Transactions" -> {
                        financeHistoricalFilterList.clear()

                        val title = arrayOf(
                            "UT All Projects",
                            "UT Pochentong 555",
                            "UT Muk Kampul",
                            "UT New Airport",
                            "UT Veng Sreng",
                            "UT Sihanoukville"
                        )
                        val status = arrayOf(
                            1,
                            0,
                            0,
                            0,
                            0,
                            0
                        )
                        val image = arrayOf(
                            R.drawable.ic_baseline_check_24,
                            R.drawable.ic_baseline_check_24,
                            R.drawable.ic_baseline_check_24,
                            R.drawable.ic_baseline_check_24,
                            R.drawable.ic_baseline_check_24,
                            R.drawable.ic_baseline_check_24,
                        )

                        for (i in title.indices){
                            val historicalFilter = FinanceHistoricalFilterModel(
                                title[i],
                                status[i],
                                image[i],
                            )
                            financeHistoricalFilterList.add(historicalFilter)
                        }

                        rvFilterHistorical.adapter = FinanceHistoricalFilterAdapter(financeHistoricalFilterList, onClickAdapter)

                    }
                    "Trade" -> {
                        financeHistoricalFilterList.clear()

                        val title = arrayOf(
                            "All Projects",
                            "Pochentong 555",
                            "Siem Reap 17140",
                            "Muk Kampul 16644",
                            "New Airport 38Ha",
                            "Veng Sreng 2719"
                        )
                        val status = arrayOf(
                            1,
                            0,
                            0,
                            0,
                            0,
                            0
                        )
                        val image = arrayOf(
                            R.drawable.ic_baseline_check_24,
                            R.drawable.ic_baseline_check_24,
                            R.drawable.ic_baseline_check_24,
                            R.drawable.ic_baseline_check_24,
                            R.drawable.ic_baseline_check_24,
                            R.drawable.ic_baseline_check_24,
                        )

                        for (i in title.indices){
                            val historicalFilter = FinanceHistoricalFilterModel(
                                title[i],
                                status[i],
                                image[i],
                            )
                            financeHistoricalFilterList.add(historicalFilter)
                        }

                        rvFilterHistorical.adapter = FinanceHistoricalFilterAdapter(financeHistoricalFilterList, onClickAdapter)
                    }
                    "All Transactions" -> {
                        financeHistoricalFilterList.clear()

                        val title = arrayOf(
                            "All Projects",
                            "Pochentong 555",
                            "Muk Kampul 16644",
                            "Siem Reap 17140",
                            "New Airport 38Ha",
                            "Veng Sreng 2719",
                            "KT 1665"
                        )
                        val status = arrayOf(
                            1,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0
                        )
                        val image = arrayOf(
                            R.drawable.ic_baseline_check_24,
                            R.drawable.ic_baseline_check_24,
                            R.drawable.ic_baseline_check_24,
                            R.drawable.ic_baseline_check_24,
                            R.drawable.ic_baseline_check_24,
                            R.drawable.ic_baseline_check_24,
                            R.drawable.ic_baseline_check_24,
                        )

                        for (i in title.indices){
                            val historicalFilter = FinanceHistoricalFilterModel(
                                title[i],
                                status[i],
                                image[i],
                            )
                            financeHistoricalFilterList.add(historicalFilter)
                        }

                        rvFilterHistorical.adapter = FinanceHistoricalFilterAdapter(financeHistoricalFilterList, onClickAdapter)

                    }
                }
            }
        }
    }

    companion object {
        fun newInstance(
        ): FinanceHistoricalFilterBottomSheet {
            val financeHistoricalFilterBottomSheet = FinanceHistoricalFilterBottomSheet()
            val args = Bundle()

            financeHistoricalFilterBottomSheet.arguments = args
            return financeHistoricalFilterBottomSheet
        }
    }

    private var onClickAdapter: FinanceHistoricalFilterAdapter.OnClickAdapter = object: FinanceHistoricalFilterAdapter.OnClickAdapter {
        override fun onClickMe(financeHistoricalFilterModel: FinanceHistoricalFilterModel) {
            SettingVariable.finance_historical_filter.value = financeHistoricalFilterModel.titleHistorical
            dismiss()
        }
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {}

    override fun onNothingSelected(p0: AdapterView<*>?) {}

}