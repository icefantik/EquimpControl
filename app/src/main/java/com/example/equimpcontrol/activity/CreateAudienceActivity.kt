package com.example.equimpcontrol.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.equimpcontrol.R
import com.example.equimpcontrol.database.DBEquimpControl
import com.example.equimpcontrol.database.EquipElem

class CreateAudienceActivity : AppCompatActivity() {
    lateinit var typeEquimp : Array<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_equip)

        val buttonCreate : Button = findViewById(R.id.editEquip)
        buttonCreate.setOnClickListener {
            getText()
            val intent = Intent(this@CreateAudienceActivity, MainActivity::class.java)
            startActivity(intent)
        }
        val buttonEditEquipEtc : Button = findViewById(R.id.editEquipEtc)
        buttonEditEquipEtc.setOnClickListener {
            getText()
            val intent = Intent(this@CreateAudienceActivity, CreateAudienceActivity::class.java)
            startActivity(intent)
        }
    }
    private fun getText()
    {
        val equipElem = EquipElem()
        var editText : EditText  = findViewById(R.id.editTextEquipId)
        val EquipId = editText.text.toString().toInt()

        editText = findViewById(R.id.editTextEquipTypeId)
        equipElem.EquipTypeId = editText.text.toString().toInt()

        editText = findViewById(R.id.editTextEquipName)
        equipElem.Name = editText.text.toString()

        editText = findViewById(R.id.editTextDayOf)
        equipElem.DayOf = editText.text.toString()

        editText = findViewById(R.id.editTextAudiencNum)
        equipElem.AudiencNum = editText.text.toString().toInt()

        val db = DBEquimpControl(this)
        db.openDatabase()
        db.insertDataEquip(equipElem, EquipId)
    }
}