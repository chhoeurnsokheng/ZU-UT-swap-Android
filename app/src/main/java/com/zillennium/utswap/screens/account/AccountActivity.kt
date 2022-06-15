package com.zillennium.utswap.screens.account

import android.content.Intent
import android.graphics.Paint
import android.text.Html
import android.view.View
import com.zillennium.utswap.Datas.GlobalVariable.SettingVariable
import com.zillennium.utswap.Datas.StoredPreferences.SessionPreferences
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivityAccountBinding
import com.zillennium.utswap.screens.account.accountDetail.AccountDetailActivity
import com.zillennium.utswap.screens.account.customerSupport.CustomerSupportActivity
import com.zillennium.utswap.screens.account.dialog.ChangeProfileBottomSheet
import com.zillennium.utswap.screens.account.documents.DocumentsActivity
import com.zillennium.utswap.screens.account.lockTimeOut.LockTimeOutActivity
import com.zillennium.utswap.screens.account.referralInformation.ReferralInformationActivity
import com.zillennium.utswap.screens.navbar.projectTab.subscriptionScreen.bottomSheet.SubscriptionBottomSheet
import com.zillennium.utswap.screens.security.securityActivity.signInScreen.SignInActivity

class AccountActivity :
    BaseMvpActivity<AccountView.View, AccountView.Presenter, ActivityAccountBinding>(),
    AccountView.View {

    override var mPresenter: AccountView.Presenter = AccountPresenter()
    override val layoutResource: Int = R.layout.activity_account

    var preferencesCondition = true

    override fun initView() {
        super.initView()
        try {
            binding.apply {

                txtSignOut.text = Html.fromHtml("<p><u>Sign Out</u></p>")
                txtNo.text = Html.fromHtml("<p><u>No</u></p>")
                txtYes.text = Html.fromHtml("<p><u>Yes</u></p>")
                txtQuestion.text = Html.fromHtml("<p><u>Are you sure?</u></p>")

                if(SessionPreferences().SESSION_PHONE_NUMBER.toString() != "")
                {
                    txtPhoneNumber.visibility = View.VISIBLE
                    txtPhoneNumber.text = SessionPreferences().SESSION_PHONE_NUMBER.toString()
                }

                imgClose.setOnClickListener{
                    finish()
                }

                linearLayoutAccount.setOnClickListener {
                    val intent = Intent(UTSwapApp.instance, AccountDetailActivity::class.java)
                    startActivity(intent)
                }

                linearLayoutProfile.setOnClickListener {
                    val intent = Intent(UTSwapApp.instance, AccountDetailActivity::class.java)
                    startActivity(intent)
                }

                linearLayoutPreferences.setOnClickListener {
                    if(preferencesCondition)
                    {
                        linearLayoutDetailPreferences.visibility = View.VISIBLE
                        imgArrowDropDown.rotation = 90f
                    }else{
                        linearLayoutDetailPreferences.visibility = View.GONE
                        imgArrowDropDown.rotation = 270f
                    }

                    preferencesCondition = !preferencesCondition
                }

                linearLayoutLockTimeOut.setOnClickListener {
                    val intent = Intent(UTSwapApp.instance,LockTimeOutActivity::class.java)
                    startActivity(intent)
                }

                linearLayoutReferral.setOnClickListener {
                    val intent = Intent(UTSwapApp.instance,ReferralInformationActivity::class.java)
                    startActivity(intent)
                }

                linearLayoutDocuments.setOnClickListener {
                    val intent = Intent(UTSwapApp.instance,DocumentsActivity::class.java)
                    startActivity(intent)
                }

                linearLayoutCustomerSupport.setOnClickListener {
                    val intent = Intent(UTSwapApp.instance,CustomerSupportActivity::class.java)
                    startActivity(intent)
                }

                imgProfile.setOnClickListener {
                    val changeProfileBottomSheet = ChangeProfileBottomSheet()
                    changeProfileBottomSheet.show(supportFragmentManager, "changeProfileBottomSheet")
                }

                txtNo.setOnClickListener {
                    linearLayoutSignOut.visibility = View.GONE
                    txtSignOut.visibility = View.VISIBLE
                }

                txtSignOut.setOnClickListener {
                    linearLayoutSignOut.visibility = View.VISIBLE
                    txtSignOut.visibility = View.GONE
                }

                txtYes.setOnClickListener {
                    this@AccountActivity.finish()
                    val intent = Intent(UTSwapApp.instance,SignInActivity::class.java)
                    startActivity(intent)
                }

            }
            // Code
        } catch (error: Exception) {
            // Must be safe
        }
    }
}