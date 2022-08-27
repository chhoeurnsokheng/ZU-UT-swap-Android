package com.zillennium.utswap.module.project.subscriptionScreen.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.databinding.DialogNavbarProjectSubscriptionConfirmBinding
import com.zillennium.utswap.module.project.subscriptionScreen.bottomSheet.SubscriptionBottomSheet
import com.zillennium.utswap.module.security.securityDialog.FundPasswordDialog
import com.zillennium.utswap.utils.Constants
import eightbitlab.com.blurview.RenderScriptBlur

class SubscriptionConfirmDialog : DialogFragment() {

    private var binding: DialogNavbarProjectSubscriptionConfirmBinding? = null

    companion object {
        fun newInstance(
            id: Int,
            volume: String?,
            title: String?,
            projectName: String?,
            lockTime: String?,
            volumePrice: Double,
            subscriptionPrice: String?
        ): SubscriptionConfirmDialog {
            val subscriptionConfirmDialog = SubscriptionConfirmDialog()
            val args = Bundle()
            args.putInt("id", id)
            args.putString("volume", volume)
            args.putString("title", title)
            args.putString("project_name", projectName)
            args.putString("lock_time", lockTime)
            args.putDouble("volume_price", volumePrice)
            args.putString("subscription_price", subscriptionPrice)
            subscriptionConfirmDialog.arguments = args
            return subscriptionConfirmDialog
        }
    }

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
            DataBindingUtil.inflate(
                inflater,
                R.layout.dialog_navbar_project_subscription_confirm,
                container,
                true
            )
        binding?.txtVolume?.text = arguments?.get("volume").toString()
        binding?.tvSubscriptionsPrice?.text = arguments?.get("subscription_price").toString()
        binding?.tvProjectTitle?.text = arguments?.get("project_name").toString()
        binding?.tvTimeLock?.text = arguments?.get("lock_time").toString()
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {

            btnBack.setOnClickListener {
                Constants.SubscriptionBottomSheet.id = 0
                Constants.SubscriptionBottomSheet.volume = ""
                Constants.SubscriptionBottomSheet.title = ""
                Constants.SubscriptionBottomSheet.project_name = ""
                Constants.SubscriptionBottomSheet.lock_time = ""
                Constants.SubscriptionBottomSheet.volume_price = 0.0
                Constants.SubscriptionBottomSheet.subscription = ""

                val subscriptionBottomSheetDialog: SubscriptionBottomSheet =
                    SubscriptionBottomSheet.newInstance(
                        arguments?.get("id").toString().toInt(),
                        arguments?.get("volume").toString(),
                        arguments?.get("title").toString(),
                        arguments?.get("project_name").toString(),
                        arguments?.get("lock_time").toString(),
                        arguments?.get("volume_price").toString().toDouble(),
                        arguments?.get("subscription_price").toString(),
                    )
                subscriptionBottomSheetDialog.show(
                    requireActivity().supportFragmentManager,
                    "balanceHistoryDetailDialog"
                )
                Handler().postDelayed({
                    dismiss()
                }, 2000)
            }
            btnConfirm.setOnClickListener {

                Constants.SubscriptionBottomSheet.id = 0
                Constants.SubscriptionBottomSheet.volume = ""
                Constants.SubscriptionBottomSheet.title = ""
                Constants.SubscriptionBottomSheet.project_name = ""
                Constants.SubscriptionBottomSheet.lock_time = ""
                Constants.SubscriptionBottomSheet.volume_price = 0.0
                Constants.SubscriptionBottomSheet.subscription = ""

                val fundPasswordDialog: FundPasswordDialog = FundPasswordDialog.newInstance(
                    arguments?.get("id").toString().toInt(),
                    arguments?.get("volume").toString(),
                    arguments?.get("title").toString(),
                    arguments?.get("project_name").toString(),
                    arguments?.get("lock_time").toString(),
                    arguments?.get("volume_price").toString().toDouble(),
                    arguments?.get("subscription_price").toString(),
                )
                fundPasswordDialog.show(
                    requireActivity().supportFragmentManager,
                    "balanceHistoryDetailDialog"
                )
                Handler().postDelayed({
                    dismiss()
                }, 2000)
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

}
