package com.example.hope

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class iee : AppCompatActivity() {
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ib)

        db = FirebaseFirestore.getInstance()

        val textViewQuiz = findViewById<TextView>(R.id.quiz)
        val textViewMinor1 = findViewById<TextView>(R.id.minor1)
        val textViewMinor2 = findViewById<TextView>(R.id.minor2)
        val textViewMajor = findViewById<TextView>(R.id.major)
        val textViewProfessor = findViewById<TextView>(R.id.professor_grading_system)
        val textViewNotes = findViewById<TextView>(R.id.notes)

        textViewQuiz.setOnClickListener { openPdf("quiz_ie") }
        textViewMinor1.setOnClickListener { openPdf("minor1_ie") }
        textViewMinor2.setOnClickListener { openPdf("minor2_ie") }
        textViewMajor.setOnClickListener { openPdf("major_ie") }
        textViewProfessor.setOnClickListener { openPdf("professor_ie") }
        textViewNotes.setOnClickListener { openPdf("notes_ie") }
    }

    private fun openPdf(documentId: String) {
        val ref = db.collection("pdfs").document(documentId)
        ref.get()
            .addOnSuccessListener { document ->
                if (document != null && document.exists()) {
                    val pdfUrl = document.getString("url")
                    if (pdfUrl != null) {
                        Log.d("Firestore", "URL retrieved: $pdfUrl")
                        val intent = Intent(this, pdfopen::class.java)
                        intent.putExtra("pdfUrl", pdfUrl)
                        startActivity(intent)
                    } else {
                        Log.d("Firestore", "No URL found in document")
                        showToast("No URL found in document")
                    }
                } else {
                    Log.d("Firestore", "No such document")
                    showToast("Document does not exist")
                }
            }
            .addOnFailureListener { exception ->
                Log.d("Firestore", "get failed with ", exception)
                showToast("Failed to fetch document")
            }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
