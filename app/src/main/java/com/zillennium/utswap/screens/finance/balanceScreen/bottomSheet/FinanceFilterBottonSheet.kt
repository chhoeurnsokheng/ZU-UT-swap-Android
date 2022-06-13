package com.zillennium.utswap.screens.finance.balanceScreen.bottomSheet

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.zillennium.utswap.R
import com.zillennium.utswap.databinding.BottomSheetBalanceFilterBinding

class FinanceFilterBottonSheet: BottomSheetDialogFragment(), AdapterView.OnItemSelectedListener{

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
            (view.parent as View).setBackgroundColor(resources.getColor(android.R.color.transparent))

            balanceDeposit.setOnClickListener {
                Toast.makeText(context, "BALANCE DEPOSIT IS CLICKED", Toast.LENGTH_SHORT).show()
            }

            balanceWithdrawal.setOnClickListener{}
            balanceTransfer.setOnClickListener{}
            balanceTransfer.setOnClickListener{}
            balanceTrading.setOnClickListener{}
            balanceSubscription.setOnClickListener{}
        }
    }

    companion object {
        fun newInstance(
        ): FinanceFilterBottonSheet {
            val financeFilterBottonSheet = FinanceFilterBottonSheet()
            val args = Bundle()

            financeFilterBottonSheet.arguments = args
            return financeFilterBottonSheet
        }
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {}

    override fun onNothingSelected(p0: AdapterView<*>?) {}
}