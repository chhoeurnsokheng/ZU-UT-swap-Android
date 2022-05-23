package com.zillennium.utswap.screens.navbar.projectTab.projectInfoScreen.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import com.zillennium.utswap.R
import com.zillennium.utswap.screens.navbar.projectTab.projectInfoScreen.ProjectInfoFragment

class DialogProjectSliderImage : DialogFragment() {

    private var btnClose: ImageView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.dialog_project_slider_image, container, false)
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)

        dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))


        val image = arguments?.get("image")

        val imageView = view?.findViewById<ImageView>(R.id.image_view)
        imageView?.setImageResource(image as Int)

        btnClose = view?.findViewById(R.id.close_image)
        btnClose?.setOnClickListener {
            dismiss()
        }

        return view
    }

   companion object {
        fun newInstance(): DialogProjectSliderImage {
            val dialogProjectSliderImage = DialogProjectSliderImage()
            val args = Bundle()

            dialogProjectSliderImage.arguments = args
            return dialogProjectSliderImage
        }
    }
}