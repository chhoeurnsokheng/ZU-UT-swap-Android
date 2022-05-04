package com.zillennium.utswap.screens.setting.profileScreen

import android.widget.ListView
import com.zillennium.utswap.R
import com.zillennium.utswap.bases.BaseListViewHeightActivity
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivitySettingProfileBinding
import com.zillennium.utswap.models.ListProfile.ListProfile
import com.zillennium.utswap.models.ListProfile.ListProfileAdapter
import java.io.IOException

class ProfileActivity :
    BaseMvpActivity<ProfileView.View, ProfileView.Presenter, ActivitySettingProfileBinding>(),
    ProfileView.View {

    override var mPresenter: ProfileView.Presenter = ProfilePresenter()
    override val layoutResource: Int = R.layout.activity_setting_profile

    override fun initView() {
        super.initView()
        try {
            binding.apply {

                // Set Personal ListView
                val titlePersonal = arrayOf(
                    "Full Name",
                    "Email",
                    "Phone Number"
                )
                val descPersonal = arrayOf(
                    "Bunna Panhchakrith",
                    "b.panhchakrith@gmail.com",
                    "0966897978"
                )

                val personalArrayList: ArrayList<ListProfile> = ArrayList<ListProfile>()

                for (i in titlePersonal.indices) {
                    val personal = ListProfile(titlePersonal[i], descPersonal[i])
                    personalArrayList.add(personal)
                }

                val profileAdapter = ListProfileAdapter(this@ProfileActivity, personalArrayList)

                val lvPersonal: ListView = binding.lvPersonal
                lvPersonal.adapter = profileAdapter
                BaseListViewHeightActivity.setListViewHeightBasedOnItems(lvPersonal)

                // Set Career ListView

                // Set Career ListView
                val titleCareer = arrayOf(
                    "Company",
                    "Occupation"
                )
                val descCareer = arrayOf(
                    "Z Valley",
                    "Web Developer"
                )

                val careerArrayList = ArrayList<ListProfile>()

                for (i in titleCareer.indices) {
                    val career = ListProfile(titleCareer[i], descCareer[i])
                    careerArrayList.add(career)
                }

                val careerAdapter = ListProfileAdapter(this@ProfileActivity, careerArrayList)

                val lvCareer: ListView = binding.lvCareer
                lvCareer.adapter = careerAdapter
                BaseListViewHeightActivity.setListViewHeightBasedOnItems(lvCareer)

                // Set Bank ListView

                // Set Bank ListView
                val titleBank = arrayOf(
                    "Account Name",
                    "Account Number",
                    "Bank Name",
                    "Bank City",
                    "Bank Address"
                )
                val descBank = arrayOf(
                    "Bunna Panhchakrith",
                    "123456789",
                    "ABA Bank",
                    "Phnom Penh",
                    "Phnom Penh, Cambodia, 12000"
                )

                val bankArrayList = ArrayList<ListProfile>()

                for (i in titleBank.indices) {
                    val bank = ListProfile(titleBank[i], descBank[i])
                    bankArrayList.add(bank)
                }

                val bankAdapter = ListProfileAdapter(this@ProfileActivity, bankArrayList)

                val lvBank: ListView = binding.lvBank
                lvBank.adapter = bankAdapter
                BaseListViewHeightActivity.setListViewHeightBasedOnItems(lvBank)

                // Set Passed Back

                // Set Passed Back
                ivBack.setOnClickListener {
                    onBackPressed()
                }

            }
            // Code
        } catch (error: IOException) {
            // Must be safe
        }
    }
}