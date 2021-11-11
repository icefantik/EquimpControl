package com.example.equimpcontrol

import android.app.Activity
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.equimpcontrol.database.AudiencDatabase
import com.example.equimpcontrol.database.EmployeDatabase

class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_searchres)
        val button : Button = findViewById(R.id.editButton)
        button.setOnClickListener {
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
        val textView : TextView = findViewById(R.id.descriptText)
        textView.text = text
    }
}
