package com.example.assignment

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assigment.R

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ItemAdapter
    private val itemList = mutableListOf("Apple", "Banana", "Orange", "Mango")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = ItemAdapter(itemList, { item ->
            Toast.makeText(this, "Clicked: $item", Toast.LENGTH_SHORT).show()
        }, { position ->
            itemList.removeAt(position)
            adapter.notifyItemRemoved(position)
        })

        recyclerView.adapter = adapter
    }
}
