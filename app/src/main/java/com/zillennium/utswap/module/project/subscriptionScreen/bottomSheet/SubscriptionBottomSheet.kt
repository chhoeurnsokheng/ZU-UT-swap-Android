package com.zillennium.utswap.module.project.subscriptionScreen.bottomSheet

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.gis.z1android.api.errorhandler.CallbackWrapper
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.zillennium.utswap.Datas.GlobalVariable.SessionVariable
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.api.manager.ApiManager
import com.zillennium.utswap.api.manager.ApiProjectImp
import com.zillennium.utswap.databinding.BottomSheetProjectSubscriptionBinding
import com.zillennium.utswap.models.project.SubscriptionProject
import com.zillennium.utswap.screens.navbar.navbar.MainActivity
import com.zillennium.utswap.utils.ClientClearData
import com.zillennium.utswap.utils.Constants
import com.zillennium.utswap.utils.UtilKt
import com.zillennium.utswap.utils.formatThreeDigitValue
import rx.Subscription


class SubscriptionBottomSheet : BottomSheetDialogFragment(), AdapterView.OnItemSelectedListener {

    private var binding: BottomSheetProjectSubscriptionBinding? = null
    private var volumeDollarPrice: Double? = 0.0
    private val EMPTY_STRING = ""
    private val WHITE_SPACE = " "
    private var lastSource = EMPTY_STRING
    var count = 0
    private var isDelete = false
    var subscriptPrice = ""
    private var subscriptions: Subscription? = null

    //companion object == static
    companion object {
        fun newInstance(
            id: Int,
            volume: String?,
            title: String?,
            projectName: String?,
            lockTime: String?,
            volumePrice: Double,
            subscriptionPrice: String,
            totalUt: Int,
            min: Int,
            max: Int
        ): SubscriptionBottomSheet {
            val subscriptionBottomSheetDialog = SubscriptionBottomSheet()
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
            subscriptionBottomSheetDialog.arguments = args
            return subscriptionBottomSheetDialog
        }
    }

    override fun getTheme(): Int {
        return R.style.BottomSheetStyle
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        if (dialog is BottomSheetDialog) {
            (dialog as BottomSheetDialog).behavior.skipCollapsed = true
            (dialog as BottomSheetDialog).behavior.state = BottomSheetBehavior.STATE_EXPANDED
        }

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.bottom_sheet_project_subscription,
            container,
            false
        )
        return binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {

            (view.parent as View).setBackgroundColor(
                ContextCompat.getColor(
                    UTSwapApp.instance,
                    android.R.color.transparent
                )
            )
            subscriptPrice = ""
            if (arguments?.getString("volume").toString().isNotEmpty()) {

                etInputVolume.setText(arguments?.getString("volume").toString())
                tvSubscriptPrice.text = arguments?.getString("subscription_price").toString()

                btnSubscript.backgroundTintList = ColorStateList.valueOf(
                    ContextCompat.getColor(
                        UTSwapApp.instance,
                        R.color.success
                    )
                )
                btnSubscript.isClickable = true
            }

            btnSubscript.setOnClickListener {
                val txtUtRemoveSpace = etInputVolume.text.toString().trim().replace(" ", "")
                onSubscriptionProjectCheck(
                    SubscriptionProject.SubscriptionCheckObj(
                        arguments?.getInt(
                            "id"
                        ), txtUtRemoveSpace.toInt()
                    ), UTSwapApp.instance
                )

            }

            txtStandard.text = arguments?.getString("title")
            tvProjectName.text = arguments?.getString("project_name")

            var lockDay = arguments?.getString("lock_time")
            var format_lockDay = lockDay?.toDouble()?.let { UtilKt().formatValue(it, "###,###.##") }
            tvTimeLock.text = "Subscription amount is subjected to a locked period of $format_lockDay Day(s)"
            etInputVolume.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }


                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    val strVolume = if (etInputVolume.text.toString().isNotEmpty()) {

                        etInputVolume.text.toString().replace("\\s".toRegex(), "").toLong()
                    } else {
                        0
                    }

                    if (strVolume > 0) {
                        btnSubscript.backgroundTintList = ColorStateList.valueOf(
                            ContextCompat.getColor(
                                UTSwapApp.instance,
                                R.color.success
                            )
                        )
                        btnSubscript.isClickable = true
                    } else {
                        btnSubscript.backgroundTintList = ColorStateList.valueOf(
                            ContextCompat.getColor(
                                UTSwapApp.instance,
                                R.color.gray_999999
                            )
                        )
                        btnSubscript.isClickable = false
                    }

