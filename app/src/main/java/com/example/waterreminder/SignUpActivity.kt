package com.example.waterreminder

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        // Get references to the input fields and button
        val inputName1 = findViewById<EditText>(R.id.input_name_1)
        val inputName2 = findViewById<EditText>(R.id.input_name_2)
        val inputName3 = findViewById<EditText>(R.id.input_name_3)
        val signUpButton = findViewById<Button>(R.id.signup_button)

        // Handle button click
        signUpButton.setOnClickListener {
            val name1 = inputName1.text.toString()
            val name2 = inputName2.text.toString()
            val name3 = inputName3.text.toString()

            if (name1.isEmpty() || name2.isEmpty() || name3.isEmpty()) {
                Toast.makeText(this, "Please fill out all the fields", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Sign Up Successful", Toast.LENGTH_SHORT).show()
                // Optionally, you could send this data to a backend server or a new activity
            }

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
