package com.example.molt35app


// code for no actual payment
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.molt35app.databinding.ActivityDonateBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class Donate : AppCompatActivity() {
    private lateinit var binding: ActivityDonateBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var firebaseDatabase: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDonateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        firebaseDatabase = FirebaseDatabase.getInstance()

        setupPayment()

        //return home
        binding.button5.setOnClickListener {
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
        }

    }

    private fun setupPayment() {
        binding.donateButton.setOnClickListener {
            val userId = firebaseAuth.currentUser?.uid
            if (userId != null) {
                val amount = binding.amountEditText.text.toString().toDoubleOrNull() ?: 0.0
                saveDonationAmount(userId, amount)
                Toast.makeText(this, "Donation amount saved: $amount", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "User not authenticated", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun saveDonationAmount(userId: String, amount: Double) {
        val donationsRef = firebaseDatabase.reference.child("donations").child(userId)
        donationsRef.setValue(amount)
            .addOnSuccessListener {
                // Donation amount saved successfully
            }
            .addOnFailureListener {
                // Failed to save donation amount
            }
    }
}
/* code for actual payment
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.molt35app.databinding.ActivityDonateBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.stripe.android.PaymentConfiguration
import com.stripe.android.Stripe
import com.stripe.android.model.ConfirmPaymentIntentParams
//import com.google.android.gms.tasks.Task
//import com.stripe.android.model.PaymentIntent

class Donate : AppCompatActivity() {
    private lateinit var binding: ActivityDonateBinding
    private lateinit var stripe: Stripe
    private lateinit var paymentIntentClientSecret: String
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var firebaseDatabase: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDonateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        firebaseDatabase = FirebaseDatabase.getInstance()

        // Initialize the Stripe SDK with publishable key
        PaymentConfiguration.init(
            applicationContext,
            "YOUR_PUBLISHABLE_KEY" // Stripe publishable key
        )

        stripe = Stripe(
            applicationContext,
            PaymentConfiguration.getInstance(applicationContext).publishableKey
        )

        setupPayment()
    }

    private fun setupPayment() {
        paymentIntentClientSecret = "YOUR_CLIENT_SECRET" // client secret from the server

        binding.donateButton.setOnClickListener {
            val paymentMethodId = "pm_card_visa" // payment method ID
            val params = ConfirmPaymentIntentParams.createWithPaymentMethodId(
                paymentMethodId, paymentIntentClientSecret
            )
            stripe.confirmPayment(this, params)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Payment successful, save the amount to Firebase
                        val userId = firebaseAuth.currentUser?.uid
                        if (userId != null) {
                            val amount = binding.amountEditText.text.toString().toDoubleOrNull() ?: 0.0
                            saveDonationAmount(userId, amount)
                        }
                        Toast.makeText(this, "Payment successful", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Payment failed", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }

    private fun saveDonationAmount(userId: String, amount: Double) {
        val donationsRef = firebaseDatabase.reference.child("donations").child(userId)
        donationsRef.setValue(amount)
            .addOnSuccessListener {
                // Donation amount saved successfully
            }
            .addOnFailureListener {
                // Failed to save donation amount
            }
    }
}
 */