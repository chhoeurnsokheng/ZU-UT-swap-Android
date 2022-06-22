package com.zillennium.utswap.screens.navbar.tradeTab.tradeExchangeScreen.fragment.orders.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import com.google.android.material.button.MaterialButton
import com.zillennium.utswap.R

class DeleteOrdersDialog : DialogFragment() {
    internal var view: View? = null
    private var btnConfirm: MaterialButton? = null
    private var btnBack: MaterialButton? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view = inflater.inflate(R.layout.dialog_delete_orders, container, false)
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        btnConfirm = view?.findViewById(R.id.btn_confirm)
        btnBack = view?.findViewById(R.id.btn_cancel)

        btnConfirm?.setOnClickListener {
            dismiss()
        }

        btnBack?.setOnClickListener {
            dismiss()
        }

        return view
    }

    companion object {
        fun newInstance(): DeleteOrdersDialog {
            val deleteOrdersDialog = DeleteOrdersDialog()
            deleteOrdersDialog.arguments = Bundle()
            return deleteOrdersDialog
        }
    }

    override fun onResume() {

//        val width = (resources.displayMetrics.widthPixels * 0.92).toInt()
//        dialog!!.window!!.setLayout(
//            280,
//            ViewGroup.LayoutParams.WRAP_CONTENT
//        )
        super.onResume()
    }
}