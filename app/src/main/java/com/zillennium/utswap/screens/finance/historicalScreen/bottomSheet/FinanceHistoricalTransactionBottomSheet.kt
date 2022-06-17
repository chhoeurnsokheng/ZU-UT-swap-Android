package com.zillennium.utswap.screens.finance.historicalScreen.bottomSheet

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.zillennium.utswap.Datas.GlobalVariable.SettingVariable
import com.zillennium.utswap.R
import com.zillennium.utswap.databinding.BottomSheetFinanceHistoricalTransactionBinding
import com.zillennium.utswap.screens.finance.lockUpActivity.bottomSheet.FinanceLockUpBottomSheet

class FinanceHistoricalTransactionBottomSheet: BottomSheetDialogFragment(), AdapterView.OnItemSelectedListener {

    private var binding: BottomSheetFinanceHistoricalTransactionBinding? = null

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
            DataBindingUtil.inflate(inflater, R.layout.bottom_sheet_finance_historical_transaction, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            (view.parent as View).setBackgroundColor(resources.getColor(android.R.color.transparent))

            when(arguments?.get("title")){
                "My Transactions" ->{imgCheckMyTransactions.visibility = View.VISIBLE}
                "Trade" -> imgCheckTrade.visibility = View.VISIBLE
                "All Transactions" ->  imgCheckAllTransaction.visibility = View.VISIBLE
            }

            val item = arrayOf(
                btnMyTransactions,
                btnTrade,
                btnAllTransactions
            )

            val title = arrayOf(
                "My Transactions",
                "Trade",
                "All Transactions"
            )
            val text = arrayOf(
                "UT All Projects",
                "All Projects",
                "All Projects",
            )

            item.mapIndexed { index, it ->
                it.setOnClickListener {
                    SettingVariable.finance_historical_selected.value = title[index].toString()
                    SettingVariable.finance_historical_filter.value = text[index]
                    dismiss()
                }
            }
        }
    }

    companion object {
        fun newInstance(
            title: String?,
        ): FinanceHistoricalTransactionBottomSheet {
            val financeHistoricalTransaction = FinanceHistoricalTransactionBottomSheet()
            val args = Bundle()
            args.putString("title", title)

            financeHistoricalTransaction.arguments = args
            return financeHistoricalTransaction
        }
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {}

    override fun onNothingSelected(p0: AdapterView<*>?) {}

}