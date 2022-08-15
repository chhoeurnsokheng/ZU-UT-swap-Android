package com.zillennium.utswap.module.finance.historicalScreen.bottomSheet

import android.app.DatePickerDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.widget.DatePicker
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.zillennium.utswap.Datas.GlobalVariable.SettingVariable
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.databinding.BottomSheetFinanceHistoricalSelectDateRangeBinding
import java.text.SimpleDateFormat
import java.util.*

class FinanceHistoricalSelectDateRangeBottomSheet (
        var startDateSelect: String,
        var startEndSelect: String,
        var listener: CallBackDateListener
        ): BottomSheetDialogFragment(){
    private var binding: BottomSheetFinanceHistoricalSelectDateRangeBinding? = null

    override fun getTheme(): Int {
        return R.style.BottomSheetStyle
    }

    interface CallBackDateListener {
        fun onSelectDateChangeSelect(startDate: String, endDate: String)
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
                R.layout.bottom_sheet_finance_historical_select_date_range,
                container,
                false
            )
        return binding?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding?.apply {
            (view.parent as View).setBackgroundColor(ContextCompat.getColor(UTSwapApp.instance, R.color.transparent))

            val calendar = Calendar.getInstance()

            val dateStart =
                DatePickerDialog.OnDateSetListener { _: DatePicker?, year: Int, month: Int, day: Int ->
                    calendar[Calendar.YEAR] = year
                    calendar[Calendar.MONDAY] = month
                    calendar[Calendar.DAY_OF_MONTH] = day
                    val format = "dd-MMM-yyyy"
                    val simpleDateFormat =
                        SimpleDateFormat(format, Locale.US)
                    etStartDate.setText(simpleDateFormat.format(calendar.time))
                }

            val dateEnd =
                DatePickerDialog.OnDateSetListener { _: DatePicker?, year: Int, month: Int, day: Int ->
                    calendar[Calendar.YEAR] = year
                    calendar[Calendar.MONDAY] = month
                    calendar[Calendar.DAY_OF_MONTH] = day
                    val format = "dd-MMM-yyyy"
                    val simpleDateFormat =
                        SimpleDateFormat(format, Locale.US)
                    etEndDate.setText(simpleDateFormat.format(calendar.time))
                }

            etStartDate.isFocusableInTouchMode = false
            etStartDate.isLongClickable = false
            etStartDate.setOnClickListener {
                DatePickerDialog(
                    this@FinanceHistoricalSelectDateRangeBottomSheet.requireActivity(),
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
                    this@FinanceHistoricalSelectDateRangeBottomSheet.requireActivity(),
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
                    etEndDate.setHintTextColor(resources.getColor(R.color.primary))
                    etEndDate.isEnabled = true
                }

            })

            etEndDate.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun afterTextChanged(p0: Editable?) {
                    if (etEndDate.text.toString() < etStartDate.text.toString()) {
                        Toast.makeText(
                            UTSwapApp.instance,
                            "EndDate should be greater than StartDate",
                            Toast.LENGTH_LONG
                        ).show()
                    }else{
                        listener.onSelectDateChangeSelect(etStartDate.text.toString(), etEndDate.text.toString())
                        dismiss()
                    }
                }
            })
        }
    }
}