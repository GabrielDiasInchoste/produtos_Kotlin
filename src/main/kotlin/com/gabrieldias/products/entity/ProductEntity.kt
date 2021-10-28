package com.gabrieldias.products.entity

import javax.persistence.*

@Entity
data class ProductEntity(

    @Id
    @Column(name = "productId")
    val id: Int,

    @Column(name = "NOME")
    val nome: String,

    @Column(name = "DESCRICAO")
    val descricao: String,

    @Column(name = "QUANTIDADE")
    val quantidade: Int,

    @Column(name = "PRECO")
    val preco: Double
)