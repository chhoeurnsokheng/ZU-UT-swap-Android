package com.zillennium.utswap.screens.setting.settingScreen

import android.content.Intent
import android.net.Uri
import android.widget.AdapterView.OnItemClickListener
import android.widget.ListView
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.BaseDialogActivity
import com.zillennium.utswap.bases.BaseListViewHeightActivity
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivitySettingBinding
import com.zillennium.utswap.datas.SharedPreferencesStore
import com.zillennium.utswap.models.ListMenu.ListMenu
import com.zillennium.utswap.models.ListMenu.ListMenuAdapter
import com.zillennium.utswap.screens.setting.creditCardScreen.CreditCardActivity
import com.zillennium.utswap.screens.setting.fundPasswordScreen.FundPasswordActivity
import com.zillennium.utswap.screens.setting.kyc.KYCActivity
import com.zillennium.utswap.screens.setting.languageScreen.LanguageActivity
import com.zillennium.utswap.screens.setting.lockScreen.LockScreenActivity
import com.zillennium.utswap.screens.setting.logScreen.LogActivity
import com.zillennium.utswap.screens.setting.loginPasswordScreen.LoginPasswordActivity
import com.zillennium.utswap.screens.setting.profileScreen.ProfileActivity
import com.zillennium.utswap.screens.setting.twoFAScreen.TwoFAActivity
import java.io.IOException

