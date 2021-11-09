package com.example.equimpcontrol

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SearchActivity : AppCompatActivity() {
    private val textView : TextView = findViewById(R.id.descriptText)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_searchres)
        val button : Button = findViewById(R.id.editButton)
        button.setOnClickListener {
            getTextView()
            val intent = Intent(this@SearchActivity, EditActivity::class.java)
            startActivity(intent)
        }
    }
    private fun getTextView() : String
    {
        return ""
    }
    private fun setTextView(text : String)
    {
        textView.text = text
    }
}