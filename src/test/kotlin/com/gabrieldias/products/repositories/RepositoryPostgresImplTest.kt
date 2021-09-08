package com.gabrieldias.products.repositories

import com.gabrieldias.products.BuilderTest
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
class RepositoryPostgresImplTest {

    private val productRepository: ProductRepository = mockk()
    private val builderTest = BuilderTest()

    private val repositoryPostgresImpl = RepositoryPostgresImpl(productRepository)

    @BeforeEach
    fun init() {
        clearMocks(productRepository)
    }

    @Test
    fun `should save product successfully`() {
        every { productRepository.save(any()) } returns builderTest.productEntity

        val response = shouldNotThrowAny { repositoryPostgresImpl.saveProduct(builderTest.productDTO) }

        response shouldBe builderTest.productDTO
    }

    @Test
    fun `should find product successfully and not throw exception`() {
        every { productRepository.findById(any()) } returns Optional.of(builderTest.productEntity)

        val response = shouldNotThrowAny { repositoryPostgresImpl.findProductByIdOrThrow(builderTest.productId) }

        response shouldBe builderTest.productDTO
    }

    @Test
    fun `should throw exception with find product successfully`() {
        every { productRepository.findById(any()) } returns Optional.empty()

        val response = shouldThrowExactly<Exception> { repositoryPostgresImpl.findProductByIdOrThrow(builderTest.productId) }

        response shouldBe Exception("ProductService.findProductById - Error to find Product - productId: 1")
    }

    @Test
    fun `should find product successfully`() {
        every { productRepository.findById(any()) } returns Optional.of(builderTest.productEntity)

        val response = shouldNotThrowAny { repositoryPostgresImpl.findProductById(builderTest.productId) }

        response shouldBe Optional.of(builderTest.productEntity)
    }

    @Test
    fun `should delete product successfully`() {
        every { productRepository.delete(any()) } just Runs
        every { productRepository.findById(any()) } returns Optional.of(builderTest.productEntity)

        shouldNotThrowAny { repositoryPostgresImpl.delete(builderTest.productId) }
        verify(exactly = 1) { productRepository.delete(any()) }
    }
}