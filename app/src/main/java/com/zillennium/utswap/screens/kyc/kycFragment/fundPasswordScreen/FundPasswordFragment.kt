package com.zillennium.utswap.screens.kyc.kycFragment.fundPasswordScreen

import android.annotation.SuppressLint
import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.navigation.fragment.findNavController
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.FragmentKycFundPasswordBinding
import com.zillennium.utswap.screens.kyc.kycFragment.contractScreen.ContractFragment

class FundPasswordFragment :
    BaseMvpFragment<FundPasswordView.View, FundPasswordView.Presenter, FragmentKycFundPasswordBinding>(),
    FundPasswordView.View {

    override var mPresenter: FundPasswordView.Presenter = FundPasswordPresenter()
    override val layoutResource: Int = R.layout.fragment_kyc_fund_password

    private var clickCount = 2
    private var click = 2

    override fun initView() {
        super.initView()
        try {
            binding.apply {

                imgBack.setOnClickListener {
                    findNavController().popBackStack()
                }

                editBox1.requestFocus()
//                showSoftKeyboard(editBox1 as View)

                editBox1.addTextChangedListener(object : TextWatcher {
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
                        if (charSequence.toString().trim { it <= ' ' }.isNotEmpty()) {
                            editBox2.requestFocus()
                        } else {
                            editBox1.clearFocus()
                        }
                    }

                    override fun afterTextChanged(editable: Editable) {}
                })
                editBox2.addTextChangedListener(object : TextWatcher {
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
                        if (charSequence.toString().trim { it <= ' ' }.isNotEmpty()) {
                            editBox3.requestFocus()
                        } else editBox1.requestFocus()
                    }

                    override fun afterTextChanged(editable: Editable) {}
                })
                editBox3.addTextChangedListener(object : TextWatcher {
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
                        if (charSequence.toString().trim { it <= ' ' }.isNotEmpty()) {
                            editBox4.requestFocus()
                        } else editBox2.requestFocus()
                    }

                    override fun afterTextChanged(editable: Editable) {}
                })
                editBox4.addTextChangedListener(object : TextWatcher {
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
                        if (charSequence.toString().trim { it <= ' ' }.isNotEmpty()) {
                            editBoxConfirm1.requestFocus()
                        } else editBoxConfirm3.requestFocus()
                    }

                    override fun afterTextChanged(editable: Editable) {}
                })

                editBoxConfirm1.addTextChangedListener(object : TextWatcher {
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
                        if (charSequence.toString().trim { it <= ' ' }.isNotEmpty()) {
                            editBoxConfirm2.requestFocus()
                        } else editBoxConfirm1.clearFocus()
                    }

                    override fun afterTextChanged(editable: Editable) {}
                })
                editBoxConfirm2.addTextChangedListener(object : TextWatcher {
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
                        if (charSequence.toString().trim { it <= ' ' }.isNotEmpty()) {
                            editBoxConfirm3.requestFocus()
                        } else editBoxConfirm1.requestFocus()
                    }

                    override fun afterTextChanged(editable: Editable) {}
                })
                editBoxConfirm3.addTextChangedListener(object : TextWatcher {
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
                        if (charSequence.toString().trim { it <= ' ' }.isNotEmpty()) {
                            editBoxConfirm4.requestFocus()
                        } else editBoxConfirm2.requestFocus()
                    }

                    override fun afterTextChanged(editable: Editable) {}
                })
                editBoxConfirm4.addTextChangedListener(object : TextWatcher {
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
                        if (charSequence.toString().trim { it <= ' ' }.isNotEmpty()) {
                            editBoxConfirm4.clearFocus()
                        } else editBoxConfirm3.requestFocus()
                    }

                    override fun afterTextChanged(editable: Editable) {}
                })

                imgShowPassword.setOnClickListener {
                    clickCount++
                    showPassword(clickCount)
                }

                imgShowConfirmPassword.setOnClickListener {
                    click++
                    showCfPassword(click)

                }

                btnNext.setOnClickListener {
                    findNavController().navigate(R.id.action_to_contract_kyc_fragment)
//                    val intent = Intent(UTSwapApp.instance, ContractFragment::class.java)
//                    startActivity(intent)
                }

            }

        } catch (error: Exception) {
            // Must be safe
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun showPassword(clickCount: Int) {
        binding.apply {
            if (clickCount % 2 == 0) {
                editBox1.transformationMethod = PasswordTransformationMethod.getInstance()
                editBox2.transformationMethod = PasswordTransformationMethod.getInstance()
                editBox3.transformationMethod = PasswordTransformationMethod.getInstance()
                editBox4.transformationMethod = PasswordTransformationMethod.getInstance()
                imgShowPassword.setImageDrawable(getDrawable(UTSwapApp.instance,R.drawable.ic_baseline_remove_red_eye_24))
            } else {
                editBox1.transformationMethod = HideReturnsTransformationMethod.getInstance()
                editBox2.transformationMethod = HideReturnsTransformationMethod.getInstance()
                editBox3.transformationMethod = HideReturnsTransformationMethod.getInstance()
                editBox4.transformationMethod = HideReturnsTransformationMethod.getInstance()
                imgShowPassword.setImageDrawable(getDrawable(UTSwapApp.instance, R.drawable.ic_baseline_visibility_off_24))
            }
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun showCfPassword(click: Int) {
        binding.apply {
            if (click % 2 == 0) {
                editBoxConfirm1.transformationMethod = PasswordTransformationMethod.getInstance()
                editBoxConfirm2.transformationMethod = PasswordTransformationMethod.getInstance()
                editBoxConfirm3.transformationMethod = PasswordTransformationMethod.getInstance()
                editBoxConfirm4.transformationMethod = PasswordTransformationMethod.getInstance()
                imgShowConfirmPassword.setImageDrawable(getDrawable(UTSwapApp.instance, R.drawable.ic_baseline_remove_red_eye_24))
            } else {
                editBoxConfirm1.transformationMethod = HideReturnsTransformationMethod.getInstance()
                editBoxConfirm2.transformationMethod = HideReturnsTransformationMethod.getInstance()
                editBoxConfirm3.transformationMethod = HideReturnsTransformationMethod.getInstance()
                editBoxConfirm4.transformationMethod = HideReturnsTransformationMethod.getInstance()
                imgShowConfirmPassword.setImageDrawable(getDrawable(UTSwapApp.instance, R.drawable.ic_baseline_visibility_off_24))
            }
        }

    }

//    private fun showSoftKeyboard(editText: View) {
//        if (editText.requestFocus()) {
//            val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
//            imm.showSoftInput(editText.windowToken as View, InputMethodManager.SHOW_IMPLICIT)
//        }
//    }

//    fun hideSoftKeyboard(view: View) {
//        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
//        imm.hideSoftInputFromWindow(view.windowToken, 0)
//    }
}