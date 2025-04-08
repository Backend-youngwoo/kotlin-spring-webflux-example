package com.example.market.product.controller

import com.example.market.entity.Product
import com.example.market.exception.ProductNotFoundException
import com.example.market.product.service.ProductService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/products")
class ProductController(private val productService: ProductService) {
    @GetMapping("/{id}")
    suspend fun getProductById(@PathVariable id: String): ResponseEntity<Any> {
        return try {
            val product = productService.getProductById(id)
            ResponseEntity.ok(product)
        } catch (e: ProductNotFoundException) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(mapOf("error" to e.message))
        }
    }

    @GetMapping("/name/{name}")
    suspend fun getProductByName(@PathVariable name: String): ResponseEntity<Any> {
        return try {
            val product = productService.getProductByName(name)
            ResponseEntity.ok(product)
        } catch (e: ProductNotFoundException) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(mapOf("error" to e.message))
        }
    }

    @PostMapping
    suspend fun addProduct(@RequestBody product: Product): ResponseEntity<Any> {
        return try {
            val savedProduct = productService.addProduct(product)
            ResponseEntity.ok(savedProduct)
        } catch (e: IllegalArgumentException) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mapOf("error" to e.message))
        }
    }

    @DeleteMapping("/{id}")
    suspend fun deleteProductById(@PathVariable id: String): ResponseEntity<Any> {
        return try {
            productService.deleteProductById(id)
            ResponseEntity.status(HttpStatus.NO_CONTENT).build()
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mapOf("error" to "Failed to delete product with id '$id'"))
        }
    }
}