package com.zillennium.utswap.module.main.trade.tradeExchangeScreen.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.zillennium.utswap.R

class CancelOrderDialog : DialogFragment() {
    internal var view: View? = null
    private var txtOk: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view = inflater.inflate(R.layout.dialog_cancel_place_order, container, false)
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        txtOk = view?.findViewById(R.id.txt_ok)

        txtOk?.setOnClickListener {
            dismiss()
        }

        return view
    }
}