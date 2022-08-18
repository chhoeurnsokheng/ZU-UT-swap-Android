package com.zillennium.utswap.module.finance.balanceScreen.bottomSheet

import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.zillennium.utswap.Datas.GlobalVariable.SettingVariable
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.databinding.BottomSheetBalanceFilterBinding
import com.zillennium.utswap.utils.Constants

class FinanceFilterBottomSheet(
    var balanceFilterSelect: String,
    var listenerFilter: CallBackFilterListener
): BottomSheetDialogFragment(){

    private var binding: BottomSheetBalanceFilterBinding? = null

    override fun getTheme(): Int {
        return R.style.BottomSheetStyle
    }

    interface CallBackFilterListener {
        fun onChangeFilterSelected(balanceFilterSelected: String)
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
        binding =
            DataBindingUtil.inflate(inflater, R.layout.bottom_sheet_balance_filter, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            (view.parent as View).setBackgroundColor(ContextCompat.getColor(UTSwapApp.instance, android.R.color.transparent))

            balanceDeposit.setOnClickListener {
                listenerFilter.onChangeFilterSelected(Constants.UserBalance.Deposit)
                dismiss()
            }

            balanceWithdrawal.setOnClickListener{
                listenerFilter.onChangeFilterSelected(Constants.UserBalance.Withdrawal)
                dismiss()
            }

            balanceTransfer.setOnClickListener{
                listenerFilter.onChangeFilterSelected(Constants.UserBalance.Transfer)
                dismiss()
            }
            balanceTrading.setOnClickListener{
                listenerFilter.onChangeFilterSelected(Constants.UserBalance.Trading)
                dismiss()
            }
            balanceSubscription.setOnClickListener{
                listenerFilter.onChangeFilterSelected(Constants.UserBalance.Subscriptions)
                dismiss()
            }

            when(balanceFilterSelect){
                Constants.UserBalance.Deposit -> imgCheckDeposit.visibility = View.VISIBLE
                Constants.UserBalance.Withdrawal -> imgCheckWithdrawal.visibility = View.VISIBLE
                Constants.UserBalance.Transfer -> imgCheckTransfer.visibility = View.VISIBLE
                Constants.UserBalance.Trading -> imgCheckTrading.visibility = View.VISIBLE
                Constants.UserBalance.Subscriptions -> imgCheckSubscriptions.visibility = View.VISIBLE
            }
        }
    }
}