package com.zillennium.utswap.screens.wallet.depositScreen.bottomSheet

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.AdapterView
import android.widget.LinearLayout
import android.widget.Spinner
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.models.PaymentMethod.CustomItem
import com.zillennium.utswap.screens.wallet.depositScreen.adapter.CustomAdapter


class DepositBottomSheetFragment : BottomSheetDialogFragment(), AdapterView.OnItemSelectedListener {
    private var customSpinner: Spinner? = null
    private var customList: ArrayList<CustomItem>? = null
    private var btnSubmitDeposit: LinearLayout? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.layout_deposit_bottomsheet_fragment, container, false)
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        btnSubmitDeposit = view.findViewById(R.id.btn_submit_deposit)
        customSpinner = view.findViewById(R.id.spinner)
        customList = getCustomList()
        val adapter = CustomAdapter(UTSwapApp.instance, customList!!)
        if (customSpinner != null) {
            customSpinner!!.adapter = adapter
            customSpinner!!.onItemSelectedListener = this
        }
        btnSubmitDeposit?.setOnClickListener { dismiss() }
        return view
    }

    private fun getCustomList(): ArrayList<CustomItem> {
        val customList = ArrayList<CustomItem>()
        customList.add(
            CustomItem(
                "Choose your Payment Method",
                R.drawable.ic_baseline_payment_24
            )
        )
        customList.add(CustomItem("Visa/Master Card", R.drawable.visa_card_logo))
        customList.add(CustomItem("ABA PAY", R.drawable.aba_logo))
        customList.add(CustomItem("ACLEDA Bank", R.drawable.acleda_logo))
        return customList
    }

    override fun onItemSelected(adapterView: AdapterView<*>, view: View, i: Int, l: Long) {
        val item = adapterView.selectedItem as CustomItem
    }

    override fun onNothingSelected(adapterView: AdapterView<*>?) {}

    companion object {
        fun newInstance(): DepositBottomSheetFragment {
            val depositBottomSheetFragment = DepositBottomSheetFragment()
            depositBottomSheetFragment.arguments = Bundle()
            return depositBottomSheetFragment
        }
    }
}
