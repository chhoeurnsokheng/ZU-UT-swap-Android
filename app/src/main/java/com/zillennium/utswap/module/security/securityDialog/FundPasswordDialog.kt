package com.zillennium.utswap.module.security.securityDialog

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.*
import androidx.core.content.ContextCompat
import androidx.core.view.children
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.gis.z1android.api.errorhandler.CallbackWrapper
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.api.manager.ApiManager
import com.zillennium.utswap.api.manager.ApiProjectImp
import com.zillennium.utswap.databinding.DialogSecurityFundPasswordBinding
import com.zillennium.utswap.models.project.SubscriptionProject
import com.zillennium.utswap.module.project.subscriptionScreen.SubscriptionActivity
import com.zillennium.utswap.module.project.subscriptionScreen.dialog.SubscriptionConfirmDialog
import eightbitlab.com.blurview.RenderScriptBlur
import rx.Subscription
import java.lang.Exception


class FundPasswordDialog : DialogFragment() {

    private var binding: DialogSecurityFundPasswordBinding? = null
    private var codes: String = ""
    private var subCode: String = ""
    private var subscription: Subscription? = null

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
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.dialog_security_fund_password,
            container,
            true
        )
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            activity?.apply {
                blurView.setupWith(findViewById<ViewGroup>(android.R.id.content))
                    .setFrameClearDrawable(window.decorView.background)
                    .setBlurAlgorithm(RenderScriptBlur(UTSwapApp.instance))
                    .setBlurRadius(20f)
                    .setBlurAutoUpdate(false)
                    .setHasFixedTransformationMatrix(false)
                    .setOverlayColor(getColor(R.color.white))

                imgBack.setOnClickListener {
                    when (javaClass.simpleName.toString()) {
                        "WithdrawActivity" -> {
                            dismiss()
                        }
                        "TransferActivity" -> {
                            dismiss()
                        }
                        else -> {
                            val subscriptionConfirmDialog: SubscriptionConfirmDialog =
                                SubscriptionConfirmDialog.newInstance(
                                    arguments?.get("id").toString().toInt(),
                                    arguments?.get("volume").toString(),
                                    arguments?.get("title").toString(),
                                    arguments?.get("project_name").toString(),
                                    arguments?.get("lock_time").toString(),
                                    arguments?.get("volume_price").toString().toDouble(),
                                    arguments?.get("subscription_price").toString()
                                )
                            subscriptionConfirmDialog.show(requireActivity().supportFragmentManager, "balanceHistoryDetailDialog")
                            dismiss()
                        }
                    }
                }


                val numberList = arrayListOf(
                    number0,
                    number1,
                    number2,
                    number3,
                    number4,
                    number5,
                    number6,
                    number7,
                    number8,
                    number9,
                )

                numberList.forEachIndexed { index, element ->
                    element.setOnClickListener {
                        setNumber(index)
                    }
                }

                removeNumber.setOnClickListener { removeNumber() }
            }
        }
    }

    private fun setNumber(number: Number) {
        codes += if (codes.length < 4) {
            number.toString()
        } else {
            ""
        }
        setPingCode()
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun setPingCode() {
        binding?.apply {
            for (pingCode in layPingCode.children) {
                pingCode.background =
                    ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.bg_circular_border)
            }

            if (codes.isNotEmpty()) {
                for (i in codes.indices) {
                    layPingCode.getChildAt(i).background =
                        ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.bg_circular)
                }
            } else {
                for (i in subCode.indices) {
                    layPingCode.getChildAt(i).background =
                        ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.bg_circular)
                }
            }



            if (codes.length == 4) {
                subCode = codes
                layProgressBar.visibility = View.VISIBLE

                val volume = arguments?.getString("volume")?.replace("\\s".toRegex(), "")?.toInt()
                val id = arguments?.getInt("id")
                onCheckSubscriptionConfirmPassword(
                    SubscriptionProject.SubscribeConfirmBody(
                        id,
                        volume,
                        codes
                    ), UTSwapApp.instance
                )
            } else {
                imgIcon.setImageResource(R.drawable.ic_fund_key_normal)
                txtMessage.text = "Please enter your fund password"
                txtMessage.setTextColor(
                    ContextCompat.getColor(
                        UTSwapApp.instance,
                        R.color.primary
                    )
                )
                for (pingCode in layPingCode.children) {
                    pingCode.backgroundTintList = ColorStateList.valueOf(
                        ContextCompat.getColor(
                            UTSwapApp.instance,
                            R.color.primary
                        )
                    )
                }
            }

        }
    }

    private fun removeNumber() {
        if (codes.isNotEmpty()) {
            if (codes.length in 1..4) {
                codes = codes.substring(0, codes.length - 1)
                setPingCode()
            }
        } else {
            if (subCode.length in 1..4) {
                subCode = subCode.substring(0, subCode.length - 1)
                setPingCode()
            }
        }


    }


    /**   Subscription Project  Order **/
    private fun onCheckSubscriptionSuccess(data: SubscriptionProject.SubscriptionConfirmRes) {

        binding?.apply {
            imgIcon.setImageResource(R.drawable.ic_fund_key_success)
            txtMessage.text = "Success"
            txtMessage.setTextColor(
                ContextCompat.getColor(
                    UTSwapApp.instance,
                    R.color.success
                )
            )
            for (pingCode in layPingCode.children) {
                pingCode.backgroundTintList = ColorStateList.valueOf(
                    ContextCompat.getColor(
                        UTSwapApp.instance,
                        R.color.success
                    )
                )
            }

            Handler().postDelayed({
                layProgressBar.visibility = View.GONE
                dismiss()
            }, 3000)
        }
    }

    private fun onCheckSubscriptionFail(data: SubscriptionProject.SubscriptionConfirmRes) {
        binding?.apply {
            imgIcon.setImageResource(R.drawable.ic_fund_key_invalid)
            txtMessage.text = "Invalid"
            txtMessage.setTextColor(
                ContextCompat.getColor(
                    UTSwapApp.instance,
                    R.color.danger
                )
            )
            for (pingCode in layPingCode.children) {
                pingCode.backgroundTintList = ColorStateList.valueOf(
                    ContextCompat.getColor(
                        UTSwapApp.instance,
                        R.color.danger
                    )
                )
            }
            layProgressBar.visibility = View.GONE
            codes = ""
        }
    }

    private fun onCheckSubscriptionConfirmPassword(
        body: SubscriptionProject.SubscribeConfirmBody,
        context: Context
    ) {
        subscription?.unsubscribe()
        subscription = ApiProjectImp().subscriptionProjectOrder(body, context).subscribe({
            if (it.status == 1) {
                onCheckSubscriptionSuccess(it)
            } else {
                onCheckSubscriptionFail(it)
            }
        }, {
            object : CallbackWrapper(it, UTSwapApp.instance, arrayListOf()) {
                override fun onCallbackWrapper(status: ApiManager.NetworkErrorStatus, data: Any) {

                }

            }

        })
    }

    companion object {
        fun newInstance(
            id: Int,
            volume: String?,
            title: String?,
            projectName: String?,
            lockTime: String?,
            volumePrice: Double,
            subscriptionPrice: String?
        ): FundPasswordDialog {
            val subscriptionConfirmDialog = FundPasswordDialog()
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
}


