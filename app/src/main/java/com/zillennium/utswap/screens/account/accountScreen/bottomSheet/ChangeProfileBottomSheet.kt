package com.zillennium.utswap.screens.account.accountScreen.bottomSheet

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.databinding.BottomSheetAccountChangeProfileBinding


class ChangeProfileBottomSheet : BottomSheetDialogFragment() {

    private var binding: BottomSheetAccountChangeProfileBinding? = null


    override fun getTheme(): Int {
        return R.style.BottomSheetStyle
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        binding = DataBindingUtil.inflate(inflater, R.layout.bottom_sheet_account_change_profile, container, false)
        return binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {

            (view.parent as View).setBackgroundColor(ContextCompat.getColor(UTSwapApp.instance, android.R.color.transparent))

            txtTakePhoto.setOnClickListener {

            }


            //Choose Image From gallery
            txtChooseFromGallery.setOnClickListener {
                val intent  = Intent(Intent.ACTION_PICK);
                intent.type = "image/*"
                startActivityForResult(intent,PICK_IMAGE_FROM_GALLERY)
            }

        }
    }

    private val PICK_IMAGE_FROM_GALLERY =1
    private var pathImage : Uri? = null
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data!=null){
            // Case user select Image from Gallery
            if (requestCode == PICK_IMAGE_FROM_GALLERY && requestCode == AppCompatActivity.RESULT_OK){
                val profileUser = activity?.findViewById<ImageView>(R.id.img_profile)
                pathImage = data.data
                profileUser?.setImageURI(pathImage)
            }


        }
    }
}