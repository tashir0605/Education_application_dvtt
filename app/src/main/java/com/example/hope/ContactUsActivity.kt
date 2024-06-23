package com.example.hope

import android.os.Bundle

import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ContactUsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.contact_us)

        val nameEditText: EditText = findViewById(R.id.nameEditText)
        val emailEditText: EditText = findViewById(R.id.emailEditText)
        val messageEditText: EditText = findViewById(R.id.messageEditText)
        val sendMessageButton: TextView = findViewById(R.id.sendMessageButton)

        sendMessageButton.setOnClickListener {
            // Show a toast message
            Toast.makeText(this, "Thank You for your Feedback", Toast.LENGTH_SHORT).show()

            // Clear the input fields
            nameEditText.text.clear()
            emailEditText.text.clear()
            messageEditText.text.clear()
        }
    }
}