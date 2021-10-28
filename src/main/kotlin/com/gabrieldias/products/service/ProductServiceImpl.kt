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
        try {

            val productDTO = repositoryPostgresImpl.findProductByIdOrThrow(productId)
            log.info("ProductService.getProduct - end - productDTO: $productDTO")
            return productDTO
        } catch (ex: Exception) {
            log.error("ProductService.getProduct - Error : ${ex.message}", ex)
            throw ex
        }
    }

    override fun putProduct(productRequest: ProductDTO): ProductDTO {
        try {
            log.info("ProductService.putProduct - start - product: $productRequest")

            repositoryPostgresImpl.findProductByIdOrThrow(productRequest.id)
            val productDTO = repositoryPostgresImpl.saveProduct(productRequest)

            log.info("ProductService.putProduct - end - product: $productDTO")
            return productDTO
        } catch (ex: Exception) {
            log.error("ProductService.putProduct - Error : ${ex.message}", ex)
            throw ex
        }
    }

    override fun postProduct(productRequest: ProductDTO): ProductDTO {
        try {
            log.info("ProductService.postProduct - start - product: $productRequest")

            repositoryPostgresImpl.findProductById(productRequest.id).ifPresent {
                throw Exception("ProductService.postProduct - Error product already registered - productId: $productRequest")
            }
            val productDTO = repositoryPostgresImpl.saveProduct(productRequest)
            log.info("ProductService.postProduct - end - product: $productDTO")
            return productDTO
        } catch (ex: Exception) {
            log.error("ProductService.postProduct - Error : ${ex.message}", ex)
            throw ex
        }
    }

    override fun deleteProduct(productId: Int) {
        try {
            log.info("ProductService.deleteProduct - start - productId: $productId")

            repositoryPostgresImpl.delete(productId)

            log.info("ProductService.deleteProduct - end - productId: $productId")
        } catch (ex: Exception) {
            log.error("ProductService.deleteProduct - Error : ${ex.message}", ex)
            throw ex
        }
    }
}