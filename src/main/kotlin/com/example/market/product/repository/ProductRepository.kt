package com.example.market.product.repository

import com.example.market.entity.Product
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface ProductRepository : ReactiveMongoRepository<Product, String> {
    fun findByName(name: String): Mono<Product>
}