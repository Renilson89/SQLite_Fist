package com.example.bd_sqlite.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log


class DatabaseHelper(context: Context): SQLiteOpenHelper(context, "loja.db",null,1) {

    companion object{
        const val TABELA_PRODUTOS = "produtos"
        const val ID_PRODUTOS = "id_produto"
        const val TITULO = "titulo"
        const val DESCRICAO = "descricao"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val sql = "CREATE TABLE IF NOT EXISTS $TABELA_PRODUTOS(\n" +
                "\t$ID_PRODUTOS integer NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                "  \t$TITULO varchar(100),\n" +
                "  \t$DESCRICAO text\n" +
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