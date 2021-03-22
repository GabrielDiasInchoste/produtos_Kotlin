package com.gabrieldias.produtos.service

import com.gabrieldias.produtos.entity.ProdutoEntity
import com.gabrieldias.produtos.repositories.ProdutoRepository
import lombok.extern.slf4j.Slf4j
import org.springframework.stereotype.Service

@Slf4j
@Service
class ProdutoService(
    private val produtoRepository : ProdutoRepository
){

    fun getProduto(produtoId : Long): ProdutoEntity{
//        log.info("ProdutoService.getProduto - start - produtoId: {$produtoId}")

        val produtoEntity = produtoRepository.findById(produtoId)
//        log.info("ProdutoService.getProduto - end - produtoId: {$produtoId}")

        if (produtoEntity.isEmpty){
            return produtoEntity.get()
        } else throw Exception()

    }




}