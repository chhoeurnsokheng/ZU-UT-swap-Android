package com.zillennium.utswap.screens.setting.languageScreen

import android.widget.AdapterView.OnItemClickListener
import android.widget.ImageView
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.BaseListViewHeightActivity
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivitySettingLanguageBinding
import com.zillennium.utswap.models.ListLanguage.ListLanguage
import com.zillennium.utswap.models.ListLanguage.ListLanguageAdapter
import java.io.IOException

class LanguageActivity :
    BaseMvpActivity<LanguageView.View, LanguageView.Presenter, ActivitySettingLanguageBinding>(),
    LanguageView.View {

    override var mPresenter: LanguageView.Presenter = LanguagePresenter()
    override val layoutResource: Int = R.layout.activity_setting_language

    override fun initView() {
        super.initView()
        try {
            binding.apply {

                // Set Passed Back
                // Set Language ListView

                // Set Language ListView
                val iconLanguage = intArrayOf(
                    R.drawable.flag_english,
                    R.drawable.flag_khmer
                )
                val titleLanguage = arrayOf(
                    "English",
                    "Khmer"
                )


                val languageArrayList = ArrayList<ListLanguage>()

                for (i in titleLanguage.indices) {
                    val language: ListLanguage = if (titleLanguage[i] === "English") {
                        ListLanguage(titleLanguage[i], iconLanguage[i], true)
                    } else {
                        ListLanguage(titleLanguage[i], iconLanguage[i], false)
                    }
                    languageArrayList.add(language)
                }

                val languageAdapter = ListLanguageAdapter(UTSwapApp.instance, languageArrayList)


                lvLanguage.onItemClickListener =
                    OnItemClickListener { adapterView, view, position, _ ->
                        val title = titleLanguage[position]
                        for (i in 0 until adapterView.adapter.count) {
                            val iconRight1 =
                                adapterView.getChildAt(i).findViewById<ImageView>(R.id.icon_right)
                            iconRight1.setImageResource(0)
                        }
                        val iconRight = view.findViewById<ImageView>(R.id.icon_right)
                        iconRight.setImageResource(R.drawable.ic_circle_check_regular)

                        //               switch (title){
                        //                   case "English":
                        //                       icon_right.setImageResource(R.drawable.ic_circle_check_regular);
                        //                       break; case "Khmer":
                        //                       icon_right.setImageResource(R.drawable.ic_circle_check_regular);
                        //                       break;
                        //               }
                    }
                lvLanguage.adapter = languageAdapter
                BaseListViewHeightActivity.setListViewHeightBasedOnItems(lvLanguage)


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