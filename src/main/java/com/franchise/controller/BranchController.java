package com.franchise.controller;

import com.franchise.dto.BranchDTO;
import com.franchise.model.Branch;
import com.franchise.service.BranchService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/branches")
@RequiredArgsConstructor
public class BranchController {

    // Injecting the BranchService using constructor injection
    private final BranchService branchService;

    /**
     * Endpoint to add a new branch to a specific franchise.
     * @param franchiseId ID of the franchise to which the branch will be added.
     * @param branchDTO Data of the branch to be added.
     * @return Mono<Branch> The created branch.
     */
    @PostMapping("/franchise/{franchiseId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Branch> addBranchToFranchise(
            @PathVariable String franchiseId,
            @Valid @RequestBody BranchDTO branchDTO) {
        return branchService.addBranchToFranchise(franchiseId, branchDTO);
    }

    /**
     * Endpoint to update the name of a specific branch.
     * @param id ID of the branch to be updated.
     * @param newName New name for the branch.
     * @return Mono<Branch> The updated branch.
     */
    @PutMapping("/{id}/name")
    public Mono<Branch> updateBranchName(@PathVariable String id, @RequestBody String newName) {
        return branchService.updateBranchName(id, newName);
    }

    /**
     * Endpoint to get a branch by its ID.
     * @param id ID of the branch to be retrieved.
     * @return Mono<Branch> The found branch.
     */
    @GetMapping("/{id}")
    public Mono<Branch> getBranchById(@PathVariable String id) {
        return branchService.getBranchById(id);
    }

    /**
     * Endpoint to get all branches of a specific franchise.
     * @param franchiseId ID of the franchise.
     * @return Flux<Branch> List of branches of the franchise.
     */
    @GetMapping("/franchise/{franchiseId}")
    public Flux<Branch> getBranchesByFranchiseId(@PathVariable String franchiseId) {
        return branchService.getBranchesByFranchiseId(franchiseId);
    }
}