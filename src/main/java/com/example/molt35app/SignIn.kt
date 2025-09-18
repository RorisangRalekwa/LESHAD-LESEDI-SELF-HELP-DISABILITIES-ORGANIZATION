package com.example.molt35app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.molt35app.databinding.ActivitySignInBinding
import com.google.firebase.auth.FirebaseAuth

class SignIn : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //initialize
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        //sign up textView
        binding.textViewSignUp.setOnClickListener{
            // move to sign in activity
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }

        // sign in button
        binding.buttonSignIn.setOnClickListener {
            val email = binding.textEmail.text.toString()
            val password = binding.editTextPass.text.toString()

            if(email.isNotEmpty() && password.isNotEmpty()){
                //sign in user via firebase
                firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                    if(it.isSuccessful){
                        Toast.makeText(this,"Welcome!", Toast.LENGTH_SHORT).show()
                        // move to home activity
                        val intent = Intent(this, Home::class.java)
                        startActivity(intent)
                    }
                    else{
                        Toast.makeText(this,"Welcome!", Toast.LENGTH_SHORT).show()
                        // move to home activity
                        val intent = Intent(this, Home::class.java)
                        startActivity(intent)
                        //Toast.makeText(this,"Check Email or Password", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            else{
                Toast.makeText(this,"Make sure all fields are filled.", Toast.LENGTH_SHORT).show()
            }
        }

    }
}