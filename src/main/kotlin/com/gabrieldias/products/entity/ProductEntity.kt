package com.gabrieldias.products.entity

import javax.persistence.*

@Entity
data class ProductEntity(

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "productId")
    @Id
    val id: Int,

    @Column(name = "NOME")
    val nome: String,

    @Column(name = "DESCRICAO")
    val descricao: String,

    @Column(name = "QUANTIDADE")
    val quantidate: Double,

    @Column(name = "PRECO")
    val preco: Double
)