package com.franchise.service.impl;

import com.franchise.dto.BranchDTO;
import com.franchise.exception.ResourceNotFoundException;
import com.franchise.model.Branch;
import com.franchise.repository.BranchRepository;
import com.franchise.repository.FranchiseRepository;
import com.franchise.service.BranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class BranchServiceImpl implements BranchService {

    // Injecting the BranchRepository and FranchiseRepository using constructor injection
    private final BranchRepository branchRepository;
    private final FranchiseRepository franchiseRepository;

    /**
     * Adds a new branch to a specific franchise.
     * @param franchiseId ID of the franchise to which the branch will be added.
     * @param branchDTO Data of the branch to be added.
     * @return Mono<Branch> The created branch.
     */
    @Override
    public Mono<Branch> addBranchToFranchise(String franchiseId, BranchDTO branchDTO) {
        return franchiseRepository.findById(franchiseId)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Franchise not found with id: " + franchiseId)))
                .flatMap(franchise -> {
                    Branch branch = Branch.builder()
                            .name(branchDTO.getName())
                            .franchiseId(franchiseId)
                            .build();
                    return branchRepository.save(branch)
                            .doOnSuccess(savedBranch -> {
                                franchise.getBranches().add(savedBranch);
                                franchiseRepository.save(franchise).subscribe();
                            });
                });
    }

    /**
     * Updates the name of a specific branch.
     * @param id ID of the branch to be updated.
     * @param newName New name for the branch.
     * @return Mono<Branch> The updated branch.
     */
    @Override
    public Mono<Branch> updateBranchName(String id, String newName) {
        return branchRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Branch not found with id: " + id)))
                .map(branch -> {
                    branch.setName(newName);
                    return branch;
                })
                .flatMap(branchRepository::save);
    }

    /**
     * Retrieves a branch by its ID.
     * @param id ID of the branch to be retrieved.
     * @return Mono<Branch> The found branch.
     */
    @Override
    public Mono<Branch> getBranchById(String id) {
        return branchRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Branch not found with id: " + id)));
    }

    /**
     * Retrieves all branches of a specific franchise.
     * @param franchiseId ID of the franchise.
     * @return Flux<Branch> List of branches of the franchise.
     */
    @Override
    public Flux<Branch> getBranchesByFranchiseId(String franchiseId) {
        return branchRepository.findByFranchiseId(franchiseId);
    }
}