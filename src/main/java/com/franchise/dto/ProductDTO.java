package com.franchise.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

/**
 * Data Transfer Object for Product.
 * Used to transfer product data between layers.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    /**
     * Name of the product.
     * This field is required and cannot be blank.
     */
    @NotBlank(message = "Product name is required")
    private String name;

    /**
     * Stock quantity of the product.
     * This field cannot be negative.
     */
    @Min(value = 0, message = "Stock cannot be negative")
    private Integer stock;
}