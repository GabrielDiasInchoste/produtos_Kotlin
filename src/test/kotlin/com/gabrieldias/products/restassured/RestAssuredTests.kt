package com.gabrieldias.products.restassured

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.gabrieldias.products.BuilderTest
import io.restassured.RestAssured
import io.restassured.RestAssured.`when`
import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus

class RestAssuredTests {

    private val builderTest = BuilderTest()
    private val jacksonObjectMapper = jacksonObjectMapper()

    @BeforeEach
    fun init() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails()
    }

    @Test
    fun `should all tests successfully`() {

        val product = jacksonObjectMapper.writeValueAsString(builderTest.validateProductDTO)
        given()
            .contentType(ContentType.JSON)
            .body(product)
            .post("http://localhost:8080/v1/product")
            .then()
            .statusCode(HttpStatus.CREATED.value())
            .body("id", `is`(builderTest.validateProductDTO.id))
            .body("nome", `is`(builderTest.validateProductDTO.nome))
            .body("descricao", `is`(builderTest.validateProductDTO.descricao))
            .body("quantidade", `is`(builderTest.validateProductDTO.quantidade))


        `when`().get("http://localhost:8080/v1/product/1")
            .then()
            .statusCode(HttpStatus.OK.value())
            .body("id", `is`(builderTest.validateProductDTO.id))
            .body("nome", `is`(builderTest.validateProductDTO.nome))
            .body("descricao", `is`(builderTest.validateProductDTO.descricao))
            .body("quantidade", `is`(builderTest.validateProductDTO.quantidade))

        `when`().delete("http://localhost:8080/v1/product/1")
            .then()
            .statusCode(HttpStatus.NO_CONTENT.value())

        `when`().get("http://localhost:8080/v1/product/1")
            .then()
            .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
            .body("message", `is`("ProductService.findProductById - Error to find Product - productId: 1"))
    }

    @Test
    fun `should get product successfully`() {
        `when`().get("http://localhost:8080/v1/product/1")
            .then()
            .statusCode(HttpStatus.OK.value())
            .body("id", `is`(builderTest.validateProductDTO.id))
            .body("nome", `is`(builderTest.validateProductDTO.nome))
            .body("descricao", `is`(builderTest.validateProductDTO.descricao))
            .body("quantidade", `is`(builderTest.validateProductDTO.quantidade))
    }

    @Test
    fun `should post product successfully`() {
        given()
            .contentType(ContentType.JSON)
            .body(jacksonObjectMapper.writeValueAsString(builderTest.validateProductDTO))
            .post("http://localhost:8080/v1/product")
            .then()
            .statusCode(HttpStatus.CREATED.value())
            .body("nome", `is`(builderTest.validateProductDTO.nome))
    }

    @Test
    fun `should delete product successfully`() {
        `when`().delete("http://localhost:8080/v1/product/1")
            .then()
            .statusCode(HttpStatus.NO_CONTENT.value())
    }
}
