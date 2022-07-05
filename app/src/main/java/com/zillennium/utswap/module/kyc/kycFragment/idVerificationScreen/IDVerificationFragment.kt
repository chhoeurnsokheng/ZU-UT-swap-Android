package com.zillennium.utswap.module.kyc.kycFragment.idVerificationScreen

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.res.ColorStateList
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.DatePicker
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.zillennium.utswap.Datas.StoredPreferences.KYCPreferences
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.FragmentKycIdVerificationBinding
import com.zillennium.utswap.models.SpinnerModel
import java.text.SimpleDateFormat
import java.util.*


class IDVerificationFragment :
    BaseMvpFragment<IDVerificationView.View, IDVerificationView.Presenter, FragmentKycIdVerificationBinding>(),
    IDVerificationView.View {

    override var mPresenter: IDVerificationView.Presenter = IDVerificationPresenter()
    override val layoutResource: Int = R.layout.fragment_kyc_id_verification

    private val genderList = mutableListOf<SpinnerModel>()
    private val provinceList = mutableListOf<SpinnerModel>()
    private val districtList = mutableListOf<SpinnerModel>()
    private val communeList = mutableListOf<SpinnerModel>()

    object info {
        var firstName = ""
        var lastName = ""
        var dateOfBirth = ""
        var gender = 0
        var city = 0
        var district = 0
        var commune = 0
        var addressHouse = ""
    }

    override fun initView() {
        super.initView()
//        try {
            binding.apply {

            initSpinnerGender()
            initSpinnerCityProvince()
            initDistrictKhan()
            initCommuneSangkat()

            /* if Data already input */
            if (!KYCPreferences().FIRST_NAME.isNullOrEmpty()) {
                info.firstName = KYCPreferences().FIRST_NAME.toString()
                etFirstName.setText(info.firstName)
            }

            if (!KYCPreferences().LAST_NAME.isNullOrEmpty()) {
                info.lastName = KYCPreferences().LAST_NAME.toString()
                etLastName.setText(info.lastName)
            }

            if (!KYCPreferences().BIRTHDAY.isNullOrEmpty()) {
                info.dateOfBirth = KYCPreferences().BIRTHDAY.toString()
                etDate.setText(info.dateOfBirth)
            }

            if (!KYCPreferences().ADDRESS.isNullOrEmpty()) {
                info.addressHouse = KYCPreferences().ADDRESS.toString()
                etHouse.setText(info.addressHouse)
            }

            btnBack.setOnClickListener {
                popFragmentNavigation()
            }

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
                    this@IDVerificationFragment.requireActivity(),
                    dateSetListener,
                    calendar[Calendar.YEAR],
                    calendar[Calendar.MONTH],
                    calendar[Calendar.DAY_OF_MONTH]
                ).show()
            }
//
            btnNext.setOnClickListener {
                var isHaveError = false

                //  FirstName Error
                if (etFirstName.text.toString().isEmpty()) {
                    txtErrorFirstName.visibility = View.VISIBLE
                    etFirstName.backgroundTintList =
                        ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.danger))
                    isHaveError = true
                }

                //  Lastname Error
                if (etLastName.text.toString().isEmpty()) {
                    txtErrorLastName.visibility = View.VISIBLE
                    etLastName.backgroundTintList =
                        ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.danger))
                    isHaveError = true
                }

                // Date Of Birth Error
                if (etDate.text.toString().isEmpty()) {
                    txtErrorDate.visibility = View.VISIBLE
                    etDate.backgroundTintList =
                        ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.danger))
                    isHaveError = true
                }

                //Gender Error
                if (info.gender == 0) {
                    spinnerGender.underlineColor = ContextCompat.getColor(UTSwapApp.instance, R.color.danger)
                    txtErrorGender.visibility = View.VISIBLE
                    isHaveError = true
                }

                // City/Province Error
                if (info.city == 0) {
                    spinnerCityProvince.underlineColor = ContextCompat.getColor(UTSwapApp.instance, R.color.danger)
                    txtErrorCity.visibility = View.VISIBLE
                    isHaveError = true
                }

                // District Error
                if (info.district == 0) {
                    spinnerDistrictKhan.underlineColor = ContextCompat.getColor(UTSwapApp.instance, R.color.danger)
                    txtErrorDistrict.visibility = View.VISIBLE
                    isHaveError = true
               }

                // Commune Error
                if (info.commune == 0) {
                    spinnerCommuneSangkat.underlineColor = ContextCompat.getColor(UTSwapApp.instance, R.color.danger)
                    txtErrorCommune.visibility = View.VISIBLE
                    isHaveError = true
                }

                // House Address Error
                if (etHouse.text.toString().isEmpty()) {
                    txtErrorHouse.visibility = View.VISIBLE
                    etHouse.backgroundTintList =
                        ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.danger))
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

                    findNavController().navigate(R.id.action_to_id_selfie_holding_fragment)
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
                    txtErrorFirstName.visibility = View.GONE
                    etFirstName.backgroundTintList =
                        ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.secondary_text))

                    info.firstName = charSequence.toString()
                }

                override fun afterTextChanged(editable: Editable?) {


                }
            })
//
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
                    txtErrorLastName.visibility = View.GONE
                    etLastName.backgroundTintList =
                        ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.secondary_text))

                    info.lastName = charSequence.toString()
                }

                override fun afterTextChanged(editable: Editable?) {


                }
            })
//
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
                    txtErrorDate.visibility = View.GONE
                    etDate.backgroundTintList =
                        ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.secondary_text))

                    info.dateOfBirth = charSequence.toString()
                }

                override fun afterTextChanged(editable: Editable?) {

                }
            })
