package com.example.hope

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.hope.databinding.ActivityImageUploaderBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class imageUploader : AppCompatActivity() {
    private lateinit var binding: ActivityImageUploaderBinding
    private lateinit var storageRef: StorageReference
    private lateinit var firebaseFirestore: FirebaseFirestore
    private var imageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageUploaderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initFirebase()
        initViews()
        setupClickListeners()
    }

    private fun initFirebase() {
        storageRef = FirebaseStorage.getInstance().reference.child("Images")
        firebaseFirestore = FirebaseFirestore.getInstance()
    }

    private fun initViews() {
        // Initialize any views or UI elements here if needed
    }

    private fun setupClickListeners() {
        binding.uploadBtn.setOnClickListener {
            uploadImage()
        }
        binding.imageView.setOnClickListener {
            selectImageFromGallery()
        }
    }

    private val selectImageLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            imageUri = it
            binding.imageView.setImageURI(it)
        }
    }

    private fun selectImageFromGallery() {
        selectImageLauncher.launch("image/*")
    }

    private fun uploadImage() {
        imageUri?.let { uri ->
            binding.progressBar.visibility = View.VISIBLE
            val imageRef = storageRef.child("${System.currentTimeMillis()}")
            imageRef.putFile(uri)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        imageRef.downloadUrl.addOnSuccessListener { downloadUri ->
                            val imageMap = hashMapOf(
                                "pic" to downloadUri.toString()
                            )
                            firebaseFirestore.collection("images")
                                .add(imageMap)
                                .addOnCompleteListener { firestoreTask ->
                                    if (firestoreTask.isSuccessful) {
                                        Toast.makeText(this, "Uploaded Successfully", Toast.LENGTH_SHORT).show()
                                    } else {
                                        Toast.makeText(this, firestoreTask.exception?.message, Toast.LENGTH_SHORT).show()
                                    }
                                    resetUI()
                                }
                        }
                    } else {
                        Toast.makeText(this, task.exception?.message, Toast.LENGTH_SHORT).show()
                        resetUI()
                    }
                }
        } ?: Toast.makeText(this, "Select an image first", Toast.LENGTH_SHORT).show()
    }

    private fun resetUI() {
        binding.imageView.setImageResource(R.drawable.vector)
        binding.progressBar.visibility = View.GONE
    }
}
