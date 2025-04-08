package com.example.market.product.service

import com.example.market.entity.Product
import com.example.market.exception.ProductNotFoundException
import com.example.market.product.repository.ProductRepository
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.springframework.stereotype.Service

@Service
class ProductService(private val productRepository: ProductRepository) {
    suspend fun getProductById(id: String): Product {
        return productRepository.findById(id).awaitSingleOrNull()
            ?: throw ProductNotFoundException("Product with ID '$id' not found")
    }

    suspend fun getProductByName(name: String): Product {
        return productRepository.findByName(name).awaitSingleOrNull()
            ?: throw ProductNotFoundException("Product with name '$name' not found")
    }

    suspend fun addProduct(product: Product): Product {
        val existingProduct = productRepository.findByName(product.name).awaitSingleOrNull()
        if (existingProduct != null) {
            throw IllegalArgumentException("Product with name '${product.name}' already exists")
        }
        return productRepository.save(product).awaitSingle()
    }

    suspend fun deleteProductById(id: String) {
        productRepository.deleteById(id).awaitSingleOrNull()
    }
}