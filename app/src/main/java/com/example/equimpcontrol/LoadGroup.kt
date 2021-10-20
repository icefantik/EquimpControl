package com.example.equimpcontrol

import java.sql.*

class LoadGroup {
    val query = "SELECT * FROM group"
    var groups : ArrayList<dataGroups> = TODO()
    fun connection() {
        try {
            val con = DriverManager.getConnection(
                "jdbc:mysql://localhost",
                "",
                ""
            )
            val statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
            val query = statement.executeQuery(query)
        }
        catch (e : SQLException)
        {
            e.printStackTrace()
        }
    }
}
class dataGroups
{
    var numberGroup : ArrayList<Int> = TODO()
    var descriptionGroup : ArrayList<String> = TODO()
}