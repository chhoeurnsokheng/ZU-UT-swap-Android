package com.zillennium.utswap.module.project.projectInfoScreen.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.databinding.DialogProjectSliderImageBinding
import com.zillennium.utswap.utils.dpToPx


class DialogProjectSliderImage : DialogFragment() {

    private var binding: DialogProjectSliderImageBinding? = null

    override fun getTheme(): Int {
        return R.style.FullScreenDialogWithCancelable
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        binding =
            DataBindingUtil.inflate(inflater, R.layout.dialog_project_slider_image, container, true)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            closeImage.setOnClickListener { dismiss() }

            val image = arguments?.getString("selectedImage")
            Glide.with(UTSwapApp.instance)
                .load(image)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(imageView)

        }

    }

    companion object {
        fun newInstance(image: String): DialogProjectSliderImage {
            val dialogProjectSliderImage = DialogProjectSliderImage()
            val args = Bundle()
            args.putString("selectedImage", image)
            dialogProjectSliderImage.arguments = args
            return dialogProjectSliderImage
        }
    }
}
