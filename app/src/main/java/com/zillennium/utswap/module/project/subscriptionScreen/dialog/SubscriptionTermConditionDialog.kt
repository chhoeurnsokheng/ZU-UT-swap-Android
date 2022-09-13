package com.zillennium.utswap.module.project.subscriptionScreen.dialog

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.zillennium.utswap.R
import com.zillennium.utswap.databinding.DialogSubscriptionTermConditionBinding
import com.zillennium.utswap.module.project.subscriptionScreen.SubscriptionActivity

class SubscriptionTermConditionDialog : DialogFragment() {

    private var binding: DialogSubscriptionTermConditionBinding? = null

    companion object {
        fun newInstance(
            id: Int,
            titleDeed: String?,
            landSize: String?,
            basePrice: Double?,
            targetPrice: Double?,
            totalUt: Int?,
            manageBy: String?,
            googleMap: String,
            projectName: String?
        ): SubscriptionTermConditionDialog{
            val subscription = SubscriptionTermConditionDialog()
            val args = Bundle()

            args.putInt("id", id)
            args.putString("project_name", projectName)
            args.putString("title",titleDeed)
            args.putString("landSize",landSize)
            basePrice?.let { args.putDouble("basePrice", it) }
            targetPrice?.let { args.putDouble("targetPrice", it) }
            totalUt?.let { args.putInt("totalUt", it) }
            args.putString("manageBy",manageBy)
            args.putString("googleMap",googleMap)

            subscription.arguments = args
            return subscription
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
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.dialog_subscription_term_condition,
                container,
                true
            )

        binding?.apply {
            tvTitleDeed.text = arguments?.get("title").toString()
            tvLandSize.text = arguments?.get("landSize").toString()
            tvBasePrice.text = arguments?.get("basePrice").toString()
            tvTargetPrice.text = arguments?.get("targetPrice").toString()
            tvTotalUt.text = arguments?.get("totalUt").toString()
            tvManageBy.text = arguments?.get("manageBy").toString()

            // Google map
            layGoogleMap.setOnClickListener {
                val url = arguments?.get("googleMap").toString()
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(intent)
            }

            btnAgree.setOnClickListener {
                SubscriptionActivity.launchSubscriptionActivity(requireContext(),
                    arguments?.get("id").toString(),
                    arguments?.get("project_name").toString()
                )
                dismiss()
            }
        }

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            ivClose.setOnClickListener {
                dismiss()
            }
        }
    }
}