package com.franchise.service;

import com.franchise.dto.FranchiseDTO;
import com.franchise.model.Franchise;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service interface for managing franchises.
 * Defines methods for creating, updating, and retrieving franchises.
 */
public interface FranchiseService {

    /**
     * Creates a new franchise.
     * @param franchiseDTO Data of the franchise to be created.
     * @return Mono<Franchise> The created franchise.
     */
    Mono<Franchise> createFranchise(FranchiseDTO franchiseDTO);

    /**
     * Updates the name of a specific franchise.
     * @param id ID of the franchise to be updated.
     * @param newName New name for the franchise.
     * @return Mono<Franchise> The updated franchise.
     */
    Mono<Franchise> updateFranchiseName(String id, String newName);

    /**
     * Retrieves a franchise by its ID.
     * @param id ID of the franchise to be retrieved.
     * @return Mono<Franchise> The found franchise.
     */
    Mono<Franchise> getFranchiseById(String id);

    /**
     * Retrieves all franchises.
     * @return Flux<Franchise> List of all franchises.
     */
    Flux<Franchise> getAllFranchises();
}