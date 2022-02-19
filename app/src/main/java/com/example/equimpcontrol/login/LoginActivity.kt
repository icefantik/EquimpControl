package com.example.equimpcontrol.login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.equimpcontrol.activity.MainActivity
import com.example.equimpcontrol.R
import com.example.equimpcontrol.database.DBEquimpControl

class LoginActivity : AppCompatActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val db : DBEquimpControl = DBEquimpControl(this)
        db.openDatabase()
        val checkCreateDB = db.checkDataBase()
        val buttonLogin : Button = findViewById(R.id.login)
        buttonLogin.setOnClickListener {
            intentMainMenu(db)
        }
        buttonLogin.isEnabled = true
    }
    private fun intentMainMenu(db : DBEquimpControl)
    {
        val loginEditText : EditText = findViewById(R.id.username)
        val passwordEditText : EditText = findViewById(R.id.password)
        val loginModel = LoginModel()

        if (loginModel.login(loginEditText.text.toString(), passwordEditText.text.toString()) && db.checkLoginPassword(loginEditText.text.toString(), passwordEditText.text.toString())) {
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intent)
        } else {
            Toast.makeText(applicationContext, "Неправильный пароль", Toast.LENGTH_SHORT).show()
        }
    }
}
