package com.gabrieldias.products.service

import com.gabrieldias.products.dto.ProductDTO
import com.gabrieldias.products.repositories.RepositoryPostgresImpl
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class ProductServiceImpl(
    private val repositoryPostgresImpl: RepositoryPostgresImpl
) : ProductService {
    private val log = LoggerFactory.getLogger(javaClass)

    override fun getProduct(productId: Int): ProductDTO {
        log.info("ProductService.getProduct - start - productId: $productId")

        val productDTO = repositoryPostgresImpl.findProductByIdOrThrow(productId)

        log.info("ProductService.getProduct - end - productDTO: $productDTO")
        return productDTO

    }

    override fun putProduct(productRequest: ProductDTO): ProductDTO {
        log.info("ProductService.putProduct - start - product: $productRequest")

        repositoryPostgresImpl.findProductByIdOrThrow(productRequest.id)
        val productDTO = repositoryPostgresImpl.saveProduct(productRequest)

        log.info("ProductService.postProduct - end - product: $productDTO")
        return productDTO
    }

    override fun postProduct(productRequest: ProductDTO): ProductDTO {
        log.info("ProductService.postProduct - start - product: $productRequest")

        repositoryPostgresImpl.findProductById(productRequest.id).ifPresent {
            throw Exception("ProductService.postProduct - Error product already registered - productId: $productRequest")
        }
        val productDTO = repositoryPostgresImpl.saveProduct(productRequest)
        log.info("ProductService.postProduct - end - product: $productDTO")
        return productDTO
    }

    override fun deleteProduct(productId: Int) {
        log.info("ProductService.getProduct - start - productId: $productId")

        repositoryPostgresImpl.delete(productId)

        log.info("ProductService.getProduct - end - productId: $productId")
    }
}