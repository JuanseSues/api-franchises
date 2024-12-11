package com.franchise.controller;

import com.franchise.dto.FranchiseDTO;
import com.franchise.model.Franchise;
import com.franchise.service.FranchiseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/franchises")
@RequiredArgsConstructor
public class FranchiseController {

    // Injecting the FranchiseService using constructor injection
    private final FranchiseService franchiseService;

    /**
     * Endpoint to create a new franchise.
     * @param franchiseDTO Data of the franchise to be created.
     * @return Mono<Franchise> The created franchise.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Franchise> createFranchise(@Valid @RequestBody FranchiseDTO franchiseDTO) {
        return franchiseService.createFranchise(franchiseDTO);
    }

    /**
     * Endpoint to update the name of a specific franchise.
     * @param id ID of the franchise to be updated.
     * @param newName New name for the franchise.
     * @return Mono<Franchise> The updated franchise.
     */
    @PutMapping("/{id}/name")
    public Mono<Franchise> updateFranchiseName(@PathVariable String id, @RequestBody String newName) {
        return franchiseService.updateFranchiseName(id, newName);
    }

    /**
     * Endpoint to get a franchise by its ID.
     * @param id ID of the franchise to be retrieved.
     * @return Mono<Franchise> The found franchise.
     */
    @GetMapping("/{id}")
    public Mono<Franchise> getFranchiseById(@PathVariable String id) {
        return franchiseService.getFranchiseById(id);
    }

    /**
     * Endpoint to get all franchises.
     * @return Flux<Franchise> List of all franchises.
     */
    @GetMapping
    public Flux<Franchise> getAllFranchises() {
        return franchiseService.getAllFranchises();
    }
}