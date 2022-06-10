package com.zillennium.utswap.screens.navbar.tradeTab.tradeExchangeScreen.dialog

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.google.android.material.button.MaterialButton
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp

class MarketDialog : DialogFragment() {
    internal var view: View? = null
    private var btnBuy: MaterialButton? = null
    private var btnCancel: MaterialButton? = null
    private var txtVolume: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view = inflater.inflate(R.layout.dialog_market, container, false)
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        btnBuy = view?.findViewById(R.id.btn_buy_confirm)
        btnCancel = view?.findViewById(R.id.btn_cancel)
        txtVolume = view?.findViewById(R.id.txt_volume)

        txtVolume?.text = arguments?.get("volume").toString()
        btnBuy?.text = arguments?.get("status").toString()
        btnBuy?.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.white))

        if(arguments?.get("status") == "SELL")
        {
            btnBuy?.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.main_red))
        }else{
            btnBuy?.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.success))
        }

        btnCancel?.setOnClickListener {
            dismiss()
        }

        btnBuy?.setOnClickListener {
            dismiss()
        }


        return view
    }


    companion object {
        fun newInstance(
            volume: String?,
            txtStatus: String?
        ): MarketDialog {
            val marketDialog = MarketDialog()
            val args = Bundle()
            args.putString("volume", volume)
            args.putString("status", txtStatus)
            marketDialog.arguments = args
            return marketDialog
        }
    }
}