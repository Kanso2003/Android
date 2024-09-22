package com.example.assignment

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.assigment.R

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

        loginButton.setOnClickListener {
            val inputEmail = emailInput.text.toString()
            val inputPassword = passwordInput.text.toString()

            // Check if the credentials are valid
            if (inputEmail == VALID_EMAIL && inputPassword == VALID_PASSWORD) {
                // Credentials are valid, go to MainActivity
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                // Invalid credentials, show error message
                Toast.makeText(this, "Invalid credentials. Please try again.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
