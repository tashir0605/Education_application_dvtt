package com.example.hope

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RecyclerViewAdapter(
    private val activity: AppCompatActivity,
    private val listOfAh: List<Lists>
) : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.sample_layout, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.MyViewHolder, position: Int) {
        val item = listOfAh[position]
        holder.number.text = item.number
        holder.title.text = item.title
        Glide.with(activity)
            .load(item.imageUrl)
            .into(holder.img)

        holder.itemView.setOnClickListener {
            // Handle item click
        }
    }

    override fun getItemCount(): Int {
        return listOfAh.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.Title)
        val number: TextView = itemView.findViewById(R.id.Number)
        val img: ImageView = itemView.findViewById(R.id.ivMovieImg)
    }
}
