package com.example.molt35app

import android.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
//import com.google.firebase.storage.FirebaseStorage
//import com.google.firebase.storage.StorageReference
import com.example.molt35app.databinding.ActivityVolunteerBinding
import com.google.firebase.auth.FirebaseAuth

class Volunteer : AppCompatActivity() {
    private lateinit var binding: ActivityVolunteerBinding
    private lateinit var volunteerOpportunities: List<String>
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var firebaseDatabase: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVolunteerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        firebaseDatabase = FirebaseDatabase.getInstance()

        // Fetch volunteer opportunities from backend
        fetchVolunteerOpportunities()

        // add opportunities
        populateDropdownMenu()

        // Handle submit button click
        binding.submitButton.setOnClickListener {
            signUpForVolunteerOpportunity()
        }

        //return home
        binding.button5.setOnClickListener {
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
        }
    }

    private fun fetchVolunteerOpportunities() {
        volunteerOpportunities = listOf(
            "Helping at Food Bank",
            "Community Cleanup",
            "Tutoring Students",
            // i will add more volunteer opportunities as needed
        )
    }

    private fun populateDropdownMenu() {
        // Populate dropdown menu with volunteer opportunities
        val adapter = ArrayAdapter(this, R.layout.simple_spinner_item, volunteerOpportunities)
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        binding.dropdownMenu.adapter = adapter
    }

    private fun signUpForVolunteerOpportunity() {
        val name = binding.nameEditText.text.toString().trim()
        val contactDetails = binding.contactDetailsEditText.text.toString().trim()
        val selectedOpportunity = volunteerOpportunities[binding.dropdownMenu.selectedItemPosition]

        if (name.isEmpty() || contactDetails.isEmpty()) {
            Toast.makeText(applicationContext, "Please fill out all fields", Toast.LENGTH_SHORT).show()
            return
        }

        // Store volunteer sign-up data in Firebase Realtime Database
        val userId = firebaseAuth.currentUser?.uid
        if (userId != null) {
            val databaseReference = firebaseDatabase.reference.child("volunteer_signups").child(userId)
            val signUpData = mapOf(
                "name" to name,
                "contactDetails" to contactDetails,
                "opportunity" to selectedOpportunity
            )
            databaseReference.setValue(signUpData).addOnSuccessListener {
                Toast.makeText(applicationContext, "Signed up for opportunity: $selectedOpportunity", Toast.LENGTH_SHORT).show()
                finish() // Close the activity after successful sign-up
            }.addOnFailureListener {
                Toast.makeText(applicationContext, "Failed to sign up for opportunity", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(applicationContext, "User not authenticated", Toast.LENGTH_SHORT).show()
        }
    }
}
