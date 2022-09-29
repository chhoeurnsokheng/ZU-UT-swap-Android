package com.zillennium.utswap.module.kyc.kycFragment.fundPasswordScreen

import android.R.attr.maxLength
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.text.Editable
import android.text.InputFilter
import android.text.InputFilter.LengthFilter
import android.text.InputType
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.text.method.TransformationMethod
import android.util.Base64
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.getSystemService
import androidx.core.view.children
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.io.output.ByteArrayOutputStream
import com.zillennium.utswap.Datas.StoredPreferences.KYCPreferences
import com.zillennium.utswap.Datas.StoredPreferences.SessionPreferences
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.FragmentKycFundPasswordBinding
import com.zillennium.utswap.models.userService.User
import com.zillennium.utswap.module.kyc.kycFragment.employmentInfoScreen.EmploymentInfoFragment
import com.zillennium.utswap.module.kyc.kycFragment.idTypeScreen.camera.idCardCameraFragment.IDCardCameraFragment
import com.zillennium.utswap.module.kyc.kycFragment.idVerificationScreen.IDVerificationFragment
import com.zillennium.utswap.utils.DialogUtil
import com.zillennium.utswap.utils.DialogUtilKyc
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class FundPasswordFragment :
    BaseMvpFragment<FundPasswordView.View, FundPasswordView.Presenter, FragmentKycFundPasswordBinding>(),
    FundPasswordView.View {

    override var mPresenter: FundPasswordView.Presenter = FundPasswordPresenter()
    override val layoutResource: Int = R.layout.fragment_kyc_fund_password

    private var clickCountPassword = 1
    private var clickCountConfirmPassword = 1
    private var afterClick: MutableLiveData<Boolean> = MutableLiveData(false)

    companion object {
        var status = ""
    }


    object KycInfor {
        var truename = ""
        var gender = ""
        var occupation = ""
        var companyname = ""
        var email = ""
        var phonenumber = ""
        var citycode = ""
        var districtcode = ""
        var communecode = ""
        var streetnumber = ""
        var idcardinfo = ""
        var idcardfront = ""
        var idcardrear = ""
        var userImage = ""
        var idcard = ""
        var termandcondition = ""
        var paypassword = ""
        var repaypassword = ""
    }

    @SuppressLint("UseCompatLoadingForDrawables", "SetTextI18n")
    override fun initView() {
        super.initView()

        toolBar()
        binding.apply {
//            VerifyPhoneNumber()
//            validateFundPassword()
//            validateConfrimFild()
//            comfirmPasswordBothFiles()
//            validateBtnNext()
//            checkHideRoShowEyes()
            showPassword()
            customFunPassword()

        }

    }

    private fun editTextFocus(editText: EditText) {
        binding.apply {
            editText.setOnFocusChangeListener { view, b ->
                if (b) {
                    when (view) {
                        textView4 -> {
                            textView4.background = ContextCompat.getDrawable(
                                UTSwapApp.instance,
                                R.drawable.bg_border_bottom_active
                            )

                            view.setOnKeyListener { view, i, keyEvent ->
                                if (i == KeyEvent.KEYCODE_DEL) {
                                    textView4.setText("")
                                    textView4.background = ContextCompat.getDrawable(
                                        UTSwapApp.instance,
                                        R.drawable.bg_border_bottom
                                    )
                                    afterClick.value = false
//                                    textView3.filters =
//                                        arrayOf<InputFilter>(LengthFilter(maxLength))

                                }
                                false
                            }
                            textView1.isEnabled = false
                            textView2.isEnabled = false
                            textView3.isEnabled = false
                        }
                        textView3 -> {
                            textView3.background = ContextCompat.getDrawable(
                                UTSwapApp.instance,
                                R.drawable.bg_border_bottom_active
                            )
                            view.setOnKeyListener { view, i, keyEvent ->
                                if (i == KeyEvent.KEYCODE_DEL) {
                                    afterClick.value = false
//                                    textView2.filters =
//                                        arrayOf<InputFilter>(LengthFilter(maxLength))
                                }
                                false
                            }
                            textView1.isEnabled = false
                            textView2.isEnabled = false
                            textView4.isEnabled = false
                        }
                        textView2 -> {
                            textView2.background = ContextCompat.getDrawable(
                                UTSwapApp.instance,
                                R.drawable.bg_border_bottom_active
                            )
                            view.setOnKeyListener { view, i, keyEvent ->
                                if (i == KeyEvent.KEYCODE_DEL) {
                                    afterClick.value = false
//                                    textView1.filters =
//                                        arrayOf<InputFilter>(LengthFilter(maxLength))

                                }
                                false
                            }
                            textView1.isEnabled = false
                            textView3.isEnabled = false
                            textView4.isEnabled = false
                        }
                        textView1 -> {
                            textView1.background = ContextCompat.getDrawable(
                                UTSwapApp.instance,
                                R.drawable.bg_border_bottom_active
                            )
                            textView2.isEnabled = false
                            textView3.isEnabled = false
                            textView4.isEnabled = false
//                            activity?.let {
//                                val inputMethodManager: InputMethodManager = it.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//                                inputMethodManager.showSoftInput(textView1, InputMethodManager.SHOW_FORCED)
//                            }
                        }
                    }
                }
            }
        }
    }

    private fun customFunPassword() {
        var thirdText = ""
        var secondText = ""
        var firstText = ""

        binding.apply {
            editTextFocus(textView1)
            editTextFocus(textView2)
            editTextFocus(textView3)
            editTextFocus(textView4)

            textView1.requestFocus()

            textView1.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                    if (p0.toString()
                            .isNotEmpty() && p0.toString().length < 2 && afterClick.value == false
                    ) {
                        textView1.background = ContextCompat.getDrawable(
                            UTSwapApp.instance,
                            R.drawable.bg_border_bottom_active
                        )
                        firstText = p0.toString()
                        lifecycleScope.launch {
                            delay(100)
                            textView2.isEnabled = true
                            textView2.requestFocus()
                        }

                    } else if (p0.toString().length == 2) {
                        textView1.removeTextChangedListener(this)
                        textView1.setText(firstText)
                        textView1.addTextChangedListener(this)
                        val text = p0?.get(p0.lastIndex)
                        textView2.isEnabled = true
                        textView2.requestFocus()
                        textView2.setText(text.toString())
                    }
                }

                override fun afterTextChanged(p0: Editable?) {
                    if (p0.toString().isNotEmpty()) {
                        textView1.setSelection(1)
                    }
                }
            })

            textView2.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                    if (p0.toString().isNotEmpty() && p0.toString().length < 2) {
                        textView2.background = ContextCompat.getDrawable(
                            UTSwapApp.instance,
                            R.drawable.bg_border_bottom_active
                        )
                        secondText = p0.toString()
                        if (afterClick.value == false) {
                            lifecycleScope.launch {
                                delay(100)
                                textView3.isEnabled = true
                                textView3.requestFocus()
                            }
                        }

                    } else if (p0.toString().length == 2) {
                        textView2.removeTextChangedListener(this)
                        textView2.setText(secondText)
                        textView2.addTextChangedListener(this)
                        val text = p0?.get(p0.lastIndex)
                        textView3.isEnabled = true
                        textView3.requestFocus()
                        textView3.setText(text.toString())
                    }
                }

                override fun afterTextChanged(p0: Editable?) {
                    afterClick.observe(this@FundPasswordFragment) {
                        if (p0.toString().isEmpty() && !it) {
                            textView2.background = ContextCompat.getDrawable(
                                UTSwapApp.instance,
                                R.drawable.bg_border_bottom
                            )
                            textView1.isEnabled = true
                            textView1.requestFocus()
                        }
                    }
                    if (p0.toString().isNotEmpty()) {
                        textView2.setSelection(1)
                    }
                }
            })

            textView3.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {


                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    if (p0.toString().isNotEmpty() && p0.toString().length < 2) {
                        textView3.background = ContextCompat.getDrawable(
                            UTSwapApp.instance,
                            R.drawable.bg_border_bottom_active
                        )
                        thirdText = p0.toString()
                        if (afterClick.value == false) {
                            lifecycleScope.launch {
                                delay(100)
                                textView4.isEnabled = true
                                textView4.requestFocus()
                            }
                        }

                    } else if (p0.toString().length == 2) {
                        textView3.removeTextChangedListener(this)
                        textView3.setText(thirdText)
                        textView3.addTextChangedListener(this)
                        val text = p0?.get(p0.lastIndex)
                        textView4.isEnabled = true
                        textView4.requestFocus()
                        textView4.setText(text.toString())
                    }
                }

                override fun afterTextChanged(p0: Editable?) {
                    afterClick.observe(this@FundPasswordFragment) {
                        if (p0.toString().isEmpty() && !it) {
                            textView3.background = ContextCompat.getDrawable(
                                UTSwapApp.instance,
                                R.drawable.bg_border_bottom
                            )
                            textView2.isEnabled = true
                            textView2.requestFocus()
                        }
                    }
                    if (p0.toString().isNotEmpty()) {
                        textView3.setSelection(1)
                    }

                }
            })

            textView4.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    if (p0.toString().isNotEmpty()) {
                        textView4.background = ContextCompat.getDrawable(
                            UTSwapApp.instance,
                            R.drawable.bg_border_bottom_active
                        )

                    }
                    /*else {
                        textView4.background = ContextCompat.getDrawable(
                            UTSwapApp.instance,
                            R.drawable.bg_border_bottom
                        )
                    }*/
                    textView4.setSelection(p0.toString().length)

                }

                override fun afterTextChanged(p0: Editable?) {
                    afterClick.observe(this@FundPasswordFragment) {
                        if (p0.toString().isEmpty() && !it) {
                            textView3.isEnabled = true
                            textView3.requestFocus()
                        }
                    }


                }
            })
        }
    }


    private fun checkHideRoShowEyes() {
        binding.apply {
            imgShowPassword.setOnClickListener {
                clickCountPassword++
//                showPassword(clickCountPassword)
            }
            imgShowConfirmPassword.setOnClickListener {
                clickCountConfirmPassword++
//                showConfirmPassword(clickCountConfirmPassword)
            }
            imgShowPassword.callOnClick()
            imgShowConfirmPassword.callOnClick()
        }
    }


    fun getFileToByte(filePath: String): String {
        var bmp: Bitmap?
        var bos: ByteArrayOutputStream? = null
        var bt: ByteArray? = null
        var encodeString: String = ""
        try {
            bmp = BitmapFactory.decodeFile(filePath)
            bos = ByteArrayOutputStream()
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, bos)
            bt = bos.toByteArray()
            encodeString = Base64.encodeToString(bt, Base64.DEFAULT)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return encodeString
    }

    private fun validateBtnNext() {
        binding.apply {
            btnNext.setOnClickListener {
                if (editFundPassword.text.toString() == editConfirmFundPassword.text.toString() && editFundPassword.length() == 4 && editConfirmFundPassword.length() == 4) {
                    KYCPreferences().FUND_PASSWORD = editFundPassword.text.toString()


                    KycInfor.truename =
                        "${IDVerificationFragment.sureName} ${IDVerificationFragment.name}"
                    KycInfor.email = KYCPreferences().EMAIL.toString()
                    KycInfor.gender = if (IDVerificationFragment.gender == "Male") "M" else "F"
                    KycInfor.phonenumber = KYCPreferences().PHONE_NUMBER.toString()
                    KycInfor.occupation = EmploymentInfoFragment.occupation
                    KycInfor.companyname = EmploymentInfoFragment.company
                    KycInfor.citycode = IDVerificationFragment.proCode
                    KycInfor.districtcode = IDVerificationFragment.disCode
                    KycInfor.communecode = IDVerificationFragment.comCode
                    KycInfor.streetnumber = IDVerificationFragment.houseNumber
                    KycInfor.idcardinfo = "National Id"
                    KycInfor.idcardfront = IDCardCameraFragment.imageFront
                    KycInfor.idcardrear = IDCardCameraFragment.imageBack
                    KycInfor.userImage = KYCPreferences().SELFIE_HOLDING.toString()
                    KycInfor.termandcondition = KYCPreferences().TERNCONDITION.toString()
                    KycInfor.paypassword = KYCPreferences().FUND_PASSWORD.toString()
                    KycInfor.repaypassword = KYCPreferences().FUND_PASSWORD.toString()

                    var idCardFront =
                        "data:image/jpeg;base64," + getFileToByte(KycInfor.idcardfront)
                    var idCardBack = "data:image/jpeg;base64," + getFileToByte(KycInfor.idcardrear)
                    var imageUser = "data:image/jpeg;base64," + getFileToByte(KycInfor.userImage)

                    mPresenter.addKyc(
                        User.Kyc(
                            KycInfor.truename,
                            KycInfor.gender,
                            KycInfor.occupation,
                            KycInfor.companyname,
                            KycInfor.email,
                            "",
                            "",
                            KycInfor.citycode,
                            KycInfor.districtcode,
                            KycInfor.communecode,
                            KycInfor.streetnumber,
                            KycInfor.idcardinfo,
                            idCardFront,
                            idCardBack,
                            imageUser,
                            KycInfor.idcard,
                            "1",
                            KycInfor.paypassword,
                            KycInfor.repaypassword
                        ), requireActivity()
                    )
                    progressBar.visibility = View.VISIBLE
                } else {
                    for (child in numberVerification.children) {
                        child.background = ContextCompat.getDrawable(
                            UTSwapApp.instance,
                            R.drawable.bg_border_bottom_red
                        )
                        child as TextView
                        child.setTextColor(
                            ContextCompat.getColor(
                                UTSwapApp.instance,
                                R.color.danger
                            )
                        )
                    }
                    for (child in confirmNumberVerification.children) {
                        child.background = ContextCompat.getDrawable(
                            UTSwapApp.instance,
                            R.drawable.bg_border_bottom_red
                        )
                        child as TextView
                        child.setTextColor(
                            ContextCompat.getColor(
                                UTSwapApp.instance,
                                R.color.danger
                            )
                        )
                    }
                    Toast.makeText(
                        UTSwapApp.instance,
                        "Fund Password is not match",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun VerifyPhoneNumber() {
        binding.apply {
            numberVerification.setOnClickListener {
                editFundPassword.requestFocus()
                val inputManager1 =
                    requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputManager1.showSoftInput(editFundPassword, InputMethodManager.SHOW_IMPLICIT)

                if (editFundPassword.text.isEmpty()) {
                    val textInputLast = numberVerification.getChildAt(0) as TextView
                    textInputLast.text = "|"
                    textInputLast.transformationMethod =
                        HideReturnsTransformationMethod.getInstance()
                }

                if (editConfirmFundPassword.text.isEmpty()) {
                    val confirmTextInputLast = confirmNumberVerification.getChildAt(0) as TextView
                    confirmTextInputLast.text = ""
                }
            }
        }
    }

    private fun validateFundPassword() {
        binding.apply {
            editFundPassword.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(ch: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun onTextChanged(chr: CharSequence?, p1: Int, p2: Int, p3: Int) {

                    for (child in numberVerification.children) {
                        val children = child as TextView
                        children.background = ContextCompat.getDrawable(
                            UTSwapApp.instance,
                            R.drawable.bg_border_bottom
                        )
                        children.text = ""
//                        showPassword(clickCountPassword)
                        children.setTextColor(
                            ContextCompat.getColor(
                                UTSwapApp.instance,
                                R.color.black
                            )
                        )
                    }

                    if (chr?.length!! <= 3) {
                        val textInputLast =
                            chr.length.let { numberVerification.getChildAt(it.toInt()) } as TextView
                        textInputLast.text = "|"
                        textInputLast.transformationMethod =
                            HideReturnsTransformationMethod.getInstance()
                    }

                    for (index in chr.indices) {
                        val textInput = numberVerification.getChildAt(index) as TextView
                        textInput.text = chr[index].toString()
                        if (index == numberVerification.childCount - 1) {
                            confirmNumberVerification.callOnClick()
                        }
                    }
                }

                override fun afterTextChanged(p0: Editable?) {}
            })
        }
    }

    private fun validateConfrimFild() {
        binding.apply {
            editConfirmFundPassword.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(ch: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun onTextChanged(chr: CharSequence?, p1: Int, p2: Int, p3: Int) {

                    for (child in confirmNumberVerification.children) {
                        val children = child as TextView
                        children.background = ContextCompat.getDrawable(
                            UTSwapApp.instance,
                            R.drawable.bg_border_bottom
                        )
                        children.text = ""
                        children.setTextColor(
                            ContextCompat.getColor(
                                UTSwapApp.instance,
                                R.color.black
                            )
                        )
                        showConfirmPassword(clickCountConfirmPassword)
                    }

                    if (chr?.length!! <= 3) {
                        val textInputLast =
                            chr.length.let { confirmNumberVerification.getChildAt(it.toInt()) } as TextView
                        textInputLast.text = "|"
                        textInputLast.transformationMethod =
                            HideReturnsTransformationMethod.getInstance()
                    }

                    for (index in chr.indices) {
                        val textInput2 = confirmNumberVerification.getChildAt(index) as TextView
                        textInput2.text = chr[index].toString()
                        if (index == numberVerification.childCount - 1) {
                            val inputManager2 =
                                requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                            inputManager2.hideSoftInputFromWindow(view?.windowToken, 0)
                        }
                    }
                }

                override fun afterTextChanged(p0: Editable?) {}
            })
        }
    }

    private fun comfirmPasswordBothFiles() {
        binding.apply {

            /* Confirm Fund Password code */
            confirmNumberVerification.setOnClickListener {
                editConfirmFundPassword.requestFocus()
                val inputManager2 =
                    requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputManager2.showSoftInput(
                    editConfirmFundPassword,
                    InputMethodManager.SHOW_IMPLICIT
                )

                if (editConfirmFundPassword.text.isEmpty()) {
                    val confirmTextInputLast = confirmNumberVerification.getChildAt(0) as TextView
                    confirmTextInputLast.text = "|"
                    confirmTextInputLast.transformationMethod =
                        HideReturnsTransformationMethod.getInstance()
                }

                if (editFundPassword.text.isEmpty()) {
                    val textInputLast = numberVerification.getChildAt(0) as TextView
                    textInputLast.text = ""
                }
            }
        }
    }

    private fun toolBar() {
        binding.apply {
            includeLayout.apply {
                cdBack.setOnClickListener {
                    findNavController().popBackStack()
                }
            }
        }
    }

    override fun addKycSuccess(data: User.KycRes) {
        /*(activity as MainActivity).statusKYC  = data.status*/
        status = data.status

        if (data.status == "0") {
            KYCPreferences().DO_KYC_STATUS = data.status
            binding.apply {
                progressBar.visibility = View.GONE
            }
            DialogUtilKyc().customDialog(
                R.drawable.icon_log_out,
                "KYC Issue",
                "There has been an issue with your KYC submission. Please try again.",
                "ok",
                object : DialogUtil.OnAlertDialogClick {
                    override fun onLabelCancelClick() {
                        activity?.finish()
                    }
                },
                requireActivity()
            )
        }
        if (data.status == "1") {

            KYCPreferences().DO_KYC_STATUS = data.status
            SessionPreferences().SESSION_KYC_SUBMIT_STATUS = data.data?.status_kyc_submit
            SessionPreferences().SESSION_KYC = data.data?.status_kyc_approved
            binding.apply {
                progressBar.visibility = View.GONE
            }
            findNavController().navigate(R.id.action_to_contract_kyc_fragment)

            IDVerificationFragment.apply {
                provice = ""
                district = ""
                commune = ""
                name = ""
                sureName = ""
                gender = ""
                date = ""
                houseNumber = ""
                proCode = ""
                disCode = ""
                comCode = ""
            }
            IDCardCameraFragment.apply {
                imageFront = ""
                imageBack = ""
            }
            EmploymentInfoFragment.apply {
                occupation = ""
                company = ""
            }

        }
    }

    override fun addKycFail(data: String) {
        DialogUtilKyc().customDialog(
            R.drawable.icon_log_out,
            "KYC Issue",
            "There has been an issue with your KYC submission. Please try again.",
            "ok",
            object : DialogUtil.OnAlertDialogClick {
                override fun onLabelCancelClick() {
                    activity?.finish()
                }
            },
            requireActivity()
        )
    }


    @SuppressLint("UseCompatLoadingForDrawables")
    private fun showPassword() {
        binding.apply {
            /*for (child in numberVerification.children) {
                child as EditText
                child.transformationMethod = PasswordTransformationMethod.getInstance()
            }*/
            textView1.transformationMethod = PasswordTransformationMethod.getInstance()
            textView2.transformationMethod = PasswordTransformationMethod.getInstance()
            textView3.transformationMethod = PasswordTransformationMethod.getInstance()
            textView4.transformationMethod = PasswordTransformationMethod.getInstance()

            imgShowPassword.setImageResource(R.drawable.ic_baseline_visibility_off_24)
            imgShowPassword.setOnClickListener {
                afterClick.value = true
                if (clickCountPassword % 2 == 0) {
                    textView1.transformationMethod = PasswordTransformationMethod.getInstance()
                    textView2.transformationMethod = PasswordTransformationMethod.getInstance()
                    textView3.transformationMethod = PasswordTransformationMethod.getInstance()
                    textView4.transformationMethod = PasswordTransformationMethod.getInstance()

                    imgShowPassword.setImageResource(R.drawable.ic_baseline_visibility_off_24)
                    clickCountPassword++
                } else {
                    textView1.transformationMethod = HideReturnsTransformationMethod.getInstance()
                    textView2.transformationMethod = HideReturnsTransformationMethod.getInstance()
                    textView3.transformationMethod = HideReturnsTransformationMethod.getInstance()
                    textView4.transformationMethod = HideReturnsTransformationMethod.getInstance()
                    clickCountPassword++
                    imgShowPassword.setImageResource(R.drawable.ic_baseline_remove_red_eye_24)
                }
            }
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun showConfirmPassword(clickConfirmPassword: Int) {
        binding.apply {
            if (clickConfirmPassword % 2 == 0) {
                for (child in confirmNumberVerification.children) {
                    val children = child as EditText
                    children.transformationMethod = PasswordTransformationMethod.getInstance()
                }
                imgShowConfirmPassword.setImageResource(R.drawable.ic_baseline_visibility_off_24)
            } else {
                for (child in confirmNumberVerification.children) {
                    val children = child as TextView
                    children.transformationMethod = HideReturnsTransformationMethod.getInstance()

                }
                imgShowConfirmPassword.setImageResource(R.drawable.ic_baseline_remove_red_eye_24)

            }
        }

    }
}
