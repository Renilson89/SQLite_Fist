package com.example.bd_sqlite.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log


class DatabaseHelper(context: Context): SQLiteOpenHelper(context, "loja.db",null,1) {

    override fun onCreate(db: SQLiteDatabase?) {
        val sql = "CREATE TABLE IF NOT EXISTS produtos(\n" +
                "\tid_produto integer NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                "  \ttitulo varchar(100),\n" +
                "  \tdescricao text\n" +
                "  \n" +
                ");"

        try {
            db?.execSQL(sql)
            Log.i("info_db", "Sucesso ao criar a tabela")
        }catch (e: Exception){
            e.printStackTrace()
            Log.i("info_db", "Erro")
        }

    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {

    }

}