package com.example.equimpcontrol.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.os.Environment
import com.example.equimpcontrol.R
import java.io.*
import java.util.zip.ZipEntry
import java.util.zip.ZipInputStream


class Database(var context : Context) : SQLiteOpenHelper(context, "Users.db", null, 1) {
    private val DB_PATH = "//data//data//com.example.equimpcontrol//databases//"
    private val DB_NAME = "Users.db"

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

    private fun openDatabase()
    {
        val myPath: String = DB_PATH + DB_NAME
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE)
    }


    override fun onCreate(db: SQLiteDatabase?) {
        createDataBase()
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    public fun createDataBase() {
        val DB_PATH : File = File(DB_PATH)
        DB_PATH.mkdirs() //создаёт каталог для базы данных
        val db: File = File(DB_PATH, DB_NAME) //проверяем есть ли уже файл БД на карте
        if (!db.exists()) {  //если файла нет, то попытаемся его создать
            db.createNewFile()
        }
        try {
            copyFromZipFile()
        } catch (e: IOException) {

        }
    }
    private fun copyFromZipFile()
    {
        val istream : InputStream = context.getResources().openRawResource(R.raw.databases)
        val outFile : File = File(DB_PATH, DB_NAME)
        val ostream : OutputStream = FileOutputStream(outFile.absolutePath)
        val zis : ZipInputStream = ZipInputStream(BufferedInputStream(istream))

        while (zis.nextEntry != null) {
            var byteArrayOutStream : ByteArrayOutputStream? = null
            var buffer = ByteArray(1024)
            while (zis.read(buffer) != -1) {
                byteArrayOutStream?.write(buffer, 0, zis.read(buffer))
            }
            byteArrayOutStream?.writeTo(ostream)
        }
        zis.close()
        ostream.flush()
        ostream.close()
        istream.close()
    }
}