package com.zillennium.utswap.screens.finance.subscriptionScreen.bottomSheet

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
import com.zillennium.utswap.databinding.BottomSheetSubscriptionFilterBinding
import com.zillennium.utswap.models.financeSubscription.FinanceSubscriptionFilterModel
import com.zillennium.utswap.screens.finance.subscriptionScreen.adapter.FinanceSubscriptionFilterAdapter

class FinanceSubscriptionFilterBottomSheet : BottomSheetDialogFragment(),
    AdapterView.OnItemSelectedListener {

    private var binding: BottomSheetSubscriptionFilterBinding? = null
    private var filterBottomSheetList = ArrayList<FinanceSubscriptionFilterModel>()

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
                R.layout.bottom_sheet_subscription_filter,
                container,
                false
            )
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            (view.parent as View).setBackgroundColor(resources.getColor(android.R.color.transparent))

            val title = arrayOf(
                "All Projects",
                "UT Kampot",
                "UT Mondolkiri",
                "UT Pailin",
                "UT Sihanoukville"
            )
            val status = arrayOf(
                1,
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
            )

            for (i in title.indices){
                val subscriptionFilter = FinanceSubscriptionFilterModel(
                    title[i],
                    status[i],
                    image[i],
                )
                filterBottomSheetList.add(subscriptionFilter)
            }

            rvFilter.layoutManager = LinearLayoutManager(UTSwapApp.instance)
            rvFilter.adapter = FinanceSubscriptionFilterAdapter(filterBottomSheetList, onClickAdapter)

        }
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {}

    override fun onNothingSelected(p0: AdapterView<*>?) {}

    companion object {
        fun newInstance(
        ): FinanceSubscriptionFilterBottomSheet {
            val financeSubscriptionFilterBottomSheet = FinanceSubscriptionFilterBottomSheet()
            val args = Bundle()

            financeSubscriptionFilterBottomSheet.arguments = args
            return financeSubscriptionFilterBottomSheet
        }
    }

    private var onClickAdapter: FinanceSubscriptionFilterAdapter.OnClickAdapter = object: FinanceSubscriptionFilterAdapter.OnClickAdapter {
        override fun onClickMe(financeSubscriptionFilterModel: FinanceSubscriptionFilterModel) {
            SettingVariable.finance_subscription_filter.value = financeSubscriptionFilterModel.titleFilter
            dismiss()
        }
    }
}