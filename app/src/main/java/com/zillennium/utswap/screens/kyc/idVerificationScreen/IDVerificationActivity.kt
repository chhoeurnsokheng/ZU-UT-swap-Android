package com.zillennium.utswap.screens.kyc.idVerificationScreen

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.DatePicker
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivityKycIdVerificationBinding
import com.zillennium.utswap.models.Subscription.Subscription
import com.zillennium.utswap.screens.kyc.idTypeScreen.fragment.nationalID.NationalIDFragment
import com.zillennium.utswap.screens.kyc.selfieHoldingScreen.SelfieHoldingActivity
import com.zillennium.utswap.screens.wallet.subScriptionScreen.adapter.SubscriptionAdapter
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class IDVerificationActivity :
    BaseMvpActivity<IDVerificationView.View, IDVerificationView.Presenter, ActivityKycIdVerificationBinding>(),
    IDVerificationView.View, AdapterView.OnItemSelectedListener {

    override var mPresenter: IDVerificationView.Presenter = IDVerificationPresenter()
    override val layoutResource: Int = R.layout.activity_kyc_id_verification

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun initView() {
        super.initView()
        try {
            binding.apply {

                imgBack.setOnClickListener { finish() }

                val calendar = Calendar.getInstance()

                val dateSetListener =
                    OnDateSetListener { _: DatePicker?, year: Int, month: Int, day: Int ->
                        calendar[Calendar.YEAR] = year
                        calendar[Calendar.MONDAY] = month
                        calendar[Calendar.DAY_OF_MONTH] = day
                        val format = "MM/dd/yyyy"
                        val simpleDateFormat =
                            SimpleDateFormat(format, Locale.US)
                        etDate.setText(simpleDateFormat.format(calendar.time))
                    }

                imgCalendar.setOnClickListener {
                    DatePickerDialog(
                        this@IDVerificationActivity,
                        dateSetListener,
                        calendar[Calendar.YEAR],
                        calendar[Calendar.MONTH],
                        calendar[Calendar.DAY_OF_MONTH]
                    ).show()
                }

                btnNext.setOnClickListener {
                    var isHaveError = false
                    if (etFirstName.text.toString().isEmpty()) {
                        txtErrorFirstName.visibility = View.VISIBLE
                        etFirstName.background =
                            getDrawable(R.drawable.outline_edittext_error_normal)
                        isHaveError = true
                    }
                    if (etLastName.text.toString().isEmpty()) {
                        txtErrorLastName.visibility = View.VISIBLE
                        etLastName.background =
                            getDrawable(R.drawable.outline_edittext_error_normal)
                        isHaveError = true
                    }
                    if (etDate.text.toString().isEmpty()) {
                        txtErrorDate.visibility = View.VISIBLE
                        etDate.background = getDrawable(R.drawable.outline_edittext_error_normal)
                        isHaveError = true
                    }
                    if (isHaveError) {
                        return@setOnClickListener
                    } else {
                        val intent = Intent(UTSwapApp.instance, SelfieHoldingActivity::class.java)
                        startActivity(intent)
                    }
                }

                etFirstName.addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(
                        charSequence: CharSequence,
                        i: Int,
                        i1: Int,
                        i2: Int
                    ) {
                    }

                    override fun onTextChanged(
                        charSequence: CharSequence,
                        i: Int,
                        i1: Int,
                        i2: Int
                    ) {
                    }

                    override fun afterTextChanged(editable: Editable) {
                        txtErrorFirstName.visibility = View.GONE
                        etFirstName.background =
                            getDrawable(R.drawable.outline_edittext_change_color_focus)
                    }
                })

                etLastName.addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(
                        charSequence: CharSequence,
                        i: Int,
                        i1: Int,
                        i2: Int
                    ) {
                    }

                    override fun onTextChanged(
                        charSequence: CharSequence,
                        i: Int,
                        i1: Int,
                        i2: Int
                    ) {
                    }

                    override fun afterTextChanged(editable: Editable) {
                        txtErrorLastName.visibility = View.GONE
                        etLastName.background =
                            getDrawable(R.drawable.outline_edittext_change_color_focus)
                    }
                })

                etDate.addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(
                        charSequence: CharSequence,
                        i: Int,
                        i1: Int,
                        i2: Int
                    ) {
                    }

                    override fun onTextChanged(
                        charSequence: CharSequence,
                        i: Int,
                        i1: Int,
                        i2: Int
                    ) {
                    }

                    override fun afterTextChanged(editable: Editable) {
                        txtErrorDate.visibility = View.GONE
                        etDate.background =
                            getDrawable(R.drawable.outline_edittext_change_color_focus)
                    }
                })

                val customList = getCustomList()
                val adapter = SubscriptionAdapter(this@IDVerificationActivity, customList)
                spinner.adapter = adapter
                spinner.onItemSelectedListener = this@IDVerificationActivity
            }

        } catch (error: Exception) {
            // Must be safe
        }
    }

    private fun getCustomList(): ArrayList<Subscription> {
        val customList = ArrayList<Subscription>()
        customList.add(Subscription("Male"))
        customList.add(Subscription("Female"))
        return customList
    }

    override fun onItemSelected(adapterView: AdapterView<*>, view: View?, i: Int, l: Long) {
        val item = adapterView.selectedItem as Subscription
    }

    override fun onNothingSelected(adapterView: AdapterView<*>?) {}
}
