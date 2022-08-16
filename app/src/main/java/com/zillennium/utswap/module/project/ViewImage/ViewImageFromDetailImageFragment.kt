package com.zillennium.utswap.module.project.ViewImage

import android.app.Activity
import android.media.Image
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alexvasilkov.gestures.views.GestureFrameLayout
import com.bumptech.glide.Glide
import com.github.toxa2033.ScaleImageView
import com.zillennium.utswap.R

class ViewImageFromDetailImageFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root: View = inflater.inflate(R.layout.image_view_fragment, container, false)
        val image: String = arguments?.getString("image","").toString()
        val layout = root.findViewById<GestureFrameLayout>(R.id.container)
        layout.controller.settings
            .setZoomEnabled(true)
            .setRotationEnabled(false)
            .setFlingEnabled(false)
            .setPanEnabled(false)
            .setExitEnabled(false)
            .setDoubleTapEnabled(true)
            .setMaxZoom(2f).overzoomFactor = 2f
        val imv = root.findViewById<ScaleImageView>(R.id.iv_image)
        imv.setOnDismissRateChange { rate: Float, isCanNowDismiss: Boolean ->
            if (isCanNowDismiss && rate == 1f) {
                activity?.finish()
                activity?.overridePendingTransition(0, 0)
            }
        }
        activity?.let { Glide.with(it).load(image).into(imv) }
        return root
    }

    companion object {
        fun newInstance(
            image: String,
            position: Int
        ): ViewImageFromDetailImageFragment {
            val args = Bundle()
            args.putSerializable("image", image)
            args.putSerializable("position", position)
            val fragment = ViewImageFromDetailImageFragment()
            fragment.arguments = args
            return fragment
        }
    }
}