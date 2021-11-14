package com.example.equimpcontrol

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class CreateAudienceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_audience)
        val buttonCreate : Button = findViewById(R.id.buttonCreateAudience)
        buttonCreate.setOnClickListener {
//            val intent = Intent(this@CreateAudienceActivity, ::class.java)
//            startActivity(intent)
        }
    }
}