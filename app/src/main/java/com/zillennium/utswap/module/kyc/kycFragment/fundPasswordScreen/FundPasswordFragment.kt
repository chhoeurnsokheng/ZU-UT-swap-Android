package com.zillennium.utswap.module.kyc.kycFragment.fundPasswordScreen

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.text.Editable
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Base64
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.children
import androidx.navigation.fragment.findNavController
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.io.output.ByteArrayOutputStream
import com.zillennium.utswap.Datas.GlobalVariable.SessionVariable
import com.zillennium.utswap.Datas.StoredPreferences.KYCPreferences
import com.zillennium.utswap.Datas.StoredPreferences.SessionPreferences
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.FragmentKycFundPasswordBinding
import com.zillennium.utswap.models.userService.User
import com.zillennium.utswap.utils.DialogUtil
import com.zillennium.utswap.utils.DialogUtilKyc

class FundPasswordFragment :
    BaseMvpFragment<FundPasswordView.View, FundPasswordView.Presenter, FragmentKycFundPasswordBinding>(),
    FundPasswordView.View {

    override var mPresenter: FundPasswordView.Presenter = FundPasswordPresenter()
    override val layoutResource: Int = R.layout.fragment_kyc_fund_password

    private var clickCountPassword = 1
    private var clickCountConfirmPassword = 1


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
            VerifyPhoneNumber()
            validateFundPassword()
            validateConfrimFild()
            comfirmPasswordBothFiles()
            validateBtnNext()
            checkHideRoShowEyes()
        }


    }

    private fun checkHideRoShowEyes() {
        binding.apply {
            imgShowPassword.setOnClickListener {
                clickCountPassword++
                showPassword(clickCountPassword)
            }
            imgShowConfirmPassword.setOnClickListener {
                clickCountConfirmPassword++
                showConfirmPassword(clickCountConfirmPassword)
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

                    KycInfor.truename = KYCPreferences().FIRST_NAME.toString()
                    KycInfor.email = KYCPreferences().EMAIL.toString()
                    KycInfor.gender = KYCPreferences().GENDER.toString()
                    KycInfor.phonenumber = KYCPreferences().PHONE_NUMBER.toString()
                    KycInfor.occupation = KYCPreferences().OCCUPATION.toString()
                    KycInfor.companyname = KYCPreferences().COMPANY.toString()
                    KycInfor.citycode = KYCPreferences().CITY_PROVINCE.toString()
                    KycInfor.districtcode = KYCPreferences().DISTRICT_KHAN.toString()
                    KycInfor.communecode = KYCPreferences().COMMUNE_SANGKAT.toString()
                    KycInfor.streetnumber = KYCPreferences().ADDRESS.toString()
                    KycInfor.idcardinfo = KYCPreferences().ID_CARD_INFOR.toString()
                    KycInfor.idcardfront = KYCPreferences().NATIONAL_ID_FRONT.toString()
                    KycInfor.idcardrear = KYCPreferences().NATIONAL_ID_BACK.toString()
                    KycInfor.userImage = KYCPreferences().SELFIE_HOLDING.toString()
                    KycInfor.termandcondition = KYCPreferences().TERNCONDITION.toString()
                    KycInfor.paypassword = KYCPreferences().FUND_PASSWORD.toString()
                    KycInfor.repaypassword = KYCPreferences().FUND_PASSWORD.toString()

                    var idCardFront = "data:image/jpeg;base64," + getFileToByte(KycInfor.idcardfront)
                    var idCardBack = "data:image/jpeg;base64," + getFileToByte(KycInfor.idcardrear)
                    var imageUser = "data:image/jpeg;base64," + getFileToByte(KycInfor.userImage)

                    mPresenter.addKyc(
                        User.Kyc(
                            KycInfor.truename,
                            KycInfor.gender,
                            KycInfor.occupation,
                            KycInfor.companyname,
                            KycInfor.email,
                            KycInfor.phonenumber,
                            "+855",
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
                    progressBar.visibility =View.VISIBLE
                } else {
                    for (child in numberVerification.children) {
                        child.background = ContextCompat.getDrawable(
                            UTSwapApp.instance,
                            R.drawable.bg_border_bottom_red
                        )
                    }
                    for (child in confirmNumberVerification.children) {
                        child.background = ContextCompat.getDrawable(
                            UTSwapApp.instance,
                            R.drawable.bg_border_bottom_red
                        )
                    }
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
                        showPassword(clickCountPassword)
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
                        showConfirmPassword(clickCountConfirmPassword)
                    }

                    if (chr?.length!! <= 3) {
                        val textInputLast =
                            chr.length.let { confirmNumberVerification.getChildAt(it.toInt()) } as TextView
                        textInputLast.text = "|"
                        textInputLast.transformationMethod =
                            HideReturnsTransformationMethod.getInstance()
                    }

                    for (index in chr?.indices) {
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
                    requireActivity().finish()
                }
            }
        }
    }

    override fun addKycSuccess(data: User.KycRes) {
        if (data.status == 0) {
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
        if (data.status == 1) {
            binding.apply {
                progressBar.visibility =View.GONE
            }
            findNavController().navigate(R.id.action_to_contract_kyc_fragment)
        }
    }

    override fun addKycFail(data: String) {}

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun showPassword(clickPassword: Int) {
        binding.apply {
            if (clickPassword % 2 == 0) {
                numberVerification
                for (child in numberVerification.children) {
                    val children = child as TextView
                    children.transformationMethod = PasswordTransformationMethod.getInstance()
                }
                imgShowPassword.setImageResource(R.drawable.ic_baseline_visibility_off_24)
            } else {
                for (child in numberVerification.children) {
                    val children = child as TextView
                    children.transformationMethod = HideReturnsTransformationMethod.getInstance()
                }

                imgShowPassword.setImageResource(R.drawable.ic_baseline_remove_red_eye_24)
            }
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun showConfirmPassword(clickConfirmPassword: Int) {
        binding.apply {
            if (clickConfirmPassword % 2 == 0) {
                for (child in confirmNumberVerification.children) {
                    val children = child as TextView
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
