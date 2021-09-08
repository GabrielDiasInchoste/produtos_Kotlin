package com.gabrieldias.products.service

import com.gabrieldias.products.dto.ProductDTO

interface ProductService {
    fun getProduct(productId: Int): ProductDTO

    fun putProduct(productRequest: ProductDTO): ProductDTO

    fun postProduct(productRequest: ProductDTO): ProductDTO

    fun deleteProduct(productId: Int)
}