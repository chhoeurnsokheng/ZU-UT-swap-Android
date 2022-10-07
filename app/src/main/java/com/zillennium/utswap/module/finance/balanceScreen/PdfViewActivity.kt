package com.zillennium.utswap.module.finance.balanceScreen

import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.barteksc.pdfviewer.PDFView
import com.zillennium.utswap.R
import com.zillennium.utswap.utils.Constants
import java.io.BufferedInputStream
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class PdfViewActivity : AppCompatActivity() {
    lateinit var pdfView: PDFView

    companion object {
        fun launchProjectInfoActivity(context: Context, filePath: String?) {
            val intent = Intent(context, PdfViewActivity::class.java)
            intent.putExtra("PDFView", filePath)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdf_view)
        pdfView = findViewById(R.id.idPDFView)
        if (intent.hasExtra("PDFView")) {
            val file = intent.extras?.getString("PDFView").toString()

           // RetrievePDFFromURL(pdfView).execute("https://dev-staging.zillionunited.io/Upload/AccountBalance/1665134485-Transaction-report.pdf")


            pdfView.fromAsset(file)
                .enableSwipe(true)
                .swipeHorizontal(true)
                .enableDoubletap(true)
                .defaultPage(0)
                .enableAnnotationRendering(false)
                .password(null)
                .scrollHandle(null)
                .enableAntialiasing(true)
                .spacing(0)

               // .pageFitPolicy(FitPolicy.WIDTH)
                .load()
        }
    }

    class RetrievePDFFromURL(pdfView: PDFView) :
        AsyncTask<String, Void, InputStream>() {

        val mypdfView: PDFView = pdfView

        override fun doInBackground(vararg params: String?): InputStream? {
            // on below line we are creating a variable for our input stream.
            var inputStream: InputStream? = null
            try {
                // on below line we are creating an url
                // for our url which we are passing as a string.
                val url = URL(params.get(0))

                // on below line we are creating our http url connection.
                val urlConnection: HttpURLConnection = url.openConnection() as HttpsURLConnection

                // on below line we are checking if the response
                // is successful with the help of response code
                // 200 response code means response is successful
                if (urlConnection.responseCode == 200) {
                    // on below line we are initializing our input stream
                    // if the response is successful.
                    inputStream = BufferedInputStream(urlConnection.inputStream)
                }
            }
            // on below line we are adding catch block to handle exception
            catch (e: Exception) {
                // on below line we are simply printing
                // our exception and returning null
                e.printStackTrace()
                return null;
            }
            // on below line we are returning input stream.
            return inputStream;
        }

        // on below line we are calling on post execute
        // method to load the url in our pdf view.
        override fun onPostExecute(result: InputStream?) {
            // on below line we are loading url within our
            // pdf view on below line using input stream.
            mypdfView.fromStream(result).load()
        }
    }
}