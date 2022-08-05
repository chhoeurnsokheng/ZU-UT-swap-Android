package com.zillennium.utswap.module.project.projectInfoScreen.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.zillennium.utswap.bases.mvp.BaseRecyclerViewAdapterGeneric
import com.zillennium.utswap.bases.mvp.BaseViewHolder
import com.zillennium.utswap.databinding.ItemListProjectInfoSliderImageBinding
import com.zillennium.utswap.models.projectList.ProjectInfoDetail


class ProjectViewPagerAdapter : BaseRecyclerViewAdapterGeneric<ProjectInfoDetail.ProjectInfoDetailData, ProjectViewPagerAdapter.ProjectViewPagerViewHolder>(){
    inner class ProjectViewPagerViewHolder(root : ItemListProjectInfoSliderImageBinding) :
        BaseViewHolder<ItemListProjectInfoSliderImageBinding>(root) {
        fun bindData(projectInfoDetailData: ProjectInfoDetail.ProjectInfoDetailData) {
            binding.apply {

            }
        }

    }

    override fun onCreateItemHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ) = ProjectViewPagerViewHolder(ItemListProjectInfoSliderImageBinding.inflate(inflater, parent, false))

    override fun onBindItemHolder(
        holder: ProjectViewPagerViewHolder,
        position: Int,
        context: Context
    ) {
        holder.bindData(items[position])
    }

}

//    (
//    private val arrayList: ArrayList<ProjectInfoSlideImageModel>,
//    private val onclickAdapter: OnclickAdapter
//) : RecyclerView.Adapter<ProjectViewPagerAdapter.ViewPagerViewHolder>() {
//
//    inner class ViewPagerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//        var imageViews: ImageView = view.findViewById<View>(R.id.ivImage) as ImageView
//    }
//
//    override fun onCreateViewHolder(
//        viewGroup: ViewGroup,
//        viewType: Int
//    ): ProjectViewPagerAdapter.ViewPagerViewHolder {
//        return ViewPagerViewHolder(
//            LayoutInflater.from(viewGroup.context)
//                .inflate(R.layout.item_list_project_info_slider_image, viewGroup, false)
//        )
//    }
//
//    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
//        val imageList: ProjectInfoSlideImageModel = arrayList[position]
//        Glide.with(UTSwapApp.instance)
//            .asBitmap()
//            .load(imageList.imageSlider)
//            .apply(RequestOptions().override(200, 200))
//            .fitCenter()
//            .diskCacheStrategy(DiskCacheStrategy.NONE)
//            .skipMemoryCache(true)
//            .into(holder.imageViews)
//
//        holder.itemView.setOnClickListener {
//            onclickAdapter.onClickMe(imageList, position, holder.itemView)
//        }
//    }
//
//    override fun getItemCount(): Int {
//        return arrayList.size
//    }
//
//    interface OnclickAdapter {
//        fun onClickMe(projectInfoSlideImageModel: ProjectInfoSlideImageModel, position: Int, view: View)
//    }
//}
