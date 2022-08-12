package com.zillennium.utswap.module.finance.subscriptionScreen.bottomSheet

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.zillennium.utswap.Datas.GlobalVariable.SettingVariable
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.databinding.BottomSheetFinanceSubscriptionFilterBinding
import com.zillennium.utswap.models.financeSubscription.FinanceSubscriptionFilterModel
import com.zillennium.utswap.models.financeSubscription.SubscriptionObject
import com.zillennium.utswap.module.finance.subscriptionScreen.adapter.FinanceSubscriptionFilterAdapter

class FinanceSubscriptionFilterBottomSheet(var listProject: ArrayList<SubscriptionObject.ProjectList>) : BottomSheetDialogFragment(),
    AdapterView.OnItemSelectedListener {

    private var binding: BottomSheetFinanceSubscriptionFilterBinding? = null

    override fun getTheme(): Int {
        return R.style.BottomSheetStyle
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.bottom_sheet_finance_subscription_filter,
                container,
                false
            )
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            (view.parent as View).setBackgroundColor(ContextCompat.getColor(UTSwapApp.instance, android.R.color.transparent))
            rvFilter.layoutManager = LinearLayoutManager(UTSwapApp.instance)
            rvFilter.adapter = FinanceSubscriptionFilterAdapter(listProject, onClickAdapter)

        }
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {}

    override fun onNothingSelected(p0: AdapterView<*>?) {}

    /*companion object {
        fun newInstance(
        ): FinanceSubscriptionFilterBottomSheet {
            val financeSubscriptionFilterBottomSheet = FinanceSubscriptionFilterBottomSheet()
            val args = Bundle()

            financeSubscriptionFilterBottomSheet.arguments = args
            return financeSubscriptionFilterBottomSheet
        }
    }*/

    private var onClickAdapter: FinanceSubscriptionFilterAdapter.OnClickAdapter = object: FinanceSubscriptionFilterAdapter.OnClickAdapter {
        override fun onClickMe(projectId: String, projectName: String) {
            SettingVariable.finance_subscription_filter.value = projectId
            SettingVariable.finance_subscription_filter_project_name.value = projectName
            dismiss()
        }
    }
}