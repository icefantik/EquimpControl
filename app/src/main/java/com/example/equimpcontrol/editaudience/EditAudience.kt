package com.example.equimpcontrol.editaudience

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.equimpcontrol.R

class EditAudience : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_audience)
        val saveButton : Button = findViewById(R.id.saveButton2)
        saveButton.setOnClickListener {
            
        }
    }
}