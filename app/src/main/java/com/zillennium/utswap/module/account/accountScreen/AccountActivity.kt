package com.zillennium.utswap.module.account.accountScreen

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import android.text.Html
import android.util.Base64
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import com.androidstudy.networkmanager.Tovuti
import com.bumptech.glide.Glide
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.io.output.ByteArrayOutputStream
import com.zillennium.utswap.BuildConfig
import com.zillennium.utswap.Datas.GlobalVariable.SessionVariable
import com.zillennium.utswap.Datas.GlobalVariable.SettingVariable
import com.zillennium.utswap.Datas.StoredPreferences.SessionPreferences
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivityAccountBinding
import com.zillennium.utswap.models.userService.User
import com.zillennium.utswap.module.account.accountDetailScreen.AccountDetailActivity
import com.zillennium.utswap.module.account.accountScreen.bottomSheet.ChangeProfileBottomSheet
import com.zillennium.utswap.module.account.addNumberScreen.AddNumberActivity
import com.zillennium.utswap.module.account.customerSupportScreen.CustomerSupportActivity
import com.zillennium.utswap.module.account.documentsScreen.DocumentsActivity
import com.zillennium.utswap.module.account.lockTimeOutScreen.LockTimeOutActivity
import com.zillennium.utswap.module.account.referralInformationScreen.ReferralInformationActivity
import com.zillennium.utswap.module.kyc.kycFragment.fundPasswordScreen.FundPasswordFragment
import com.zillennium.utswap.utils.DialogUtil
import java.io.File

