package com.zillennium.utswap.screens.kyc.termConditionScreen

import android.content.Intent
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivityKycTermConditionBinding
import com.zillennium.utswap.screens.kyc.idTypeScreen.IdTypeActivity
import java.io.IOException

class TermConditionActivity :
    BaseMvpActivity<TermConditionView.View, TermConditionView.Presenter, ActivityKycTermConditionBinding>(),
    TermConditionView.View {

    override var mPresenter: TermConditionView.Presenter = TermConditionPresenter()
    override val layoutResource: Int = R.layout.activity_kyc_term_condition

    override fun initView() {
        super.initView()
        try {
            binding.apply {

                imgBack.setOnClickListener { finish() }

                btnNext.setOnClickListener {
                    val intent = Intent(UTSwapApp.instance, IdTypeActivity::class.java)
                    startActivity(intent)
                }

                txtContent.text = """1. IMPORTANT & MUST READ
You shall and are advised to read our Private Policy (herein called the "Terms") carefully before continuing to use Services UT Swap website and app (herein called "UT Swap") All Terms shall govern the use of UT Swap and apply to all users and recipients (herein called "You") of UT Swap, a platform owned and managed by Zillion United (herein called "We", "Us" or "Our"). By accessing or continuing to use UT Swap, You unconditionally consent to all Terms stated herein. Your use of UT Swap signifies Your agreement with these Terms. IF YOU DO NOT AGREE WITH ANY OF THESE TERMS, DO NOT USE UT SWAP.

You represent to us that You are over the age of 18. We do not permit those under 18 to use UT Swap. Your access to and use of UT Swap is also conditioned on Your acceptance of and compliance with the Privacy Policy and other rules.

2. TRADING ACCOUNTS AND AGREEMENT
When You create an account and trading agreement with Us, You shall provide Us information, including KYC that is accurate, complete, and current at all times. Failure to do so constitutes a breach of the Terms, which may result in immediate termination of Your account on UT Swap. You are required to visit our Office to sign trading agreement (except permitted otherwise) and fulfill the qualified trading training before starting trading on UT Swap. You are responsible for safeguarding the passcode and other required keys to access the service or to allow transactions to occur. You agree not to disclose your passcode and other required keys to any third party. You shall notify Us immediately upon becoming aware of any breach of security or unauthorized use of or access into Your account. You shall sure your real name and identity on UT Swap. You may not use the name of another person or entity, or name is not allowed or prohibited by law.

3. OUR RESERVATIONS
We reserve the right, in our sole discretion, to modify, alter or otherwise update any of these Terms at any time. The amended Terms shall become effective from the date as specified in the notification. By using or continuing to use UT Swap after our notice of such modifications You agree to be bound by such amended Terms. SHOULD YOU OBJECT TO ANY OF OUR AMENDED TERMS, YOU SHALL IMMEDIATELY CEASE TO USE UT SWAP.

4. INTELLECTUAL PROPERTY
All contents and objects on UT Swap, including but not limited to, texts, images, audio records, video clips, trademarks, or other applicable objects, are protected under Cambodian intellectual property laws. Such intellectual properties may be owned by us or by Third Party. You may only use these materials for private and non-commercial purposes as permitted under Cambodian laws. You are not allowed to reproduce, republish, post and/or exploit in whole or in part in whatever format any of our content or information without our prior consent. A prerequisite written permission must be obtained from us or from the owner(s) for any use for other purposes than the above exceptions as permitted under Cambodian laws or otherwise You shall be held directly liable for such violations.

5. PROHIBITED ACTS
You shall use UT Swap in accordance with these Terms and for lawful purpose only. You are prohibited from assessing or using of UT Swap, including but not limit, to:

(a) carry out any action that imposes an unreasonable or disproportionately large load or make excessive traffic demands on UT Swap's infrastructure, such as spam or other similar tool;
(b) disclose or share the passwords and/or confirmation numbers with any unauthorized third parties for any unauthorized use or purpose;
(c) attempt to disembody or disassemble the software or programming code forming one part or the whole part of UT Swap;
(d) upload, post, email or otherwise transmit any information, content, or proprietary rights that You are not entitled to do so under Cambodian laws.
(e) use any software or other technology to, whether through manual process or automated device, copy, steal, retrieve, scrape or carry out other illegal acts on UT Swap or its content without our prior written permission;
(f) use any software or other technology to, whether through manual process or automated device, interfere or attempt to interfere in whatever forms with the functioning of UT Swap, including but not limit to transmission of viruses, worms, defects, Trojan horses or other tools of destructive nature;
(g) use UT Swap or any content from UT Swap in any manner which is, in our sole discretion, not reasonable and / or not for the purpose which it is made for;
(h) violate our intellectual property rights including copyright, trade secret or any other rights that we are entitled to;
(i) Illegally login or attempt to login to steal passwords, personal information or other authorized items in violation of any Terms imposed by us or any applicable law. If You breach any of the above prohibited acts, we reserve the right to take all measures deemed necessary to prevent and repress unauthorized access to or misuse of UT Swap, including but not limited to, technological, administrative and legal approaches.
6. CONTENT, INFORMATION AND DISCLAIMER OF WARRANTIES
All content and information on UT Swap is provided solely for information only. Such content and information do not mean to suggest, endorse, or induce any action or omission with regard to transaction to be carried out by You. Therefore, please be reminded that You must always seek for professional and expert advice before taking any action, otherwise You are completely assumed to act at Your own risk.

Also, for editorial content, it should not be regarded as advice or recommendation. You should never solely rely on our editorial content without reaching out to an expert or professional for further advice and verification.

Although regular backups of content are performed, Zillion United does not guarantee there will be no loss or corruption of data. Corrupt or invalid backup points may be caused by, without limitation, content that is corrupted prior to being backed up or that changes during the time a backup is performed. Zillion United will provide support and attempt to troubleshoot any known or discovered issues that may affect the backups of Content. But You acknowledge that Zillion United has no liability related to the integrity of content or the failure to successfully restore content to a usable state. You agree to maintain a complete and accurate copy of any content.

Neither Zillion United, nor our affiliations, directors, staff or service providers, shall be held liable for any loss or damage, whether direct or indirect, suffered by any user or recipient through relying on anything contained in or omitted from our UT Swap.

7. ONE CLICK TRADING MODE
Please bear in mind that we may use One Click Trading Mode for You to carry out any transaction via UT Swap. One Click Trading Mode is a one-step process and it allows You to perform trading operations on UT Swap with only one click on the Buy or Sell with or without any additional confirmation. You should ensure beforehand that all parameters are set based on Your trading intentions. You agree to accept all risks associated with the use of the order submission mode You have chosen, including, without limitation, the risk of errors, omissions or mistakes made in submitting any order. You also agree to fully indemnify and hold harmless Zillion United from any and all losses, costs and expenses that may incur as a result of any such errors, omissions or mistakes by You or any other person trading on Your behalf.

8. ACCEPTANCE OF POSSIBLE RISK AND LOSS OF PROFIT
You hereby confirm to have read, understood and hereby accepts the possible risk and loss of profit as result of any transaction conducted via UT Swap. By accepting these Terms, You accept that You have read and understood the information contained in these Terms, the nature transactions, and possible associated risks.

9. LINKS AND ADS
While using UT Swap, You may from time to time see links, ads or other internet tools that may link You to many other website or apps operated by third parties who may not be affiliated with us. Please bear in mind that Your access to those linked sites or apps is beyond our control and is governed by the Terms of those linked sites or apps. When You depart from UT Swap to other websites or apps, You are at Your own risk. We encourage You to read their Terms carefully before taking any action. Also, we do not warrant any information contained in those websites or apps. Neither Zillion United, nor our affiliations, directors, staff or service providers, shall be held liable for any loss or damage, whether direct or indirect, suffered by any user or recipient through relying on anything contained in or omitted from third party websites or apps linked from ours.

10. DISCLAIMER OF LIABILITY
You expressly agree to use our UT Swap at Your own risk. We, our directors, staff, our affiliates, service providers, information providers, and other persons/entities involved in the production, distribution, hosting, modification, deletion, default, or omission of the information in our UT Swap shall not be held accountable and responsible to any person or entity for any direct, indirect or consequential loss, damages or claims at present or in the future, resulting from the use or reliance of information and content in our UT Swap. Should any of our disclaimer not fully comply with certain compulsory provisions under Cambodian laws, or should our breach of any implied warranty result a damage, we will do our best to rectify our Terms. In case that certain claims for remedies cannot be waived by our Terms, but on the other hand be compulsorily applied under Cambodian laws, only a minimum and limited liability can be resorted against us.

11. ASSIGNMENT
These Terms shall be personal to You and You shall not be entitled to assign or transfer any of Your rights or obligations under these Terms to a third party. We may at any time assign or transfer any of our rights or obligations under these Terms to a third party. We shall notify You of any such assignment.

12. DISPUTE RESOLUTION AND APPLICABLE LAWS
Your use of UT Swap and the application of these entire Terms shall be governed by Cambodian laws. If a dispute arises between You and us regarding any of these Terms, first of all, an amicable discussion shall be made between parties in an attempt to reach an appropriate solution that is efficient and cost-effective within 3 months. Parties shall negotiate and exercise maximum restraints to compromise and rectify the acts, in a spirit of cooperation and friendship, without having to resort to a third-party. However, if the dispute cannot be resolved after 3 months, it can eventually be referred to the jurisdiction of the Courts in Cambodia.

13. SEVERABILITY
Each and all Terms referenced herein represent the entire Agreement between Zillion United and You, and override any implied or verbal agreements or understandings not integrated herein. In the event that any inconsistency exists between these Terms and any future amended Terms, the amended and valid Terms shall prevail.

14. TERMINATION
We may terminate or suspend Your Account immediately, without prior notice or liability, for any reason whatsoever, including without limitation if You breach these Terms and Conditions. Upon termination, Your right to use the Service will cease immediately. If You wish to terminate Your Account, You may simply discontinue using the Service.

15. AMENDMENT
We reserve the right, at Our sole discretion, to modify or replace these Terms at any time. If a revision is material, we will make reasonable efforts to provide at least 30 days' notice prior to any new Terms taking effect. What constitutes a material change will be determined at Our sole discretion. By continuing to access or use Our Service after those revisions become effective, you agree to be bound by the revised Terms. If You do not agree to the new Terms, in whole or in part, please stop using UT Swap.

16. INQUIRIES, SPECIAL REQUESTS OR ASSISTANCE NEEDED
If You have any inquiry, special request or assistance with regard to any of these Terms, please feel free to contact us via (+855) 78 880 111 Our team will promptly respond to and/or take appropriate measures to respond to Your request in a reasonable period of time."""

            }

        } catch (error: IOException) {
            // Must be safe
        }
    }
}