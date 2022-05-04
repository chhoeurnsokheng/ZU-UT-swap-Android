package com.zillennium.utswap.screens.kyc.addressInfoScreen

import android.annotation.SuppressLint
import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivityKycAddressInfoBinding
import com.zillennium.utswap.models.Subscription.Subscription
import com.zillennium.utswap.screens.kyc.declarationScreen.DeclarationActivity
import com.zillennium.utswap.screens.wallet.subScriptionScreen.adapter.SubscriptionAdapter
import java.io.IOException

class AddressInfoActivity :
    BaseMvpActivity<AddressInfoView.View, AddressInfoView.Presenter, ActivityKycAddressInfoBinding>(),
    AddressInfoView.View, AdapterView.OnItemSelectedListener {

    override var mPresenter: AddressInfoView.Presenter = AddressInfoPresenter()
    override val layoutResource: Int = R.layout.activity_kyc_address_info

    private var strCity: String? = null
    private  var strDistrict: String? = null
    private  var strCommune: String? = null

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun initView() {
        super.initView()
        try {
            binding.apply {

                val cityList = getCityList()
                val adapterCity = SubscriptionAdapter(UTSwapApp.instance, cityList)
                spinnerCity.adapter = adapterCity
                spinnerCity.onItemSelectedListener = this@AddressInfoActivity

                val districtList = getDistrictList()
                val adapterDistrict = SubscriptionAdapter(UTSwapApp.instance, districtList)
                spinnerDistrict.adapter = adapterDistrict
                spinnerDistrict.onItemSelectedListener = this@AddressInfoActivity

                val communeList = getCommuneList()
                val adapterCommune = SubscriptionAdapter(UTSwapApp.instance, communeList)
                spinnerCommune.adapter = adapterCommune
                spinnerCommune.onItemSelectedListener = this@AddressInfoActivity

                imgBack.setOnClickListener { finish() }
                btnNext.setOnClickListener(View.OnClickListener {
                    var isHaveError = false

                    //Toast.makeText(this,strCity,Toast.LENGTH_SHORT).show();
                    if (spinnerCity.childCount == 0) {
                        txtErrorCity.visibility = View.VISIBLE
                        spinnerCity.background = getDrawable(R.drawable.outline_edittext_error_normal)
                        isHaveError = true
                    }
                    if (etHouse.text.toString().isEmpty()) {
                        txtErrorHouse.visibility = View.VISIBLE
                        etHouse.background = getDrawable(R.drawable.outline_edittext_error_normal)
                        isHaveError = true
                    }
                    if (isHaveError) {
                        return@OnClickListener
                    } else {
                        val intent = Intent(UTSwapApp.instance, DeclarationActivity::class.java)
                        startActivity(intent)
                    }
                })
                etHouse.addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
                    override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
                    @SuppressLint("UseCompatLoadingForDrawables")
                    override fun afterTextChanged(editable: Editable) {
                        txtErrorHouse.visibility = View.GONE
                        etHouse.background = getDrawable(R.drawable.outline_edittext_change_color_focus)
                    }
                })
            }

        } catch (error: IOException) {
            // Must be safe
        }
    }

    private fun getCityList(): ArrayList<Subscription> {
        val cityList = ArrayList<Subscription>()
        cityList.add(Subscription("----- Select -----"))
        cityList.add(Subscription("Banteay Meanchey Province"))
        cityList.add(Subscription("Battambang Province"))
        cityList.add(Subscription("Kompot Province"))
        cityList.add(Subscription("Phnom Penh"))
        return cityList
    }

    private fun getDistrictList(): ArrayList<Subscription> {
        val districtList = ArrayList<Subscription>()
        districtList.add(Subscription("----- Select -----"))
        districtList.add(Subscription("Prey Chas"))
        districtList.add(Subscription("Ta Aek"))
        districtList.add(Subscription("Svay Sa"))
        districtList.add(Subscription("Ta Prok"))
        return districtList
    }

    private fun getCommuneList(): ArrayList<Subscription> {
        val communeList = ArrayList<Subscription>()
        communeList.add(Subscription("----- Select -----"))
        communeList.add(Subscription("Angkor Borei"))
        communeList.add(Subscription("Angkaol"))
        communeList.add(Subscription("Ba Tang"))
        communeList.add(Subscription("Chbar Ampov"))
        return communeList
    }

    override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, i: Int, l: Long) {
        binding.apply {
            strCity = spinnerCity.getItemAtPosition(i).toString()
            strDistrict = spinnerDistrict.getItemAtPosition(i).toString()
            strCommune = spinnerCommune.getItemAtPosition(i).toString()
        }

    }

    override fun onNothingSelected(adapterView: AdapterView<*>?) {}
}