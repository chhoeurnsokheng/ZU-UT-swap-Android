package com.zillennium.utswap.screens.wallet.withdrawalScreen.bottomSheet


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.LinearLayout
import android.widget.Spinner
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.models.PaymentMethod.CustomItem
import com.zillennium.utswap.screens.wallet.depositScreen.adapter.CustomAdapter


class WithDrawBottomSheetFragment : BottomSheetDialogFragment(),
    AdapterView.OnItemSelectedListener {
    private var customSpinner: Spinner? = null
    private var customList: ArrayList<CustomItem>? = null
    private var btnWithDrawConfirm: LinearLayout? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.layout_withdraw_bottomsheet_fragment, container, false)
        customSpinner = view.findViewById(R.id.spinner)
        customList = getCustomList()
        val adapter = CustomAdapter(UTSwapApp.instance, customList!!)
        if (customSpinner != null) {
            customSpinner!!.adapter = adapter
            customSpinner!!.onItemSelectedListener = this
        }
        btnWithDrawConfirm = view.findViewById(R.id.btn_withdraw_confirm)
        btnWithDrawConfirm?.setOnClickListener { dismiss() }
        return view
    }

    private fun getCustomList(): ArrayList<CustomItem> {
        val customList = ArrayList<CustomItem>()
        customList.add(
            CustomItem(
                "Choose your Receiving Address",
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
        fun newInstance(): WithDrawBottomSheetFragment {
            val withDrawBottomSheetFragment = WithDrawBottomSheetFragment()
            withDrawBottomSheetFragment.arguments = Bundle()
            return withDrawBottomSheetFragment
        }
    }
}