package com.zillennium.utswap.screens.navbar.tradeTab.fragment.tradeDetail.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.google.android.material.button.MaterialButton
import com.zillennium.utswap.R

class BuyDialog : DialogFragment() {
    internal var view: View? = null
    private var btnBuy: MaterialButton? = null
    private var btnCancel: MaterialButton? = null

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

        btnBuy?.setOnClickListener {
            dismiss()
        }

        btnCancel?.setOnClickListener {
            dismiss()
        }

        return view
    }


    companion object {
        fun newInstance(): BuyDialog {
            val buyDialog = BuyDialog()
            val args = Bundle()
            buyDialog.arguments = args
            return buyDialog
        }
    }
}