package com.example.equimpcontrol.login

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.os.Environment
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.equimpcontrol.MainActivity
import com.example.equimpcontrol.R
import com.example.equimpcontrol.database.Database
import java.io.File
import java.sql.SQLException

class LoginActivity : AppCompatActivity()  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val db : Database = Database(this)
        val database : SQLiteDatabase = db.getWritableDatabase()
        val tmp = db.checkDataBase()

        val buttonLogin : Button = findViewById(R.id.login)
        buttonLogin.setOnClickListener {
            val userData = db.readDatabase(database)
            intentMainMenu()
        }
        buttonLogin.isEnabled = true
    }
    private fun intentMainMenu()
    {
        val loginEditText : EditText = findViewById(R.id.username)
        val passwordEditText : EditText = findViewById(R.id.password)
        val loginModel = LoginModel()
        if (loginModel.login(loginEditText.text.toString(), passwordEditText.text.toString())) {
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intent)
        }
        else {
            Toast.makeText(applicationContext, "Неправильный пароль", Toast.LENGTH_SHORT).show()
        }
    }
    
}
