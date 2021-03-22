package com.gabrieldias.produtos.service

import com.gabrieldias.produtos.entity.ProdutoEntity
import com.gabrieldias.produtos.repositories.ProdutoRepository
import lombok.extern.slf4j.Slf4j
import org.springframework.stereotype.Service

@Slf4j
@Service
class ProdutoService(
    private val produtoRepository: ProdutoRepository
) : ProdutoInterface {

    override fun getProduto(produtoId: Int): ProdutoEntity {
//        log.info("ProdutoService.getProduto - start - produtoId: {$produtoId}")

        val produtoEntity = produtoRepository.findById(produtoId)
//        log.info("ProdutoService.getProduto - end - produtoId: {$produtoId}")

        if (!produtoEntity.isEmpty) {
            return produtoEntity.get()
        } else throw Exception()

    }

    override fun putProduto(produto: ProdutoEntity): ProdutoEntity {
//        log.info("ProdutoService.putProduto - start - produtoId: {$produtoId}")

        val produtoEntity = produtoRepository.findById(produto.id)
//        log.info("ProdutoService.putProduto - end - produtoId: {$produtoId}")

        if (produtoEntity.isEmpty) {
            throw Exception()
        } else {
            // FAZER MAPPER
            return produtoRepository.save(produtoEntity.get())
        }

    }

    override fun postProduto(produto: ProdutoEntity): ProdutoEntity {
//        log.info("ProdutoService.postProduto - start - produtoId: {$produtoId}")

        val produtoEntity = produtoRepository.findById(produto.id)
//        log.info("ProdutoService.postProduto - end - produtoId: {$produtoId}")

        if (produtoEntity.isEmpty) {
            return produtoRepository.save(produtoEntity.get())
        } else throw Exception()

    }

    override fun deleteProduto(produtoId: Int) {
//        log.info("ProdutoService.getProduto - start - produtoId: {$produtoId}")

        val produtoEntity = produtoRepository.findById(produtoId)
//        log.info("ProdutoService.getProduto - end - produtoId: {$produtoId}")

        if (!produtoEntity.isEmpty) {
            produtoRepository.delete(produtoEntity.get())
        } else throw Exception()
    }

}