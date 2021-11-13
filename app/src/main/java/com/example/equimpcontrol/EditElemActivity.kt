package com.example.equimpcontrol

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class EditElemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_elem)

        val saveButton : Button = findViewById(R.id.saveButton2)
        saveButton.setOnClickListener {

        }
    }
}