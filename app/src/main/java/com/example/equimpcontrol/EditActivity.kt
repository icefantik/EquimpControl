package com.example.equimpcontrol

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class EditActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        val saveButton : Button = findViewById(R.id.saveButton)
        saveButton.setOnClickListener {
            val intent = Intent(this@EditActivity, SearchActivity::class.java)
            startActivity(intent)
        }
    }
}