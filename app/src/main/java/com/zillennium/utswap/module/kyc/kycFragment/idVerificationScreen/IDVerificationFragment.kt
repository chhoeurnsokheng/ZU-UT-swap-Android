package com.zillennium.utswap.module.kyc.kycFragment.idVerificationScreen

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.res.ColorStateList
import android.text.Editable
import android.text.Html
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
import com.zillennium.utswap.models.SpinnerTestModel
import com.zillennium.utswap.models.province.PProvinceObj
import com.zillennium.utswap.utils.Util
import java.text.SimpleDateFormat
import java.util.*


class IDVerificationFragment :
    BaseMvpFragment<IDVerificationView.View, IDVerificationView.Presenter, FragmentKycIdVerificationBinding>(),
    IDVerificationView.View {

    override var mPresenter: IDVerificationView.Presenter = IDVerificationPresenter()
    override val layoutResource: Int = R.layout.fragment_kyc_id_verification

    private val genderList = mutableListOf<SpinnerModel>()
    private val dataListTest = mutableListOf<SpinnerTestModel>()
    private val provinceTest = mutableListOf<SpinnerTestModel>()
    private var provinceList = mutableListOf<PProvinceObj.Items>()
    private var districtList = mutableListOf<PProvinceObj.Items>()
    private var communeList = mutableListOf<PProvinceObj.Items>()
    private var parent_code: String? = null
    private var parent_code1: String? = null

    object info {
        var firstName = ""
        var lastName = ""
        var dateOfBirth = ""
        var gender = ""
        var city = 0
        var district = 0
        var commune = 0
        var addressHouse = ""
    }

    override fun initView() {
        super.initView()
        toolBar()
        KYCPreferences().GENDER = ""
        KYCPreferences().CITY_PROVINCE = ""
        KYCPreferences().DISTRICT_KHAN = ""
        KYCPreferences().CITY_PROVINCE = ""
        KYCPreferences().FIRST_NAME = ""
        KYCPreferences().LAST_NAME = ""
        KYCPreferences().GENDER = ""
        KYCPreferences().BIRTHDAY = ""
        KYCPreferences().ADDRESS = ""
        info.commune = 0
        info.city = 0
        info.district = 0
        info.city = 0
        info.dateOfBirth = ""
        info.gender = ""
        info.firstName = ""
        info.lastName = ""
        binding.apply {

            spinnerCommuneSangkat.floatingLabelText  = ""
                //Util().getHtmlText("#DCDCDC","Commune/Sangkat"," *","#EE1111")
            spinnerCommuneSangkat.hint = Util().getHtmlText("#DCDCDC","Commune/Sangkat"," *","#EE1111")
            spinnerCommuneSangkatView.hint = Util().getHtmlText("#DCDCDC","Commune/Sangkat"," *","#EE1111")

            spinnerDistrictKhan.hint = Util().getHtmlText("#DCDCDC", "District/Khan", " *", "#EE1111")
            spinnerDistrictKhan.floatingLabelText = ""

            spinnerCityProvince.hint = Util().getHtmlText("#DCDCDC", "City/Province", "*", "#EE1111")
            spinnerCityProvince.floatingLabelText = ""
            etFirstName.hint =  Util().getHtmlText("#DCDCDC", "Surename", "*", "#EE1111")
            etLastName.hint =  Util().getHtmlText("#DCDCDC", "Name", "*", "#EE1111")
            etDate.hint =  Util().getHtmlText("#DCDCDC", "Date of Birth", "*", "#EE1111")
            spinnerGender.hint =  Util().getHtmlText("#DCDCDC", "Gender", "*", "#EE1111")


        }
        mPresenter.getAllProvinceSuccess(requireActivity())
        validateBtnNext()
        binding.apply {
            mPresenter.getAllProvinceSuccess(requireActivity())

            initSpinnerGender()

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
                        ColorStateList.valueOf(
                            ContextCompat.getColor(
                                UTSwapApp.instance,
                                R.color.secondary_text
                            )
                        )

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
                        ColorStateList.valueOf(
                            ContextCompat.getColor(
                                UTSwapApp.instance,
                                R.color.secondary_text
                            )
                        )

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
                        ColorStateList.valueOf(
                            ContextCompat.getColor(
                                UTSwapApp.instance,
                                R.color.secondary_text
                            )
                        )

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
                        ColorStateList.valueOf(
                            ContextCompat.getColor(
                                UTSwapApp.instance,
                                R.color.secondary_text
                            )
                        )

                    info.addressHouse = charSequence.toString()
                }

                override fun afterTextChanged(editable: Editable?) {

                }
            })
        }
    }


    private fun validateBtnNext() {

        binding.apply {

            btnNext.setOnClickListener {
                var isHaveError = false

                //  FirstName Error
                if (etFirstName.text.toString().isEmpty()) {
                    txtErrorFirstName.visibility = View.VISIBLE
                    etFirstName.backgroundTintList =
                        ColorStateList.valueOf(
                            ContextCompat.getColor(
                                UTSwapApp.instance,
                                R.color.danger
                            )
                        )
                    isHaveError = true
                }

                //  Lastname Error
                if (etLastName.text.toString().isEmpty()) {
                    txtErrorLastName.visibility = View.VISIBLE
                    etLastName.backgroundTintList =
                        ColorStateList.valueOf(
                            ContextCompat.getColor(
                                UTSwapApp.instance,
                                R.color.danger
                            )
                        )
                    isHaveError = true
                }

                // Date Of Birth Error
                if (etDate.text.toString().isEmpty()) {
                    txtErrorDate.visibility = View.VISIBLE
                    etDate.backgroundTintList =
                        ColorStateList.valueOf(
                            ContextCompat.getColor(
                                UTSwapApp.instance,
                                R.color.danger
                            )
                        )
                    isHaveError = true
                }

                //Gender Error
                if (info.gender.isEmpty()) {
                    spinnerGender.underlineColor =
                        ContextCompat.getColor(UTSwapApp.instance, R.color.danger)
                    txtErrorGender.visibility = View.VISIBLE
                    isHaveError = true
                }

                // City/Province Error
                if (info.city == 0) {
                    spinnerCityProvince.underlineColor =
                        ContextCompat.getColor(UTSwapApp.instance, R.color.danger)
                    txtErrorCity.visibility = View.VISIBLE
                    isHaveError = true
                }

                // District Error
                if (info.district == 0) {
                    spinnerDistrictKhan.underlineColor =
                        ContextCompat.getColor(UTSwapApp.instance, R.color.danger)
                    txtErrorDistrict.visibility = View.VISIBLE
                    isHaveError = true
                }

                // Commune Error
                if (info.commune == 0) {
                    spinnerCommuneSangkat.underlineColor =
                        ContextCompat.getColor(UTSwapApp.instance, R.color.danger)
                    txtErrorCommune.visibility = View.VISIBLE
                    isHaveError = true
                }

                // House Address Error
                if (etHouse.text.toString().isEmpty()) {
                    txtErrorHouse.visibility = View.VISIBLE
                    etHouse.backgroundTintList =
                        ColorStateList.valueOf(
                            ContextCompat.getColor(
                                UTSwapApp.instance,
                                R.color.danger
                            )
                        )
                    isHaveError = true
                }
                if (info.gender.isEmpty()){
                    txtErrorGender.visibility =View.VISIBLE
                    isHaveError  =true
                }


                if (!isHaveError) {
                    KYCPreferences().FIRST_NAME = info.firstName
                    KYCPreferences().LAST_NAME = info.lastName
                    KYCPreferences().BIRTHDAY = info.dateOfBirth
                    KYCPreferences().GENDER = info.gender
                    KYCPreferences().CITY_PROVINCE = info.city.toString()
                    KYCPreferences().DISTRICT_KHAN = info.district.toString()
                    KYCPreferences().COMMUNE_SANGKAT = info.commune.toString()
                    KYCPreferences().ADDRESS = info.addressHouse
                    KYCPreferences().ID_CARD_INFOR = "National Id"
                    findNavController().navigate(R.id.action_to_id_selfie_holding_fragment)

                } else {
                    return@setOnClickListener
                }

            }
        }

    }

    override fun OngetAllProvinceSuccess(data: PProvinceObj.ProvinceRes) {
        provinceList = data.data as MutableList<PProvinceObj.Items>
        binding.apply {
            spinnerCityProvince.item = provinceList.map { it.english }
            spinnerCityProvince.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        adapterView: AdapterView<*>,
                        view: View,
                        position: Int,
                        id: Long
                    ) {
                        if (spinnerCityProvince.item == null) {
                            txtLabelProvince.visibility = View.GONE
                            info.city = 0
                        } else {
                            txtLabelProvince.visibility = View.VISIBLE
                            info.city = provinceList[position].code?.toInt()!!
                            parent_code = provinceList[position].code
                            provinceList[position].code?.let { mPresenter.queryProvince(requireActivity(), PProvinceObj.BodyProvince(parent_code))
                            }
                        }
                        spinnerCityProvince.underlineColor = ContextCompat.getColor(UTSwapApp.instance, R.color.secondary_text)
                        txtErrorCity.visibility = View.GONE
                        info.commune = 0
                       spinnerCommuneSangkat.visibility = View.GONE
                       spinnerCommuneSangkatView.visibility =View.VISIBLE

                    }

                    override fun onNothingSelected(adapterView: AdapterView<*>) {
                        txtLabelProvince.visibility = View.GONE
                        info.city = 0
                    }
                }
        }
    }

    override fun OngetAllProvinceFail(data: PProvinceObj.ProvinceRes) {

    }


    override fun OnQueryProvinceSucess(data: PProvinceObj.ProvinceRes) {
        districtList = data.data as MutableList<PProvinceObj.Items>
        var dataRespo = (data.data as MutableList<PProvinceObj.Items>).map { it.english }


        binding.apply {

            spinnerDistrictKhan.item = districtList.map { it.english }
            spinnerDistrictKhan.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        adapterView: AdapterView<*>,
                        view: View,
                        position: Int,
                        id: Long
                    ) {
                        if (dataRespo == null) {
                            txtLabelDistrict.visibility = View.GONE
                            info.district = 0
                        } else {
                            txtLabelDistrict.visibility = View.VISIBLE
                            info.district = districtList[position].code?.toInt()!!
                            mPresenter.queryCommune(requireActivity(),
                                PProvinceObj.BodyProvince(districtList[position].code)
                            )
                        }
                        spinnerDistrictKhan.underlineColor = ContextCompat.getColor(UTSwapApp.instance, R.color.secondary_text)
                        txtErrorDistrict.visibility = View.GONE
                        spinnerCommuneSangkat.visibility =View.VISIBLE
                        spinnerCommuneSangkatView.visibility =View.GONE

                    }

                    override fun onNothingSelected(adapterView: AdapterView<*>) {
                        txtLabelDistrict.visibility = View.GONE
                        info.district = 0
                        info.commune = 0
                        mPresenter.queryCommune(requireActivity(),PProvinceObj.BodyProvince("111111"))

                    }
                }
        }

    }


    override fun OnQueryCommuneSucess(data: PProvinceObj.ProvinceRes) {
        communeList = data.data as MutableList<PProvinceObj.Items>

        binding.apply {
            spinnerCommuneSangkat.item = communeList.map { it.english }
            spinnerCommuneSangkat.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        adapterView: AdapterView<*>,
                        view: View,
                        position: Int,
                        id: Long
                    ) {
                        if (spinnerCommuneSangkat.item == null) {
                            txtLabelCommune.visibility = View.GONE
                            info.commune = 0
                        } else {
                            txtLabelCommune.visibility = View.VISIBLE
                            info.commune = communeList[position].code?.toInt()!!

                        }
                        spinnerCommuneSangkat.underlineColor = ContextCompat.getColor(UTSwapApp.instance, R.color.secondary_text)
                        txtErrorCommune.visibility = View.GONE
                    }

                    override fun onNothingSelected(adapterView: AdapterView<*>) {
                        txtLabelCommune.visibility = View.GONE
                        info.commune = 0

                    }
                }
        }
    }


    private fun toolBar() {
        activity.let {
            binding.apply {
                includeLayout.apply {
                    tbTitle.text = "2/4"
                    cdBack.setOnClickListener {
                        findNavController().popBackStack()
                    }
                }
            }
        }
    }

    private fun initSpinnerGender() {
        binding.apply {

            genderList.add(SpinnerModel("1", "M", "Male"))
            genderList.add(SpinnerModel("2", "F", "Female"))
            spinnerGender.item = genderList.map { it.showName }

            spinnerGender.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    adapterView: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
                ) {
                    spinnerGender.underlineColor =
                        ContextCompat.getColor(UTSwapApp.instance, R.color.secondary_text)
                    info.gender = genderList[position].name
                    // KYCPreferences().GENDER = genderList[position].name
                    txtErrorGender.visibility = View.GONE
                }

                override fun onNothingSelected(adapterView: AdapterView<*>) {
                    info.gender = ""
                }
            }
        }

    }



    override fun onResume() {
        super.onResume()
        binding.apply {
//            if (KYCPreferences().GENDER.isNullOrBlank()) {
//                info.gender = KYCPreferences().GENDER ?: ""
////                genderList.forEachIndexed { index, item ->
////                    if (item.id == info.gender) {
////                        spinnerGender.setSelection(index)
////                    }
////                }
//            }
//            if (KYCPreferences().CITY_PROVINCE.isNullOrEmpty()) {
//                info.city = KYCPreferences().CITY_PROVINCE ?: ""
//
////                provinceList.forEachIndexed { index, item ->
////                    if (item.id == info.city) {
////                        spinnerCityProvince.setSelection(index)
////                    }
////                }
//            }

//            if (KYCPreferences().DISTRICT_KHAN.isNullOrEmpty()) {
//                info.district = KYCPreferences().DISTRICT_KHAN ?: ""
////                districtList.forEachIndexed { index, item ->
////                    if (item.id == info.district) {
////                        spinnerDistrictKhan.setSelection(index)
////                    }
//            }
        }

//        if (KYCPreferences().COMMUNE_SANGKAT.isNullOrEmpty()) {
//            info.commune = KYCPreferences().COMMUNE_SANGKAT ?: ""
//            communeList.forEachIndexed { index, item ->
////                    if (item.id == info.commune) {
////                        spinnerCommuneSangkat.setSelection(index)
////                    }
//            }
//        }


    }

}


