package com.example.bd_sqlite.database

import com.example.bd_sqlite.model.Produto

interface IProdutoDAO {
    fun salvar(produto: Produto): Boolean
    fun atualizar(produto: Produto): Boolean
    fun remover(idProduto: Int): Boolean
    fun listar(): List<Produto>

}