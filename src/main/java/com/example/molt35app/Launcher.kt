package com.example.molt35app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Launcher : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher)

        //vars
        val btnSignUp = findViewById<Button>(R.id.btnSignUp)
        val btnSignIn = findViewById<Button>(R.id.btnSignIn)

        // navigate to pages
        btnSignUp.setOnClickListener {
            // move to sign up activity
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }

        btnSignIn.setOnClickListener {
            // move to sign in activity
            val intent = Intent(this, SignIn::class.java)
            startActivity(intent)
        }
    }
}