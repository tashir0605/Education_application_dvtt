package com.example.hope

import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.hope.databinding.ActivityPdfopenBinding
import com.github.barteksc.pdfviewer.PDFView
import okhttp3.*
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class pdfopen : AppCompatActivity() {
    private lateinit var binding: ActivityPdfopenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPdfopenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pdfUrl = intent.getStringExtra("pdfUrl")
        if (pdfUrl != null) {
            Log.d("PDFView", "PDF URL received: $pdfUrl")
            showLoading(true)
            downloadPdf(pdfUrl)
        } else {
            Log.e("PDFView", "PDF URL is null")
            showToast("No PDF URL provided")
        }
    }

    private fun downloadPdf(pdfUrl: String) {
        val client = OkHttpClient()
        val request = Request.Builder().url(pdfUrl).build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("PDFView", "Download failed", e)
                runOnUiThread {
                    showLoading(false)
                    showToast("Error downloading PDF")
                }
            }

            override fun onResponse(call: Call, response: Response) {
                if (!response.isSuccessful) {
                    Log.e("PDFView", "Server returned error: ${response.code}")
                    runOnUiThread {
                        showLoading(false)
                        showToast("Error downloading PDF")
                    }
                    return
                }

                val pdfFile = File(getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), "downloaded.pdf")
                try {
                    val sink = FileOutputStream(pdfFile)
                    sink.write(response.body?.bytes())
                    sink.close()
                    runOnUiThread {
                        showLoading(false)
                        displayPdf(pdfFile)
                    }
                } catch (e: Exception) {
                    Log.e("PDFView", "Error saving PDF", e)
                    runOnUiThread {
                        showLoading(false)
                        showToast("Error saving PDF")
                    }
                }
            }
        })
    }

    private fun displayPdf(pdfFile: File) {
        binding.pdfView.fromFile(pdfFile)
            .enableSwipe(true)
            .swipeHorizontal(false)
            .enableAnnotationRendering(true)
            .onLoad {
                Log.d("PDFView", "PDF loaded successfully")
            }
            .onError { t ->
                Log.e("PDFView", "Error loading PDF", t)
                showToast("Error loading PDF")
            }
            .load()
    }

    private fun showLoading(show: Boolean) {
        binding.progressBar.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
