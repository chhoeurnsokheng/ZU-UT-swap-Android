package com.zillennium.utswap.screens.kyc.idVerificationScreen

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Intent
import android.content.res.ColorStateList
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.DatePicker
import com.chivorn.smartmaterialspinner.SmartMaterialSpinner
import com.zillennium.utswap.Datas.StoredPreferences.KYCPreferences
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivityKycIdVerificationBinding
import com.zillennium.utswap.screens.kyc.selfieHoldingScreen.SelfieHoldingActivity
import java.text.SimpleDateFormat
import java.util.*

object info {
    var firstName = ""
    var lastName = ""
    var dateOfBirth = ""
    var gender = ""
    var city = ""
    var district = ""
    var commune = ""
    var addressHouse = ""
}


class IDVerificationActivity :
    BaseMvpActivity<IDVerificationView.View, IDVerificationView.Presenter, ActivityKycIdVerificationBinding>(),
    IDVerificationView.View, AdapterView.OnItemSelectedListener {

    override var mPresenter: IDVerificationView.Presenter = IDVerificationPresenter()
    override val layoutResource: Int = R.layout.activity_kyc_id_verification

    private var spGender: SmartMaterialSpinner<String>? = null
    private var genderList: MutableList<String>? = null

    private var spCityProvince: SmartMaterialSpinner<String>? = null
    private var provinceList: MutableList<String>? = null

    private var spDistrictKhan: SmartMaterialSpinner<String>? = null
    private var districtList: MutableList<String>? = null

    private var spCommuneSankat: SmartMaterialSpinner<String>? = null
    private var communeList: MutableList<String>? = null



    @SuppressLint("UseCompatLoadingForDrawables")
    override fun initView() {
        super.initView()
        try {
        binding.apply {

            /* if Data already input */
            if(KYCPreferences().FIRST_NAME.isNullOrEmpty()){
                etFirstName.setText(KYCPreferences().FIRST_NAME)
            }
            if(KYCPreferences().LAST_NAME.isNullOrEmpty()){
                etLastName.setText(KYCPreferences().LAST_NAME)
            }
            if(KYCPreferences().BIRTHDAY.isNullOrEmpty()){
                etDate.setText(KYCPreferences().BIRTHDAY)
            }
            if(KYCPreferences().ADDRESS.isNullOrEmpty()){
                etHouse.setText(KYCPreferences().ADDRESS)
            }

            initSpinnerGender()
            initSpinnerCityProvince()
            initDistrictKhan()
            initCommuneSangkat()



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

            etDate.isFocusableInTouchMode = false
            etDate.isLongClickable = false
            etDate.setOnClickListener {
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

                // Gender Error
                if (info.gender.isEmpty()){
                    txtErrorGender.visibility = View.VISIBLE
                    isHaveError = true
                }else{
                    isHaveError = false
                }

                // City/Province Error
                if (info.city.isEmpty()) {
                    txtErrorCity.visibility = View.VISIBLE
                    isHaveError = true
                }else{
                    isHaveError = false
                }

                // District Error
                if (info.district.isEmpty()){
                    txtErrorDistrict.visibility = View.VISIBLE
                    isHaveError = true
                }else{
                    isHaveError = false
                }

                // Commune Error
                if (info.commune.isEmpty()){
                    txtErrorCommune.visibility = View.VISIBLE
                    isHaveError = true
                }else{
                    txtErrorCommune.visibility = View.GONE
                    isHaveError = false
                }

                // House Address Error
                if (etHouse.text.toString().isEmpty()) {
                    txtErrorHouse.visibility = View.VISIBLE
                    etHouse.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.red))
                    isHaveError = true
                }

                //  ID Verification Error
                if (etFirstName.text.toString().isEmpty()) {
                    txtErrorFirstName.visibility = View.VISIBLE
                    etFirstName.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.red))
                    isHaveError = true
                }
                //  Lastname Error
                if (etLastName.text.toString().isEmpty()) {
                    txtErrorLastName.visibility = View.VISIBLE
                    etLastName.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.red))
                    isHaveError = true
                }
                // Date Of Birth Error
                if (etDate.text.toString().isEmpty()) {
                    txtErrorDate.visibility = View.VISIBLE
                    etDate.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.red))
                    isHaveError = true
                }


                if (isHaveError) {
                    return@setOnClickListener
                } else {

                    KYCPreferences().FIRST_NAME = info.firstName
                    KYCPreferences().LAST_NAME = info.lastName
                    KYCPreferences().BIRTHDAY = info.dateOfBirth
                    KYCPreferences().GENDER = info.gender
                    KYCPreferences().CITY_PROVINCE = info.city
                    KYCPreferences().DISTRICT_KHAN = info.district
                    KYCPreferences().COMMUNE_SANGKAT = info.commune
                    KYCPreferences().ADDRESS = info.addressHouse

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
                    etFirstName.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.secondary_text))
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
                    etLastName.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.secondary_text))

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
                    etDate.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.secondary_text))
                }
            })

            etHouse.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    charSequence: CharSequence,
                    i: Int,
                    i1: Int,
                    i2: Int
                ) {
                }

                override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}

                @SuppressLint("UseCompatLoadingForDrawables")
                override fun afterTextChanged(editable: Editable) {
                    txtErrorHouse.visibility = View.GONE
                    etHouse.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.secondary_text))
                }
            })

        }
        } catch (error: Exception) {
            // Must be safe
        }
    }

    override fun onItemSelected(adapterView: AdapterView<*>, view: View?, i: Int, l: Long) {}

    override fun onNothingSelected(adapterView: AdapterView<*>?) {}

    private fun initSpinnerGender() {
        spGender = findViewById(R.id.spinner_gender)
        genderList = ArrayList()

        genderList?.add("Male")
        genderList?.add("Female")

        spGender?.item = genderList

        spGender?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>, view: View, position: Int, id: Long) {
                info.gender = spGender?.selectedItemPosition.toString()
                binding.apply {
                    txtErrorGender.visibility = View.GONE
                }
            }

            override fun onNothingSelected(adapterView: AdapterView<*>) {}
        }
    }

    private fun initSpinnerCityProvince() {
        spCityProvince = findViewById(R.id.spinner_city_province)
        provinceList = ArrayList()

        provinceList?.add("Kampong Thom")
        provinceList?.add("Kampong Cham")
        provinceList?.add("Kampong Chhnang")
        provinceList?.add("Phnom Penh")
        provinceList?.add("Kandal")
        provinceList?.add("Kampot")

        spCityProvince?.item = provinceList

        spCityProvince?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>, view: View, position: Int, id: Long) {
                info.city = spCityProvince?.selectedItemPosition.toString()
                binding.apply {
                    txtErrorCity.visibility = View.GONE
                }
            }

            override fun onNothingSelected(adapterView: AdapterView<*>) {}
        }
    }

    private fun initDistrictKhan() {
        spDistrictKhan = findViewById(R.id.spinner_district_khan)
        districtList = ArrayList()

        districtList?.add("Chamkarmon")
        districtList?.add("Toul Kork")
        districtList?.add("Steung Meanchey")
        districtList?.add("Chbar Om Pov")
        districtList?.add("Toul Tom Pong I")
        districtList?.add("Toul Tom Pong II")

        spDistrictKhan?.item = districtList

        spDistrictKhan?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>, view: View, position: Int, id: Long) {
                info.district = spDistrictKhan?.selectedItemPosition.toString()
                binding.apply {
                    txtErrorDistrict.visibility = View.GONE
                }
            }

            override fun onNothingSelected(adapterView: AdapterView<*>) {}
        }
    }

    private fun initCommuneSangkat() {
        spCommuneSankat = findViewById(R.id.spinner_commune_sangkat)
        communeList = ArrayList()

        communeList?.add("Beoung Tra Bek")
        communeList?.add("Toul Tom Pong")
        communeList?.add("Steung Meanchey")
        communeList?.add("Chbar Om Pov")
        communeList?.add("Prek Pra")
        communeList?.add("Toul Sleng")

        spCommuneSankat?.item = communeList

        spCommuneSankat?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>, view: View, position: Int, id: Long) {
                info.commune = spCommuneSankat?.selectedItemPosition.toString()
                binding.apply {
                    txtErrorCommune.visibility = View.GONE
                }
            }

            override fun onNothingSelected(adapterView: AdapterView<*>) {}
        }
    }
}

