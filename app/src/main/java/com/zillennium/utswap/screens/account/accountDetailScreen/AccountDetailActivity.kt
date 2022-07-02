package com.zillennium.utswap.screens.account.accountDetailScreen

import android.content.Intent
import android.text.Html
import androidx.core.content.ContextCompat
import com.zillennium.utswap.Datas.GlobalVariable.SettingVariable
import com.zillennium.utswap.Datas.StoredPreferences.SessionPreferences
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivityAccountDetailBinding
import com.zillennium.utswap.screens.account.accountDetailScreen.dialog.DialogAccountUTType
import com.zillennium.utswap.screens.account.addNumberScreen.AddNumberActivity
import com.zillennium.utswap.screens.account.logsScreen.LogsActivity
import com.zillennium.utswap.screens.security.securityActivity.changeFundPassword.ChangeFundPasswordActivity
import com.zillennium.utswap.screens.security.securityActivity.changeLoginPassword.ChangeLoginPasswordActivity

class AccountDetailActivity :
    BaseMvpActivity<AccountDetailView.View, AccountDetailView.Presenter, ActivityAccountDetailBinding>(),
    AccountDetailView.View {

    override var mPresenter: AccountDetailView.Presenter = AccountDetailPresenter()
    override val layoutResource: Int = R.layout.activity_account_detail

    override fun initView() {
        super.initView()
        try {
            binding.apply {
                txtAddPhoneNumber.text = Html.fromHtml("<u>Add Phone Number</u>")

                if(SessionPreferences().SESSION_PHONE_NUMBER.toString() != "")
                {
                    txtAddPhoneNumber.text = SessionPreferences().SESSION_PHONE_NUMBER.toString()
                    txtAddPhoneNumber.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.black_000000))
                    txtAddPhoneNumber.isEnabled = false
                }

                SettingVariable.phoneNumber.observe(this@AccountDetailActivity) {
                    if(SettingVariable.phoneNumber.value.toString() != "")
                    {
//                        txtAddPhoneNumber.text = groupingSeparatorPhoneNumber(SettingVariable.phoneNumber.value.toString().toInt())
                        txtAddPhoneNumber.text = SettingVariable.phoneNumber.value.toString()
                    }
                }

                btnChangeLoginPassword.setOnClickListener {
                    val intent = Intent(UTSwapApp.instance,ChangeLoginPasswordActivity::class.java)
                    startActivity(intent)
                }

                btnChangeFundPassword.setOnClickListener {
                    val intent = Intent(UTSwapApp.instance,ChangeFundPasswordActivity::class.java)
                    startActivity(intent)
                }

                imgClose.setOnClickListener {
                    finish()
                }

                txtAddPhoneNumber.setOnClickListener {
                    val intent = Intent(UTSwapApp.instance,AddNumberActivity::class.java)
                    startActivity(intent)
                }

                btnCheckAccountLogs.setOnClickListener {
                    val intent = Intent(UTSwapApp.instance,LogsActivity::class.java)
                    startActivity(intent)
                }

                imgUtType.setOnClickListener {
                    val dialogAccountUTType: DialogAccountUTType = DialogAccountUTType.newInstance()
                    dialogAccountUTType.show(supportFragmentManager, "dialogAccountUTType")
                }

            }
            // Code
        } catch (error: Exception) {
            // Must be safe
        }
    }
}