package com.franchise.service;

import com.franchise.dto.BranchDTO;
import com.franchise.model.Branch;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service interface for managing branches.
 * Defines methods for adding, updating, and retrieving branches.
 */
public interface BranchService {

    /**
     * Adds a new branch to a specific franchise.
     * @param franchiseId ID of the franchise to which the branch will be added.
     * @param branchDTO Data of the branch to be added.
     * @return Mono<Branch> The created branch.
     */
    Mono<Branch> addBranchToFranchise(String franchiseId, BranchDTO branchDTO);

    /**
     * Updates the name of a specific branch.
     * @param id ID of the branch to be updated.
     * @param newName New name for the branch.
     * @return Mono<Branch> The updated branch.
     */
    Mono<Branch> updateBranchName(String id, String newName);

    /**
     * Retrieves a branch by its ID.
     * @param id ID of the branch to be retrieved.
     * @return Mono<Branch> The found branch.
     */
    Mono<Branch> getBranchById(String id);

    /**
     * Retrieves all branches of a specific franchise.
     * @param franchiseId ID of the franchise.
     * @return Flux<Branch> List of branches of the franchise.
     */
    Flux<Branch> getBranchesByFranchiseId(String franchiseId);
}