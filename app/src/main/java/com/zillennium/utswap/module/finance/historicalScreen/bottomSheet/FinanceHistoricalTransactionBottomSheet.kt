package com.zillennium.utswap.module.finance.historicalScreen.bottomSheet

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.provider.ContactsContract
import android.view.*
import android.widget.AdapterView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.zillennium.utswap.Datas.GlobalVariable.SettingVariable
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.databinding.BottomSheetFinanceHistoricalTransactionBinding
import com.zillennium.utswap.utils.Constants

class FinanceHistoricalTransactionBottomSheet(
    var historicalTransactionSelect: String,
    var listener: CallBackListener
): BottomSheetDialogFragment() {

    private var binding: BottomSheetFinanceHistoricalTransactionBinding? = null

    override fun getTheme(): Int {
        return R.style.BottomSheetStyle
    }

    interface CallBackListener {
        fun onChangeSelect(historicalTransactionSelect: String)
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

        binding = DataBindingUtil.inflate(inflater, R.layout.bottom_sheet_finance_historical_transaction, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            (view.parent as View).setBackgroundColor(ContextCompat.getColor(UTSwapApp.instance, R.color.transparent))

            btnMyTransactions.setOnClickListener {
                listener.onChangeSelect(Constants.HistoricalTransaction.MyTransactions)
                dismiss()
            }

            btnTrade.setOnClickListener {
                listener.onChangeSelect(Constants.HistoricalTransaction.Trade)
                dismiss()
            }

            btnAllTransactions.setOnClickListener {
                listener.onChangeSelect(Constants.HistoricalTransaction.AllTransactions)
                dismiss()
            }

            when(historicalTransactionSelect){
                Constants.HistoricalTransaction.MyTransactions -> imgCheckMyTransactions.visibility = View.VISIBLE
                Constants.HistoricalTransaction.Trade -> imgCheckTrade.visibility = View.VISIBLE
                Constants.HistoricalTransaction.AllTransactions ->  imgCheckAllTransaction.visibility = View.VISIBLE
            }
        }
    }

}