package com.zillennium.utswap.module.kyc.kycFragment.idVerificationScreen

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.res.ColorStateList
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.DatePicker
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zillennium.utswap.Datas.StoredPreferences.KYCPreferences
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.FragmentKycIdVerificationBinding
import com.zillennium.utswap.models.SpinnerModel
import com.zillennium.utswap.models.SpinnerTestModel
import com.zillennium.utswap.models.province.PProvinceObj
import com.zillennium.utswap.module.kyc.kycFragment.dropDownAdapter.DropDownAdapter
import com.zillennium.utswap.utils.Util
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class IDVerificationFragment :
    BaseMvpFragment<IDVerificationView.View, IDVerificationView.Presenter, FragmentKycIdVerificationBinding>(),
    IDVerificationView.View {

    override var mPresenter: IDVerificationView.Presenter = IDVerificationPresenter()
    override val layoutResource: Int = R.layout.fragment_kyc_id_verification

    private val genderList: ArrayList<Any> = arrayListOf()
    private val dataListTest = mutableListOf<SpinnerTestModel>()
    private val provinceTest = mutableListOf<SpinnerTestModel>()
    private var provinceList: ArrayList<Any> = arrayListOf()
    private var districtList: ArrayList<Any> = arrayListOf()
    private var communeList: ArrayList<Any> = arrayListOf()
    private var parent_code: String? = null
    private var parent_code1: String? = null
    private var isClickable = true


    object info {
        var firstName = ""
        var lastName = ""
        var dateOfBirth = ""
        var gender = ""
        var genderAsShortWord = ""
        var city = ""
        var district = ""
        var commune = ""
        var addressHouse = ""
    }

    companion object {
        var provice = ""
        var district = ""
        var commune = ""
        var sureName = ""
        var name = ""
        var date = ""
        var gender = ""
        var houseNumber = ""
        var proCode = ""
        var disCode = ""

    }


    override fun initView() {
        super.initView()
        toolBar()
        binding.apply {
            setTextToAddress()
            tvValueCity.hint = Util().getHtmlText("#DCDCDC", "City/Province", "*", "#EE1111")
            tvValueDistict.hint = Util().getHtmlText("#DCDCDC", "District/Khan", "*", "#EE1111")
            tvValueCommune.hint = Util().getHtmlText("#DCDCDC", "Commune/Sangkat", "*", "#EE1111")
            etHouse.hint = Util().getHtmlText("#DCDCDC", "House No, Street #", "*", "#EE1111")
            tvGender.hint = Util().getHtmlText("#DCDCDC", "Gender", "*", "#EE1111")




            spinnerCommuneSangkat.floatingLabelText = ""
            //Util().getHtmlText("#DCDCDC","Commune/Sangkat"," *","#EE1111")
            spinnerCommuneSangkat.hint =
                Util().getHtmlText("#DCDCDC", "Commune/Sangkat", " *", "#EE1111")
            spinnerCommuneSangkatView.hint =
                Util().getHtmlText("#DCDCDC", "Commune/Sangkat", " *", "#EE1111")

            spinnerDistrictKhan.hint =
                Util().getHtmlText("#DCDCDC", "District/Khan", " *", "#EE1111")
            spinnerDistrictKhan.floatingLabelText = ""

            spinnerCityProvince.hint =
                Util().getHtmlText("#DCDCDC", "City/Province", "*", "#EE1111")
            spinnerCityProvince.floatingLabelText = ""
            etFirstName.hint = Util().getHtmlText("#DCDCDC", "Surename", "*", "#EE1111")
            etLastName.hint = Util().getHtmlText("#DCDCDC", "Name", "*", "#EE1111")
            etDate.hint = Util().getHtmlText("#DCDCDC", "Date of Birth", "*", "#EE1111")
            spinnerGender.hint = Util().getHtmlText("#DCDCDC", "Gender", "*", "#EE1111")


        }
        mPresenter.getAllProvinceSuccess(requireActivity())
        validateBtnNext()
        binding.apply {
            tvGender.setOnClickListener {
                genderList.clear()
                genderList.add("Male")
                genderList.add("Female")
                showDialog(genderList, "")
            }
//            mPresenter.getAllProvinceSuccess(requireActivity())

//            initSpinnerGender()

            /* if Data already input */
            /*if (!KYCPreferences().FIRST_NAME.isNullOrEmpty()) {
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
            }*/


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

//                    info.firstName = charSequence.toString()
                }

                override fun afterTextChanged(editable: Editable?) {
                    sureName = editable.toString()


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

//                    info.lastName = charSequence.toString()
                }

                override fun afterTextChanged(editable: Editable?) {
                    name = editable.toString()


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
                    txtErrorDate.visibility = View.GONE
                    etDate.backgroundTintList =
                        ColorStateList.valueOf(
                            ContextCompat.getColor(
                                UTSwapApp.instance,
                                R.color.secondary_text
                            )
                        )

//                    info.dateOfBirth = charSequence.toString()

                }

                override fun afterTextChanged(editable: Editable?) {
                    date = editable.toString()

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
                    if (charSequence.toString().isNotEmpty()) {
                        txtLabelHouseNum.visibility = View.VISIBLE
                    } else {
                        txtLabelHouseNum.visibility = View.INVISIBLE

                    }
                    etHouse.backgroundTintList =
                        ColorStateList.valueOf(
                            ContextCompat.getColor(
                                UTSwapApp.instance,
                                R.color.secondary_text
                            )
                        )

//                    info.addressHouse = charSequence.toString()
                }

                override fun afterTextChanged(editable: Editable?) {
                    houseNumber = editable.toString()

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
                if (gender.isEmpty()) {
                    viewUnderlineGender.setBackgroundColor(
                        ContextCompat.getColor(
                            UTSwapApp.instance,
                            R.color.danger
                        )
                    )
                    txtErrorGender.visibility = View.VISIBLE
                    isHaveError = true
                }

                // City/Province Error
                if (tvValueCity.text.isEmpty()) {
                    viewUnderline1.setBackgroundColor(
                        ContextCompat.getColor(
                            UTSwapApp.instance,
                            R.color.danger
                        )
                    )
                    txtErrorCity.visibility = View.VISIBLE
                    isHaveError = true
                }

                // District Error
                if (tvValueDistict.text.isEmpty()) {
                    viewUnderline2.setBackgroundColor(
                        ContextCompat.getColor(
                            UTSwapApp.instance,
                            R.color.danger
                        )
                    )
                    txtErrorDistrict.visibility = View.VISIBLE
                    isHaveError = true
                }

                // Commune Error
                if (tvValueCommune.text.isEmpty()) {
                    viewUnderline3.setBackgroundColor(
                        ContextCompat.getColor(
                            UTSwapApp.instance,
                            R.color.danger
                        )
                    )

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



                if (!isHaveError) {
                    KYCPreferences().FIRST_NAME = info.firstName
                    KYCPreferences().LAST_NAME = info.lastName
                    KYCPreferences().BIRTHDAY = info.dateOfBirth
                    KYCPreferences().GENDER = info.gender.toString()
                    KYCPreferences().CITY_PROVINCE = info.city.toString()
                    KYCPreferences().DISTRICT_KHAN = info.district.toString()
                    KYCPreferences().COMMUNE_SANGKAT = info.commune.toString()
                    KYCPreferences().ADDRESS = info.addressHouse
                    KYCPreferences().ID_CARD_INFOR = "National Id"
                    KYCPreferences().GENDER_AS_SHORT_LETTER = info.genderAsShortWord
                    findNavController().navigate(R.id.action_to_id_selfie_holding_fragment)

                } else {
//                    return@setOnClickListener
                }

            }
        }

    }

    private fun showDialog(mList: ArrayList<Any>, type: String) {
        activity?.let {
            val alertDialog = AlertDialog.Builder(it)
                .setView(R.layout.pick_dialog)
                .setCancelable(true)
                .show()
            val dropDownAdapter =
                DropDownAdapter(mList, it, type, object : DropDownAdapter.OnclickListener {
                    override fun onItemClick(
                        textProvince: String,
                        textDistrict: String,
                        textCommune: String,
                        provinceCode: String,
                        districtCode: String,
                        textGender: String
                    ) {
                        when (type) {
                            "province" -> {
                                provice = textProvince
                                district = ""
                                commune = ""
                                proCode = provinceCode
                                disCode = ""
                                binding.tvValueCity.text = provice
                                mPresenter.queryProvince(
                                    requireActivity(),
                                    PProvinceObj.BodyProvince(proCode)
                                )
                                setTextToAddress()
                            }
                            "district" -> {
                                district = textDistrict
                                disCode = districtCode
                                binding.tvValueDistict.text = district
                                commune = ""
                                mPresenter.queryCommune(
                                    requireActivity(),
                                    PProvinceObj.BodyProvince(disCode)
                                )
                                setTextToAddress()

                            }
                            "commune" -> {
                                commune = textCommune
                                setTextToAddress()

                            }
                            else -> {
                                gender = textGender
                                setTextToAddress()
                            }

                        }


                        alertDialog.dismiss()
                    }
                })
            val recyclerView = alertDialog.findViewById<RecyclerView>(R.id.list)
            recyclerView.layoutManager =
                LinearLayoutManager(it, LinearLayoutManager.VERTICAL, false)
            recyclerView.adapter = dropDownAdapter
        }
    }

    override fun OngetAllProvinceSuccess(data: PProvinceObj.ProvinceRes) {
        provinceList.clear()
        provinceList.addAll(data.data ?: arrayListOf())
        /*if (disCode.isNotEmpty()) {
            district = ""
        }*/
//        commune = ""
        if (proCode.isNotEmpty()) {
            mPresenter.queryProvince(
                requireActivity(),
                PProvinceObj.BodyProvince(proCode)
            )
        }

        setEventShowDialog()
        binding.rlProvince.setOnClickListener {
            showDialog(provinceList, "province")
        }

//        binding.apply {
//            spinnerCityProvince.item = provinceList.map { it.english }
//            spinnerCityProvince.onItemSelectedListener =
//                object : AdapterView.OnItemSelectedListener {
//                    override fun onItemSelected(
//                        adapterView: AdapterView<*>,
//                        view: View,
//                        position: Int,
//                        id: Long
//                    ) {
//                        if (spinnerCityProvince.item == null) {
//                            txtLabelProvince.visibility = View.GONE
//                            info.city = ""
//                        } else {
//                            txtLabelProvince.visibility = View.VISIBLE
//                            info.city = provinceList[position].code?.toString().toString()
//                            parent_code = provinceList[position].code.toString()
//                            provinceList[position].code?.let { mPresenter.queryProvince(requireActivity(), PProvinceObj.BodyProvince(parent_code))
//                            }
//                        }
//                        spinnerCityProvince.underlineColor = ContextCompat.getColor(UTSwapApp.instance, R.color.secondary_text)
//                        txtErrorCity.visibility = View.GONE
//                       spinnerCommuneSangkat.visibility = View.GONE
//                       spinnerCommuneSangkatView.visibility =View.VISIBLE
//
//                    }
//
//                    override fun onNothingSelected(adapterView: AdapterView<*>) {
//                        txtLabelProvince.visibility = View.GONE
//                        info.city = ""
//                    }
//                }
//        }
    }

    override fun OngetAllProvinceFail(data: PProvinceObj.ProvinceRes) {

    }


    override fun OnQueryProvinceSucess(data: PProvinceObj.DistrictRes) {
        districtList.clear()
        districtList.addAll(data.data ?: arrayListOf())
        if (disCode.isNotEmpty()) {
            mPresenter.queryCommune(
                requireActivity(),
                PProvinceObj.BodyProvince(disCode)
            )
        }
        setEventShowDialog()
//        binding.llDistrict.setOnClickListener {
//            showDialog(districtList, "district")
//        }


//        var dataRespo = (data.data as MutableList<PProvinceObj.Items>).map { it.english }

//        binding.apply {
//
//            spinnerDistrictKhan.item = districtList.map { it.english }
//            spinnerDistrictKhan.onItemSelectedListener =
//                object : AdapterView.OnItemSelectedListener {
//                    override fun onItemSelected(
//                        adapterView: AdapterView<*>,
//                        view: View,
//                        position: Int,
//                        id: Long
//                    ) {
//                        if (dataRespo == null) {
//                            txtLabelDistrict.visibility = View.GONE
//                            info.district = ""
//                        } else {
//                            txtLabelDistrict.visibility = View.VISIBLE
//                            info.district = districtList[position].code?.toString().toString()
//                            mPresenter.queryCommune(
//                                requireActivity(),
//                                PProvinceObj.BodyProvince(districtList[position].code.toString())
//                            )
//                        }
//                        spinnerDistrictKhan.underlineColor =
//                            ContextCompat.getColor(UTSwapApp.instance, R.color.secondary_text)
//                        txtErrorDistrict.visibility = View.GONE
//                        spinnerCommuneSangkat.visibility = View.VISIBLE
//                        spinnerCommuneSangkatView.visibility = View.GONE
//
//                    }
//
//                    override fun onNothingSelected(adapterView: AdapterView<*>) {
//                        txtLabelDistrict.visibility = View.GONE
//                        mPresenter.queryCommune(
//                            requireActivity(),
//                            PProvinceObj.BodyProvince("111111")
//                        )
//                        info.district = ""
//                    }
//                }
//        }

    }

    override fun OnQueryCommuneSucess(data: PProvinceObj.CommuneRes) {
        communeList.clear()
        communeList.addAll(data.data ?: arrayListOf())
        setEventShowDialog()
//        binding.llCommune.setOnClickListener {
//            showDialog(communeList, "commune")
//        }

        /*if (district.isNotEmpty()) {
            binding.llCommune.setOnClickListener {
                showDialog(communeList, "commune")
            }
        }*/

//        binding.apply {
//            spinnerCommuneSangkat.item = communeList.map { it.english }
//            spinnerCommuneSangkat.onItemSelectedListener =
//                object : AdapterView.OnItemSelectedListener {
//                    override fun onItemSelected(
//                        adapterView: AdapterView<*>,
//                        view: View,
//                        position: Int,
//                        id: Long
//                    ) {
//                        if (spinnerCommuneSangkat.item == null) {
//                            txtLabelCommune.visibility = View.GONE
//                            info.commune = ""
//                        } else {
//                            txtLabelCommune.visibility = View.VISIBLE
//                            info.commune = communeList[position].code?.toString().toString()
//
//
//                        }
//                        spinnerCommuneSangkat.underlineColor =
//                            ContextCompat.getColor(UTSwapApp.instance, R.color.secondary_text)
//                        txtErrorCommune.visibility = View.GONE
//
//
//                    }
//
//                    override fun onNothingSelected(adapterView: AdapterView<*>) {
//                        txtLabelCommune.visibility = View.GONE
//                        info.commune = ""
//
//                    }
//                }
//        }
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

//    private fun initSpinnerGender() {
//        binding.apply {
//
//            genderList.add(SpinnerModel(1, "M", "Male"))
//            genderList.add(SpinnerModel(2, "F", "Female"))
//            spinnerGender.item = genderList.map { it.showName }
//
//            spinnerGender.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//                override fun onItemSelected(
//                    adapterView: AdapterView<*>,
//                    view: View,
//                    position: Int,
//                    id: Long
//                ) {
//                    spinnerGender.underlineColor =
//                        ContextCompat.getColor(UTSwapApp.instance, R.color.secondary_text)
//                    gender = genderList[position].id.toString()
//                    gender = genderList[position].name
//                    txtErrorGender.visibility = View.GONE
//                }
//
//                override fun onNothingSelected(adapterView: AdapterView<*>) {
//
//                }
//            }
//        }
//
//    }


    /*override fun onResume() {
        super.onResume()
        binding.apply {

            if (KYCPreferences().GENDER?.isNotBlank() == true) {
                info.gender = KYCPreferences().GENDER.toString()
                genderList.forEachIndexed { index, item ->
                    if (item.id == info.gender.toInt()) {
                        spinnerGender.setSelection(index)
                    }
                }
            }

            if (KYCPreferences().CITY_PROVINCE?.isNotBlank() == true) {
                info.city = KYCPreferences().CITY_PROVINCE.toString()
                provinceList.forEachIndexed { index, item ->
                    if (item.code == info.city.toInt()) {
                        spinnerCityProvince.setSelection(index)
                    }
                }
            }

            if (KYCPreferences().DISTRICT_KHAN?.isNotBlank() == true) {
                info.district = KYCPreferences().DISTRICT_KHAN.toString()
                districtList.forEachIndexed { index, item ->
                    if (item.code == info.district.toInt()) {
                        spinnerDistrictKhan.setSelection(index)
                    }
                }
            }

            if (KYCPreferences().COMMUNE_SANGKAT?.isNotBlank() == true) {

                info.commune = KYCPreferences().COMMUNE_SANGKAT.toString()
                communeList.forEachIndexed { index, item ->
                    if (item.code == info.commune.toInt()) {
                        spinnerCommuneSangkat.setSelection(index)
//                        spinnerCommuneSangkat.visibility =View.VISIBLE
//                        spinnerCommuneSangkatView.visibility =View.GONE
                    }
                }
            }

        }
    }*/

    private fun setTextToAddress() {
        binding.apply {
            if (provice.isNotEmpty()) {
                tvValueCity.text = provice
                txtErrorCity.visibility = View.GONE
                txtLabelProvince.visibility = View.VISIBLE
                viewUnderline1.setBackgroundColor(
                    ContextCompat.getColor(
                        UTSwapApp.instance,
                        R.color.secondary_text
                    )
                )
            } else {
                tvValueCity.text = ""
                txtLabelProvince.visibility = View.INVISIBLE

            }

            if (district.isNotEmpty()) {
                tvValueDistict.text = district
                txtLabelDistrict.visibility = View.VISIBLE
                txtErrorDistrict.visibility = View.GONE
                viewUnderline2.setBackgroundColor(
                    ContextCompat.getColor(
                        UTSwapApp.instance,
                        R.color.secondary_text
                    )
                )

            } else {
                tvValueDistict.text = ""
                txtLabelDistrict.visibility = View.INVISIBLE

            }

            if (commune.isNotEmpty()) {
                tvValueCommune.text = commune
                txtLabelCommune.visibility = View.VISIBLE
                txtErrorCommune.visibility = View.GONE
                viewUnderline3.setBackgroundColor(
                    ContextCompat.getColor(
                        UTSwapApp.instance,
                        R.color.secondary_text
                    )
                )

            } else {
                tvValueCommune.text = ""
                txtLabelCommune.visibility = View.INVISIBLE

            }

            if (gender.isNotEmpty()) {
                tvGender.text = gender
                txtErrorGender.visibility = View.GONE

                viewUnderlineGender.setBackgroundColor(
                    ContextCompat.getColor(
                        UTSwapApp.instance,
                        R.color.secondary_text
                    )
                )
            } else {
                tvGender.text = ""
            }

            if (houseNumber.isNotEmpty()) {
                etHouse.setText(houseNumber)
            } else {
                etHouse.setText("")

            }


            etFirstName.setText(sureName.ifEmpty { "" })
            etLastName.setText(name.ifEmpty { "" })
            etDate.setText(date.ifEmpty { "" })


        }
    }

    private fun setEventShowDialog() {
        if (district.isNotEmpty()) {
            binding.llCommune.isClickable = true
            binding.llCommune.setOnClickListener {
                showDialog(communeList, "commune")
            }
        } else {
            binding.llCommune.isClickable = false
        }
        if (provice.isNotEmpty()) {
            binding.llDistrict.isClickable = true
            binding.llDistrict.setOnClickListener {
                showDialog(districtList, "district")
            }
        } else {
            binding.llDistrict.isClickable = false
        }
    }

}

