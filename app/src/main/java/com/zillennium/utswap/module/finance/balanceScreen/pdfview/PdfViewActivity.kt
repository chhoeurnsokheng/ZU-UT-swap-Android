package com.zillennium.utswap.module.finance.balanceScreen.pdfview

import android.content.Context
import android.content.Intent
import android.os.Environment
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.downloader.Error
import com.downloader.OnDownloadListener
import com.downloader.PRDownloader
import com.github.barteksc.pdfviewer.PDFView
import com.zillennium.utswap.R
import com.zillennium.utswap.bases.mvp.BaseMvpActivity
import com.zillennium.utswap.databinding.ActivityPdfViewBinding
import java.io.File

class PdfViewActivity :
    BaseMvpActivity<PdfViewView.View, PdfViewView.Presenter, ActivityPdfViewBinding>(),
    PdfViewView.View {

    override val layoutResource: Int = R.layout.activity_pdf_view
    override var mPresenter: PdfViewView.Presenter = PdfViewPresenter()
    lateinit var pdfView: PDFView
    lateinit var progressBar: ProgressBar
    var file = ""
    var tittle = ""
    companion object {
        fun launchProjectInfoActivity(context: Context, filePath: String?, tittle:String?) {
            val intent = Intent(context, PdfViewActivity::class.java)
            intent.putExtra("PDFView", filePath)
            intent.putExtra("Tittle", tittle)
            context.startActivity(intent)
        }
    }

    override fun initView() {
        super.initView()
        binding.apply {
            toolBar()
            pdfView = binding.txtPdfView
            this@PdfViewActivity.progressBar = binding.progressBar

            if (intent.hasExtra("PDFView")) {
                 file = intent.extras?.getString("PDFView").toString()
                PRDownloader.initialize(applicationContext)
                val fileName = "myFile.pdf"
                downloadPdfFromInternet(
                    file,
                    getRootDirPath(this@PdfViewActivity),
                    fileName
                )
            }
        }
    }

    private fun toolBar() {
        setSupportActionBar(binding.includeLayout.tb)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back_primary)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.includeLayout.apply {
            tbTitleLeft.visibility = View.VISIBLE
            tbTitleLeft.setOnClickListener {
                startShareIntent(file)
            }
            if (intent.hasExtra("Tittle")){
                tittle = intent.extras?.getString("Tittle").toString()
                tbTitle.text = tittle
            }

            tbTitle.setTextColor(ContextCompat.getColor(applicationContext, R.color.primary))
            tb.setNavigationOnClickListener {
                finish()
            }
        }
    }
    private fun startShareIntent(shortLink: String) {
        val sharingIntent = Intent(Intent.ACTION_SEND)
        sharingIntent.type = "text/plain"
        sharingIntent.putExtra(Intent.EXTRA_TEXT, shortLink)
        sharingIntent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
        startActivity(Intent.createChooser(sharingIntent, shortLink))

    }
    private fun showPdfFromFile(file: File) {
        pdfView.fromFile(file)
            .password(null)
            .defaultPage(0)
            .enableSwipe(true)
            .swipeHorizontal(false)
            .enableDoubletap(true)
            .onPageError { page, _ ->
                Toast.makeText(
                    this@PdfViewActivity,
                    "Error at page: $page", Toast.LENGTH_LONG
                ).show()
            }
            .load()
    }

    fun getRootDirPath(context: Context): String {
        return if (Environment.MEDIA_MOUNTED == Environment.getExternalStorageState()) {
            val file: File = ContextCompat.getExternalFilesDirs(
                context.applicationContext,
                null
            )[0]
            file.absolutePath
        } else {
            context.applicationContext.filesDir.absolutePath
        }
    }

    private fun downloadPdfFromInternet(url: String, dirPath: String, fileName: String) {
        PRDownloader.download(
            url,
            dirPath,
            fileName
        ).build()
            .start(object : OnDownloadListener {
                override fun onDownloadComplete() {
                    val downloadedFile = File(dirPath, fileName)
                    progressBar.visibility = View.GONE
                    showPdfFromFile(downloadedFile)
                }

                override fun onError(error: Error?) {
                    Toast.makeText(
                        this@PdfViewActivity,
                        "Error in downloading file : $error",
                        Toast.LENGTH_LONG
                    )
                        .show()
                }
            })
    }


}