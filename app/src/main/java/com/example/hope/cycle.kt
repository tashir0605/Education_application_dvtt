package com.example.hope

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class cycle : AppCompatActivity() {
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cycle)

        db = Firebase.firestore

        val headingTextView: TextView = findViewById(R.id.mainHeading)
        val roleofclub: TextView = findViewById(R.id.role)
        val imageView: ImageView = findViewById(R.id.mainlogo)
        val secy: TextView = findViewById(R.id.secyname)
        val secyImg: ImageView = findViewById(R.id.secyimg)

        fetchCycleData { data ->
            headingTextView.text = data["mainheading"] as String?
            roleofclub.text = data["role"] as String?
            Glide.with(this)
                .load(data["mainimage"] as String?)
                .into(imageView)
            Glide.with(this)
                .load(data["secyimg"] as String?)
                .into(secyImg)
            secy.text = data["secyname"] as String?
        }
    }

    private fun fetchCycleData(callback: (Map<String, Any>) -> Unit) {
        db.collection("Clubs")
            .document("cycle")
            .get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    callback(document.data ?: emptyMap())
                } else {
                    // Handle the case where the document does not exist
                }
            }
            .addOnFailureListener { exception ->
                // Handle any errors that occurred during the fetch
            }
    }
}
