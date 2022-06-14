package com.zillennium.utswap.screens.navbar.homeTab.bottomSheet

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.databinding.BottomSheetHomeFinanceBinding
import com.zillennium.utswap.screens.finance.balanceActivity.FinanceBalanceActivity
import com.zillennium.utswap.screens.finance.subscriptionsActivity.FinanceSubscriptionsActivity

class HomeFinanceBottomSheet : BottomSheetDialogFragment(), AdapterView.OnItemSelectedListener {

    private var binding: BottomSheetHomeFinanceBinding? = null

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
            DataBindingUtil.inflate(inflater, R.layout.bottom_sheet_home_finance, container, false)
        return binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {

            (view.parent as View).setBackgroundColor(resources.getColor(android.R.color.transparent))

            balanceButton.setOnClickListener {
                val intent = Intent(UTSwapApp.instance, FinanceBalanceActivity::class.java)
                startActivity(intent)
            }

            subscriptionButton.setOnClickListener {
                val intent = Intent(UTSwapApp.instance, FinanceSubscriptionsActivity::class.java)
                startActivity(intent)
            }

            lockUpButton.setOnClickListener {}

            historicalButton.setOnClickListener {}
        }
    }

    companion object {
        const val TAG = "CustomBottomSheetDialogFragment"
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        TODO("Not yet implemented")
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}