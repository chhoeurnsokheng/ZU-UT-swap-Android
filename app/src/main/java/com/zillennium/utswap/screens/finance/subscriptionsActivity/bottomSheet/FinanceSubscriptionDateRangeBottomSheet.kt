package com.zillennium.utswap.screens.finance.subscriptionsActivity.bottomSheet

import android.app.DatePickerDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.widget.AdapterView
import android.widget.DatePicker
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.zillennium.utswap.Datas.GlobalVariable.SettingVariable
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.databinding.BottomSheetSubscriptionSelectDateRangeBinding
import java.text.SimpleDateFormat
import java.util.*


class FinanceSubscriptionDateRangeBottomSheet : BottomSheetDialogFragment(),
    AdapterView.OnItemSelectedListener {

    private var binding: BottomSheetSubscriptionSelectDateRangeBinding? = null

    override fun getTheme(): Int {
        return R.style.BottomSheetStyle
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.bottom_sheet_subscription_select_date_range,
                container,
                false
            )
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding?.apply {
            (view.parent as View).setBackgroundColor(resources.getColor(android.R.color.transparent))

            val calendar = Calendar.getInstance()

            val dateStart =
                DatePickerDialog.OnDateSetListener { _: DatePicker?, year: Int, month: Int, day: Int ->
                    calendar[Calendar.YEAR] = year
                    calendar[Calendar.MONDAY] = month
                    calendar[Calendar.DAY_OF_MONTH] = day
                    val format = "dd-MMMM-yyyy"
                    val simpleDateFormat =
                        SimpleDateFormat(format, Locale.US)
                    etStartDate.setText(simpleDateFormat.format(calendar.time))
                }

            val dateEnd =
                DatePickerDialog.OnDateSetListener { _: DatePicker?, year: Int, month: Int, day: Int ->
                    calendar[Calendar.YEAR] = year
                    calendar[Calendar.MONDAY] = month
                    calendar[Calendar.DAY_OF_MONTH] = day
                    val format = "dd-MMMM-yyyy"
                    val simpleDateFormat =
                        SimpleDateFormat(format, Locale.US)
                    etEndDate.setText(simpleDateFormat.format(calendar.time))
                }

            etStartDate.isFocusableInTouchMode = false
            etStartDate.isLongClickable = false
            etStartDate.setOnClickListener {
                DatePickerDialog(
                    this@FinanceSubscriptionDateRangeBottomSheet.requireActivity(),
                    dateStart,
                    calendar[Calendar.YEAR],
                    calendar[Calendar.MONTH],
                    calendar[Calendar.DAY_OF_MONTH]
                ).show()
            }

            etEndDate.isFocusableInTouchMode = false
            etEndDate.isLongClickable = false
            etEndDate.isEnabled = false
            etEndDate.setOnClickListener {
                DatePickerDialog(
                    this@FinanceSubscriptionDateRangeBottomSheet.requireActivity(),
                    dateEnd,
                    calendar[Calendar.YEAR],
                    calendar[Calendar.MONTH],
                    calendar[Calendar.DAY_OF_MONTH]
                ).show()
            }

            etStartDate.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun afterTextChanged(p0: Editable?) {
                    etEndDate.setHintTextColor(resources.getColor(R.color.color_main))
                    etEndDate.isEnabled = true
                }

            })

            etEndDate.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun afterTextChanged(p0: Editable?) {

                    if (etStartDate.text.toString() < etEndDate.text.toString()) {
                        SettingVariable.finance_subscription_date_start.value =
                            etStartDate.text.toString()
                        SettingVariable.finance_subscription_date_end.value =
                            etEndDate.text.toString()
                        dismiss()
                    } else {
                        Toast.makeText(
                            UTSwapApp.instance,
                            "EndDate should be greater than StartDate",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            })
        }
    }

    companion object {
        fun newInstance(
//            startDate: String?,
//            endDate: String?,
        ): FinanceSubscriptionDateRangeBottomSheet {
            val financeSubscriptionSelectDateRangeBottomSheet =
                FinanceSubscriptionDateRangeBottomSheet()
            val args = Bundle()

//            args.putString("startDate", startDate)
//            args.putString("endDate", endDate)

            financeSubscriptionSelectDateRangeBottomSheet.arguments = args
            return financeSubscriptionSelectDateRangeBottomSheet
        }
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {}

    override fun onNothingSelected(p0: AdapterView<*>?) {}
}