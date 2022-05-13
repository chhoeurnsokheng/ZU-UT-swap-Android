package com.zillennium.utswap.screens.kyc.kycFragment.declarationScreen

import android.content.Intent
import androidx.navigation.fragment.findNavController
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.FragmentKycDeclarationBinding
import com.zillennium.utswap.screens.kyc.kycFragment.fundPasswordScreen.FundPasswordFragment

class DeclarationFragment :
    BaseMvpFragment<DeclarationView.View, DeclarationView.Presenter, FragmentKycDeclarationBinding>(),
    DeclarationView.View {

    override var mPresenter: DeclarationView.Presenter = DeclarationPresenter()
    override val layoutResource: Int = R.layout.fragment_kyc_declaration

    override fun initView() {
        super.initView()
        try {
            binding.apply {

                imgBack.setOnClickListener {
                    findNavController().popBackStack()
                }

                btnAccept.setOnClickListener {

                    findNavController().navigate(R.id.action_to_fund_password_kyc_fragment)
//                    val intent = Intent(
//                        UTSwapApp.instance,
//                        FundPasswordFragment::class.java
//                    )
//                    startActivity(intent)
                }

                txtContent.text =
                    """
                    1- I acknowledge and confirm that the information provided above is true and correct to the best of my knowledge and belief. In case any of the above specified information is found to be false or untrue or misleading or misrepresenting, I am aware that I may liable for it. 
                    
                    2- I hereby authorize you “Zillion United” to disclose, share, remit in any form, mode or manner, all / any of the information provided by me through UT Swap platform, including all changes, updates to such information as and when provided by me to Trustee, judicial authorities, tax authorities agencies for transaction/operation involve to me or wherever it is legally required and other investigation agencies without any obligation of advising me of the same.  
                    
                    3- I also undertake to keep you informed in writing about any changes/ modification to the above information in future and also undertake to provide any other additional information as may be required by you and/or any other regulations/authority. 
                    
                    4- I acknowledge and understand I shall join Zillion United training on UT Swap platform to understand the platform and its rule to get my account become officially trading account.
                    """.trimIndent()

            }

        } catch (error: Exception) {
            // Must be safe
        }
    }
}