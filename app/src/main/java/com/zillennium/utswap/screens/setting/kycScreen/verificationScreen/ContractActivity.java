package com.zillennium.utswap.screens.setting.kycScreen.verificationScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.zillennium.utswap.R;

public class ContractActivity extends AppCompatActivity {

    private ImageView imgBack;
    private TextView txtContent;
    private MaterialButton btnAccept;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kyc_contract);

        initView();

        initAction();
    }

    public void initView(){
        imgBack = findViewById(R.id.img_back);
        txtContent = findViewById(R.id.txt_content);
        btnAccept = findViewById(R.id.btn_accept);
    }

    public void initAction(){
        imgBack.setOnClickListener(view -> {
            finish();
        });

        btnAccept.setOnClickListener(view -> {
            Intent intent =  new Intent(this, KycApplication.class);
            startActivity(intent);
        });

        txtContent.setText("ព្រះរាជាណាចក្រកម្ពុជា\n" +
                "ជាតិ   សាសនា   ព្រះមហាក្សត្រ\n" +
                "KINGDOM OF CAMBODIA\n" +
                "NATION RELIGION KING\n" +
                "\n"+
                "កិច្ចព្រមព្រៀងចូលរួមលក់ទិញឯកតាមូលនិធិនៃគម្រោងអចលនវត្ថុ\n" +
                " តាមរយៈប្រព័ន្ធអេឡិកត្រូនិក\n" +
                "AGREEMENT ON SALE AND PURCHASE OF REAL ESTATE PROJECT BASED UNIT OF TRUST \n" +
                "កិច្ចព្រមព្រៀងចូលរួមលក់ទិញឯកតាមូលនិធិនេះ(ហៅថា “កិច្ចព្រមព្រៀង”) ត្រូវបានធ្វើឡើងនៅថ្ងៃទី..........ខែ..........ឆ្នាំ២០២២ ដោយ និង រវាង៖\n" +
                "This Agreement on Sale and Purchase of Real Estate Project Based Unit of Trust (hereinafter called the “Agreement”) is concluded on …………………… by and between:\n" +
                "អ្នកបង្កើតគម្រោង / PROJECT OWNER\n" +
                "ក្រុមហ៊ុន ហ្សីលាន យូណាយធីត ឯ.ក (ZILLION UNITED CO., LTD.) ជាក្រុមហ៊ុនបានចុះបញ្ជីពាណិជ្ជកម្មលេខ ០០០០៩៨២៣ ចុះថ្ងៃទី០២ ខែវិច្ឆិកា ឆ្នាំ២០២១ ស្របតាមច្បាប់នៃព្រះរាជាណាចក្រកម្ពុជា មានអាសយដ្ឋានចុះបញ្ជីស្ថិតនៅ SAMAI SQUARE អគារលេខ២ ជាន់ផ្ទាល់ដី និងជាន់ទី១ បន្ទប់លេខ១៣ និង១៣A ផ្លូវលេខ៣៣៧ ភូមិលេខ៦ សង្កាត់បឹងកក់១ ខណ្ឌទួលគោក រាជធានីភ្នំពេញ តំណាងដោយ លោក កាង សុធី ជាប្រធានក្រុមប្រឹក្សាភិបាល តទៅហៅថា ជាម្ចាស់គម្រោង ឬ (ភាគី “ក”) ។\n" +
                "ZILLION UNITED CO., LTD., a duly registered under number 00009823 dated 02 November 2021 under laws of the Kingdom of Cambodia, having register address at SAMAI SQUARE Building No. 2 ground floor and 1st floor, room #13 and #13A, Street #337, Village 6, Boeung Kork I Commune, Toul Kork District, Phnom Penh, Cambodia, and represented by Mr. KANG SOTHY, Chairman, hereinafter is called Project establisher or (“Party A”).\n" +
                "\tនិង/And\t\n" +
                "ភាគីអ្នកចូលរួមវិនិយោគ/TRADER or INVESTOR\n" +
                "លោក/លោកស្រី .............................. កើតនៅថ្ងៃទី..........ខែ..........ឆ្នាំ.......... កាន់អត្តសញ្ញាណប័ណ្ណសញ្ជាតិខ្មែរ/លិខិតឆ្លងដែនលេខ ............................. មានសុពលភាពចាប់ពីថ្ងៃទី..........ខែ..........ឆ្នាំ.......... រហូតដល់ថ្ងៃទី..........ខែ..........ឆ្នាំ......... មានអាសយដ្ឋានស្ថិតនៅ ផ្ទះលេខ..........ផ្លូវលេខ..........ភូមិ..........សង្កាត់..........ខណ្ឌ..........រាជធានី/ខេត្ត.......... តទៅហៅថា អ្នកចូលរួមវិនិយោគ ឬ (ភាគី  “ខ”) ។   \n" +
                "Mr./Ms. …………………………, born on ………………………., holding Cambodia ID Card/Passport No. …………………., validity period from ………………. to ………………………. having address ………….…………………………………………. …………………………………………, hereinafter is called Trader/Investor (or “Party B”).\n" +
                "\n" +
                "\n" +
                "ភាគី “ក” និង  ភាគី “ខ” តទៅអាចហៅថាដោយឡែកពីគ្នាថា  “ភាគី” និង ហៅរួមថា “គូភាគី” (មានភ្ជាប់អត្តសញ្ញាណប័ណ្ណរបស់គូភាគីក្នុងឧបសម្ព័ន្ធ) ។ \n" +
                "Each of the Parties to this Agreement hereinafter is called a “Party” and collectively is called “Parties”, (the company documents and identity of parties shall be attached in Annex of this agreement).\n" +
                "បុព្វកថា\n" +
                "ដោយហេតុថា១.\tភាគី “ក” បានទទួលសិទ្ធិក្នុងការបង្កើតគម្រោងលក់ទិញអចលនវត្ថុ តាមរយៈការបង្កើតចំនួនឯកតាមូលនិធិជាក់លាក់លើគម្រោង ហើយមានបំណងចង់បើកឱ្យអ្នកចូលរួមវិនិយោគចូលរួមលក់ទិញគម្រោងឯកតាមូលនិធិនេះ ក្នុងគោលដៅដើម្បីទទួលបានប្រាក់ចំណេញ ។\n" +
                "២.\tភាគី “ក” បានបង្កើតកម្មវិធីអេឡិកត្រូនិកតាមរយៈប្រព័ន្ធអិនធ័រណែត ដែលមានឈ្មោះថា “យូធីស្វាប” ដើម្បីសម្របសម្រួលប្រតិបត្តិការលក់ទិញឯកតាមូលនិធិលើគម្រោងអចលនវត្ថុនោះ ។\n" +
                "៣.\tភាគី “ខ” មានបំណងលក់ទិញចំណែកឯកតាមូលនិធិ នៃគម្រោងនោះ ក្នុងឋានៈជាអ្នកចូលរួមវិនិយោគ ស្របតាមលក្ខខណ្ឌកំណត់ក្នុងកិច្ចព្រមព្រៀងនេះ ។\n" +
                "ហេតុដូច្នេះ គូភាគីបានព្រមព្រៀងគ្នាស្របតាម លក្ខខណ្ឌដូចតទៅ៖\n" +
                "\n" +
                "RECITALS\n" +
                "WHEREAS:\n" +
                "\n" +
                "1.\tParty “A” has been authorized to establish Real Estate Project Based Unit of Trust (“UT”); and Party “A” allows interested investors/ traders to participate in trading of UT in order to obtain benefits.\n" +
                "2.\tParty “A” established an electronic trading platform known as “UT Swap” in order to facilitate trading of Real Estate Project Based Unit of Trust. \n" +
                "3.\tParty “B” is willing to participate in trading of UT under the terms and conditions contained in this Agreement.\n" +
                "\n" +
                "Therefore, Parties voluntarily established this Agreement under the following terms and conditions:\n" +
                "ប្រការ ១ ៖ \tកម្មវត្ថុនៃកិច្ចព្រមព្រៀង\n" +
                "ភាគី “ខ” យល់ព្រមចូលរួមលក់ទិញឯកតាមូលនិធិលើគម្រោងអចលនវត្ថុ ដែលគ្រប់គ្រងដោយភាគី “ក” ដោយការចូលរួមលក់ទិញចំណែកឯកតាមូលនិធិនៃគម្រោងនេះ ត្រូវធ្វើឡើងតាមប្រព័ន្ធអេឡិកត្រូនិក ឬហៅថា កម្មវិធីលក់ទិញអចលនទ្រព្យតាមប្រព័ន្ធអេឡិកត្រូនិកដែលមានឈ្មោះថា “យូធីស្វាប (UT Swap)” ក្នុងឋានៈជាអ្នកចូលរួមវិនិយោគ ហើយត្រូវគោរពតាមលក្ខខណ្ឌដូចមានចែងក្នុងកិច្ចព្រមព្រៀងនេះ និងបទបញ្ញាត្ដិអមផ្សេងៗរួមមាន គោលការណ៍ប្រតិបត្តិការ មគ្គុទេស៍ណែនាំ  ក៏ដូចជាលក្ខខណ្ឌនៃការប្រើប្រាស់  និងគោលការឯកជនភាព  ។\n" +
                "\n" +
                "\n" +
                "ARTICLE 1:\tOBJECTIVE OF AGREEMENT\n" +
                "Party “B” agrees to participate in Real Estate Project Based Unit of Trust under supervision of Party “A”. The sale and purchase of UT shall be conducted via the electronic platform so-called “UT Swap”. Party “B” shall comply with the terms and conditions contained in this agreement and other binding laws and regulations including Operational Rules, Guidebook, Terms and Conditions, and Privacy Policy of “UT Swap”.\n" +
                "ប្រការ ២ ៖\tរយៈពេលនៃកិច្ចព្រមព្រៀង \n" +
                "កិច្ចព្រមព្រៀងនេះមានសុពលភាពចាប់ពីកាលបរិច្ឆេទ ដែលគូភាគីបានផ្តិតស្នាមមេដៃ ឬចុះហត្ថលេខាខាងក្រោម ។ កិច្ចព្រមព្រៀងនេះ នឹងត្រូវបញ្ចប់សុពលភាពនៅពេល ភាគី “ខ” ស្នើសុំបិទគណនីវិនិយោគ ឬដោយសារមូលហេតុផ្សេងៗទៀតដូចមានចែងក្នុងគោលការណ៍ប្រតិបត្តិការ  មគ្គុទេស៍ណែនាំ  ក៏ដូចជាលក្ខខណ្ឌនៃការប្រើប្រាស់(តទៅហៅថា “រយៈពេលកិច្ចព្រមព្រៀង”)។\n" +
                "\n" +
                "ARTICLE 2:\tDURATION OF AGREEMENT\n" +
                "\n" +
                "This agreement is valid from the date of thumbprint or signature of this agreement. This agreement may be terminated at the request by Party “B” or due to other reasons as stated under Operational Rules, Guidebook and Terms and Conditions (so-called Duration of Agreement).\n" +
                "\n" +
                "ប្រការ ៣ ៖   \tសិទ្ធិ និងកាតព្វកិច្ចរបស់ភាគី “ក”\n" +
                "៣.១\tភាគី “ក” ធានាថា អចលនវត្ថុដែលខ្លួនយកមកបង្កើតជាឯកតាមូលនិធិដើម្បី ឱ្យភាគី “ខ” លក់ទិញ  តាមរយៈយូធីស្វាប គឺជាអចលនវត្ថុស្របច្បាប់ ដែលភាគី “ក” បានទទួលបានសិទ្ធិពេញលេញ ក្នុងការបង្កើតឯកតាមូលនិធិ សម្រាប់លក់ទិញ ។ ក្នុងករណីអចលនវត្ថុនេះនៅមានជាប់វិវាទ នោះភាគី “ក” ជាអ្នកចេញមុខដោះស្រាយដោយឯកឯង ដោយមិនទាក់ទងនឹងភាគី “ខ” ឡើយ។\n" +
                "៣.២\tភាគី “ក” ត្រូវធានាផ្តល់ជូននូវឯកសារដែលពាក់ព័ន្ធក្នុងការចូលរួមទិញលក់ឯកតាមូលនិធិតាមរយៈប្រព័ន្ធអេឡិកត្រូនិកនេះជូន ភាគី “ខ” រួមមានគោលការណ៍ប្រតិបត្តិការ  មគ្គុទេស៍ណែនាំលក្ខខណ្ឌនៃការប្រើប្រាស់ក្នុងការចូលរួមទិញឯកតាមូលនិធិ និងព័ត៌មានលម្អិតនៃគម្រោងនីមួយៗ ដើម្បីឱ្យ ភាគី “ខ” ទទួលបាននូវព័ត៌មានពេញលេញ មុនពេញសម្រេចចិត្ដធ្វើការលក់ទិញ ។ល។\n" +
                " ៣.៣\tភាគី “ក” មានសិទ្ធិចាត់វិធានការរដ្ឋបាលនានា  រួមមានការកំហិតការជួញដូរ ឬ ការបិទការជួញដូរ ឬការចេញលិខិតប្រមានចំពោះអតិថិជន ប្រសិនបើមានស្ថានភាពណាមួយដូចជា បានលាក់បាំង ឬការផ្តល់ព័ត៌មានមិនពិត ការមិនអនុវត្តតាមប្រការណាមួយនៃកិច្ចព្រមព្រៀងនេះ ឬ ភាគី “ក” យល់ថាមានភាពចាំបាច់ក្នុងការបង្ការស្ថានភាពជួញដូរមិនប្រក្រតី ឬមានការអនុវត្តការជួញដូរឯកតាមូលនិធិមិនត្រឹមត្រូវ ឬល្មើសច្បាប់ ។\n" +
                "៣.៤\tភាគី “ក” ត្រូវធានាប្រសិទ្ធិភាពនៃកម្មវិធី UT Swap ហើយត្រូវជូនដំណឹងភ្លាមៗដល់ភាគី “ខ” ក្នុងករណីបានដឹងថា មានបុគ្គលដែលមិនមានសិទ្ធិបញ្ជាលក់ទិញប្រើប្រាស់ព័ត៌មានសម្ងាត់របស់ ភាគី “ខ” ដើម្បីបញ្ជាលក់ទិញ ឬធ្វើសកម្មភាពផ្សេងៗ ។\n" +
                "\n" +
                "ARTICLE 3:\tRIGHTS AND OBLIGATIONS OF PARTY “A”\n" +
                "\n" +
                "3.1\tParty “A” guarantees that the Real Estate Project Based Unit of Trust is issued based on actual properties that Party “A” is legally entitled do so. In the event that the property is in dispute, Party “A” shall be solely liable. \n" +
                "3.2\tParty “A” shall provide to Party “B” the documents related to trading of UT through UT Swap including Operational Rules, Guidebook, Terms and Conditions, and the detail information of each project, in order for Party “B” to be fully aware of the project prior to making any decision on trading.\n" +
                "3.3\tParty “A” has the right to take any administrative actions including trading restriction, trading suspension, or warning notification to trader, in the following situations: falsified information provided by trader, breach of this agreement by trader, abnormal trading behavior or illegal trading activity. \n" +
                "3.4\tParty “A” shall ensure transparency in the operation of UT Swap, and shall immediately notify Party “B” in any event that it is aware of any unauthorized access into Party “B”s information or trading activity.  \n" +
                "ប្រការ ៤ ៖   \tសិទ្ធិ និងកាតព្វកិច្ចរបស់ភាគី “ខ”\n" +
                "៤.១\tភាគី “ខ” ទទួលស្គាល់ និងព្រមព្រៀងលើអត្រា និងថ្លៃកម្រៃសេវាពាក់ព័ន្ធនឹងប្រតិបត្ដិការនានាតាមរយៈយូធីស្វាប និងភាគលាភផ្សេងៗ ដែលបានកំណត់នៅក្នុងគោលការណ៍ប្រតិបត្តិការ  និងវិធានផ្សេងៗទៀតដែលមានកំណត់នៅក្នុងកម្មវិធី UT Swap ។\n" +
                "៤.២\tភាគី “ខ” បានអាន និងយល់ច្បាស់អំពីច្បាប់ និងបទប្បញ្ញាត្តិពាក់ព័ន្ធព្រមទាំងលក្ខខណ្ឌទូទៅនៃការជួញដូរឯកតាមូលនិធិនៅក្នុងកម្មវិធីយូធីស្វាប និងធានាអនុវត្តតាមច្បាប់ និងបទប្បញ្ញាត្តិ ព្រមទាំងលក្ខខណ្ឌទូទៅទាំងនេះ ។\n" +
                "៤.៣\tភាគី “ខ” ត្រូវធានាថាសាច់ប្រាក់ ឬហិរញ្ញវត្ថុ ដែលប្រើប្រាស់ក្នុងប្រតិបត្ដិការតាមរយៈយូធីស្វាបគឺមានប្រភពស្របច្បាប់ បើពុំដូច្នេះទេ ខ្លួននឹងទទួលខុសត្រូវចំពោះច្បាប់ដែលមានជាធរមាន តាមលក្ខខណ្ឌដូចមានចែងក្នុងកិច្ចព្រមព្រៀងនេះ ក៏ដូចជាបទបញ្ញាត្ដិអមជាមួយនឹងកិច្ចព្រមព្រៀងនេះ ។ ភាគី “ខ” យល់ព្រមដាក់ដំកល់សាច់ប្រាក់ ឬទ្រព្យសម្បត្តិគ្រប់គ្រាន់សម្រាប់ធានាមុនពេលធ្វើការជួញដូរឯកតាមូលនិធិ ។ \n" +
                "៤.៤\tភាគី “ខ” អាចចូលរួមទិញលក់ឯកតាមូលនិធិក្នុងគម្រោងបាន តាមរយៈការបើកគណនីវិនិយោគផ្ទាល់ខ្លួនក្នុងប្រព័ន្ធអេឡិកត្រូនិក ដែលមានឈ្មោះថា “យូធីស្វាប (UT Swap)” ។ ភាគី “ខ” យល់ព្រមផ្តល់ជូនភាគី “ក” នូវព័ត៌មាន KYC (Know your client) ស្តីពីអត្តសញ្ញាណពិតប្រាកដរបស់ខ្លួននិងអ្នកទទួលផលប្រយោជន៍ពីខ្លួន គោលដៅនៃការជួញដូររបស់ខ្លួន និងព័ត៌មានតម្រូវផ្សេងៗទៀត ។\n" +
                "៤.៥\tភាគី “ខ” ទទួលស្គាល់ និងយល់ច្បាស់អំពី និងឯកភាពលើនីតិវិធីនៃការស្នើសុំការទិញលក់ ព្រមទាំងព័ត៌មានលម្អិតផ្សេងៗ នឹងមានចែងក្នុងគោលការណ៍ប្រតិបត្តិការ  មគ្គុទេស៍ ណែនាំ ក៏ដូចជាលក្ខខណ្ឌនៃការប្រើប្រាស់ និងគោលការណ៍ឯកជនភាព ក្នុងការចូលរួមទិញឯកតាមូលនិធិដែលផ្តល់ជូនដោយភាគី “ក” ឬអ្នកតំណាង។\n" +
                "\n" +
                "ARTICLE 4:\tRIGHTS AND OBLIGATIONS OF PARTY “B”\n" +
                "4.1\tParty “B” acknowledged and agreed on the rate and service charge via UT Swap and other dividends as stated in the Operational Rules and other rules defined in UT Swap. \n" +
                "4.2\tParty “B” had read and clearly understood the laws and regulations, and general conditions of trading via UT Swap, and shall be bound by such regulations.\n" +
                "4.3\tParty “B” shall guarantee that the credit or finance used for trading via UT Swap is derived from legitimate source. Party “B” shall be responsible before the applicable laws relevant to this trading. Party “B” agreed to deposit sufficient amount before trading activity.\n" +
                "4.4\tParty “B” may participate in UT trading by registering trading account via UT Swap. Party “B” agrees to provide personal information to Party “A” including personal identity, beneficiary, purpose of trading, and other trading information as required by KYC (Know your client) terms.\n" +
                "4.5\tParty “B” acknowledged, understood, and agreed on trading procedure and other detail information stated in Operational Rules, Guidebook, Terms and Conditions, and Privacy Policy.\n" +
                "ប្រការ ៥ ៖\tកម្មសិទិ្ធលើឯកតាមូលនិធិ និងកម្មសិទ្ធិអចលនវត្ថុ \n" +
                "៥.១\tភាគី “ក” មានសិទ្ធិស្របច្បាប់ក្នុងការបង្កើតគម្រោងវិនិយោគលើអចលនវត្ថុដែលយកមកបង្កើតជាឯកតាមូលនិធិដើម្បីលក់ទិញ បើទោះបីជា ខ្លួនពុំមែនជាកម្មសិទ្ធិករលើអចលនវត្ថុនោះក៏ដោយ ។\n" +
                "៥.២\tភាគី “ខ” ត្រូវមានសិទិ្ធលើចំណែកឯកតាមូលនិធិ ស្របតាមទំហំ និង សមាមាត្រ នៃចំនួនឯកតាមូលនិធិ ដែលខ្លួនបានទិញ ។\n" +
                "៥.៣\tគូភាគី ទទួលស្គាល់ និងឯកភាពថា អចលនវត្ថុដែលយកមកបង្កើតជាឯកតាមូលនិធិដើម្បីទិញលក់ គឺជាកម្មសិទ្ធិរបស់តតិយជនដែលបានព្រមព្រៀង និងអនុញ្ញាត្ដឱ្យបង្កើតគម្រោងអចលនវត្ថុតាមរយៈយូធីស្វាប ហើយសិទ្ធិក្នុងការចាត់ចែង និងលក់បន្ដ ក៏ដូចជាសិទ្ធិក្នុងការចាត់ចែងប្រាក់ដែលបានមកពីការលក់ទិញអចលនវត្ថុនោះទាំងស្រុង គឺជាសិទ្ធិផ្ដាច់មុខរបស់ម្ចាស់អចនលវត្ថុ។\n" +
                "\n" +
                "ARTICLE 5:\tUNIT OF TRUST OWNERSHIP AND ASSET OWNERSHIP\n" +
                "5.1\tParty “A” is legally entitled to issue Real Estate Project Based Unit of Trust although Party “A” is not the actual owner of the properties. \n" +
                "5.2\tParty “B” is legally entitled to UT in proportion to the amount of purchased/owned UT.\n" +
                "5.3\tBoth parties acknowledged and agreed that the actual property subject to Real Estate Project Based Unit of Trust may belong to a third party who authorized Party “A” to issue Real Estate Project Based Unit of Trust via UT Swap. The property owner shall have exclusive rights to dispose and sell the property, and manage all fund derived from selling of the property.\n" +
                "\n" +
                "ប្រការ ៦ ៖     ការព្រមព្រៀងពិសេសពីអ្នកចូលរួមវិនិយោ\n" +
                "៦.១\tពហុភាពនៃអ្នកចូលរួមវិនិយោគ៖ ភាគី “ខ” បានដឹងនិងយល់ព្រមថា មានតតិយជនផ្សេងទៀតជាច្រើន ជាអ្នកចូលរួមទិញនៅក្នុងគម្រោងនេះ ហើយដែលពួកគេទទួលបានសិទិ្ធនិងកាតព្វកិច្ចដូចភាគី “ខ” ផងដែរ។\n" +
                "៦.២\tគោលដៅនៃការចូលរួមវិនិយោគ៖ ភាគី “ខ” ព្រមព្រៀងថា ការចុះកិច្ចព្រមព្រៀងនេះ គឺជាការចូលរួមលក់ទិញឯកតាមូលនិធិលើអចលនវត្ថុដែលត្រូវបានយកមកបង្កើតជាឯកតាមូលនិធិដើម្បីទិញលក់ ហើយ ភាគី “ខ” មិនអាចទាមទារចំណែកកម្មសិទ្ធិជាក់ស្ដែង ឬការផ្ទេរសិទ្ធិលើអចលនវត្ថុ ទោះក្នុងទំហំណាមួយបានឡើយ ។  \n" +
                "៦.៣\tកម្មសិទិ្ធបញ្ញា៖ អ្នកចូលរួមវិនិយោគទាំងអស់យល់ព្រមថា រាល់ប្រតិបត្តិការនៃការជួញដូរនិងការប្រើប្រាស់កម្មវិធីលក់ទិញឯកតាមូលនិធិលើអចលនវត្ថុតាមប្រព័ន្ធអេឡិកត្រូនិកនេះ ជាកម្មសិទ្ធិបញ្ញារបស់ ក្រុមហ៊ុន ហ្សីលាន យូណាយធីត ឯ.ក ដោយរួមបញ្ចូលទាំងទម្រង់ ខ្លឹមសារ រូបមន្ត អត្ថបទព័ត៌មាន រូបភាព សម្លេង វិដេអូ ម៉ាក ប្រកាសនីយបត្រតក្កកម្ម ឬ វត្ថុផ្សេងទៀតត្រូវបានការពារដោយច្បាប់ ស្តីពីសិទិ្ធអ្នកនិពន្ធនិងសិទ្ធិប្រហាក់ប្រហែលនៃព្រះរាជាណាចក្រកម្ពុជា និង/ឬ សិន្ធិសញ្ញា/អនុសញ្ញាអន្តរជាតិ ស្តីពីកម្មសិទិ្ធបញ្ញាដែលទទួលស្គាល់ដោយរាជរដ្ឋាភិបាលកម្ពុជា ។\n" +
                "\n" +
                "ARTICLE 6:\tSPECIAL PROVISIONS FOR TRADERS/INVESTORS\n" +
                "6.1\tPluralism of Traders/Investors: Party “B” acknowledged and agreed that there can be other traders/investors in this project, and they are entitled to the same rights and obligations as Party “B”. \n" +
                "6.2\tPurpose of UT Trading: Party “B” agreed that the purpose of this agreement is to participate in selling and purchasing UT, and Party “B” shall not be entitled to ownership over any part of the actual property.\n" +
                "6.3\tIntellectual Property: All traders agreed that all trading operations and the use of UT Swap are subject to intellectual property right owned by Zillion United Co., Ltd. Those may include format, contents, formula, news, images, audio, records, video clip, trademarks, patent or other applicable objects protected under Cambodian intellectual property laws and/or other international treaties/covenants recognized by the Royal Government of Cambodia. \n" +
                "ប្រការ ៧ ៖\tព័ត៌មានសម្ងាត់\n" +
                "ភាគី “ក” យល់ព្រមថា មិនប្រើប្រាស់ ឬបង្ហាញអំពីព័ត៌មានសម្ងាត់របស់ ភាគី “ខ” ដោយគ្មានការព្រមព្រៀងជាលាយលក្ខណ៍អក្សរជាមុនពី ភាគី “ខ” ឡើយលើកលែងតែមានការតម្រូវពីតុលាការ ឬស្ថាប័នមានសមត្ថកិច្ច និងការតម្រូវដោយច្បាប់និងបទប្បញ្ញាត្តិពាក់ព័ន្ធផ្សេងៗ ។\n" +
                "\n" +
                "\n" +
                "ARTICLE 7:\tCONFIDITIALITY OF INFORMATION\n" +
                "Party “A” agrees not to use or disclose the confidential information of Party “B” without prior written agreement from Party “B” except it is required by Court or other authority or laws or related regulations.\n" +
                "ប្រការ ៨ ៖     ប្រសិទ្ធភាពនៃកិច្ចព្រមព្រៀង និងការកែប្រែ\n" +
                "\tប្រសិនបើ ប្រការណាមួយនៃកិច្ចព្រមព្រៀងនេះត្រូវបានចាត់ទុកថា ខុសច្បាប់ មោឃភាព ឬមិនអាចអនុវត្តបានក្រោមច្បាប់ដែលមាននៅជាធរមាននាពេលបច្ចុប្បន្ន ឬអនាគត ប្រការនោះត្រូវលុបចេញទាំងស្រុង ហើយត្រូវបកស្រាយថា ប្រការដែលមិនស្របច្បាប់ មោឃភាព ឬមិនអាចអនុវត្តបាននោះមិនមាននៅក្នុងកិច្ចព្រមព្រៀងនេះ ហើយប្រការផ្សេងទៀតនៃកិច្ចព្រមព្រៀងនេះ ត្រូវអនុវត្ត បើមិន         ប៉ះពាល់ដោយភាពមិនស្របច្បាប់ មោឃភាព ឬមិនអាចអនុវត្តបាននៃប្រការនោះ។ ជំនួសឱ្យប្រការដែលមិនស្របច្បាប់ មោឃភាព ឬមិនអាចអនុវត្តបាន ត្រូវបន្ថែមដោយស្វ័យប្រវត្តិក្នុងកិច្ចព្រមព្រៀងនេះនូវប្រការ ដែលមានភាពស្របច្បាប់ មានសុពលភាព ឬអាចអនុវត្តបាន។\n" +
                "ARTICLE 8:\tBINDING EFFECT AND AMENDMENT\n" +
                "\n" +
                "If any provision of this Agreement is held to be illegal, invalid, unenforceable under present or future laws, such provision shall be removed from this agreement. The remaining provisions of this agreement shall remain in full force and shall not be affected by the illegal, invalid, or unenforceable provision, there shall be automatically added as a part of this agreement provision similar in terms to such illegal, invalid, or enforceable provision as may be possible and be legal, valid, or enforceable.\n" +
                "ប្រការ ៩ ៖\tការកែប្រែនិងការបញ្ចប់កិច្ចព្រមព្រៀង\n" +
                "៩.១\tកិច្ចព្រមព្រៀងនេះ មិនត្រូវកែប្រែ ឬធ្វើវិសោធនកម្មដោយគ្មានការយល់ព្រមជាលាយលក្ខណ៍អក្សរពីគូភាគីឡើយ ។\n" +
                "៩.២\tគូភាគី មានសិទ្ធិបញ្ចប់កិច្ចព្រមព្រៀងនេះគ្រប់ពេល ដោយត្រូវជូនដំណឹងជាលាយលក្ខណ៍អក្សរក្នុងរយៈពេល ៧ (ប្រាំពីរ) ថ្ងៃ ដល់ភាគីម្ខាងទៀត ។\n" +
                "ARTICLE 9:\tAMENDMENT AND TERMINATION\t\n" +
                "9.1\tThis agreement shall not be amended or modified without prior written agreement by both Parties.\n" +
                "9.2\tBoth Parties have the right to terminate this agreement by prior written notice to other Party within 7 (seven) business days.\n" +
                "ប្រការ ១០ ៖    កត្តាហានិភ័យ\n" +
                "១០.១\tភាគី “ខ” បានសិក្សា និងស្វែងយល់ច្បាស់អំពី ហានិភ័យនានាពាក់ព័ន្ធឯកតាមូលនិធិ និងបានយល់ដឹងអំពីលក្ខខណ្ឌនៃការជួញដូរឯកតាមូលនិធិ យន្តការ និងការអនុវត្តការជួញដូរ លក្ខខណ្ឌដាក់និងដកប្រាក់ក្នុងគណនី  កត្តាហានិភ័យផ្សេងៗ និងព្យសនកម្ម ដែលជាអាចជាលទ្ធផលបណ្តាលមកពីការជួញដូរឯកតាមូលនិធិក្នុងកម្មវិធី UT Swap ។ \n" +
                "១០.២\tភាគី “ខ” បានយល់ដឹង និងទទួលស្គាល់ថា ការលក់ទិញឯកតាមូលនិធិតាមរយៈប្រព័ន្ធអេឡិកត្រូនិក UT Swap នេះគឺជាការទទួលខុសត្រូវលើហានិភ័យ និងផលចំណេញផ្សេងៗ ដោយអាស្រ័យលើតម្លៃទីផ្សារ (Market Price) នៃឯកតាមូលនិធិក្នុងគម្រោងនីមួយៗ ។\n" +
                "១០.៣\tភាគី “ខ” យល់ដឹង និងទទួលស្គាល់ថា ការជួញដូរឯកតាមូលនិធិគឺជាការជួញដូរដែលអាចមានហានិភ័យខ្ពស់ ហើយខ្លួនក៏នឹងមិនសម្រេចចិត្តចុះកិច្ចព្រមព្រៀងនេះឡើយ ប្រសិនបើខ្លួនមិនបានយល់ដឹងច្បាស់អំពីលក្ខខណ្ឌដូចមានចែងក្នុងកិច្ចព្រមព្រៀងនេះ និងបទបញ្ញាត្ដិអមផ្សេងៗ។\n" +
                "ARTICLE 10:\tRISK FACTOR\n" +
                "10.1\tParty “B” has learned and clearly understood about the related risk of UT and acknowledged the trading conditions, mechanism and transaction, credit deposit and withdrawal conditions, other risk factors and harms as a result from UT trading via UT Swap.\n" +
                "10.2\tParty “B” understood and acknowledged that Party “B” shall bear his/her own responsibility in trading UT via UT Swap and other benefits based on the Market Price of UT in each project. \n" +
                "10.3\tParty “B” understood and acknowledged the trading of UT is of high risk, and therefore Party “B” would not have decided to sign this agreement if Party “B” had not clearly understood the conditions stated under this agreement. \n" +
                "ប្រការ ១១ ៖\tភាសា និង ចំនួននៃកិច្ចព្រមព្រៀង \n" +
                "កិច្ចព្រមព្រៀងនេះត្រូវបានធ្វើឡើងជាភាសាខ្មែរ និងភាសាអង់គ្លេសចំនួន ០៣(បី) ច្បាប់ដើម  មានតម្លៃស្មើគ្នាចំពោះមុខច្បាប់ ។ ករណីមានការបកស្រាយខុសគ្នារវាងភាសាទាំងពីរ ត្រូវយកភាសាខ្មែរជាគោល។ ច្បាប់ដើមនីមួយៗនៃកិច្ចព្រមព្រៀងនេះត្រូវរក្សាទុកដូចខាងក្រោម៖\n" +
                "\n" +
                "- ភាគី “ក”............................................................. ០១ច្បាប់\n" +
                "- ភាគី “ខ”............................................................. ០១ច្បាប់\n" +
                "- មេធាវី................................................................. ០១ច្បាប់\n" +
                "\tភាគីបានអាន និងយល់ព្រមតាមគ្រប់លក្ខខណ្ឌ និងខ្លឹមសារនេះទាំងស្រុង ព្រមទាំងធ្វើការចុះហត្ថលេខា ឬផ្ដិតស្នាមមេដៃដើម្បីទុកជា សក្ខីភាពចំពោះមុខច្បាប់ ។\n" +
                "\n" +
                "ARTICLE 11:\tLANGUAGE AND NUMBER OF COPIES\n" +
                "This agreement is made in Khmer and English Languages in 03 (three) original copies, and each of which shall have equal legal effect. In any event, there is any discrepancy between the two languages, the Khmer Language shall be prevailed. Each set of the Agreement shall be kept as the following:\n" +
                "- Party “A”.............................................................. 01 Copy\n" +
                "- Party “B”.............................................................. 01 Copy\n" +
                "- Lawyer.................................................................. 01 Copy\n" +
                "\n" +
                "\n" +
                "Parties have read and agreed to all the terms and conditions, sign or issue a thumbprint as evidence before the law. \n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\t\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n");
    }
}