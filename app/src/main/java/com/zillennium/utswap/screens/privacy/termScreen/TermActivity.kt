package com.zillennium.utswap.screens.privacy.termScreen

import android.text.Html
import android.view.View
import android.widget.TextView
import com.zillennium.utswap.R
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivityPrivacyTermBinding
import java.io.IOException

class TermActivity :
    BaseMvpActivity<TermView.View, TermView.Presenter, ActivityPrivacyTermBinding>(),
    TermView.View {

    override var mPresenter: TermView.Presenter = TermPresenter()
    override val layoutResource: Int = R.layout.activity_privacy_term

    override fun initView() {
        super.initView()
        try {
            binding.apply {

                val intent = intent
                val title = intent.getStringExtra("title")

                txtTerm.text = title

                when (title) {
                    "Term and Conditions" -> txtContent.text = Html.fromHtml(termsAndConditions)
                    "Privacy Policy" -> txtContent.text = Html.fromHtml(privacy)
                    "Operation Rules" -> txtContent.text = Html.fromHtml(operational)
                    else -> txtContent.text = "Not have Content"
                }

                // Set Passed Back

                // Set Passed Back
                ivBack.setOnClickListener {
                    onBackPressed()
                }

            }
            // Code
        } catch (error: Exception) {
            // Must be safe
        }
    }

    val termsAndConditions = """<section class="p_container home-section" id="check_login">
        <h1 style=" color: #252552; font-size: 27px;">Terms and Conditions</h1>
        <p class="txt-cw" style="font-size: 20px; color: #294378 !important;">Welcome to UT Swap!</p>
        <p>These terms and conditions outline the rules and regulations for the use of UT Swap's Website, located at https://utswap.io</p>
        <p>By accessing this website we assume you accept these terms and conditions. Do not continue to use UT Swap if you do not agree to take all of the terms and conditions stated on this page.</p>
        <p>The following terminology applies to these Terms and Conditions, Privacy Statement and Disclaimer Notice and all Agreements: "Client", "You" and "Your" refers to you, the person log on this website and compliant to the Company’s terms and conditions. "The Company", "Ourselves", "We", "Our" and "Us", refers to our Company. "Party", "Parties", or "Us", refers to both the Client and ourselves. All terms refer to the offer, acceptance and consideration of payment necessary to undertake the process of our assistance to the Client in the most appropriate manner for the express purpose of meeting the Client’s needs in respect of provision of the Company’s stated services, in accordance with and subject to, prevailing law of the Netherlands. Any use of the above terminology or other words in the singular, plural, capitalization and/or he/she or they, are taken as interchangeable and therefore as referring to the same.</p>
        <h5 class="txt-cw" style="color: #294378 !important;font-size: 20px;">Cookies</h5>
        <p>We employ the use of cookies. By accessing UT Swap, you agreed to use cookies in agreement with the UT Swap's Privacy Policy.</p>
        <p>Most interactive websites use cookies to let us retrieve the user’s details for each visit. Cookies are used by our website to enable the functionality of certain areas to make it easier for people visiting our website. Some of our affiliate/advertising partners may also use Cookies.</p>
        <h5 class="txt-cw" style="color: #294378 !important; font-size: 20px;">License</h5>
        <p>Unless otherwise stated, UT Swap and/or its licensors own the intellectual property rights for all material on UT Swap. All intellectual property rights are reserved. You may access this from UT Swap for your own personal use subjected to restrictions set in these terms and conditions.</p>
        <p>You must not:</p>
        <ul>
            <li class="txt-cw" style="color: #294378 !important;">Republish material from UT Swap</li>
            <li class="txt-cw" style="color: #294378 !important;">Sell, rent or sub-license material from UT Swap</li>
            <li class="txt-cw" style="color: #294378 !important;">Reproduce, duplicate or copy material from UT Swap</li>
            <li class="txt-cw" style="color: #294378 !important;">Redistribute content from UT Swap</li>
        </ul>
        <p>Parts of this website offer an opportunity for users to post and exchange opinions and information in certain areas of the website. UT Swap does not filter, edit, publish or review Comments prior to their presence on the website. Comments do not reflect the views and opinions of UT Swap, its agents and/or affiliates. Comments reflect the views and opinions of the person who posts their views and opinions. To the extent permitted by applicable laws, UT Swap shall not be liable for the Comments or for any liability, damages or expenses caused and/or suffered as a result of any use of and/or posting of and/or appearance of the Comments on this website.</p>
        <p>UT Swap reserves the right to monitor all Comments and to remove any Comments which can be considered inappropriate, offensive or causes breach of these Terms and Conditions.</p>
        <p>You warrant and represent that:</p>
        <ul>
            <li class="txt-cw" style="color: #294378 !important;">You are entitled to post the Comments on our website and have all necessary licenses and consents to do so;</li>
            <li class="txt-cw" style="color: #294378 !important;">You are entitled to post the Comments on our website and have all necessary licenses and consents to do so;</li>
            <li class="txt-cw" style="color: #294378 !important;">The Comments do not contain any defamatory, libelous, offensive, indecent or otherwise unlawful material which is an invasion of privacy</li>
            <li class="txt-cw" style="color: #294378 !important;">The Comments will not be used to solicit or promote business or custom or present commercial activities or unlawful activity.</li>
        </ul>
        <p>You hereby grant UT Swap a non-exclusive license to use, reproduce, edit and authorize others to use, reproduce and edit any of your Comments in any and all forms, formats or media.</p>
        <h5 class="txt-cw" style="color: #294378 !important; font-size: 20px;">Hyperlinking to our Content</h5>
        <p>The following organizations may link to our Website without prior written approval:</p>
        <ul>
            <li class="txt-cw" style="color: #294378 !important;">Government agencies;</li>
            <li class="txt-cw" style="color: #294378 !important;">Search engines;</li>
            <li class="txt-cw" style="color: #294378 !important;">News organizations;</li>
            <li class="txt-cw" style="color: #294378 !important;">Online directory distributors may link to our Website in the same manner as they hyperlink to the Websites of other listed businesses; and</li>
            <li class="txt-cw" style="color: #294378 !important;">System wide Accredited Businesses except soliciting non-profit organizations, charity shopping malls, and charity fundraising groups which may not hyperlink to our Web site.</li>
        </ul>
        <p>These organizations may link to our home page, to publications or to other Website information so long as the link: (a) is not in any way deceptive; (b) does not falsely imply sponsorship, endorsement or approval of the linking party and its products and/or services; and (c) fits within the context of the linking party’s site.</p>
        <p>We may consider and approve other link requests from the following types of organizations:</p>
        <ul>
            <li class="txt-cw" style="color: #294378 !important;">commonly-known consumer and/or business information sources;</li>
            <li class="txt-cw" style="color: #294378 !important;">dot.com community sites;</li>
            <li class="txt-cw" style="color: #294378 !important;">associations or other groups representing charities;</li>
            <li class="txt-cw" style="color: #294378 !important;">online directory distributors;</li>
            <li class="txt-cw" style="color: #294378 !important;">internet portals;</li>
            <li class="txt-cw" style="color: #294378 !important;">accounting, law and consulting firms; and</li>
            <li class="txt-cw" style="color: #294378 !important;">educational institutions and trade associations.</li>
        </ul>
        <p>We will approve link requests from these organizations if we decide that: (a) the link would not make us look unfavorably to ourselves or to our accredited businesses; (b) the organization does not have any negative records with us; (c) the benefit to us from the visibility of the hyperlink compensates the absence of UT Swap; and (d) the link is in the context of general resource information.</p>
        <p>These organizations may link to our home page so long as the link: (a) is not in any way deceptive; (b) does not falsely imply sponsorship, endorsement or approval of the linking party and its products or services; and (c) fits within the context of the linking party’s site.</p>
        <p>If you are one of the organizations listed in paragraph 2 above and are interested in linking to our website, you must inform us by sending an e-mail to UT Swap. Please include your name, your organization name, contact information as well as the URL of your site, a list of any URLs from which you intend to link to our Website, and a list of the URLs on our site to which you would like to link. Wait 2-3 weeks for a response.</p>
        <p>Approved organizations may hyperlink to our Website as follows:</p>
        <ul>
            <li class="txt-cw" style="color: #294378 !important;">By use of our corporate name; or</li>
            <li class="txt-cw" style="color: #294378 !important;">By use of the uniform resource locator being linked to; or</li>
            <li class="txt-cw" style="color: #294378 !important;">By use of any other description of our Website being linked to that makes sense within the context and format of content on the linking party’s site.</li>
        </ul>
        <p>No use of UT Swap's logo or other artwork will be allowed for linking absent a trademark license agreement.</p>
        <h5 class="txt-cw" style="color: #294378 !important; font-size: 20px;">iFrames</h5>
    
        <p>Without prior approval and written permission, you may not create frames around our Webpages that alter in any way the visual presentation or appearance of our Website.</p>
        <h5 class="txt-cw" style="color: #294378 !important; font-size: 20px;">Content Liability</h5>
        <p>We shall not be hold responsible for any content that appears on your Website. You agree to protect and defend us against all claims that is rising on your Website. No link(s) should appear on any Website that may be interpreted as libelous, obscene or criminal, or which infringes, otherwise violates, or advocates the infringement or other violation of, any third party rights.</p>
        <h5 class="txt-cw" style="color: #294378 !important;">Your Privacy</h5>
        <p>Please read Privacy Policy</p>
        <h5 class="txt-cw" style="color: #294378 !important;font-size: 20px;">Reservation of Rights</h5>
        <p>We reserve the right to request that you remove all links or any particular link to our Website. You approve to immediately remove all links to our Website upon request. We also reserve the right to amend these terms and conditions and it’s linking policy at any time. By continuously linking to our Website, you agree to be bound to and follow these linking terms and conditions.</p>
        <h5 class="txt-cw" style="color: #294378 !important;">Removal of links from our website</h5>
        <p>If you find any link on our Website that is offensive for any reason, you are free to contact and inform us any moment. We will consider requests to remove links but we are not obligated to or so or to respond to you directly.</p>
        <p>We do not ensure that the information on this website is correct, we do not warrant its completeness or accuracy; nor do we promise to ensure that the website remains available or that the material on the website is kept up to date.</p>
        <h5 class="txt-cw" style="color: #294378 !important;font-size: 20px;">Disclaimer</h5>
        <p>To the maximum extent permitted by applicable law, we exclude all representations, warranties and conditions relating to our website and the use of this website. Nothing in this disclaimer will:</p>
        <ul>
            <li class="txt-cw" style="color: #294378 !important;">limit or exclude our or your liability for death or personal injury;</li>
            <li class="txt-cw" style="color: #294378 !important;">limit or exclude our or your liability for fraud or fraudulent misrepresentation;</li>
            <li class="txt-cw" style="color: #294378 !important;">limit any of our or your liabilities in any way that is not permitted under applicable law; or</li>
            <li class="txt-cw" style="color: #294378 !important;">exclude any of our or your liabilities that may not be excluded under applicable law.</li>
        </ul>
        <p>The limitations and prohibitions of liability set in this Section and elsewhere in this disclaimer: (a) are subject to the preceding paragraph; and (b) govern all liabilities arising under the disclaimer, including liabilities arising in contract, in tort and for breach of statutory Duty.</p>
    </section>"""
    val privacy =
        """<div class="container-floud main_contain" id="check_login" style="margin-top: 4rem;">
        <div class="row " style="margin: auto;padding: 0;">
            <div class="m-0 text-14 px-0">
                <h1 class="p-policy" style="font-size: 27px;">PRIVACY POLICY </h1>
                <p></p>
                <h4 class="nmber">1. IMPORTANCE INFORMATION (MUST READ)</h4>
                <p></p>
                <p><b>This <code class="f-weig">Policy</code> applies to</b> <code class="ut-swap"> UT Swap platform</code> and <code class="znd">Zillion United</code> s website, accessible from <a href="https://utswap.io/.">https://utswap.io/.</a> This policy is not applicable to any information collected offline or via channels other than this <code class="ut-swap">UT Swap platform</code> <code class="f-weig">and/or</code> <code class="znd">Zillion United'</code> s website.</p>
                <p></p>
                <p>You shall and are advised to read our Private Policy (herein called the <code class="f-weig">"Policy"</code>) carefully before continuing to use our <code class="ut-swap">UT Swap Platform (UT Swap)</code> and <code class="znd">Zillion United'</code> s website. This Policy describes in details how your personal information is being collected, stored, used, disclosed, processed or otherwise managed by <code class="znd">Zillion United</code> and our affiliates (herein called, <code class="f-weig">“we,”</code> <code class="f-weig">“our,”</code> or <code class="f-weig">“us”</code>). By using our <code class="ut-swap">UT Swap</code>, you agree to enter into an agreement with us and you unconditionally agree with all terms contained in this policy without any reservation.</p>
                <p></p>
                <p>By using our <code class="ut-swap">UT Swap platform,</code> you consent to our collection, storage, processing and transfer of your personal information as described in this Privacy Policy. <code class="f-weig">IF YOU DO NOT AGREE WITH ANY OF THESE TERMS, DO NOT USE THIS MOBILE PLATFORM.</code> </p>
                <p></p>
                <h4 class="nmber">2.	OUR RESPECT OF YOUR PRIVACY</h4>
                <p></p>
                <p>One of our main priorities is the privacy of our users and visitors. We strive to protect and maintain privacy and transparency of collected information from our Users. Without prejudice to terms of our Policy, we respect all principles of privacy and legal provisions related to privacy under Cambodia laws which we are bound to obey.</p>
                <p></p>
                <h4 class="nmber">3.	COLLECTION OF INFORMATION</h4>
                <p></p>
                <p>All personal information collected through <code class="ut-swap">UT Swap platform</code> is voluntarily provided by you. You may decline to provide us any of required information, however you may not be able to use certain services on <code class="ut-swap">UT Swap platform</code>. Only necessary and relevant information may be collected including your name, gender, date of birth, home address, national ID/passport, email address, phone number and selfies. When you register for an Account, we may ask for your contact information, including items such as a company name, address, email address and telephone number. </p>
                <p></p>
                <p>Please be aware that the personal information that you are asked to provide is for the purpose of complying with our Know Your Customer (“KYC”) obligations required under applicable laws and regulations, and Anti-Money Laundering laws, and other regulations.</p>
                <h4 class="nmber">4.	HOW WE USE YOUR INFORMATION</h4>
                <p></p>
                <p>By providing us your personal information, you are voluntarily allowed us or we are entitled to use your personal information for the following purposes: </p>
                <p></p>
                <div class="ul-li">
                    <ul class="ul" style="padding-left: 35px;">
                        <li style="list-style: outside; ">Providing, operating, information and maintenance our platform and website</li>
                        <li style="list-style: outside;">Improving, personalizing, and expanding our service</li>
                        <li style="list-style: outside;">Understanding and analyzing how you use our platform</li>
                        <li style="list-style: outside;">Developing new products, services, features, and functionality</li>
                        <li style="list-style: outside;">Communicating with you, either directly or through one of our partners, including for customer service, to provide you with updates and other information relating to the website, and for marketing and promotional purposes</li>
                        <li style="list-style: outside;">Notifying you the updates about any amendment with regard to our Terms and Policy;</li>
                        <li style="list-style: outside;">Securing and protecting our business against any cyber-crime or attempt to commit cyber-crime by allowing us to investigate, detect and take necessary actions to prevent or repress unlawful or unauthorized acts such as fraud, unauthorized access or other threats to carry out illegitimate acts as prohibited under laws and regulations; </li>
                        <li style="list-style: outside;">Sharing your personal information with our business partners and third parties so that they can reach out to you directly to demonstrate their products and services; and </li>
                        <li style="list-style: outside;">Complying with our internal regulations and external legal obligations, including but not limit to, audits, reports, corporate governance, business operation, dispute resolution and litigation etc. </li>
                    </ul>
                </div>
                <p></p>
                <h4 class="nmber">5.	DISCLOSURE OF YOUR PERSONAL INFORMATION </h4>
                <p></p>
                <p>By using our <code class="ut-swap">UT Swap</code>, you are aware and agree that we may disclose your personal information to the following persons/entities: </p>
                <p></p>
                <div class="ul-li">
                    <ul class="ul" style="padding-left: 35px;">
                        <li style="list-style: outside; ">Our affiliates: we may disclose your personal information to our affiliated companies, staff, agents and business partners. Their use of your privacy will be subject to this policy. </li>
                        <li style="list-style: outside; ">Our service providers: we may disclose your personal information to our service providers who are assigned to perform the services for us including hosting, advising, auditing and other relevant services. </li>
                        <li style="list-style: outside; ">Real estate agency and developers: we may disclose your personal information to real estate agencies and developers who may provide you services of your interest. </li>
                        <li style="list-style: outside; ">Business transfers: We may disclose your personal information to our forthcoming affiliates that join our company as result of merger and acquisition, asset transfers, succession, bankruptcy or other forms of business transfers. </li>
                        <li style="list-style: outside; ">Authorities: We may disclose your personal information to public authorities should it be a legal requirement. We may also disclose your personal information to public authorities to protect our rights and claims, should it be necessary to do so. </li>
                        <li style="list-style: outside; ">Other third parties: We may disclose your personal information to other third parties with permission from you. </li>
                    </ul>
                </div>
                <p></p>
                <h4 class="nmber">6.	SECURITY AND DATA RETENTION  </h4>
                <p></p>
                <p>We are committed to the security and protection of your privacy. To do so, we exploit up-to-date technical and administrative tools and procedure to protect your privacy from unauthorized access or theft etc. However, despite our efforts, it must be acknowledged that no security measure is ever perfect. We are committed to taking reasonable administrative and technical steps to fix, restitute and prevent future reoccurrence. However, we will not take any responsibility for any events or consequences beyond our control as result of unauthorized access to your personal information. We require that, in the event that the security of your account is being in danger, you report to us immediately.  We will retain your personal information up until the period necessary to fulfill our purposes and business objectives as outlined this Policy or for a longer period as permitted by law.</p>
                <p></p>
                <p>We will retain your personal information up until the period necessary to fulfill our purposes and business objectives as outlined this Policy or for a longer period as permitted by law. </p>
                <p></p>
                <h4 class="nmber">7.	EXPORT OF PERSONAL INFORMATION OUTSIDE CAMBODIA  </h4>
                <p></p>
                <p>Please be aware that for technology and safety reasons, your personal information provided to us may be held in the servers outside of Cambodia. By providing us your personal information, you agree to allow us to transfer your information outside of Cambodia for the above-mentioned purposes. </p>
                <h4 class="nmber">8.	LOG FILES</h4>
                <p></p>
                <p><code class="ut-swap">UT Swap</code> follows a standard procedure of using log files. These files log visitors when they visit our platform and/or websites. All hosting companies do this as part of hosting services' analytics. The information collected by log files include internet protocol (IP) addresses, browser type, Internet Service Provider (ISP), date and time stamp. referring/exit pages and possibly the number of clicks. These are not linked to any information that is personally identifiable. The purpose of the information is for analyzing trends, administering the site, tracking users' movement on the website and gathering demographic information.</p>
                <h4 class="nmber">9.	PARTNER OR THIRD-PARTY APPLICATIONS</h4>
                <p></p>
                <p><code class="ut-swap">UT Swap</code> may contain links to our business partner or third-party mobile apps, websites or services, which are not under our operation or control. Accordingly, this Policy does not apply to any Mobile App or service operated by any third party. Provided that you click on or by other means visit the third-party applications, you will leave our Mobile App and enter into the third-party applications where you will be subject to their private policy and terms. We encourage you to read their policy carefully, and we also are not responsible for any policy or practices conducted by third party. </p>
                <h4 class="nmber">10.	OPTIONS AND RIGHT TO CHOOSE </h4>
                <p></p>
                <p><code class="f-weig">Marketing</code>: you may opt out from receiving marketing-related communications from us by promptly contacting us or by unsubscribing. We will do our best to comply with your request(s). Please be reminded that even if you may opt out from receiving marketing-related communications from us, you will continue to receive certain important administrative messages from us. </p>
                <p></p>
                <p><code class="f-weig">Cookies</code> : we may use first party and third-party cookies, plugins, and other tools to gather and/or collect information when you access our Services. For example, when you begin using our Services, our server may record your IP address and other information such as the type of operating system, its version, screen resolution, and other information related to your device. We may record this information for security purposes or for the improvement of your experience in using our Services. We may also collect statistical information in order to continually improve and upgrade our apps from time to time. If you have disable one or more cookies, we may still use or retain information collected from the previous cookies you used. However, we will stop using the disabled cookie to collect any further information. </p>
                <p></p>
                <p><code class="f-weig">Access, Review, Amendment</code> and <code class="f-weig">Deletion</code>: You have the right to request for review, amendment, deletion of your personal information, provided that the regulations related to privacy permit so. Should you wish to do so, please feel free to contact us as provided at 11 below. </p>
                <p></p>
                <p>Deactivation: You can deactivate your account at any time by using any means available on <code class="ut-swap">UT Swap</code> or otherwise by contacting us. Provided that you request us to assist you in such deactivation, we will do our best to respond to your request in an appropriate period of time. Please bear in mind that once your account is deactivated, you will not be able to access any of the services. </p>
                <p></p>
                <p><code class="f-weig">Recordkeeping</code>: Please bear in mind that we may need to retain certain information for our recordkeeping purposes and/or to realize all transactions that you began prior to requesting a change or deletion. That retained information will remain within our databases and/or other records. </p>
                <p></p>
                <h4 class="nmber">11.	FUTURE AMENDMENT OF PRIVACY POLICY  </h4>
                <p></p>
                <p>We reserve the rights, at our sole discretion, to modify this Policy at any time. In case of amendment, we will provide prior notification of such amendment on our <code class="ut-swap">UT Swap</code> and it is your own responsibility to review and observe our Policy periodically to keep yourself updated. Amended policy shall become effective from the date as specified in the notification. By using or continuing to use this <code class="ut-swap">UT Swap</code> on and after the effective date you agree to be bound by the new amended policy. Should you object to any of our revised Terms, you shall immediately cease to use <code class="ut-swap">UT Swap</code>. </p>
                <p></p>
                <h4 class="nmber">12.	INQUIRIES, SPECIAL REQUESTS OR ASSISTANCES </h4>
                <p></p>
                <p>If you have any inquiry, special request or assistance with regard to this privacy policy, please feel free to contact us via (+855) 078 880 111.  Our team will promptly respond to and/or take appropriate measure to respond to your request in a reasonable period of time.</p>
            </div>
        </div>
    </div>"""
    val operational =
        """<section class="main_section home-section custom_padding " id="check_login" style="min-height: 60vh;">
    <h2 class="prim_heading first_heading">OPERATIONAL RULES</h2>
    <!-- <h3 class="sec_heading">Table of Contents</h3>
    <ul class="tb_content">
        <li>I. General Provision.......1</li>
        <li>II. UT Trading in UT Swap platform....... 2</li>
        <li>III. UT Trading Order ....... 3</li>
        <li>IV. Trading Conditions ....... 4</li>
        <li>V. Market Management....... 5</li>
        <li>VI. Monitoring of UT Trading ....... 6</li>
        <li>VII. Online UT Trading Benefits and Risks ....... 6</li>
        <li>VIII. Suspension, Termination and Cancelation of UT Swap account ....... 7</li>
        <li>IX. Miscellaneous ....... 7</li>
    </ul> -->
    <h2 class="prim_heading" style="font-size: 20px;">I. General Provision</h2>
    <h3 class="sec_heading">Article 1: Purpose</h3>
    <p>The purpose of the Operating Rules of Unit of Trust (“UT”) Trading Market is to prescribe the terms
        and conditions, method, mechanism, procedure and management of the trading of UT on the UT
        Swap Online Trading Application (“App”).</p>
    <h3 class="sec_heading">Article 2: Definition</h3>
    <p>The terms used in these rules are defined as below:</p>
    <p><span class="t_bold">Unit of Trust (“UT”): </span>unit linked to Zillion United’s Project.</p>
    <p><span class="t_bold">Project: </span>investment scheme created by Zillion United.</p>
    <p><span class="t_bold">UT Swap account: </span>the official account/user for login and/or for UT trading in UT Swap
        platform</p>
    <p><span class="t_bold">Trade: </span>the action of buy and sell UT in secondary market through UT Swap platform</p>
    <p><span class="t_bold">Subscribe: </span>the action of buy UT at initial offering a project to the public.</p>
    <p><span class="t_bold">Opening Price: </span>first traded price of UT on each trading day.</p>
    <p><span class="t_bold">Closing Price: </span>last traded price of UT on each trading day.</p>
    <p><span class="t_bold">Market price: </span>the last matched price of UT.</p>
    <p><span class="t_bold">Base price: </span>the initial listed price of UT which varies in accordance to project.</p>
    <p><span class="t_bold">24H High: </span>highest traded price of UT on each trading day.</p>
    <p><span class="t_bold">24H Low: </span>lowest traded price of UT on each trading day.</p>
    <p><span class="t_bold">24H Volume: </span>total traded volume of UT on each trading day.</p>
    <p><span class="t_bold">Order Book: </span>list of all unmatched orders for the project.</p>
    <p><span class="t_bold">Trader: </span>users who have obtained trader function in the platform</p>
    <p><span class="t_bold">Market watch:: </span>the monitoring of market situation through analyzing the transactions
        of UT trading in
        the market, the status of orders, the announcements related to disclosure document, rumors, and
        information obtained from other sources.</p>
    <h2 class="prim_heading" style="font-size: 20px;">II. UT Trading in UT Swap platform</h2>
    <h3 class="sec_heading mt-2">Article 3: Creation of UT Swap account</h3>
    <p>In order to have right to trade UT in UT Swap platform, trader shall have their own personal UT Swap
        account by conduct the following procedure:</p>
    <ul>
        <li>1. Go to Browser: <a href="https://utswap.io/">https://utswap.io/</a></li>
        <li>2. Sign up for a new account</li>
        <li>3. Complete the information required by system</li>
        <li>4. Follow our privacy policy</li>
        <li>5. Agree and accept our Term &amp; Conditions</li>
    </ul>
    <h3 class="sec_heading mt-2">Article 4: Subscribe/ Place order in UT Swap</h3>
    <p>In order to subscribe/ place a buy or sell order of UT on the UT Swap platform, user shall conduct
        the following procedures:</p>
    <ul>
        <li>1. Go to Browser: <a href="https://utswap.io/">https://utswap.io/</a></li>
        <li>2. Use your UT Swap account user to Sign/ log in UT Swap platform</li>
        <li>3. Check the availability of your balance or UT to ensure the settlement or trading of order</li>
        <li>4. Identify the detailed order information as prescribed in <span class="t_bold">Article 11</span>; and</li>
        <li>5. Subscribe/ place order on our platform as prescribed in <span class="t_bold">Article 7</span>.</li>
    </ul>
    <h3 class="sec_heading mt-2">Article 5: Account Type</h3>
    <p>Traders are classified, upgraded or degraded to certain account type based on the criteria below:</p>
    <table class="table table-bordered mobile_table">
        <thead>
            <tr>
                <th scope="col"></th>
                <th scope="col">Standard</th>
                <th scope="col">Premium</th>
                <th scope="col">Professional</th>
            </tr>
        </thead>
        <tbody>
            <tr class="table-primary">
                <th scope="row"><span class="t_bold">I. Register and Set-up</span></th>
                <td></td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td scope="row">1. Account registration</td>
                <td>✓</td>
                <td>✓</td>
                <td>✓</td>
            </tr>
            <tr class="table-primary">
                <td scope="row">2. KYC submission and approval</td>
                <td>✓</td>
                <td>✓</td>
                <td>✓</td>
            </tr>
            <tr>
                <th scope="row">II. Balance Requirement</th>
                <td></td>
                <td></td>
                <td></td>
            </tr>
            <tr class="table-primary">
                <td scope="row">1. No minimum balance requirement</td>
                <td>✓</td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td scope="row">2. &gt;USD30K net balance in the past 3 months/
                    for 3months</td>
                <td></td>
                <td>✓</td>
                <td></td>
            </tr>
            <tr class="table-primary">
                <td scope="row">3. &gt;USD50K net balance in the past 3 months/for 3months</td>
                <td></td>
                <td></td>
                <td>✓</td>
            </tr>
            <tr>
                <th scope="row">III. Trading Activities</th>
                <td></td>
                <td></td>
                <td></td>
            </tr>
            <tr class="table-primary">
                <td scope="row">1. &gt;USD20K average trade value the past 3 months</td>
                <td></td>
                <td></td>
                <td>✓</td>
            </tr>
            <tr>
                <th scope="row">IV. Training by Zillion United</th>
                <td></td>
                <td></td>
                <td></td>
            </tr>
            <tr class="table-primary">
                <td scope="row">1. Training session completion</td>
                <td>✓</td>
                <td>✓</td>
                <td>✓</td>
            </tr>
            <tr>
                <th scope="row">V. Other Requirements</th>
                <td></td>
                <td></td>
                <td></td>
            </tr>
            <tr class="table-primary">
                <td scope="row">1. Assets certificate deposit at Zillion Trust for
                    balance margin accessibility</td>
                <td></td>
                <td>✓</td>
                <td>✓</td>
            </tr>
        </tbody>
    </table>
    <p>*Note: Net balance = UT*VWAP + Cash</p>
    <h3 class="sec_heading mt-2">Article 6: Account Type Privileges</h3>
    <p>Account type are classified and entitled to the privileges set out below:</p>
    <table class="table table-bordered mobile_table">
        <thead>
            <tr>
                <th scope="col"></th>
                <th scope="col">Standard</th>
                <th scope="col">Premium</th>
                <th scope="col">Professional</th>
            </tr>
        </thead>
        <tbody>
            <tr class="table-primary">
                <th scope="row"><span class="t_bold">I. Information</span></th>
                <td></td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td scope="row">1. View general information</td>
                <td>✓</td>
                <td>✓</td>
                <td>✓</td>
            </tr>
            <tr class="table-primary">
                <th scope="row">II. Market Access</th>
                <td></td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td scope="row">1. Primary market subscription (presale discount)</td>
                <td>✓</td>
                <td>✓</td>
                <td>✓</td>
            </tr>
            <tr class="table-primary">
                <td scope="row">2. Property swap</td>
                <td></td>
                <td>✓</td>
                <td>✓</td>
            </tr>
            <tr>
                <td scope="row">3. Offline project advanced notice</td>
                <td></td>
                <td>✓</td>
                <td>✓</td>
            </tr>
            <tr class="table-primary">
                <th scope="row">III. Other Benefits</th>
                <td></td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td scope="row">1. &gt;XX% trade credit margin <br>
                    (Able to trade over existing trading balance)</td>
                <td></td>
                <td>✓</td>
                <td></td>
            </tr>
            <tr class="table-primary">
                <td scope="row">2. XX% trade credit margin
                    (Able to trade over existing trading balance)</td>
                <td></td>
                <td></td>
                <td>✓</td>
            </tr>
            <tr>
                <td scope="row">3. Z1 membership program</td>
                <td></td>
                <td>✓</td>
                <td>✓</td>
            </tr>

        </tbody>
    </table>
    <h2 class="prim_heading" style="font-size: 20px;">III. UT Trading Order</h2>
    <h3 class="sec_heading mt-2">Article 7: Type of Order</h3>
    <p>Traders shall select one of the below order types:</p>
    <ul>
        <li><span class="t_bold"><span class="mr-2">•</span>Limit Order: </span>an order to bid or ask for UTs at
            a specific maximum price acceptable to the
            trader or at a better price than the said price. For bid order, better price refers to the price
            lower than the specific price while for the case of ask order, better price refers to the
            price higher than the specific price.</li>
        <li><span class="t_bold"><span class="mr-2">•</span>Market Order: </span>an order to bid or ask for UTs at
            the best price available on the market
            without a specific maximum price preset.</li>
    </ul>

    <h3 class="sec_heading mt-2">Article 8: Order Conditions</h3>
    <p>All orders shall have one of the below conditions affixed to it:</p>
    <ul>
        <li><span class="t_bold"><span class="mr-2">•</span>Total Order Condition: </span>Condition specifying to
            execute order only if the entire order
            quantity can be fulfilled.</li>
        <li><span class="t_bold"><span class="mr-2">•</span>Partial Order Condition: </span>Condition specifying
            to execute order at any quantity within the
            order quantity (default).</li>
    </ul>

    <h3 class="sec_heading mt-2">Article 9: Validity of order</h3>
    <ul>
        <li><span class="mr-2">•</span>A UT order shall be valid from the time it is submitted to the trading
            function of the UT
            Swap, and it is executed during the trading session.</li>
        <li><span class="mr-2">•</span>Notwithstanding Paragraph 1, the validity of an order of an UT or property
            swap may be
            suspended when the trading of such project or product is suspended during the trading
            hours.</li>
    </ul>

    <h3 class="sec_heading mt-2">Article 10: Correction or Cancellation of a Submitted Order</h3>
    <p>If an order is not executed yet, the trader may cancel the booking of the order.</p>
    <h3 class="sec_heading mt-2">Article 11: Detailed Order Information</h3>
    <p>All orders shall include the following details:</p>
    <ul>
        <li>1. Project name;</li>
        <li>2. Unit price (applicable on limit order only)</li>
        <li>3. Quantity of UT</li>
        <li>4. Order condition (total or partial – applicable on limit order only);</li>
        <li>5. Classification of order (bid or ask order)</li>
    </ul>
    <h3 class="sec_heading mt-2">Article 12: Trading Methods</h3>
    <p>All UT trades shall be done through the Auction Trading Method (ATM).</p>
    <h3 class="sec_heading mt-2">Article 13: Auction Trading Method (ATM)</h3>
    <p>ATM is a method of automatically matching bid and ask orders using single or multiple price auction.
        Priority of orders in a single and multiple price auction shall be determined in accordance with the
        following guidelines:</p>
    <ul>
        <li>1. The lower asking price order has priority over higher asking price order, and higher bidding
            price order shall have priority over lower buying price order; and</li>
        <li>2. In the case of the same price orders, the orders placed earlier shall have priority to be
            matched over the orders placed later.</li>
        <li>3. The matched transaction subject to fee charge as mention in <span class="t_bold">Article 23.</span> </li>
    </ul>
    <h3 class="sec_heading mt-2">Article 14: Trading Days and Hours</h3>
    <ul>
        <li><span class="mr-2">•</span>Trading is open 7/7 and trading hours shall be from 9am to 4pm.</li>
        <li><span class="mr-2">•</span>Traders can place bid and ask orders OUT of the trading hours, though all
            orders would
            ONLY be matched and executed during trading hours based on ATM prescribed in Article13</li>
    </ul>

    <h2 class="prim_heading" style="font-size: 20px;">IV. Trading Conditions</h2>
    <h3 class="sec_heading mt-2">Article 15: Currency, Order Unit, Minimum Order and Maximum UT</h3>

    <ul>
        <li><span class="mr-2">•</span>Currency for UT trading is US dollars.</li>
        <li><span class="mr-2">•</span>The order unit for UT shall be 01 (one) unit of UT</li>
        <li><span class="mr-2">•</span>The minimum quantity per order is 1 UT.</li>
        <li><span class="mr-2">•</span>The maximum 20% of the whole UT of each project held by a user</li>
    </ul>

    <h3 class="sec_heading mt-2">Article 16: Price Limits</h3>
    <ul>
        <li><span class="mr-2">•</span>Price for all orders shall be in 2 decimals</li>
        <li><span class="mr-2">•</span>For bid order, bid price shall NOT be higher than last price by 30%;</li>
        <li><span class="mr-2">•</span>For ask order, ask price shall NOT be lower than last price by 30%.</li>
    </ul>

    <h3 class="sec_heading mt-2">Article 17: Correction of Trading Transaction Errors</h3>
    <p>In case the platform makes any technical error in the course of intermediating the trading
        transaction, Zillion United (“Administrator”) shall correct the transaction in due course. Trader shall
        carefully check and verify before carrying out any transaction.</p>
    <h3 class="sec_heading mt-2">Article 18: Management of UT trading</h3>
    <p>Zillion United may adopt special terms and conditions for each project and/or UT trading market
        prior to listing (for example: lock up period, buyback …etc.).</p>

    <h2 class="prim_heading" style="font-size: 20px;">V. Market Management</h2>
    <h3 class="sec_heading mt-2">Article 19: Publication of Market Data</h3>
    <p>The UT Swap platform shall make available to public the following data</p>
    <ul>
        <li><span class="mr-2">•</span>Opening price, closing price and market price</li>
        <li><span class="mr-2">•</span>Total trading volume and available UT amount</li>
        <li><span class="mr-2">•</span>Subscription volume and available UT amount</li>
        <li><span class="mr-2">•</span>News and updates related to project</li>
        <li><span class="mr-2">•</span>Details and performance of project</li>
    </ul>
    <h3 class="sec_heading mt-2">Article 20: Matters to be Notified to Traders</h3>
    <p>The Administrator shall notify each of the following matters to traders:</p>
    <ul>
        <li><span class="mr-2">•</span>The operating rules and amendments (if any);</li>
        <li><span class="mr-2">•</span>Initially listed UT, additional listing, delisting, UT suspended from
            trading;</li>
        <li><span class="mr-2">•</span>Temporary closure of market, suspension of market and changes in trading
            hours and other matters deemed relevant and necessary.</li>
    </ul>
    <h3 class="sec_heading mt-2">Article 21: Deposit</h3>
    <p>Traders can deposit to the UT Swap account anytime from any bank account; and it will immediately
        credit to your account. The deposit processes subject to fee as mention in the <span class="t_bold">Article
            23.</span></p>
    <h3 class="sec_heading mt-2">Article 22: Withdrawal</h3>
    <li><span class="mr-2">•</span>Traders can withdraw fund from the UT Swap account anytime; and subject to the
        limit set
        force in accordance to his/her account class as below: <br>
        <table class="table mobile_table">
            <thead>
                <tr>
                    <th>Account Type</th>
                    <th>Standard</th>
                    <th>Premium</th>
                    <th>Professional</th>
                </tr>
            </thead>
            <tbody>
                <tr class="table-primary">
                    <td scope="row">Daily withdrawal limit</td>
                    <td>USD2,000</td>
                    <td>USD10,000</td>
                    <td>USD20,000</td>
                </tr>
            </tbody>
        </table>
        <p><span class="t_bold">**Note:</span> Any withdraw exceed daily limit shall inform to Zillion United within
            7days.</p>
    </li>
    <li><span class="mr-2">•</span>Trader’s account type is subject to reclassification based on regular criteria
        review as
        prescribed in the <span class="t_bold">Article 5.</span></li>
    <li><span class="mr-2">•</span>The process of withdrawal subject to fee charge as mention in the <span class="t_bold">Article 23.</span></li>
    <h3 class="sec_heading mt-2">Article 23: Fee Charge</h3>
    <p>The<span style="color: #0d0d72; font-size: 1.2rem;"> Zillion United </span>will charge of our digital marketplace
        service through UT Swap platform as below:</p>
    <ul>
        <li><span class="t_bold">• Transaction Fee</span>(Buy/sell order): <span class="t_bold">0.3%</span> of
            total order value</li>
        <li><span class="t_bold">• Deposit Fee:</span>
            <ul>
                <li><span class="t_bold mr-2">■ Local bank: USD2</span>per transaction of any amount</li>
                <li><span class="t_bold mr-2">■ VISA, Master, JCB: 3%</span>of deposit amount per transaction</li>
            </ul>

        </li>
        <li><span class="t_bold mr-2">• Withdrawal Fee: 1.5%</span>(Buy/sell order): <span class="t_bold">0.3%</span> of withdrawal amount (minimum USD5) per transaction</li>

    </ul>

    <h2 class="prim_heading" style="font-size: 20px;">VI. Monitoring of UT Trading</h2>
    <h3 class="sec_heading mt-2">Article 24: Preventive Actions for Online Trading Application</h3>
    <p>To maintain a fair-trade environment and to protect investors, the Administrator may undertake
        preventive actions against unfair or abnormal trading.</p>
    <h3 class="sec_heading mt-2">Article 25: Market Watch</h3>
    <p>The Administrator conducts market watch to identify the UT suspicious of abnormal trading by taking
        into accounts such matters as trading manners, price fluctuation, UT trading volume, and
        relationship between the market price and trade, and the details of rumors.</p>

    <h2 class="prim_heading" style="font-size: 20px;">VII. Online UT Trading Benefits and Risks</h2>
    <h3 class="sec_heading mt-2">Article 26: Benefits Associated with Online Trading</h3>
    <p>Users’ benefit includes:</p>
    <ul>
        <li><span>-</span><span class="t_bold">Time saving:</span>although required to thumbprint on written agreement
            later, online trading
            reduces the brick-and-mortar way of trade which requires appointment and paperwork ready
            before trading and execution. Trading can take place via mobile devices or laptops, allowing
            more flexibility, freedom and trading on the go.</li>
        <li><span>-</span><span class="t_bold">Quick execution:</span>allow traders to place order for execution
            instantly which reduces listing
            time and facilitate complicated general and legal documentation process. Trading is time-
            sensitive, fluctuation of price and profit / loss could be made within seconds.</li>
        <li><span>-</span><span class="t_bold">Real time monitoring:</span>allows traders to track investment portfolio
            with a single click. Traders
            could also view all UT bid and ask transactions to analyze the trading pattern for each project.</li>
        <li><span>-</span><span class="t_bold">Access to data and tool for investment analysis:</span>allows traders to
            get access to pools of data
            and tools for investment analysis purpose. Traders are able to view real time orders, market
            prices and built-in portfolio valuation function for investment decision.</li>
        <li><span>-</span><span class="t_bold">Knowledge:</span>online trading platform features analytic tools for
            indicators to users.</li>

    </ul>

    <h3 class="sec_heading mt-2">Article 27: Risks Associated with Online Trading</h3>
    <ul>
        <li><span>-</span><span class="t_bold">Possible loss on investment:</span>the return on investment or
            performance of UT is subject to
            change due to trading performance. Traders could incur loss as a result of own trading
            decision and there is no guarantee on positive returns.</li>
        <li><span>-</span><span class="t_bold">Technical error:</span>simplified trading function allows room for quick
            profits and technical
            missteps. A single click without verification could result in profit / loss.</li>
        <li><span>-</span><span class="t_bold">Internet dependent:</span>If the internet connection is interrupted,
            traders could lose out on a
            potentially important or lucrative trade.</li>
        <li><span>-</span><span class="t_bold">Addictive nature:</span>Traders could go from investing to gambling since
            trading process has been
            simplified.</li>
    </ul>

    <h2 class="prim_heading" style="font-size: 20px;">VIII. Suspension, Termination and Cancelation of UT Swap account
    </h2>
    <h3 class="sec_heading mt-2">Article 28: Suspension of UT Swap account</h3>
    <p><span style="color: #0d0d72; font-size: 1.2rem;">Zillion United </span>shall be entitled to temporarily suspend
        UT trading the following situations:</p>
    <ul>
        <li><span class="mr-2">-</span>unfair or abnormal trading pattern has been detected; or</li>
        <li><span class="mr-2">-</span>orders from the competent authorities or court; or</li>
        <li><span class="mr-2">-</span>other conditions deem relevant and necessary, to be decided by <span style=" color: #0d0d72; font-size: 1.2rem;">Zillion United.</span></li>
    </ul>
    <p>Traders shall bear in mind that suspension of trading may be held without prior to notification. Once
        suspension is made, it shall never last longer than 03 days. <span style=" color: #0d0d72; font-size: 1.2rem;">Zillion United</span> will notify to traders the
        schedule that market is set to resume.</p>
    <h3 class="sec_heading mt-2">Article 29: Termination of UT Swap account</h3>
    <p><span style=" color: #0d0d72; font-size: 1.2rem;">Zillion United</span> shall be entitled to terminate UT Swap
        account in the following situations:</p>
    <ul>
        <li><span class="mr-2">-</span>inactive account for the past 12months;</li>
        <li><span class="mr-2">-</span>repeated violations of operational rules by the account holder;</li>
        <li><span class="mr-2">-</span>request by the account holder;</li>
        <li><span class="mr-2">-</span>orders from the competent authorities or court; or</li>
        <li><span class="mr-2">-</span>other conditions required or permitted by the laws.</li>

    </ul>
    <p>Prior to termination, notification may be issued to the account holder, provided that it is relevant
        and necessary to do so.</p>
    <p>In case the account is being terminated, balance that remains in the account, if any, shall be returned
        to the account holder, except required otherwise by the competent authorities, court or applicable
        laws.</p>
    <h3 class="sec_heading mt-2">Article 30: Violation and disciplinary actions (notification, warning, administrative
        action, penalty)</h3>
    <p>In case traders violate any term of these operational rules, <span style=" color: #0d0d72; font-size: 1.2rem;">Zillion United</span> is entitled undertake
        actions as follows:</p>
    <ul>
        <li><span class="mr-2">-</span>Notification; and/or</li>
        <li><span class="mr-2">-</span>Warning; and /or</li>
        <li><span class="mr-2">-</span>Administrative actions (example suspension or termination of account etc.); and
            /or</li>
        <li><span class="mr-2">-</span>Appropriate penalty allowed by laws; and/or</li>
        <li><span class="mr-2">-</span>Legal action(s), if deems necessary.</li>
    </ul>
    <h2 class="prim_heading" style="font-size: 20px;">IX. Miscellaneous</h2>
    <h3 class="sec_heading mt-2">Article 31: Disclaimers and waivers</h3>
    <p>Traders shall bear in mind that property or project that is being traded has been studied through a
        legality and due diligence process before its entry into market. However, it is natural that such
        property on the later stage become defective due to acts of God, war, terrorism, civil disturbance,
        court order or other natural disaster likes beyond reasonable control of <span style=" color: #0d0d72; font-size: 1.2rem;">Zillion United</span>. In such
        7 | Pagesituations, <span style=" color: #0d0d72; font-size: 1.2rem;">Zillion United</span> shall not be held
        responsible for any loss of capital or profit of trader as
        result of any event occurred outside of its control or caused by any force majeure event. Traders
        shall waive all claims and hold harmless against <span style=" color: #0d0d72; font-size: 1.2rem;">Zillion
            United</span>, its directors and staff for any and all
        losses of capital or profit, liabilities, demands and expenses (including attorney’s fee) as result of
        any event occurred as described above.</p>
    <h3 class="sec_heading mt-2">Article 32: Applicable laws and dispute resolutions</h3>
    <p>These operational rules are governed by the law of the Kingdom of Cambodia. In the event of any
        dispute arising out of or in connection with the interpretation of these operation rules, including
        question regarding the existence, validity or termination, such disputes shall be resolved peacefully
        and in the best possible capacity for a maximum of sixty (60) Business Days. In the event that a
        peaceful settlement is not feasible or does not achieve results, either party may refer the case to
        competent institution or court.</p>
    <h3 class="sec_heading mt-2">Article 33: Amendment of operational rules</h3>
    <p>These operational rules may be amended from time to time upon the necessity of market conditions,
        requirement by competent authorities and applicable laws. Zillion United will notify traders about
        future amendment of these operational rules, if any.</p>
    <h3 class="sec_heading mt-2">Article 34: Inquiry</h3>
    <p>Any inquiry regarding these operational rules can be reach:</p>
    <table class="table mobile_table">
        <tbody><tr>
            <td><span style="color: #0d0d72;"><i class="fas fa-archive"></i></span> Company</td>
            <td><span style=" color: #0d0d72; font-size: 1.2rem;">: Zillion United Co., Ltd</span></td>
        </tr>
        <tr>
            <td><span style="color: #0d0d72;"><i class="fas fa-archive"></i></span> Address</td>
            <td>: Samai Square #2, Khan Toul Kork, Phnom Penh, Cambodia</td>
        </tr>
        <tr>
            <td><span style="color: #0d0d72;"><i class="fas fa-archive"></i></span> Telephone</td>
            <td>: 078 880 111</td>
        </tr>
        <tr>
            <td><span style="color: #0d0d72;"><i class="fas fa-archive"></i></span> Email address</td>
            <td>: info@zillionunited.io</td>
        </tr>
        <tr>
            <td><span style="color: #0d0d72;"><i class="fas fa-archive"></i></span> Website address</td>
            <td><a href="https://zillionunited.io/" target="_blank">www.zillionunited.io</a></td>
        </tr>
    </tbody></table>
</section>"""
}