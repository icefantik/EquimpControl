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

    private val Login = "LOGIN"
    private val Pwd = "PWD"
    private val FullName = "FULLNAME"

    private val ID = "ID"
    private val EquioTypeID = "EQUIPTYPEID"
    private val Name = "NAME"
    private val DayOf = "DAYOF"
    private val AudiencNum = "AUDIENCNUM"

    private val EquipID = "EQUIPID"
    private val EquipPartID = "EQUIPPARTID"
    private val Note = "NOTE"

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE ${DB_TABLE_EMPLOYEES} (" +
                "${ID} INTEGER PRIMARY KEY, " +
                "${Login} TEXT, " +
                "${Pwd} TEXT, " +
                "${FullName} TEXT" + ")")
        db?.execSQL("INSERT INTO " + DB_TABLE_EMPLOYEES +
                "(${ID}, ${Login}, ${Pwd}, ${FullName}) VALUES " +
                "(1, \"icefantik\", \"123Qwaz\", \"Митюшин Пётр Алексеевич\"), " +
                "(2, \"Andron\", \"sdelalBDnarus0\", \"Слепов Андрей Дмитреевич\")")

        db?.execSQL("CREATE TABLE ${DB_TABLE_EQUIPTYPE} " +
                "(${ID} INTEGER PRIMARY KEY, ${Name} TEXT)")
        db?.execSQL("INSERT INTO ${DB_TABLE_EQUIPTYPE} (${ID}, ${Name})" +
                " VALUES " +
                "(1, \"Комплектующие компьютера\"), " +
                "(2, \"Комплектующие принтера\")"
        )

        db?.execSQL("CREATE TABLE " + DB_TABLE_EQUIP + "(" +
                "${ID} INTEGER, " +
                "${EquioTypeID} INTEGER, " +
                "${Name} TEXT, " +
                "${DayOf} TEXT, " +
                "${AudiencNum} INTEGER, " +
                "PRIMARY KEY (${EquioTypeID}, ${ID})" +
                "FOREIGN KEY(${EquioTypeID}) REFERENCES " + DB_TABLE_EQUIPTYPE +" (${ID})"+ ")")
        db?.execSQL("INSERT INTO " + DB_TABLE_EQUIP +
                "(${ID}, ${EquioTypeID}, ${Name}, ${DayOf}, ${AudiencNum}) " +
                "VALUES (1, null, \"Монитор Acer K272HLEBD\", \"01.09.2020\", 102), " +
                "(2, 1, \"intel hd690\", \"01.09.2020\", 102), " +
                "(3, 1, \"Nvidia RTX 2080\", \"01.09.2020\", 102), " +
                "(4, 2, \"Cet CET0419\", \"01.09.2020\", 105), " +
                "(5, 1, \"Motherboard ASROCK B450 PRO4 AMD\", \"01.09.2020\", 102), " +
                "(6, 1, \"ATX Zalman S3\", \"01.09.2020\", 102)")

        db?.execSQL("CREATE TABLE " + DB_TABLE_EQUIPPART + "(" +
                "${ID} INTEGER PRIMARY KEY, " +
                "${EquipID} INTEGER, " +
                "${EquipPartID} INTEGER, " +
                "${DayOf} TEXT, " +
                "${Note} TEXT, " +
                "FOREIGN KEY(${EquipPartID}) REFERENCES ${DB_TABLE_EQUIP}(${ID}), " +
                "FOREIGN KEY(${EquipID}) REFERENCES ${DB_TABLE_EQUIP}(${ID}))")
        db?.execSQL("INSERT INTO " + DB_TABLE_EQUIPPART +
                "(${ID}, ${EquipID}, ${EquipPartID}, ${DayOf}, ${Note}) VALUES " +
                "(1, 6, 5, \"01.09.2020\", \"\"), " +
                "(2, 6, 3, \"01.09.2020\", \"\"), " +
                "(3, 6, 2, \"01.09.2020\", \"\")")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }
}