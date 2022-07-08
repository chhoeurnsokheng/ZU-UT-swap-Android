package com.zillennium.utswap.module.finance.balanceScreen.dialog

import android.content.ContentValues
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.provider.MediaStore
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.zillennium.utswap.R
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.databinding.DialogFinanceBalanceBinding
import eightbitlab.com.blurview.RenderScriptBlur
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

class FinanceBalanceDialog: DialogFragment() {

    private var binding: DialogFinanceBalanceBinding? = null

    override fun getTheme(): Int {
        return R.style.AlertDialog
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        binding =
            DataBindingUtil.inflate(inflater, R.layout.dialog_finance_balance, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {

            activity?.apply {
                blurView.setupWith(findViewById<ViewGroup>(android.R.id.content))
                    .setFrameClearDrawable(window.decorView.background)
                    .setBlurAlgorithm(RenderScriptBlur(UTSwapApp.instance))
                    .setBlurRadius(20f)
                    .setBlurAutoUpdate(true)
                    .setHasFixedTransformationMatrix(true)
            }

            closeImage.setOnClickListener{
                dismiss()
            }

            val status = arguments?.getInt("status")

            if (status == 1){
                txtSymbol.visibility = View.GONE
                txtMoneyType.text = "Money In"
                txtDollar.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.success))
                amountBalance.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.success))
            }else{
                txtSymbol.visibility = View.VISIBLE
                txtMoneyType.text = "Money Out"
                txtDollar.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.danger))
                amountBalance.setTextColor(ContextCompat.getColor(UTSwapApp.instance, R.color.danger))
            }

            val length = arguments?.getDouble("amountBalance").toString().length

            if ((arguments?.getDouble("amountBalance").toString()[0] == '-')) {
                if (length > 1) {
                    amountBalance.text = arguments?.getDouble("amountBalance").toString().substring(1, length)
                }
            }else{
                amountBalance.text = arguments?.getDouble("amountBalance").toString()
            }

            titleTransaction.text = arguments?.getString("title_transaction")
            dateTransaction.text = arguments?.getString("date_transaction")

            imgScreenShot.setOnClickListener {
                val bitmap = getScreenShotFromView(view)
                if(bitmap != null)
                {
                    saveMediaToStorage(bitmap)
                }
                Handler().postDelayed({
                    dismiss()
                },1000)
            }

        }
    }

    private fun getScreenShotFromView(v: View): Bitmap? {
        var screenshot: Bitmap? = null
        try {
            screenshot = Bitmap.createBitmap(v.measuredWidth, v.measuredHeight, Bitmap.Config.ARGB_8888)

            val canvas = Canvas(screenshot)
            v.draw(canvas)
        } catch (e: Exception) {
            Log.e("GFG", "Failed to capture screenshot because:" + e.message)
        }
        return screenshot
    }

    private fun saveMediaToStorage(bitmap: Bitmap) {

        val filename = "${System.currentTimeMillis()}.jpg"
        var fos: OutputStream? = null

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            // getting the contentResolver
            UTSwapApp.instance.contentResolver?.also { resolver ->

                val contentValues = ContentValues().apply {
                    put(MediaStore.MediaColumns.DISPLAY_NAME, filename)
                    put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg")
                    put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
                }

                val imageUri: Uri? = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

                fos = imageUri?.let { resolver.openOutputStream(it) }
            }
        } else {
            val imagesDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
            val image = File(imagesDir, filename)
            fos = FileOutputStream(image)
        }

        fos?.use {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it)
            Toast.makeText(UTSwapApp.instance , "Captured View and saved to Gallery" , Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        fun newInstance(
        title: String?,
        date: String?,
        amount: Double,
        status: Int,
        ): FinanceBalanceDialog {
            val financeBalanceDialog = FinanceBalanceDialog()
            val args = Bundle()
            args.putString("title_transaction", title)
            args.putString("date_transaction", date)
            args.putDouble("amountBalance", amount)
            args.putInt("status", status)
            financeBalanceDialog.arguments = args
            return financeBalanceDialog
        }
    }
}