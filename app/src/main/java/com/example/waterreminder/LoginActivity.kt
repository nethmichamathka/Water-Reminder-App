package com.example.waterreminder

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.waterreminder.R

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Get references to the input fields, button, and splash image
        val inputName1 = findViewById<EditText>(R.id.input_name_1)
        val inputName2 = findViewById<EditText>(R.id.input_name_2)
        val loginButton = findViewById<Button>(R.id.login_button)
        val splashImage = findViewById<ImageView>(R.id.splash_image)

        // Handle button click
        loginButton.setOnClickListener {
            val name1 = inputName1.text.toString()
            val name2 = inputName2.text.toString()

            if (name1.isEmpty() || name2.isEmpty()) {
                Toast.makeText(this, R.string.error_empty_fields, Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, R.string.login_successful, Toast.LENGTH_SHORT).show()
                // Optionally, you can perform login logic here
            }

            val intent = Intent(this, PageOneActivity::class.java)
            startActivity(intent)
        }

        // Set splash image visibility (optional, if any dynamic behavior is needed)
        splashImage.setImageResource(R.drawable.splashdrop)
    }
}
