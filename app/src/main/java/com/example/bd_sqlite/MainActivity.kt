package com.example.bd_sqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
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
        produtoDAO.remover(9)

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

        var texto = ""
        if(listaProdutos.isNotEmpty()){
            listaProdutos.forEach{produto ->
                texto += "${produto.idProduto} - ${produto.titulo} \n"
                Log.i("info_db", "${produto.idProduto} - ${produto.titulo}")
            }
            binding.txtResultado.text = texto
        } else {
            binding.txtResultado.text = "Nenhum item cadastrado!"
        }
    }

    private fun salvar() {
        val titulo = binding.editProduto.textAlignment.toString()
        val produtoDAO = ProdutoDAO(this)
        val produto = Produto( -1, titulo, "descricacao...")
       if (produtoDAO.salvar(produto)){
           Toast.makeText(
               this,
               "Sucesso ao cadastrar o Produto",
               Toast.LENGTH_SHORT).show()
               // binding.editProduto.setText("")
       }else {
           Toast.makeText(
               this,
               "Erro ao cadastrar o Produto",
               Toast.LENGTH_SHORT).show()
       }

    }


    }


