package com.zillennium.utswap.module.account.documentsScreen.termsConditions

import com.zillennium.utswap.R
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivityAccountTermsConditionsBinding

class TermsConditionsActivity :
    BaseMvpActivity<TermsConditionsView.View, TermsConditionsView.Presenter, ActivityAccountTermsConditionsBinding>(),
    TermsConditionsView.View {

    override var mPresenter: TermsConditionsView.Presenter = TermsConditionsPresenter()
    override val layoutResource: Int = R.layout.activity_account_terms_conditions

    override fun initView() {
        super.initView()
        try {
            binding.apply {
              toolBar()

                txtHeader1.text = "1. IMPORTANT & MUST READ"
                txtContentHeader1.text = "You shall and are advised to read our Private Policy (herein called the \"Terms\") carefully before continuing to use Services UT Swap website and app (herein called \"UT Swap\") All Terms shall govern the use of UT Swap and apply to all users and recipients (herein called \"You\") of UT Swap, a platform owned and managed by Zillion United (herein called \"We\", \"Us\" or \"Our\"). By accessing or continuing to use UT Swap, You unconditionally consent to all Terms stated herein. Your use of UT Swap signifies Your agreement with these Terms. IF YOU DO NOT AGREE WITH ANY OF THESE TERMS, DO NOT USE UT SWAP. "
                txtContentHeader12.text = "You represent to us that You are over the age of 18. We do not permit those under 18 to use UT Swap. Your access to and use of UT Swap is also conditioned on Your acceptance of and compliance with the Privacy Policy and other rules."

                txtHeader2.text = "2. TRADING ACCOUNTS AND AGREEMENT"
                txtContentHeader2.text = "When You create an account and trading agreement with Us, You shall provide Us information, including KYC that is accurate, complete, and current at all times. Failure to do so constitutes a breach of the Terms, which may result in immediate termination of Your account on UT Swap. You are required to visit our Office to sign trading agreement (except permitted otherwise) and fulfill the qualified trading training before starting trading on UT Swap. You are responsible for safeguarding the passcode and other required keys to access the service or to allow transactions to occur. You agree not to disclose your passcode and other required keys to any third party. You shall notify Us immediately upon becoming aware of any breach of security or unauthorized use of or access into Your account. You shall sure your real name and identity on UT Swap. You may not use the name of another person or entity, or name is not allowed or prohibited by law."

                txtHeader3.text = "3. OUR RESERVATIONS"
                txtContentHeader3.text = "We reserve the right, in our sole discretion, to modify, alter or otherwise update any of these Terms at any time. The amended Terms shall become effective from the date as specified in the notification. By using or continuing to use UT Swap after our notice of such modifications You agree to be bound by such amended Terms. SHOULD YOU OBJECT TO ANY OF OUR AMENDED TERMS, YOU SHALL IMMEDIATELY CEASE TO USE UT SWAP."

                txtHeader4.text = "4. INTELLECTUAL PROPERTY"
                txtContentHeader4.text = "All contents and objects on UT Swap, including but not limited to, texts, images, audio records, video clips, trademarks, or other applicable objects, are protected under Cambodian intellectual property laws. Such intellectual properties may be owned by us or by Third Party. You may only use these materials for private and non-commercial purposes as permitted under Cambodian laws. You are not allowed to reproduce, republish, post and/or exploit in whole or in part in whatever format any of our content or information without our prior consent. A prerequisite written permission must be obtained from us or from the owner(s) for any use for other purposes than the above exceptions as permitted under Cambodian laws or otherwise You shall be held directly liable for such violations."


            }
            // Code
        } catch (error: Exception) {
            // Must be safe
        }
    }

    private fun toolBar(){
        setSupportActionBar(binding.includeLayout.tb)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.includeLayout.apply {
            tbTitle.setText(R.string.terms_and_conditions)
            tb.setNavigationOnClickListener {
                finish()
            }
        }
    }

}