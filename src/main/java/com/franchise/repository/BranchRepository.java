package com.franchise.repository;

import com.franchise.model.Branch;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

/**
 * Repository interface for Branch entities.
 * Extends ReactiveMongoRepository to provide CRUD operations.
 */
@Repository
public interface BranchRepository extends ReactiveMongoRepository<Branch, String> {

    /**
     * Custom query method to find all branches by franchise ID.
     * @param franchiseId The ID of the franchise.
     * @return Flux<Branch> A reactive stream of branches belonging to the specified franchise.
     */
    Flux<Branch> findByFranchiseId(String franchiseId);
}