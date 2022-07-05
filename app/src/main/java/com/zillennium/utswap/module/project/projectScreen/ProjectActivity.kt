package com.zillennium.utswap.module.project.projectScreen

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Build
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivityProjectBinding
import com.zillennium.utswap.models.ProjectModel
import com.zillennium.utswap.models.TestModel
import com.zillennium.utswap.module.project.projectInfoScreen.ProjectInfoActivity
import com.zillennium.utswap.module.project.projectScreen.adapter.ProjectAdapter
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*


class ProjectActivity :
    BaseMvpActivity<ProjectView.View, ProjectView.Presenter, ActivityProjectBinding>(),
    ProjectView.View {

    override var mPresenter: ProjectView.Presenter = ProjectPresenter()
    override val layoutResource: Int = R.layout.activity_project

    private var projectArrayList = ArrayList<ProjectModel>()
    private var search = ""
    private var viewGrid = false
    private var sortedDate = true

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("ResourceType")
    override fun initView() {
        super.initView()
//        try {
        mPresenter.getDataFromApi()

//            mPresenter.getProject().map {
//                Log.d("Test", it.title)
//            }

//            Handler().postDelayed({
        binding.apply {

            Log.d("hello", "world")

            pgLoading.visibility = View.GONE
            rvProject.visibility = View.VISIBLE

            backImage.setOnClickListener {
                finish()
            }


            val publicDate = arrayOf(
                "05-05-2021",
                "01-01-2022",
                "03-03-2022",
                "02-01-2022",
                "02-04-2022",
            )

            val imageIcon = arrayOf(
                "https://utswap.io/Upload/issue/62258e1d402b7.png",
                "https://utswap.io/Upload/issue/62258e6ce881f.jpg",
                "https://utswap.io/Upload/issue/62258de873321.jpg",
                "https://utswap.io/Upload/issue/62258dc331263.jpg",
                "https://utswap.io/Upload/issue/62258d2401bb7.jpg"
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
                sortedDate = !sortedDate
                if(sortedDate){
                    imgLast.rotation = 180f
                }else{
                    imgLast.rotation = 0f
                }
                getData()
            }

            /* Change View on click */
            layView.setOnClickListener {
                viewGrid = !viewGrid
                getData()
            }
            layView.callOnClick()

            etSearch.setOnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    imgSearch.imageTintList =
                        ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.primary))
                    laySearch.backgroundTintList =
                        ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.primary))
                } else {
                    imgSearch.imageTintList =
                        ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.light_gray))
                    laySearch.backgroundTintList =
                        ColorStateList.valueOf(ContextCompat.getColor(UTSwapApp.instance, R.color.light_gray))
                }
            }

            etSearch.addTextChangedListener(object: TextWatcher{
                override fun beforeTextChanged(char: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(char: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    search = char.toString()
                }

                override fun afterTextChanged(p0: Editable?) {
                    getData()
                }

            })
        }
//            }, 5000)


//        } catch (error: Exception) {
//            // Must be safe
//        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getData(){
        binding.apply {

            var dataProject: ArrayList<ProjectModel> = arrayListOf()

            if(search.isNotEmpty()){
                dataProject.clear()
                projectArrayList.map {
                    if(it.titleProject.contains(search, ignoreCase = true)){
                        dataProject.add(it)
                    }
                }
            }else{
                dataProject = projectArrayList
            }

            val dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
            if(sortedDate){
                dataProject.sortByDescending {
                    LocalDate.parse(
                        it.publicDate,
                        dateTimeFormatter
                    )
                }
            }else{
                dataProject.sortBy {
                    LocalDate.parse(
                        it.publicDate,
                        dateTimeFormatter
                    )
                }
            }

            if(viewGrid){
                viewType.setImageResource(R.drawable.ic_grid_view)
                rvProject.layoutManager = GridLayoutManager(UTSwapApp.instance, 2)
                rvProject.adapter =
                    ProjectAdapter(dataProject, R.layout.item_list_project_grid, onclickProject)
            }else{
                viewType.setImageResource(R.drawable.ic_list_view)
                rvProject.layoutManager = LinearLayoutManager(UTSwapApp.instance)
                rvProject.adapter =
                    ProjectAdapter(dataProject, R.layout.item_list_project, onclickProject)
            }

        }

    }

    private val onclickProject: ProjectAdapter.OnclickProject = object :
        ProjectAdapter.OnclickProject {
        override fun onClickMe(projectHistory: ProjectModel?, selectedPosition: Int?) {
//            val bundle = bundleOf("id" to selectedPosition)
//            findNavController().navigate(R.id.action_to_project_info, bundle)
                val intent: Intent = Intent(UTSwapApp.instance, ProjectInfoActivity::class.java)
                startActivity(intent)

        }

    }

    override fun onGetPhoto(data: List<TestModel>) {
        Log.d("onGetPhoto Fragment", "onGetPhoto Fragment")
//        data.map {
//            Log.d("123213", it.url)
//        }

    }

}
