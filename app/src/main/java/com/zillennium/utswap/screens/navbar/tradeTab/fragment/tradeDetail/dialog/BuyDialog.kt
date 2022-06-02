package com.zillennium.utswap.screens.navbar.tradeTab.fragment.tradeDetail.dialog

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.google.android.material.button.MaterialButton
import com.zillennium.utswap.R

class BuyDialog : DialogFragment() {
    internal var view: View? = null
    private var btnBuy: MaterialButton? = null
    private var btnCancel: MaterialButton? = null
    private var txtVolume: TextView? = null
    private var txtPrice: TextView? = null
    private var txtGrossValue: TextView? = null
    private var txtNetValue: TextView? = null
    private var volume: String? = ""
    private var price: String? = ""

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view = inflater.inflate(R.layout.dialog_buy, container, false)
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        btnBuy = view?.findViewById(R.id.btn_buy_confirm)
        btnCancel = view?.findViewById(R.id.btn_cancel)
        txtVolume = view?.findViewById(R.id.txt_volume)
        txtPrice = view?.findViewById(R.id.txt_price)
        txtGrossValue = view?.findViewById(R.id.txt_gross_volume)
        txtNetValue = view?.findViewById(R.id.txt_net_value)

        volume = arguments?.getString("volume")
        price = arguments?.getString("price")

        txtVolume?.text = volume.toString()
        txtPrice?.text = price.toString()
        txtGrossValue?.text = (volume?.toFloat()?.times(price!!.toFloat())).toString()
        txtNetValue?.text = (txtGrossValue?.text.toString().toFloat() + 13.81).toString()

        btnBuy?.setOnClickListener {
            dismiss()
        }

        btnCancel?.setOnClickListener {
            dismiss()
        }

        return view
    }


    companion object {
        fun newInstance(
            volume: String?,
            price: String?
        ): BuyDialog {
            val buyDialog = BuyDialog()
            val args = Bundle()
            args.putString("volume", volume)
            args.putString("price", price)
            buyDialog.arguments = args
            return buyDialog
        }
    }
}