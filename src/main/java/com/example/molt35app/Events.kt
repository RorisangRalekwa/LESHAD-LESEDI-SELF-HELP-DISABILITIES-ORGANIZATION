package com.example.molt35app

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.molt35app.databinding.ActivityEventsBinding

class Events : AppCompatActivity() {
    private lateinit var binding: ActivityEventsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEventsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //return home
        binding.button5.setOnClickListener {
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
        }

        // Example data
        val eventList = listOf(
            Event("Annual Picnic", "Date Time 1", "Location 1"),
            Event("Teens Fun-Day", "Date Time 2", "Location 2"),
            Event("MOLT Conference", "Date Time 3", "Location 3")
        )

        // Set up RecyclerView
        binding.eventsRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.eventsRecyclerView.adapter = EventAdapter(eventList)
    }
}
