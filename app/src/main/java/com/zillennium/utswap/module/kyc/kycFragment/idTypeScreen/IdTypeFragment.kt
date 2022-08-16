package com.zillennium.utswap.module.kyc.kycFragment.idTypeScreen

import android.app.AlertDialog
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.zillennium.utswap.Datas.StoredPreferences.KYCPreferences
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.FragmentKycIdTypeBinding
import com.zillennium.utswap.module.kyc.kycActivity.KYCActivity
import com.zillennium.utswap.module.kyc.kycFragment.employmentInfoScreen.EmploymentInfoFragment
import com.zillennium.utswap.module.kyc.kycFragment.idTypeScreen.camera.idCardCameraFragment.IDCardCameraFragment
import com.zillennium.utswap.module.kyc.kycFragment.idTypeScreen.fragment.nationalID.NationalIDFragment
import com.zillennium.utswap.module.kyc.kycFragment.idTypeScreen.fragment.passport.PassportFragment
import com.zillennium.utswap.module.kyc.kycFragment.idVerificationScreen.IDVerificationFragment
import com.zillennium.utswap.module.security.securityActivity.registerScreen.RegisterActivity


open class IdTypeFragment :
    BaseMvpFragment<IdTypeView.View, IdTypeView.Presenter, FragmentKycIdTypeBinding>(),
    IdTypeView.View {

    override var mPresenter: IdTypeView.Presenter = IdTypePresenter()
    override val layoutResource: Int = R.layout.fragment_kyc_id_type

    private val NUM_PAGES = 2
    private var pageAdapter: FragmentStateAdapter? = null

    override fun initView() {
        super.initView()
        try {
            if (activity?.intent?.hasExtra("KYCStatus") == true) {
                (activity as KYCActivity).kycStatus = activity?.intent?.getSerializableExtra("KYCStatus").toString()
                    if ((activity as KYCActivity).kycStatus== "Pending") {
                    findNavController().navigate(R.id.action_IDTypeKycFragment_to_KycApplicationKycFragment)
                }
            }
            activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true){
                override fun handleOnBackPressed() {
                    IDVerificationFragment.apply {
                        provice = ""
                        district = ""
                        commune = ""
                        name = ""
                        sureName = ""
                        gender = ""
                        date = ""
                        houseNumber = ""
                        proCode = ""
                        disCode = ""
                        comCode = ""
                    }
                    IDCardCameraFragment.apply {
                        imageFront = ""
                        imageBack = ""
                    }
                    EmploymentInfoFragment.apply {
                        occupation = ""
                        company = ""
                    }
                    activity?.finish()
                }
            })

            toolbar()
//            KYCPreferences().NATIONAL_ID_BACK = ""
//            KYCPreferences().NATIONAL_ID_FRONT = ""
//            KYCPreferences().PASSPORT_FRONT = ""

            binding.apply {
                checkValidation()
                pageAdapter = ScreenSlidePageAdapter(this@IdTypeFragment, NUM_PAGES)
                vpVerify.adapter = pageAdapter
                vpVerify.isSaveEnabled = false
                vpVerify.isUserInputEnabled = false
                vpVerify.registerOnPageChangeCallback(object : OnPageChangeCallback() {
                    override fun onPageScrolled(
                        position: Int,
                        positionOffset: Float,
                        positionOffsetPixels: Int
                    ) {
                        super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                    }

                    override fun onPageSelected(position: Int) {
                        super.onPageSelected(position)
                    }

                    override fun onPageScrollStateChanged(state: Int) {
                        super.onPageScrollStateChanged(state)
                    }
                })

                nationalId.setOnClickListener { view ->

                    if(!KYCPreferences().PASSPORT_FRONT.isNullOrEmpty()){
                        //alert dialog
                        val builder = AlertDialog.Builder(requireActivity())
                        builder.setTitle("Are you sure want to change the tab?")
                        builder.setMessage("Your photo that you just add will delete after change to new tab")
                        builder.setIcon(android.R.drawable.ic_dialog_alert)

                        builder.setPositiveButton("Yes"){dialogInterface, which ->
                            onChangeTabs(view)
                            vpVerify.setCurrentItem(0, false)
                            checkValidation()
                            KYCPreferences().removeValue("PASSPORT_FRONT")
                        }

                        builder.setNegativeButton("No"){dialogInterface, which ->

                        }

                        val alertDialog: AlertDialog = builder.create()

                        alertDialog.show()
                    }else{
                        onChangeTabs(view)
                        vpVerify.setCurrentItem(0, false)
                        checkValidation()
                    }
                }
                passport.setOnClickListener { view ->

                    //alert dialog
                        val builder = AlertDialog.Builder(requireActivity())
                        builder.setTitle("Passport is coming soon")
                        builder.setMessage("We don't implement add passport yet !!")


//                        builder.setPositiveButton("Yes"){dialogInterface, which ->
//                            onChangeTabs(view)
//                            vpVerify.setCurrentItem(1, true)
//                            checkValidation()
//                          //  KYCPreferences().removeValue("NATIONAL_ID_FRONT")
//                          //  KYCPreferences().removeValue("NATIONAL_ID_BACK")
//                        }

                        builder.setNegativeButton("Yes"){dialogInterface, which ->

                        }

                        val alertDialog: AlertDialog = builder.create()

                        alertDialog.show()
//
//                    if(!KYCPreferences().NATIONAL_ID_FRONT.isNullOrEmpty() || !KYCPreferences().NATIONAL_ID_BACK.isNullOrEmpty()){
//                        //alert dialog
//                        val builder = AlertDialog.Builder(requireActivity())
//                        builder.setTitle("Are you sure you want to switch tabs?")
//                        builder.setMessage("Your added photos will be deleted.")
//
//
//                        builder.setPositiveButton("Yes"){dialogInterface, which ->
//                            onChangeTabs(view)
//                            vpVerify.setCurrentItem(1, false)
//                            checkValidation()
//                            KYCPreferences().removeValue("NATIONAL_ID_FRONT")
//                            KYCPreferences().removeValue("NATIONAL_ID_BACK")
//                        }
//
//                        builder.setNegativeButton("No"){dialogInterface, which ->
//
//                        }
//
//                        val alertDialog: AlertDialog = builder.create()
//
//                        alertDialog.show()
//                    }else{
//                        onChangeTabs(view)
//                        vpVerify.setCurrentItem(1, false)
//                        checkValidation()
//                    }

                }

            }

        } catch (error: Exception) {
            // Must be safe
        }
    }

  private fun toolbar(){
      binding.apply {
          activity.let {
              includeLayout.apply {
                 cdBack.setOnClickListener {
                     IDVerificationFragment.apply {
                         provice = ""
                         district = ""
                         commune = ""
                         name = ""
                         sureName = ""
                         gender = ""
                         date = ""
                         houseNumber = ""
                         proCode = ""
                         disCode = ""
                         comCode = ""
                     }
                     IDCardCameraFragment.apply {
                         imageFront = ""
                         imageBack = ""
                     }
                     EmploymentInfoFragment.apply {
                         occupation = ""
                         company = ""
                     }
                     activity?.finish()
                 }
                  tbTitle.text ="1/4"
              }

          }

      }
  }


    private fun onChangeTabs(view: View) {
        binding.apply {
            if (view.id == R.id.national_id) {
                tabSelect.animate()?.x(0f)?.duration = 100
                nationalId.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.white))
                passport.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.primary))
            } else if (view.id == R.id.passport) {
                nationalId.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.primary))
                passport.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.white))
                val size: Int = passport.width ?: 0
                tabSelect.animate().x(size.toFloat()).duration = 100
            }
        }

    }

    private class ScreenSlidePageAdapter(idTypeActivity: IdTypeFragment?,
                                         private val NUM_PAGES: Int
    ) :
        FragmentStateAdapter(idTypeActivity!!) {

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> NationalIDFragment()
                else -> PassportFragment()
            }
        }

        override fun getItemCount(): Int {
            return NUM_PAGES
        }
    }

    fun checkValidation(){
        binding.apply {
            if(vpVerify.currentItem == 0){
                if(IDCardCameraFragment.imageFront.isNotEmpty() && IDCardCameraFragment.imageBack.isNotEmpty()){
                    btnNext.visibility = View.VISIBLE
                    btnNext.isClickable = true
                    btnNext.setOnClickListener {
                        findNavController().navigate(R.id.action_to_id_verification_kyc_fragment)
                    }
                }else if (IDCardCameraFragment.imageFront.isEmpty() || IDCardCameraFragment.imageBack.isEmpty()){
                    btnNext.visibility = View.GONE
                    btnNext.isClickable = false
                    btnNext.setOnClickListener {}
                }
            }else if (vpVerify.currentItem == 1){
                if(!KYCPreferences().PASSPORT_FRONT.isNullOrEmpty()){
                    btnNext.visibility = View.VISIBLE
                    btnNext.isClickable = true
                    btnNext.setOnClickListener {
                        findNavController().navigate(R.id.action_to_id_verification_kyc_fragment)
                    }
                }else{
                    btnNext.visibility = View.GONE
                    btnNext.isClickable = false
                    btnNext.setOnClickListener {}
                }
            }
        }

    }

    override fun onResume() {
        super.onResume()
        checkValidation()

    }
}

