package com.gabrieldias.produtos.entity

import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity
data class ProdutoEntity (

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "produtoId")
    @Id
    val id : Int,

    @Column(name = "NOME")
    @NotBlank(message = "Nome nao informado")
    var nome : String? = null,

    @Column(name = "DESCRICAO")
    @NotBlank(message = "Descricao nao informada")
    var descricao : String? = null,

    @Column(name = "QUANTIDADE")
    @NotNull(message = "Quantidade nao informado")
    var quandidate : Double? = null,

    @Column(name = "PRECO")
    @NotNull(message = "Preco nao informado")
    var preco : Double? = null,

    @Column(name = "TIPO")
    @NotBlank(message = "Tipo nao informado")
    var tipo : String? = null

)