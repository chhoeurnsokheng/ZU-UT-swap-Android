package com.zillennium.utswap.screens.wallet.transactionScreen.dialog

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.AdapterView
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Spinner
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.button.MaterialButton
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.models.Subscription.Subscription
import com.zillennium.utswap.screens.wallet.subScriptionScreen.adapter.SubscriptionAdapter
import java.text.SimpleDateFormat
import java.util.*

class FilterBottomSheetDialog : BottomSheetDialogFragment(), AdapterView.OnItemSelectedListener {
    internal var view: View? = null
    private var customSpinner: Spinner? = null
    private var btnClear: MaterialButton? = null
    private var btnFiler: MaterialButton? = null
    private var etStart: EditText? = null
    private var etEnd: EditText? = null
    private var statusList: ArrayList<Subscription?>? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view = inflater.inflate(R.layout.layout_filter_bottomsheet_fragment, container, false)
        Objects.requireNonNull(dialog)?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        initView()
        statusList = list
        val adapter = SubscriptionAdapter(UTSwapApp.instance, statusList!!)
        if (customSpinner != null) {
            customSpinner!!.adapter = adapter
            customSpinner!!.onItemSelectedListener = this
        }
        initAction()
        return view
    }

    fun initView() {
        customSpinner = view?.findViewById(R.id.spinner_status)
        btnClear = view?.findViewById(R.id.btn_clear)
        btnFiler = view?.findViewById(R.id.btn_filter)
        etStart = view?.findViewById(R.id.et_start)
        etEnd = view?.findViewById(R.id.et_end)
    }

    private val list: ArrayList<Subscription?>
        get() {
            statusList = ArrayList()
            statusList!!.add(Subscription("Balance"))
            statusList!!.add(Subscription("Deposit"))
            statusList!!.add(Subscription("Transfer"))
            statusList!!.add(Subscription("Withdrawal"))
            return statusList as ArrayList<Subscription?>
        }

    fun initAction() {
        btnFiler!!.setOnClickListener { dismiss() }
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

    override fun onItemSelected(adapterView: AdapterView<*>?, view: View, i: Int, l: Long) {}
    override fun onNothingSelected(adapterView: AdapterView<*>?) {}

    companion object {
        fun newInstance(): FilterBottomSheetDialog {
            val filterBottomSheetDialog = FilterBottomSheetDialog()
            filterBottomSheetDialog.arguments = Bundle()
            return filterBottomSheetDialog
        }
    }
}