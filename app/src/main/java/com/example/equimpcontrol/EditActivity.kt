package com.example.equimpcontrol

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.equimpcontrol.database.DBEquimpControl

class EditActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        removeAllTextViews()

        setTextView(intent.getSerializableExtra("EquipString") as String)
        val numberAudienc = intent.getSerializableExtra("ExtraNumberGroup") as Int
        val db = DBEquimpControl(this)
        db.openDatabase()

        val numberEquip : EditText = findViewById(R.id.editTextNumberEquip)
        val buttonFurther : Button = findViewById(R.id.buttonFurther)
        buttonFurther.setOnClickListener {
            if (numberEquip.text.toString() != "" && db.checkEquimpInAudienc(numberEquip.text.toString().toInt(), numberAudienc)) { // Проверка есть ли оборудование под этим id в этой аудитории
                val intent = Intent(this@EditActivity, EditElemActivity::class.java)
                val equipElem = db.getDataForChangeEquip(numberAudienc, numberEquip.text.toString().toInt())
                intent.putExtra("ExtraEquimpId", numberEquip.text.toString().toInt())
                intent.putExtra("ExtraEquipTypeId", equipElem.EquipTypeId)
                intent.putExtra("ExtraEquipName", equipElem.Name)
                intent.putExtra("ExtraEquipDayOf", equipElem.DayOf)
                intent.putExtra("ExtraEquipAudiencNum", equipElem.AudiencNum)
                startActivity(intent)
            } else {
                Toast.makeText(applicationContext, "Введите номер оборудования", Toast.LENGTH_SHORT).show()
            }
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
    private fun removeAllTextViews()
    {
        val numAudienc : ScrollView = findViewById(R.id.descriptText2)
        numAudienc.removeAllViews()
    }
}