package com.gabrieldias.produtos.service

import com.gabrieldias.produtos.entity.ProdutoEntity

interface ProdutoInterface {

    fun getProduto(produtoId: Int): ProdutoEntity
    fun putProduto(produto: ProdutoEntity): ProdutoEntity
    fun postProduto(produto: ProdutoEntity): ProdutoEntity
    fun deleteProduto(produtoId: Int)

}