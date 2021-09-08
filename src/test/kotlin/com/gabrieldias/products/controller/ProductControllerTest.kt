package com.gabrieldias.products.controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.gabrieldias.products.BuilderTest
import com.gabrieldias.products.service.ProductServiceImpl
import com.ninjasquad.springmockk.MockkBean
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@AutoConfigureMockMvc
@WebMvcTest(ProductController::class)
@ExtendWith(SpringExtension::class)
@ContextConfiguration(classes = [ProductController::class])
class ProductControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @MockkBean
    lateinit var productService: ProductServiceImpl

    private val builderTest = BuilderTest()
    private val objectMapper = jacksonObjectMapper()

    @Test
    fun `should get product successfully`() {
        every { productService.getProduct(any()) } returns builderTest.productDTO

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/product/${builderTest.productId}"))
            .andExpect(status().isOk)
            .andReturn()
    }

    @Test
    fun `should post product successfully`() {
        every { productService.postProduct(any()) } returns builderTest.productDTO

        mockMvc.perform(
            MockMvcRequestBuilders.post("/v1/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(builderTest.productDTO))
        )
            .andExpect(status().isCreated)
            .andReturn()
    }

    @Test
    fun `should put product successfully`() {
        every { productService.putProduct(any()) } returns builderTest.productDTO

        mockMvc.perform(
            MockMvcRequestBuilders.put("/v1/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(builderTest.productDTO))
        )
            .andExpect(status().isOk)
            .andReturn()
    }

    @Test
    fun `should delete product successfully`() {
        every { productService.deleteProduct(any()) } just Runs

        mockMvc.perform(MockMvcRequestBuilders.delete("/v1/product/${builderTest.productId}"))
            .andExpect(status().isNoContent)
            .andReturn()
    }
}