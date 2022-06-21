package com.zillennium.utswap.screens.kyc.kycFragment.declarationScreen

import android.content.Intent
import android.text.TextWatcher
import android.view.View
import android.widget.CheckBox
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
                btnAccept.isEnabled = false
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
                txtEAgreement.text =
                    """ព្រះរាជាណាចក្រកម្ពុជា ជាតិ សាសនា ព្រះមហាក្សត្រ កិច្ចព្រមព្រៀងចូលរួមទិញ-លក់ឯកតាមូលនិធិតាមប្រព័ន្ធអេឡិចត្រូនិក កិច្ចព្រមព្រៀងចូលរួមទិញលក់ឯកតាមូលនិធិតាមប្រព័ន្ធអេឡិកត្រូនិកនេះ(ហៅថា “កិច្ចព្រមព្រៀង”) 
                    |ត្រូវបានធ្វើឡើងនៅ ថ្ងៃទី………ខែ………ឆ្នាំ២០២២ ដោយ និង រវាង៖ ម្ចាស់គម្រោង៖ ក្រុមហ៊ុន ហ្សីលាន យូណាយធីត ឯ.ក (ZILLION UNITED CO., LTD.) ជាក្រុមហ៊ុនបានចុះបញ្ជីពាណិជ្ជកម្មលេខ០០០០៩៨២៣ ចុះថ្ងៃទី០២ ខែវិច្ឆិកា ឆ្នាំ២០២១ ស្របតាមច្បាប់នៃព្រះរាជាណាចក្រកម្ពុជា មានអាសយដ្ឋានចុះបញ្ជីស្ថិតនៅSAMAI SQUARE អគារលេខ២ ជាន់ផ្ទាល់ដី និងជាន់ទី១ បន្ទប់លេខ១៣ និង១៣A ផ្លូវលេខ៣៣៧ ភូមិ៦ សង្កាត់បឹងកក់១ ខណ្ឌទួលគោក រាជធានីភ្នំពេញ តំណាងដោយ លោក បុង រ៉ាយ៉ានិក ជាអភិបាល តទៅហៅថាជាម្ចាស់គម្រោង (ភាគី “ក”) ។ និង ភាគីអ្នកចូលរួមវិនិយោគ៖ លោក/ លោកស្រី ………………………………… កើតនៅថ្ងៃទី ខែ ឆ្នាំ កាន់អត្តសញ្ញាណប័ណ្ណលេខ មានសុពលភាពចាប់ពីថ្ងៃទី ខែ ឆ្នាំ ដល់ថ្ងៃទី ខែ ឆ្នាំ មានអាសយដ្ឋានស្ថិតនៅផ្ទះលេខ ផ្លូវ ភូមិ សង្កាត់ ខណ្ឌ រាជធានី/ខេត្ត តទៅហៅថាអ្នកចូលរួមវិនិយោគ (ភាគី “ខ”) ។
                    | ភាគី “ក” និង ភាគី “ខ” តទៅអាចហៅថាដោយឡែកពីគ្នាថា “ភាគី” និង ហៅរួមថា “គូភាគី” (មានភ្ជាប់អត្តសញ្ញាណប័ណ្ណរបស់គូភាគីក្នុងឧបសម្ព័ន្ធ)។ 
                    | បុព្វកថា ដោយហេតុថា៖ 
                    | ១. ភាគី “ក” បានបង្កើតគម្រោងលក់ទិញឯកតាមូលនិធិលើអលចនទ្រព្យ (គម្រោង) ភាគី“ក” មានបំណងបើកឱ្យសមាជិកនៃក្រុមហ៊ុនបុត្រសម្ព័ន្ធនិងភាគីពាក់ព័ន្ធចូលរួមទិញគម្រោងឯកតាមូលនិធិនេះ ក្នុងគោលដៅដើម្បីទទួលបានប្រាក់ចំណេញ។ 
                    | ២. ភាគី “ក” បានបង្កើតកម្មវិធីអេឡិកត្រូនិកដើរលើប្រព័ន្ធអិនធ័រណែតដែលមានឈ្មោះថា “យូធីស្វេប (UT Swap)” ឬ “យូធីស្វេបផ្លេតហ្វម (UT Swap platform)” សម្របសម្រួល ប្រតិបត្តិការក្នុងគម្រោងទិញលក់ជាឯកតាមូលនិធិលើអចលនទ្រព្យ។ 
                    | ៣. ភាគី “ខ” មានបំណងទិញចំណែកឯកតាមូលនិធិ នៃគម្រោងនេះក្នុងឋានៈជាអ្នកចូលរួមវិនិយោគ ស្របតាមលក្ខខណ្ឌកំណត់ក្នុងកិច្ចព្រមព្រៀងនេះ។ 
                    | អាស្រ័យហេតុនេះ គូភាគីបានព្រមព្រៀងគ្នានូវលក្ខខណ្ឌដូចតទៅ៖ 
                    | ប្រការ ១ ៖ កម្មវត្ថុនៃកិច្ចព្រមព្រៀង ភាគី “ខ” យល់ព្រមទិញលក់ឯកតាមូលនិធិលើគម្រោងដែលគ្រប់គ្រងដោយភាគី “ក” ដោយការរួមទិញលក់ចំណែកឯកតាមូលនិធិនៃគម្រោងអចលទ្រព្យនេះដែលត្រូវធ្វើឡើងតាមប្រព័ន្ធអេឡិកត្រូនិកឬ ហៅថាកម្មវិធីទិញលក់ចលនទ្រព្យតាមប្រពន្ធ័អេឡិកត្រូនិកដែលមានឈ្មោះថា “យូធីស្វេប (UT Swap)” ក្នុងឋានៈជាអ្នកចូលរួមវិនិយោគ។
                    | ប្រការ ២ ៖ រយៈពេលនៃកិច្ចព្រមព្រៀង កិច្ចព្រមព្រៀងនេះមានសុពលភាពចាប់ពីកាលបរិច្ឆេទ ដែលគូភាគីបានផ្តិតស្នាមមេដៃខាងក្រោម ហើយការស្នើសុំបើកគណនីវិនិយោគត្រូវបានឯកភាពពីភាគី “ក” កិច្ចព្រមព្រៀងនេះ នឹងត្រូវបញ្ចប់សុពលភាពនៅពេល ភាគី“ខ” ស្នើសុំបិទគណនីវិនិយោគ ឬដោយសារមូលហេតុផ្សេងៗទៀត (ហៅថា “ រយៈពេលកិច្ចព្រមព្រៀង ”)។
                    | ប្រការ ៣ ៖ សិទ្ធិ និងកាតព្វកិច្ចរបស់គូភាគី ៣.១ ភាគី “ក” ធានាថា អចលនវត្ថុដែលខ្លួនយកមកវិនិយោគ គឺជាអចលនវត្ថុដែលភាគី“ក” មានសិទ្ធិស្របច្បាប់ក្នុងការធ្វើគម្រោងវិនិយោគលើអចលនវត្ថុនេះ។ ក្នុងករណីអចលនវត្ថុនេះនៅមានជាប់វិវាទ នោះភាគី “ក” ជាចេញមុខដោះស្រាយដោយឯកឯង ដោយមិនទាក់ទងនឹងភាគី “ខ” ឡើយ។ ៣.២ ភាគី “ខ” អាចចូលរួមទិញលក់ឯកតាមូលនិធិក្នុងគម្រោងបាន តាមរយៈការបើកគណនីវិនិយោគផ្ទាល់ខ្លួនក្នុងប្រពន្ធ័អេឡិកត្រូនិកដែលមានឈ្មោះថា “យូធីស្វេប (UT Swap)” ។ ភាគី “ខ” ត្រូវបំពេញព័ត៌មានផ្ទាល់ខ្លួនដោយភា្ជប់ជាមួយអត្តសញ្ញាណប័ណ្ណ ឬលិខិតឆ្លងដែនក្នុងប្រព័ន្ធដោយខ្លួនឯង។ ៣.៣ ភាគី “ខ” ទទួលស្គាល់និងយល់ច្បាស់ពីនិតិវិធីនៃការស្នើសុំ ការទិញលក់ ព្រមទាំងព័ត៌មានលម្អិតផ្សេងៗ នឹងមានចែងក្នុងគោលការណ៍ប្រតិបត្តិការ (Operating Rule) មគ្គុទេស៍ ណែនាំ (Guidebook) ក្នុងការចូលរួមទិញឯកតាមូលនិធិដែលផ្តល់ជូនដោយភាគី “ក” ឬអ្នកតំណាង។ ៣.៤ ភាគី “ខ” បានដឹង និងទទួលស្គាល់ថាការទិញលក់ឯកតាមូលនិធិតាមរយៈប្រព័ន្ធអេឡិកត្រូនិកនេះគឺជាការទទួលខុសត្រូវលើហានិភ័យ និងផលចំណេញផ្សេងៗ ដោយអាស្រ័យលើតម្លៃទីផ្សារ (Market Price) នៃឯកតាមូលនិធិក្នុងគម្រោងនីមួយៗ។ ៣.៥ ភាគី “ក” ត្រូវផ្តល់ជូននូវឯកសារដែលពាក់ព័ន្ធក្នុងការចូលរួមទិញលក់ឯកតាមូលនិធិតាមរយៈ ប្រព័ន្ធអេឡិកត្រូនិកនេះជូនភាគី “ខ” រួមមានគោលការណ៍ប្រតិបត្តិការ (Operating Rule) មគ្គុទេស៍ណែនាំក្នុងការចូលរួមទិញឯកតាមូលនិធិ (Guidebook) និងព័ត៌មានលម្អិតនៃគម្រោងនីមួយៗ ។ល។
                    | ប្រការ ៤ ៖ កម្មសិទិ្ធលើគម្រោង ភាគី “ក” គឺជាម្ចាស់សិទ្ធិស្របច្បាប់ក្នុងការធ្វើគម្រោងវិនិយោគលើអចលនវត្ថុនេះ និងភាគី “ខ”ម្ចាស់សិទិ្ធលើចំណែកឯកតាមូលនិធិ ដែលមានទំហំសមាមាត្រទៅនឹងចំនួននៃចំណែកឯកតាមូលនិធិដែលខ្លួនបានទិញ។
                    | ប្រការ ៥ ៖ ការព្រមព្រៀងជាពិសេសពីអ្នកចូលរួមវិនិយោគ ៥.១ ពហុភាពនៃអ្នកចូលរួមវិនិយោគ៖ ភាគី “ខ” បានដឹងនិងយល់ព្រមថា មានតតិយជនផ្សេងទៀតជាច្រើន ជាអ្នកចូលរួមទិញនៅក្នុងគម្រោងនេះ ហើយដែលពួកគេទទួលបានសិទិ្ធនិងកាតព្វកិច្ចដូចភាគី “ខ” ផងដែរ។ ៥.២ គោលដៅនៃការចូលរួមវិនិយោគ៖ ភាគី “ខ” ព្រមព្រៀងថា ការចុះកិច្ចព្រមព្រៀងនេះ គឺជាការចូលរួមទិញលក់ឯកតាមូលនិធិលើអចលនទ្រព្យហើយ ភាគី “ខ” មិនអាចទាមទារកាត់ឈ្មោះលើអចលនទ្រព្យ ក្នុងទំហំណាមួយបានឡើយ។ ៥.៣ កម្មសិទិ្ធបញ្ញា៖ អ្នកចូលរួមវិនិយោគទាំងអស់យល់ព្រមថា រាល់ប្រតិបត្តិការនៃការជួញដូរនិងការប្រើប្រាស់កម្មវិធីទិញលក់ឯកតាមូលនិធិលើ អចលនទ្រព្យតាមប្រព័ន្ធអេឡិកត្រូនិកនេះ ជាកម្មសិទ្ធិបញ្ញារបស់ ក្រុមហ៊ុន ហ្សីលាន យូណាយធីត ឯ.ក ដោយរួមបញ្ចូលទាំងទម្រង់ ខ្លឹមសារ រូបមន្ត អត្ថបទព័ត៌មាន រូបភាព សម្លេង វិដេអូ ម៉ាក ប្រកាសនីយបត្រតក្កកម្ម ឬ វត្ថុផ្សេងទៀតត្រូវបានការពារដោយច្បាប់ ស្តីពីសិទិ្ធអ្នកនិពន្ធនិងសិទ្ធិប្រហាក់ប្រហែលនៃព្រះរាជាណាចក្រកម្ពុជា និង/ឬ សិន្ធិសញ្ញា/អនុសញ្ញាអន្តរជាតិ ស្តីពីកម្មសិទិ្ធបញ្ញាដែលទទួលស្គាល់ដោយរាជរដ្ឋាភិបាលកម្ពុជា។ 
                    | ប្រការ ៦ ៖ ប្រសិទ្ធភាពនៃកិច្ចព្រមព្រៀង ប្រសិនបើប្រការណាមួយនៃកិច្ចព្រមព្រៀងនេះត្រូវបានចាត់ទុកថា ខុសច្បាប់ មោឃភាព ឬមិនអាចអនុវត្តបានក្រោមច្បាប់ដែលមាននៅជាធរមាននាពេលបច្ចុប្បន្ន ឬអនាគត ប្រការនោះត្រូវ លុបចេញទាំងស្រុង ហើយត្រូវបកស្រាយថា ប្រការដែលមិនស្របច្បាប់ មោឃភាព ឬមិនអាចអនុវត្តបាននោះមិនមាននៅក្នុងកិច្ចព្រមព្រៀងនេះ ហើយប្រការផ្សេងទៀតនៃកិច្ចព្រមព្រៀងនេះ ត្រូវអនុវត្ត បើមិនប៉ះពាល់ដោយភាពមិនស្របច្បាប់ មោឃភាព ឬមិនអាចអនុវត្តបាននៃប្រការនោះ។ ជំនួសឱ្យប្រការដែលមិនស្របច្បាប់ មោឃភាព ឬមិនអាចអនុវត្តបាន ត្រូវបន្ថែមដោយស្វ័យប្រវត្តិក្នុងកិច្ចព្រមព្រៀងនេះនូវប្រការ ដែលមានភាពស្របច្បាប់ មានសុពលភាព ឬអាចអនុវត្តបាន។ 
                    | ប្រការ ៧ ៖ ភាសា និង ចំនួននៃកិច្ចព្រមព្រៀង កិច្ចព្រមព្រៀងនេះត្រូវបានធ្វើឡើងជាភាសាខ្មែរ និងភាសាអង់គ្លេសចំនួន ០៣(បី)ច្បាប់ដើម មានតម្លៃស្មើគ្នាចំពោះមុខច្បាប់។ ករណីមានការបកស្រាយខុសគ្នារវាងភាសាទាំងពីរ ត្រូវយកភាសាខ្មែរជាគោល។ ច្បាប់ដើមនីមួយៗនៃកិច្ចព្រមព្រៀងនេះត្រូវរក្សាទុកដូចខាងក្រោម៖ - ភាគី “ក”............................................................................ ០១ច្បាប់ - ភាគី “ខ”.......................................................................... ០១ច្បាប់ - មេធាវី ............................................................................ ០១ច្បាប់ ភាគីបានអាន និងយល់ព្រមតាមគ្រប់លក្ខខណ្ឌ និងខ្លឹមសារនេះទាំងស្រុង ព្រមទាំងធ្វើការចុះហត្ថលេខា ឬផ្ដិតស្នាមមេដៃដើម្បីទុកជា សក្ខីភាពចំពោះមុខច្បាប់ ។ ស្នាមមេដៃស្ដាំភាគី“ក” ហត្ថលេខា និងត្រា ភាគី“ខ” លេខ៖...................................................... បានឃើញ និងបញ្ជាក់ថា ៖ គូភាគីពិតជាបានព្រមព្រៀងគ្នាផ្ដិតស្នាមមេដៃស្ដាំនៅចំពោះមុខមេធាវី ថ្ងៃ............................... ខែ.............. ឆ្នាំឆ្លូវ ត្រីស័ក ព.ស ២៥៦៥ ធ្វើនៅរាជធានីភ្នំពេញ ថ្ងៃទី.......... ខែ.......... ឆ្នាំ២០២២ មេធាវី""".trimMargin()
                //Declaration check box
                cbDeclaration.setOnClickListener {
                    val declarationCheck = cbDeclaration.isEnabled
                    val check = cbDeclaration.isChecked
                    if (declarationCheck == check) {
                        svDeclaration.visibility = View.GONE
                        svEAgreement.visibility = View.VISIBLE
                        cbEAgreement.isEnabled = true
                    } else {
                        cbEAgreement.isEnabled = false
                        svDeclaration.visibility = View.VISIBLE
                        svEAgreement.visibility = View.GONE
                    }
                }
                cbEAgreement.setOnClickListener{
                    val eAgreementCheck = cbEAgreement.isEnabled
                    val checkAgrement = cbEAgreement.isChecked
                    if (eAgreementCheck == checkAgrement){
                        svEAgreement.visibility = View.VISIBLE
                        btnAccept.isEnabled = true
                    }
                    else{
                        btnAccept.isEnabled = false

                    }
                }
            }


        } catch (error: Exception) {
            // Must be safe
        }
    }
}