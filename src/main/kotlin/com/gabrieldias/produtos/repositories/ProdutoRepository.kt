package com.gabrieldias.produtos.repositories

import com.gabrieldias.produtos.entity.ProdutoEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProdutoRepository : JpaRepository<ProdutoEntity,Int>{
}