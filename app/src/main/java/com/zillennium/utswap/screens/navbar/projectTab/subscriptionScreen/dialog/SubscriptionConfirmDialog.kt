package com.zillennium.utswap.screens.navbar.projectTab.subscriptionScreen.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.LinearLayout
import androidx.fragment.app.DialogFragment
import com.zillennium.utswap.R
import com.zillennium.utswap.screens.security.securityDialog.FundPasswordDialog

class SubscriptionConfirmDialog : DialogFragment(){

    private var btnBack: LinearLayout? = null
    private var btnConfirm : LinearLayout? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_navbar_project_subscription_confirm, container, false)
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        btnBack = view?.findViewById(R.id.btn_back)
        btnBack?.setOnClickListener {
            dismiss()
        }

        btnConfirm = view?.findViewById(R.id.btn_confirm)
        btnConfirm?.setOnClickListener {
            FundPasswordDialog().show(requireActivity().supportFragmentManager, "balanceHistoryDetailDialog")
//            val subscriptionConfirmDialog: SubscriptionConfirmDialog = SubscriptionConfirmDialog.newInstance()
//            subscriptionConfirmDialog.show(requireActivity().supportFragmentManager, "balanceHistoryDetailDialog")
        }

        return view
    }

    companion object {
        fun newInstance(): SubscriptionConfirmDialog {
            val subscriptionConfirmDialog = SubscriptionConfirmDialog()
            val args = Bundle()
            subscriptionConfirmDialog.arguments = args
            return subscriptionConfirmDialog
        }
    }
}
