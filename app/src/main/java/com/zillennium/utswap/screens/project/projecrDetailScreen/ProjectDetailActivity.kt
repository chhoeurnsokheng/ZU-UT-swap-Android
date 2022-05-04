package com.zillennium.utswap.screens.project.projecrDetailScreen

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivityProjectDescriptionBinding
import com.zillennium.utswap.screens.project.projectICOScreen.ProjectICOActivity
import com.zillennium.utswap.screens.trade.tradeExchangeScreen.TradeExchangeActivity
import java.io.IOException

class ProjectDetailActivity :
    BaseMvpActivity<ProjectDetailView.View, ProjectDetailView.Presenter, ActivityProjectDescriptionBinding>(),
    ProjectDetailView.View {

    override var mPresenter: ProjectDetailView.Presenter = ProjectDetailPresenter()
    override val layoutResource: Int = R.layout.activity_project_description

    override fun initView() {
        super.initView()
        try {
            binding.apply {
                val imageSlider = findViewById<ImageSlider>(R.id.image_slider)

                val slideModels = ArrayList<SlideModel>()

                slideModels.add(
                    SlideModel(
                        "https://utswap.io/Upload/issue/61fdf19956a5c.JPG",
                        ScaleTypes.FIT
                    )
                )
                slideModels.add(
                    SlideModel(
                        "https://utswap.io/Upload/issue/61fdf19b13e7e.JPG",
                        ScaleTypes.FIT
                    )
                )
                slideModels.add(
                    SlideModel(
                        "https://utswap.io/Upload/issue/61fdf19b4a9e5.JPG",
                        ScaleTypes.FIT
                    )
                )
                slideModels.add(
                    SlideModel(
                        "https://utswap.io/Upload/issue/61fdf19dd63cb.JPG",
                        ScaleTypes.FIT
                    )
                )

                imageSlider.setImageList(slideModels, ScaleTypes.FIT)

                val icon = intArrayOf(
                    R.drawable.ic_location_dot_regular,
                    R.drawable.ic_calendar_days_regular,
                    R.drawable.ic_earth_americas_regular,
                    R.drawable.ic_coins_regular,
                    R.drawable.ic_arrow_right_arrow_left_regular,
                    R.drawable.ic_gem_regular,
                    R.drawable.ic_location_dot_regular,
                    R.drawable.ic_location_dot_regular
                )
                val title = arrayOf(
                    "Project",
                    "Title Deed",
                    "Land Size",
                    "Total UT",
                    "Indiaction Price",
                    "Future Price",
                    "Address",
                    "Google Map"
                )
                val desc = arrayOf(
                    "UT Mondulkiri",
                    "Hard Title",
                    "16 644 sqm",
                    "1 000 000 UT",
                    "$18.00 /sqm",
                    "$22.50 /sqm",
                    "Memang Commune Kaev Seima District Mondul Kiri Province",
                    "https://goo.gl/maps/VK2aUW4e823PtJwVA"
                )

                val projectArrayList = ArrayList<ProjectDetail>()

                for (i in title.indices) {
                    val project = ProjectDetail(
                        title[i],
                        desc[i], icon[i]
                    )
                    projectArrayList.add(project)
                }

                val recyclerView = findViewById<View>(R.id.rv_article_project) as RecyclerView
                recyclerView.layoutManager = LinearLayoutManager(UTSwapApp.instance)
                recyclerView.adapter = ProjectDetailAdapter(projectArrayList)

                btnSubscribe.setOnClickListener {
                    startActivity(
                        Intent(
                            UTSwapApp.instance,
                            ProjectICOActivity::class.java
                        )
                    )
                }

                btnTrade.setOnClickListener {
                    startActivity(
                        Intent(
                            UTSwapApp.instance,
                            TradeExchangeActivity::class.java
                        )
                    )
                }

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