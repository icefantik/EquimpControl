package com.example.equimpcontrol.login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.equimpcontrol.MainActivity
import com.example.equimpcontrol.R

class LoginActivity : AppCompatActivity()  {
    val login : String? = null
    val password : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val buttonLogin : Button = findViewById(R.id.login)
        buttonLogin.setOnClickListener {
            openCreateMain()
        }
        buttonLogin.isEnabled = true
    }
    fun openCreateMain()
    {
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        startActivity(intent)
    }
}