class AccountActivity :
    BaseMvpActivity<AccountView.View, AccountView.Presenter, ActivityAccountBinding>(),
    AccountView.View {

    override var mPresenter: AccountView.Presenter = AccountPresenter()
    override val layoutResource: Int = R.layout.activity_account
    private val SECOND_ACTIVITY_REQUEST_CODE = 0
    private val PICK_IMAGE_FROM_GALLERY = 1
    private var newImageFile: File? = null
    var preferencesCondition = true

    override fun initView() {
        super.initView()
        onOtherActivity()
        onCallApi()
    }

    private fun onOtherActivity(){
        binding.apply {
            txtSignOut.text = Html.fromHtml("<u>Sign Out</u>")

            txtVersion.text = "Version" + " APT" + "  ${BuildConfig.VERSION_NAME} "

//            if (SessionPreferences().SESSION_USER_PROFILE != "") {
//                // Glide.with(UTSwapApp.instance).load("https://image.kpopmap.com/2019/02/IU-LILAC.jpg").into(profileImageView)
//            }
//
//            if (SessionPreferences().SESSION_PHONE_NUMBER.toString() != "") {
//                //  txtPhoneNumber.visibility = View.VISIBLE
//                txtPhoneNumber.text = SessionPreferences().SESSION_PHONE_NUMBER.toString()
//            }

            SessionVariable.SESSION_PHONE_NUMBER.observe(this@AccountActivity) {
               onCallApi()
            }

            imgClose.setOnClickListener {
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
                if (preferencesCondition) {
                    linearLayoutDetailPreferences.visibility = View.VISIBLE
                    imgArrowDropDown.rotation = 90f
                } else {
                    linearLayoutDetailPreferences.visibility = View.GONE
                    imgArrowDropDown.rotation = 270f
                }

                preferencesCondition = !preferencesCondition
            }

            linearLayoutLockTimeOut.setOnClickListener {
                val intent = Intent(UTSwapApp.instance, LockTimeOutActivity::class.java)
                startActivity(intent)
            }

            linearLayoutReferral.setOnClickListener {
                val intent = Intent(UTSwapApp.instance, ReferralInformationActivity::class.java)
                startActivity(intent)
            }

            linearLayoutDocuments.setOnClickListener {
                val intent = Intent(UTSwapApp.instance, DocumentsActivity::class.java)
                startActivity(intent)
            }

            linearLayoutCustomerSupport.setOnClickListener {
                val intent = Intent(UTSwapApp.instance, CustomerSupportActivity::class.java)
                startActivity(intent)
            }

            txtPhoneNumber.setOnClickListener {
                val intent = Intent(UTSwapApp.instance, AddNumberActivity::class.java)
                startActivity(intent)
            }

            profileImageView.setOnClickListener {

                ImagePicker.with(this@AccountActivity)
                    //  .crop()
                    // .compress(1024)
                    //  .maxResultSize(1080, 1080)
                    .start { resultCode, data ->4
                        when (resultCode) {
                            Activity.RESULT_OK -> {
                                val fileUri = data?.data
                                profileImageView.setImageURI(fileUri)

                                // You can get File object from intent
                                newImageFile = ImagePicker.getFile(data)

                                var profileImage =
                                    "data:image/jpeg;base64," + getFileToByte(newImageFile.toString())
                                mPresenter.uploadProfile(User.AccountUploadProfileObject(profileImage),UTSwapApp.instance)
                            }
                            ImagePicker.RESULT_ERROR -> {
                                Toast.makeText(
                                    this@AccountActivity,
                                    ImagePicker.getError(data),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
//                    val changeProfileBottomSheet = ChangeProfileBottomSheet()
//                    changeProfileBottomSheet.show(supportFragmentManager, "changeProfileBottomSheet")
            }

            txtSignOut.setOnClickListener {
                DialogUtil().customDialog(
                    R.drawable.icon_log_out,
                    "Are you sure you want to sign out?",
                    "",
                    "Cancel",
                    "Sign Out",
                    object : DialogUtil.OnAlertDialogClick {
                        override fun onLabelCancelClick() {
                            SessionVariable.SESSION_STATUS.value = false
                            SessionVariable.SESSION_KYC.value = false
                            SessionVariable.SESSION_KYC_STATUS.value = 0

                            SessionPreferences().removeValue("SESSION_TOKEN")
                            SessionPreferences().removeValue("SESSION_ID")
                            SessionPreferences().removeValue("SESSION_USERNAME")
                            SessionPreferences().removeValue("SESSION_KYC")
                            SessionPreferences().removeValue("SESSION_X_TOKEN_API")
                            SessionPreferences().removeValue("SESSION_STATUS")
                            SessionPreferences().removeValue("SESSION_KYC_SUBMIT_STATUS")
                            SessionPreferences().removeValue("SESSION_KYC_STATUS")

                            finish()
                            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                        }
                    },
                    this@AccountActivity

                )

            }
        }
    }

    private fun onCallApi(){
        Tovuti.from(UTSwapApp.instance).monitor{ _, isConnected, _ ->
            if(isConnected)
            {
                mPresenter.onGetUserInfo(UTSwapApp.instance)
            }
        }
    }

    override fun onGetUserInfoSuccess(data: User.AppSideBarData) {
        binding.apply {
            txtPhoneNumber.visibility = View.VISIBLE

            if(!data.phonenumber.isNullOrEmpty())
            {
                val phoneNumStr = data.phonenumber.toString()
                txtPhoneNumber.text = phoneNumStr.replace("+855", "0")
                txtPhoneNumber.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.white))
                txtPhoneNumber.isEnabled = false
            }else{
                txtPhoneNumber.text = Html.fromHtml("<u>Add Phone Number</u>")
                txtPhoneNumber.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.dark_yellow))
                txtPhoneNumber.isEnabled = true
            }

            if (!data.username.isNullOrEmpty())
            {
                txtName.text = data.truename.toString()
            }

            Glide
                .with(imgLevel.context)
                .load(data.image_lavel.toString())
                .placeholder(R.drawable.ic_placeholder)
                .into(imgLevel)

            Glide
                .with(profileImageView.context)
                .load(data.image_profile.toString())
                .placeholder(R.drawable.ic_placeholder)
                .into(profileImageView)
        }
    }

    override fun uploadProfileSuccess(data: User.AccountUploadProfileRes) {
        mPresenter.onGetUserInfo(UTSwapApp.instance)
        Toast.makeText(UTSwapApp.instance,"Upload Success", Toast.LENGTH_SHORT).show()
    }

    override fun uploadProfileFail(data: User.AccountUploadProfileRes) {
        Toast.makeText(UTSwapApp.instance,"Upload Fail", Toast.LENGTH_SHORT).show()
    }

    override fun onGetUserInfoFail(data: User.AppSideBarData) {

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && data != null) {
            val pathImage = data.data.toString()

            binding.apply {
                val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, pathImage.toUri())
                //   profileImageView.setImageBitmap(bitmap)
                //  SessionPreferences().SESSION_USER_PROFILE = pathImage
            }
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

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }
}