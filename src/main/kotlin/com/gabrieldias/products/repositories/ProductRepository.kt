package com.gabrieldias.products.repositories

import com.gabrieldias.products.entity.ProductEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : JpaRepository<ProductEntity,Int>{}