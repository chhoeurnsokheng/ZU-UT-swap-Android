package com.zillennium.utswap.screens.navbar.tradeTab.tradeExchangeScreen.dialog

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

class SellDialog : DialogFragment() {
    internal var view: View? = null
    private var btnSell: MaterialButton? = null
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
        view = inflater.inflate(R.layout.dialog_sell, container, false)
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        btnSell = view?.findViewById(R.id.btn_sell_confirm)
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

        btnSell?.setOnClickListener {
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
        ): SellDialog {
            val sellDialog = SellDialog()
            val args = Bundle()
            args.putString("volume", volume)
            args.putString("price", price)
            sellDialog.arguments = args
            return sellDialog
        }
    }
}