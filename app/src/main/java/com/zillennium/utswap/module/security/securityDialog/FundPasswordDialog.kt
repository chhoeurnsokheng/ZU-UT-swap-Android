package com.zillennium.utswap.module.security.securityDialog

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.*
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.view.children
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.gis.z1android.api.errorhandler.CallbackWrapper
import com.zillennium.utswap.Datas.GlobalVariable.SessionVariable
import com.zillennium.utswap.Datas.StoredPreferences.SessionPreferences
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.api.manager.ApiManager
import com.zillennium.utswap.api.manager.ApiProjectImp
import com.zillennium.utswap.databinding.DialogSecurityFundPasswordBinding
import com.zillennium.utswap.models.project.SubscriptionProject
import com.zillennium.utswap.api.manager.ApiTransferImp
import com.zillennium.utswap.models.financeTransfer.Transfer
import com.zillennium.utswap.module.finance.transferScreen.TransferActivityReview
import com.zillennium.utswap.module.finance.transferScreen.dialog.TransferSuccessDialog
import com.zillennium.utswap.module.project.subscriptionScreen.dialog.SubscriptionConfirmDialog
import com.zillennium.utswap.screens.navbar.navbar.MainActivity
import com.zillennium.utswap.utils.ClientClearData
import com.zillennium.utswap.utils.Constants
import com.zillennium.utswap.utils.VerifyClientData
import eightbitlab.com.blurview.RenderScriptBlur
import rx.Subscription


class FundPasswordDialog : DialogFragment() {

    private var binding: DialogSecurityFundPasswordBinding? = null
    private var codes: String = ""
    private var subCode: String = ""
    private var subscription: Subscription? = null
    private var getTransferData = Transfer.TransferSuccessulReview()

    var amount = ""
    var trxTransfer = ""
    var fromAccount = ""
    var trxDate = ""
    var toAccount = ""
    var sender = ""

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

    @RequiresApi(Build.VERSION_CODES.S)
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
                  //  backToTransferScreen = true
                    startActivity(Intent(requireActivity(), TransferActivityReview::class.java))
//                    when (javaClass.simpleName.toString()) {
//                        "WithdrawActivity" -> {
//                            dismiss()
//                        }
//                        "TransferActivity" -> {
//                            startActivity(Intent(requireActivity(), TransferActivityReview::class.java))
//                          //  dismiss()
//                        }
//                        else -> {
//                            val subscriptionConfirmDialog: SubscriptionConfirmDialog =
//                                SubscriptionConfirmDialog.newInstance(
//                                    arguments?.get("id").toString().toInt(),
//                                    arguments?.get("volume").toString(),
//                                    arguments?.get("title").toString(),
//                                    arguments?.get("project_name").toString(),
//                                    arguments?.get("lock_time").toString(),
//                                    arguments?.get("volume_price").toString().toDouble(),
//                                    arguments?.get("subscription_price").toString(),
//                                    Constants.SubscriptionBottomSheet.total_ut,
//                                    Constants.SubscriptionBottomSheet.min,
//                                    Constants.SubscriptionBottomSheet.max,
//                                )
//                            subscriptionConfirmDialog.show(
//                                requireActivity().supportFragmentManager,
//                                "balanceHistoryDetailDialog"
//                            )
//                            dismiss()
//                        }
//                    }
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

                val transferCheck = arguments?.getString("transferFund")
                val subscriptionCheck = arguments?.getString("subscriptionFund")

