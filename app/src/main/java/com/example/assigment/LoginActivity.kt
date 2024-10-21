package com.example.assigment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    // Hardcoded credentials
    private val VALID_EMAIL = "test@example.com"
    private val VALID_PASSWORD = "123456"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val emailInput: EditText = findViewById(R.id.email)
        val passwordInput: EditText = findViewById(R.id.password)
        val loginButton: Button = findViewById(R.id.loginButton)

        // Step 3: Check if user is already logged in
        val sharedPreferences = getSharedPreferences("user_session", Context.MODE_PRIVATE)
        val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)

        if (isLoggedIn) {
            // If user is already logged in, skip login and go to MainActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Close the LoginActivity
        }

        loginButton.setOnClickListener {
            val inputEmail = emailInput.text.toString()
            val inputPassword = passwordInput.text.toString()

            // Check if the credentials are valid
            if (inputEmail == VALID_EMAIL && inputPassword == VALID_PASSWORD) {
                // Step 3: Save login status using SharedPreferences
                val editor = sharedPreferences.edit()
                editor.putBoolean("isLoggedIn", true)
                editor.apply() // Commit changes

                // Credentials are valid, go to MainActivity
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish() // Close the LoginActivity
            } else {
                // Invalid credentials, show error message
                Toast.makeText(this, "Invalid credentials. Please try again.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
