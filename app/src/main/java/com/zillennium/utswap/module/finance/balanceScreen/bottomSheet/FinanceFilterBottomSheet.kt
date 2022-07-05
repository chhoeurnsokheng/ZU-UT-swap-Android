package com.zillennium.utswap.module.finance.balanceScreen.bottomSheet

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.zillennium.utswap.Datas.GlobalVariable.SettingVariable
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.databinding.BottomSheetBalanceFilterBinding

class FinanceFilterBottomSheet: BottomSheetDialogFragment(), AdapterView.OnItemSelectedListener{

    private var binding: BottomSheetBalanceFilterBinding? = null

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
            DataBindingUtil.inflate(inflater, R.layout.bottom_sheet_balance_filter, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            (view.parent as View).setBackgroundColor(ContextCompat.getColor(UTSwapApp.instance, android.R.color.transparent))

            balanceDeposit.setOnClickListener {
                SettingVariable.balance_filter.value = 5
                dismiss()
            }

            balanceWithdrawal.setOnClickListener{
                SettingVariable.balance_filter.value = 4
                dismiss()
            }

            balanceTransfer.setOnClickListener{
                SettingVariable.balance_filter.value = 2
                dismiss()
            }
            balanceTrading.setOnClickListener{
                SettingVariable.balance_filter.value = 1
                dismiss()
            }
            balanceSubscription.setOnClickListener{
                SettingVariable.balance_filter.value = 3
                dismiss()
            }
        }
    }

    companion object {
        fun newInstance(
        ): FinanceFilterBottomSheet {
            val financeFilterBottomSheet = FinanceFilterBottomSheet()
            val args = Bundle()

            financeFilterBottomSheet.arguments = args
            return financeFilterBottomSheet
        }
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {}

    override fun onNothingSelected(p0: AdapterView<*>?) {}
}