                if (subscriptionCheck == Constants.FundPasswordType.subscription) {
                    val volume =
                        arguments?.getString("volume")?.replace("\\s".toRegex(), "")?.toInt()
                    val id = arguments?.getInt("id")
                    var params: Map<String, String> = emptyMap()
                    params = mapOf(
                        "sign_type" to "MD5",
                        "id" to id.toString(),
                        "ut_number" to volume.toString(),
                        "fund_password" to codes
                    )
                    val result = VerifyClientData.makeSign(
                        params,
                        SessionPreferences().SESSION_X_TOKEN_API.toString()
                    )
                    onCheckSubscriptionConfirmPassword(
                        SubscriptionProject.SubscribeOrderBody(
                            "MD5",
                            result,
                            id,
                            volume,
                            codes
                        ), UTSwapApp.instance
                    )
                } else if (transferCheck == Constants.FundPasswordType.transfer) {
                    var params: Map<String, String> = emptyMap()
                    params = mapOf(
                        "sign_type" to "MD5",
                        "amount" to arguments?.getString("amountTrans").toString(),
                        "currency" to arguments?.getString("currencyTrans").toString(),
                        "receiver" to arguments?.getString("receiverTrans").toString(),
                        "paypassword" to codes
                    )
                    val result = VerifyClientData.makeSign(
                        params,
                        SessionPreferences().SESSION_X_TOKEN_API.toString()
                    )
                    onGetTransfer(
                        Transfer.GetTransferObject(
                            "MD5",
                            result,
                            arguments?.getString("amountTrans"),
                            arguments?.getString("currencyTrans"),
                            arguments?.getString("receiverTrans"),
                            codes
                        ), UTSwapApp.instance
                    )

                }

            } else {
                imgIcon.setImageResource(R.drawable.ic_fund_key_normal)
                txtMessage.text = "Please enter your fund password"
                txtMessage.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.primary))
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
    private fun onCheckSubscriptionSuccess(data: SubscriptionProject.SubscriptionOrderRes) {
        binding?.apply {
            layProgressBar.visibility = View.GONE
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

            SessionVariable.SESSION_SUBSCRIPTION_ORDER.value = true
            Handler().postDelayed({
                dismiss()
            }, 1000)
        }
    }

    private fun onCheckSubscriptionFail(data: SubscriptionProject.SubscriptionOrderRes) {
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
        body: SubscriptionProject.SubscribeOrderBody,
        context: Context
    ) {
        subscription?.unsubscribe()
        subscription = ApiProjectImp().subscriptionProjectOrder(body, context).subscribe({
            if (it.status == 1) {
                onCheckSubscriptionSuccess(it)
            } else {
                if (it.message.toString() == "Please sign in") {
                    Handler().postDelayed({
                        dismiss()
                    }, 1000)
                    ClientClearData.clearDataUser()
                    startActivity(Intent(requireContext(), MainActivity::class.java))
                    requireActivity().overridePendingTransition(
                        R.anim.slide_in_right,
                        R.anim.slide_out_left
                    )
                } else {
                    onCheckSubscriptionFail(it)
                }

            }
        }, {
            object : CallbackWrapper(it, UTSwapApp.instance, arrayListOf()) {
                override fun onCallbackWrapper(status: ApiManager.NetworkErrorStatus, data: Any) {

                }
            }
        })
    }

    /** Finance Transfer Balance */
    private fun onGetTransferSuccess(data: Transfer.GetTransferData) {
        binding?.apply {
            layProgressBar.visibility = View.GONE
            imgIcon.setImageResource(R.drawable.ic_fund_key_success)
            txtMessage.text = "Success"
            txtMessage.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.success))
            for (pingCode in layPingCode.children) {
                pingCode.backgroundTintList = ColorStateList.valueOf(
                    ContextCompat.getColor(
                        UTSwapApp.instance,
                        R.color.success
                    )
                )
            }

            getTransferData = Transfer.TransferSuccessulReview()
            getTransferData.amount = data.amount
            getTransferData.trx_transfer = data.trx_transfer
            getTransferData.sender = data.sender
            getTransferData.receiver = data.receiver
            getTransferData.trx_date = data.trx_date
            val transfer = TransferSuccessDialog(getTransferData)
            transfer.show(
                requireActivity().supportFragmentManager,
                "balanceHistoryDetailDialog"
            )



            amount = data.amount.toString()
            trxTransfer  = data.trx_transfer.toString()
            toAccount = data.receiver.toString()


            Handler().postDelayed({
                dismiss()
            }, 800)

            SessionVariable.successTransfer.value = true

        }
    }

    private fun onGetTransferFail(data: Transfer.GetTransfer) {
        binding?.apply {
            imgIcon.setImageResource(R.drawable.ic_fund_key_invalid)
            txtMessage.text = "Invalid"
            txtMessage.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.danger))
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

    private fun onGetTransfer(body: Transfer.GetTransferObject, context: Context) {
        subscription?.unsubscribe()
        subscription = ApiTransferImp().getFinanceTransfer(body, context).subscribe({
            if (it.status == 1) {
                it.data?.let { it1 -> onGetTransferSuccess(it1) }
            } else {
                onGetTransferFail(it)
            }
        }, {
            object : CallbackWrapper(it, UTSwapApp.instance, arrayListOf()) {
                override fun onCallbackWrapper(status: ApiManager.NetworkErrorStatus, data: Any) {}
            }
        })
    }

    companion object {

        var amount   = ""
        var trxTransfer  = ""
        var fromAccount   = ""
        var trxDate  = ""
        var toAccount  = ""
        var sender  = ""
        var backToTransferScreen = false
        fun newInstance(
            id: Int,
            volume: String?,
            title: String?,
            projectName: String?,
            lockTime: String?,
            volumePrice: Double,
            subscriptionPrice: String,
            subscriptionFund: String
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
            args.putString("subscriptionFund", subscriptionFund)
            subscriptionConfirmDialog.arguments = args
            return subscriptionConfirmDialog
        }

        fun transferInstance(
            amount: String?,
            currency: String?,
            receiver: String?,
            transferFund: String?,
        ): FundPasswordDialog {
            val subscriptionConfirmTransferDialog = FundPasswordDialog()
            val args = Bundle()
            args.putString("amountTrans", amount)
            args.putString("currencyTrans", currency)
            args.putString("receiverTrans", receiver)
            args.putString("transferFund", transferFund)
            subscriptionConfirmTransferDialog.arguments = args
            return subscriptionConfirmTransferDialog
        }
    }
}


