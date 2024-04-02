package com.example.bd_sqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.bd_sqlite.database.DatabaseHelper
import com.example.bd_sqlite.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val bancoDados by lazy {
        DatabaseHelper(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding){
            btnSalvar.setOnClickListener{
                salvar()
            }
            btnListar.setOnClickListener {
                Listar()
            }
  }

    }

    private fun Listar(){

        val sql = "SELECT * FROM ${DatabaseHelper.TABELA_PRODUTOS};"
        val cursor = bancoDados.readableDatabase.rawQuery(sql, null)

        val indiceId = cursor.getColumnIndex("${DatabaseHelper.ID_PRODUTOS}")
        val indiceTitulo = cursor.getColumnIndex("${DatabaseHelper.TITULO}")
        val indiceDescricao = cursor.getColumnIndex("${DatabaseHelper.DESCRICAO}")

        while (cursor.moveToNext()){
           val idProduto = cursor.getInt(indiceId)
            val titulo = cursor.getString(indiceTitulo)
            val descricao = cursor.getString(indiceDescricao)
            Log.i("info_db", "id: $idProduto - $titulo")
        }
    }

    private fun salvar() {

        val titulo = binding.editProduto.textAlignment.toString()
        val sql = "INSERT INTO produtos VALUES(null, '$titulo', 'Descricação.');"
        try {
            bancoDados.writableDatabase.execSQL(sql)
            Log.i("info_db", "Sucesso ao Inserir")

        }catch (e: Exception){
            Log.i("info_db", "Erro ao Inserir")
        }
    }


    }


