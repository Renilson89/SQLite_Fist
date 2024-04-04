package com.example.bd_sqlite.database

import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.example.bd_sqlite.model.Produto

class ProdutoDAO(context: Context) : IProdutoDAO {

   private val escrita = DatabaseHelper(context).writableDatabase
   private val leitura = DatabaseHelper(context).readableDatabase


    override fun salvar(produto: Produto): Boolean {
        //val titulo = produto.titulo
       // val sql = "INSERT INTO produtos VALUES(null, '$titulo ', 'Descricação.');"
       val valores = ContentValues()
        valores.put("${DatabaseHelper.TITULO}", produto.titulo)
        valores.put("${DatabaseHelper.DESCRICAO}", produto.descricao)
        try {
            //escrita.execSQL(sql)
            escrita.insert(
                DatabaseHelper.TABELA_PRODUTOS,
                null,
                valores
            )
            Log.i("info_db", "Sucesso ao Inserir")

        }catch (e: Exception){
            Log.i("info_db", "Erro ao Inserir")
            return false
        }
        return true
    }

    override fun atualizar(produto: Produto): Boolean {
        /*val titulo = produto.titulo
        val idProduto = produto.idProduto
        val sql = "UPDATE ${DatabaseHelper.TABELA_PRODUTOS} " +
                "  SET ${DatabaseHelper.TITULO} = '$titulo' WHERE ${DatabaseHelper.ID_PRODUTOS} = $idProduto;"*/
        val valores = ContentValues()
        valores.put("${DatabaseHelper.TITULO}", produto.titulo)
        valores.put("${DatabaseHelper.DESCRICAO}", produto.descricao)
        val args = arrayOf(produto.idProduto.toString())
        try {
            escrita.update(DatabaseHelper.TABELA_PRODUTOS, valores,
             "id_produto = ?",
                args)
            //escrita.execSQL(sql)
            Log.i("info_db", "Sucesso ao Atulizar!!")

        }catch (e: Exception){
            Log.i("info_db", "Erro ao Atualizar")
            return false
        }
        return true
    }

    override fun remover(idProduto: Int): Boolean {
        /*val sql = "DELETE FROM ${DatabaseHelper.TABELA_PRODUTOS} WHERE ${DatabaseHelper.ID_PRODUTOS} = $idProduto"*/
        val args = arrayOf(idProduto.toString())
        try {
            //escrita.execSQL(sql)
            escrita.delete(DatabaseHelper.TABELA_PRODUTOS,
                "id_produto = ?",
                args
            )
            Log.i("info_db", "Sucesso ao Remover")

        }catch (e: Exception){
            Log.i("info_db", "Erro ao Remover")
            return false
        }
        return false
    }

    override fun listar(): List<Produto> {
        val listaProdutos = mutableListOf<Produto>()

        val sql = "SELECT * FROM ${DatabaseHelper.TABELA_PRODUTOS};"
        val cursor = leitura.rawQuery(sql, null)

        val indiceId = cursor.getColumnIndex("${DatabaseHelper.ID_PRODUTOS}")
        val indiceTitulo = cursor.getColumnIndex("${DatabaseHelper.TITULO}")
        val indiceDescricao = cursor.getColumnIndex("${DatabaseHelper.DESCRICAO}")

        while (cursor.moveToNext()){
            val idProduto = cursor.getInt(indiceId)
            val titulo = cursor.getString(indiceTitulo)
            val descricao = cursor.getString(indiceDescricao)
            //Log.i("info_db", "id: $idProduto - $titulo")
            val produto =
            listaProdutos.add(Produto(idProduto, titulo, descricao))
        }
        return listaProdutos
    }

}