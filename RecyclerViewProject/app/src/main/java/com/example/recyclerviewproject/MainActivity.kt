package com.example.recyclerviewproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = RecyclerAdapter(fillList())
    }

    private fun fillList(): List<RecyclerEntry> {
        val list = mutableListOf<RecyclerEntry>()
        for(i in 0..100)
            list.add(RecyclerEntry("$i Description", "$i Header"))
        return list
    }
}