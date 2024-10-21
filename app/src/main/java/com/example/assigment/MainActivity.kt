package com.example.assigment

import android.os.Bundle
import android.content.Context
import android.content.Intent
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assigment.R

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ItemAdapter
    private val itemList = mutableListOf("Apple", "Banana", "Orange", "Mango","Strawberry")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPreferences = getSharedPreferences("user_session", Context.MODE_PRIVATE)
        val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)

        if (!isLoggedIn) {
            // If user is not logged in, redirect to LoginActivity
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        setContentView(R.layout.activity_main)

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = ItemAdapter(itemList, { item ->
            // Handle item click
            Toast.makeText(this, "Clicked: $item", Toast.LENGTH_SHORT).show()
        }, { position ->
            // Handle remove button click
            itemList.removeAt(position)
            adapter.notifyItemRemoved(position)
        })

        recyclerView.adapter = adapter

        // Initialize Logout Button
        val logoutButton = findViewById<Button>(R.id.logoutButton)
        logoutButton.setOnClickListener {
            // Clear stored session (e.g., using SharedPreferences)
            val sharedPreferences = getSharedPreferences("user_session", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.clear() // Clear the session
            editor.apply()

            // Navigate to LoginActivity
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish() // Finish current activity to prevent the user from going back
        }
    }

}
