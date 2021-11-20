package com.example.recyclerviewproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(val entries: List<RecyclerEntry>) :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView: ImageView? = null
        var headerView: TextView? = null
        var descriptionView: TextView? = null

        init {
            imageView = itemView.findViewById(R.id.imageView)
            headerView = itemView.findViewById(R.id.headerView)
            descriptionView = itemView.findViewById(R.id.descriptionView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_entry, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.headerView?.text = entries[position].header
        holder.descriptionView?.text = entries[position].description
        holder.imageView?.setImageResource(R.drawable.ic_launcher_background)
    }

    override fun getItemCount(): Int {
        return entries.size
    }
}