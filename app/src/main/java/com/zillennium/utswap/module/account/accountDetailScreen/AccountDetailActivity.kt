package com.zillennium.utswap.module.account.accountDetailScreen

import android.content.Intent
import android.text.Html
import android.view.View
import androidx.core.content.ContextCompat
import androidx.databinding.adapters.ViewBindingAdapter
import com.androidstudy.networkmanager.Tovuti
import com.bumptech.glide.Glide
import com.zillennium.utswap.Datas.GlobalVariable.SessionVariable
import com.zillennium.utswap.Datas.GlobalVariable.SettingVariable
import com.zillennium.utswap.Datas.StoredPreferences.SessionPreferences
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivityAccountDetailBinding
import com.zillennium.utswap.models.userService.User
import com.zillennium.utswap.module.account.accountDetailScreen.dialog.DialogAccountUTType
import com.zillennium.utswap.module.account.addNumberScreen.AddNumberActivity
import com.zillennium.utswap.module.account.logsScreen.LogsActivity
import com.zillennium.utswap.module.kyc.kycActivity.KYCActivity
import com.zillennium.utswap.module.security.securityActivity.changeFundPassword.ChangeFundPasswordActivity
import com.zillennium.utswap.module.security.securityActivity.changeLoginPassword.ChangeLoginPasswordActivity

class AccountDetailActivity :
        BaseMvpActivity<AccountDetailView.View, AccountDetailView.Presenter, ActivityAccountDetailBinding>(),
        AccountDetailView.View {

    override var mPresenter: AccountDetailView.Presenter = AccountDetailPresenter()
    override val layoutResource: Int = R.layout.activity_account_detail

    private var strCriteria: String? = ""
    private var strPriority: String? = ""
    private var strTitle: String? = ""

    override fun initView() {
        super.initView()

        toolBar()
        onOtherActivity()
        onCallApi()
    }

    private fun onOtherActivity(){
        binding.apply {

//            if (SessionPreferences().SESSION_PHONE_NUMBER.toString() != "") {
//                txtAddPhoneNumber.text = SessionPreferences().SESSION_PHONE_NUMBER.toString()
//                txtAddPhoneNumber.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.black_000000))
//                txtAddPhoneNumber.isEnabled = false
//            }

            SessionVariable.SESSION_PHONE_NUMBER.observe(this@AccountDetailActivity) {
                onCallApi()
            }

            btnChangeLoginPassword.setOnClickListener {
                val intent = Intent(UTSwapApp.instance, ChangeLoginPasswordActivity::class.java)
                startActivity(intent)
            }

            btnChangeFundPassword.setOnClickListener {
                val intent = Intent(UTSwapApp.instance, ChangeFundPasswordActivity::class.java)
                startActivity(intent)
            }

            txtAddPhoneNumber.setOnClickListener {
                val intent = Intent(UTSwapApp.instance, AddNumberActivity::class.java)
                startActivity(intent)
            }

            btnCheckAccountLogs.setOnClickListener {
                val intent = Intent(UTSwapApp.instance, LogsActivity::class.java)
                startActivity(intent)
            }

            imgUtType.setOnClickListener {
                val dialogAccountUTType: DialogAccountUTType = DialogAccountUTType.newInstance(strTitle,strCriteria, strPriority)
                dialogAccountUTType.show(supportFragmentManager, "dialogAccountUTType")
            }

            txtName.setOnClickListener {
                val intent = Intent(UTSwapApp.instance, KYCActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun onCallApi(){
        Tovuti.from(UTSwapApp.instance).monitor{ _, isConnected, _ ->
            if(isConnected)
            {
                mPresenter.onGetUserInfoDetail(UTSwapApp.instance)
                binding.progressBar.visibility = View.VISIBLE
            }
        }
    }

    override fun onGetUserInfoDetailSuccess(data: User.AppSideBarData) {
        binding.apply {

            progressBar.visibility = View.GONE

            strTitle = data.name_user_lavel.toString()

            if(data.doc_user_lavel?.isNotEmpty() == true){
                strCriteria = data.doc_user_lavel?.get(0)?.criteria.toString()
                strPriority = data.doc_user_lavel?.get(0)?.priority_and_privileges.toString()
            }

            if(!data.phonenumber.isNullOrEmpty())
            {
                val phoneNumStr = data.phonenumber.toString()
                //txtPhoneNumber.text = phoneNumStr.replace("+855", "0")
                val phoneReplace = phoneNumStr.replace("+855", "0")
                if(phoneReplace.length == 9){
                    val result = buildString {
                        for (i in 0 until phoneReplace.length) {
                            if (i % 3 == 0 && i > 0)
                                append(' ')
                            append(phoneReplace[i])
                        }
                    }
                    txtAddPhoneNumber.text = result
                }else{
                    val result = buildString {
                        for (i in 0 until phoneReplace.length) {
                            if (i % 3 == 0 && i<9)
                                append(' ')
                            append(phoneReplace[i])
                        }
                    }
                    txtAddPhoneNumber.text = result
                }

                txtAddPhoneNumber.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.black_000000))
                txtAddPhoneNumber.isEnabled = false
            }else{
                txtAddPhoneNumber.text = Html.fromHtml("<u>Add Phone Number</u>")
                txtAddPhoneNumber.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.dark_yellow))
                txtAddPhoneNumber.isEnabled = true
            }

            if (!data.username.isNullOrEmpty())
            {
                txtName.text = data.truename.toString()
                txtName.isEnabled = false
            }else{
                txtName.text = resources.getString(R.string.verify_your_identity)
                txtName.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.primary))
                txtName.isEnabled = true
            }

            Glide
                .with(imgUtType.context)
                .load(data.image_lavel.toString())
                .placeholder(R.drawable.ic_placeholder)
                .into(imgUtType)

            if (!data.company_name.isNullOrEmpty())
            {
                linearLayoutCompany.visibility = View.VISIBLE
                txtCompany.text = data.company_name.toString()
            }

            if (!data.email.isNullOrEmpty())
            {
                txtEmail.text = data.email.toString()
            }else{
                txtEmail.text = resources.getString(R.string.n_a)
            }

            if (!data.ocupation.isNullOrEmpty())
            {
                linearLayoutOccupation.visibility = View.VISIBLE
                txtOccupation.text = data.ocupation.toString()
            }

            if(data.kyc.toString() == "0")
            {
                btnCertified.visibility = View.INVISIBLE
            }else if(data.kyc.toString() == "2"){
                btnCertified.visibility = View.INVISIBLE
            }else{
                btnCertified.visibility = View.VISIBLE
            }

            txtUserLevel.text = data.name_user_lavel.toString()
        }
    }

    override fun onGetUserInfoDetailFail(data: User.AppSideBarData) {
        binding.apply {
            progressBar.visibility = View.GONE
        }
    }

    private fun toolBar() {
        setSupportActionBar(binding.includeLayout.tb)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_left)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.includeLayout.apply {
            tbTitle.setText(R.string.account)
            tbTitle.setTextColor(ContextCompat.getColor(applicationContext,R.color.primary))
            tb.setNavigationOnClickListener {
                finish()
            }
        }
    }

}