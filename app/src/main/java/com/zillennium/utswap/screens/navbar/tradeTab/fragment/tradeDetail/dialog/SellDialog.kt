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

class SellDialog : DialogFragment() {
    internal var view: View? = null
    private var btnSell: MaterialButton? = null
    private var btnCancel: MaterialButton? = null

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

        btnSell?.setOnClickListener {
            dismiss()
        }

        btnCancel?.setOnClickListener {
            dismiss()
        }

        return view
    }


    companion object {
        fun newInstance(): SellDialog {
            val sellDialog = SellDialog()
            val args = Bundle()
            sellDialog.arguments = args
            return sellDialog
        }
    }
}