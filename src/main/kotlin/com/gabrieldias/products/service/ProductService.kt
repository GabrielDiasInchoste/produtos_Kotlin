package com.gabrieldias.products.service

import com.gabrieldias.products.dto.ProductDTO
import com.gabrieldias.products.dto.toDTO
import com.gabrieldias.products.dto.toEntity
import com.gabrieldias.products.entity.ProductEntity
import com.gabrieldias.products.repositories.ProductRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class ProductService(
    private val productRepository: ProductRepository
) : ProductInterface {
    private val log = LoggerFactory.getLogger(javaClass)

    override fun getProduct(productId: Int): ProductDTO {
        log.info("ProductService.getProduct - start - productId: $productId")

        val productEntity = findProductById(productId)
        log.info("ProductService.getProduct - end - productEntity: $productEntity")

        return productEntity.toDTO()

    }

    override fun putProduct(productRequest: ProductDTO): ProductDTO {
        log.info("ProductService.putProduct - start - product: $productRequest")

        findProductById(productRequest.id)

        val product = productRepository.save(productRequest.toEntity())
        log.info("ProductService.postProduct - end - product: $product")
        return product.toDTO()
    }

    override fun postProduct(productRequest: ProductDTO): ProductDTO {
        log.info("ProductService.postProduct - start - product: $productRequest")

        productRepository.findById(productRequest.id).ifPresent {
            throw Exception("ProductService.postProduct - Error product already registered - productId: $productRequest")
        }
        val product = productRepository.save(productRequest.toEntity())

        log.info("ProductService.postProduct - end - product: $product")
        return product.toDTO()
    }

    override fun deleteProduct(productId: Int) {
        log.info("ProductService.getProduct - start - productId: $productId")

        val productEntity = findProductById(productId)
        log.info("ProductService.getProduct - end - productId: $productId")

        productRepository.delete(productEntity)
    }

    private fun findProductById(productId: Int): ProductEntity {
        return productRepository.findById(productId).takeIf { it.isPresent }?.get()
            ?: throw Exception("ProductService.findProductById - Error to find Product - productId: $productId")
    }

}