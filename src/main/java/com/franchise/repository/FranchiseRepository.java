package com.franchise.repository;

import com.franchise.model.Franchise;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

/**
 * Repository interface for Franchise entities.
 * Extends ReactiveMongoRepository to provide CRUD operations.
 */
@Repository
public interface FranchiseRepository extends ReactiveMongoRepository<Franchise, String> {

    /**
     * Custom query method to find a franchise by its name.
     * @param name The name of the franchise.
     * @return Mono<Franchise> A reactive stream containing the franchise with the specified name.
     */
    Mono<Franchise> findByName(String name);
}