//
            etHouse.addTextChangedListener(object : TextWatcher {
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
                        txtErrorHouse.visibility = View.GONE
                        etHouse.backgroundTintList =
                            ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.secondary_text))

                        info.addressHouse = charSequence.toString()
                    }

                    override fun afterTextChanged(editable: Editable?) {

                    }
                })
        }
//        } catch (error: Exception) {
//            // Must be safe
//        }
    }

    private fun initSpinnerGender() {
        binding.apply {

            genderList.add(SpinnerModel(1, "Male"))
            genderList.add(SpinnerModel(2, "Female"))

            spinnerGender.item = genderList.map { it.name }

            spinnerGender.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    adapterView: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
                ) {
                    spinnerGender.underlineColor = ContextCompat.getColor(UTSwapApp.instance, R.color.secondary_text)
                    info.gender = genderList[position].id
                    txtErrorGender.visibility = View.GONE
                }

                override fun onNothingSelected(adapterView: AdapterView<*>) {}
            }
        }

    }

    private fun initSpinnerCityProvince() {
        binding.apply {

            provinceList.add(SpinnerModel(1, "Kampong Thom"))
            provinceList.add(SpinnerModel(2, "Kampong Cham"))
            provinceList.add(SpinnerModel(3, "Kampong Chhnang"))
            provinceList.add(SpinnerModel(4, "Phnom Penh"))
            provinceList.add(SpinnerModel(5, "Kandal"))
            provinceList.add(SpinnerModel(6, "Kampot"))

            spinnerCityProvince.item = provinceList.map { it.name }

            spinnerCityProvince.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        adapterView: AdapterView<*>,
                        view: View,
                        position: Int,
                        id: Long
                    ) {
                        spinnerCityProvince.underlineColor = ContextCompat.getColor(UTSwapApp.instance, R.color.secondary_text)
                        info.city = provinceList[position].id
                        txtErrorCity.visibility = View.GONE
                    }

                    override fun onNothingSelected(adapterView: AdapterView<*>) {}
                }
        }

    }

    private fun initDistrictKhan() {
        binding.apply {

            districtList.add(SpinnerModel(1, "Chamkarmon"))
            districtList.add(SpinnerModel(2, "Toul Kork"))
            districtList.add(SpinnerModel(3, "Steung Meanchey"))
            districtList.add(SpinnerModel(4, "Chbar Om Pov"))
            districtList.add(SpinnerModel(5, "Toul Tom Pong I"))
            districtList.add(SpinnerModel(6, "Toul Tom Pong II"))

            spinnerDistrictKhan.item = districtList.map { it.name }

            spinnerDistrictKhan.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        adapterView: AdapterView<*>,
                        view: View,
                        position: Int,
                        id: Long
                    ) {
                        spinnerDistrictKhan.underlineColor = ContextCompat.getColor(UTSwapApp.instance, R.color.secondary_text)
                        info.district = districtList[position].id
                        txtErrorDistrict.visibility = View.GONE
                    }

                    override fun onNothingSelected(adapterView: AdapterView<*>) {}
                }
        }

    }

    private fun initCommuneSangkat() {

        binding.apply {

            communeList.add(SpinnerModel(1, "Beoung Tra Bek"))
            communeList.add(SpinnerModel(2, "Toul Tom Pong"))
            communeList.add(SpinnerModel(3, "Steung Meanchey"))
            communeList.add(SpinnerModel(4, "Chbar Om Pov"))
            communeList.add(SpinnerModel(5, "Prek Pra"))
            communeList.add(SpinnerModel(6, "Toul Sleng"))

            spinnerCommuneSangkat.item = communeList.map { it.name }

            spinnerCommuneSangkat.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        adapterView: AdapterView<*>,
                        view: View,
                        position: Int,
                        id: Long
                    ) {
                        spinnerCommuneSangkat.underlineColor = ContextCompat.getColor(UTSwapApp.instance, R.color.secondary_text)
                        info.commune = communeList[position].id
                        txtErrorCommune.visibility = View.GONE
                    }

                    override fun onNothingSelected(adapterView: AdapterView<*>) {}
                }
        }

    }

    override fun onResume() {
        super.onResume()
        binding.apply {
            if (KYCPreferences().GENDER != 0) {
                info.gender = KYCPreferences().GENDER ?: 0
                genderList.forEachIndexed { index, item ->
                    if (item.id == info.gender) {
                        spinnerGender.setSelection(index)
                    }
                }
            }

            if (KYCPreferences().CITY_PROVINCE != 0) {
                info.city = KYCPreferences().CITY_PROVINCE ?: 0
                provinceList.forEachIndexed { index, item ->
                    if (item.id == info.city) {
                        spinnerCityProvince.setSelection(index)
                    }
                }
            }

            if (KYCPreferences().DISTRICT_KHAN != 0) {
                info.district = KYCPreferences().DISTRICT_KHAN ?: 0
                districtList.forEachIndexed { index, item ->
                    if (item.id == info.district) {
                        spinnerDistrictKhan.setSelection(index)
                    }
                }
            }

            if (KYCPreferences().COMMUNE_SANGKAT != 0) {
                info.commune = KYCPreferences().COMMUNE_SANGKAT ?: 0
                communeList.forEachIndexed { index, item ->
                    if (item.id == info.commune) {
                        spinnerCommuneSangkat.setSelection(index)
                    }
                }
            }

        }
    }
}

