package com.example.equimpcontrol.database

import android.annotation.SuppressLint
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class AudiencDatabase(var context : Context) : SQLiteOpenHelper(context, "audiences.db", null, 1) {
    private val DB_PATH = "//data//data//com.example.equimpcontrol//databases//"
    private val DB_NAME = "audiences.db"
    private var DB_TABEL = ""
    var myDataBase : SQLiteDatabase? = null

    public fun checkDataBase() : Boolean {
        var checkDB: SQLiteDatabase? = null
        try {
            val myPath = DB_PATH + DB_NAME
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE)
        } catch (e: SQLiteException) {
            //база еще не существует
        }
        checkDB?.close()
        return checkDB != null
    }

    public fun openDatabase() : SQLiteDatabase?
    {
        val myPath: String =  DB_PATH + DB_NAME
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE)
        return myDataBase
    }

    @SuppressLint("Range")
    public fun checkAudiences(audiencNumber : Int, db : SQLiteDatabase?) : Boolean
    {
        DB_TABEL = "AudienceList"
        val query : String = "SELECT * FROM " + DB_TABEL
        val cursor : Cursor = db!!.rawQuery(query, null)
        while (cursor.moveToNext()) {
            if (audiencNumber == cursor.getInt(cursor.getColumnIndex("AudienceNumber"))) {
                
                return true
            }
        }
        return false
    }

    @SuppressLint("Range")
    private fun srcSoftwareAudiences(idAudienceAssign : Int, db : SQLiteDatabase?) : String // назначение аудитории
    {
        DB_TABEL = "Аудитория"
        val query : String = "SELECT * FROM " + DB_TABEL
        val cursor : Cursor = db!!.rawQuery(query, null)
        while (cursor.moveToNext()) {
            if (idAudienceAssign == cursor.getInt(cursor.getColumnIndex("ID номер аудитории"))) {
                return cursor.getString(cursor.getColumnIndex("ПО"))
            }
        }
        return ""
    }

    @SuppressLint("Range")
    private fun srcHardwareAudience(idCharacter : Int, db : SQLiteDatabase?) : String // характеристика -> переферия
    {
        DB_TABEL = "Характеристика"
        var id_hardware : Int? = null
        var query : String = "SELECT * FROM " + DB_TABEL
        var cursor : Cursor = db!!.rawQuery(query, null)
        while (cursor.moveToNext()) {
            if (idCharacter == cursor.getInt(cursor.getColumnIndex("ID характеристики"))) {
                id_hardware = cursor.getInt(cursor.getColumnIndex("ID переферии"))
            }
        }
        DB_TABEL = "Переферия"
        query = "SELECT * FROM " + DB_TABEL
        cursor = db!!.rawQuery(query, null)
        while (cursor.moveToNext()) {
            if (id_hardware == cursor.getInt(cursor.getColumnIndex("ID переферии"))) {

            }
        }
        return ""
    }

    //сделай функцию makeCursore

    override fun onCreate(db: SQLiteDatabase?) {
        TODO("Not yet implemented")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

}