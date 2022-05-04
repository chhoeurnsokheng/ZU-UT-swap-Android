package com.zillennium.utswap.screens.setting.lockScreen

import android.widget.AdapterView.OnItemClickListener
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.BaseListViewHeightActivity
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivitySettingLockScreenBinding
import com.zillennium.utswap.models.ListLockScreen.ListLockScreen
import com.zillennium.utswap.models.ListLockScreen.ListLockScreenAdapter
import java.io.IOException

class LockScreenActivity :
    BaseMvpActivity<LockScreenView.View, LockScreenView.Presenter, ActivitySettingLockScreenBinding>(),
    LockScreenView.View {

    override var mPresenter: LockScreenView.Presenter = LockScreenPresenter()
    override val layoutResource: Int = R.layout.activity_setting_lock_screen

    override fun initView() {
        super.initView()
        try {
            binding.apply {

                // Set Lock Screen ListView
                val titleLockscreen = arrayOf(
                    "Lock Screen After 1 Minute",
                    "Lock Screen After 2 Minute",
                    "Lock Screen After 3 Minute"
                )

                val lockScreenArrayList = ArrayList<ListLockScreen>()

                for (i in titleLockscreen.indices) {
                    val lockscreen: ListLockScreen =
                        if (titleLockscreen[i] === "Lock Screen After 1 Minute") {
                            ListLockScreen(titleLockscreen[i], true)
                        } else {
                            ListLockScreen(titleLockscreen[i], false)
                        }
                    lockScreenArrayList.add(lockscreen)
                }

                val lockScreenAdapter = ListLockScreenAdapter(UTSwapApp.instance, lockScreenArrayList)
                val lvLockscreen = binding.lvLockscreen
                lvLockscreen.adapter = lockScreenAdapter
                BaseListViewHeightActivity.setListViewHeightBasedOnItems(lvLockscreen)
                lvLockscreen.onItemClickListener =
                    OnItemClickListener { adapterView, view, position, l ->
                        val title = titleLockscreen[position]
                        for (i in 0 until adapterView.adapter.count) {
                            val pLayItem =
                                adapterView.getChildAt(i).findViewById<LinearLayout>(R.id.lay_item)
                            val pIvIcon =
                                adapterView.getChildAt(i).findViewById<ImageView>(R.id.iv_icon)
                            val pTxtTitle =
                                adapterView.getChildAt(i).findViewById<TextView>(R.id.txt_title)
                            pLayItem.setBackgroundResource(R.drawable.bg_lockscreen)
                            pIvIcon.setColorFilter(adapterView.resources.getColor(R.color.secondary))
                            pTxtTitle.setTextColor(adapterView.resources.getColor(R.color.secondary))
                        }
                        val layItem = view.findViewById<LinearLayout>(R.id.lay_item)
                        val ivIcon = view.findViewById<ImageView>(R.id.iv_icon)
                        val txtTitle = view.findViewById<TextView>(R.id.txt_title)
                        layItem.setBackgroundResource(R.drawable.bg_lockscreen_selected)
                        ivIcon.setColorFilter(view.resources.getColor(R.color.white))
                        txtTitle.setTextColor(view.resources.getColor(R.color.white))

                        //               switch (title){
                        //                   case "English":
                        //                       icon_right.setImageResource(R.drawable.ic_circle_check_regular);
                        //                       break; case "Khmer":
                        //                       icon_right.setImageResource(R.drawable.ic_circle_check_regular);
                        //                       break;
                        //               }
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
}