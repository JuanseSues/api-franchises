package com.franchise.service.impl;

import com.franchise.dto.FranchiseDTO;
import com.franchise.exception.ResourceNotFoundException;
import com.franchise.model.Franchise;
import com.franchise.repository.FranchiseRepository;
import com.franchise.service.FranchiseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class FranchiseServiceImpl implements FranchiseService {
    
    // Injecting the FranchiseRepository using constructor injection
    private final FranchiseRepository franchiseRepository;

    /**
     * Creates a new franchise.
     * @param franchiseDTO Data of the franchise to be created.
     * @return Mono<Franchise> The created franchise.
     */
    @Override
    public Mono<Franchise> createFranchise(FranchiseDTO franchiseDTO) {
        Franchise franchise = Franchise.builder()
                .name(franchiseDTO.getName())
                .build();
        return franchiseRepository.save(franchise);
    }

    /**
     * Updates the name of a specific franchise.
     * @param id ID of the franchise to be updated.
     * @param newName New name for the franchise.
     * @return Mono<Franchise> The updated franchise.
     */
    @Override
    public Mono<Franchise> updateFranchiseName(String id, String newName) {
        return franchiseRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Franchise not found with id: " + id)))
                .map(franchise -> {
                    franchise.setName(newName);
                    return franchise;
                })
                .flatMap(franchiseRepository::save);
    }

    /**
     * Retrieves a franchise by its ID.
     * @param id ID of the franchise to be retrieved.
     * @return Mono<Franchise> The found franchise.
     */
    @Override
    public Mono<Franchise> getFranchiseById(String id) {
        return franchiseRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Franchise not found with id: " + id)));
    }

    /**
     * Retrieves all franchises.
     * @return Flux<Franchise> List of all franchises.
     */
    @Override
    public Flux<Franchise> getAllFranchises() {
        return franchiseRepository.findAll();
    }
}