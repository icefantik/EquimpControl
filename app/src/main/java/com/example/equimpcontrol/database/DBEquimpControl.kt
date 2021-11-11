package com.example.equimpcontrol.database

import android.annotation.SuppressLint
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper

class DBEquimpControl(var context : Context) : SQLiteOpenHelper(context, "DatabaseEquimpControl.db", null, 1) {
    private val DB_PATH = "//data//data//com.example.equimpcontrol//databases//"
    private val DB_NAME = "DatabaseEquimpControl.db"
    private var DB_TABEL = ""
    var myDataBase : SQLiteDatabase? = null

    public fun checkDataBase() : Boolean {
        var checkDB: SQLiteDatabase? = null
        try {
            val myPath = DB_PATH + DB_NAME
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY)
        } catch (e: SQLiteException) {
            //база еще не существует
        }
        checkDB?.close()
        return checkDB != null
    }

    public fun openDatabase() : SQLiteDatabase?
    {
        val myPath: String =  DB_PATH + DB_NAME
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY)
        return myDataBase
    }

    @SuppressLint("Range")
    public fun checkLoginPassword(login : String, password : String, db: SQLiteDatabase?) : Boolean
    {
        DB_TABEL = "Employees"
        val query : String = "SELECT * FROM " + DB_TABEL
        val cursor : Cursor = db!!.rawQuery(query, null)
        while (cursor.moveToNext()) {
            if (login == cursor.getString(cursor.getColumnIndex("login")) && password == cursor.getString(cursor.getColumnIndex("password")))
                return true
        }
        cursor.close()
        return false
    }

    @SuppressLint("Range")
    public fun checkAudiences(audiencNumber : Int, db : SQLiteDatabase?)
    {
        DB_TABEL = "AudienceList"
        val query : String = "SELECT * FROM " + DB_TABEL
        val cursor : Cursor = db!!.rawQuery(query, null)
        while (cursor.moveToNext()) {
            //if (audiencNumber == cursor.getInt(cursor.getColumnIndex("AudienceNumber"))) {
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        TODO("Not yet implemented")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

}