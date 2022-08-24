package com.zillennium.utswap.module.project.subscriptionScreen.bottomSheet

import android.content.res.ColorStateList
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.databinding.BottomSheetProjectSubscriptionBinding
import com.zillennium.utswap.module.project.subscriptionScreen.dialog.SubscriptionConfirmDialog
import com.zillennium.utswap.utils.formatThreeDigitValue


class SubscriptionBottomSheet : BottomSheetDialogFragment(), AdapterView.OnItemSelectedListener {

    private var binding: BottomSheetProjectSubscriptionBinding? = null
    private var volumeDollarPrice: Double? = 0.0
    private val EMPTY_STRING = ""
    private val WHITE_SPACE = " "
    private var lastSource = EMPTY_STRING
    var count = 0
    private var isDelete = false

    //companion object == static
    companion object {
        fun newInstance(
            volume: String?,
            title: String?,
            projectName: String?,
            lockTime: String?,
            volumePrice: Double,
            subscriptionPrice: String
        ): SubscriptionBottomSheet {
            val subscriptionBottomSheetDialog = SubscriptionBottomSheet()
            val args = Bundle()
            args.putString("volume", volume)
            args.putString("title", title)
            args.putString("project_name", projectName)
            args.putString("lock_time", lockTime)
            args.putDouble("volume_price", volumePrice)
            args.putString("subscription_price", subscriptionPrice)
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


//        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
//        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//        dialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
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
                val utSubscriptionPrice =
                    volumeDollarPrice?.let { formatThreeDigitValue(it, "###,###.##") }
                if (!etInputVolume.text.isNullOrEmpty()) {
                    if (etInputVolume.text.toString().toLong() > 0) {
                        val subscriptionConfirmDialog: SubscriptionConfirmDialog =
                            SubscriptionConfirmDialog.newInstance(
                                etInputVolume.text.toString(),
                                arguments?.get("title").toString(),
                                arguments?.get("project_name").toString(),
                                arguments?.get("lock_time").toString(),
                                arguments?.get("volume_price").toString().toDouble(),
                                utSubscriptionPrice.toString(),
                            )
                        subscriptionConfirmDialog.show(
                            requireActivity().supportFragmentManager,
                            "balanceHistoryDetailDialog"
                        )
                        dismiss()
                    }
                }

            }

            txtStandard.text = arguments?.getString("title")
            tvProjectName.text = arguments?.getString("project_name")
            tvTimeLock.text = arguments?.getString("lock_time")
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
                        //etInputVolume.text.toString().toLong()
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
                        tvSubscriptPrice.text =
                            volumeDollarPrice?.let { formatThreeDigitValue(it, "###,###.##") }
                    }

                }

                override fun afterTextChanged(s: Editable?) {
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


//                    if (s.toString().isNotEmpty()) {
//                        SubscriptionActivity.volume = s.toString().toInt()
//                    }

                }
            })
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }
}
