package com.zillennium.utswap.module.project.subscriptionScreen.dialog

import android.content.Context
import android.content.Intent
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
import com.gis.z1android.api.errorhandler.CallbackWrapper
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.api.manager.ApiManager
import com.zillennium.utswap.api.manager.ApiUserImp
import com.zillennium.utswap.databinding.DialogNavbarProjectSubscriptionConfirmBinding
import com.zillennium.utswap.module.project.subscriptionScreen.bottomSheet.SubscriptionBottomSheet
import com.zillennium.utswap.module.security.securityDialog.FundPasswordDialog
import com.zillennium.utswap.screens.navbar.navbar.MainActivity
import com.zillennium.utswap.utils.ClientClearData
import com.zillennium.utswap.utils.Constants
import com.zillennium.utswap.utils.UtilKt
import eightbitlab.com.blurview.RenderScriptBlur
import rx.Subscription

class SubscriptionConfirmDialog : DialogFragment() {

    private var binding: DialogNavbarProjectSubscriptionConfirmBinding? = null
    private var subscriptions: Subscription? = null
       private var price_SubScription:String? = null
    companion object {
        fun newInstance(
            id: Int,
            volume: String?,
            title: String?,
            projectName: String?,
            lockTime: String?,
            volumePrice: Double,
            subscriptionPrice: String?,
            totalUt: Int,
            min: Int,
            max: Int,

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
            args.putInt("totalUT", totalUt)
            args.putInt("min", min)
            args.putInt("max", max)

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
        binding?.tvSubscriptionsPrice?.text =     arguments?.get("volume_price").toString()
        binding?.tvProjectTitle?.text = arguments?.get("project_name").toString()
        var timeLocked = arguments?.get("lock_time").toString()
        binding?.tvTimeLock?.text = UtilKt().formatValue(timeLocked.toDouble(), "###,###.##")
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
                        arguments?.get("totalUT").toString().toInt(),
                        arguments?.get("min").toString().toInt(),
                        arguments?.get("max").toString().toInt()
                    )
                subscriptionBottomSheetDialog.show(
                    requireActivity().supportFragmentManager,
                    "balanceHistoryDetailDialog"
                )
                    dismiss()
            }
            btnConfirm.setOnClickListener {
                checkUserLoginStatus(UTSwapApp.instance)
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

    private fun checkUserLoginStatus(context: Context){
        subscriptions?.unsubscribe()
        subscriptions = ApiUserImp().checkUserLoginStatus(context).subscribe({
            if(it.status == 1){
                onSubscribeProject()
            }else{
                Handler().postDelayed({
                    dismiss()
                }, 1000)
                ClientClearData.clearDataUser()
                startActivity(Intent(requireContext(), MainActivity::class.java))
                requireActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            }
        },{
            object : CallbackWrapper(it, UTSwapApp.instance, arrayListOf()){
                override fun onCallbackWrapper(status: ApiManager.NetworkErrorStatus, data: Any) {
                }
            }
        })
    }

    private fun onSubscribeProject(){
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
            Constants.FundPasswordType.subscription
        )
        fundPasswordDialog.show(
            requireActivity().supportFragmentManager,
            "balanceHistoryDetailDialog"
        )
        Handler().postDelayed({
            dismiss()
        }, 1000)
    }
}
