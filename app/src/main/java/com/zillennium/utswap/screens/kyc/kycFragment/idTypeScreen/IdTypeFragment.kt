package com.zillennium.utswap.screens.kyc.kycFragment.idTypeScreen

import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.zillennium.utswap.R
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.FragmentKycIdTypeBinding
import com.zillennium.utswap.screens.kyc.kycFragment.idTypeScreen.fragment.nationalID.NationalIDFragment
import com.zillennium.utswap.screens.kyc.kycFragment.idTypeScreen.fragment.passport.PassportFragment

class IdTypeFragment :
    BaseMvpFragment<IdTypeView.View, IdTypeView.Presenter, FragmentKycIdTypeBinding>(),
    IdTypeView.View {

    override var mPresenter: IdTypeView.Presenter = IdTypePresenter()
    override val layoutResource: Int = R.layout.fragment_kyc_id_type

    public val NUM_PAGES = 2
    private var pageAdapter: FragmentStateAdapter? = null

    override fun initView() {
        super.initView()
        try {
            binding.apply {

                binding.apply {
                    pageAdapter = ScreenSlidePageAdapter(this@IdTypeFragment, NUM_PAGES)
                    vpVerify.adapter = pageAdapter
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
                }


                nationalId.setOnClickListener { view ->
                    onChangeTabs(view)
                    vpVerify.setCurrentItem(0, false)
                }
                passport.setOnClickListener { view ->
                    onChangeTabs(view)
                    vpVerify.setCurrentItem(1, false)
                }

                btnNext.setOnClickListener {
                    findNavController().navigate(R.id.action_to_id_verification_kyc_fragment)
                }


                // Set Passed Back
                ivBack.setOnClickListener {
                    activity?.finish()
                }
            }
        } catch (error: Exception) {
            // Must be safe
        }
    }

    private fun onChangeTabs(view: View) {
        binding.apply {
            if (view.id == R.id.national_id) {
                tabSelect.animate()?.x(0f)?.duration = 100
                nationalId.setTextColor(resources.getColor(R.color.white))
                passport.setTextColor(resources.getColor(R.color.color_main))
            } else if (view.id == R.id.passport) {
                nationalId.setTextColor(resources.getColor(R.color.color_main))
                passport.setTextColor(resources.getColor(R.color.white))
                val size: Int = passport.width ?: 0
                tabSelect.animate().x(size.toFloat()).duration = 100
            }
        }

    }

    private class ScreenSlidePageAdapter(idTypeActivity: IdTypeFragment?, NUM_PAGES: Int) :
        FragmentStateAdapter(idTypeActivity!!) {

        private val NUM_PAGES = NUM_PAGES

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

    override fun onResume() {
        super.onResume()

        binding.apply {
            if(vpVerify.currentItem == 1){
                vpVerify.setCurrentItem(0, false)
            }
        }

    }
}