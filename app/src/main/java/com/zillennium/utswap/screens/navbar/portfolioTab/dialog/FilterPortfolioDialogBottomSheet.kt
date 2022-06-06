package com.zillennium.utswap.screens.navbar.portfolioTab.dialog

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.zillennium.utswap.Datas.GlobalVariable.SettingVariable
import com.zillennium.utswap.R
import com.zillennium.utswap.databinding.BottomSheetFilterPortfolioBinding


class FilterPortfolioDialogBottomSheet : BottomSheetDialogFragment(){

    private var binding: BottomSheetFilterPortfolioBinding? = null

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
        binding = DataBindingUtil.inflate(inflater, R.layout.bottom_sheet_filter_portfolio, container, false)
        return binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {

            (view.parent as View).setBackgroundColor(resources.getColor(android.R.color.transparent))

            when(arguments?.get("title")){
                "Performance" -> imgCheckPerformance.visibility = View.VISIBLE
                "Change" -> imgCheckChange.visibility = View.VISIBLE
                "Price" -> imgCheckPrice.visibility = View.VISIBLE
                "Balance" -> imgCheckBalance.visibility = View.VISIBLE
                "Weight" -> imgCheckWeight.visibility = View.VISIBLE
            }

            val item = arrayOf(
                btnChange,
                btnPerformance,
                btnPrice,
                btnBalance,
                btnWeight
            )

            val title = arrayOf(
                "Change",
                "Performance",
                "Price",
                "Balance",
                "Weight"
            )

            item.mapIndexed { index, it ->
                it.setOnClickListener {
                    SettingVariable.portfolio_selected.value = title.get(index).toString()
                    dismiss()
                }
            }

        }
    }

    companion object {
        fun newInstance(
            title: String?,
        ): FilterPortfolioDialogBottomSheet {
            val filterPortfolioDialogBottomSheet = FilterPortfolioDialogBottomSheet()
            val args = Bundle()
            args.putString("title",title)

            filterPortfolioDialogBottomSheet.arguments = args
            return filterPortfolioDialogBottomSheet
        }
    }

}
