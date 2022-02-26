package com.example.equimpcontrol.database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException

class DBEquimpControl(var context : Context)  {
    private val DB_PATH = "//data//data//com.example.equimpcontrol//databases//"
    private var myDataBase : SQLiteDatabase? = null
    private val dbHelper = MyDBHelper(context)
    private lateinit var typeEquimp : Array<String>

    fun checkDataBase() : Boolean {
        var checkDB: SQLiteDatabase? = null
        try {
            val myPath = DB_PATH + NamesDB.DB_NAME
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY)
        } catch (e: SQLiteException) {
            //onCreate(myDataBase)
        }
        checkDB?.close()
        return checkDB != null
    }

    fun openDatabase() {
        myDataBase = dbHelper.writableDatabase
    }

    @SuppressLint("Range")
    fun checkLoginPassword(login : String, password : String) : Boolean
    {
        val query : String = "SELECT * FROM " + NamesDB.DB_TABLE_EMPLOYEES
        val cursor : Cursor = myDataBase!!.rawQuery(query, null)
        while (cursor.moveToNext()) {
            if (login == cursor.getString(cursor.getColumnIndex("LOGIN")) && password == cursor.getString(cursor.getColumnIndex("PWD")))
                return true
        }
        cursor.close()
        return false
    }

    private fun setTextEquipTypeID(idEquipType: Int) : String
    {
        val query = "SELECT * FROM ${NamesDB.DB_TABLE_EQUIPTYPE} WHERE ID = $idEquipType"
        val cursor : Cursor = myDataBase!!.rawQuery(query, null)
        cursor.moveToNext()
        if (cursor.count > 0)
            return cursor.getString(cursor.getColumnIndex("NAME"))
        return "Неизвестный тип комплектующего"
    }

    fun getEquipType() : ArrayList<String>
    {
        val equipTypes = ArrayList<String>()
        val query = "SELECT * FROM $NamesDB.DB_TABLE_EQUIP"
        val cursor : Cursor = myDataBase!!.rawQuery(query, null)
        while (cursor.moveToNext()) {
            equipTypes.add(cursor.getString(cursor.getColumnIndex("ID")) + ": " + cursor.getString(cursor.getColumnIndex("NAME")))
        }
        return equipTypes
    }

    @SuppressLint("Range")
    fun checkAudienceNumber(audienceNumber : Int) : Boolean // Проверяет есть ли номер аудитории в базе данных
    {
        val query : String = "SELECT " + NamesDB.DB_COLUMN_AUDIENCENUM + " FROM " + NamesDB.DB_TABLE_EQUIP +
                " WHERE " + NamesDB.DB_COLUMN_AUDIENCENUM + " LIKE " + audienceNumber +
                " GROUP BY " + NamesDB.DB_COLUMN_AUDIENCENUM
        val cursor : Cursor = myDataBase!!.rawQuery(query, null)
        return cursor.count > 0
    }

    fun checkIDEquip(IDEquip : Int?) : Boolean
    {
        NamesDB.DB_COLUMN = "ID"
        val query : String = "SELECT " + NamesDB.DB_COLUMN + " FROM " + NamesDB.DB_TABLE_EQUIP +
                " WHERE " + NamesDB.DB_COLUMN + " LIKE " + IDEquip
        val cursor : Cursor = myDataBase!!.rawQuery(query, null)
        if (cursor.count == 0) {
            return true
        }
        return false
    }

    @SuppressLint("Range")
    fun getTextEquipAudience(audienceNumber : Int) : String
    {
        var equipStr = ""
        val query : String = "SELECT * FROM " + NamesDB.DB_TABLE_EQUIP + " WHERE AUDIENCNUM = " + audienceNumber
        val cursor : Cursor = myDataBase!!.rawQuery(query, null)
        while (cursor.moveToNext()) {
            equipStr += cursor.getString(cursor.getColumnIndex("ID")) + ", " +
                    setTextEquipTypeID(cursor.getInt(cursor.getInt(cursor.getColumnIndex("EQUIPTYPEID")))) + ", " +
                    cursor.getString(cursor.getColumnIndex("NAME")) + ", " +
                    cursor.getString(cursor.getColumnIndex("DAYOF")) + ", " +
                    cursor.getString(cursor.getColumnIndex("AUDIENCNUM")) + "\n\n"
        }
        return equipStr
    }

    fun updateDataOnEquipID(idEquip : Int, equipElem: EquipElem)
    {
        NamesDB.DB_COLUMN = "ID"
        val contentValues = ContentValues()
        contentValues.put(MyDBHelper.EquioTypeID, setTextEquipTypeID(equipElem.EquipTypeId!!.toInt()))
        contentValues.put(MyDBHelper.Name, equipElem.Name)
        contentValues.put(MyDBHelper.DayOf, equipElem.DayOf)
        contentValues.put(MyDBHelper.AudiencNum, equipElem.AudiencNum)

       myDataBase!!.update(NamesDB.DB_TABLE_EQUIP, contentValues, "ID = $idEquip", null)
    }

    fun insertDataEquip(equipElem: EquipElem, idEquip: Int)
    {
        myDataBase!!.insert(NamesDB.DB_TABLE_EQUIP, null, contentValuesEquipElem(equipElem, idEquip))
    }

    fun deleteDataOnEquipID(idEquip : Int)
    {
        myDataBase!!.delete(NamesDB.DB_TABLE_EQUIP, "ID = $idEquip", null)
    }

    private fun contentValuesEquipElem(equipElem: EquipElem, idEquip : Int?) : ContentValues
    {
        val contentValues = ContentValues()
        contentValues.put("ID", idEquip)
        contentValues.put("EQUIPTYPEID", setTextEquipTypeID(equipElem.EquipTypeId!!.toInt()))
        contentValues.put("NAME", equipElem.Name)
        contentValues.put("DAYOF", equipElem.DayOf)
        contentValues.put("AUDIENCNUM", equipElem.AudiencNum)
        return contentValues
    }

    fun checkEquimpInAudience(idEquip : Int, audienceNumber: Int) : Boolean
    {
        val query = "SELECT * FROM $NamesDB.DB_TABLE_EQUIP WHERE $NamesDB.DB_COLUMN LIKE $audienceNumber"
        val cursor : Cursor = myDataBase!!.rawQuery(query, null)
        while (cursor.moveToNext()) {
            if (idEquip == cursor.getInt(cursor.getColumnIndex("ID"))) {
                return true
            }
        }
        return false
    }
    @SuppressLint("Range")
    fun getDataForChangeEquip(audienceNumber : Int, idEquip : Int) : EquipElem
    {
        NamesDB.DB_COLUMN = "AUDIENCNUM"
        val equipElem = EquipElem()
        val query = "SELECT * FROM $NamesDB.DB_TABLE_EQUIP WHERE $NamesDB.DB_COLUMN LIKE $audienceNumber AND ID LIKE $idEquip"
        val cursor : Cursor = myDataBase!!.rawQuery(query, null)
        cursor.moveToNext()
        equipElem.EquipTypeId = cursor.getInt(cursor.getColumnIndex("EQUIPTYPEID"))
        equipElem.Name = cursor.getString(cursor.getColumnIndex("NAME"))
        equipElem.DayOf = cursor.getString(cursor.getColumnIndex("DAYOF"))
        equipElem.AudiencNum = cursor.getInt(cursor.getColumnIndex("AUDIENCNUM"))
        return equipElem
    }
    @JvmName("getTypeEquimp1")
    fun getTypeEquimp() : Array<String> {
        val query : String = "SELECT NAME FROM ${NamesDB.DB_TABLE_EQUIPTYPE}"
        val cursor : Cursor = myDataBase!!.rawQuery(query, null)

        return typeEquimp
    }
}