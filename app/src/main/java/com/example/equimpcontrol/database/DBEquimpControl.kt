package com.example.equimpcontrol.database

import android.annotation.SuppressLint
import android.content.ContentValues
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

    public fun getEquipType() : ArrayList<String>
    {
        val equipTypes = ArrayList<String>()
        val query : String = "SELECT * FROM ${DB_TABLE_EQUIP}"
        val cursor : Cursor = myDataBase!!.rawQuery(query, null)
        while (cursor.moveToNext()) {
            equipTypes.add(cursor.getString(cursor.getColumnIndex("ID")) + ": " + cursor.getString(cursor.getColumnIndex("NAME")))
        }
        return equipTypes
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

    public fun checkIDEquip(IDEquip : Int?) : Boolean
    {
        DB_COLUMN = "ID"
        val query : String = "SELECT " + DB_COLUMN + " FROM " + DB_TABLE_EQUIP +
                " WHERE " + DB_COLUMN + " LIKE " + IDEquip
        val cursor : Cursor = myDataBase!!.rawQuery(query, null)
        if (cursor.count == 0) {
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

    public fun updateDataOnEquipID(idEquip : Int, equipElem: EquipElem)
    {
        DB_COLUMN = "ID"
        val contentValues : ContentValues = ContentValues()
        contentValues.put("EQUIPTYPEID", equipElem.EquipTypeId)
        contentValues.put("NAME", equipElem.Name)
        contentValues.put("DAYOF", equipElem.DayOf)
        contentValues.put("AUDIENCNUM", equipElem.AudiencNum)

       myDataBase!!.update(DB_TABLE_EQUIP, contentValues, "ID = ${idEquip}", null)
    }

    public fun insertDataEquip(equipElem: EquipElem, idEquip: Int)
    {
        myDataBase!!.insert(DB_TABLE_EQUIP, null, contentValuesEquipElem(equipElem, idEquip))
    }

    public fun deleteDataOnEquipID(idEquip : Int)
    {
        myDataBase!!.delete(DB_TABLE_EQUIP, "ID = ${idEquip}", null)
    }

    private fun contentValuesEquipElem(equipElem: EquipElem, idEquip : Int?) : ContentValues
    {
        val contentValues : ContentValues = ContentValues()
        contentValues.put("ID", idEquip)
        contentValues.put("EQUIPTYPEID", equipElem.EquipTypeId)
        contentValues.put("NAME", equipElem.Name)
        contentValues.put("DAYOF", equipElem.DayOf)
        contentValues.put("AUDIENCNUM", equipElem.AudiencNum)
        return contentValues
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
    @SuppressLint("Range")
    public fun getDataForChangeEquip(audiencNumber : Int, idEquip : Int) : EquipElem
    {
        DB_COLUMN = "AUDIENCNUM"
        var equipElem = EquipElem()
        val query = "SELECT * FROM ${DB_TABLE_EQUIP} WHERE ${DB_COLUMN} LIKE ${audiencNumber} AND ID LIKE ${idEquip}"
        val cursor : Cursor = myDataBase!!.rawQuery(query, null)
        cursor.moveToNext()
        equipElem.EquipTypeId = cursor.getInt(cursor.getColumnIndex("EQUIPTYPEID"))
        equipElem.Name = cursor.getString(cursor.getColumnIndex("NAME"))
        equipElem.DayOf = cursor.getString(cursor.getColumnIndex("DAYOF"))
        equipElem.AudiencNum = cursor.getInt(cursor.getColumnIndex("AUDIENCNUM"))
        return equipElem
    }
}