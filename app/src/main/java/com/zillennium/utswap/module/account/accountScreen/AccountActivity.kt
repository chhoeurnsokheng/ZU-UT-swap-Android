package com.zillennium.utswap.module.account.accountScreen

import android.content.Intent
import android.provider.MediaStore
import android.text.Html
import android.view.View
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.zillennium.utswap.BuildConfig
import com.zillennium.utswap.Datas.GlobalVariable.SessionVariable
import com.zillennium.utswap.Datas.StoredPreferences.SessionPreferences
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivityAccountBinding
import com.zillennium.utswap.module.account.accountDetailScreen.AccountDetailActivity
import com.zillennium.utswap.module.account.accountScreen.bottomSheet.ChangeProfileBottomSheet
import com.zillennium.utswap.module.account.customerSupportScreen.CustomerSupportActivity
import com.zillennium.utswap.module.account.documentsScreen.DocumentsActivity
import com.zillennium.utswap.module.account.lockTimeOutScreen.LockTimeOutActivity
import com.zillennium.utswap.module.account.referralInformationScreen.ReferralInformationActivity

class AccountActivity :
    BaseMvpActivity<AccountView.View, AccountView.Presenter, ActivityAccountBinding>(),
    AccountView.View {

    override var mPresenter: AccountView.Presenter = AccountPresenter()
    override val layoutResource: Int = R.layout.activity_account
    private val SECOND_ACTIVITY_REQUEST_CODE = 0
    private val PICK_IMAGE_FROM_GALLERY =1

    var preferencesCondition = true

    override fun initView() {
        super.initView()
//        try {
            binding.apply {

                txtSignOut.text = Html.fromHtml("<u>Sign Out</u>")
                txtNo.text = Html.fromHtml("<u>No</u>")
                txtYes.text = Html.fromHtml("<u>Yes</u>")
                txtQuestion.text = Html.fromHtml("<u>Are you sure?</u>") // "  ${BuildConfig.VERSION_NAME} "+
                txtVersion.text ="Version" + "  ${BuildConfig.VERSION_NAME} "


                if(SessionPreferences().SESSION_USER_PROFILE != "")
                {
                    Glide.with(UTSwapApp.instance).load("https://image.kpopmap.com/2019/02/IU-LILAC.jpg").into(imgProfile)
                }

                if(SessionPreferences().SESSION_PHONE_NUMBER.toString() != "")
                {
                    txtPhoneNumber.visibility = View.VISIBLE
                    txtPhoneNumber.text = SessionPreferences().SESSION_PHONE_NUMBER.toString()
                }

                imgClose.setOnClickListener{
                    finish()
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
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
                    SessionPreferences().SESSION_STATUS = false
                    SessionVariable.SESSION_STATUS.value = false
                    finish()
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                }

            }
            // Code
//        } catch (error: Exception) {
//            // Must be safe
//        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
            if (resultCode == RESULT_OK && data != null) {
                val pathImage = data.data.toString()

                binding.apply {
                    val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, pathImage.toUri())
                    imgProfile.setImageBitmap(bitmap)
                    SessionPreferences().SESSION_USER_PROFILE = pathImage
                }
            }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }
}