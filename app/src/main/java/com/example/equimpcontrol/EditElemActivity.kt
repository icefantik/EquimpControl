package com.example.equimpcontrol

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.equimpcontrol.database.DBEquimpControl
import com.example.equimpcontrol.database.EquipElem
import java.util.*

class EditElemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_elem)
        setText()
        val saveButton : Button = findViewById(R.id.saveButton2)
        saveButton.setOnClickListener {
            getText()
            val intent = Intent(this@EditElemActivity, SearchActivity::class.java)
            startActivity(intent)
        }
    }
    private fun setText()
    {
//        var editText : EditText = findViewById(R.id.editTextEquipId)
//        var textInt : Int = intent.getSerializableExtra("ExtraEquipId") as Int
//        editText.setText(textInt.toString(), TextView.BufferType.EDITABLE)

        var editText : EditText = findViewById(R.id.editTextEquipTypeId)
        var textInt = intent.getSerializableExtra("ExtraEquipTypeId") as Int
        editText.setText(textInt.toString())

        editText = findViewById(R.id.editTextEquipName)
        editText.setText(intent.getSerializableExtra("ExtraEquipName") as String)

        editText = findViewById(R.id.editTextDayOf)
        editText.setText(intent.getSerializableExtra("ExtraEquipDayOf") as String)

        editText = findViewById(R.id.editTextAudiencNum)
        textInt = intent.getSerializableExtra("ExtraEquipAudiencNum") as Int
        editText.setText(textInt.toString())
    }

    private fun getText()
    {
        val equipElem = EquipElem()

//        var editText : EditText = findViewById(R.id.editTextEquipId)
//        equipElem.EquipId = editText.text.toString().toInt()

        var editText : EditText  = findViewById(R.id.editTextEquipTypeId)
        equipElem.EquipTypeId = editText.text.toString().toInt()

        editText = findViewById(R.id.editTextEquipName)
        equipElem.Name = editText.text.toString()

        editText = findViewById(R.id.editTextDayOf)
        equipElem.DayOf = editText.text.toString()

        editText = findViewById(R.id.editTextAudiencNum)
        equipElem.AudiencNum = editText.text.toString().toInt()

        val db = DBEquimpControl(this)
        db.openDatabase()

        val id : Int = intent.getSerializableExtra("ExtraEquimpId") as Int
        db.updateDataOnEquipID(id, equipElem)
//        if (equipElem.EquipId != oldIdEquip) {
//            if (db.checkIDEquip(equipElem.EquipId)) {
//
//            } else {
//                Toast.makeText(applicationContext, "Такой ID уже существует", Toast.LENGTH_SHORT).show()
//            }
//        } else {
//
//        }
    }
}