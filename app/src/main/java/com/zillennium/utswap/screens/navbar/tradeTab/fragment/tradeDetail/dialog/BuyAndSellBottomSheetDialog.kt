package com.zillennium.utswap.screens.navbar.tradeTab.fragment.tradeDetail.dialog

import android.os.Bundle
import android.view.*
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.button.MaterialButton
import com.zillennium.utswap.R

class BuyAndSellBottomSheetDialog : BottomSheetDialogFragment() {

    private var btnBuy: MaterialButton? = null
    private var btnSell: MaterialButton? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_buy_sell_bottom_sheet, container, false)
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)

        btnBuy = view.findViewById(R.id.btn_buy)
        btnSell = view.findViewById(R.id.btn_sell)

        btnBuy?.setOnClickListener {
            val buyDialog: BuyDialog =
                BuyDialog.newInstance()
            activity?.supportFragmentManager?.let { buyDialog.show(it, "asaf") }

            dismiss()
        }

        btnSell?.setOnClickListener {
            val sellDialog: SellDialog =
                SellDialog.newInstance()
            activity?.supportFragmentManager?.let { sellDialog.show(it, "asaf") }

            dismiss()
        }

        return view
    }

    companion object {
        fun newInstance(): BuyAndSellBottomSheetDialog {
            val buyAndSellBottomSheetDialog = BuyAndSellBottomSheetDialog()
            buyAndSellBottomSheetDialog.arguments = Bundle()
            return buyAndSellBottomSheetDialog
        }
    }
}
