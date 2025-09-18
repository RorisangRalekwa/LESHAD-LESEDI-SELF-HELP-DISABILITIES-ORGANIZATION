package com.example.molt35app

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.molt35app.DonatedItem
import com.example.molt35app.Home
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
//import com.google.firebase.storage.UploadTask
import com.example.molt35app.databinding.ActivityDonateItemsBinding
import java.io.ByteArrayOutputStream
import java.util.*

class DonateItems : AppCompatActivity() {
    private lateinit var binding: ActivityDonateItemsBinding
    private var imageBitmap: Bitmap? = null
    private lateinit var storageReference: StorageReference

    companion object {
        private const val REQUEST_IMAGE_CAPTURE = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDonateItemsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Firebase Storage
        storageReference = FirebaseStorage.getInstance().reference.child("item_images")

        binding.takePhotoButton.setOnClickListener {
            dispatchTakePictureIntent()
        }

        binding.submitButton.setOnClickListener {
            val itemName = binding.itemNameEditText.text.toString()
            val itemDescription = binding.itemDescriptionEditText.text.toString()

            if (itemName.isNotEmpty() && itemDescription.isNotEmpty() && imageBitmap != null) {
                uploadImageToStorage(itemName, itemDescription)
            } else {
                Toast.makeText(this, "Please fill out all fields and take a photo", Toast.LENGTH_LONG).show()
            }
        }

        //return home
        binding.button5.setOnClickListener {
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
        }
    }

    private fun dispatchTakePictureIntent() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePictureIntent.resolveActivity(packageManager) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as? Bitmap
            imageBitmap?.let {
                binding.imageView.setImageBitmap(it)
                this.imageBitmap = it
            }
        }
    }

    private fun uploadImageToStorage(itemName: String, itemDescription: String) {
        val baos = ByteArrayOutputStream()
        imageBitmap?.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val imageData = baos.toByteArray()

        // Generate a random UUID for the image file name
        val imageName = "${UUID.randomUUID()}.jpg"

        val imageRef = storageReference.child(imageName)
        val uploadTask = imageRef.putBytes(imageData)
        uploadTask.addOnSuccessListener {
            // Image uploaded successfully, get the download URL
            imageRef.downloadUrl.addOnSuccessListener { imageUrl ->
                // Save item data to the database
                saveItemToDatabase(itemName, itemDescription, imageUrl.toString())
            }.addOnFailureListener {
                Toast.makeText(this, "Failed to upload image", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            Toast.makeText(this, "Failed to upload image", Toast.LENGTH_SHORT).show()
        }
    }

    private fun saveItemToDatabase(itemName: String, itemDescription: String, imageUrl: String) {
        val databaseReference = FirebaseDatabase.getInstance().reference.child("donated_items")
        val newItemKey = databaseReference.push().key ?: return

        val donatedItem = DonatedItem(itemName, itemDescription, imageUrl)
        databaseReference.child(newItemKey).setValue(donatedItem).addOnSuccessListener {
            Toast.makeText(this, "Item donation submitted: $itemName", Toast.LENGTH_LONG).show()
            // Clear the form fields and image after successful submission
            binding.itemNameEditText.text.clear()
            binding.itemDescriptionEditText.text.clear()
            binding.imageView.setImageBitmap(null)
        }.addOnFailureListener {
            Toast.makeText(this, "Failed to submit donation", Toast.LENGTH_LONG).show()
        }
    }
}
