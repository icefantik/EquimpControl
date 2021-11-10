package com.example.equimpcontrol.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class AudiencDatabase(var context : Context) : SQLiteOpenHelper(context, "", null, 1) {
    private val DB_PATH = "//data//data//com.example.equimpcontrol//databases//"
    private val DB_NAME = "audiences.db"
    private var DB_TABEL = ""

    public fun checkAudiences(audiencNumber : Int)
    {

    }

    override fun onCreate(db: SQLiteDatabase?) {
        TODO("Not yet implemented")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

}