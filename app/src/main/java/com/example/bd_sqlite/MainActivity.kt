package com.example.bd_sqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bd_sqlite.database.DatabaseHelper

class MainActivity : AppCompatActivity() {

    private val bancoDados by lazy {
        DatabaseHelper(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        try {
            bancoDados.writableDatabase.execSQL(
                "INSERT INTO produtos VALUES(null, 'Note', 'Descricação.');"
            )
        }catch (e: Exception){

        }
    }
}