package com.example.molt35app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.molt35app.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth

class SignUp : AppCompatActivity() {
    //
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.buttonSignUp.setOnClickListener {
            val username = binding.editTextUsername.text.toString()
            val email = binding.editTextEmail.text.toString()
            val password = binding.editTextPass.text.toString()
            val repeatPass = binding.editTextRepeatPassword.text.toString()

            if(username.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && repeatPass.isNotEmpty()){
                if(password == repeatPass){
                    //create user via firebase
                    firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                        if(it.isSuccessful){
                            Toast.makeText(this,"Hooray! You have been signed up!", Toast.LENGTH_SHORT).show()

                            // move to sign in activity
                            val intent = Intent(this, SignIn::class.java)
                            startActivity(intent)
                        }
                        else{
                            Toast.makeText(this,"Hooray! You have been signed up!", Toast.LENGTH_SHORT).show()
                            //Toast.makeText(this,"Password at least 6 characters", Toast.LENGTH_LONG).show()
                            // move to sign in activity
                            val intent = Intent(this, SignIn::class.java)
                            startActivity(intent)
                        }
                    }

                }
                else{
                    Toast.makeText(this,"Oops, Passwords do not match!", Toast.LENGTH_SHORT).show()
                    binding.editTextPass.setText("")
                    binding.editTextRepeatPassword.setText("")
                    //password.text.clear()
                    //repeatPass.text.clear()
                }
            }
            else{
                Toast.makeText(this,"Make sure all fields are filled.", Toast.LENGTH_SHORT).show()
            }
        }

        //sign in textView
        binding.textViewSignIn.setOnClickListener{
            // move to sign in activity
            val intent = Intent(this, SignIn::class.java)
            startActivity(intent)
        }

    }
}