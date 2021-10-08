package com.gabrieldias.products.dto

import com.gabrieldias.products.entity.ProductEntity
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class ProductDTO(
    val id: Int,
    @NotBlank(message = "Nome nao informado")
    val nome: String,
    @NotBlank(message = "Descricao nao informada")
    val descricao: String,

    val quantidade: Int,

    val preco: Double
)

fun ProductDTO.toEntity(): ProductEntity {
    return ProductEntity(
        id = id,
        nome = nome,
        descricao = descricao,
        quantidade = quantidade,
        preco = preco
    )
}

fun ProductEntity.toDTO(): ProductDTO {
    return ProductDTO(
        id = id,
        nome = nome,
        descricao = descricao,
        quantidade = quantidade,
        preco = preco
    )
}