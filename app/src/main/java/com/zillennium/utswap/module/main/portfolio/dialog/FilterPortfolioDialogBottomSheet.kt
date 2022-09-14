package com.zillennium.utswap.module.main.portfolio.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.compose.ui.unit.Constraints
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.zillennium.utswap.Datas.GlobalVariable.SettingVariable
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.databinding.BottomSheetPortfolioFilterBinding
import com.zillennium.utswap.utils.Constants


class FilterPortfolioDialogBottomSheet(
    var portfolioSelectedType: String,
    var listenerPortfolioType: CallBackTypeListener
) : BottomSheetDialogFragment(){

    private var binding: BottomSheetPortfolioFilterBinding? = null

    override fun getTheme(): Int {
        return R.style.BottomSheetStyle
    }

    interface CallBackTypeListener {
        fun onChangeTypeSelected(portfolioSelectedType: String)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        binding = DataBindingUtil.inflate(inflater, R.layout.bottom_sheet_portfolio_filter, container, false)
        return binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {

            (view.parent as View).setBackgroundColor(ContextCompat.getColor(UTSwapApp.instance, android.R.color.transparent))

            btnChange.setOnClickListener {
                listenerPortfolioType.onChangeTypeSelected(Constants.PortfolioFilter.Change)
                dismiss()
            }
            btnPerformance.setOnClickListener {
                listenerPortfolioType.onChangeTypeSelected(Constants.PortfolioFilter.Performance)
                dismiss()
            }
            btnPrice.setOnClickListener {
                listenerPortfolioType.onChangeTypeSelected(Constants.PortfolioFilter.Price)
                dismiss()
            }
            btnBalance.setOnClickListener {
                listenerPortfolioType.onChangeTypeSelected(Constants.PortfolioFilter.Balance)
                dismiss()
            }
            btnWeight.setOnClickListener {
                listenerPortfolioType.onChangeTypeSelected(Constants.PortfolioFilter.Weight)
                dismiss()
            }

            when(portfolioSelectedType){
                Constants.PortfolioFilter.Change -> imgCheckChange.visibility = View.VISIBLE
                Constants.PortfolioFilter.Performance -> imgCheckPerformance.visibility = View.VISIBLE
                Constants.PortfolioFilter.Price -> imgCheckPrice.visibility = View.VISIBLE
                Constants.PortfolioFilter.Balance -> imgCheckBalance.visibility = View.VISIBLE
                Constants.PortfolioFilter.Weight -> imgCheckWeight.visibility = View.VISIBLE
            }
        }
    }
}
