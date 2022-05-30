package com.zillennium.utswap.screens.navbar.projectTab.subscriptionScreen.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.databinding.DialogNavbarProjectSubscriptionConfirmBinding
import com.zillennium.utswap.screens.security.securityDialog.FundPasswordDialog
import eightbitlab.com.blurview.RenderScriptBlur

class SubscriptionConfirmDialog : DialogFragment(){

    private var binding: DialogNavbarProjectSubscriptionConfirmBinding? = null

    override fun getTheme(): Int {
        return R.style.FullScreenDialog
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        binding =
            DataBindingUtil.inflate(inflater, R.layout.dialog_navbar_project_subscription_confirm, container, true)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            btnBack.setOnClickListener {
                dismiss()
            }
            btnConfirm.setOnClickListener {
                FundPasswordDialog().show(requireActivity().supportFragmentManager, "balanceHistoryDetailDialog")
            }
            activity?.apply {
                blurView.setupWith(findViewById<ViewGroup>(android.R.id.content))
                    .setFrameClearDrawable(window.decorView.background)
                    .setBlurAlgorithm(RenderScriptBlur(UTSwapApp.instance))
                    .setBlurRadius(20f)
                    .setBlurAutoUpdate(true)
                    .setHasFixedTransformationMatrix(true)
            }
        }

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
