package com.example.molt35app

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.molt35app.databinding.ActivityNewsBinding

class News : AppCompatActivity() {
    private lateinit var binding: ActivityNewsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //return home
        binding.button5.setOnClickListener {
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
        }

        // articles data
        val newsList = listOf(
            NewsArticle("Who Are We?", "At Matters of Life, we are dedicated to empowering young girls and boys to reach their full potential. Our foundation provides essential support, resources, and opportunities that help shape brighter futures. Through educational programs, mentorship, and community events, we strive to create a nurturing environment where every child can thrive.\n" +
                    "Join us on our mission to make a difference. Whether you wish to donate, volunteer, or participate in our events, your involvement makes a powerful impact. Together, we can inspire and uplift the next generation."),
            NewsArticle("Our Mission", "At Matters of Life, our mission is to empower young girls and boys to achieve their full potential by providing them with the support, resources, and opportunities they need to succeed. We believe that every child deserves a chance to thrive, regardless of their background or circumstances.\n"),
            NewsArticle("Our Vision", "We envision a world where all young people have access to the tools and guidance necessary to become confident, capable, and compassionate individuals. Through our programs and initiatives, we aim to build a community where children feel valued, supported, and inspired to pursue their dreams.\n")
        )

        // Set up RecyclerView
        binding.newsRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.newsRecyclerView.adapter = NewsAdapter(newsList)
    }
}
