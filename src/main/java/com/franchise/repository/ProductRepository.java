package com.franchise.repository;

import com.franchise.model.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Product entities.
 * Extends ReactiveMongoRepository to provide CRUD operations.
 */
@Repository
public interface ProductRepository extends ReactiveMongoRepository<Product, String> {
}