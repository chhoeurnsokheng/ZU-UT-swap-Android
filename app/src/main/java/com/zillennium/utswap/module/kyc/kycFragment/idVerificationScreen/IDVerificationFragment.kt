package com.zillennium.utswap.module.kyc.kycFragment.idVerificationScreen

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.res.ColorStateList
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
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
import com.zillennium.utswap.models.province.Items
import com.zillennium.utswap.models.province.Province
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class IDVerificationFragment :
    BaseMvpFragment<IDVerificationView.View, IDVerificationView.Presenter, FragmentKycIdVerificationBinding>(),
    IDVerificationView.View {

    override var mPresenter: IDVerificationView.Presenter = IDVerificationPresenter()
    override val layoutResource: Int = R.layout.fragment_kyc_id_verification

    private val genderList = mutableListOf<SpinnerModel>()
    private var provinceList :MutableList<Items> = arrayListOf()
    private val districtList :List<Items>? = null
    private val communeList :List<Items>? = null
    private var dataList : MutableList<Items> = arrayListOf() // as districtList , communeList
    object info {
        var firstName = ""
        var lastName = ""
        var dateOfBirth = ""
        var gender = 0
        var city = ""
        var district = ""
        var commune = ""
        var addressHouse = ""
    }

    override fun initView() {
        super.initView()
            toolBar()

            mPresenter.getAllProvinceSuccess(requireActivity())
            binding.apply {

            initSpinnerGender()
            initSpinnerCityProvince()
         //       initDistrictKhan()
       //     initCommuneSangkat()

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

                // District Error
                if (info.district.isEmpty()) {
                    spinnerDistrictKhan.underlineColor = ContextCompat.getColor(UTSwapApp.instance, R.color.danger)
                    txtErrorDistrict.visibility = View.VISIBLE
                    isHaveError = true
               }

                // Commune Error
                if (info.commune.isEmpty()) {
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
    }



    private fun toolBar(){
        activity.let {
            binding.apply {
                includeLayout.apply {
                    tbTitle.text = "1/4"
                    cdBack.setOnClickListener {
                        requireActivity().finish()
                    }
                }
            }
        }
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

        }

    }

    private fun initDistrictKhan() {
        binding.apply {
            if (dataList?.isNotEmpty() == true){
                spinnerDistrictKhan.item = dataList?.map { it.english }
            }

            spinnerDistrictKhan.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        adapterView: AdapterView<*>,
                        view: View,
                        position: Int,
                        id: Long
                    ) {
                        spinnerDistrictKhan.underlineColor = ContextCompat.getColor(UTSwapApp.instance, R.color.secondary_text)
                        info.district = districtList?.get(position)?.code.toString()
                        txtErrorDistrict.visibility = View.GONE
                    }

                    override fun onNothingSelected(adapterView: AdapterView<*>) {}
                }
        }

    }

    private fun initCommuneSangkat() {

        binding.apply {

            spinnerCommuneSangkat.item = dataList.map { it.english }

            spinnerCommuneSangkat.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        adapterView: AdapterView<*>,
                        view: View,
                        position: Int,
                        id: Long
                    ) {
                        spinnerCommuneSangkat.underlineColor = ContextCompat.getColor(UTSwapApp.instance, R.color.secondary_text)
                        info.commune = communeList?.get(position)?.code ?: ""
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

        }
    }

    override fun OngetAllProvinceSuccess(data: Province) {
        provinceList = data.data as MutableList<Items>



    }

    override fun OngetAllProvinceFail(data: Province) {}
    override fun OnQueryProvinceSucess(data: Province) {
        dataList = data.data as ArrayList<Items>
    }
}

