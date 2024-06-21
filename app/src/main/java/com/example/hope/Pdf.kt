package com.example.hope

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.documentfile.provider.DocumentFile
import com.example.hope.databinding.ActivityPdfBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class Pdf : AppCompatActivity() {
    private lateinit var binding: ActivityPdfBinding
    private var pdfFileUri: Uri? = null
    private lateinit var storageReference: StorageReference
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        initClickListener()
    }

    private fun init() {
        binding = ActivityPdfBinding.inflate(layoutInflater)
        setContentView(binding.root)

        storageReference = FirebaseStorage.getInstance().reference.child("pdfs")
        firestore = FirebaseFirestore.getInstance()
    }

    private fun initClickListener() {
        binding.selectPdfButton.setOnClickListener {
            launcher.launch("application/pdf")
        }

        binding.uploadBtn.setOnClickListener {
            if (pdfFileUri != null) {
                uploadPdfFile()
            } else {
                Toast.makeText(this, "Please Select Pdf first", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private val launcher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        pdfFileUri = uri
        val fileName = uri?.let { DocumentFile.fromSingleUri(this, it)?.name }
        binding.fileName.text = fileName.toString()
    }

    private fun uploadPdfFile() {
        val fileName = binding.fileName.text.toString()
        val mStorageRef = storageReference.child("${System.currentTimeMillis()}/$fileName")

        pdfFileUri?.let { uri ->
            mStorageRef.putFile(uri).addOnSuccessListener {
                mStorageRef.downloadUrl.addOnSuccessListener { downloadUri ->
                    val pdfFile = hashMapOf(
                        "fileName" to fileName,
                        "fileUrl" to downloadUri.toString()
                    )
                    firestore.collection("pdfs").add(pdfFile)
                        .addOnSuccessListener {
                            pdfFileUri = null
                            binding.fileName.text = resources.getString(R.string.no_pdf_file_selected_yet)
                            Toast.makeText(this, "Successfully Uploaded", Toast.LENGTH_SHORT).show()
                            binding.progressBar.visibility = View.GONE
                        }
                        .addOnFailureListener { err ->
                            Toast.makeText(this, err.message.toString(), Toast.LENGTH_SHORT).show()
                            binding.progressBar.visibility = View.GONE
                        }
                }
            }
                .addOnProgressListener { uploadTask ->
                    val uploadingPercent = (100.0 * uploadTask.bytesTransferred / uploadTask.totalByteCount).toInt()
                    binding.progressBar.progress = uploadingPercent
                    binding.progressBar.visibility = View.VISIBLE
                }
                .addOnFailureListener { err ->
                    Toast.makeText(this, err.message.toString(), Toast.LENGTH_SHORT).show()
                    binding.progressBar.visibility = View.GONE
                }
        }
    }
}
