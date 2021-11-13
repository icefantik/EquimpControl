package com.example.equimpcontrol.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDBHelper(context : Context) : SQLiteOpenHelper(context, "EquipControl", null, 1) {
    public val DB_NAME = "EquipControl"
    public var DB_TABLE_EMPLOYEES = "Employees"
    public var DB_TABLE_EQUIP = "Equip"
    public var DB_TABLE_EQUIPTYPE = "EquipType"
    public var DB_TABLE_EQUIPPART = "EquipPart"
    public var DB_COLUMN = ""
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE ${DB_TABLE_EMPLOYEES} (" +
                "ID INTEGER PRIMARY KEY, " +
                "LOGIN TEXT, " +
                "PWD TEXT, " +
                "FULLNAME TEXT" + ")")
        db?.execSQL("INSERT INTO " + DB_TABLE_EMPLOYEES +
                "(" + "ID, " + "LOGIN, " + "PWD, " + "FULLNAME" + ") VALUES " +
                "(1, \"icefantik\", \"123Qwaz\", \"Митюшин Пётр Алексеевич\"), " +
                "(2, \"Andron\", \"sdelalBDnarus0\", \"Слепов Андрей Дмитреевич\")")

        db?.execSQL("CREATE TABLE " + DB_TABLE_EQUIPTYPE + "(" +
                "ID INTEGER PRIMARY KEY, " +
                "NAME TEXT" + ")")
        db?.execSQL("INSERT INTO " + DB_TABLE_EQUIPTYPE + "(" +
                "ID, " +
                "NAME" + ")" +
                " VALUES " +
                "(1, \"Комплектующие компьютера\"), " +
                "(2, \"Комплектующие принтера\")"
        )

        db?.execSQL("CREATE TABLE " + DB_TABLE_EQUIP + "(" +
                "ID INTEGER PRIMARY KEY, " +
                "EQUIPTYPEID INTEGER, " +
                "NAME TEXT, " +
                "DAYOF TEXT, " +
                "AUDIENCNUM INTEGER, " +
                "FOREIGN KEY(\"EQUIPTYPEID\") REFERENCES " + DB_TABLE_EQUIPTYPE +" (\"ID\")"+ ")")
        db?.execSQL("INSERT INTO " + DB_TABLE_EQUIP + "(" +
                "ID, " +
                "EQUIPTYPEID, " +
                "NAME, " +
                "DAYOF, " +
                "AUDIENCNUM" + ") VALUES " +
                "(1, null, \"Монитор Acer K272HLEBD\", \"01.09.2020\", 102), " +
                "(2, 1, \"intel hd690\", \"01.09.2020\", 102), " +
                "(3, 1, \"Nvidia RTX 2080\", \"01.09.2020\", 102), " +
                "(4, 2, \"Cet CET0419\", \"01.09.2020\", 105), " +
                "(5, 1, \"Motherboard ASROCK B450 PRO4 AMD\", \"01.09.2020\", 102), " +
                "(6, 1, \"ATX Zalman S3\", \"01.09.2020\", 102)")

        db?.execSQL("CREATE TABLE " + DB_TABLE_EQUIPPART + "(" +
                "ID INTEGER PRIMARY KEY, " +
                "EQUIPID INTEGER, " +
                "EQUIPPARTID INTEGER, " +
                "DAYOF TEXT, " +
                "NOTE TEXT, " +
                "FOREIGN KEY(\"EQUIPPARTID\") REFERENCES \"Equip\"(\"ID\"), " +
                "FOREIGN KEY(\"EQUIPID\") REFERENCES \"Equip\"(\"ID\")" + ")")
        db?.execSQL("INSERT INTO " + DB_TABLE_EQUIPPART + "(" +
                "ID, " +
                "EQUIPID, " +
                "EQUIPPARTID, " +
                "DAYOF, " +
                "NOTE" +
                ") VALUES " +
                "(1, 6, 5, \"01.09.2020\", \"\"), " +
                "(2, 6, 3, \"01.09.2020\", \"\"), " +
                "(3, 6, 2, \"01.09.2020\", \"\")")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

}