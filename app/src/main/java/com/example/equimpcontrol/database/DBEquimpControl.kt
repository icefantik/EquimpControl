package com.example.equimpcontrol.database

import android.annotation.SuppressLint
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper

class DBEquimpControl(var context : Context)  {
    private val DB_PATH = "//data//data//com.example.equimpcontrol//databases//"
    public val DB_NAME = "EquipControl.db"
    public var DB_TABLE_EMPLOYEES = "Employees"
    public var DB_TABLE_EQUIP = "Equip"
    public var DB_TABLE_EQUIPTYPE = "EquipType"
    public var DB_TABLE_EQUIPPART = "EquipPart"
    public var DB_COLUMN = ""
    var myDataBase : SQLiteDatabase? = null
    val dbHelper = MyDBHelper(context)

    public fun checkDataBase() : Boolean {
        var checkDB: SQLiteDatabase? = null
        try {
            val myPath = DB_PATH + DB_NAME
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY)
        } catch (e: SQLiteException) {
            //onCreate(myDataBase)
        }
        checkDB?.close()
        return checkDB != null
    }

    public fun openDatabase()
    {
        myDataBase = dbHelper.writableDatabase
    }

    @SuppressLint("Range")
    public fun checkLoginPassword(login : String, password : String) : Boolean
    {
        val query : String = "SELECT * FROM " + DB_TABLE_EMPLOYEES
        val cursor : Cursor = myDataBase!!.rawQuery(query, null)
        while (cursor.moveToNext()) {
            if (login == cursor.getString(cursor.getColumnIndex("LOGIN")) && password == cursor.getString(cursor.getColumnIndex("PWD")))
                return true
        }
        cursor.close()
        return false
    }

    @SuppressLint("Range")
    public fun checkAudiencNumber(audiencNumber : Int) : Boolean
    {
        DB_COLUMN = "AUDIENCNUM"
        val query : String = "SELECT " + DB_COLUMN + " FROM " + DB_TABLE_EQUIP +
                " WHERE " + DB_COLUMN + " LIKE " + audiencNumber +
                " GROUP BY " + DB_COLUMN
        val cursor : Cursor = myDataBase!!.rawQuery(query, null)
        if (cursor != null && cursor.count > 0) {
            return true
        }
        return false
    }

    @SuppressLint("Range")
    public fun getTextEquipAudienc(audiencNumber : Int) : String
    {
        var index = 0
        var equipStr : String = ""
        val query : String = "SELECT * FROM " + DB_TABLE_EQUIP + " WHERE AUDIENCNUM LIKE " + audiencNumber
        val cursor : Cursor = myDataBase!!.rawQuery(query, null)
        while (cursor.moveToNext()) {
            //equip[index++] = cursor.getString(cursor.getColumnIndex("NAME"))
            equipStr += cursor.getString(cursor.getColumnIndex("ID")) + ", " +
                    cursor.getString(cursor.getColumnIndex("EQUIPTYPEID")) + ", " +
                    cursor.getString(cursor.getColumnIndex("NAME")) + ", " +
                    cursor.getString(cursor.getColumnIndex("DAYOF")) + ", " +
                    cursor.getString(cursor.getColumnIndex("AUDIENCNUM")) + "\n\n"
        }
        return equipStr
    }

    public fun checkEquimpInAudienc(idEquip : Int, audiencNumber: Int) : Boolean
    {
        DB_COLUMN = "AUDIENCNUM"
        val query = "SELECT * FROM ${DB_TABLE_EQUIP} WHERE ${DB_COLUMN} LIKE ${audiencNumber}"
        val cursor : Cursor = myDataBase!!.rawQuery(query, null)
        while (cursor.moveToNext()) {
            if (idEquip == cursor.getInt(cursor.getColumnIndex("ID"))) {
                return true
            }
        }
        return false
    }

    public fun getDataForChangeEquip(audiencNumber : Int) : ArrayList<EquipElem>
    {
        var equipElem = ArrayList<EquipElem>()

        val query = "SELECT * FROM ${DB_TABLE_EQUIP} WHERE ${DB_COLUMN} LIKE ${audiencNumber}"
        val cursor : Cursor = myDataBase!!.rawQuery(query, null)
        while (cursor.moveToNext()) {
            //equipElem.add
        }
        return equipElem

    }
}