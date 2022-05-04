package com.zillennium.utswap.screens.kyc.declarationScreen

import android.content.Intent
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivityKycDeclarationBinding
import com.zillennium.utswap.screens.kyc.fundPasswordScreen.FundPasswordActivity
import java.io.IOException

class DeclarationActivity :
    BaseMvpActivity<DeclarationView.View, DeclarationView.Presenter, ActivityKycDeclarationBinding>(),
    DeclarationView.View {

    override var mPresenter: DeclarationView.Presenter = DeclarationPresenter()
    override val layoutResource: Int = R.layout.activity_kyc_declaration

    override fun initView() {
        super.initView()
        try {
            binding.apply {

                imgBack.setOnClickListener { finish() }

                btnAccept.setOnClickListener {
                    val intent = Intent(
                        UTSwapApp.instance,
                        FundPasswordActivity::class.java
                    )
                    startActivity(intent)
                }

                txtContent.text =
                    """
                    1- I acknowledge and confirm that the information provided above is true and correct to the best of my knowledge and belief. In case any of the above specified information is found to be false or untrue or misleading or misrepresenting, I am aware that I may liable for it. 
                    
                    2- I hereby authorize you “Zillion United” to disclose, share, remit in any form, mode or manner, all / any of the information provided by me through UT Swap platform, including all changes, updates to such information as and when provided by me to Trustee, judicial authorities, tax authorities agencies for transaction/operation involve to me or wherever it is legally required and other investigation agencies without any obligation of advising me of the same.  
                    
                    3- I also undertake to keep you informed in writing about any changes/ modification to the above information in future and also undertake to provide any other additional information as may be required by you and/or any other regulations/authority. 
                    
                    4- I acknowledge and understand I shall join Zillion United training on UT Swap platform to understand the platform and its rule to get my account become officially trading account.
                    """.trimIndent()

            }

        } catch (error: IOException) {
            // Must be safe
        }
    }
}