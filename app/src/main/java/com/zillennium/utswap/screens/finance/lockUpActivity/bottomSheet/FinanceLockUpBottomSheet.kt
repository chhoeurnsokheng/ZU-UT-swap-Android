package com.zillennium.utswap.screens.finance.lockUpActivity.bottomSheet

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
import com.zillennium.utswap.databinding.BottomSheetFinanceLockUpBinding
import com.zillennium.utswap.screens.navbar.portfolioTab.dialog.FilterPortfolioDialogBottomSheet

class FinanceLockUpBottomSheet: BottomSheetDialogFragment(), AdapterView.OnItemSelectedListener {

    private var binding: BottomSheetFinanceLockUpBinding? = null

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
            DataBindingUtil.inflate(inflater, R.layout.bottom_sheet_finance_lock_up, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {

            (view.parent as View).setBackgroundColor(ContextCompat.getColor(UTSwapApp.instance, android.R.color.transparent))

            when(arguments?.get("title")){
                "Buy Back" -> imgCheckBuyBack.visibility = View.VISIBLE
                "Swap" -> imgCheckSwap.visibility = View.VISIBLE
            }

            val item = arrayOf(
                btnBuyBack,
                btnSwap,
            )

            val title = arrayOf(
                "Buy Back",
                "Swap",
            )

            item.mapIndexed { index, it ->
                it.setOnClickListener {
                    SettingVariable.finance_lock_up_selected.value = title[index].toString()
                    dismiss()
                }
            }
        }
    }

    companion object {
        fun newInstance(
            title: String?,
        ): FinanceLockUpBottomSheet {
            val financeLockUpBottomSheet = FinanceLockUpBottomSheet()
            val args = Bundle()
            args.putString("title", title)

            financeLockUpBottomSheet.arguments = args
            return financeLockUpBottomSheet
        }
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {}

    override fun onNothingSelected(p0: AdapterView<*>?) {}
}