package com.gabrieldias.products.repositories

import com.gabrieldias.products.dto.ProductDTO
import com.gabrieldias.products.dto.toDTO
import com.gabrieldias.products.dto.toEntity
import com.gabrieldias.products.entity.ProductEntity
import java.util.*

class RepositoryPostgresImpl(
    private val productRepository: ProductRepository
) {
    fun saveProduct(productDTO: ProductDTO): ProductDTO {
        return productRepository.save(productDTO.toEntity()).toDTO()
    }

    fun findProductByIdOrThrow(productId: Int): ProductDTO {
        return productRepository.findById(productId).takeIf { it.isPresent }?.get()?.toDTO()
            ?: throw Exception("ProductService.findProductById - Error to find Product - productId: $productId")
    }

    fun findProductById(productId: Int): Optional<ProductEntity> {
        return productRepository.findById(productId)
    }

    fun delete(productId: Int) {
        val productDTO = findProductByIdOrThrow(productId)
        productRepository.delete(productDTO.toEntity())
    }
}