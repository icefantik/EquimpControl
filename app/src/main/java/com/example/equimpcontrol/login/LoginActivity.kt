package com.example.equimpcontrol.login

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.equimpcontrol.MainActivity
import com.example.equimpcontrol.R
import com.example.equimpcontrol.database.EmployeDatabase

class LoginActivity : AppCompatActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val buttonLogin : Button = findViewById(R.id.login)
        buttonLogin.setOnClickListener {
            intentMainMenu()
        }
        buttonLogin.isEnabled = true
    }
    private fun intentMainMenu()
    {
        val loginEditText : EditText = findViewById(R.id.username)
        val passwordEditText : EditText = findViewById(R.id.password)
        val loginModel = LoginModel()

        val db : EmployeDatabase = EmployeDatabase(this)
        val database : SQLiteDatabase = db.getWritableDatabase()
        //val tmp = db.checkDataBase()
        if (loginModel.login(loginEditText.text.toString(), passwordEditText.text.toString()) && db.checkLoginPassword(loginEditText.text.toString(), passwordEditText.text.toString(), database)) {
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intent)
        } else {
            Toast.makeText(applicationContext, "Неправильный пароль", Toast.LENGTH_SHORT).show()
        }
    }
}
