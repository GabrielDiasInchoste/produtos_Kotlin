package com.gabrieldias.products.service

import com.gabrieldias.products.BuilderTest
import com.gabrieldias.products.repositories.RepositoryPostgresImpl
import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.matchers.shouldBe
import io.mockk.*
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.util.*

@ExtendWith(MockKExtension::class)
class ProductServiceTest {

    private val repositoryPostgresImpl: RepositoryPostgresImpl = mockk()
    private val builderTest = BuilderTest()

    private val productService: ProductService = ProductServiceImpl(repositoryPostgresImpl)

    @BeforeEach
    fun init() {
        clearMocks(repositoryPostgresImpl)
    }

    @Test
    fun `should get product successfully`() {
        every { repositoryPostgresImpl.findProductByIdOrThrow(any()) } returns builderTest.productDTO

        val response = shouldNotThrowAny { productService.getProduct(builderTest.productId) }

        response shouldBe builderTest.productDTO
    }

    @Test
    fun `should create product successfully`() {
        every { repositoryPostgresImpl.findProductById(any()) } returns Optional.empty()
        every { repositoryPostgresImpl.saveProduct(any()) } returns builderTest.productDTO

        val response = shouldNotThrowAny { productService.postProduct(builderTest.productDTO) }

        response shouldBe builderTest.productDTO
    }

    @Test
    fun `should throw exception with create product successfully`() {
        every { repositoryPostgresImpl.findProductById(any()) } returns Optional.of(builderTest.productEntity)

        val response = shouldThrowExactly<Exception> { productService.postProduct(builderTest.productDTO) }

        response shouldBe Exception("ProductService.postProduct - Error product already registered - productId: ${builderTest.productDTO}")
        verify(exactly = 0) { repositoryPostgresImpl.saveProduct(any()) }
    }

    @Test
    fun `should edit product successfully`() {
        every { repositoryPostgresImpl.findProductByIdOrThrow(any()) } returns builderTest.productDTO
        every { repositoryPostgresImpl.saveProduct(any()) } returns builderTest.putProductDTO

        val response = shouldNotThrowAny { productService.putProduct(builderTest.putProductDTO) }

        response shouldBe builderTest.validateProductDTO
    }

    @Test
    fun `should delete product successfully`() {
        every { repositoryPostgresImpl.delete(any()) } just Runs

        shouldNotThrowAny { productService.deleteProduct(builderTest.productId) }
        verify(exactly = 1) { repositoryPostgresImpl.delete(any()) }

    }
}