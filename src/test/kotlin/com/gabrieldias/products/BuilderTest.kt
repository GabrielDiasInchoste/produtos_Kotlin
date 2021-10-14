package com.gabrieldias.products

import com.gabrieldias.products.dto.ProductDTO
import com.gabrieldias.products.entity.ProductEntity

class BuilderTest {
    val productId = 1

    val productDTO = ProductDTO(
        id = 1,
        nome = "TV",
        descricao = "TV 50 Polegada",
        quantidade = 10,
        preco = 1099.50
    )

    val putProductDTO = ProductDTO(
        id = 1,
        nome = "Cadeira",
        descricao = "Cadeira Gamer",
        quantidade = 15,
        preco = 899.99
    )

    val validateProductDTO = ProductDTO(
        id = 1,
        nome = "Cadeira",
        descricao = "Cadeira Gamer",
        quantidade = 15,
        preco = 899.99
    )

    val productEntity = ProductEntity(
        id = 1,
        nome = "TV",
        descricao = "TV 50 Polegada",
        quantidade = 10,
        preco = 1099.50
    )

}