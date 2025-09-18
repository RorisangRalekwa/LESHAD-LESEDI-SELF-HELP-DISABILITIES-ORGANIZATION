package com.example.molt35app

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.molt35app.databinding.ActivityContactBinding

class Contact : AppCompatActivity() {
    private lateinit var binding: ActivityContactBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupSocialMediaButtons()

        //return home
        binding.button5.setOnClickListener {
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
        }
    }

    private fun setupSocialMediaButtons() {
        binding.facebookButton.setOnClickListener {
            openUrl("https://www.facebook.com/profile.php?id=100083503761278&mibextid=LQQJ4d")
        }
        binding.twitterButton.setOnClickListener {
            openUrl("https://www.twitter.com/mattersoflifewithlady@molt35")
        }
        binding.instagramButton.setOnClickListener {
            openUrl("https://www.instagram.com/mattersoflifewithladyt/")
        }
    }

    private fun openUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }
}