class SettingActivity :
    BaseMvpActivity<SettingView.View, SettingView.Presenter, ActivitySettingBinding>(),
    SettingView.View {

    override var mPresenter: SettingView.Presenter = SettingPresenter()
    override val layoutResource: Int = R.layout.activity_setting

    override fun initView() {
        super.initView()
        try {
            binding.apply {


                // Set Setting ListView
                val iconSetting = intArrayOf(
                    R.drawable.ic_user_pen_regular,
                    R.drawable.ic_rectangle_list_regular,
                    R.drawable.ic_credit_card_front_regular,
                    R.drawable.ic_language_regular,
                    R.drawable.ic_unlock_keyhole_regular,
                    R.drawable.ic_unlock_keyhole_regular,
                    R.drawable.ic_tablet_regular,
                    R.drawable.ic_shield_keyhole_regular,
                    R.drawable.ic_timer_regular
                )
                val titleSetting = arrayOf(
                    "Profile",
                    "KYC",
                    "Credit Card",
                    "Language",
                    "Login Password",
                    "Fund Password",
                    "Log",
                    "2FA",
                    "Lock Screen"
                )

                val settingArrayList: ArrayList<ListMenu> = ArrayList()

                for (i in titleSetting.indices) {
                    val setting = ListMenu(titleSetting[i], iconSetting[i])
                    settingArrayList.add(setting)
                }

                val accountAdapter = ListMenuAdapter(UTSwapApp.instance, settingArrayList)

                lvSettings.adapter = accountAdapter
                lvSettings.onItemClickListener =
                    OnItemClickListener { _, _, position, _ ->
                        val intent: Intent
                        val title = titleSetting[position]
                        intent = when (title) {
                            "Profile" -> Intent(UTSwapApp.instance, ProfileActivity::class.java)
                            "Credit Card" -> Intent(UTSwapApp.instance, CreditCardActivity::class.java)
                            "KYC" -> Intent(UTSwapApp.instance, KYCActivity::class.java)
                            "Language" -> Intent(UTSwapApp.instance, LanguageActivity::class.java)
                            "Login Password" -> Intent(UTSwapApp.instance, LoginPasswordActivity::class.java)
                            "Fund Password" -> Intent(UTSwapApp.instance, FundPasswordActivity::class.java)
                            "Log" -> Intent(UTSwapApp.instance, LogActivity::class.java)
                            "2FA" -> Intent(UTSwapApp.instance, TwoFAActivity::class.java)
                            "Lock Screen" -> Intent(UTSwapApp.instance, LockScreenActivity::class.java)
                            else -> Intent(UTSwapApp.instance, ProfileActivity::class.java) // As Profile
                        }
                        startActivity(intent)
                    }
                BaseListViewHeightActivity.setListViewHeightBasedOnItems(lvSettings)

                val iconSupports = intArrayOf(
                    R.drawable.ic_phone_regular,
                    R.drawable.ic_earth_americas_regular,
                    R.drawable.ic_facebook_square_brand,
                    R.drawable.ic_telegram_brand,
                    R.drawable.ic_instagram_brand,
                    R.drawable.ic_twitter_brand
                )
                val titleSupports =
                    arrayOf("Telephone", "Website", "Facebook", "Telegram", "Instagram", "Twitter")

                val supportArrayList: ArrayList<ListMenu> = ArrayList<ListMenu>()

                for (i in titleSupports.indices) {
                    val support = ListMenu(titleSupports[i], iconSupports[i])
                    supportArrayList.add(support)
                }

                val supportAdapter = ListMenuAdapter(UTSwapApp.instance, supportArrayList)

                val lvSupport: ListView = binding.lvSupport
                lvSupport.adapter = supportAdapter
                lvSupport.onItemClickListener =
                    OnItemClickListener { adapterView, view, position, j ->
                        val title = titleSupports[position]
                        when (title) {
                            "Telephone" -> {
                                startActivity(
                                    Intent(
                                        "android.intent.action.VIEW",
                                        Uri.parse("tel: 0239999999")
                                    )
                                )
                                return@OnItemClickListener
                            }
                            "Telegram" -> {
                                startActivity(
                                    Intent(
                                        "android.intent.action.VIEW",
                                        Uri.parse(SharedPreferencesStore.APP_TELEGRAM)
                                    )
                                )
                                return@OnItemClickListener
                            }
                            "Facebook" -> {
                                try {
                                    startActivity(
                                        Intent(
                                            "android.intent.action.VIEW",
                                            Uri.parse("fb://page/101543225693802")
                                        )
                                    )
                                    return@OnItemClickListener
                                } catch (unused: Exception) {
                                    startActivity(
                                        Intent(
                                            "android.intent.action.VIEW",
                                            Uri.parse("https://www.facebook.com/101543225693802")
                                        )
                                    )
                                    return@OnItemClickListener
                                }
                                try {
                                    val intent = Intent(
                                        "android.intent.action.VIEW",
                                        Uri.parse("twitter://user?screen_name=zillionunited")
                                    )
                                    intent.setPackage("com.twitter.android")
                                    startActivity(intent)
                                    return@OnItemClickListener
                                } catch (unused2: Exception) {
                                    startActivity(
                                        Intent(
                                            "android.intent.action.VIEW",
                                            Uri.parse("https://twitter.com/zillionunited")
                                        )
                                    )
                                    return@OnItemClickListener
                                }
                                try {
                                    val intent2 = Intent(
                                        "android.intent.action.VIEW",
                                        Uri.parse("https://www.instagram.com/_u/zillion_united")
                                    )
                                    intent2.setPackage("com.instagram.android")
                                    startActivity(intent2)
                                    return@OnItemClickListener
                                } catch (unused3: Exception) {
                                    startActivity(
                                        Intent(
                                            "android.intent.action.VIEW",
                                            Uri.parse("https://www.instagram.com/zillion_united")
                                        )
                                    )
                                    return@OnItemClickListener
                                }
                                startActivity(
                                    Intent(
                                        "android.intent.action.VIEW",
                                        Uri.parse(SharedPreferencesStore.APP_WEBSITE)
                                    )
                                )
                                return@OnItemClickListener
                            }
                            "Twitter" -> {
                                try {
                                    val intent = Intent(
                                        "android.intent.action.VIEW",
                                        Uri.parse("twitter://user?screen_name=zillionunited")
                                    )
                                    intent.setPackage("com.twitter.android")
                                    startActivity(intent)
                                    return@OnItemClickListener
                                } catch (unused2: Exception) {
                                    startActivity(
                                        Intent(
                                            "android.intent.action.VIEW",
                                            Uri.parse("https://twitter.com/zillionunited")
                                        )
                                    )
                                    return@OnItemClickListener
                                }
                                try {
                                    val intent2 = Intent(
                                        "android.intent.action.VIEW",
                                        Uri.parse("https://www.instagram.com/_u/zillion_united")
                                    )
                                    intent2.setPackage("com.instagram.android")
                                    startActivity(intent2)
                                    return@OnItemClickListener
                                } catch (unused3: Exception) {
                                    startActivity(
                                        Intent(
                                            "android.intent.action.VIEW",
                                            Uri.parse("https://www.instagram.com/zillion_united")
                                        )
                                    )
                                    return@OnItemClickListener
                                }
                                startActivity(
                                    Intent(
                                        "android.intent.action.VIEW",
                                        Uri.parse(SharedPreferencesStore.APP_WEBSITE)
                                    )
                                )
                                return@OnItemClickListener
                            }
                            "Instagram" -> {
                                try {
                                    val intent2 = Intent(
                                        "android.intent.action.VIEW",
                                        Uri.parse("https://www.instagram.com/_u/zillion_united")
                                    )
                                    intent2.setPackage("com.instagram.android")
                                    startActivity(intent2)
                                    return@OnItemClickListener
                                } catch (unused3: Exception) {
                                    startActivity(
                                        Intent(
                                            "android.intent.action.VIEW",
                                            Uri.parse("https://www.instagram.com/zillion_united")
                                        )
                                    )
                                    return@OnItemClickListener
                                }
                                startActivity(
                                    Intent(
                                        "android.intent.action.VIEW",
                                        Uri.parse(SharedPreferencesStore.APP_WEBSITE)
                                    )
                                )
                                return@OnItemClickListener
                            }
                            else -> {
                                startActivity(
                                    Intent(
                                        "android.intent.action.VIEW",
                                        Uri.parse(SharedPreferencesStore.APP_WEBSITE)
                                    )
                                )
                                return@OnItemClickListener
                            }
                        }
                    }
                BaseListViewHeightActivity.setListViewHeightBasedOnItems(lvSupport)

                // Set Button Click Logout

                // Set Button Click Logout
                val btnLogout = binding.btnLogout
                btnLogout.setOnClickListener {
                    BaseDialogActivity.showDialog(
                        this@SettingActivity,
                        R.layout.dialog_setting_logout
                    )
                }

                // Set Passed Back

                // Set Passed Back
                ivBack.setOnClickListener {
                    onBackPressed()
                }
            }
        } catch (error: IOException) {
            // Must be safe
        }
    }
}