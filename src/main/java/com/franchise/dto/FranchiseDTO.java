package com.franchise.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;

/**
 * Data Transfer Object for Franchise.
 * Used to transfer franchise data between layers.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FranchiseDTO {

    /**
     * Name of the franchise.
     * This field is required and cannot be blank.
     */
    @NotBlank(message = "Franchise name is required")
    private String name;
}