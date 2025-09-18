package com.example.molt35app

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.molt35app.databinding.ActivityAssistanceBinding
import com.google.firebase.database.FirebaseDatabase

class Assistance : AppCompatActivity() {
    private lateinit var binding: ActivityAssistanceBinding
    private lateinit var firebaseDatabase: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAssistanceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseDatabase = FirebaseDatabase.getInstance()

        // Set up spinner with assistance types
        val assistanceTypes = resources.getStringArray(R.array.assistance_types)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, assistanceTypes)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.assistanceTypeSpinner.adapter = adapter

        binding.submitButton.setOnClickListener {
            saveAssistanceRequest()
        }

        //return home
        binding.button5.setOnClickListener {
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
        }
    }

    private fun saveAssistanceRequest() {
        val name = binding.nameEditText.text.toString().trim()
        val contactDetails = binding.contactEditText.text.toString().trim()
        val assistanceType = binding.assistanceTypeSpinner.selectedItem.toString()

        if (name.isEmpty() || contactDetails.isEmpty() || assistanceType.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            return
        }

        val assistanceRequest = AssistanceRequest(name, contactDetails, assistanceType)
        val databaseRef = firebaseDatabase.reference.child("assistance_requests").push()
        databaseRef.setValue(assistanceRequest)
            .addOnSuccessListener {
                Toast.makeText(this, "Request submitted successfully", Toast.LENGTH_SHORT).show()
                clearForm()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Failed to submit request", Toast.LENGTH_SHORT).show()
            }
    }

    private fun clearForm() {
        binding.nameEditText.text.clear()
        binding.contactEditText.text.clear()
        binding.assistanceTypeSpinner.setSelection(0)
    }

    data class AssistanceRequest(val name: String, val contactDetails: String, val assistanceType: String)
}
