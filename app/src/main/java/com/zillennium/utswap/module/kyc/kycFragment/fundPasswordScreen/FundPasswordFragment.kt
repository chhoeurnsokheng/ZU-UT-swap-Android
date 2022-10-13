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
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.children
import androidx.core.view.forEach
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
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent.setEventListener
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener


class FundPasswordFragment :
    BaseMvpFragment<FundPasswordView.View, FundPasswordView.Presenter, FragmentKycFundPasswordBinding>(),
    FundPasswordView.View {

    override var mPresenter: FundPasswordView.Presenter = FundPasswordPresenter()
    override val layoutResource: Int = R.layout.fragment_kyc_fund_password

    private var clickCountPassword = 1
    private var count = 1
    private var afterClick = false
    private var clickHideShow = false
    private var isError = false
    private var isErrorConfirm = false
    private var showAfterHideKeyboard = false
    private var newFundPassword = ""
    private var confirmPassword = ""

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

    @SuppressLint("UseCompatLoadingForDrawables", "SetTextI18n", "ClickableViewAccessibility")

    override fun initView() {
        super.initView()
        toolBar()

        binding.root.rootView.setOnKeyListener { view, i, keyEvent ->
            if (i == KeyEvent.KEYCODE_BACK) {
                Toast.makeText(UTSwapApp.instance, "Back", Toast.LENGTH_SHORT).show()
            }
            false
        }
        binding.apply {
            /** touch child in confirm fund to clear focus on new fund*/
            llCoverConfirm.setOnClickListener { it1 ->
                activity?.apply {
                    if (showAfterHideKeyboard) {
                        showKeyboard(this)
                    }
                }

                when {
                    etConfirmFour.text.isNotEmpty() || etConfirmThree.text.isNotEmpty() -> {
                        etConfirmFour.isEnabled = true
                        etConfirmFour.requestFocus()
                    }
                    etConfirmTwo.text.isNotEmpty() -> {
                        etConfirmThree.isEnabled = true
                        etConfirmThree.requestFocus()
                    }
                    etConfirmOne.text.isNotEmpty() -> {
                        etConfirmTwo.isEnabled = true
                        etConfirmTwo.requestFocus()
                    }
                    etConfirmOne.text.isEmpty() -> {
                        etConfirmOne.isEnabled = true
                        etConfirmOne.requestFocus()
                    }

                }

                if (etFour.text.isEmpty()) {
                    etFour.background = ContextCompat.getDrawable(
                        UTSwapApp.instance,
                        R.drawable.bg_border_bottom
                    )
                }
                if (etThree.text.isEmpty()) {
                    etThree.background = ContextCompat.getDrawable(
                        UTSwapApp.instance,
                        R.drawable.bg_border_bottom
                    )
                }
                if (etTwo.text.isEmpty()) {
                    etTwo.background = ContextCompat.getDrawable(
                        UTSwapApp.instance,
                        R.drawable.bg_border_bottom
                    )
                }
                if (etOne.text.isEmpty()) {
                    etOne.background = ContextCompat.getDrawable(
                        UTSwapApp.instance,
                        R.drawable.bg_border_bottom
                    )
                }
            }

            /**  when click new fund from confirm fund to focus after EditText has value and clear focus on confirm fund */
            llCover.setOnClickListener {
                activity?.apply {
                    if (showAfterHideKeyboard) {
                        showKeyboard(this)
                    }
                }

                when {
                    etFour.text.isNotEmpty() || etThree.text.isNotEmpty() -> {
                        etFour.isEnabled = true
                        etFour.requestFocus()
                    }
                    etTwo.text.isNotEmpty() -> {
                        etThree.isEnabled = true
                        etThree.requestFocus()
                    }
                    etOne.text.isNotEmpty() -> {
                        etTwo.isEnabled = true
                        etTwo.requestFocus()
                    }
                    etOne.text.isEmpty() -> {
                        etOne.isEnabled = true
                        etOne.requestFocus()
                    }

                }

                if (etConfirmFour.text.isEmpty()) {
                    etConfirmFour.background = ContextCompat.getDrawable(
                        UTSwapApp.instance,
                        R.drawable.bg_border_bottom
                    )
                }
                if (etConfirmThree.text.isEmpty()) {
                    etConfirmThree.background = ContextCompat.getDrawable(
                        UTSwapApp.instance,
                        R.drawable.bg_border_bottom
                    )
                }
                if (etConfirmTwo.text.isEmpty()) {
                    etConfirmTwo.background = ContextCompat.getDrawable(
                        UTSwapApp.instance,
                        R.drawable.bg_border_bottom
                    )
                }
                if (etConfirmOne.text.isEmpty()) {
                    etConfirmOne.background = ContextCompat.getDrawable(
                        UTSwapApp.instance,
                        R.drawable.bg_border_bottom
                    )
                }

            }

            llFundPassword.setOnClickListener {
                hideKeyboard()

            }

            validateBtnNext()
            showPassword()

            customFunPassword()

        }

    }

    private fun editTextFocus(editText: EditText) {
        binding.apply {
            editText.setOnFocusChangeListener { view, b ->
                if (b) {
                    activity?.let {
                        showKeyboard(it)
                    }
                    when (view) {
                        etFour -> {
                            etFour.background = ContextCompat.getDrawable(
                                UTSwapApp.instance,
                                R.drawable.bg_border_bottom_active
                            )
                            etOne.isEnabled = false
                            etTwo.isEnabled = false
                            etThree.isEnabled = false
                        }
                        etThree -> {
                            etThree.background = ContextCompat.getDrawable(
                                UTSwapApp.instance,
                                R.drawable.bg_border_bottom_active
                            )
                            etOne.isEnabled = false
                            etTwo.isEnabled = false
                            etFour.isEnabled = false
                        }
                        etTwo -> {
                            etTwo.background = ContextCompat.getDrawable(
                                UTSwapApp.instance,
                                R.drawable.bg_border_bottom_active
                            )
                            etOne.isEnabled = false
                            etThree.isEnabled = false
                            etFour.isEnabled = false
                        }
                        etOne -> {
                            etOne.background = ContextCompat.getDrawable(
                                UTSwapApp.instance,
                                R.drawable.bg_border_bottom_active
                            )
                            etTwo.isEnabled = false
                            etThree.isEnabled = false
                            etFour.isEnabled = false
                        }


                        etConfirmFour -> {
                            etConfirmFour.background = ContextCompat.getDrawable(
                                UTSwapApp.instance,
                                R.drawable.bg_border_bottom_active
                            )
                            etConfirmOne.isEnabled = false
                            etConfirmTwo.isEnabled = false
                            etConfirmThree.isEnabled = false
                        }
                        etConfirmThree -> {
                            etConfirmThree.background = ContextCompat.getDrawable(
                                UTSwapApp.instance,
                                R.drawable.bg_border_bottom_active
                            )
                            etConfirmOne.isEnabled = false
                            etConfirmTwo.isEnabled = false
                            etConfirmFour.isEnabled = false
                        }
                        etConfirmTwo -> {
                            etConfirmTwo.background = ContextCompat.getDrawable(
                                UTSwapApp.instance,
                                R.drawable.bg_border_bottom_active
                            )
                            etConfirmOne.isEnabled = false
                            etConfirmThree.isEnabled = false
                            etConfirmFour.isEnabled = false
                        }
                        etConfirmOne -> {
                            etConfirmOne.background = ContextCompat.getDrawable(
                                UTSwapApp.instance,
                                R.drawable.bg_border_bottom_active
                            )
                            etConfirmTwo.isEnabled = false
                            etConfirmThree.isEnabled = false
                            etConfirmFour.isEnabled = false
                        }

                    }
                    if (isError) {
                        etOne.background = ContextCompat.getDrawable(
                            UTSwapApp.instance,
                            R.drawable.bg_border_bottom_red
                        )
                        etTwo.background = ContextCompat.getDrawable(
                            UTSwapApp.instance,
                            R.drawable.bg_border_bottom_red
                        )
                        etThree.background = ContextCompat.getDrawable(
                            UTSwapApp.instance,
                            R.drawable.bg_border_bottom_red
                        )
                        etFour.background = ContextCompat.getDrawable(
                            UTSwapApp.instance,
                            R.drawable.bg_border_bottom_red
                        )

                    }
                    if (isErrorConfirm) {
                        etConfirmOne.background = ContextCompat.getDrawable(
                            UTSwapApp.instance,
                            R.drawable.bg_border_bottom_red
                        )
                        etConfirmTwo.background = ContextCompat.getDrawable(
                            UTSwapApp.instance,
                            R.drawable.bg_border_bottom_red
                        )
                        etConfirmThree.background = ContextCompat.getDrawable(
                            UTSwapApp.instance,
                            R.drawable.bg_border_bottom_red
                        )
                        etConfirmFour.background = ContextCompat.getDrawable(
                            UTSwapApp.instance,
                            R.drawable.bg_border_bottom_red
                        )
                    }
                } else {
                    /** clear focus when keyboard hide*/
                    activity?.let { it1 ->
                        setEventListener(
                            it1,
                            object : KeyboardVisibilityEventListener {
                                override fun onVisibilityChanged(isOpen: Boolean) {
                                    if (!isOpen) {
                                        binding.numberVerification.forEach {
                                            if (it is EditText) {
                                                if (it.text.isEmpty()) {
                                                    it.background = ContextCompat.getDrawable(
                                                        UTSwapApp.instance,
                                                        R.drawable.bg_border_bottom
                                                    )
                                                }
                                                it.clearFocus()

                                            }

                                        }
                                        binding.confirmNumberVerification.forEach {
                                            if (it is EditText) {
                                                if (it.text.isEmpty()) {
                                                    it.background = ContextCompat.getDrawable(
                                                        UTSwapApp.instance,
                                                        R.drawable.bg_border_bottom
                                                    )
                                                }
                                                it.clearFocus()

                                            }
                                        }

                                    }
                                }
                            })

                    }
                }
            }
        }
    }

    private fun customFunPassword() {
        var thirdText = ""
        var secondText = ""
        var firstText = ""
        var firstTextConfirm = ""
        var secondTextConfirm = ""
        var thirdTextConfirm = ""

        binding.apply {
            editTextFocus(etOne)
            editTextFocus(etTwo)
            editTextFocus(etThree)
            editTextFocus(etFour)
            editTextFocus(etConfirmOne)
            editTextFocus(etConfirmTwo)
            editTextFocus(etConfirmThree)
            editTextFocus(etConfirmFour)
            etOne.requestFocus()

            etFour.setOnKeyListener { view, i, keyEvent ->
                if (i == KeyEvent.KEYCODE_DEL) {
                    etFour.background = ContextCompat.getDrawable(
                        UTSwapApp.instance,
                        R.drawable.bg_border_bottom
                    )
                    lifecycleScope.launch {
                        delay(50)
                        etThree.isEnabled = true
                        etThree.requestFocus()
                    }

                }
                false
            }
            etThree.setOnKeyListener { view, i, keyEvent ->
                if (i == KeyEvent.KEYCODE_DEL) {
                    etThree.background = ContextCompat.getDrawable(
                        UTSwapApp.instance,
                        R.drawable.bg_border_bottom
                    )
                    lifecycleScope.launch {
                        delay(50)
                        etTwo.isEnabled = true
                        etTwo.requestFocus()
                    }

                }
                false
            }
            etTwo.setOnKeyListener { view, i, keyEvent ->
                if (i == KeyEvent.KEYCODE_DEL) {
                    etTwo.background = ContextCompat.getDrawable(
                        UTSwapApp.instance,
                        R.drawable.bg_border_bottom
                    )
                    lifecycleScope.launch {
                        delay(50)
                        etOne.isEnabled = true
                        etOne.requestFocus()
                    }

                }
                false
            }

            etConfirmFour.setOnKeyListener { view, i, keyEvent ->
                if (i == KeyEvent.KEYCODE_DEL) {
                    etConfirmFour.background = ContextCompat.getDrawable(
                        UTSwapApp.instance,
                        R.drawable.bg_border_bottom
                    )
                    lifecycleScope.launch {
                        delay(50)
                        etConfirmThree.isEnabled = true
                        etConfirmThree.requestFocus()
                    }


                }
                false
            }
            etConfirmThree.setOnKeyListener { view, i, keyEvent ->
                if (i == KeyEvent.KEYCODE_DEL) {
                    etConfirmThree.background = ContextCompat.getDrawable(
                        UTSwapApp.instance,
                        R.drawable.bg_border_bottom
                    )
                    lifecycleScope.launch {
                        delay(50)
                        etConfirmTwo.isEnabled = true
                        etConfirmTwo.requestFocus()
                    }

                }
                false
            }
            etConfirmTwo.setOnKeyListener { view, i, keyEvent ->
                if (i == KeyEvent.KEYCODE_DEL) {
                    etConfirmTwo.background = ContextCompat.getDrawable(
                        UTSwapApp.instance,
                        R.drawable.bg_border_bottom
                    )
                    lifecycleScope.launch {
                        delay(50)
                        etConfirmOne.isEnabled = true
                        etConfirmOne.requestFocus()
                    }

                }
                false
            }

            etOne.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                    if (p0.toString().isNotEmpty() && p0.toString().length < 2) {
                        etOne.background = ContextCompat.getDrawable(
                            UTSwapApp.instance,
                            R.drawable.bg_border_bottom_active
                        )
                        firstText = p0.toString()
                        if (!afterClick) {
                            lifecycleScope.launch {
                                delay(100)
                                etTwo.isEnabled = true
                                etTwo.requestFocus()
                            }
                        }

                    } else if (p0.toString().length == 2) {
                        etOne.removeTextChangedListener(this)
                        etOne.setText(firstText)
                        etOne.addTextChangedListener(this)
                        val text = p0?.get(p0.lastIndex)
                        etTwo.isEnabled = true
                        etTwo.requestFocus()
                        etTwo.setText(text.toString())
                    }
                }

                override fun afterTextChanged(p0: Editable?) {
                    etOne.setOnKeyListener { view, i, keyEvent ->
                        if (i == KeyEvent.KEYCODE_DEL) {
                            afterClick = false

                        }
                        false
                    }
                    if (p0.toString().isNotEmpty()) {
                        etOne.setSelection(1)
                    }
                }
            })
            etTwo.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                    if (p0.toString().isNotEmpty() && p0.toString().length < 2) {
                        etTwo.background = ContextCompat.getDrawable(
                            UTSwapApp.instance,
                            R.drawable.bg_border_bottom_active
                        )
                        secondText = p0.toString()
                        if (!afterClick) {
                            lifecycleScope.launch {
                                delay(100)
                                etThree.isEnabled = true
                                etThree.requestFocus()
                            }
                        }

                    } else if (p0.toString().length == 2) {
                        etTwo.removeTextChangedListener(this)
                        etTwo.setText(secondText)
                        etTwo.addTextChangedListener(this)
                        val text = p0?.get(p0.lastIndex)
                        etThree.isEnabled = true
                        etThree.requestFocus()
                        etThree.setText(text.toString())
                    }
                }

                override fun afterTextChanged(p0: Editable?) {
                    etTwo.setOnKeyListener { view, i, keyEvent ->
                        if (i == KeyEvent.KEYCODE_DEL) {
                            etTwo.background = ContextCompat.getDrawable(
                                UTSwapApp.instance,
                                R.drawable.bg_border_bottom
                            )
                            lifecycleScope.launch {
                                delay(50)
                                etOne.isEnabled = true
                                etOne.requestFocus()
                            }
                            afterClick = false

                        }
                        false
                    }
                    if (p0.toString().isNotEmpty()) {
                        etTwo.setSelection(1)

                    }


                }
            })
            etThree.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {


                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    if (p0.toString().isNotEmpty() && p0.toString().length < 2) {
                        etThree.background = ContextCompat.getDrawable(
                            UTSwapApp.instance,
                            R.drawable.bg_border_bottom_active
                        )
                        thirdText = p0.toString()
                        if (!afterClick) {
                            lifecycleScope.launch {
                                delay(100)
                                etFour.isEnabled = true
                                etFour.requestFocus()
                            }
                        }

                    } else if (p0.toString().length == 2) {
                        etThree.removeTextChangedListener(this)
                        etThree.setText(thirdText)
                        etThree.addTextChangedListener(this)
                        val text = p0?.get(p0.lastIndex)
                        etFour.isEnabled = true
                        etFour.requestFocus()
                        etFour.setText(text.toString())
                    }
                }

                override fun afterTextChanged(p0: Editable?) {
                    etThree.setOnKeyListener { view, i, keyEvent ->
                        if (i == KeyEvent.KEYCODE_DEL) {
                            etThree.background = ContextCompat.getDrawable(
                                UTSwapApp.instance,
                                R.drawable.bg_border_bottom
                            )
                            lifecycleScope.launch {
                                delay(50)
                                etTwo.isEnabled = true
                                etTwo.requestFocus()
                            }
                            afterClick = false


                        }
                        false
                    }
                    if (p0.toString().isNotEmpty()) {
                        etThree.setSelection(1)
                    }


                }
            })
            etFour.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    if (p0.toString().isNotEmpty()) {
                        etFour.background = ContextCompat.getDrawable(
                            UTSwapApp.instance,
                            R.drawable.bg_border_bottom_active
                        )
                    }
                    etFour.setSelection(p0.toString().length)
                }

                override fun afterTextChanged(p0: Editable?) {
                    btnNext.isEnabled = etConfirmFour.text.isNotEmpty() && etFour.text.isNotEmpty()
                    etFour.setOnKeyListener { view, i, keyEvent ->
                        if (i == KeyEvent.KEYCODE_DEL) {
                            etFour.background = ContextCompat.getDrawable(
                                UTSwapApp.instance,
                                R.drawable.bg_border_bottom
                            )
                            lifecycleScope.launch {
                                delay(50)
                                etThree.isEnabled = true
                                etThree.requestFocus()
                            }
                            if (isError) {
                                etOne.background = ContextCompat.getDrawable(
                                    UTSwapApp.instance,
                                    R.drawable.bg_border_bottom_active
                                )
                                etTwo.background = ContextCompat.getDrawable(
                                    UTSwapApp.instance,
                                    R.drawable.bg_border_bottom_active
                                )
                                etThree.background = ContextCompat.getDrawable(
                                    UTSwapApp.instance,
                                    R.drawable.bg_border_bottom_active
                                )
                                etOne.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.black))
                                etTwo.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.black))
                                etThree.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.black))
                                etFour.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.black))
                            }
                            isError = false
                            afterClick = false
                            btnNext.isEnabled = false
                        }
                        false
                    }
                    afterClick = false
                    if (isError) {
                        showError()
                    }
                }
            })

            etConfirmOne.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                    if (p0.toString().isNotEmpty() && p0.toString().length < 2) {
                        etConfirmOne.background = ContextCompat.getDrawable(
                            UTSwapApp.instance,
                            R.drawable.bg_border_bottom_active
                        )
                        firstTextConfirm = p0.toString()
                        if (!clickHideShow) {
                            lifecycleScope.launch {
                                delay(100)
                                etConfirmTwo.isEnabled = true
                                etConfirmTwo.requestFocus()
                            }
                        }

                    } else if (p0.toString().length == 2) {
                        etConfirmOne.removeTextChangedListener(this)
                        etConfirmOne.setText(firstTextConfirm)
                        etConfirmOne.addTextChangedListener(this)
                        val text = p0?.get(p0.lastIndex)
                        etConfirmTwo.isEnabled = true
                        etConfirmTwo.requestFocus()
                        etConfirmTwo.setText(text.toString())
                    }
                }

                override fun afterTextChanged(p0: Editable?) {
                    etConfirmOne.setOnKeyListener { view, i, keyEvent ->
                        if (i == KeyEvent.KEYCODE_DEL) {
                            clickHideShow = false

                        }
                        false
                    }
                    if (p0.toString().isNotEmpty()) {
                        etConfirmOne.setSelection(1)
                    }
                }
            })
            etConfirmTwo.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                    if (p0.toString().isNotEmpty() && p0.toString().length < 2) {
                        etConfirmTwo.background = ContextCompat.getDrawable(
                            UTSwapApp.instance,
                            R.drawable.bg_border_bottom_active
                        )
                        secondTextConfirm = p0.toString()
                        if (!clickHideShow) {
                            lifecycleScope.launch {
                                delay(100)
                                etConfirmThree.isEnabled = true
                                etConfirmThree.requestFocus()
                            }
                        }

                    } else if (p0.toString().length == 2) {
                        etConfirmTwo.removeTextChangedListener(this)
                        etConfirmTwo.setText(secondTextConfirm)
                        etConfirmTwo.addTextChangedListener(this)
                        val text = p0?.get(p0.lastIndex)
                        etConfirmThree.isEnabled = true
                        etConfirmThree.requestFocus()
                        etConfirmThree.setText(text.toString())
                    }
                }

                override fun afterTextChanged(p0: Editable?) {
                    etConfirmTwo.setOnKeyListener { view, i, keyEvent ->
                        if (i == KeyEvent.KEYCODE_DEL) {
                            etConfirmTwo.background = ContextCompat.getDrawable(
                                UTSwapApp.instance,
                                R.drawable.bg_border_bottom
                            )
                            lifecycleScope.launch {
                                delay(50)
                                etConfirmOne.isEnabled = true
                                etConfirmOne.requestFocus()
                            }
                            clickHideShow = false

                        }
                        false
                    }
                    if (p0.toString().isNotEmpty()) {
                        etConfirmTwo.setSelection(1)

                    }


                }
            })
            etConfirmThree.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                    if (p0.toString().isNotEmpty() && p0.toString().length < 2) {
                        etConfirmThree.background = ContextCompat.getDrawable(
                            UTSwapApp.instance,
                            R.drawable.bg_border_bottom_active
                        )
                        thirdTextConfirm = p0.toString()
                        if (!clickHideShow) {
                            lifecycleScope.launch {
                                delay(100)
                                etConfirmFour.isEnabled = true
                                etConfirmFour.requestFocus()
                            }
                        }

                    } else if (p0.toString().length == 2) {
                        etConfirmThree.removeTextChangedListener(this)
                        etConfirmThree.setText(thirdTextConfirm)
                        etConfirmThree.addTextChangedListener(this)
                        val text = p0?.get(p0.lastIndex)
                        etConfirmFour.isEnabled = true
                        etConfirmFour.requestFocus()
                        etConfirmFour.setText(text.toString())
                    }
                }

                override fun afterTextChanged(p0: Editable?) {
                    etConfirmThree.setOnKeyListener { view, i, keyEvent ->
                        if (i == KeyEvent.KEYCODE_DEL) {
                            etConfirmThree.background = ContextCompat.getDrawable(
                                UTSwapApp.instance,
                                R.drawable.bg_border_bottom
                            )
                            lifecycleScope.launch {
                                delay(50)
                                etConfirmTwo.isEnabled = true
                                etConfirmTwo.requestFocus()
                            }
                            clickHideShow = false

                        }
                        false
                    }
                    if (p0.toString().isNotEmpty()) {
                        etConfirmThree.setSelection(1)

                    }


                }
            })
            etConfirmFour.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    if (p0.toString().isNotEmpty()) {
                        etConfirmFour.background = ContextCompat.getDrawable(
                            UTSwapApp.instance,
                            R.drawable.bg_border_bottom_active
                        )
                    }
                    etConfirmFour.setSelection(p0.toString().length)
                }

                override fun afterTextChanged(p0: Editable?) {

                    btnNext.isEnabled = etConfirmFour.text.isNotEmpty() && etFour.text.isNotEmpty()
                    etConfirmFour.setOnKeyListener { view, i, keyEvent ->
                        if (i == KeyEvent.KEYCODE_DEL) {
                            etConfirmFour.background = ContextCompat.getDrawable(
                                UTSwapApp.instance,
                                R.drawable.bg_border_bottom
                            )
                            lifecycleScope.launch {
                                delay(50)
                                etConfirmThree.isEnabled = true
                                etConfirmThree.requestFocus()
                            }
                            if (isErrorConfirm) {
                                etConfirmOne.background = ContextCompat.getDrawable(
                                    UTSwapApp.instance,
                                    R.drawable.bg_border_bottom_active
                                )
                                etConfirmTwo.background = ContextCompat.getDrawable(
                                    UTSwapApp.instance,
                                    R.drawable.bg_border_bottom_active
                                )
                                etConfirmThree.background = ContextCompat.getDrawable(
                                    UTSwapApp.instance,
                                    R.drawable.bg_border_bottom_active
                                )
                                etConfirmOne.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.black))
                                etConfirmTwo.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.black))
                                etConfirmThree.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.black))
                                etConfirmFour.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.black))
                            }
                            isErrorConfirm = false
                            clickHideShow = false
                            btnNext.isEnabled = false
                        }
                        false
                    }
                    if (isErrorConfirm) {
                        showError()
                    }
                    clickHideShow = false
                }
            })


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
                for (child in confirmNumberVerification.children) {
                    child as EditText
                    confirmPassword += child.text
                }

                for (child in numberVerification.children) {
                    child as EditText
                    newFundPassword += child.text
                }
                Log.d("new", newFundPassword + " " + confirmPassword)
                if (newFundPassword == confirmPassword) {
                    KYCPreferences().FUND_PASSWORD = newFundPassword
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
                    isError = true
                    isErrorConfirm = true
                    numberVerification.children.forEach {
                        if (it is EditText) {
                            it.background = ContextCompat.getDrawable(
                                UTSwapApp.instance,
                                R.drawable.bg_border_bottom_red
                            )
                            it.setTextColor(
                                ContextCompat.getColor(
                                    UTSwapApp.instance,
                                    R.color.danger
                                )
                            )
                        }
                    }
                    confirmNumberVerification.children.forEach {
                        if (it is EditText) {
                            it.background = ContextCompat.getDrawable(
                                UTSwapApp.instance,
                                R.drawable.bg_border_bottom_red
                            )
                            it.setTextColor(
                                ContextCompat.getColor(
                                    UTSwapApp.instance,
                                    R.color.danger
                                )
                            )
                        }
                    }
                    Toast.makeText(
                        UTSwapApp.instance,
                        "Fund password and Confirm fund password must match.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                newFundPassword = ""
                confirmPassword = ""
            }
        }
    }

    private fun toolBar() {
        binding.apply {
            includeLayout.apply {
                cdBack.setOnClickListener {
                    hideKeyboard()
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
            findNavController().navigate(R.id.action_FundPasswordKycFragment_to_fundPasswordSuccess)
        }
    }

    private fun showKeyboard(context: Context) {
        (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).toggleSoftInput(
            InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY
        )
        showAfterHideKeyboard = false
    }

    private fun hideKeyboard() {
        val inputManager =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(view?.windowToken, 0)
        showAfterHideKeyboard = true
        binding.apply {
            numberVerification.children.forEach {
                if (it is EditText && it.text.isEmpty()) {
                    it.background = ContextCompat.getDrawable(
                        UTSwapApp.instance,
                        R.drawable.bg_border_bottom
                    )
                    it.clearFocus()
                }
            }
            confirmNumberVerification.children.forEach {
                if (it is EditText && it.text.isEmpty()) {
                    it.background = ContextCompat.getDrawable(
                        UTSwapApp.instance,
                        R.drawable.bg_border_bottom
                    )
                    it.clearFocus()
                }
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


    private fun showPassword() {
        binding.apply {
            etOne.transformationMethod = PasswordTransformationMethod.getInstance()
            etTwo.transformationMethod = PasswordTransformationMethod.getInstance()
            etThree.transformationMethod = PasswordTransformationMethod.getInstance()
            etFour.transformationMethod = PasswordTransformationMethod.getInstance()

            imgShowPassword.setImageResource(R.drawable.ic_baseline_visibility_off_24)
            imgShowPassword.setOnClickListener {
                afterClick = true
                if (clickCountPassword % 2 == 0) {
                    etOne.transformationMethod = PasswordTransformationMethod.getInstance()
                    etTwo.transformationMethod = PasswordTransformationMethod.getInstance()
                    etThree.transformationMethod = PasswordTransformationMethod.getInstance()
                    etFour.transformationMethod = PasswordTransformationMethod.getInstance()

                    imgShowPassword.setImageResource(R.drawable.ic_baseline_visibility_off_24)
                    clickCountPassword++
                } else {
                    etOne.transformationMethod = HideReturnsTransformationMethod.getInstance()
                    etTwo.transformationMethod = HideReturnsTransformationMethod.getInstance()
                    etThree.transformationMethod = HideReturnsTransformationMethod.getInstance()
                    etFour.transformationMethod = HideReturnsTransformationMethod.getInstance()
                    clickCountPassword++
                    imgShowPassword.setImageResource(R.drawable.ic_baseline_remove_red_eye_24)
                }
            }

            etConfirmOne.transformationMethod = PasswordTransformationMethod.getInstance()
            etConfirmTwo.transformationMethod = PasswordTransformationMethod.getInstance()
            etConfirmThree.transformationMethod = PasswordTransformationMethod.getInstance()
            etConfirmFour.transformationMethod = PasswordTransformationMethod.getInstance()

            ivHideShow.setOnClickListener {
                clickHideShow = true
                if (count % 2 == 0) {
                    etConfirmOne.transformationMethod = PasswordTransformationMethod.getInstance()
                    etConfirmTwo.transformationMethod = PasswordTransformationMethod.getInstance()
                    etConfirmThree.transformationMethod = PasswordTransformationMethod.getInstance()
                    etConfirmFour.transformationMethod = PasswordTransformationMethod.getInstance()
                    ivHideShow.setImageResource(R.drawable.ic_baseline_visibility_off_24)
                    count++
                } else {
                    etConfirmOne.transformationMethod =
                        HideReturnsTransformationMethod.getInstance()
                    etConfirmTwo.transformationMethod =
                        HideReturnsTransformationMethod.getInstance()
                    etConfirmThree.transformationMethod =
                        HideReturnsTransformationMethod.getInstance()
                    etConfirmFour.transformationMethod =
                        HideReturnsTransformationMethod.getInstance()
                    ivHideShow.setImageResource(R.drawable.ic_baseline_remove_red_eye_24)
                    count++

                }
            }
        }
    }

    private fun showError() {
        binding.apply {
            numberVerification.children.forEach {
                if (it is EditText) {
                    it.background = ContextCompat.getDrawable(
                        UTSwapApp.instance,
                        R.drawable.bg_border_bottom_red
                    )
                    it.setTextColor(
                        ContextCompat.getColor(
                            UTSwapApp.instance,
                            R.color.danger
                        )
                    )
                }
            }
            confirmNumberVerification.children.forEach {
                if (it is EditText) {
                    it.background = ContextCompat.getDrawable(
                        UTSwapApp.instance,
                        R.drawable.bg_border_bottom_red
                    )
                    it.setTextColor(
                        ContextCompat.getColor(
                            UTSwapApp.instance,
                            R.color.danger
                        )
                    )
                }
            }
        }
    }

}

