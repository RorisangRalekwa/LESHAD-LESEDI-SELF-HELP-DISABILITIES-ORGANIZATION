package com.example.molt35app

//import DonateItems
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.molt35app.databinding.ActivityHomeBinding

class Home : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize ViewBinding
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            val intent = Intent(this, Donate::class.java)
            startActivity(intent)
        }
        binding.button2.setOnClickListener {
            val intent = Intent(this, DonateItems::class.java)
            startActivity(intent)
        }
        binding.button3.setOnClickListener {
            val intent = Intent(this, Volunteer::class.java)
            startActivity(intent)
        }
        binding.buttonContact.setOnClickListener {
            val intent = Intent(this, Contact::class.java)
            startActivity(intent)
        }
        binding.buttonEvents.setOnClickListener {
            val intent = Intent(this, Events::class.java)
            startActivity(intent)
        }
        binding.buttonNews.setOnClickListener {
            val intent = Intent(this, News::class.java)
            startActivity(intent)
        }
        binding.buttonAssistance.setOnClickListener {
            val intent = Intent(this, Assistance::class.java)
            startActivity(intent)
        }
        binding.button4.setOnClickListener {
            val url = "https://sites.google.com/d/1ur4zse_ZDbbGGHJb8LKufwICI7rsxVR-/p/1uJZFHr7H1bB2eV9kWQnYVkrtSkbM2lJs/edit?pli=1"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }
    }
}


