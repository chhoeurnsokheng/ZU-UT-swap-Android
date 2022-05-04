package com.zillennium.utswap.screens.wallet.MyWallet.dialog

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.*
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.button.MaterialButton
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import java.text.SimpleDateFormat
import java.util.*


class ExportTransactionBottomSheetDialog : BottomSheetDialogFragment(),
    AdapterView.OnItemSelectedListener {
    internal var view: View? = null
    private var etStart: EditText? = null
    private var etEnd: EditText? = null
    private var btnClear: MaterialButton? = null
    private var btnExport: MaterialButton? = null
    private var spinner: Spinner? = null
    private val strPeriod = arrayOf(
        "Select Time",
        "Today",
        "Yesterday",
        "This week",
        "Last week",
        "This Month",
        "Last Month"
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view = inflater.inflate(
            R.layout.layout_export_transaction_bottomsheet_fragment,
            container,
            false
        )
        Objects.requireNonNull(dialog)?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        initView()
        spinner!!.onItemSelectedListener = this
        val aa = ArrayAdapter(UTSwapApp.instance, android.R.layout.simple_spinner_item, strPeriod)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner!!.adapter = aa
        initAction()
        return view
    }

    fun initView() {
        etStart = view?.findViewById(R.id.et_start)
        etEnd = view?.findViewById(R.id.et_end)
        btnClear = view?.findViewById(R.id.btn_clear)
        btnExport = view?.findViewById(R.id.btn_export)
        spinner = view?.findViewById(R.id.spinner_period)
    }

    fun initAction() {
        btnExport!!.setOnClickListener { dismiss() }
        btnClear!!.setOnClickListener { dismiss() }
        val calendar = Calendar.getInstance()
        val dateStart =
            OnDateSetListener { _: DatePicker?, year: Int, month: Int, day: Int ->
                calendar[Calendar.YEAR] = year
                calendar[Calendar.MONDAY] = month
                calendar[Calendar.DAY_OF_MONTH] = day
                val format = "MM/dd/yyyy"
                val simpleDateFormat =
                    SimpleDateFormat(format, Locale.US)
                etStart!!.setText(simpleDateFormat.format(calendar.time))
            }
        val dateEnd =
            OnDateSetListener { _: DatePicker?, year: Int, month: Int, day: Int ->
                calendar[Calendar.YEAR] = year
                calendar[Calendar.MONDAY] = month
                calendar[Calendar.DAY_OF_MONTH] = day
                val format = "MM/dd/yyyy"
                val simpleDateFormat =
                    SimpleDateFormat(format, Locale.US)
                etEnd!!.setText(simpleDateFormat.format(calendar.time))
            }
        etStart!!.setOnClickListener {
            DatePickerDialog(
                UTSwapApp.instance,
                dateStart,
                calendar[Calendar.YEAR],
                calendar[Calendar.MONTH],
                calendar[Calendar.DAY_OF_MONTH]
            ).show()
        }
        etEnd!!.setOnClickListener {
            DatePickerDialog(
                UTSwapApp.instance,
                dateEnd,
                calendar[Calendar.YEAR],
                calendar[Calendar.MONTH],
                calendar[Calendar.DAY_OF_MONTH]
            ).show()
        }
    }

    override fun onItemSelected(adapterView: AdapterView<*>?, view: View, i: Int, l: Long) {
        Toast.makeText(UTSwapApp.instance, strPeriod[i], Toast.LENGTH_LONG).show()
    }

    override fun onNothingSelected(adapterView: AdapterView<*>?) {}

    companion object {
        fun newInstance(): ExportTransactionBottomSheetDialog {
            val exportTransactionBottomSheetDialog = ExportTransactionBottomSheetDialog()
            exportTransactionBottomSheetDialog.arguments = Bundle()
            return exportTransactionBottomSheetDialog
        }
    }
}