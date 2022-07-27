package com.zillennium.utswap.module.project.ViewImage

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewImageFromDetailVPA(
    fm: FragmentManager,
    behavior: Int,
    private val imageList: ArrayList<String>
) :
    FragmentPagerAdapter(fm, behavior) {
    override fun getItem(position: Int): Fragment {
        return ViewImageFromDetailImageFragment.newInstance(imageList[position], position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return imageList.size
    }
}
