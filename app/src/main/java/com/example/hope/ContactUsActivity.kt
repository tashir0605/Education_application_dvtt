package com.example.hope

import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class ContactUsActivity : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.contact_us)

        db = FirebaseFirestore.getInstance()

        val nameEditText: EditText = findViewById(R.id.nameEditText)
        val emailEditText: EditText = findViewById(R.id.emailEditText)
        val messageEditText: EditText = findViewById(R.id.messageEditText)
        val sendMessageButton: TextView = findViewById(R.id.sendMessageButton)

        sendMessageButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val email = emailEditText.text.toString()
            val message = messageEditText.text.toString()

            if (name.isNotEmpty() && email.isNotEmpty() && message.isNotEmpty()) {
                // Create a new feedback object
                val feedback = hashMapOf(
                    "name" to name,
                    "email" to email,
                    "message" to message
                )

                // Add the feedback to Firestore
                db.collection("feedback")
                    .add(feedback)
                    .addOnSuccessListener {
                        // Show a toast message
                        Toast.makeText(this, "Thank you for your feedback", Toast.LENGTH_SHORT).show()

                        // Clear the input fields
                        nameEditText.text.clear()
                        emailEditText.text.clear()
                        messageEditText.text.clear()
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(this, "Failed to send feedback: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
            } else {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
