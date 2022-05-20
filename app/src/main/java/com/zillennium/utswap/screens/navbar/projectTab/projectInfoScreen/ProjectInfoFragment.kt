package com.zillennium.utswap.screens.navbar.projectTab.projectInfoScreen

import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.FragmentNavbarProjectInfoBinding
import com.zillennium.utswap.models.ProjectInfoDetail
import com.zillennium.utswap.models.ProjectInfoInvestment
import com.zillennium.utswap.screens.navbar.projectTab.projectInfoScreen.adapter.ProjectInfoDetailsAdapter
import com.zillennium.utswap.screens.navbar.projectTab.projectInfoScreen.adapter.ProjectInfoInvestmentAdapter
import java.util.*


class ProjectInfoFragment :
    BaseMvpFragment<ProjectInfoView.View, ProjectInfoView.Presenter, FragmentNavbarProjectInfoBinding>(),
    ProjectInfoView.View {

    override var mPresenter: ProjectInfoView.Presenter = ProjectInfoPresenter()
    override val layoutResource: Int = R.layout.fragment_navbar_project_info

    private var termCondition = true
    private var condition = true

    override fun initView() {
        super.initView()
        try {
            binding.apply {

                btnBack.setOnClickListener {
                    findNavController().popBackStack()
                }

                btnSubscriptTrade.setOnClickListener {
                    findNavController().navigate(R.id.action_to_project_subscription)
                }

                /* Slide show */
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


                /* Recycle view of project info detail */
                val titleInfoDetail = arrayOf(
                    "Title Deep",
                    "Land Size",
                    "Total UT",
                    "Base Price",
                    "Target Price",
                    "Managed by",
                    "Location"
                )
                val valueInfo = arrayOf(
                    "Hard Title (10)",
                    "384 761",
                    "220 000 UT",
                    "$1 625/sqm",
                    "$1 804/sqm",
                    "Focus Property Co., Ltd.",
                    "St. 105k, Kbal Damrei 1, Kakab 1, Pur Senchey, Phnom Penh"
                )

                val projectInfoDetailArrayList = arrayListOf<ProjectInfoDetail>()

                for (i in titleInfoDetail.indices){
                    val projectInfo = ProjectInfoDetail(
                        titleInfoDetail[i],
                        valueInfo[i]
                    )
                    projectInfoDetailArrayList.add(projectInfo)
                }

                rvProjectInfoDetail.layoutManager = LinearLayoutManager(UTSwapApp.instance)
                rvProjectInfoDetail.adapter = ProjectInfoDetailsAdapter(projectInfoDetailArrayList)


                /* Recycle Investment info */
                val perUT = arrayOf(
                    "3.90",
                    "4.00",
                    "4.10",
                    "4.20",
                    "4.30",
                    "4.35",
                    "4.45",
                    "4.50",
                    "4.55",
                )
                val valueUT = arrayOf(
                    "858 000",
                    "880 000",
                    "902 000",
                    "924 000",
                    "935 000",
                    "957 000",
                    "979 000",
                    "990 000",
                    "1 001 000",
                )
                val sqmUT = arrayOf(
                    "1 546",
                    "1 586",
                    "1 625",
                    "1 665",
                    "1 685",
                    "1 724",
                    "1 764",
                    "1 784",
                    "1 804",
                )

                val projectInfoInvestmentArrayList = arrayListOf<ProjectInfoInvestment>()

                for (i in perUT.indices){
                    val projectInvestment = ProjectInfoInvestment(
                        perUT[i],
                        valueUT[i],
                        sqmUT[i]
                    )
                    projectInfoInvestmentArrayList.add(projectInvestment)
                }

                rvProjectInvestmentInfo.layoutManager = LinearLayoutManager(UTSwapApp.instance)
                rvProjectInvestmentInfo.adapter = ProjectInfoInvestmentAdapter(projectInfoInvestmentArrayList)


                /* Term and condition && Document */
                txtTermCondition.text =
                    "1. Locked-up period of 45 days is applicable on all UTS bought on discount. " +
                            "\n2. NO guaranteed rate of return on investment; Rate of return is variable depending on the deal closing price. " +
                            "\n3. NO guarantee on the duration to when the deal can be closed. \n4. NO contract for the sale and purchase of UT. " +
                            "The transaction report testifies your economic benefit. \n5. Right to the decision on the sale of land belongs to Trading Committee. " +
                            "The SPA will testify the selling price to the third party. This price will be the delisting price of all UT. " +
                            "\n6. UT will be sold and bought back via digital trading platform only. Once 80% of the UT are collected by the company, " +
                            "the remaining 20% will be automatically exchanged at the delisting price. \n7. Trading is open 7/7 on UTSWAP Platform from 9 am 4 pm. " +
                            "\n8. The transaction fee per transaction is 0.3% of traded value. \n9. Minimum investment is 1 UT. \n10. Failure to comply with our terms " +
                            "and conditions results in penalty of 100% of the violated transaction value in addition to account freezing."
                layoutTermCondition.setOnClickListener{
                    if (termCondition){
                        arrowDownTermCondition.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24)

                        txtTermCondition.visibility = View.VISIBLE
                        termCondition = !termCondition
                    }else{
                        arrowDownTermCondition.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24)
                        txtTermCondition.visibility = View.GONE
                        termCondition = !termCondition
                    }
                }
                layoutDocument.setOnClickListener {
                    if (condition){
                        arrowDownDocument.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24)
                        pdfDocument.visibility = View.VISIBLE
                        condition = !condition
                    }else{
                        arrowDownDocument.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24)
                        pdfDocument.visibility = View.GONE
                        condition = !condition
                    }
                }
//                txtDetailTitle.text = arguments?.getInt("id").toString()
            }

        } catch (error: Exception) {
            // Must be safe
        }


    }
}