                    if (etInputVolume.text.isNotEmpty()) {
                        volumeDollarPrice = (arguments?.getDouble("volume_price")?.times(strVolume))
                        tvSubscriptPrice.text = volumeDollarPrice?.let { formatThreeDigitValue(it, "###,###.##") }
                    }

                }

                override fun afterTextChanged(s: Editable?) {
                    if (s.toString().isEmpty()){
                        binding?.tvSubscriptPrice?.text = "0.00"
                    }
                    val inputVolumePrice: Int = etInputVolume.text.toString().length
                    if (count <= inputVolumePrice && (inputVolumePrice == 3 ||
                                inputVolumePrice == 7 || inputVolumePrice == 11 || inputVolumePrice == 15)
                    ) {
                        etInputVolume.setText(etInputVolume.text.toString() + " ")
                        val pos = etInputVolume.text.length
                        etInputVolume.setSelection(pos)
                    } else if (count >= inputVolumePrice && (inputVolumePrice == 3 ||
                                inputVolumePrice == 7 || inputVolumePrice == 11 || inputVolumePrice == 15)
                    ) {
                        etInputVolume.setText(
                            etInputVolume.text.toString()
                                .substring(0, etInputVolume.text.toString().length - 1)
                        )
                        val pos = etInputVolume.text.length
                        etInputVolume.setSelection(pos)
                    }
                    count = etInputVolume.text.toString().length


                    val min = arguments?.get("min").toString().toInt()
                    val max = arguments?.get("max").toString().toInt()
                    val etUtVolume = etInputVolume.text.toString().replace("\\s".toRegex(), "")
                    if (etUtVolume.isNotEmpty()) {
                        if (etUtVolume.toLong() > max) {
                            btnSubscript.backgroundTintList = ColorStateList.valueOf(
                                ContextCompat.getColor(
                                    UTSwapApp.instance,
                                    R.color.gray_999999
                                )
                            )
                            btnSubscript.isClickable = false
                            Toast.makeText(
                                UTSwapApp.instance,
                                "The maximum subscription amount per user is ${max} UT",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            })
        }
    }

    private fun onSubscriptionProjectCheck(
        body: SubscriptionProject.SubscriptionCheckObj,
        context: Context
    ) {
        subscriptions?.unsubscribe()
        subscriptions = ApiProjectImp().subscriptionProjectCheck(body, context).subscribe({
            if (it.status == 1) {
                onConfirmDialog()
            } else {
                if (it.message.toString() == "Insufficient UT BalanceUSD") {
                    Toast.makeText(
                        UTSwapApp.instance,
                        "Insufficient UT Balance USD",
                        Toast.LENGTH_LONG
                    ).show()
                } else if (it.message.toString() == "Please sign in") {
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
                    Toast.makeText(UTSwapApp.instance, it.message.toString(), Toast.LENGTH_LONG)
                        .show()
                }
            }
        }, {
            object : CallbackWrapper(it, UTSwapApp.instance, arrayListOf()) {
                override fun onCallbackWrapper(status: ApiManager.NetworkErrorStatus, data: Any) {
                }
            }
        })

    }

    private fun onConfirmDialog() {
        binding?.apply {
            val utSubscriptionPrice =
                volumeDollarPrice?.let { formatThreeDigitValue(it, "###,###.##") }
            if (!etInputVolume.text.isNullOrEmpty()) {
                if (etInputVolume.text.toString().replace("\\s".toRegex(), "").toLong() > 0) {
                    Constants.SubscriptionBottomSheet.id = arguments?.get("id").toString().toInt()
                    Constants.SubscriptionBottomSheet.title = arguments?.get("title").toString()
                    Constants.SubscriptionBottomSheet.project_name =
                        arguments?.get("project_name").toString()
                    Constants.SubscriptionBottomSheet.lock_time =
                        arguments?.get("lock_time").toString()
                    Constants.SubscriptionBottomSheet.volume_price =
                        arguments?.get("volume_price").toString().toDouble()
                    Constants.SubscriptionBottomSheet.volume = etInputVolume.text.toString()
                    Constants.SubscriptionBottomSheet.subscription = utSubscriptionPrice.toString()
                    Constants.SubscriptionBottomSheet.total_ut =
                        arguments?.get("totalUT").toString().toInt()
                    Constants.SubscriptionBottomSheet.min = arguments?.get("min").toString().toInt()
                    Constants.SubscriptionBottomSheet.max = arguments?.get("max").toString().toInt()
                    Handler().postDelayed({
                        dismiss()
                    }, 1000)

                    SessionVariable.SESSION_SUBSCRIPTION_BOTTOM_SHEET.value = true
                }
            }
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }


}
