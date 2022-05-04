package com.zillennium.utswap.screens.system.searchScreen

import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemClickListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.BaseListViewHeightActivity
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivitySystemSearchBinding
import com.zillennium.utswap.models.ListMenu.ListMenu
import com.zillennium.utswap.models.ListMenu.ListMenuAdapter
import com.zillennium.utswap.screens.setting.kyc.KYCActivity
import com.zillennium.utswap.screens.setting.creditCardScreen.CreditCardActivity
import com.zillennium.utswap.screens.setting.languageScreen.LanguageActivity
import com.zillennium.utswap.screens.setting.lockScreen.LockScreenActivity
import com.zillennium.utswap.screens.setting.profileScreen.ProfileActivity
import com.zillennium.utswap.screens.system.searchScreen.SearchAdapter.OnclickItemSearch
import java.io.IOException

class SearchActivity :
    BaseMvpActivity<SearchView.View, SearchView.Presenter, ActivitySystemSearchBinding>(),
    SearchView.View {

    override var mPresenter: SearchView.Presenter = SearchPresenter()
    override val layoutResource: Int = R.layout.activity_system_search

    override fun initView() {
        super.initView()
        try {
            binding.apply {

                val title = arrayOf(
                    "Trading",
                    "History",
                    "Project",
                    "Title Deed",
                    "Land Size",
                    "Total UT",
                    "Indiaction Price",
                    "Future Price",
                    "Address",
                    "Google Map"
                )

                val searchArrayList = ArrayList<Search>()

                for (i in title.indices) {
                    val project = Search(0, title[i])
                    searchArrayList.add(project)
                }


                val recyclerView = findViewById<View>(R.id.rc_history) as RecyclerView
                recyclerView.layoutManager = LinearLayoutManager(UTSwapApp.instance)
                recyclerView.adapter = SearchAdapter(searchArrayList, onclickItemSearch)



                edSearch.addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(
                        charSequence: CharSequence,
                        i: Int,
                        i1: Int,
                        i2: Int
                    ) {
                    }

                    override fun onTextChanged(
                        charSequence: CharSequence,
                        i: Int,
                        i1: Int,
                        i2: Int
                    ) {
                        val layResult: LinearLayout = binding.layResult
                        val layDefault: LinearLayout = binding.layDefault
                        val btnClear: ImageView = binding.btnClear
                        if (charSequence.length > 1) {
                            layDefault.visibility = View.GONE
                            layResult.visibility = View.VISIBLE
                            btnClear.visibility = View.VISIBLE
                            btnClear.setOnClickListener {
                                edSearch.setText(
                                    ""
                                )
                            }
                        } else {
                            layDefault.visibility = View.VISIBLE
                            layResult.visibility = View.GONE
                            btnClear.visibility = View.GONE
                            btnClear.setOnClickListener { }
                        }
                    }

                    override fun afterTextChanged(editable: Editable) {}
                })

                recyclerView.setOnClickListener {
                    edSearch.setText("UT Swap Test Search")
                    Toast.makeText(UTSwapApp.instance, "hello world", Toast.LENGTH_LONG).show()
                }
                // Set Setting ListView
                // Set Setting ListView
                val iconSetting = intArrayOf(
                    R.drawable.ic_user_pen_regular,
                    R.drawable.ic_rectangle_list_regular,
                    R.drawable.ic_credit_card_front_regular,
                    R.drawable.ic_language_regular,
                    R.drawable.ic_timer_regular
                )
                val titleSetting = arrayOf(
                    "Profile",
                    "KYC",
                    "Credit Card",
                    "Language",
                    "Lock Screen"
                )

                val settingArrayList = ArrayList<ListMenu>()

                for (i in titleSetting.indices) {
                    val setting = ListMenu(titleSetting[i], iconSetting[i])
                    settingArrayList.add(setting)
                }

                val accountAdapter = ListMenuAdapter(UTSwapApp.instance, settingArrayList)

                val lvSettings: ListView = binding.lvSettings
                lvSettings.adapter = accountAdapter
                lvSettings.onItemClickListener =
                    OnItemClickListener { _, view, position, _ ->
                        val intent: Intent
                        val title = titleSetting[position]
                        intent = when (title) {
                            "Profile" -> Intent(view.context, ProfileActivity::class.java)
                            "Credit Card" -> Intent(view.context, CreditCardActivity::class.java)
                            "KYC" -> Intent(view.context, KYCActivity::class.java)
                            "Language" -> Intent(view.context, LanguageActivity::class.java)
                            "Lock Screen" -> Intent(view.context, LockScreenActivity::class.java)
                            else -> Intent(view.context, ProfileActivity::class.java) // As Profile
                        }
                        startActivity(intent)
                    }
                BaseListViewHeightActivity.setListViewHeightBasedOnItems(lvSettings)

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

    private val onclickItemSearch: OnclickItemSearch = object : OnclickItemSearch {
        override fun onClickMe(textSearch: String?) {
            binding.apply {
                edSearch.setText(textSearch)
                edSearch.setSelection(edSearch.text.length)
            }
        }
    }
}