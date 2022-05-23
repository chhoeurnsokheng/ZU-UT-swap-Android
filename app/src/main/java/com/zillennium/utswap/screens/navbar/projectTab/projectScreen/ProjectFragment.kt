package com.zillennium.utswap.screens.navbar.projectTab.projectScreen

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.os.Build
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpFragment
import com.zillennium.utswap.databinding.FragmentNavbarProjectBinding
import com.zillennium.utswap.models.ProjectModel
import com.zillennium.utswap.screens.navbar.projectTab.projectScreen.adapter.ProjectAdapter
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*


class ProjectFragment :
    BaseMvpFragment<ProjectView.View, ProjectView.Presenter, FragmentNavbarProjectBinding>(),
    ProjectView.View {

    override var mPresenter: ProjectView.Presenter = ProjectPresenter()
    override val layoutResource: Int = R.layout.fragment_navbar_project
    private var viewGrid = true
    private var sortedDate = true

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("ResourceType")
    override fun initView() {
        super.initView()
//        try {
            binding.apply {

                val publicDate = arrayOf(
                    "05-05-2021",
                    "01-01-2022",
                    "03-03-2022",
                    "02-01-2022",
                    "02-04-2022",
                )

                val imageIcon = arrayOf(
                    R.drawable.popular_1,
                    R.drawable.popular_2,
                    R.drawable.popular_3,
                    R.drawable.popular_1,
                    R.drawable.popular_2
                )

                val titleProject = arrayOf(
                    "KT 1665",
                    "Siem Reap 17140",
                    "Muk Kampul 16644",
                    "Veng Sreng 2719",
                    "Pochentong 555",
                )

                val subTitle = arrayOf(
                    "KT 1665",
                    "Siem Reap 17140",
                    "Muk Kampul 16644",
                    "Veng Sreng 2719",
                    "Flipping Strategy"
                )

                val status = arrayOf(
                    "Upcoming",
                    "",
                    "",
                    "",
                    ""
                )

                val projectArrayList = ArrayList<ProjectModel>()

                for (i in publicDate.indices) {
                    val project = ProjectModel(
                        i,
                        publicDate[i],
                        imageIcon[i],
                        titleProject[i],
                        subTitle[i],
                        status[i]
                    )
                    projectArrayList.add(project)
                }

                for (i in publicDate.indices) {
                    val project = ProjectModel(
                        i + 10,
                        publicDate[i],
                        imageIcon[i],
                        titleProject[i],
                        subTitle[i],
                        status[i]
                    )
                    projectArrayList.add(project)
                }

                val dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
                projectArrayList.sortByDescending {
                    LocalDate.parse(
                        it.publicDate,
                        dateTimeFormatter
                    )
                }

                /* Sorted on click */
                layLast.setOnClickListener {
                    val layout = if(!viewGrid){
                        R.layout.item_list_project_grid
                    }else{
                        R.layout.item_list_project
                    }
                    if (sortedDate) {
                        projectArrayList.sortByDescending {
                            LocalDate.parse(
                                it.publicDate,
                                dateTimeFormatter
                            )
                        }
                    } else {
                        projectArrayList.sortBy {
                            LocalDate.parse(
                                it.publicDate,
                                dateTimeFormatter
                            )
                        }

                    }
                    rvProject.adapter =
                        ProjectAdapter(projectArrayList, layout, onclickProject)
                    sortedDate = !sortedDate
                }

                /* Change View on click */
                layView.setOnClickListener {
                    if (viewGrid) {
                        viewType.setImageResource(R.drawable.ic_grid_view)
//                        txtViewType.text = "List View"
                        rvProject.layoutManager = GridLayoutManager(UTSwapApp.instance, 2)
                        rvProject.adapter =
                            ProjectAdapter(projectArrayList, R.layout.item_list_project_grid, onclickProject)
                    } else {
                        viewType.setImageResource(R.drawable.ic_list_view)
//                        txtViewType.text = "Grid View"
                        rvProject.layoutManager = LinearLayoutManager(UTSwapApp.instance)
                        rvProject.adapter =
                            ProjectAdapter(projectArrayList, R.layout.item_list_project, onclickProject)
                    }
                    viewGrid = !viewGrid
                }
                layView.callOnClick()

                etSearch.setOnFocusChangeListener { _, hasFocus ->
                    if (hasFocus) {
                        imgSearch.imageTintList =
                            ColorStateList.valueOf(resources.getColor(R.color.color_main))
                        laySearch.backgroundTintList =
                            ColorStateList.valueOf(resources.getColor(R.color.color_main))
                    } else {
                        imgSearch.imageTintList =
                            ColorStateList.valueOf(resources.getColor(R.color.light_gray))
                        laySearch.backgroundTintList =
                            ColorStateList.valueOf(resources.getColor(R.color.light_gray))
                    }
                }

                etSearch.addTextChangedListener(object: TextWatcher{
                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                    }

                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                    }

                    override fun afterTextChanged(p0: Editable?) {

                    }

                })
            }

//        } catch (error: Exception) {
//            // Must be safe
//        }
    }

    private val onclickProject: ProjectAdapter.OnclickProject = object :
        ProjectAdapter.OnclickProject {
        override fun onClickMe(projectHistory: ProjectModel?, selectedPosition: Int?) {
            val bundle = bundleOf("id" to selectedPosition)
            findNavController().navigate(R.id.action_to_project_info, bundle)

        }

    }

}
