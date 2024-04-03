package com.example.bd_sqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.bd_sqlite.database.DatabaseHelper
import com.example.bd_sqlite.database.ProdutoDAO
import com.example.bd_sqlite.databinding.ActivityMainBinding
import com.example.bd_sqlite.model.Produto

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
            btnAtualizar.setOnClickListener {
                atualizar()
            }
            btnRemover.setOnClickListener {
                remover()
            }
  }

    }

    private fun remover() {

        val produtoDAO = ProdutoDAO(this)
        produtoDAO.remover(3)

    }

    private fun atualizar() {
        val titulo = binding.editProduto.textAlignment.toString()
        val produtoDAO = ProdutoDAO(this)
        val produto = Produto( -1, titulo, "descricacao...")
        produtoDAO.atualizar(produto)
    }

    private fun Listar(){

        val produtoDAO = ProdutoDAO(this)
        val listaProdutos = produtoDAO.listar()
        
        if(listaProdutos.isNotEmpty()){
            listaProdutos.forEach{produto ->
                Log.i("info_db", "${produto.idProduto} - ${produto.titulo}")
            }
        }
    }

    private fun salvar() {
        val titulo = binding.editProduto.textAlignment.toString()
        val produtoDAO = ProdutoDAO(this)
        val produto = Produto( -1, titulo, "descricacao...")
        produtoDAO.salvar(produto)

    }


    }


