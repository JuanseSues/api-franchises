package com.franchise.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;

/**
 * Data Transfer Object for Branch.
 * Used to transfer branch data between layers.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BranchDTO {

    /**
     * Name of the branch.
     * This field is required and cannot be blank.
     */
    @NotBlank(message = "Branch name is required")
    private String name;
}