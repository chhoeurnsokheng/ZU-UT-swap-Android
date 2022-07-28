package com.zillennium.utswap.module.project.ViewImage

import android.Manifest
import android.annotation.SuppressLint
import android.app.DownloadManager
import android.content.pm.PackageManager
import android.media.Image
import android.net.Uri
import android.os.Environment
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.google.gson.Gson
import com.zillennium.utswap.R
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ImageViewLayoutBinding
import com.zillennium.utswap.models.ViewImageModel
import com.zillennium.utswap.module.project.projectInfoScreen.ProjectInfoView
import java.io.File

class ImageViewActivity


 : BaseMvpActivity<ImageViewView.View, ImageViewView.Presenter, ImageViewLayoutBinding>(),
    ImageViewView.View {
    override val layoutResource: Int = R.layout.image_view_layout
    override var mPresenter: ImageViewView.Presenter = ImageViewPresenter()
    private var data: ViewImageModel.ViewImage? = null
    private val PERMISSION_REQUEST_WRITE_EXTERNAL_STORAGE: Int = 1222
    private var gallery: ArrayList<Image> = arrayListOf()
    private var position: Int = 0
    private var pos: Int = 0

    override fun initView() {
        super.initView()
        window.statusBarColor = ContextCompat.getColor(this, R.color.black)
        supportActionBar?.hide()
        if (intent.hasExtra("VIEW_IMAGE")) {
            try {
                data = Gson().fromJson(intent.getStringExtra("VIEW_IMAGE"), ViewImageModel.ViewImage::class.java)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        initRecyclerView()


        binding.apply {
            ivDownload.setOnClickListener {
                saveImageIntoDevice("UT Swap", data?.position, data?.gallery, PERMISSION_REQUEST_WRITE_EXTERNAL_STORAGE)
            }
            ivClose.setOnClickListener {
                finish()
                this@ImageViewActivity.overridePendingTransition(0, 0)

            }

        }
    }

    @SuppressLint("SetTextI18n")
    private fun initRecyclerView() {
        binding.ivImage.visibility = View.GONE
        val viewImageFromDetailVPA = data?.gallery?.let {
            ViewImageFromDetailVPA(
                supportFragmentManager, 0, it
            )
        }
        binding.imageVp.adapter = viewImageFromDetailVPA
        binding.tag.text = "${(data?.position?.plus(1)).toString()  + " / " + data?.gallery?.size}"
        binding.imageVp.setCurrentItem(data?.position ?: 0, false)
        binding.imageVp.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                binding.tag.text = "${(position + 1).toString() + " / " + data?.gallery?.size}"
                data?.position = position
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })

    }

    private fun saveImageIntoDevice(
        name: String,
        pos: Int?,
        gallery: ArrayList<String>?,
        PERMISSION_REQUEST_WRITE_EXTERNAL_STORAGE: Int
    ) {
        try {
            if (isHasWritePermission()) {
                val filename = "$name.jpg"
                var downloadUrlOfImage = ""
                if (gallery != null) {
                    downloadUrlOfImage = gallery[pos ?: 0]
                }
                val direct = File(
                    Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                        .absolutePath + "/" + "UT Swap" + "/"
                )
                if (!direct.exists()) {
                    direct.mkdir()
                }
                val dm =
                    this.applicationContext.getSystemService(DOWNLOAD_SERVICE) as DownloadManager
                val downloadUri = Uri.parse(downloadUrlOfImage)
                val request = DownloadManager.Request(downloadUri)
                request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
                    .setAllowedOverRoaming(false)
                    .setMimeType("image/jpeg")
                    .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                    .setDestinationInExternalPublicDir(
                        Environment.DIRECTORY_PICTURES,
                        File.separator + "UT Swap" + File.separator + filename
                    )
                dm.enqueue(request)
                val toast: Toast = Toast.makeText(
                    this.applicationContext,
                    "Saved",
                    Toast.LENGTH_SHORT
                )
                toast.show()
            } else {
                requestWriteFile(PERMISSION_REQUEST_WRITE_EXTERNAL_STORAGE)
            }
        } catch (e: Exception) {
            Toast.makeText(
                this,
                "Sorry! We can not save this image,please try again later!",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun requestWriteFile(PERMISSION_REQUEST_WRITE_EXTERNAL_STORAGE: Int) {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
            PERMISSION_REQUEST_WRITE_EXTERNAL_STORAGE
        )
    }

    private fun isHasWritePermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
    }
}