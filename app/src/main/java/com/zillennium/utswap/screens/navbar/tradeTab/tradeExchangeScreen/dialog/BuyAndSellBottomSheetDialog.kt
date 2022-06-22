package com.zillennium.utswap.screens.navbar.tradeTab.tradeExchangeScreen.dialog

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.databinding.BottomSheetExchangeBuySellBinding

class BuyAndSellBottomSheetDialog : BottomSheetDialogFragment() {

    private var binding: BottomSheetExchangeBuySellBinding? = null

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
        binding = DataBindingUtil.inflate(inflater, R.layout.bottom_sheet_exchange_buy_sell, container, false)
        return binding?.root

    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {

            (view.parent as View).setBackgroundColor(ContextCompat.getColor(UTSwapApp.instance, android.R.color.transparent))
            var click = true

            btnBuy.setOnClickListener {
                var isHaveError = false
                if(etVolume.text.isEmpty())
                {
                    etVolume.background = ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.bg_error_red)
                    isHaveError = true
                }

                if(linearPrice.visibility == View.VISIBLE)
                {
                    if(etPriceOfVolume.text.isEmpty())
                    {
                        etPriceOfVolume.background = ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.bg_error_red)
                        isHaveError = true
                    }
                }

                if (isHaveError) return@setOnClickListener

                if(click)
                {
                    val buyDialog: BuyDialog =
                        BuyDialog.newInstance(
                            etVolume.text.toString(),
                            etPriceOfVolume.text.toString()
                        )
                    activity?.supportFragmentManager?.let { buyDialog.show(it, "asaf") }

                    dismiss()
                }else{
                    val marketDialog: MarketDialog =
                        MarketDialog.newInstance(etVolume.text.toString(), "BUY")
                    activity?.supportFragmentManager?.let { marketDialog.show(it, "asaf") }
                    dismiss()
                }

            }

            btnSell.setOnClickListener {
                var isHaveError = false
                if(etVolume.text.isEmpty())
                {
                    etVolume.background = ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.bg_error_red)
                    isHaveError = true
                }

                if(linearPrice.visibility == View.VISIBLE)
                {
                    if(etPriceOfVolume.text.isEmpty())
                    {
                        etPriceOfVolume.background = ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.bg_error_red)
                        isHaveError = true
                    }
                }

                if (isHaveError) return@setOnClickListener

                if(click)
                {
                    val sellDialog: SellDialog =
                        SellDialog.newInstance(
                            etVolume.text.toString(),
                            etPriceOfVolume.text.toString()
                        )
                    activity?.supportFragmentManager?.let { sellDialog.show(it, "asaf") }

                    dismiss()
                }else{
                    val marketDialog: MarketDialog =
                        MarketDialog.newInstance(etVolume.text.toString(), "SELL")
                    activity?.supportFragmentManager?.let { marketDialog.show(it, "asaf") }
                    dismiss()
                }
            }

            etVolume.addTextChangedListener(object :TextWatcher{
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    etVolume.background = ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.outline_edittext_change_color_focus)
                }

                override fun afterTextChanged(p0: Editable?) {

                }
            })

            etPriceOfVolume.addTextChangedListener(object :TextWatcher{
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    etPriceOfVolume.background = ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.outline_edittext_change_color_focus)
                }

                override fun afterTextChanged(p0: Editable?) {

                }
            })

            btnMarket.setOnClickListener {
                linearPrice.visibility = View.GONE
                btnMarket.background = ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.bg_circular)
                btnMarket.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.color_main))
                txtMarket.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.white))
                txtLimit.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.color_main))
                btnLimit.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.gray))
                etVolume.background = ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.outline_edittext_change_color_focus)
                click = false
            }

            btnLimit.setOnClickListener {
                linearPrice.visibility = View.VISIBLE
                btnLimit.background = ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.bg_circular)
                btnLimit.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.color_main))
                txtLimit.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.white))
                txtMarket.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.color_main))
                btnMarket.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.gray))
                etPriceOfVolume.background = ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.outline_edittext_change_color_focus)
                etVolume.background = ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.outline_edittext_change_color_focus)
                click = true
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
