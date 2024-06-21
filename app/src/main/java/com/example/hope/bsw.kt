package com.example.hope

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore

class bsw : AppCompatActivity() {

    private lateinit var recyclerView1: RecyclerView
    private lateinit var recyclerView2: RecyclerView
    private lateinit var recyclerView3: RecyclerView
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bsw)

        db = FirebaseFirestore.getInstance()

        recyclerView1 = findViewById(R.id.headLists)
        recyclerView2 = findViewById(R.id.assistantheadsLists)
        recyclerView3 = findViewById(R.id.sgLists)

        setupRecyclerView(recyclerView1)
        setupRecyclerView(recyclerView2)
        setupRecyclerView(recyclerView3)

        fetchItems("Collection1", recyclerView1)
        fetchItems("collection2", recyclerView2)
        fetchItems("collection3", recyclerView3)
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        recyclerView.layoutManager = GridLayoutManager(this,2)
        recyclerView.setHasFixedSize(true)
    }

    private fun fetchItems(collection: String, recyclerView: RecyclerView) {
        db.collection(collection).get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val itemList = task.result?.map { document ->
                        Lists(
                            title = document.getString("title"),
                            number = document.getString("number"),
                            imageUrl = document.getString("imageUrl") // Assuming imageUrl is a field
                        )
                    } ?: emptyList()

                    val adapter = RecyclerViewAdapter(this, itemList)
                    recyclerView.adapter = adapter
                } else {
                    // Handle error
                }
            }
    }
}
