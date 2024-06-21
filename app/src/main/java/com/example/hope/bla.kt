package com.example.hope

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class bla : AppCompatActivity() {
    private var recyclerView: RecyclerView? = null
    private var recyclerViewMovieAdapter: RecyclerViewMovieAdapter? = null
    private var movieList = mutableListOf<Movie>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_bcca)

        recyclerView = findViewById(R.id.rvMovieLists)
        recyclerViewMovieAdapter = RecyclerViewMovieAdapter(this@bla, movieList)
        recyclerView!!.layoutManager = GridLayoutManager(this, 2)
        recyclerView!!.adapter = recyclerViewMovieAdapter

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        prepareMovieListData()
    }

    private fun prepareMovieListData() {

        var movie = Movie("Quiz Society", R.drawable.quiz)
        movieList.add(movie)
        movie = Movie("Pheme", R.drawable.pheme)
        movieList.add(movie)
        movie = Movie("LitSoc", R.drawable.littsoc)
        movieList.add(movie)

        recyclerViewMovieAdapter!!.notifyDataSetChanged()
    }
}