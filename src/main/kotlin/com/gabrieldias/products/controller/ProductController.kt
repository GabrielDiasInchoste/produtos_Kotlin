package com.gabrieldias.products.controller

import com.gabrieldias.products.dto.ProductDTO
import com.gabrieldias.products.entity.ProductEntity
import com.gabrieldias.products.service.ProductService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/v1/")
class ProductController(
    private val productService: ProductService
) {

    @GetMapping(value = ["product/{productId}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getProduct(@PathVariable(value = "productId") productId: Int): ResponseEntity<ProductDTO>? {
        return ResponseEntity.status(HttpStatus.OK)
            .contentType(MediaType.APPLICATION_JSON)
            .body(productService.getProduct(productId))
    }

    @PostMapping(value = ["product"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun postProduct(@RequestBody @Valid product: ProductDTO): ResponseEntity<ProductDTO>? {
        return ResponseEntity.status(HttpStatus.CREATED)
            .contentType(MediaType.APPLICATION_JSON)
            .body(productService.postProduct(product))
    }

    @PutMapping(value = ["product/{productId}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun putProduct(
        @PathVariable(value = "productId") productId: Int,
        @RequestBody @Valid productEntity: ProductDTO
    ): ResponseEntity<ProductDTO>? {
        return ResponseEntity.status(HttpStatus.OK)
            .contentType(MediaType.APPLICATION_JSON)
            .body(productService.putProduct(productEntity))
    }

    @DeleteMapping(value = ["product/{productId}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun deleteProduct(@PathVariable(value = "productId") productId: Int): ResponseEntity<Void> {
        productService.deleteProduct(productId)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }
}
