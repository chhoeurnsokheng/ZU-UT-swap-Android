package com.zillennium.utswap.module.security.securityFragment.changeFundPassword

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.children
import androidx.core.view.forEach
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.FragmentAccountChangeFundPasswordBinding
import com.zillennium.utswap.models.userService.User
import com.zillennium.utswap.screens.navbar.navbar.MainActivity
import com.zillennium.utswap.utils.ClientClearData
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener


class ChangeFundPasswordFragment :
    BaseMvpFragment<ChangeFundPasswordView.View, ChangeFundPasswordView.Presenter, FragmentAccountChangeFundPasswordBinding>(),
    ChangeFundPasswordView.View {

    override var mPresenter: ChangeFundPasswordView.Presenter = ChangeFundPasswordPresenter()
    override val layoutResource: Int = R.layout.fragment_account_change_fund_password

    private var clickCountPassword = 1
    private var afterClick = false
    private var isError = false
    private var showAfterHideKeyboard = false
    private var newFundPassword = ""

    override fun initView() {
        super.initView()
        onSubmitFundPassword()
        showPassword()
        initToolBar()
        customFunPassword()
        binding.apply {
            btnNext.isClickable = false
            llContainer.setOnClickListener {
                hideKeyboard()
            }
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

            }

        }

    }


    private fun initToolBar() {
        binding.apply {
            includeLayout.apply {
                cdBack.setOnClickListener {
                    hideKeyboard()
                    activity?.finish()
                }
                tbTitle.setText(R.string.change_fund_password)
            }
        }
    }

    private fun onSubmitFundPassword() {
        binding.apply {
            btnNext.setOnClickListener {
                hideKeyboard()
                onProgressBar(true)
                for (child in numberVerification.children) {
                    child as EditText
                    newFundPassword += child.text
                }
                mPresenter.onSubmitOldFundPassword(
                    User.CheckOldFundPasswordObject(
                        newFundPassword
                    ), UTSwapApp.instance
                )
                newFundPassword = ""
            }
        }
    }

    override fun checkOldFundPasswordSuccess(data: User.CheckOldFundPasswordRes) {
        onProgressBar(false)
        binding.apply {
            txtMessage.visibility = View.GONE
            for (child in numberVerification.children) {
                child.background = ContextCompat.getDrawable(
                    UTSwapApp.instance,
                    R.drawable.bg_border_bottom
                )
                child as TextView
                child.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.black))
            }
        }
        findNavController().navigate(R.id.action_to_new_fund_password)
    }

    override fun checkOldFundPasswordFail(data: User.CheckOldFundPasswordRes) {
        onProgressBar(false)
        isError = true
        binding.apply {
            txtMessage.visibility = View.VISIBLE
            txtMessage.text = resources.getString(R.string.old_fund_password_wrong)
            for (child in numberVerification.children) {
                child.background =
                    ContextCompat.getDrawable(UTSwapApp.instance, R.drawable.bg_border_bottom_red)
                child as EditText
                child.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.danger))
            }
        }
    }

    override fun onUserExpiredToken() {
        ClientClearData.clearDataUser()
        startActivity(Intent(requireActivity(), MainActivity::class.java))
        activity?.finish()
        requireActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }

    private fun onProgressBar(status: Boolean) {
        binding.apply {
            if (status) {
                pbNext.visibility = View.VISIBLE
                btnNext.isClickable = false
                btnNext.alpha = 0.6F
            } else {
                pbNext.visibility = View.GONE
                btnNext.isClickable = true
                btnNext.alpha = 1F
            }
        }
    }

    override fun onPause() {
        super.onPause()
        hideKeyboard()
    }

    private fun hideKeyboard() {
        val inputManager =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(view?.windowToken, 0)
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
        }
    }

    private fun customFunPassword() {
        var thirdText = ""
        var secondText = ""
        var firstText = ""

        binding.apply {
            editTextFocus(etOne)
            editTextFocus(etTwo)
            editTextFocus(etThree)
            editTextFocus(etFour)
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
                    txtMessage.visibility = View.GONE
                    if (etFour.text.isNotEmpty()) {
                        btnNext.isClickable = true
                        btnNext.isEnabled = true
                        btnNext.backgroundTintList = ContextCompat.getColorStateList(UTSwapApp.instance, R.color.primary)
                    } else {
                        btnNext.isClickable = false
                        btnNext.backgroundTintList = ContextCompat.getColorStateList(UTSwapApp.instance, R.color.light_gray)

                    }
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
                                etOne.setTextColor(
                                    ContextCompat.getColor(
                                        UTSwapApp.instance,
                                        R.color.black
                                    )
                                )
                                etTwo.setTextColor(
                                    ContextCompat.getColor(
                                        UTSwapApp.instance,
                                        R.color.black
                                    )
                                )
                                etThree.setTextColor(
                                    ContextCompat.getColor(
                                        UTSwapApp.instance,
                                        R.color.black
                                    )
                                )
                                etFour.setTextColor(
                                    ContextCompat.getColor(
                                        UTSwapApp.instance,
                                        R.color.black
                                    )
                                )
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


        }
    }

    private fun showKeyboard(context: Context) {
        (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).toggleSoftInput(
            InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY
        )
        showAfterHideKeyboard = false
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

                } else {
                    /** clear focus when keyboard hide*/
                    activity?.let { it1 ->
                        KeyboardVisibilityEvent.setEventListener(
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

                                    }
                                }
                            })
                    }
                }
            }
        }
    }


}