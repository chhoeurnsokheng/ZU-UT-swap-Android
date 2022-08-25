package com.zillennium.utswap.module.main.trade.tradeExchangeScreen.dialog

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.zillennium.utswap.Datas.GlobalVariable.SessionVariable
import com.zillennium.utswap.Datas.StoredPreferences.SessionPreferences
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.databinding.BottomSheetExchangeBuySellBinding
import com.zillennium.utswap.utils.Constants
import com.zillennium.utswap.utils.groupingSeparator
import com.zillennium.utswap.utils.groupingSeparatorInt
import kotlin.math.roundToInt

class BuyAndSellBottomSheetDialog(var onDismissListener: OnDismissListener) :
    BottomSheetDialogFragment() {

    private var binding: BottomSheetExchangeBuySellBinding? = null
    private var txtPrice: String? = ""
    private var txtVolume: String? = ""

    private var marketPriceSell: MutableLiveData<String> = MutableLiveData()
    private var marketPriceBuy: MutableLiveData<String> = MutableLiveData()

    private var tradeType: String = "limit"

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
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.bottom_sheet_exchange_buy_sell,
            container,
            false
        )
        return binding?.root

    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            etVolume.requestFocus()

            (view.parent as View).setBackgroundColor(
                ContextCompat.getColor(
                    UTSwapApp.instance,
                    android.R.color.transparent
                )
            )
            var click = true

            txtAvailable.text = Constants.TradeExchange.availableBalance

            txtUt.text = Constants.TradeExchange.utBalance

            SessionVariable.marketPriceSell.observe(this@BuyAndSellBottomSheetDialog){
                if(it.toString() != "" && SessionVariable.marketPriceBuy.value != ""){
                    marketPriceSell.value = SessionVariable.marketPriceSell.value.toString()
                    btnMarket.isClickable = true
                    btnMarket.isEnabled = true
                }else{
                    btnMarket.isClickable = false
                    btnMarket.isEnabled = false
                }
            }

            SessionVariable.marketPriceBuy.observe(this@BuyAndSellBottomSheetDialog){
                if(it.toString() != "" && SessionVariable.marketPriceSell.value != ""){
                    marketPriceBuy.value = SessionVariable.marketPriceBuy.value.toString()
                    btnMarket.isClickable = true
                    btnMarket.isEnabled = true
                }else{
                    btnMarket.isClickable = false
                    btnMarket.isEnabled = false
                }
            }

            btnBuyBottomSheet.setOnClickListener {
                if (SessionPreferences().SESSION_KYC_SUBMIT_STATUS == true && SessionPreferences().SESSION_KYC == false) {
                    dismiss()
                    onDismissListener.onDismiss(true)

                } else {
                    var isHaveError = false
                    if (etVolume.text.isEmpty()) {
                        etVolume.background =
                            ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.bg_error_red)
                        isHaveError = true
                    }

                    if (linearPrice.visibility == View.VISIBLE) {
                        if (etPriceOfVolume.text.isEmpty()) {
                            etPriceOfVolume.background =
                                ContextCompat.getDrawable(
                                    UTSwapApp.instance,
                                    R.drawable.bg_error_red
                                )
                            isHaveError = true
                        }
                    }

                    if (isHaveError) return@setOnClickListener

                    if (click) {
                        val buyDialog: BuyDialog =
                            BuyDialog.newInstance(
                                etVolume.text.toString(),
                                etPriceOfVolume.text.toString(),
                                tradeType
                            )
                        activity?.supportFragmentManager?.let { buyDialog.show(it, "asaf") }

                        dismiss()
                    } else {
                        val marketDialog: MarketDialog =
                            MarketDialog.newInstance(etVolume.text.toString(), "BUY",tradeType)
                        activity?.supportFragmentManager?.let { marketDialog.show(it, "asaf") }
                        dismiss()
                    }
                }

            }


            btnSellBottomSheet.setOnClickListener {
                if (SessionPreferences().SESSION_KYC_SUBMIT_STATUS == true && SessionPreferences().SESSION_KYC == false) {
                    dismiss()
                    onDismissListener.onDismiss(true)

                } else {
                    var isHaveError = false
                    if (etVolume.text.isEmpty()) {
                        etVolume.background =
                            ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.bg_error_red)
                        isHaveError = true
                    }

                    if (linearPrice.visibility == View.VISIBLE) {
                        if (etPriceOfVolume.text.isEmpty()) {
                            etPriceOfVolume.background =
                                ContextCompat.getDrawable(
                                    UTSwapApp.instance,
                                    R.drawable.bg_error_red
                                )
                            isHaveError = true
                        }
                    }

                    if (isHaveError) return@setOnClickListener

                    if (click) {
                        val sellDialog: SellDialog =
                            SellDialog.newInstance(
                                etVolume.text.toString(),
                                etPriceOfVolume.text.toString()
                            )
                        activity?.supportFragmentManager?.let { sellDialog.show(it, "asaf") }
                        dismiss()
                    } else {
                        val marketDialog: MarketDialog =
                            MarketDialog.newInstance(etVolume.text.toString(), "SELL",tradeType)
                        activity?.supportFragmentManager?.let { marketDialog.show(it, "asaf") }
                        dismiss()
                    }
                }
            }

            etVolume.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                @SuppressLint("SetTextI18n")
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    etVolume.background = ContextCompat.getDrawable(
                        UTSwapApp.instance,
                        R.drawable.outline_edittext_change_color_focus
                    )

                    if(etPriceOfVolume.text.toString() == "."){
                        etPriceOfVolume.text.clear()

                    }

                    if(tradeType == "limit"){
                        if(etVolume.text.toString().isNotEmpty() && etPriceOfVolume.text.toString().isNotEmpty())
                        {
                            if(etPriceOfVolume.text.toString() != ".")
                            {
                                val price = etVolume.text.toString().toInt() * etPriceOfVolume.text.toString().toDouble()

                                txtPriceBuy.text = groupingSeparator(price)
                                txtPriceSell.text = groupingSeparator(price)
                            }
                        }else{
                            txtPriceSell.text = "0.00"
                            txtPriceBuy.text = "0.00"
                        }
                    }else{
                        if(etVolume.text.toString().isNotEmpty())
                        {
                            val priceSell = etVolume.text.toString().toInt() * SessionVariable.marketPriceBuy.value.toString().toDouble()
                            txtPriceSell.text = groupingSeparator(priceSell)

                            val priceBuy = etVolume.text.toString().toInt() * SessionVariable.marketPriceSell.value.toString().toDouble()
                            txtPriceBuy.text = groupingSeparator(priceBuy)
                        }else{
                            txtPriceSell.text = "0.00"
                            txtPriceBuy.text = "0.00"
                        }
                    }
                }

                override fun afterTextChanged(p0: Editable?) {

                }
            })

            etPriceOfVolume.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                @SuppressLint("SetTextI18n")
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    etPriceOfVolume.background = ContextCompat.getDrawable(
                        UTSwapApp.instance,
                        R.drawable.outline_edittext_change_color_focus
                    )

                    if(etPriceOfVolume.text.toString() == "."){
                        etPriceOfVolume.text.clear()

                    }

                    if(tradeType == "limit"){
                        if(etVolume.text.toString().isNotEmpty() && etPriceOfVolume.text.toString().isNotEmpty())
                        {
                            if(etPriceOfVolume.text.toString() != ".")
                            {
                                val price = etVolume.text.toString().toInt() * etPriceOfVolume.text.toString().toDouble()
                                txtPriceBuy.text = groupingSeparator(price)
                                txtPriceSell.text = groupingSeparator(price)
                            }
                        }else{
                            txtPriceSell.text = "0.00"
                            txtPriceBuy.text = "0.00"
                        }
                    }else{
                        if(etVolume.text.toString().isNotEmpty())
                        {
                            val priceSell = etVolume.text.toString().toInt() * SessionVariable.marketPriceBuy.value.toString().toDouble()
                            txtPriceSell.text = groupingSeparator(priceSell)

                            val priceBuy = etVolume.text.toString().toInt() * SessionVariable.marketPriceSell.value.toString().toDouble()
                            txtPriceBuy.text = groupingSeparator(priceBuy)
                        }else{
                            etVolume.text.clear()
                            etPriceOfVolume.text.clear()
                            txtPriceSell.text = "0.00"
                            txtPriceBuy.text = "0.00"
                        }
                    }
                }

                override fun afterTextChanged(p0: Editable?) {

                }
            })

            btnMarket.setOnClickListener {

                //change trade type
                tradeType = "market"

                etVolume.text.clear()
                etPriceOfVolume.text.clear()
                txtPriceSell.text = "0"
                txtPriceBuy.text = "0"

                linearPrice.visibility = View.GONE
                btnMarket.background =
                    ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.bg_circular)
                btnMarket.backgroundTintList = ColorStateList.valueOf(
                    ContextCompat.getColor(
                        UTSwapApp.instance,
                        R.color.primary
                    )
                )
                txtMarket.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.white))
                txtLimit.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.primary))
                btnLimit.backgroundTintList =
                    ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.gray))
                etVolume.background = ContextCompat.getDrawable(
                    UTSwapApp.instance,
                    R.drawable.outline_edittext_change_color_focus
                )
                click = false
            }

            btnLimit.setOnClickListener {

                //change trade type
                tradeType = "limit"

                etVolume.text.clear()
                etPriceOfVolume.text.clear()
                txtPriceSell.text = "0"
                txtPriceBuy.text = "0"

                linearPrice.visibility = View.VISIBLE
                btnLimit.background =
                    ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.bg_circular)
                btnLimit.backgroundTintList = ColorStateList.valueOf(
                    ContextCompat.getColor(
                        UTSwapApp.instance,
                        R.color.primary
                    )
                )
                txtLimit.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.white))
                txtMarket.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.primary))
                btnMarket.backgroundTintList =
                    ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.gray))
                etPriceOfVolume.background = ContextCompat.getDrawable(
                    UTSwapApp.instance,
                    R.drawable.outline_edittext_change_color_focus
                )
                etVolume.background = ContextCompat.getDrawable(
                    UTSwapApp.instance,
                    R.drawable.outline_edittext_change_color_focus
                )
                click = true
            }
        }
    }

    /*companion object {
        fun newInstance(): BuyAndSellBottomSheetDialog {
            val buyAndSellBottomSheetDialog = BuyAndSellBottomSheetDialog()
            buyAndSellBottomSheetDialog.arguments = Bundle()
            return buyAndSellBottomSheetDialog
        }
    }*/

    interface OnDismissListener {
        fun onDismiss(isDismiss: Boolean)
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
//        onDismissListener.onDismiss(true)
    }
}
