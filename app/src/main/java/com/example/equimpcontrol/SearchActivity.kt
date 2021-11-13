package com.example.equimpcontrol

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.equimpcontrol.database.DBEquimpControl

class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_searchres)
        val extraNumberGroup : Int = intent.getSerializableExtra("ExtraNumberGroup") as Int
        val db = DBEquimpControl(this)
        db.openDatabase()
        val equipString = db.getTextEquipAudienc(extraNumberGroup)
        setTextView(equipString)
        val button : Button = findViewById(R.id.editButton)
        button.setOnClickListener {
            val intent = Intent(this@SearchActivity, EditActivity::class.java)
            intent.putExtra("EquipString", equipString)
            intent.putExtra("ExtraNumberGroup", extraNumberGroup)
            startActivity(intent)
        }
        
    }
    @SuppressLint("ResourceAsColor")
    private fun setTextView(text : String)
    {
        val tv = TextView(this)
        tv.text = text
        tv.setTextSize(25F)
        tv.setTextColor(R.color.black)
        val numAudienc : ScrollView = findViewById(R.id.descriptText)
        numAudienc.addView(tv)
    }
}
