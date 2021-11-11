package com.example.equimpcontrol

import android.app.Activity
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.equimpcontrol.database.AudiencDatabase

class MainActivity : AppCompatActivity() {
    private var numberGroup: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editTextSearch : EditText = findViewById(R.id.editTextSearcher)
        val btnSearch : Button = findViewById(R.id.buttonSearch)
        btnSearch.setOnClickListener {

            numberGroup = editTextSearch.text.toString()
            getTextFromDB()
            if (numberGroup != null) { //Если этот номер кабинета есть в базе данных, то переходим на следующий activity
                val intent = Intent(this@MainActivity, SearchActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(applicationContext, "Кабинет не найден", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun getTextFromDB()
    {
        val db : AudiencDatabase = AudiencDatabase(this)
        val cheackExistsDB = db.checkDataBase()
        val tmp = db.checkAudiences(numberGroup!!.toInt(), db.openDatabase())
    }
}