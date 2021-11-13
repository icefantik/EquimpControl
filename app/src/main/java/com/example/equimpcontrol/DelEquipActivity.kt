package com.example.equimpcontrol

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.equimpcontrol.database.DBEquimpControl

class DelEquipActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_del_equip)

        val db = DBEquimpControl(this)
        db.openDatabase()
        val equipString = db.getTextEquipAudienc(intent.getSerializableExtra("ExtraNumberGroup") as Int)
        setTextView(equipString)

        val textEdit : TextView = findViewById(R.id.editTextNumberEquip)
        val buttonDel : Button = findViewById(R.id.buttonDel)
        buttonDel.setOnClickListener {
            db.deleteDataOnEquipID(textEdit.text.toString().toInt())
            val intent = Intent(this@DelEquipActivity, SearchActivity::class.java)
            startActivity(intent)
        }
    }
    private fun setTextView(text : String)
    {
        val numAudienc : ScrollView = findViewById(R.id.descriptText2)
        val textView : TextView = TextView(this)
        textView.text = text
        textView.setTextSize(25F)
        numAudienc.addView(textView)
    }
}