package com.zillennium.utswap.screens.navbar.tradeTab.fragment.tradeDetail.dialog

import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.button.MaterialButton
import com.zillennium.utswap.R
import com.zillennium.utswap.databinding.BottomSheetNavbarProjectSubscriptionBinding
import com.zillennium.utswap.databinding.DialogBuySellBottomSheetBinding

class BuyAndSellBottomSheetDialog : BottomSheetDialogFragment() {

    private var binding: DialogBuySellBottomSheetBinding? = null

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
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_buy_sell_bottom_sheet, container, false)
        return binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {

            (view.parent as View).setBackgroundColor(resources.getColor(android.R.color.transparent))

            btnBuy.setOnClickListener {
                val buyDialog: BuyDialog =
                    BuyDialog.newInstance()
                activity?.supportFragmentManager?.let { buyDialog.show(it, "asaf") }

                dismiss()
            }

            btnSell.setOnClickListener {
                val sellDialog: SellDialog =
                    SellDialog.newInstance()
                activity?.supportFragmentManager?.let { sellDialog.show(it, "asaf") }

                dismiss()
            }
        }
    }

    companion object {
        fun newInstance(): BuyAndSellBottomSheetDialog {
            val buyAndSellBottomSheetDialog = BuyAndSellBottomSheetDialog()
            buyAndSellBottomSheetDialog.arguments = Bundle()
            return buyAndSellBottomSheetDialog
        }
    }
}
