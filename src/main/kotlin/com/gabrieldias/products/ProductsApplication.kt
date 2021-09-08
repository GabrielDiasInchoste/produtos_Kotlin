package com.gabrieldias.products

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ProductsApplication {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            runApplication<ProductsApplication>(*args)
        }
    }
}
