package com.example.hope

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class bac : AppCompatActivity() {
    private var recyclerView: RecyclerView? = null
    private var recyclerViewMovieAdapter: RecyclerViewMovieAdapter? = null
    private var movieList = mutableListOf<Movie>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bac)

        movieList = ArrayList()
        recyclerView = findViewById<View>(R.id.rvMovieLists) as RecyclerView
        recyclerViewMovieAdapter = RecyclerViewMovieAdapter(this@bac, movieList)
        var layoutManager: RecyclerView.LayoutManager = GridLayoutManager(this, 2)
        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.adapter = recyclerViewMovieAdapter

        prepareMovieListData()
    }
    private fun prepareMovieListData() {
        var movie= Movie("Dramebaaz", R.drawable.dramebaaz)
        movieList.add(movie)
        movie= Movie("Ateliers", R.drawable.attelies)
        movieList.add(movie)
        movie= Movie("Raw", R.drawable.raw)
        movieList.add(movie)
        movie= Movie("Sangam", R.drawable.sangam)
        movieList.add(movie)
        movie= Movie("Designerds", R.drawable.designerds)
        movieList.add(movie)
        movie= Movie("Framex", R.drawable.frame)
        movieList.add(movie)
        movie= Movie("Tgt", R.drawable.tgt)
        movieList.add(movie)
        movie= Movie("Shutterbugs", R.drawable.shutterbugs)
        movieList.add(movie)

        recyclerViewMovieAdapter!!.notifyDataSetChanged()

    }

}