package com.franchise.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

/**
 * Represents a product in the franchise system.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "products")
public class Product {

    /**
     * Unique identifier for the product.
     */
    @Id
    private String id;

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