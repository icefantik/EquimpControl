package com.example.equimpcontrol.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.equimpcontrol.R
import com.example.equimpcontrol.database.DBEquimpControl

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val db = DBEquimpControl(this)
        val openDatabase = db.openDatabase()
        val editTextSearch : EditText = findViewById(R.id.editTextSearcher)
        val btnSearch : Button = findViewById(R.id.buttonSearch)
        btnSearch.setOnClickListener {
            val numberGroup : Int = editTextSearch.text.toString().toInt()
            if (db.checkAudienceNumber(numberGroup) && editTextSearch.text.toString() != "") { //Если этот номер кабинета есть в базе данных, то переходим на следующий activity
                val intent = Intent(this@MainActivity, SearchActivity::class.java)
                intent.putExtra("ExtraNumberGroup", numberGroup)
                startActivity(intent)
            } else {
                Toast.makeText(applicationContext, "Кабинет не найден", Toast.LENGTH_SHORT).show()
            }
        }
        val edtButton : Button = findViewById(R.id.buttonNewEquip)
        edtButton.setOnClickListener {
            val intent = Intent(this@MainActivity, CreateAudienceActivity::class.java)
            startActivity(intent)
        }
    }
}